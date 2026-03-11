package me.chanjar.weixin.common.util.http.apache;

import org.apache.http.impl.client.BasicResponseHandler;

public class ApacheBasicResponseHandler extends BasicResponseHandler {

  public static final ApacheBasicResponseHandler INSTANCE = new ApacheBasicResponseHandler();

}
