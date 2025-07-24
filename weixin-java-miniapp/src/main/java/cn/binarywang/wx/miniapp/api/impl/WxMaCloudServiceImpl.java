package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaCloudService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.cloud.*;
import cn.binarywang.wx.miniapp.bean.cloud.request.WxCloudSendSmsV2Request;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Cloud.*;

/**
 * 云开发相关接口实现类.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on  2020-01-22
 */
@Slf4j
@RequiredArgsConstructor
public class WxMaCloudServiceImpl implements WxMaCloudService {
  private static final Joiner blankJoiner = Joiner.on("").skipNulls();

  private final WxMaService wxMaService;

  @Override
  public String invokeCloudFunction(String name, String body) throws WxErrorException {
    String cloudEnv = this.wxMaService.getWxMaConfig().getCloudEnv();
    return invokeCloudFunction(cloudEnv, name, body);
  }

  @Override
  public String invokeCloudFunction(String env, String name, String body) throws WxErrorException {
    String cloudEnv = this.wxMaService.getWxMaConfig().getCloudEnv();
    final String response = this.wxMaService.post(String.format(INVOKE_CLOUD_FUNCTION_URL, cloudEnv, name), body);
    return GsonParser.parse(response).get("resp_data").getAsString();
  }

  @Override
  public List<String> add(String collection, List<?> list) throws WxErrorException {
    String jsonData = WxMaGsonBuilder.create().toJson(list);
    String query = blankJoiner.join(
      "db.collection('", collection, "')",
      ".add({data: ", jsonData, "})");

    JsonObject params = new JsonObject();
    params.addProperty("env", this.wxMaService.getWxMaConfig().getCloudEnv());
    params.addProperty("query", query);

    String responseContent = wxMaService.post(DATABASE_ADD_URL, params.toString());
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(WxConsts.ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent));
    }
    JsonArray idArray = jsonObject.getAsJsonArray("id_list");
    List<String> idList = new ArrayList<>();
    for (JsonElement id : idArray) {
      idList.add(id.getAsString());
    }
    return idList;
  }

  @Override
  public String add(String collection, Object obj) throws WxErrorException {
    String jsonData = WxMaGsonBuilder.create().toJson(obj);
    String query = blankJoiner.join(
      "db.collection('", collection, "')",
      ".add({data: ", jsonData, "})");

    JsonObject params = new JsonObject();
    params.addProperty("env", this.wxMaService.getWxMaConfig().getCloudEnv());
    params.addProperty("query", query);

    String responseContent = wxMaService.post(DATABASE_ADD_URL, params.toString());
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(WxConsts.ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent));
    }
    JsonArray idArray = jsonObject.getAsJsonArray("id_list");
    return idArray.getAsString();
  }

  @Override
  public JsonArray databaseAdd(String query) throws WxErrorException {
    String cloudEnv = this.wxMaService.getWxMaConfig().getCloudEnv();
    return databaseAdd(cloudEnv, query);
  }

  @Override
  public JsonArray databaseAdd(String env, String query) throws WxErrorException {
    String response = this.wxMaService.post(DATABASE_ADD_URL, ImmutableMap.of("env", env, "query", query));
    return GsonParser.parse(response).get("id_list").getAsJsonArray();
  }

  @Override
  public Integer delete(String collection, String whereJson) throws WxErrorException {
    String query = blankJoiner.join(
      "db.collection('", collection, "')",
      ".where(", whereJson, ").remove()");

    JsonObject params = new JsonObject();
    params.addProperty("env", this.wxMaService.getWxMaConfig().getCloudEnv());
    params.addProperty("query", query);

    String responseContent = wxMaService.post(DATABASE_DELETE_URL, params.toString());
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(WxConsts.ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent));
    }
    return jsonObject.get("deleted").getAsInt();
  }

  @Override
  public int databaseDelete(String query) throws WxErrorException {
    String cloudEnv = this.wxMaService.getWxMaConfig().getCloudEnv();
    return databaseDelete(cloudEnv, query);
  }

  @Override
  public int databaseDelete(String env, String query) throws WxErrorException {
    String response = this.wxMaService.post(DATABASE_DELETE_URL, ImmutableMap.of("env", env, "query", query));
    return GsonParser.parse(response).get("deleted").getAsInt();
  }

  @Override
  public WxCloudDatabaseUpdateResult update(String collection, String whereJson, String updateJson) throws WxErrorException {
    String query = blankJoiner.join(
      "db.collection('", collection, "')",
      ".where(", whereJson, ").update({data:", updateJson, " })");

    JsonObject params = new JsonObject();
    params.addProperty("env", this.wxMaService.getWxMaConfig().getCloudEnv());
    params.addProperty("query", query);

    String responseContent = wxMaService.post(DATABASE_UPDATE_URL, params.toString());
    return WxGsonBuilder.create().fromJson(responseContent, WxCloudDatabaseUpdateResult.class);
  }

  @Override
  public WxCloudDatabaseUpdateResult databaseUpdate(String query) throws WxErrorException {
    String cloudEnv = this.wxMaService.getWxMaConfig().getCloudEnv();
    return databaseUpdate(cloudEnv, query);
  }

  @Override
  public WxCloudDatabaseUpdateResult databaseUpdate(String env, String query) throws WxErrorException {
    String response = this.wxMaService.post(DATABASE_UPDATE_URL, ImmutableMap.of("env", env, "query", query));
    return WxGsonBuilder.create().fromJson(response, WxCloudDatabaseUpdateResult.class);
  }

  @Override
  public WxCloudDatabaseQueryResult query(String collection, String whereJson, Map<String, String> orderBy,
                                          Integer skip, Integer limit) throws WxErrorException {
    if (StringUtils.isBlank(whereJson)) {
      whereJson = "{}";
    }
    StringBuilder orderBySb = new StringBuilder();
    if (null != orderBy && !orderBy.isEmpty()) {
      for (Map.Entry<String, String> entry : orderBy.entrySet()) {
        orderBySb.append(".orderBy('").append(entry.getKey()).append("', '").append(entry.getValue()).append("')");
      }
    }

    if (null == limit) {
      limit = 100;
    }
    if (null == skip) {
      skip = 0;
    }
    String query = blankJoiner.join(
      "db.collection('", collection, "')",
      ".where(", whereJson, ")", orderBySb.toString(), ".skip(", skip, ").limit(", limit, ").get()");

    JsonObject params = new JsonObject();
    params.addProperty("env", this.wxMaService.getWxMaConfig().getCloudEnv());
    params.addProperty("query", query);

    String responseContent = wxMaService.post(DATABASE_QUERY_URL, params.toString());
    return WxGsonBuilder.create().fromJson(responseContent, WxCloudDatabaseQueryResult.class);
  }

  @Override
  public WxCloudDatabaseQueryResult databaseQuery(String query) throws WxErrorException {
    String cloudEnv = this.wxMaService.getWxMaConfig().getCloudEnv();
    return databaseQuery(cloudEnv, query);
  }

  @Override
  public WxCloudDatabaseQueryResult databaseQuery(String env, String query) throws WxErrorException {
    String response = this.wxMaService.post(DATABASE_QUERY_URL, ImmutableMap.of("env", env, "query", query));
    return WxGsonBuilder.create().fromJson(response, WxCloudDatabaseQueryResult.class);
  }

  @Override
  public JsonArray databaseAggregate(String query) throws WxErrorException {
    String cloudEnv = this.wxMaService.getWxMaConfig().getCloudEnv();
    return databaseAggregate(cloudEnv, query);
  }

  @Override
  public JsonArray databaseAggregate(String env, String query) throws WxErrorException {
    String response = this.wxMaService.post(DATABASE_AGGREGATE_URL, ImmutableMap.of("env", env, "query", query));
    return GsonParser.parse(response).get("data").getAsJsonArray();
  }

  @Override
  public Long count(String collection, String whereJson) throws WxErrorException {
    String query = blankJoiner.join(
      "db.collection('", collection, "')",
      ".where(", whereJson, ").count()");

    JsonObject params = new JsonObject();
    params.addProperty("env", this.wxMaService.getWxMaConfig().getCloudEnv());
    params.addProperty("query", query);

    String responseContent = wxMaService.post(DATABASE_COUNT_URL, params.toString());
    return GsonParser.parse(responseContent).get("count").getAsLong();
  }

  @Override
  public Long databaseCount(String query) throws WxErrorException {
    String cloudEnv = this.wxMaService.getWxMaConfig().getCloudEnv();
    return databaseCount(cloudEnv, query);
  }

  @Override
  public Long databaseCount(String env, String query) throws WxErrorException {
    String response = this.wxMaService.post(DATABASE_COUNT_URL, ImmutableMap.of("env", env, "query", query));
    return GsonParser.parse(response).get("count").getAsLong();
  }

  @Override
  public void updateIndex(String collectionName, List<WxCloudDatabaseCreateIndexRequest> createIndexes,
                          List<String> dropIndexNames) throws WxErrorException {
    String cloudEnv = this.wxMaService.getWxMaConfig().getCloudEnv();
    updateIndex(cloudEnv, collectionName, createIndexes, dropIndexNames);
  }

  @Override
  public void updateIndex(String env, String collectionName, List<WxCloudDatabaseCreateIndexRequest> createIndexes,
                          List<String> dropIndexNames) throws WxErrorException {
    List<Map<String, String>> dropIndexes = Lists.newArrayList();
    if (dropIndexNames != null) {
      for (String index : dropIndexNames) {
        dropIndexes.add(ImmutableMap.of("name", index));
      }
    }

    this.wxMaService.post(UPDATE_INDEX_URL, ImmutableMap.of("env", env,
      "collection_name", collectionName, "create_indexes", createIndexes, "drop_indexes", dropIndexes));
  }

  @Override
  public Long databaseMigrateImport(String collectionName, String filePath, int fileType,
                                    boolean stopOnError, int conflictMode) throws WxErrorException {
    String cloudEnv = this.wxMaService.getWxMaConfig().getCloudEnv();
    return databaseMigrateImport(cloudEnv, collectionName, filePath, fileType, stopOnError, conflictMode);
  }

  @Override
  public Long databaseMigrateImport(String env, String collectionName, String filePath, int fileType,
                                    boolean stopOnError, int conflictMode) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("env", env);
    params.addProperty("collection_name", collectionName);
    params.addProperty("file_path", filePath);
    params.addProperty("file_type", fileType);
    params.addProperty("stop_on_error", stopOnError);
    params.addProperty("conflict_mode", conflictMode);

    String response = this.wxMaService.post(DATABASE_MIGRATE_IMPORT_URL, params.toString());
    return GsonParser.parse(response).get("job_id").getAsLong();
  }

  @Override
  public Long databaseMigrateExport(String filePath, int fileType, String query) throws WxErrorException {
    String cloudEnv = this.wxMaService.getWxMaConfig().getCloudEnv();
    return databaseMigrateExport(cloudEnv, filePath, fileType, query);
  }

  @Override
  public Long databaseMigrateExport(String env, String filePath, int fileType, String query) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("env", env);
    params.addProperty("file_path", filePath);
    params.addProperty("file_type", fileType);
    params.addProperty("query", query);

    String response = this.wxMaService.post(DATABASE_MIGRATE_EXPORT_URL, params.toString());
    return GsonParser.parse(response).get("job_id").getAsLong();
  }

  @Override
  public WxCloudCloudDatabaseMigrateQueryInfoResult databaseMigrateQueryInfo(Long jobId) throws WxErrorException {
    String cloudEnv = this.wxMaService.getWxMaConfig().getCloudEnv();
    return databaseMigrateQueryInfo(cloudEnv, jobId);
  }

  @Override
  public WxCloudCloudDatabaseMigrateQueryInfoResult databaseMigrateQueryInfo(String env, Long jobId) throws WxErrorException {
    String response = this.wxMaService.post(DATABASE_MIGRATE_QUERY_INFO_URL, ImmutableMap.of("env", env, "job_id",
      jobId));
    return WxGsonBuilder.create().fromJson(response, WxCloudCloudDatabaseMigrateQueryInfoResult.class);
  }

  @Override
  public WxCloudUploadFileResult uploadFile(String path) throws WxErrorException {
    String cloudEnv = this.wxMaService.getWxMaConfig().getCloudEnv();
    return uploadFile(cloudEnv, path);
  }

  @Override
  public WxCloudUploadFileResult uploadFile(String env, String path) throws WxErrorException {
    String response = this.wxMaService.post(UPLOAD_FILE_URL, ImmutableMap.of("env", env, "path", path));
    return WxGsonBuilder.create().fromJson(response, WxCloudUploadFileResult.class);
  }

  @Override
  public WxCloudBatchDownloadFileResult batchDownloadFile(String[] fileIds, long[] maxAges) throws WxErrorException {
    String cloudEnv = this.wxMaService.getWxMaConfig().getCloudEnv();
    return batchDownloadFile(cloudEnv, fileIds, maxAges);
  }

  @Override
  public WxCloudBatchDownloadFileResult batchDownloadFile(String env, String[] fileIds, long[] maxAges) throws WxErrorException {
    List<Map<String, Serializable>> fileList = Lists.newArrayList();
    int i = 0;
    for (String fileId : fileIds) {
      fileList.add(ImmutableMap.of("fileid", fileId, "max_age", (Serializable) maxAges[i++]));
    }

    String response = this.wxMaService.post(BATCH_DOWNLOAD_FILE_URL, ImmutableMap.of("env", env, "file_list", fileList));
    return WxGsonBuilder.create().fromJson(response, WxCloudBatchDownloadFileResult.class);
  }

  @Override
  public WxCloudBatchDeleteFileResult batchDeleteFile(String[] fileIds) throws WxErrorException {
    String cloudEnv = this.wxMaService.getWxMaConfig().getCloudEnv();
    return batchDeleteFile(cloudEnv, fileIds);
  }

  @Override
  public WxCloudBatchDeleteFileResult batchDeleteFile(String env, String[] fileIds) throws WxErrorException {
    String response = this.wxMaService.post(BATCH_DELETE_FILE_URL, ImmutableMap.of("env", env, "fileid_list", fileIds));
    return WxGsonBuilder.create().fromJson(response, WxCloudBatchDeleteFileResult.class);
  }

  @Override
  public WxCloudGetQcloudTokenResult getQcloudToken(long lifeSpan) throws WxErrorException {
    String response = this.wxMaService.post(GET_QCLOUD_TOKEN_URL, ImmutableMap.of("lifespan", lifeSpan));
    return WxGsonBuilder.create().fromJson(response, WxCloudGetQcloudTokenResult.class);
  }

  @Override
  public void databaseCollectionAdd(String collectionName) throws WxErrorException {
    String cloudEnv = this.wxMaService.getWxMaConfig().getCloudEnv();
    databaseCollectionAdd(cloudEnv, collectionName);
  }

  @Override
  public void databaseCollectionAdd(String env, String collectionName) throws WxErrorException {
    this.wxMaService.post(DATABASE_COLLECTION_ADD_URL, ImmutableMap.of("env", env, "collection_name", collectionName));
  }

  @Override
  public void databaseCollectionDelete(String collectionName) throws WxErrorException {
    String cloudEnv = this.wxMaService.getWxMaConfig().getCloudEnv();
    databaseCollectionDelete(cloudEnv, collectionName);
  }

  @Override
  public void databaseCollectionDelete(String env, String collectionName) throws WxErrorException {
    this.wxMaService.post(DATABASE_COLLECTION_DELETE_URL, ImmutableMap.of("env", env, "collection_name",
      collectionName));
  }

  @Override
  public WxCloudDatabaseCollectionGetResult databaseCollectionGet(Long limit, Long offset) throws WxErrorException {
    String cloudEnv = this.wxMaService.getWxMaConfig().getCloudEnv();
    return databaseCollectionGet(cloudEnv, limit, offset);
  }

  @Override
  public WxCloudDatabaseCollectionGetResult databaseCollectionGet(String env, Long limit, Long offset) throws WxErrorException {
    Map<String, Object> params = new HashMap<>(2);
    params.put("env", env);
    if (limit != null) {
      params.put("limit", limit);
    }

    if (offset != null) {
      params.put("offset", offset);
    }

    String response = this.wxMaService.post(DATABASE_COLLECTION_GET_URL, params);
    return WxGsonBuilder.create().fromJson(response, WxCloudDatabaseCollectionGetResult.class);
  }

  @Override
  public WxCloudSendSmsV2Result sendSmsV2(WxCloudSendSmsV2Request request) throws WxErrorException {
    // 如果没有指定云环境ID，取默认云环境ID
    if (request.getEnv() == null) {
      String cloudEnv = this.wxMaService.getWxMaConfig().getCloudEnv();
      request.setEnv(cloudEnv);
    }
    String response = this.wxMaService.post(SEND_SMS_V2_URL, request);
    return WxGsonBuilder.create().fromJson(response, WxCloudSendSmsV2Result.class);
  }
}
