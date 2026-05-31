package me.chanjar.weixin.aispeech.api.impl;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.chanjar.weixin.aispeech.api.WxAispeechKnowledgeService;
import me.chanjar.weixin.aispeech.bean.knowledge.KnowledgeInfo;
import me.chanjar.weixin.aispeech.bean.knowledge.KnowledgeListResult;
import me.chanjar.weixin.aispeech.bean.knowledge.KnowledgeManualCreateRequest;
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
  public boolean deleteKnowledge(String knowledgeId) throws WxErrorException {
    String response = service.executeKnowledgeDelete("/api/v1/knowledge/" + knowledgeId);
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

    Type listType = new TypeToken<List<KnowledgeInfo>>() { } .getType();
    return WxGsonBuilder.create().fromJson(response, listType);
  }

  @Override
  public String postRaw(String path, Object requestBody) throws WxErrorException {
    return service.executeKnowledgePost(path, requestBody);
  }

  @Override
  public String getRaw(String path, Map<String, String> queryParams) throws WxErrorException {
    return service.executeKnowledgeGet(path, queryParams);
  }
}
