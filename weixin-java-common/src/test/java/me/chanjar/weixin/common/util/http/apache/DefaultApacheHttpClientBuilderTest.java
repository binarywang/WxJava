package me.chanjar.weixin.common.util.http.apache;

import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpCoreContext;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DefaultApacheHttpClientBuilderTest {
  @Test
  public void testBuild() throws Exception {
    DefaultApacheHttpClientBuilder builder1 = DefaultApacheHttpClientBuilder.get();
    DefaultApacheHttpClientBuilder builder2 = DefaultApacheHttpClientBuilder.get();
    Assert.assertSame(builder1, builder2, "DefaultApacheHttpClientBuilder为单例,获取到的对象应该相同");
    List<TestThread> threadList = new ArrayList<>(10);
    for (int i = 0; i < 10; i++) {
      TestThread thread = new TestThread();
      thread.start();
      threadList.add(thread);
    }
    for (TestThread testThread : threadList) {
      testThread.join();
      Assert.assertNotEquals(-1, testThread.getRespState(), "请求响应code不应为-1");
    }

    for (int i = 1; i < threadList.size(); i++) {
      TestThread thread1 = threadList.get(i - 1);
      TestThread thread2 = threadList.get(i);
      Assert.assertSame(
        thread1.getClient(),
        thread2.getClient(),
        "DefaultApacheHttpClientBuilder为单例,并持有了相同的HttpClient"
      );
    }
  }

  @Test
  void testHttpClientWithInterceptor() throws Exception {
    DefaultApacheHttpClientBuilder builder = DefaultApacheHttpClientBuilder.get();


    List<String> interceptorOrder = new ArrayList<>();

    HttpRequestInterceptor requestInterceptor1 = (request, context) -> {
      context.setAttribute("interceptor_called", "requestInterceptor1");
      interceptorOrder.add("requestInterceptor1");
    };

    HttpRequestInterceptor requestInterceptor2 = (request, context) -> {
      interceptorOrder.add("requestInterceptor2");
    };

    HttpResponseInterceptor responseInterceptor1 = (response, context) -> {
      interceptorOrder.add("responseInterceptor1");
    };

    HttpResponseInterceptor responseInterceptor2 = (response, context) -> {
      interceptorOrder.add("responseInterceptor2");
    };

    builder.setRequestInterceptors(Stream.of(requestInterceptor1, requestInterceptor2).collect(Collectors.toList()));
    builder.setResponseInterceptors(Stream.of(responseInterceptor1, responseInterceptor2).collect(Collectors.toList()));

    try (CloseableHttpClient client = builder.build()) {
      HttpUriRequest request = new HttpGet("http://localhost:8080");
      HttpContext context = HttpClientContext.create();
      try (CloseableHttpResponse resp = client.execute(request, context)) {
        Assert.assertEquals(context.getAttribute("interceptor_called"), "requestInterceptor1", "成功调用 requestInterceptor1 并向 content 中写入了数据");

        // 测试拦截器执行顺序
        Assert.assertEquals(interceptorOrder.get(0), "requestInterceptor1");
        Assert.assertEquals(interceptorOrder.get(1), "requestInterceptor2");
        Assert.assertEquals(interceptorOrder.get(2), "responseInterceptor1");
        Assert.assertEquals(interceptorOrder.get(3), "responseInterceptor2");
      }
    }
  }

  public static class TestThread extends Thread {
    private CloseableHttpClient client;
    private int respState = -1;

    @Override
    public void run() {
      client = DefaultApacheHttpClientBuilder.get().build();
      HttpGet httpGet = new HttpGet("http://www.sina.com.cn/");
      try (CloseableHttpResponse resp = client.execute(httpGet)) {
        respState = resp.getStatusLine().getStatusCode();
      } catch (IOException ignored) {
      }
    }

    public CloseableHttpClient getClient() {
      return client;
    }

    public int getRespState() {
      return respState;
    }
  }
}
