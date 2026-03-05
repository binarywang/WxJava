package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaFaceService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.face.WxMaFaceGetVerifyIdRequest;
import cn.binarywang.wx.miniapp.bean.face.WxMaFaceGetVerifyIdResponse;
import cn.binarywang.wx.miniapp.bean.face.WxMaFaceQueryVerifyInfoRequest;
import cn.binarywang.wx.miniapp.bean.face.WxMaFaceQueryVerifyInfoResponse;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Face.GET_VERIFY_ID_URL;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Face.QUERY_VERIFY_INFO_URL;

/**
 * 微信小程序人脸核身相关接口实现
 *
 * @author <a href="https://github.com/github-copilot">Github Copilot</a>
 */
@RequiredArgsConstructor
public class WxMaFaceServiceImpl implements WxMaFaceService {
  private final WxMaService service;

  @Override
  public WxMaFaceGetVerifyIdResponse getVerifyId(WxMaFaceGetVerifyIdRequest request)
    throws WxErrorException {
    String responseContent = this.service.post(GET_VERIFY_ID_URL, request.toJson());
    return WxMaFaceGetVerifyIdResponse.fromJson(responseContent);
  }

  @Override
  public WxMaFaceQueryVerifyInfoResponse queryVerifyInfo(WxMaFaceQueryVerifyInfoRequest request)
    throws WxErrorException {
    String responseContent = this.service.post(QUERY_VERIFY_INFO_URL, request.toJson());
    return WxMaFaceQueryVerifyInfoResponse.fromJson(responseContent);
  }

  /**
   * 计算证件信息摘要（cert_hash）
   * <p>
   * 计算规则：
   * 1. 对 cert_type、cert_name、cert_no 字段内容进行标准 base64 编码（若含中文等Unicode字符，先进行UTF-8编码）
   * 2. 按顺序拼接各个字段：cert_type=xxx&amp;cert_name=xxx&amp;cert_no=xxx
   * 3. 对拼接串进行 SHA256 并输出十六进制小写结果
   * </p>
   *
   * @param certType 证件类型
   * @param certName 证件姓名
   * @param certNo   证件号码
   * @return cert_hash 十六进制小写字符串
   */
  public static String calcCertHash(String certType, String certName, String certNo) {
    String encodedType = Base64.getEncoder().encodeToString(certType.getBytes(StandardCharsets.UTF_8));
    String encodedName = Base64.getEncoder().encodeToString(certName.getBytes(StandardCharsets.UTF_8));
    String encodedNo = Base64.getEncoder().encodeToString(certNo.getBytes(StandardCharsets.UTF_8));
    String raw = "cert_type=" + encodedType + "&cert_name=" + encodedName + "&cert_no=" + encodedNo;
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] hashBytes = digest.digest(raw.getBytes(StandardCharsets.UTF_8));
      StringBuilder hex = new StringBuilder();
      for (byte b : hashBytes) {
        hex.append(String.format("%02x", b));
      }
      return hex.toString();
    } catch (NoSuchAlgorithmException e) {
      throw new IllegalStateException("SHA-256 algorithm not available", e);
    }
  }
}
