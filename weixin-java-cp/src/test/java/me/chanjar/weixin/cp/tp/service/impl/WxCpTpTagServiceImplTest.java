package me.chanjar.weixin.cp.tp.service.impl;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpTpTag;
import me.chanjar.weixin.cp.bean.WxCpTpTagAddOrRemoveUsersResult;
import me.chanjar.weixin.cp.bean.WxCpTpTagGetResult;
import me.chanjar.weixin.cp.config.WxCpTpConfigStorage;
import me.chanjar.weixin.cp.config.impl.WxCpTpDefaultConfigImpl;
import me.chanjar.weixin.cp.tp.service.WxCpTpTagService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.collections.CollectionUtils;

import java.util.Arrays;
import java.util.List;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Tag.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

/**
 * 企业微信-第三方开发-标签管理相关测试
 *
 * @author zhangq
 * @since 2021 /2/15 9:14
 */
public class WxCpTpTagServiceImplTest {

  @Mock
  private WxCpTpServiceApacheHttpClientImpl wxCpTpService;

  private WxCpTpConfigStorage configStorage;

  private WxCpTpTagService wxCpTpTagService;

  /**
   * Sets up.
   */
  @BeforeClass
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    configStorage = new WxCpTpDefaultConfigImpl();
    when(wxCpTpService.getWxCpTpConfigStorage()).thenReturn(configStorage);
    wxCpTpTagService = new WxCpTpTagServiceImpl(wxCpTpService);
  }

  /**
   * Test create.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testCreate() throws WxErrorException {
    String url = configStorage.getApiUrl(TAG_CREATE);
    String tagName = "test_tag_name";
    int tagId = 12;
    String result = "{\"errcode\":0,\"errmsg\":\"created\",\"tagid\":12}";
    when(wxCpTpService.post(eq(url), any(String.class))).thenReturn(result);

    assertEquals(wxCpTpTagService.create(tagName, tagId), String.valueOf(tagId));
  }

  /**
   * Test list all.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testListAll() throws WxErrorException {
    String url = configStorage.getApiUrl(TAG_LIST);
    String result = "{\"errcode\":0,\"errmsg\":\"ok\",\"taglist\":[{\"tagid\":1,\"tagname\":\"a\"},{\"tagid\":2," +
      "\"tagname\":\"b\"}]}";
    when(wxCpTpService.get(eq(url), anyString())).thenReturn(result);

    List<WxCpTpTag> wxCpTpTags = wxCpTpTagService.listAll();
    assertNotNull(wxCpTpTags);
    assertTrue(CollectionUtils.hasElements(wxCpTpTags));
    assertEquals(wxCpTpTags.get(0).getTagId(), "1");
    assertEquals(wxCpTpTags.get(1).getTagName(), "b");
  }

  /**
   * Test get.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGet() throws WxErrorException {
    String tagId = "anyTagId";
    String url = String.format(configStorage.getApiUrl(TAG_GET), tagId);
    String result = "{\"errcode\":0,\"errmsg\":\"ok\",\"tagname\":\"乒乓球协会\",\"userlist\":[{\"userid\":\"zhangsan\"," +
      "\"name\":\"李四\"}],\"partylist\":[2]}";
    when(wxCpTpService.get(eq(url), anyString())).thenReturn(result);

    WxCpTpTagGetResult getResult = wxCpTpTagService.get(tagId);
    assertEquals(getResult.getTagname(), "乒乓球协会");
    assertEquals((int) getResult.getPartylist().get(0), 2);
    assertEquals(getResult.getUserlist().get(0).getUserId(), "zhangsan");
  }

  /**
   * Test add users 2 tag.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testAddUsers2Tag() throws WxErrorException {
    String tagId = "anyTagId";
    String url = configStorage.getApiUrl(TAG_ADD_TAG_USERS);
    // 成功时返回对象
    String success = "{\"errcode\":0,\"errmsg\":\"ok\"}";
    when(wxCpTpService.post(eq(url), anyString())).thenReturn(success);
    WxCpTpTagAddOrRemoveUsersResult postResult = wxCpTpTagService
      .addUsers2Tag(tagId, Arrays.asList("usr1", "usr2"), Arrays.asList("dept1", "dept2"));
    assertEquals((int) postResult.getErrCode(), 0);
    assertNull(postResult.getInvalidParty());
    assertNull(postResult.getInvalidUsers());

    // 部分失败时返回对象
    String partFailure = "{\"errcode\":0,\"errmsg\":\"ok\",\"invalidlist\":\"usr1|usr2\",\"invalidparty\":[2,3,4]}";
    when(wxCpTpService.post(eq(url), anyString())).thenReturn(partFailure);
    postResult = wxCpTpTagService.addUsers2Tag(tagId, Arrays.asList("usr1", "usr2"), Arrays.asList("dept1", "dept2"));
    assertEquals((int) postResult.getErrCode(), 0);
    assertEquals(postResult.getInvalidUserList().size(), 2);
    assertEquals(postResult.getInvalidUserList().get(1), "usr2");
    assertEquals(postResult.getInvalidParty().length, 3);
    assertEquals(postResult.getInvalidParty()[1], "3");

    // 全部失败时返回对象
    String allFailure = "{\"errcode\":40070,\"errmsg\":\"all list invalid \"}";
    when(wxCpTpService.post(eq(url), anyString())).thenReturn(allFailure);
    postResult = wxCpTpTagService.addUsers2Tag(tagId, Arrays.asList("usr1", "usr2"), Arrays.asList("dept1", "dept2"));
    assertEquals((int) postResult.getErrCode(), 40070);
    assertNull(postResult.getInvalidParty());
    assertNull(postResult.getInvalidUsers());
  }

  /**
   * Test remove users from tag.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testRemoveUsersFromTag() throws WxErrorException {
    String tagId = "anyTagId";
    String url = configStorage.getApiUrl(TAG_DEL_TAG_USERS);
    // 成功时返回对象
    String success = "{\"errcode\":0,\"errmsg\":\"ok\"}";
    when(wxCpTpService.post(eq(url), anyString())).thenReturn(success);
    WxCpTpTagAddOrRemoveUsersResult postResult = wxCpTpTagService
      .removeUsersFromTag(tagId, Arrays.asList("usr1", "usr2"), Arrays.asList("dept1", "dept2"));
    assertEquals((int) postResult.getErrCode(), 0);
    assertNull(postResult.getInvalidParty());
    assertNull(postResult.getInvalidUsers());

    // 部分失败时返回对象
    String partFailure = "{\"errcode\":0,\"errmsg\":\"ok\",\"invalidlist\":\"usr1|usr2\",\"invalidparty\":[2,3,4]}";
    when(wxCpTpService.post(eq(url), anyString())).thenReturn(partFailure);
    postResult = wxCpTpTagService.removeUsersFromTag(tagId, Arrays.asList("usr1", "usr2"), Arrays.asList("dept1",
      "dept2"));
    assertEquals((int) postResult.getErrCode(), 0);
    assertEquals(postResult.getInvalidUserList().size(), 2);
    assertEquals(postResult.getInvalidUserList().get(1), "usr2");
    assertEquals(postResult.getInvalidParty().length, 3);
    assertEquals(postResult.getInvalidParty()[1], "3");

    // 全部失败时返回对象
    String allFailure = "{\"errcode\":40070,\"errmsg\":\"all list invalid \"}";
    when(wxCpTpService.post(eq(url), anyString())).thenReturn(allFailure);
    postResult = wxCpTpTagService.removeUsersFromTag(tagId, Arrays.asList("usr1", "usr2"), Arrays.asList("dept1",
      "dept2"));
    assertEquals((int) postResult.getErrCode(), 40070);
    assertNull(postResult.getInvalidParty());
    assertNull(postResult.getInvalidUsers());
  }
}
