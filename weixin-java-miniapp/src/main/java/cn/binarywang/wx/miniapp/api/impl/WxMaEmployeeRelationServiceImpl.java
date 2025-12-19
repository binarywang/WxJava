package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaEmployeeRelationService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.employee.WxMaSendEmployeeMsgRequest;
import cn.binarywang.wx.miniapp.bean.employee.WxMaUnbindEmployeeRequest;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Employee.SEND_EMPLOYEE_MSG_URL;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Employee.UNBIND_EMPLOYEE_URL;

/**
 * 小程序用工关系相关操作接口实现
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on 2025-12-19
 */
@RequiredArgsConstructor
public class WxMaEmployeeRelationServiceImpl implements WxMaEmployeeRelationService {
  private final WxMaService service;

  @Override
  public void unbindEmployee(WxMaUnbindEmployeeRequest request) throws WxErrorException {
    this.service.post(UNBIND_EMPLOYEE_URL, request.toJson());
  }

  @Override
  public void sendEmployeeMsg(WxMaSendEmployeeMsgRequest request) throws WxErrorException {
    this.service.post(SEND_EMPLOYEE_MSG_URL, request.toJson());
  }
}
