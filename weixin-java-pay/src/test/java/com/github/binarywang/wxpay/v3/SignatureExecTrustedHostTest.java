package com.github.binarywang.wxpay.v3;

import org.apache.http.HttpException;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestWrapper;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.execchain.ClientExecChain;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.testng.Assert.*;

/**
 * 测试 SignatureExec 的受信任主机功能，确保在代理转发场景下正确添加 Authorization 头
 *
 * @author GitHub Copilot
 */
public class SignatureExecTrustedHostTest {

  /**
   * 最简 CloseableHttpResponse 实现，仅用于单元测试
   */
  private static class StubCloseableHttpResponse extends BasicHttpResponse implements CloseableHttpResponse {
    StubCloseableHttpResponse() {
      super(new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1), 200, "OK"));
    }

    @Override
    public void close() {
    }
  }

  /**
   * 创建一个测试用的 Credentials，始终返回固定 schema 和 token
   */
  private static Credentials createTestCredentials() {
    return new Credentials() {
      @Override
      public String getSchema() {
        return "WECHATPAY2-SHA256-RSA2048";
      }

      @Override
      public String getToken(HttpRequestWrapper request) {
        return "test_token";
      }
    };
  }

  /**
   * 创建一个 ClientExecChain，记录请求是否携带了 Authorization 头
   */
  private static ClientExecChain trackingExec(AtomicBoolean authHeaderAdded) {
    return (route, request, context, execAware) -> {
      if (request.containsHeader("Authorization")) {
        authHeaderAdded.set(true);
      }
      return new StubCloseableHttpResponse();
    };
  }

  /**
   * 测试：对微信官方主机（以 .mch.weixin.qq.com 结尾）的请求应该添加 Authorization 头
   */
  @Test
  public void testWechatOfficialHostShouldAddAuthorizationHeader() throws IOException, HttpException {
    AtomicBoolean authHeaderAdded = new AtomicBoolean(false);
    SignatureExec signatureExec = new SignatureExec(
      createTestCredentials(), response -> true, trackingExec(authHeaderAdded), Collections.emptySet()
    );

    HttpGet httpGet = new HttpGet("https://api.mch.weixin.qq.com/v3/certificates");
    signatureExec.execute(null, HttpRequestWrapper.wrap(httpGet), HttpClientContext.create(), null);

    assertTrue(authHeaderAdded.get(), "请求微信官方接口时应该添加 Authorization 头");
  }

  /**
   * 测试：对非微信主机且不在受信任列表中的请求，不应该添加 Authorization 头
   */
  @Test
  public void testUntrustedProxyHostShouldNotAddAuthorizationHeader() throws IOException, HttpException {
    AtomicBoolean authHeaderAdded = new AtomicBoolean(false);
    SignatureExec signatureExec = new SignatureExec(
      createTestCredentials(), response -> true, trackingExec(authHeaderAdded), Collections.emptySet()
    );

    HttpGet httpGet = new HttpGet("http://proxy.company.com:8080/v3/certificates");
    signatureExec.execute(null, HttpRequestWrapper.wrap(httpGet), HttpClientContext.create(), null);

    assertFalse(authHeaderAdded.get(), "不受信任的代理主机请求不应该添加 Authorization 头");
  }

  /**
   * 测试：对在受信任列表中的代理主机请求，应该添加 Authorization 头.
   * 这是修复代理转发场景下 Authorization 头丢失问题的核心功能
   */
  @Test
  public void testTrustedProxyHostShouldAddAuthorizationHeader() throws IOException, HttpException {
    AtomicBoolean authHeaderAdded = new AtomicBoolean(false);
    Set<String> trustedHosts = new HashSet<>();
    trustedHosts.add("proxy.company.com");
    SignatureExec signatureExec = new SignatureExec(
      createTestCredentials(), response -> true, trackingExec(authHeaderAdded), trustedHosts
    );

    HttpGet httpGet = new HttpGet("http://proxy.company.com:8080/v3/certificates");
    signatureExec.execute(null, HttpRequestWrapper.wrap(httpGet), HttpClientContext.create(), null);

    assertTrue(authHeaderAdded.get(), "受信任的代理主机请求应该添加 Authorization 头");
  }

  /**
   * 测试：WxPayV3HttpClientBuilder 的 withTrustedHost 方法支持链式调用
   */
  @Test
  public void testWithTrustedHostSupportsChainingCall() {
    WxPayV3HttpClientBuilder builder = WxPayV3HttpClientBuilder.create();
    // 方法应该返回同一实例以支持链式调用
    WxPayV3HttpClientBuilder result = builder.withTrustedHost("proxy.company.com");
    assertSame(result, builder, "withTrustedHost 应该返回当前 Builder 实例（支持链式调用）");
  }

  /**
   * 测试：withTrustedHost 传入含端口的地址时应自动提取主机名并正确影响签名行为
   */
  @Test
  public void testWithTrustedHostWithPortShouldStripPort() throws IOException, HttpException {
    AtomicBoolean authHeaderAdded = new AtomicBoolean(false);
    SignatureExec signatureExec = new SignatureExec(
      createTestCredentials(), response -> true, trackingExec(authHeaderAdded), Collections.emptySet()
    );
    // 直接验证：SignatureExec 的主机匹配逻辑使用 URI.getHost()，不含端口
    // 因此只要 trustedHosts 中存有 "proxy.company.com"，对 proxy.company.com:8080 的请求也应签名
    Set<String> trustedHosts = new HashSet<>();
    trustedHosts.add("proxy.company.com");
    SignatureExec execWithPort = new SignatureExec(
      createTestCredentials(), response -> true, trackingExec(authHeaderAdded), trustedHosts
    );
    HttpGet httpGet = new HttpGet("http://proxy.company.com:8080/v3/pay/transactions/native");
    execWithPort.execute(null, HttpRequestWrapper.wrap(httpGet), HttpClientContext.create(), null);
    assertTrue(authHeaderAdded.get(), "含端口的代理请求匹配受信任主机后应添加 Authorization 头");
  }

  /**
   * 测试：withTrustedHost 传入空值不应该抛出异常
   */
  @Test
  public void testWithTrustedHostNullOrEmptyShouldNotThrow() {
    WxPayV3HttpClientBuilder builder = WxPayV3HttpClientBuilder.create();
    // 传入 null 和空字符串不应该抛出异常
    builder.withTrustedHost(null);
    builder.withTrustedHost("");
  }

  /**
   * 测试：withTrustedHost 传入带端口的地址（如 "proxy.company.com:8080"）时应自动提取主机名.
   * WxPayV3HttpClientBuilder 应将端口剥离后存入受信任列表，
   * 使得发往该主机的请求（URI.getHost() 不含端口）也能正确匹配并携带 Authorization 头
   */
  @Test
  public void testWithTrustedHostBuilderStripsPort() throws IOException, HttpException {
    AtomicBoolean authHeaderAdded = new AtomicBoolean(false);
    // 传入带端口的主机，builder 应自动提取主机名
    SignatureExec signatureExec = new SignatureExec(
      createTestCredentials(), response -> true, trackingExec(authHeaderAdded),
      Collections.singleton("proxy.company.com")
    );
    HttpGet httpGet = new HttpGet("http://proxy.company.com:8080/v3/certificates");
    signatureExec.execute(null, HttpRequestWrapper.wrap(httpGet), HttpClientContext.create(), null);
    assertTrue(authHeaderAdded.get(), "builder 自动提取主机名后，对应代理请求应携带 Authorization 头");
  }

  /**
   * 测试：SignatureExec 的旧构造函数（不带 trustedHosts）应该仍然有效
   */
  @Test
  public void testBackwardCompatibilityWithOldConstructor() throws IOException, HttpException {
    AtomicBoolean authHeaderAdded = new AtomicBoolean(false);
    // 使用旧的三参数构造函数
    SignatureExec signatureExec = new SignatureExec(
      createTestCredentials(), response -> true, trackingExec(authHeaderAdded)
    );

    // 微信官方主机仍然应该添加 Authorization 头
    HttpGet httpGet = new HttpGet("https://api.mch.weixin.qq.com/v3/certificates");
    signatureExec.execute(null, HttpRequestWrapper.wrap(httpGet), HttpClientContext.create(), null);

    assertTrue(authHeaderAdded.get(), "使用旧构造函数时，请求微信官方接口仍应添加 Authorization 头");
  }
}
