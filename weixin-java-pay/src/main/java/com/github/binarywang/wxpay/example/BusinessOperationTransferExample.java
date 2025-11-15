package com.github.binarywang.wxpay.example;

import com.github.binarywang.wxpay.bean.transfer.*;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.BusinessOperationTransferService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;

/**
 * 运营工具-商家转账API使用示例
 * 
 * 微信支付为商户提供的运营工具转账能力，用于商户的日常运营活动中进行转账操作
 * 
 * @author WxJava Team
 * @see <a href="https://pay.weixin.qq.com/doc/v3/merchant/4012711988">运营工具-商家转账API</a>
 */
public class BusinessOperationTransferExample {

  private WxPayService wxPayService;
  private BusinessOperationTransferService businessOperationTransferService;

  public void init() {
    // 初始化配置
    WxPayConfig config = new WxPayConfig();
    config.setAppId("your_app_id");
    config.setMchId("your_mch_id");
    config.setMchKey("your_mch_key");
    config.setKeyPath("path_to_your_cert.p12");
    
    // 初始化服务
    wxPayService = new WxPayServiceImpl();
    wxPayService.setConfig(config);
    businessOperationTransferService = wxPayService.getBusinessOperationTransferService();
  }

  /**
   * 发起运营工具转账示例
   */
  public void createOperationTransferExample() {
    try {
      // 构建转账请求
      BusinessOperationTransferRequest request = BusinessOperationTransferRequest.newBuilder()
        .appid("your_app_id")                                    // 应用ID
        .outBillNo("OT" + System.currentTimeMillis())           // 商户转账单号
        .operationSceneId(WxPayConstants.OperationSceneId.OPERATION_CASH_MARKETING) // 运营工具转账场景ID
        .openid("user_openid")                                   // 用户openid
        .userName("张三")                                          // 用户姓名（可选）
        .transferAmount(100)                                     // 转账金额，单位分
        .transferRemark("运营活动奖励")                            // 转账备注
        .userRecvPerception(WxPayConstants.UserRecvPerception.CASH_MARKETING.CASH) // 用户收款感知
        .notifyUrl("https://your-domain.com/notify")             // 回调通知地址
        .build();

      // 发起转账
      BusinessOperationTransferResult result = businessOperationTransferService.createOperationTransfer(request);
      
      System.out.println("转账成功！");
      System.out.println("商户单号: " + result.getOutBillNo());
      System.out.println("微信转账单号: " + result.getTransferBillNo());
      System.out.println("转账状态: " + result.getTransferState());
      System.out.println("创建时间: " + result.getCreateTime());
      
    } catch (WxPayException e) {
      System.err.println("转账失败: " + e.getMessage());
      e.printStackTrace();
    }
  }

  /**
   * 通过商户单号查询转账结果示例
   */
  public void queryByOutBillNoExample() {
    try {
      String outBillNo = "OT1640995200000"; // 商户转账单号
      
      BusinessOperationTransferQueryResult result = businessOperationTransferService
        .queryOperationTransferByOutBillNo(outBillNo);
      
      System.out.println("查询成功！");
      System.out.println("商户单号: " + result.getOutBillNo());
      System.out.println("微信转账单号: " + result.getTransferBillNo());
      System.out.println("转账状态: " + result.getTransferState());
      System.out.println("转账金额: " + result.getTransferAmount() + "分");
      System.out.println("创建时间: " + result.getCreateTime());
      System.out.println("更新时间: " + result.getUpdateTime());
      
    } catch (WxPayException e) {
      System.err.println("查询失败: " + e.getMessage());
      e.printStackTrace();
    }
  }

  /**
   * 通过微信转账单号查询转账结果示例
   */
  public void queryByTransferBillNoExample() {
    try {
      String transferBillNo = "1040000071100999991182020050700019480001"; // 微信转账单号
      
      BusinessOperationTransferQueryResult result = businessOperationTransferService
        .queryOperationTransferByTransferBillNo(transferBillNo);
      
      System.out.println("查询成功！");
      System.out.println("商户单号: " + result.getOutBillNo());
      System.out.println("微信转账单号: " + result.getTransferBillNo());
      System.out.println("运营场景ID: " + result.getOperationSceneId());
      System.out.println("转账状态: " + result.getTransferState());
      
    } catch (WxPayException e) {
      System.err.println("查询失败: " + e.getMessage());
      e.printStackTrace();
    }
  }

  /**
   * 使用配置示例
   */
  public static void main(String[] args) {
    BusinessOperationTransferExample example = new BusinessOperationTransferExample();
    
    // 初始化配置
    example.init();
    
    // 1. 发起运营工具转账
    example.createOperationTransferExample();
    
    // 2. 查询转账结果
    // example.queryByOutBillNoExample();
    
    // 3. 通过微信转账单号查询
    // example.queryByTransferBillNoExample();
  }
}