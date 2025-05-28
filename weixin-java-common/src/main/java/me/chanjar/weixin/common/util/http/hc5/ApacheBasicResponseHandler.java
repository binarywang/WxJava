package me.chanjar.weixin.common.util.http.hc5;

import org.apache.hc.client5.http.impl.classic.BasicHttpClientResponseHandler;

/**
 * ApacheBasicResponseHandler
 *
 * @author altusea
 */
public class ApacheBasicResponseHandler extends BasicHttpClientResponseHandler {

  public static final ApacheBasicResponseHandler INSTANCE = new ApacheBasicResponseHandler();

}
