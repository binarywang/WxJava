package me.chanjar.weixin.aispeech.api.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import me.chanjar.weixin.aispeech.api.WxAispeechKnowledgeService;
import me.chanjar.weixin.aispeech.bean.knowledge.KnowledgeInfo;
import me.chanjar.weixin.aispeech.bean.knowledge.KnowledgeListResult;
import me.chanjar.weixin.aispeech.bean.knowledge.KnowledgeManualCreateRequest;
import me.chanjar.weixin.aispeech.bean.knowledge.KnowledgeMoveProgress;
import me.chanjar.weixin.aispeech.bean.knowledge.KnowledgeMoveRequest;
import me.chanjar.weixin.aispeech.bean.knowledge.KnowledgeTagRequest;
import me.chanjar.weixin.aispeech.bean.knowledge.KnowledgeUpdateRequest;
import me.chanjar.weixin.aispeech.bean.knowledge.KnowledgeUrlCreateRequest;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import org.apache.commons.lang3.StringUtils;

public class WxAispeechKnowledgeServiceImpl implements WxAispeechKnowledgeService {
  private final WxAispeechServiceImpl service;

  public WxAispeechKnowledgeServiceImpl(WxAispeechServiceImpl service) {
    this.service = service;
  }

  @Override
  public KnowledgeInfo createKnowledgeByFile(String knowledgeBaseId, File file, String title, String description, String metadata)
    throws WxErrorException {
    String response = service.executeKnowledgeMultipartPost("/api/v1/knowledge-bases/" + knowledgeBaseId + "/knowledge/file",
      file, title, description, metadata);
    return WxGsonBuilder.create().fromJson(response, KnowledgeInfo.class);
  }

  @Override
  public KnowledgeInfo createKnowledgeByUrl(String knowledgeBaseId, KnowledgeUrlCreateRequest request)
    throws WxErrorException {
    String response = service.executeKnowledgePost("/api/v1/knowledge-bases/" + knowledgeBaseId + "/knowledge/url", request);
    return WxGsonBuilder.create().fromJson(response, KnowledgeInfo.class);
  }

  @Override
  public KnowledgeInfo createKnowledgeByManual(String knowledgeBaseId, KnowledgeManualCreateRequest request)
    throws WxErrorException {
    String response = service.executeKnowledgePost("/api/v1/knowledge-bases/" + knowledgeBaseId + "/knowledge/manual", request);
    return WxGsonBuilder.create().fromJson(response, KnowledgeInfo.class);
  }

  @Override
  public List<KnowledgeInfo> listKnowledge(String knowledgeBaseId, Integer page, Integer pageSize)
    throws WxErrorException {
    Map<String, String> query = new HashMap<>();
    query.put("page", page == null ? null : String.valueOf(page));
    query.put("page_size", pageSize == null ? null : String.valueOf(pageSize));
    String response = service.executeKnowledgeGet("/api/v1/knowledge-bases/" + knowledgeBaseId + "/knowledge", query);
    KnowledgeListResult result = WxGsonBuilder.create().fromJson(response, KnowledgeListResult.class);
    return result == null ? null : result.getData();
  }

  @Override
  public List<KnowledgeInfo> listKnowledgeByIds(List<String> knowledgeIds) throws WxErrorException {
    if (knowledgeIds == null || knowledgeIds.isEmpty()) {
      return null;
    }
    StringJoiner joiner = new StringJoiner(",");
    for (String knowledgeId : knowledgeIds) {
      if (StringUtils.isNotBlank(knowledgeId)) {
        joiner.add(knowledgeId);
      }
    }
    if (joiner.length() == 0) {
      return null;
    }

    Map<String, String> query = new HashMap<>();
    query.put("ids", joiner.toString());
    String response = service.executeKnowledgeGet("/api/v1/knowledge/batch", query);
    return parseKnowledgeInfoList(response);
  }

  @Override
  public KnowledgeInfo getKnowledge(String knowledgeId) throws WxErrorException {
    String response = service.executeKnowledgeGet("/api/v1/knowledge/" + knowledgeId, null);
    return WxGsonBuilder.create().fromJson(response, KnowledgeInfo.class);
  }

