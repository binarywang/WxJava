package me.chanjar.weixin.cp.bean.media;

import lombok.Data;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * 生成异步上传任务
 * @author <a href="https://github.com/imyzt">imyzt</a>
 * @date 2025/04/27
 */
@Data
public class MediaUploadByUrlReq {

  /**
   * 场景值。1-客户联系入群欢迎语素材（目前仅支持1）。 注意：每个场景值有对应的使用范围，详见上面的「使用场景说明」
   */
  private Integer scene;

  /**
   * 媒体文件类型。目前仅支持video-视频，file-普通文件 不超过32字节。
   */
  private String type;

  /**
   * 文件名，标识文件展示的名称。比如，使用该media_id发消息时，展示的文件名由该字段控制。 不超过128字节。
   */
  private String filename;

  /**
   * 文件cdn url。url要求支持Range分块下载 不超过1024字节。 如果为腾讯云cos链接，则需要设置为「公有读」权限。
   */
  private String url;

  /**
   * 文件md5。对比从url下载下来的文件md5是否一致。 不超过32字节。
   */
  private String md5;

  /**
   * From json wx cp base resp.
   *
   * @param json the json
   * @return the wx cp base resp
   */
  public static MediaUploadByUrlReq fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, MediaUploadByUrlReq.class);
  }

  /**
   * To json string.
   *
   * @return the string
   */
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
