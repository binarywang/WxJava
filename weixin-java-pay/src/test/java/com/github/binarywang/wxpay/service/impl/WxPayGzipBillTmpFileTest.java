package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.request.WxPayDownloadBillRequest;
import com.github.binarywang.wxpay.config.WxPayConfig;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.zip.GZIPOutputStream;

import static org.testng.Assert.assertEquals;

/**
 * 验证下载 GZIP 对账单后不会在系统临时目录中残留临时目录和文件.
 */
public class WxPayGzipBillTmpFileTest {

  @Test
  public void gzipBillDownloadLeavesNoTmpFiles() throws Exception {
    String bill = "交易时间,公众账号ID,商户号\n`2026-11-01 10:00:00,`wx0000000000000000,`1900000000\n总交易单数\n`1";
    byte[] gzipBill = gzip(bill);
    WxPayServiceApacheHttpImpl payService = new WxPayServiceApacheHttpImpl() {
      @Override
      public byte[] postForBytes(String url, String requestStr, boolean useKey) {
        return gzipBill;
      }
    };
    WxPayConfig config = new WxPayConfig();
    config.setAppId("wx0000000000000000");
    config.setMchId("1900000000");
    config.setMchKey("0123456789abcdef0123456789abcdef");
    payService.setConfig(config);

    File tmpDir = new File(System.getProperty("java.io.tmpdir"));
    Set<String> before = billTmpEntries(tmpDir);

    WxPayDownloadBillRequest request = new WxPayDownloadBillRequest();
    request.setBillDate("20261101");
    request.setBillType("ALL");
    request.setTarType("GZIP");
    assertEquals(payService.downloadRawBill(request), bill);
    assertEquals(payService.downloadRawBill(request), bill);

    assertEquals(billTmpEntries(tmpDir), before);
  }

  private static Set<String> billTmpEntries(File tmpDir) {
    String[] names = tmpDir.list((dir, name) -> name.startsWith("bill"));
    return names == null ? new TreeSet<>() : new TreeSet<>(Arrays.asList(names));
  }

  private static byte[] gzip(String content) throws IOException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    try (GZIPOutputStream gzip = new GZIPOutputStream(out)) {
      gzip.write(content.getBytes(StandardCharsets.UTF_8));
    }
    return out.toByteArray();
  }
}
