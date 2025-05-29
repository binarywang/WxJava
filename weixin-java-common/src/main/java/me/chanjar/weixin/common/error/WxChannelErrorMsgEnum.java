package me.chanjar.weixin.common.error;

import com.google.common.collect.Maps;
import java.util.Map;

/**
 *
 * <pre>
 *     微信小店公共错误码.
 *     参考文档：<a href="https://developers.weixin.qq.com/doc/store/API/basics/commErrCode.html">微信小店公共错误码</a>
 * </pre>
 *
 * @author Zeyes
 */
public enum WxChannelErrorMsgEnum {
  /**
   * 系统繁忙，此时请开发者稍候再试  system error
   */
  CODE_1(-1, "系统繁忙，此时请开发者稍候再试"),
  /**
   * 请求成功 ok
   */
  CODE_0(0, "请求成功"),
  /**
   * 获取 access_token 时 AppSecret 错误，或者 access_token 无效。请开发者认真检查 AppSecret 的正确性
   * invalid credential, access_token is invalid or not latest, could get access_token by getStableAccessToken, more details at https://mmbizurl.cn/s/JtxxFh33r
   */
  CODE_40001(40001, "获取 access_token 时 AppSecret 错误，或者 access_token 无效。请开发者认真检查 AppSecret 的正确性"),
  /**
   * 请检查 openid 的正确性
   * invalid openid
   */
  CODE_40003(40003, "请检查 openid 的正确性"),
  /**
   * 请检查 appid 的正确性，避免异常字符，注意大小写
   * invalid appid
   */
  CODE_40013(40013, "请检查 appid 的正确性，避免异常字符，注意大小写"),
  /**
   * 请检查API的URL是否与文档一致
   * invalid url
   */
  CODE_40066(40066, "请检查API的URL是否与文档一致"),
  /**
   * 缺少 access_token 参数
   * access_token missing
   */
  CODE_41001(41001, "缺少 access_token 参数"),
  /**
   * 请检查URL参数中是否有 ?appid=
   * appid missing
   */
  CODE_41002(41002, "请检查URL参数中是否有 ?appid="),
  /**
   * 请检查POST json中是否包含component_ appid宇段
   * missing component_appid
   */
  CODE_41018(41018, "请检查POST json中是否包含component_ appid宇段"),
  /**
   * access_token失效，需要重新获取新的access_token
   * access_token expired
   */
  CODE_42001(42001, "access_token失效，需要重新获取新的access_token"),
  /**
   * 请检查发起API请求的Method是否为POST
   * require POST method
   */
  CODE_43002(43002, "请检查发起API请求的Method是否为POST"),
  /**
   * 请使用HTTPS方式清求，不要使用HTTP方式
   * require https
   */
  CODE_43003(43003, "请使用HTTPS方式清求，不要使用HTTP方式"),
  /**
   * POST 的数据包为空
   * empty post data
   */
  CODE_44002(44002, "POST 的数据包为空"),
  /**
   * 请对数据进行压缩
   * content size out of limit
   */
  CODE_45002(45002, "请对数据进行压缩"),
  /**
   * 查看调用次数是否符合预期，可通过get_api_quota接口获取每天的调用quota；用完后可通过clear_quota进行清空
   * reach max api daily quota limit
   */
  CODE_45009(45009, "查看调用次数是否符合预期，可通过get_api_quota接口获取每天的调用quota；用完后可通过clear_quota进行清空"),
  /**
   * 命中每分钟的频率限制
   * api minute-quota reach limit must slower retry next minute
   */
  CODE_45011(45011, "命中每分钟的频率限制"),
  /**
   * 需要登录 channels.weixin.qq.com/shop 配置IP白名单
   * access clientip is not registered, not in ip-white-list
   */
  CODE_45035(45035, "需要登录 channels.weixin.qq.com/shop 配置IP白名单"),
  /**
   * 解析 JSON/XML 内容错误
   * data format error
   */
  CODE_47001(47001, "解析 JSON/XML 内容错误"),
  /**
   * 没有该接口权限
   * api unauthorized
   */
  CODE_48001(48001, "没有该接口权限"),
  /**
   * 接口被禁用
   * api forbidden for irregularities
   */
  CODE_48004(48004, "接口被禁用"),
  /**
   * 请找用户获取该api授权
   * user unauthorized
   */
  CODE_50001(50001, "请找用户获取该api授权"),
  /**
   * 请检查封禁原因
   * user limited
   */
  CODE_50002(50002, "请检查封禁原因"),
  /**
   * 需要登录 channels.weixin.qq.com/shop 配置IP白名单
   * access clientip is not registered, not in ip-white-list
   */
  CODE_61004(61004, "需要登录 channels.weixin.qq.com/shop 配置IP白名单"),
  /**
   * 请检查第三方平台服务商检查已获取的授权集
   * api is unauthorized to component
   */
  CODE_61007(61007, "请检查第三方平台服务商检查已获取的授权集"),
  /**
   * 需要登录 channels.weixin.qq.com/shop 继续完成注销
   * 账号发起注销，进入注销公示期
   */
  CODE_10080000(10080000, "需要登录 channels.weixin.qq.com/shop 继续完成注销"),
  /**
   * 账号已注销
   */
  CODE_10080001(10080001, "账号已注销"),
  /**
   * 小店的视频号带货身份为达人号，不允许使用该功能，如需使用，请将带货身份修改为商家
   */
  CODE_10080002(10080002, "小店的视频号带货身份为达人号，不允许使用该功能，如需使用，请将带货身份修改为商家"),

  ;

  private final int code;
  private final String msg;

  WxChannelErrorMsgEnum(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  static final Map<Integer, String> valueMap = Maps.newHashMap();

  static {
    for (WxChannelErrorMsgEnum value : WxChannelErrorMsgEnum.values()) {
      valueMap.put(value.code, value.msg);
    }
  }

  /**
   * 通过错误代码查找其中文含义.
   */
  public static String findMsgByCode(int code) {
    return valueMap.getOrDefault(code, null);
  }
}
