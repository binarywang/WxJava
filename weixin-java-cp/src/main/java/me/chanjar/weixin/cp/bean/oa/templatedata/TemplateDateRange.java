package me.chanjar.weixin.cp.bean.oa.templatedata;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * The type Template date range.
 *
 * @author gyv12345 @163.com
 */
@Data
public class TemplateDateRange implements Serializable {

  private static final long serialVersionUID = -9209035461466543180L;

  /**
   * 时间刻度：hour-精确到分钟, halfday—上午/下午
   */
  private String type;

  /**
   * 是否考虑法定节假日：0-不考虑，1-考虑
   */
  @SerializedName("official_holiday")
  private Integer officialHoliday;

  /**
   * 每天工作时长（秒），halfday模式下有效
   */
  @SerializedName("perday_duration")
  private Integer perdayDuration;
}
