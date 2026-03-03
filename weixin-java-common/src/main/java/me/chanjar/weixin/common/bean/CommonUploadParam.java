package me.chanjar.weixin.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.springframework.lang.Nullable;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 通用文件上传参数
 *
 * @author <a href="https://www.sacoc.cn">广州跨界</a>
 * created on  2024/01/11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonUploadParam implements Serializable {

  /**
   * 文件对应的接口参数名称(非文件名)，如：media
   */
  @NotNull
  private String name;

  /**
   * 上传数据
   */
  @NotNull
  private CommonUploadData data;

  /**
   * 额外的表单字段，用于在上传文件的同时提交其他表单数据
   * 例如：上传视频素材时需要提交description字段（JSON格式的视频描述信息）
   */
  @Nullable
  private Map<String, String> formFields;

  /**
   * 为保持向后兼容保留的 2 参数构造函数。
   * <p>
   * 仅设置文件参数名和上传数据，额外表单字段将为 {@code null}。
   *
   * @param name 参数名，如：media
   * @param data 上传数据
   * @deprecated 请使用包含 formFields 参数的构造函数或静态工厂方法 {@link #fromFile(String, File)}、{@link #fromBytes(String, String, byte[])}
   */
  @Deprecated
  public CommonUploadParam(@NotNull String name, @NotNull CommonUploadData data) {
    this(name, data, null);
  }

  /**
   * 从文件构造
   *
   * @param name 参数名，如：media
   * @param file 文件
   * @return 文件上传参数对象
   */
  @SneakyThrows
  public static CommonUploadParam fromFile(String name, File file) {
    return new CommonUploadParam(name, CommonUploadData.fromFile(file), null);
  }

  /**
   * 从字节数组构造
   *
   * @param name  参数名，如：media
   * @param bytes 字节数组
   * @return 文件上传参数对象
   */
  @SneakyThrows
  public static CommonUploadParam fromBytes(String name, @Nullable String fileName, byte[] bytes) {
    return new CommonUploadParam(name, new CommonUploadData(fileName, new ByteArrayInputStream(bytes), bytes.length), null);
  }

  /**
   * 添加额外的表单字段
   *
   * @param fieldName  表单字段名
   * @param fieldValue 表单字段值
   * @return 当前对象，支持链式调用
   */
  public CommonUploadParam addFormField(String fieldName, String fieldValue) {
    if (fieldName == null || fieldName.trim().isEmpty()) {
      throw new IllegalArgumentException("表单字段名不能为空");
    }
    if (fieldValue == null) {
      throw new IllegalArgumentException("表单字段值不能为null");
    }
    if (this.formFields == null) {
      this.formFields = new HashMap<>();
    }
    this.formFields.put(fieldName, fieldValue);
    return this;
  }

  @Override
  public String toString() {
    return String.format("{name:%s, data:%s, formFields:%s}", name, data, formFields);
  }
}
