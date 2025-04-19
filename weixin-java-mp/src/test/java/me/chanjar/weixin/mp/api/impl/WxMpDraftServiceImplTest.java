package me.chanjar.weixin.mp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import me.chanjar.weixin.mp.bean.draft.*;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 草稿箱单元测试.
 *
 * @author dragon
 * created on  2021-10-22
 */
@Guice(modules = ApiTestModule.class)
public class WxMpDraftServiceImplTest {

  /**
   * 1.先上传一个永久图片素材：me.chanjar.weixin.mp.api.impl.WxMpMaterialServiceImplTest.testUploadMaterial
   * 2.后续图文需要设置一个永久素材id
   */
  final String thumbMediaId = "zUUtT8ZYeXzZ4slFbtnAkh7Yd-f45DbFoF9ERzVC6s4";

  /**
   * 新增草稿后返回的id，后续查询、修改、删除，获取等需要使用
   */
  final String mediaId = "zUUtT8ZYeXzZ4slFbtnAkpgGKyqnTsjtUvMdVBRWJVk";

  @Inject
  protected WxMpService wxService;

  @Test
  public void testAddDraft() throws WxErrorException {
    // {"mediaId":"zUUtT8ZYeXzZ4slFbtnAkh7Yd-f45DbFoF9ERzVC6s4","url":"http://mmbiz.qpic.cn/mmbiz_jpg/fLtyChQRfH84IyicNUbGt3l3IlHxJRibSFz7Tky0ibmzKykzVbo9tZGYhXQGJ2npFtDPbvPhKYxBz6JxkYIibTmUicQ/0?wx_fmt=jpeg"}
    this.wxService.getDraftService().addDraft("标题", "图文消息的具体内容", thumbMediaId);
    // 【响应数据】：{"media_id":"zUUtT8ZYeXzZ4slFbtnAks-nZeGiPQmwvhShTh72CqM","item":[]}
  }

  @Test
  public void testAddGuide_another() throws WxErrorException {
    List<WxMpDraftArticles> draftArticleList = new ArrayList<>();
    WxMpDraftArticles draftArticle = WxMpDraftArticles.builder()
      .title("新建草稿-对象形式")
      .author("dragon")
      .digest("图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空")
      .content("图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS")
      .contentSourceUrl("https://github.com/Wechat-Group/WxJava")
      .thumbMediaId(thumbMediaId)
      // 显示封面、打开评论、所有人可评论
      .showCoverPic(1).needOpenComment(1).onlyFansCanComment(0)
      .picCrop2351("0.1945_0_1_0.5236")
      .picCrop11("0.1945_0_1_0.5236")
      .build();
    draftArticleList.add(draftArticle);

    WxMpAddDraft addDraft = WxMpAddDraft.builder().articles(draftArticleList).build();
    String mediaId = this.wxService.getDraftService().addDraft(addDraft);
    // 【响应数据】：{"media_id":"zUUtT8ZYeXzZ4slFbtnAkpgGKyqnTsjtUvMdVBRWJVk","item":[]}
    assertThat(mediaId).isNotNull();
  }

  @Test
  public void testGetDraft() throws WxErrorException {
    final WxMpDraftInfo draftInfo = this.wxService.getDraftService().getDraft(mediaId);
    assertThat(draftInfo).isNotNull();
    // 【响应数据】：{"news_item":[{"title":"标题","author":"","digest":"图文消息的具体内容","content":"图文消息的具体内容","content_source_url":"","thumb_media_id":"zUUtT8ZYeXzZ4slFbtnAkh7Yd-f45DbFoF9ERzVC6s4","show_cover_pic":1,"url":"http:\/\/mp.weixin.qq.com\/s?__biz=Mzk0OTI5MzM1OQ==&mid=100000006&idx=1&sn=89903965ae5ebd6014903c7c5ca34daa&chksm=435bd946742c5050d18da32289904db5ede8bbd157d181438231a1762b85030419b3c0ed4c00#rd","thumb_url":"http:\/\/mmbiz.qpic.cn\/mmbiz_jpg\/fLtyChQRfH84IyicNUbGt3l3IlHxJRibSFz7Tky0ibmzKykzVbo9tZGYhXQGJ2npFtDPbvPhKYxBz6JxkYIibTmUicQ\/0?wx_fmt=jpeg","need_open_comment":0,"only_fans_can_comment":0}],"create_time":1634886802,"update_time":1634886802}
  }

  @Test
  public void testUpdateDraft() throws WxErrorException {
    WxMpDraftArticles draftArticles = WxMpDraftArticles.builder()
      .title("新标题").content("新图文消息的具体内容").thumbMediaId(thumbMediaId)
      .picCrop2351("0.1945_0_1_0.5236")
      .picCrop11("0.1945_0_1_0.5236")
      .build();
    WxMpUpdateDraft updateDraft = WxMpUpdateDraft.builder()
      .mediaId(mediaId)
      .index(0)
      .articles(draftArticles)
      .build();
    Boolean updateDraftResult = this.wxService.getDraftService().updateDraft(updateDraft);
    // assertThat(updateDraftResult).isTrue();
    assertThat(updateDraftResult).isTrue();
  }

  @Test
  public void testDelDraft() throws WxErrorException {
    Boolean delDraftResult = this.wxService.getDraftService().delDraft(mediaId);
    // 【响应数据】：{"errcode":0,"errmsg":"ok"}
    assertThat(delDraftResult).isTrue();
  }

  @Test
  public void testListDraft() throws WxErrorException {
    WxMpDraftList draftList = this.wxService.getDraftService().listDraft(0, 10);
    /*
    【响应数据】：{"item":[{"media_id":"zUUtT8ZYeXzZ4slFbtnAks-nZeGiPQmwvhShTh72CqM",
    "content":{
      "news_item":
      [
        {"title":"标题","author":"","digest":"图文消息的具体内容","content":"图文消息的具体内容",
        "content_source_url":"","thumb_media_id":"zUUtT8ZYeXzZ4slFbtnAkh7Yd-f45DbFoF9ERzVC6s4",
        "show_cover_pic":1,"url":"http:\/\/mp.weixin.qq.com\/s?__biz=Mzk0OTI5MzM1?wx_fmt=jpeg",
        "need_open_comment":0,"only_fans_can_comment":0}],
        "create_time":1634886802,"update_time":1634886802},"update_time":1634886802}
      ]
    ,"total_count":1,"item_count":1}

    */
    assertThat(draftList).isNotNull();
  }

  @Test
  public void testCountDraft() throws WxErrorException {
    Long countDraft = this.wxService.getDraftService().countDraft();
    // 【响应数据】：{"total_count":1}
    assertThat(countDraft).isNotNull();
  }

}

