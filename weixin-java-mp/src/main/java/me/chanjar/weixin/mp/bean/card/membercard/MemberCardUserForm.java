package me.chanjar.weixin.mp.bean.card.membercard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.bean.card.enums.CardWechatFieldType;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

/**
 * 用户表单对象.
 *
 * @author yuanqixun
 * created on  2018-08-30
 */
@Data
public class MemberCardUserForm implements Serializable {
  private static final long serialVersionUID = -1142881966808073662L;

  /**
   * 当前结构（required_form或者optional_form ）内的字段是否允许用户激活后再次修改，
   * 商户设置为true 时，需要接收相应事件通知处理修改事件
   */
  @SerializedName("can_modify")
  private boolean canModify;

  /**
   * 富文本类型字段列表
   */
  @SerializedName("rich_field_list")
  List<MemberCardUserFormRichField> richFieldList;

  /**
   * 文本选项类型列表
   */
  @SerializedName("custom_field_list")
  private List<String> customFieldList;


  /**
   * 微信格式化的选项类型
   */
  @SerializedName("common_field_id_list")
  private List<String> wechatFieldIdList;

  /**
   * 添加富文本类型字段
   *
   * @param field 富文本字段
   */
  public void addRichField(MemberCardUserFormRichField field) {
    if (field == null) {
      return;
    }
    if (richFieldList == null) {
      richFieldList = new ArrayList<>();
    }
    richFieldList.add(field);
  }

  /**
   * 添加微信选项类型字段
   *
   * @param fieldType 微信字段类型
   */
  public void addWechatField(CardWechatFieldType fieldType) {
    if (fieldType == null) {
      return;
    }
    if (wechatFieldIdList == null) {
      wechatFieldIdList = new ArrayList<>();
    }
    wechatFieldIdList.add(fieldType.name());
  }

  /**
   * 添加文本类型字段
   *
   * @param field 文本字段
   */
  public void addCustomField(String field) {
    if (StringUtils.isBlank(field)) {
      return;
    }
    if (customFieldList == null) {
      customFieldList = new ArrayList<>();
    }
    customFieldList.add(field);
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

}
