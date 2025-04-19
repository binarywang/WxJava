package me.chanjar.weixin.cp.api.impl;

import com.google.gson.JsonObject;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpOaWeDriveService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.oa.wedrive.*;

import java.util.List;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Oa.*;

/**
 * 企业微信微盘接口实现类.
 *
 * @author Wang_Wong  created on  2022-04-22
 */
@Slf4j
@RequiredArgsConstructor
public class WxCpOaWeDriveServiceImpl implements WxCpOaWeDriveService {
  private final WxCpService cpService;

  @Override
  public WxCpSpaceCreateData spaceCreate(@NonNull WxCpSpaceCreateRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(SPACE_CREATE);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpSpaceCreateData.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp spaceRename(@NonNull WxCpSpaceRenameRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(SPACE_RENAME);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp spaceDismiss(@NonNull String userId, @NonNull String spaceId) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(SPACE_DISMISS);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("userid", userId);
    jsonObject.addProperty("spaceid", spaceId);
    String responseContent = this.cpService.post(apiUrl, jsonObject.toString());
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpSpaceInfo spaceInfo(@NonNull String userId, @NonNull String spaceId) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(SPACE_INFO);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("userid", userId);
    jsonObject.addProperty("spaceid", spaceId);
    String responseContent = this.cpService.post(apiUrl, jsonObject.toString());
    return WxCpSpaceInfo.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp spaceAclAdd(@NonNull WxCpSpaceAclAddRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(SPACE_ACL_ADD);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp spaceAclDel(@NonNull WxCpSpaceAclDelRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(SPACE_ACL_DEL);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp spaceSetting(@NonNull WxCpSpaceSettingRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(SPACE_SETTING);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpSpaceShare spaceShare(@NonNull String userId, @NonNull String spaceId) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(SPACE_SHARE);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("userid", userId);
    jsonObject.addProperty("spaceid", spaceId);
    String responseContent = this.cpService.post(apiUrl, jsonObject.toString());
    return WxCpSpaceShare.fromJson(responseContent);
  }

  @Override
  public WxCpFileList fileList(@NonNull WxCpFileListRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(FILE_LIST);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpFileList.fromJson(responseContent);
  }

  @Override
  public WxCpFileUpload fileUpload(@NonNull WxCpFileUploadRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(FILE_UPLOAD);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpFileUpload.fromJson(responseContent);
  }

  @Override
  public WxCpFileDownload fileDownload(@NonNull String userId, @NonNull String fileId) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(FILE_DOWNLOAD);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("userid", userId);
    jsonObject.addProperty("fileid", fileId);
    String responseContent = this.cpService.post(apiUrl, jsonObject.toString());
    return WxCpFileDownload.fromJson(responseContent);
  }

  @Override
  public WxCpFileRename fileRename(@NonNull String fileId, @NonNull String newName) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(FILE_RENAME);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("fileid", fileId);
    jsonObject.addProperty("new_name", newName);
    String responseContent = this.cpService.post(apiUrl, jsonObject.toString());
    return WxCpFileRename.fromJson(responseContent);
  }

  @Override
  public WxCpFileCreate fileCreate(@NonNull String spaceId, @NonNull String fatherId,
                                   @NonNull Integer fileType, @NonNull String fileName) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(FILE_CREATE);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("spaceid", spaceId);
    jsonObject.addProperty("fatherid", fatherId);
    jsonObject.addProperty("file_type", fileType);
    jsonObject.addProperty("file_name", fileName);
    String responseContent = this.cpService.post(apiUrl, jsonObject.toString());
    return WxCpFileCreate.fromJson(responseContent);
  }

  @Override
  public WxCpFileMove fileMove(@NonNull WxCpFileMoveRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(FILE_MOVE);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpFileMove.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp fileDelete(@NonNull List<String> fileIds) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(FILE_DELETE);
    WxCpFileDeleteRequest request = new WxCpFileDeleteRequest(fileIds);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp fileAclAdd(@NonNull WxCpFileAclAddRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(FILE_ACL_ADD);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp fileAclDel(@NonNull WxCpFileAclDelRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(FILE_ACL_DEL);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp fileSetting(@NonNull String userId, @NonNull String fileId, @NonNull Integer authScope,
                                  Integer auth) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(FILE_SETTING);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("userid", userId);
    jsonObject.addProperty("fileid", fileId);
    jsonObject.addProperty("auth_scope", authScope);
    if (auth != null) {
      jsonObject.addProperty("auth", auth);
    }
    String responseContent = this.cpService.post(apiUrl, jsonObject.toString());
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpFileShare fileShare(@NonNull String userId, @NonNull String fileId) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(FILE_SHARE);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("userid", userId);
    jsonObject.addProperty("fileid", fileId);
    String responseContent = this.cpService.post(apiUrl, jsonObject.toString());
    return WxCpFileShare.fromJson(responseContent);
  }

  @Override
  public WxCpFileInfo fileInfo(@NonNull String fileId) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(FILE_INFO);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("fileid", fileId);
    String responseContent = this.cpService.post(apiUrl, jsonObject.toString());
    return WxCpFileInfo.fromJson(responseContent);
  }

}
