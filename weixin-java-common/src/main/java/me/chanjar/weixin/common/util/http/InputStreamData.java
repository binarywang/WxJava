package me.chanjar.weixin.common.util.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.InputStream;
import java.io.Serializable;

/**
 * 输入流数据.
 * <p>
 * InputStreamData
 * </p>
 *
 * @author zichuan.zhou91@gmail.com
 * created on  2022/2/15
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class InputStreamData implements Serializable {
  private static final long serialVersionUID = -4627006604779378520L;
  private InputStream inputStream;
  private String filename;
}
