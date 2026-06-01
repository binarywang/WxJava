package me.chanjar.weixin.aispeech.api.impl;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.chanjar.weixin.aispeech.bean.knowledge.KnowledgeInfo;
import me.chanjar.weixin.aispeech.bean.knowledge.KnowledgeManualCreateRequest;
import me.chanjar.weixin.aispeech.bean.knowledge.KnowledgeMoveProgress;
import me.chanjar.weixin.aispeech.bean.knowledge.KnowledgeMoveRequest;
import me.chanjar.weixin.aispeech.bean.knowledge.KnowledgeTagRequest;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WxAispeechKnowledgeServiceImplTest {

  @Test
  public void testCreateKnowledgeByFile() throws IOException, WxErrorException {
    MockWxAispeechService service = new MockWxAispeechService();
    service.putResponse("POST_MULTIPART:/api/v1/knowledge-bases/kb1/knowledge/file", "{\"id\":\"k1\"}");
    WxAispeechKnowledgeServiceImpl knowledgeService = new WxAispeechKnowledgeServiceImpl(service);

    File file = File.createTempFile("wxjava-aispeech", ".txt");
    file.deleteOnExit();
    KnowledgeInfo result = knowledgeService.createKnowledgeByFile("kb1", file, "标题", "描述", "{\"source\":\"web\"}");

    Assert.assertEquals(result.getId(), "k1");
    Assert.assertEquals(service.lastPath, "/api/v1/knowledge-bases/kb1/knowledge/file");
    Assert.assertEquals(service.lastTitle, "标题");
    Assert.assertEquals(service.lastDescription, "描述");
    Assert.assertEquals(service.lastMetadata, "{\"source\":\"web\"}");
    Assert.assertEquals(service.lastFile, file);
  }

  @Test
  public void testListKnowledgeByIdsAndMoveProgress() throws WxErrorException {
    MockWxAispeechService service = new MockWxAispeechService();
    service.putResponse("GET:/api/v1/knowledge/batch", "{\"data\":[{\"id\":\"k1\"},{\"id\":\"k2\"}]}");
    service.putResponse("GET:/api/v1/knowledge/move/progress/task-1", "{\"task_id\":\"task-1\",\"status\":\"processing\",\"progress\":35.5}");
    WxAispeechKnowledgeServiceImpl knowledgeService = new WxAispeechKnowledgeServiceImpl(service);

    List<KnowledgeInfo> result = knowledgeService.listKnowledgeByIds(Arrays.asList("k1", "k2"));
    KnowledgeMoveProgress progress = knowledgeService.getMoveProgress("task-1");

    Assert.assertEquals(result.size(), 2);
    Assert.assertEquals(result.get(0).getId(), "k1");
    Assert.assertEquals(service.lastQueryParams.get("ids"), "k1,k2");
    Assert.assertEquals(progress.getTaskId(), "task-1");
    Assert.assertEquals(progress.getStatus(), "processing");
  }

  @Test
  public void testUpdateManualMoveAndTagApis() throws WxErrorException {
    MockWxAispeechService service = new MockWxAispeechService();
    service.putResponse("PUT:/api/v1/knowledge/manual/k1", "{\"id\":\"k1\"}");
    service.putResponse("POST:/api/v1/knowledge/move", "{\"task_id\":\"task-2\"}");
    service.putResponse("PUT:/api/v1/knowledge/tags", "{\"success\":true}");
    service.putResponse("POST:/api/v1/knowledge-bases/kb1/tags", "{\"id\":\"t1\"}");
    service.putResponse("PUT:/api/v1/knowledge-bases/kb1/tags/t1", "{\"id\":\"t1\"}");
    WxAispeechKnowledgeServiceImpl knowledgeService = new WxAispeechKnowledgeServiceImpl(service);

    KnowledgeManualCreateRequest manualRequest = new KnowledgeManualCreateRequest();
    manualRequest.setContent("# 内容");
    KnowledgeInfo updated = knowledgeService.updateManualKnowledge("k1", manualRequest);

    KnowledgeMoveRequest moveRequest = new KnowledgeMoveRequest();
    moveRequest.setSourceKnowledgeBaseId("kb1");
    moveRequest.setTargetKnowledgeBaseId("kb2");
    moveRequest.setKnowledgeIds(Arrays.asList("k1"));
    moveRequest.setMode("reuse_vectors");
    String moveResult = knowledgeService.moveKnowledge(moveRequest);

    boolean updateTagsResult = knowledgeService.updateKnowledgeTags(Arrays.asList("k1"), 1001L);
    KnowledgeTagRequest tagRequest = new KnowledgeTagRequest();
    tagRequest.setName("FAQ");
    boolean createTagResult = knowledgeService.createKnowledgeBaseTag("kb1", tagRequest);
    boolean updateTagResult = knowledgeService.updateKnowledgeBaseTag("kb1", "t1", tagRequest);

    Assert.assertEquals(updated.getId(), "k1");
    Assert.assertTrue(moveResult.contains("task-2"));
    Assert.assertTrue(updateTagsResult);
    Assert.assertTrue(createTagResult);
    Assert.assertTrue(updateTagResult);
    Assert.assertFalse(knowledgeService.updateKnowledgeTags(null, 1001L));
    Assert.assertFalse(knowledgeService.updateKnowledgeTags(Arrays.asList("k1"), null));
  }

  private static class MockWxAispeechService extends WxAispeechServiceImpl {
    private final Map<String, String> responses = new HashMap<>();
    private String lastPath;
    private Map<String, String> lastQueryParams;
    private File lastFile;
    private String lastTitle;
    private String lastDescription;
    private String lastMetadata;

    void putResponse(String key, String response) {
      this.responses.put(key, response);
    }

    @Override
    protected String executeKnowledgeGet(String path, Map<String, String> queryParams) throws WxErrorException {
      this.lastPath = path;
      this.lastQueryParams = queryParams;
      return responses.get("GET:" + path);
    }

    @Override
    protected String executeKnowledgePost(String path, Object requestBody) throws WxErrorException {
      this.lastPath = path;
      return responses.get("POST:" + path);
    }

    @Override
    protected String executeKnowledgePut(String path, Object requestBody) throws WxErrorException {
      this.lastPath = path;
      return responses.get("PUT:" + path);
    }

    @Override
    protected String executeKnowledgeMultipartPost(String path, File file, String title, String description, String metadata)
      throws WxErrorException {
      this.lastPath = path;
      this.lastFile = file;
      this.lastTitle = title;
      this.lastDescription = description;
      this.lastMetadata = metadata;
      return responses.get("POST_MULTIPART:" + path);
    }
  }
}
