package me.chanjar.weixin.cp.bean.kf;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 获取客服帐号链接-请求参数
 *
 * @author Fu  created on  2022/1/19 19:18
 */
@NoArgsConstructor
@Data
public class WxCpKfAccountLink implements Serializable {

  private static final long serialVersionUID = -1920926948347984256L;

  /**
   * 客服帐号ID
   */
  @SerializedName("open_kfid")
  private String openKfid;

  /**
   * 场景值，字符串类型，由开发者自定义。
   * 不多于32字节
   * 字符串取值范围(正则表达式)：[0-9a-zA-Z_-]*
   *
   * 1. 若scene非空，返回的客服链接开发者可拼接scene_param=SCENE_PARAM参数使用，用户进入会话事件会将SCENE_PARAM原样返回。
   * 其中SCENE_PARAM需要urlencode，且长度不能超过128字节。
   * {@code 如 https://work.weixin.qq.com/kf/kfcbf8f8d07ac7215f?enc_scene=ENCGFSDF567DF&scene_param=a%3D1%26b%3D2}
   * 2. 历史调用接口返回的客服链接（包含encScene=XXX参数），不支持scene_param参数。
   * 3. 返回的客服链接，不能修改或复制参数到其他链接使用。否则进入会话事件参数校验不通过，导致无法回调。
   */
  @SerializedName("scene")
  private String scene;
}
