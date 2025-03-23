package me.chanjar.weixin.cp.api;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.oa.wedrive.*;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.demo.WxCpDemoInMemoryConfigStorage;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * 微盘测试类.
 * 官方文档：https://developer.work.weixin.qq.com/document/path/93654
 *
 * @author Wang_Wong
 */
@Slf4j
public class WxCpOaWeDriveServiceTest {

  private static WxCpConfigStorage wxCpConfigStorage;
  private static WxCpService cpService;

  /**
   * Test.
   *
   * @throws Exception the exception
   */
  @Test
  public void test() throws Exception {

    InputStream inputStream = ClassLoader.getSystemResourceAsStream("test-config.xml");
    WxCpDemoInMemoryConfigStorage config = WxCpDemoInMemoryConfigStorage.fromXml(inputStream);

    wxCpConfigStorage = config;
    cpService = new WxCpServiceImpl();
    cpService.setWxCpConfigStorage(config);

    String createSpace = "{\"userid\":\"USERID\",\"space_name\":\"SPACE_NAME\",\"auth_info\":[{\"type\":1," +
      "\"userid\":\"USERID\",\"auth\":2},{\"type\":2,\"departmentid\":2,\"auth\":1}]}";
    WxCpSpaceCreateRequest wxCpSpaceCreateRequest = WxCpSpaceCreateRequest.fromJson(createSpace);
    log.info(wxCpSpaceCreateRequest.toJson());

    String uId = "WangKai";
    String spId = "s.ww45d3e188865aca30.652091685u4h";
    // 空间的文件id
    String fileId = "s.ww45d3e188865aca30.652091685u4h_f.652344507ysDL";
    String fileId2 = "s.ww45d3e188865aca30.652091685u4h_f.652696024TU4P";


    /*
     * 获取分享链接
     */
    WxCpFileShare fileShare = cpService.getOaWeDriveService().fileShare(uId, fileId2);
    log.info("获取分享链接返回结果为：{}", fileShare.toJson());

    /*
     * 分享设置
     */
    WxCpBaseResp fileSetting = cpService.getOaWeDriveService().fileSetting(uId, fileId2, 2, 1);
    log.info("分享设置返回结果为：{}", fileSetting.toJson());

    /*
     * 删除指定人
     */
    WxCpFileAclDelRequest aclDelRequest = new WxCpFileAclDelRequest();
    aclDelRequest.setUserId(uId);
    aclDelRequest.setFileId(fileId2);

    ArrayList<WxCpFileAclDelRequest.AuthInfo> aclDelList = Lists.newArrayList();

    WxCpFileAclDelRequest.AuthInfo aclDelAuthInfo = new WxCpFileAclDelRequest.AuthInfo();
    aclDelAuthInfo.setType(1);
    aclDelAuthInfo.setUserId(uId);

    aclDelList.add(aclDelAuthInfo);
    aclDelRequest.setAuthInfo(aclDelList);

    WxCpBaseResp aclDel = cpService.getOaWeDriveService().fileAclDel(aclDelRequest);
    log.info("删除指定人返回结果为：{}", aclDel.toJson());

    /*
     * 新增指定人
     */
    WxCpFileAclAddRequest fileAclAdd = new WxCpFileAclAddRequest();
    fileAclAdd.setUserId(uId);
    fileAclAdd.setFileId(fileId2);
    var authInfoData = new WxCpFileAclAddRequest.AuthInfo();
    authInfoData.setType(1);
    authInfoData.setAuth(1);
    authInfoData.setUserId(uId);

    ArrayList<WxCpFileAclAddRequest.AuthInfo> authList = Lists.newArrayList();
    authList.add(authInfoData);
    fileAclAdd.setAuthInfo(authList);

    WxCpBaseResp result = cpService.getOaWeDriveService().fileAclAdd(fileAclAdd);
    log.info("返回结果为：{}", result.toJson());

    /*
     * 删除文件
     */
    ArrayList<String> fileIds = Lists.newArrayList();
    fileIds.add(fileId);
    WxCpBaseResp fileDelete = cpService.getOaWeDriveService().fileDelete(fileIds);
    log.info("删除文件数据为：{}", fileDelete.toJson());

    /*
      文件信息
     */
    WxCpFileInfo fileInfo = cpService.getOaWeDriveService().fileInfo(fileId);
    log.info("fileInfo数据为：{}", fileInfo.toJson());

    /*
      移动文件
     */
    WxCpFileMoveRequest fileMoveRequest = new WxCpFileMoveRequest();
    fileMoveRequest.setFatherId(spId);
    fileMoveRequest.setReplace(true);
    fileMoveRequest.setFileId(new String[]{fileId});

    WxCpFileMove fileMove = cpService.getOaWeDriveService().fileMove(fileMoveRequest);
    log.info("fileMove数据为：{}", fileMove.toJson());

    /*
      新建文件/微文档
     */
    WxCpFileCreate fileCreate = cpService.getOaWeDriveService().fileCreate(spId, spId, 3, "新建微文档1");
    log.info("新建文件/微文档：{}", fileCreate.toJson());

    /*
      下载文件
     */
    WxCpFileDownload fileDownload = cpService.getOaWeDriveService().fileDownload(uId, fileId);
    log.info("下载文件为：{}", fileDownload.toJson());

    /*
      上传文件
     */
    WxCpFileUploadRequest fileUploadRequest = new WxCpFileUploadRequest();
    fileUploadRequest.setSpaceId(spId);
    fileUploadRequest.setFatherId(spId);
    fileUploadRequest.setFileName("第一个文件");

    // 将文件转成base64字符串
    File file = new File("D:/info.log.2022-05-07.0.log");
//    File file = new File("D:/16.png");
    FileInputStream inputFile = new FileInputStream(file);
    byte[] buffer = new byte[(int) file.length()];
    inputFile.read(buffer);
    inputFile.close();
    String encodeBase64Content = Base64.getEncoder().encodeToString(buffer);
    fileUploadRequest.setFileBase64Content(encodeBase64Content);

    WxCpFileUpload fileUpload = cpService.getOaWeDriveService().fileUpload(fileUploadRequest);
    log.info("上传文件为：{}", fileUpload.toJson());

    /*
      重命名文件
     */
    WxCpFileRename fileRename = cpService.getOaWeDriveService().fileRename(fileUpload.getFileId(), "新的名字呢");
    log.info("重命名文件：{}", fileRename.toJson());

    /*
      获取文件列表
     */
    WxCpFileListRequest fileListRequest = new WxCpFileListRequest();
    fileListRequest.setSpaceId(spId);
    fileListRequest.setFatherId(spId);
    fileListRequest.setSortType(1);
    fileListRequest.setStart(0);
    fileListRequest.setLimit(100);

    WxCpFileList fileList = cpService.getOaWeDriveService().fileList(fileListRequest);
    log.info("获取文件列表为：{}", fileList.toJson());

    /*
     * 权限管理
     */
    WxCpSpaceSettingRequest spaceSettingRequest = new WxCpSpaceSettingRequest();
    spaceSettingRequest.setUserId(uId);
    spaceSettingRequest.setSpaceId(spId);
//    spaceSettingRequest.setEnableWatermark(false);
    spaceSettingRequest.setAddMemberOnlyAdmin(true);
    spaceSettingRequest.setEnableShareUrl(false);
    spaceSettingRequest.setShareUrlNoApprove(true);
    spaceSettingRequest.setShareUrlNoApproveDefaultAuth(2);

    WxCpBaseResp spaceSetting = cpService.getOaWeDriveService().spaceSetting(spaceSettingRequest);
    log.info("权限管理信息为：{}", spaceSetting.toJson());

    /*
     * 获取邀请链接
     */
    WxCpSpaceShare spaceShare = cpService.getOaWeDriveService().spaceShare(uId, spId);
    log.info("获取邀请链接信息为：{}", spaceShare.toJson());

    /*
     * 获取空间信息
     */
    WxCpSpaceInfo data = cpService.getOaWeDriveService().spaceInfo(uId, spId);
    log.info("获取空间信息为：{}", data.toJson());

    /*
     * 移除成员/部门
     */
    WxCpSpaceAclDelRequest spaceAclDelRequest = new WxCpSpaceAclDelRequest();
    spaceAclDelRequest.setUserId(uId);
    spaceAclDelRequest.setSpaceId(spId);

    // 被移除的空间成员信息
    WxCpSpaceAclDelRequest.AuthInfo delAuthInfo = new WxCpSpaceAclDelRequest.AuthInfo();
    delAuthInfo.setType(1);
    delAuthInfo.setUserId("MiaoMiu99");

    List<WxCpSpaceAclDelRequest.AuthInfo> delAuthInfoList = new ArrayList<>();
    delAuthInfoList.add(delAuthInfo);

    spaceAclDelRequest.setAuthInfo(delAuthInfoList);
    WxCpBaseResp spaceAclDel = cpService.getOaWeDriveService().spaceAclDel(spaceAclDelRequest);
    log.info("移除成员/部门，返回数据为：{}", spaceAclDel.toJson());

    /*
     * 添加成员/部门
     * https://developer.work.weixin.qq.com/document/path/93656
     */
    WxCpSpaceAclAddRequest spaceAclAddRequest = new WxCpSpaceAclAddRequest();
    spaceAclAddRequest.setUserId(uId);
    spaceAclAddRequest.setSpaceId(spId);

    List<WxCpSpaceAclAddRequest.AuthInfo> authInfoList = new ArrayList<>();
    // 被添加的空间成员信息
    WxCpSpaceAclAddRequest.AuthInfo authInfo = new WxCpSpaceAclAddRequest.AuthInfo();
    authInfo.setAuth(2);
    authInfo.setType(1);
    authInfo.setUserId("MiaoMiu99");

    authInfoList.add(authInfo);
    spaceAclAddRequest.setAuthInfo(authInfoList);

    WxCpBaseResp wxCpBaseResp = cpService.getOaWeDriveService().spaceAclAdd(spaceAclAddRequest);
    log.info("添加成员/部门，返回数据为：{}", wxCpBaseResp.toJson());

    /*
     * 获取空间信息
     */
    WxCpSpaceInfo spaceInfo = cpService.getOaWeDriveService().spaceInfo("WangKai", "s.ww45d3e188865aca30.652091685u4h");
    log.info("获取空间信息，spaceInfo信息为：{}", spaceInfo.toJson());

    /*
     * 新建空间
     */
    WxCpSpaceCreateRequest request = new WxCpSpaceCreateRequest();
    request.setUserId("WangKai");
    request.setSpaceName("测试云盘Three");

    WxCpSpaceCreateData spaceCreateData = cpService.getOaWeDriveService().spaceCreate(request);
    log.info("空间id为：{}", spaceCreateData.getSpaceId()); //
    log.info(spaceCreateData.toJson());

    /*
     * 重命名空间
     */
    WxCpSpaceRenameRequest wxCpSpaceRenameRequest = new WxCpSpaceRenameRequest();
    wxCpSpaceRenameRequest.setUserId("WangKai");
    wxCpSpaceRenameRequest.setSpaceId(spaceCreateData.getSpaceId());
    wxCpSpaceRenameRequest.setSpaceName("测试云盘Four");
    WxCpBaseResp baseResp = cpService.getOaWeDriveService().spaceRename(wxCpSpaceRenameRequest);
    log.info("重命名成功：{}", baseResp.toJson());

    /*
     * 解散空间
     */
    WxCpBaseResp thisResp = cpService.getOaWeDriveService().spaceDismiss("WangKai", spaceCreateData.getSpaceId());
    log.info("解散成功：{}", thisResp.toJson());

  }

}
