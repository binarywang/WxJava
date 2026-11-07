package me.chanjar.weixin.channel.bean.image;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 资质文件id
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.image.QualificationFileId}。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Deprecated
public class QualificationFileId implements Serializable {

  private static final long serialVersionUID = -546135264746778249L;

  /** 文件id */
  @JsonProperty("file_id")
  private String id;
}
