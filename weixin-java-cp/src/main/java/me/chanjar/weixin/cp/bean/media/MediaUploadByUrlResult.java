package me.chanjar.weixin.cp.bean.media;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 异步上传企微素材
 * @author <a href="https://github.com/imyzt">imyzt</a>
 * @date 2025/4/27
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MediaUploadByUrlResult extends WxCpBaseResp implements Serializable {

  private static final long serialVersionUID = 330834334738622341L;

  /**
   * 任务状态。1-处理中，2-完成，3-异常失败
   */
  @SerializedName("status")
  private Integer status;

  @SerializedName("detail")
  private Detail detail;

  @Data
  public static class Detail {

    /**
     * 任务失败返回码。当status为3时返回非0，其他返回0
     * 830001 url非法 确认url是否支持Range分块下载
     * 830003 url下载数据失败 确认url本身是否能正常访问
     * 45001 文件大小超过限制 确认文件在5字节~200M范围内
     * 301019 文件MD5不匹配 确认url对应的文件内容md5，跟所填的md5参数是否一致
     * 注意: status=2时,此处微信并未返回任何值
     */
    @SerializedName("errcode")
    private Integer errCode;

    /**
     * 注意: status=2时,此处微信并未返回任何值
     */
    @SerializedName("errmsg")
    private String errMsg;

    /**
     * 媒体文件上传后获取的唯一标识，3天内有效。当status为2时返回。
     */
    @SerializedName("media_id")
    private String mediaId;

    /**
     * 媒体文件创建的时间戳。当status为2时返回。
     */
    @SerializedName("created_at")
    private String createdAt;
  }

  /**
   * From json wx cp media upload by url result.
   *
   * @param json the json
   * @return the wx cp media upload by url result
   */
  public static MediaUploadByUrlResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, MediaUploadByUrlResult.class);
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
