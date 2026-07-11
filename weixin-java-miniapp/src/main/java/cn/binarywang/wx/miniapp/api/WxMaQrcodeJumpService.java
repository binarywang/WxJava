package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.qrcode.WxMaQrcodeJumpRule;
import me.chanjar.weixin.common.error.WxErrorException;

import java.util.List;

/**
 * 小程序 URL Link 二维码快速跳转规则管理服务。
 */
public interface WxMaQrcodeJumpService {

  /**
   * 添加二维码快速跳转规则。
   *
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/qrcode-link/url-link/qr-code-quickly-jump.html
   *
   * @param rule 规则
   * @return 结果（errmsg/errcode）
   */
  String addRule(WxMaQrcodeJumpRule rule) throws WxErrorException;

  /**
   * 获取二维码快速跳转规则。
   *
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/qrcode-link/url-link/get-qr-code-jump-rule.html
   *
   * @param isDefault 是否查询默认规则
   * @param prefix 路径前缀（最长 32 个字符）
   * @return 二维码规则列表
   */
  List<WxMaQrcodeJumpRule> getRules(Boolean isDefault, String prefix) throws WxErrorException;

  /**
   * 分页获取二维码快速跳转规则列表。
   *
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/qrcode-link/url-link/get-qr-code-jump-rule-list.html
   *
   * @param getType 1：查询前缀匹配的规则；2：查询默认规则
   * @param pageNum 页码，从 1 开始
   * @param pageSize 每页条数，最多 20
   * @return 二维码规则列表
   */
  List<WxMaQrcodeJumpRule> getRuleList(Integer getType, Integer pageNum, Integer pageSize) throws WxErrorException;

  /**
   * 删除二维码快速跳转规则。
   *
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/qrcode-link/url-link/delete-qr-code-jump-rule.html
   *
   * @param prefix 路径前缀
   * @return 结果（errmsg/errcode）
   */
  String deleteRule(String prefix) throws WxErrorException;
}

