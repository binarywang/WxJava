package me.chanjar.weixin.aispeech.api;

import java.util.List;
import java.util.Map;
import me.chanjar.weixin.aispeech.bean.knowledge.KnowledgeInfo;
import me.chanjar.weixin.aispeech.bean.knowledge.KnowledgeManualCreateRequest;
import me.chanjar.weixin.aispeech.bean.knowledge.KnowledgeUpdateRequest;
import me.chanjar.weixin.aispeech.bean.knowledge.KnowledgeUrlCreateRequest;
import me.chanjar.weixin.common.error.WxErrorException;

public interface WxAispeechKnowledgeService {
  KnowledgeInfo createKnowledgeByUrl(String knowledgeBaseId, KnowledgeUrlCreateRequest request) throws WxErrorException;

  KnowledgeInfo createKnowledgeByManual(String knowledgeBaseId, KnowledgeManualCreateRequest request) throws WxErrorException;

  List<KnowledgeInfo> listKnowledge(String knowledgeBaseId, Integer page, Integer pageSize) throws WxErrorException;

  KnowledgeInfo getKnowledge(String knowledgeId) throws WxErrorException;

  KnowledgeInfo updateKnowledge(String knowledgeId, KnowledgeUpdateRequest request) throws WxErrorException;

  boolean deleteKnowledge(String knowledgeId) throws WxErrorException;

  List<KnowledgeInfo> searchKnowledge(String keyword, String knowledgeBaseId, Integer page, Integer pageSize)
    throws WxErrorException;

  String postRaw(String path, Object requestBody) throws WxErrorException;

  String getRaw(String path, Map<String, String> queryParams) throws WxErrorException;
}
