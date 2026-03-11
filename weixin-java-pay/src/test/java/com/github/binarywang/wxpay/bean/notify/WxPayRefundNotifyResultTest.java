package com.github.binarywang.wxpay.bean.notify;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;

import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.github.binarywang.wxpay.util.XmlConfig;
import org.apache.commons.codec.binary.Base64;
import org.testng.annotations.*;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.testbase.ApiTestModule;

import static org.testng.Assert.*;

/**
 * <pre>
 *  Created by BinaryWang on 2017/8/27.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxPayRefundNotifyResultTest {
  @Inject
  private WxPayConfig wxPayConfig;

  /**
   * Test from xml.
   *
   * @throws WxPayException the wx pay exception
   */
  public void testFromXML() throws WxPayException {
    String xmlString = "<xml>" +
      "<return_code>SUCCESS</return_code>" +
      "<appid><![CDATA[****]]></appid>" +
      "<mch_id><![CDATA[****]]></mch_id>" +
      "<nonce_str><![CDATA[1ee38e38b04990449808688cf3a763b7]]></nonce_str>" +
      "<req_info><![CDATA[q1QZlV5j/4I7CsJ3voq1zDgVAuzNM/Gg5JYHcpMZCLtg9KQlB6vShzsh8tgK60dU6yG2WVa0zeSDlK4B7wJCad1lUUP8Ar0Hm18M1ZEjw5vQU17wMzypRM0M9A4CcRLBezRZYzCka9CAH90E2FZ74y6VRe4DNR87t5n3DWVtSbWTBoaFUexHtNs6pyrqX77VvbilIyLZMv5ZYQYOobbQ1U3kime5He7ShEWZ0GPI3gq+z/ZOLsnIdJ5bsT4kokhq/531hSoZ5006vxRGGXnhJt8IYiG7R+oSQxZOYqYR5SKWF+0z2/g0zzM2QQlT2ynLWvBKvdVCLlgCjFN1DF4z/7IEK5FAISFP0GGF51hYw/LofL3ftlD7h7jvjOIgH5viJ0yFGmGCEFHcLKqg0DPXmzwXIrkgQSSQPsuZ6UbHUUG0L8YTRgLnl2FwNFskJIaNx0179Il6xveR1sCXbwSDGvGN78sFuQMztbnx+gFu6VYgv7C+5pFr87wHFAeuDXGTkVM6ucAwSanP7HuxSVvf7SrSrcovKslyqj869pSqn/AB0atiQ4eoq3kWaOqx87NHOV1st9SQW1SYH7SKz4jd9uhrQyDuPb6KJSg1Z2B4sU4187NjPzL4NpzZySgiYk2yXpWKhCLIz6BdZuWX79zgqxLbGxJJnhyy3tOzRWIlMkDOppGJyh8LO0LOqhXzwyrCYzPA+h2xcr7xN5WIW1IGJSZqHdURUtlemcB+yZivuzARNH0LE2MGUfuoNgZ5j1Osn7K88IrkAyKupcIEmG3ktVnPOd1A9RQ9eWbU+C7yKrl6u5ZRZOX0eElVszKfBFy4tu3XHlT7hd/zMFK5NJt8sE89k5m7M8KCGSgJ+Y90ZnUclQvDVtoR5CFkfqsP9fSpA1L+aKYsl2ESq5+fzcqsYRL3YLEhIipBKKrvg6Gy698oNeG+9oCIyuiFexJDq8ycBZ/AWiR+pFQVbNRaFbfKPR9zCW8gHwYOGnENNY9gABuuENqxxXDx9tEYkACd0H9ezLnu9psC6AuR41ACfo6wGKUA1TnpVEHsDbdvJBWDcw60l1hkmHQN2lYFy+eMusEX]]></req_info></xml>";

    WxPayRefundNotifyResult refundNotifyResult = WxPayRefundNotifyResult.fromXML(xmlString, this.wxPayConfig.getMchKey());

    assertNotNull(refundNotifyResult);
    System.out.println(refundNotifyResult);
  }

  /**
   * Encode req info.
   *
   * @throws Exception the exception
   */
  public void encodeReqInfo() throws Exception {
    String xml = "<root>\n" +
      "<out_refund_no><![CDATA[R4001312001201707262674894706_4]]></out_refund_no>\n" +
      "<out_trade_no><![CDATA[201707260201501501005710775]]></out_trade_no>\n" +
      "<refund_account><![CDATA[REFUND_SOURCE_UNSETTLED_FUNDS]]></refund_account>\n" +
      "<refund_fee><![CDATA[15]]></refund_fee>\n" +
      "<refund_id><![CDATA[50000203702017072601461713166]]></refund_id>\n" +
      "<refund_recv_accout><![CDATA[用户零钱]]></refund_recv_accout>\n" +
      "<refund_request_source><![CDATA[API]]></refund_request_source>\n" +
      "<refund_status><![CDATA[SUCCESS]]></refund_status>\n" +
      "<settlement_refund_fee><![CDATA[15]]></settlement_refund_fee>\n" +
      "<settlement_total_fee><![CDATA[100]]></settlement_total_fee>\n" +
      "<success_time><![CDATA[2017-07-26 02:45:49]]></success_time>\n" +
      "<total_fee><![CDATA[100]]></total_fee>\n" +
      "<transaction_id><![CDATA[4001312001201707262674894706]]></transaction_id>\n" +
      "</root>";

    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    final MessageDigest md5 = MessageDigest.getInstance("MD5");
    md5.update(this.wxPayConfig.getMchKey().getBytes(StandardCharsets.UTF_8));
    final String keyMd5String = new BigInteger(1, md5.digest()).toString(16).toLowerCase();
    SecretKeySpec key = new SecretKeySpec(keyMd5String.getBytes(StandardCharsets.UTF_8), "AES");
    cipher.init(Cipher.ENCRYPT_MODE, key);
    System.out.println(Base64.encodeBase64String(cipher.doFinal(xml.getBytes(StandardCharsets.UTF_8))));
  }

  /**
   * Test from xml.
   * fast mode
   *
   * @throws WxPayException the wx pay exception
   */
  public void testFromXMLFastMode() throws WxPayException {
    String xmlString = "<xml>" +
      "<return_code>SUCCESS</return_code>" +
      "<appid><![CDATA[****]]></appid>" +
      "<mch_id><![CDATA[****]]></mch_id>" +
      "<nonce_str><![CDATA[1ee38e38b04990449808688cf3a763b7]]></nonce_str>" +
      "<req_info><![CDATA[q1QZlV5j/4I7CsJ3voq1zDgVAuzNM/Gg5JYHcpMZCLtg9KQlB6vShzsh8tgK60dU6yG2WVa0zeSDlK4B7wJCad1lUUP8Ar0Hm18M1ZEjw5vQU17wMzypRM0M9A4CcRLBezRZYzCka9CAH90E2FZ74y6VRe4DNR87t5n3DWVtSbWTBoaFUexHtNs6pyrqX77VvbilIyLZMv5ZYQYOobbQ1U3kime5He7ShEWZ0GPI3gq+z/ZOLsnIdJ5bsT4kokhq/531hSoZ5006vxRGGXnhJt8IYiG7R+oSQxZOYqYR5SKWF+0z2/g0zzM2QQlT2ynLWvBKvdVCLlgCjFN1DF4z/7IEK5FAISFP0GGF51hYw/LofL3ftlD7h7jvjOIgH5viJ0yFGmGCEFHcLKqg0DPXmzwXIrkgQSSQPsuZ6UbHUUG0L8YTRgLnl2FwNFskJIaNx0179Il6xveR1sCXbwSDGvGN78sFuQMztbnx+gFu6VYgv7C+5pFr87wHFAeuDXGTkVM6ucAwSanP7HuxSVvf7SrSrcovKslyqj869pSqn/AB0atiQ4eoq3kWaOqx87NHOV1st9SQW1SYH7SKz4jd9uhrQyDuPb6KJSg1Z2B4sU4187NjPzL4NpzZySgiYk2yXpWKhCLIz6BdZuWX79zgqxLbGxJJnhyy3tOzRWIlMkDOppGJyh8LO0LOqhXzwyrCYzPA+h2xcr7xN5WIW1IGJSZqHdURUtlemcB+yZivuzARNH0LE2MGUfuoNgZ5j1Osn7K88IrkAyKupcIEmG3ktVnPOd1A9RQ9eWbU+C7yKrl6u5ZRZOX0eElVszKfBFy4tu3XHlT7hd/zMFK5NJt8sE89k5m7M8KCGSgJ+Y90ZnUclQvDVtoR5CFkfqsP9fSpA1L+aKYsl2ESq5+fzcqsYRL3YLEhIipBKKrvg6Gy698oNeG+9oCIyuiFexJDq8ycBZ/AWiR+pFQVbNRaFbfKPR9zCW8gHwYOGnENNY9gABuuENqxxXDx9tEYkACd0H9ezLnu9psC6AuR41ACfo6wGKUA1TnpVEHsDbdvJBWDcw60l1hkmHQN2lYFy+eMusEX]]></req_info></xml>";

    String xmlDecryptedReqInfo = "<root>\n" +
      "<out_refund_no><![CDATA[R4001312001201707262674894706_4]]></out_refund_no>\n" +
      "<out_trade_no><![CDATA[201707260201501501005710775]]></out_trade_no>\n" +
      "<refund_account><![CDATA[REFUND_SOURCE_UNSETTLED_FUNDS]]></refund_account>\n" +
      "<refund_fee><![CDATA[15]]></refund_fee>\n" +
      "<refund_id><![CDATA[50000203702017072601461713166]]></refund_id>\n" +
      "<refund_recv_accout><![CDATA[用户零钱]]></refund_recv_accout>\n" +
      "<refund_request_source><![CDATA[API]]></refund_request_source>\n" +
      "<refund_status><![CDATA[SUCCESS]]></refund_status>\n" +
      "<settlement_refund_fee><![CDATA[15]]></settlement_refund_fee>\n" +
      "<settlement_total_fee><![CDATA[100]]></settlement_total_fee>\n" +
      "<success_time><![CDATA[2017-07-26 02:45:49]]></success_time>\n" +
      "<total_fee><![CDATA[100]]></total_fee>\n" +
      "<transaction_id><![CDATA[4001312001201707262674894706]]></transaction_id>\n" +
      "</root>";

    XmlConfig.fastMode = true;
    try {
      WxPayRefundNotifyResult refundNotifyResult = BaseWxPayResult.fromXML(xmlString, WxPayRefundNotifyResult.class);
      System.out.println(refundNotifyResult.getReqInfoString());

      refundNotifyResult.loadReqInfo(xmlDecryptedReqInfo);
      assertEquals(refundNotifyResult.getReqInfo().getRefundFee().intValue(), 15);
      assertEquals(refundNotifyResult.getReqInfo().getRefundStatus(), "SUCCESS");
      assertEquals(refundNotifyResult.getReqInfo().getRefundRecvAccount(), "用户零钱");
      System.out.println(refundNotifyResult);
    } finally {
      XmlConfig.fastMode = false;
    }
  }

}