  @Override
  public KnowledgeInfo updateKnowledge(String knowledgeId, KnowledgeUpdateRequest request) throws WxErrorException {
    String response = service.executeKnowledgePut("/api/v1/knowledge/" + knowledgeId, request);
    return WxGsonBuilder.create().fromJson(response, KnowledgeInfo.class);
  }

  @Override
  public KnowledgeInfo updateManualKnowledge(String knowledgeId, KnowledgeManualCreateRequest request) throws WxErrorException {
    String response = service.executeKnowledgePut("/api/v1/knowledge/manual/" + knowledgeId, request);
    return WxGsonBuilder.create().fromJson(response, KnowledgeInfo.class);
  }

  @Override
  public boolean deleteKnowledge(String knowledgeId) throws WxErrorException {
    service.executeKnowledgeDelete("/api/v1/knowledge/" + knowledgeId);
    return true;
  }

  @Override
  public boolean updateKnowledgeTags(List<String> knowledgeIds, Long tagId) throws WxErrorException {
    if (knowledgeIds == null || knowledgeIds.isEmpty() || tagId == null) {
      return false;
    }

    Map<String, Object> request = new HashMap<>();
    request.put("knowledge_ids", knowledgeIds);
    request.put("tag_id", tagId);
    String response = service.executeKnowledgePut("/api/v1/knowledge/tags", request);
    return StringUtils.isNotBlank(response);
  }

  @Override
  public List<KnowledgeInfo> searchKnowledge(String keyword, String knowledgeBaseId, Integer page, Integer pageSize)
    throws WxErrorException {
    Map<String, String> query = new HashMap<>();
    query.put("keyword", keyword);
    query.put("knowledge_base_id", knowledgeBaseId);
    query.put("page", page == null ? null : String.valueOf(page));
    query.put("page_size", pageSize == null ? null : String.valueOf(pageSize));
    String response = service.executeKnowledgeGet("/api/v1/knowledge/search", query);
    return parseKnowledgeInfoList(response);
  }

  @Override
  public String moveKnowledge(KnowledgeMoveRequest request) throws WxErrorException {
    return service.executeKnowledgePost("/api/v1/knowledge/move", request);
  }

  @Override
  public KnowledgeMoveProgress getMoveProgress(String taskId) throws WxErrorException {
    String response = service.executeKnowledgeGet("/api/v1/knowledge/move/progress/" + taskId, null);
    return WxGsonBuilder.create().fromJson(response, KnowledgeMoveProgress.class);
  }

  @Override
  public boolean createKnowledgeBaseTag(String knowledgeBaseId, KnowledgeTagRequest request) throws WxErrorException {
    String response = service.executeKnowledgePost("/api/v1/knowledge-bases/" + knowledgeBaseId + "/tags", request);
    return StringUtils.isNotBlank(response);
  }

  @Override
  public boolean updateKnowledgeBaseTag(String knowledgeBaseId, String tagId, KnowledgeTagRequest request)
    throws WxErrorException {
    String response = service.executeKnowledgePut("/api/v1/knowledge-bases/" + knowledgeBaseId + "/tags/" + tagId, request);
    return StringUtils.isNotBlank(response);
  }

  @Override
  public String postRaw(String path, Object requestBody) throws WxErrorException {
    return service.executeKnowledgePost(path, requestBody);
  }

  @Override
  public String getRaw(String path, Map<String, String> queryParams) throws WxErrorException {
    return service.executeKnowledgeGet(path, queryParams);
  }

  private List<KnowledgeInfo> parseKnowledgeInfoList(String response) {
    if (StringUtils.isBlank(response)) {
      return null;
    }

    JsonElement element = WxGsonBuilder.create().fromJson(response, JsonElement.class);
    Type listType = new TypeToken<List<KnowledgeInfo>>() { } .getType();
    if (element != null && element.isJsonObject()) {
      JsonObject object = element.getAsJsonObject();
      if (object.has("data")) {
        return WxGsonBuilder.create().fromJson(object.get("data"), listType);
      }
    }
    return WxGsonBuilder.create().fromJson(element, listType);
  }
}
