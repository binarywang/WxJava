package me.chanjar.weixin.cp.bean.external.msg;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.constant.WxCpConsts;

import java.io.Serializable;

/**
 * The type Attachment.
 *
 * @author chutian0124
 */
@Data
public class Attachment implements Serializable {
  private static final long serialVersionUID = -8078748379570640198L;

  @SerializedName("msgtype")
  private String msgType;

  private Image image;

  private Link link;

  @SerializedName("miniprogram")
  private MiniProgram miniProgram;

  private Video video;

  private File file;

  /**
   * Sets image.
   *
   * @param image the image
   * @return this
   */
  public Attachment setImage(Image image) {
    this.image = image;
    this.msgType = WxCpConsts.WelcomeMsgType.IMAGE;
    return this;
  }

  /**
   * Sets link.
   *
   * @param link the link
   * @return this
   */
  public Attachment setLink(Link link) {
    this.link = link;
    this.msgType = WxCpConsts.WelcomeMsgType.LINK;
    return this;
  }

  /**
   * Sets mini program.
   *
   * @param miniProgram the mini program
   * @return this
   */
  public Attachment setMiniProgram(MiniProgram miniProgram) {
    this.miniProgram = miniProgram;
    this.msgType = WxCpConsts.WelcomeMsgType.MINIPROGRAM;
    return this;
  }

  /**
   * Sets video.
   *
   * @param video the video
   * @return this
   */
  public Attachment setVideo(Video video) {
    this.video = video;
    this.msgType = WxCpConsts.WelcomeMsgType.VIDEO;
    return this;
  }

  /**
   * Sets file.
   *
   * @param file the file
   * @return this
   */
  public Attachment setFile(File file) {
    this.file = file;
    this.msgType = WxCpConsts.WelcomeMsgType.FILE;
    return this;
  }
}
