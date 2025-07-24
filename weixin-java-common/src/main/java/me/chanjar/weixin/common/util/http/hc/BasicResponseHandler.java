package me.chanjar.weixin.common.util.http.hc;

import org.apache.hc.client5.http.impl.classic.BasicHttpClientResponseHandler;

/**
 * ApacheBasicResponseHandler
 *
 * @author altusea
 */
public class BasicResponseHandler extends BasicHttpClientResponseHandler {

  public static final BasicHttpClientResponseHandler INSTANCE = new BasicHttpClientResponseHandler();

}
