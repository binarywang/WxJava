package me.chanjar.weixin.channel.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 商品编辑状态
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SpuEditStatus {
  /** 0 初始值 */
  INIT(0, "初始值"),
  /** 1 编辑中 */
  SUBMIT(1, "编辑中"),
  /** 2 审核中 */
  ING(2, "审核中"),
  /** 3 审核失败 */
  FAIL(3, "审核失败"),
  /** 4 审核成功 */
  SUCCESS(4, "审核成功"),
  /** 5 商品信息写入中 */
  WRITING(5, "商品信息写入中"),
  /** 7	商品异步提交，上传中（处于该状态的商品调用上架商品接口会返回10020067） */
  ASYNC_WRITING(7, "商品异步提交，上传中"),
  /** 8	商品异步提交，上传失败（请重新提交） */
  ASYNC_FAIL(8, "商品异步提交，上传失败"),

  ;

  private final int status;
  private final String desc;

  SpuEditStatus(int status, String desc) {
    this.status = status;
    this.desc = desc;
  }

  public int getStatus() {
    return status;
  }

  public String getDesc() {
    return desc;
  }
}
