package cn.binarywang.wx.miniapp.demo;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.kefu.WxMaKfList;
import cn.binarywang.wx.miniapp.bean.kefu.WxMaKfSession;
import cn.binarywang.wx.miniapp.bean.kefu.WxMaKfSessionList;
import cn.binarywang.wx.miniapp.bean.kefu.request.WxMaKfAccountRequest;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 小程序客服管理功能使用示例.
 * 
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxMaKefuServiceDemo {

  private final WxMaService wxMaService;

  public WxMaKefuServiceDemo(WxMaService wxMaService) {
    this.wxMaService = wxMaService;
  }

  /**
   * 演示客服账号管理功能
   */
  public void demonstrateCustomerServiceManagement() throws WxErrorException {
    // 1. 获取客服列表
    WxMaKfList kfList = wxMaService.getKefuService().kfList();
    System.out.println("当前客服数量: " + kfList.getKfList().size());

    // 2. 添加新客服账号
    WxMaKfAccountRequest addRequest = WxMaKfAccountRequest.builder()
        .kfAccount("service001@example")
        .kfNick("客服001")
        .kfPwd("password123")
        .build();
    
    boolean addResult = wxMaService.getKefuService().kfAccountAdd(addRequest);
    System.out.println("添加客服账号结果: " + addResult);

    // 3. 更新客服账号信息
    WxMaKfAccountRequest updateRequest = WxMaKfAccountRequest.builder()
        .kfAccount("service001@example")
        .kfNick("高级客服001")
        .build();
    
    boolean updateResult = wxMaService.getKefuService().kfAccountUpdate(updateRequest);
    System.out.println("更新客服账号结果: " + updateResult);
  }

  /**
   * 演示客服会话管理功能
   */
  public void demonstrateSessionManagement() throws WxErrorException {
    String userOpenid = "user_openid_example";
    String kfAccount = "service001@example";

    // 1. 创建客服会话
    boolean createResult = wxMaService.getKefuService().kfSessionCreate(userOpenid, kfAccount);
    System.out.println("创建会话结果: " + createResult);

    // 2. 获取用户会话状态
    WxMaKfSession session = wxMaService.getKefuService().kfSessionGet(userOpenid);
    System.out.println("用户当前会话客服: " + session.getKfAccount());

    // 3. 获取客服的会话列表
    WxMaKfSessionList sessionList = wxMaService.getKefuService().kfSessionList(kfAccount);
    System.out.println("客服当前会话数量: " + sessionList.getSessionList().size());

    // 4. 关闭客服会话
    boolean closeResult = wxMaService.getKefuService().kfSessionClose(userOpenid, kfAccount);
    System.out.println("关闭会话结果: " + closeResult);
  }

  /**
   * 演示客服账号删除功能
   */
  public void demonstrateAccountDeletion() throws WxErrorException {
    String kfAccount = "service001@example";
    
    boolean deleteResult = wxMaService.getKefuService().kfAccountDel(kfAccount);
    System.out.println("删除客服账号结果: " + deleteResult);
  }
}