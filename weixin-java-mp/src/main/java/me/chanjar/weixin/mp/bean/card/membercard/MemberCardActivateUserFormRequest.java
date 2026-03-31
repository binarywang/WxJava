package me.chanjar.weixin.mp.bean.card.membercard;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 会员卡激活，用户字段提交请求
 *
 * @author yuanqixun
 * created on  2018-08-30
 */
@Data
public class MemberCardActivateUserFormRequest implements Serializable {
  @SerializedName("card_id")
  private String cardId;

  @SerializedName("service_statement")
  private JsonObject serviceStatement;

  @SerializedName("bind_old_card")
  private JsonObject bindOldCard;

  /**
   * 必填项
   */
  @SerializedName("required_form")
  private MemberCardUserForm requiredForm;

  /**
   * 可选项
   */
  @SerializedName("optional_form")
  private MemberCardUserForm optionalForm;

  /**
   * 绑定老会员卡信息
   *
   * @param name 名称
   * @param url  链接地址
   */
  public void setBindOldCard(String name, String url) {
    if (StringUtils.isAnyEmpty(name, url)) {
      return;
    }
    if (bindOldCard == null) {
      bindOldCard = new JsonObject();
    }
    bindOldCard.addProperty("name", name);
    bindOldCard.addProperty("url", url);
  }

  /**
   * 设置服务声明，用于放置商户会员卡守则
   *
   * @param name 名称
   * @param url  链接地址
   */
  public void setServiceStatement(String name, String url) {
    if (StringUtils.isAnyEmpty(name, url)) {
      return;
    }
    if (serviceStatement == null) {
      serviceStatement = new JsonObject();
    }
    serviceStatement.addProperty("name", name);
    serviceStatement.addProperty("url", url);
  }
}
