package me.chanjar.weixin.aispeech.api;

import java.io.File;
import java.util.List;
import java.util.Map;
import me.chanjar.weixin.aispeech.bean.knowledge.KnowledgeInfo;
import me.chanjar.weixin.aispeech.bean.knowledge.KnowledgeManualCreateRequest;
import me.chanjar.weixin.aispeech.bean.knowledge.KnowledgeMoveProgress;
import me.chanjar.weixin.aispeech.bean.knowledge.KnowledgeMoveRequest;
import me.chanjar.weixin.aispeech.bean.knowledge.KnowledgeTagRequest;
import me.chanjar.weixin.aispeech.bean.knowledge.KnowledgeUpdateRequest;
import me.chanjar.weixin.aispeech.bean.knowledge.KnowledgeUrlCreateRequest;
import me.chanjar.weixin.common.error.WxErrorException;

public interface WxAispeechKnowledgeService {
  KnowledgeInfo createKnowledgeByFile(String knowledgeBaseId, File file, String title, String description, String metadata)
    throws WxErrorException;

  KnowledgeInfo createKnowledgeByUrl(String knowledgeBaseId, KnowledgeUrlCreateRequest request) throws WxErrorException;

  KnowledgeInfo createKnowledgeByManual(String knowledgeBaseId, KnowledgeManualCreateRequest request) throws WxErrorException;

  List<KnowledgeInfo> listKnowledge(String knowledgeBaseId, Integer page, Integer pageSize) throws WxErrorException;

  List<KnowledgeInfo> listKnowledgeByIds(List<String> knowledgeIds) throws WxErrorException;

  KnowledgeInfo getKnowledge(String knowledgeId) throws WxErrorException;

  KnowledgeInfo updateKnowledge(String knowledgeId, KnowledgeUpdateRequest request) throws WxErrorException;

  KnowledgeInfo updateManualKnowledge(String knowledgeId, KnowledgeManualCreateRequest request) throws WxErrorException;

  boolean deleteKnowledge(String knowledgeId) throws WxErrorException;

  boolean updateKnowledgeTags(List<String> knowledgeIds, Long tagId) throws WxErrorException;

  List<KnowledgeInfo> searchKnowledge(String keyword, String knowledgeBaseId, Integer page, Integer pageSize)
    throws WxErrorException;

  String moveKnowledge(KnowledgeMoveRequest request) throws WxErrorException;

  KnowledgeMoveProgress getMoveProgress(String taskId) throws WxErrorException;

  boolean createKnowledgeBaseTag(String knowledgeBaseId, KnowledgeTagRequest request) throws WxErrorException;

  boolean updateKnowledgeBaseTag(String knowledgeBaseId, String tagId, KnowledgeTagRequest request) throws WxErrorException;

  String postRaw(String path, Object requestBody) throws WxErrorException;

  String getRaw(String path, Map<String, String> queryParams) throws WxErrorException;
}
