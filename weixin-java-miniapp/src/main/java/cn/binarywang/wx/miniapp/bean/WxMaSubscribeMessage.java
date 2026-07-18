package cn.binarywang.wx.miniapp.bean;

import cn.binarywang.wx.miniapp.constant.WxMaConstants;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import lombok.*;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 订阅消息
 * <a href="https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/subscribe-message/subscribeMessage.send.html">点击查阅文档</a>
 *
 * @author S
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class WxMaSubscribeMessage implements Serializable {
  private static final long serialVersionUID = 6846729898251286686L;

  /**
   * 预编译正则，避免每次调用 resetValue 时重复编译
   */
  private static final Pattern NUMBER_PATTERN = Pattern.compile("[^0-9.\\-]");
  private static final Pattern NUMBER_VALID_PATTERN = Pattern.compile("-?\\d+\\.?\\d*|-?\\.\\d+");
  private static final Pattern LETTER_PATTERN = Pattern.compile("[^a-zA-Z]");
  private static final Pattern SYMBOL_PATTERN = Pattern.compile("[a-zA-Z0-9\\u4e00-\\u9fa5]");
  private static final Pattern PHONE_PATTERN = Pattern.compile("[^0-9+\\-]");
  private static final Pattern NAME_PATTERN = Pattern.compile("[^\\u4e00-\\u9fa5a-zA-Z \\u00b7.\\u3001\\uff0c\\u3002\\-]");
  private static final Pattern CHARACTER_STRING_PATTERN = Pattern.compile("[\\u4e00-\\u9fa5]");
  private static final Pattern CHINESE_PATTERN = Pattern.compile("[\\u4e00-\\u9fa5]");
  private static final Pattern PHRASE_PATTERN = Pattern.compile("[^\\u4e00-\\u9fa5]");

  /**
   * 接收者（用户）的 openid.
   * <pre>
   * 参数：touser
   * 是否必填： 是
   * 描述： 接收者（用户）的 openid
   * </pre>
   */
  private String toUser;

  /**
   * 所需下发的模板消息的id.
   * <pre>
   * 参数：template_id
   * 是否必填： 是
   * 描述： 所需下发的模板消息的id
   * </pre>
   */
  private String templateId;

  /**
   * 点击模板卡片后的跳转页面，仅限本小程序内的页面.
   * <pre>
   * 参数：page
   * 是否必填： 否
   * 描述： 点击模板卡片后的跳转页面，仅限本小程序内的页面。支持带参数,（示例index?foo=bar）。该字段不填则模板无跳转。
   * </pre>
   */
  private String page;

  /**
   * 模板内容，不填则下发空模板.
   * <pre>
   * 参数：data
   * 是否必填： 是
   * 描述： 模板内容，不填则下发空模板
   * </pre>
   */
  private List<MsgData> data;

  /**
   * 跳转小程序类型：developer为开发版；trial为体验版；formal为正式版；默认为正式版
   */
  private String miniprogramState = WxMaConstants.MiniProgramState.FORMAL;

  /**
   * 进入小程序查看的语言类型，支持zh_CN(简体中文)、en_US(英文)、zh_HK(繁体中文)、zh_TW(繁体中文)，默认为zh_CN
   */
  private String lang = WxMaConstants.MiniProgramLang.ZH_CN;

  public WxMaSubscribeMessage addData(MsgData datum) {
    if (datum == null) {
      return this;
    }
    if (this.data == null) {
      this.data = new ArrayList<>();
    }

    this.data.add(resetValue(datum));

    return this;
  }

  /**
   * 处理订阅消息字符串长度及格式问题
   *
   * @link <a href="https://developers.weixin.qq.com/miniprogram/dev/server/API/mp-message-management/subscribe-message/api_sendmessage.html">发送订阅消息</a>
   */
  private MsgData resetValue(MsgData datum) {
    String name = datum.getName();
    String value = datum.getValue();

    if (StringUtils.isBlank(value)) {
      // 空值会发送失败，改为-
      datum.setValue("-");
      return datum;
    }

    if (StringUtils.startsWith(name, "thing") && value.length() > 20) {
      // thing.DATA: 20个以内字符，可汉字、数字、字母或符号组合
      value = StringUtils.substring(value, 0, 17) + "...";
    } else if (StringUtils.startsWith(name, "number")) {
      // number.DATA: 32位以内数字，只能数字，可带小数
      value = NUMBER_PATTERN.matcher(value).replaceAll("");
      if (!NUMBER_VALID_PATTERN.matcher(value).matches()) {
        value = "0";
      }
      if (value.length() > 32) {
        value = StringUtils.substring(value, 0, 32);
      }
    } else if (StringUtils.startsWith(name, "letter")) {
      // letter.DATA: 32位以内字母，只能字母
      value = LETTER_PATTERN.matcher(value).replaceAll("");
      if (value.isEmpty()) {
        value = "A";
      }
      if (value.length() > 32) {
        value = StringUtils.substring(value, 0, 32);
      }
    } else if (StringUtils.startsWith(name, "symbol")) {
      // symbol.DATA: 5位以内符号，只能符号（除中文、英文、数字外的常见符号）
      value = SYMBOL_PATTERN.matcher(value).replaceAll("");
      if (value.isEmpty()) {
        value = "-";
      }
      if (value.length() > 5) {
        value = StringUtils.substring(value, 0, 5);
      }
    } else if (StringUtils.startsWith(name, "character_string")) {
      // character_string.DATA: 32位以内，可数字、字母或符号组合（不含中文）
      value = CHARACTER_STRING_PATTERN.matcher(value).replaceAll("");
      if (value.isEmpty()) {
        value = "0";
      }
      if (value.length() > 32) {
        value = StringUtils.substring(value, 0, 32);
      }
    } else if (StringUtils.startsWith(name, "phone_number")) {
      // phone_number.DATA: 17位以内，数字、符号
      value = PHONE_PATTERN.matcher(value).replaceAll("");
      // 只允许一个前导+号，且必须在开头
      if (value.startsWith("+")) {
        value = "+" + value.substring(1).replace("+", "");
      } else {
        value = value.replace("+", "");
      }
      if (value.isEmpty()) {
        value = "0";
      }
      if (value.length() > 17) {
        value = StringUtils.substring(value, 0, 17);
      }
    } else if (StringUtils.startsWith(name, "car_number")) {
      // car_number.DATA: 8位以内，第一位与最后一位可为汉字，其余为字母或数字
      if (value.length() > 8) {
        value = StringUtils.substring(value, 0, 8);
      }
    } else if (StringUtils.startsWith(name, "name")) {
      // name.DATA: 10个以内纯汉字或20个以内纯字母或符号，中文和字母混合按中文名算10个字内
      // 过滤非法字符，不保留数字（name 类型不允许数字）
      value = NAME_PATTERN.matcher(value).replaceAll("");
      if (value.isEmpty()) {
        value = "-";
      }
      boolean containsChinese = CHINESE_PATTERN.matcher(value).find();
      if (containsChinese) {
        // 含中文，按中文名算，10个字内
        if (value.length() > 10) {
          value = StringUtils.substring(value, 0, 7) + "...";
        }
      } else {
        // 纯字母或符号，20个以内
        if (value.length() > 20) {
          value = StringUtils.substring(value, 0, 17) + "...";
        }
      }
    } else if (StringUtils.startsWith(name, "phrase")) {
      // phrase.DATA: 5个以内纯汉字
      value = PHRASE_PATTERN.matcher(value).replaceAll("");
      if (value.isEmpty()) {
        value = "好";
      }
      if (value.length() > 5) {
        value = StringUtils.substring(value, 0, 5);
      }
    }

    datum.setValue(value);
    return datum;
  }

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class MsgData implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String value;
  }

}
