package me.chanjar.weixin.cp.api.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.oa.doc.WxCpDocData;
import me.chanjar.weixin.cp.bean.oa.doc.WxCpDocGetDataRequest;
import me.chanjar.weixin.cp.bean.oa.doc.WxCpDocImageUploadResult;
import me.chanjar.weixin.cp.bean.oa.doc.WxCpDocModifyRequest;
import me.chanjar.weixin.cp.bean.oa.doc.WxCpDocSmartSheetAuth;
import me.chanjar.weixin.cp.bean.oa.doc.WxCpDocSmartSheetAuthRequest;
import me.chanjar.weixin.cp.bean.oa.doc.WxCpDocSmartSheetModifyAuthRequest;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import org.testng.annotations.Test;

import java.io.File;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Oa.WEDOC_GET_DOC_DATA;
import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Oa.WEDOC_MOD_DOC;
import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Oa.WEDOC_SMARTSHEET_GET_SHEET_AUTH;
import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Oa.WEDOC_SMARTSHEET_MOD_SHEET_AUTH;
import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Oa.WEDOC_UPLOAD_DOC_IMAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * WeDoc 接口实现测试.
 */
public class WxCpOaWeDocServiceImplTest {

  @Test
  public void testNewApisUseExpectedPaths() throws WxErrorException {
    WxCpService cpService = mock(WxCpService.class);
    WxCpConfigStorage configStorage = mock(WxCpConfigStorage.class);
    when(cpService.getWxCpConfigStorage()).thenReturn(configStorage);
    when(configStorage.getApiUrl(WEDOC_GET_DOC_DATA)).thenReturn("https://api.test/get_doc_data");
    when(configStorage.getApiUrl(WEDOC_MOD_DOC)).thenReturn("https://api.test/mod_doc");
    when(configStorage.getApiUrl(WEDOC_UPLOAD_DOC_IMAGE)).thenReturn("https://api.test/upload_doc_image");
    when(configStorage.getApiUrl(WEDOC_SMARTSHEET_GET_SHEET_AUTH)).thenReturn("https://api.test/smartsheet/get_sheet_auth");
    when(configStorage.getApiUrl(WEDOC_SMARTSHEET_MOD_SHEET_AUTH)).thenReturn("https://api.test/smartsheet/mod_sheet_auth");

    when(cpService.post(eq("https://api.test/get_doc_data"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\",\"docid\":\"doc1\"}");
    when(cpService.post(eq("https://api.test/mod_doc"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\"}");
    when(cpService.upload(eq("https://api.test/upload_doc_image"), any()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\",\"url\":\"https://img.test/a.png\"}");
    when(cpService.post(eq("https://api.test/smartsheet/get_sheet_auth"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\",\"docid\":\"doc1\",\"sheet_id\":\"sheet1\"}");
    when(cpService.post(eq("https://api.test/smartsheet/mod_sheet_auth"), anyString()))
      .thenReturn("{\"errcode\":0,\"errmsg\":\"ok\"}");

    WxCpOaWeDocServiceImpl service = new WxCpOaWeDocServiceImpl(cpService);

    JsonObject extra = new JsonObject();
    extra.addProperty("start", 0);
    WxCpDocGetDataRequest getDataRequest = WxCpDocGetDataRequest.builder()
      .docId("doc1")
      .extra(extra)
      .build();
    WxCpDocData docData = service.docGetData(getDataRequest);
    assertThat(docData.getDocId()).isEqualTo("doc1");
    verify(cpService).post(eq("https://api.test/get_doc_data"), anyString());

    JsonArray requests = new JsonArray();
    JsonObject op = new JsonObject();
    op.addProperty("op", "insert_text");
    requests.add(op);
    WxCpDocModifyRequest modifyRequest = WxCpDocModifyRequest.builder()
      .docId("doc1")
      .requests(requests)
      .build();
    WxCpBaseResp modifyResp = service.docModify(modifyRequest);
    assertThat(modifyResp.getErrcode()).isZero();
    verify(cpService).post(eq("https://api.test/mod_doc"), anyString());

    WxCpDocImageUploadResult uploadResult = service.docUploadImage(new File("demo.png"));
    assertThat(uploadResult.getUrl()).isEqualTo("https://img.test/a.png");
    verify(cpService).upload(eq("https://api.test/upload_doc_image"), any());

    WxCpDocSmartSheetAuthRequest authRequest = WxCpDocSmartSheetAuthRequest.builder()
      .docId("doc1")
      .sheetId("sheet1")
      .build();
    WxCpDocSmartSheetAuth auth = service.smartSheetGetAuth(authRequest);
    assertThat(auth.getSheetId()).isEqualTo("sheet1");
    verify(cpService).post(eq("https://api.test/smartsheet/get_sheet_auth"), anyString());

    JsonObject authInfo = new JsonObject();
    authInfo.addProperty("mode", "custom");
    WxCpDocSmartSheetModifyAuthRequest modifyAuthRequest = WxCpDocSmartSheetModifyAuthRequest.builder()
      .docId("doc1")
      .sheetId("sheet1")
      .authInfo(authInfo)
      .build();
    WxCpBaseResp modifyAuthResp = service.smartSheetModifyAuth(modifyAuthRequest);
    assertThat(modifyAuthResp.getErrcode()).isZero();
    verify(cpService).post(eq("https://api.test/smartsheet/mod_sheet_auth"), anyString());
  }
}
