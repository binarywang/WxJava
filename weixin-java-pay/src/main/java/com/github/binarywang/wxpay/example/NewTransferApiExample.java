package com.github.binarywang.wxpay.example;

import com.github.binarywang.wxpay.bean.notify.SignatureHeader;
import com.github.binarywang.wxpay.bean.transfer.*;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.TransferService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;

/**
 * 新版商户转账API使用示例
 * 
 * 从2025年1月15日开始，微信支付推出了新版的商户转账API
 * 新开通的商户号只能使用最新版本的商户转账接口
 * 
 * @author WxJava Team
 * @since 2025-01-15
 */
public class NewTransferApiExample {

    private final TransferService transferService;

    public NewTransferApiExample(WxPayConfig config) {
        // 初始化微信支付服务
        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(config);
        
        // 获取新版转账服务
        this.transferService = wxPayService.getTransferService();
    }

    /**
     * 发起单笔转账示例
     * 新版API使用 /v3/fund-app/mch-transfer/transfer-bills 接口
     */
    public void transferExample() {
        try {
            // 构建转账请求
            TransferBillsRequest request = TransferBillsRequest.newBuilder()
                .appid("wx1234567890123456")                    // 应用ID
                .outBillNo("TRANSFER_" + System.currentTimeMillis()) // 商户转账单号，确保唯一
                .transferSceneId("1005")                       // 转账场景ID（1005=佣金报酬）
                .openid("oUpF8uMuAJO_M2pxb1Q9zNjWeS6o")       // 收款用户的openid
                .userName("张三")                               // 收款用户真实姓名（可选，会自动加密）
                .transferAmount(100)                           // 转账金额，单位：分（此处为1元）
                .transferRemark("佣金报酬")                     // 转账备注，用户可见
                .notifyUrl("https://your-domain.com/transfer/notify") // 异步通知地址（可选）
                .userRecvPerception("Y")                       // 用户收款感知：Y=会收到通知，N=不会收到通知
                .build();

            // 发起转账
            TransferBillsResult result = transferService.transferBills(request);

            // 输出结果
            System.out.println("=== 转账发起成功 ===");
            System.out.println("商户单号: " + result.getOutBillNo());
            System.out.println("微信转账单号: " + result.getTransferBillNo());
            System.out.println("创建时间: " + result.getCreateTime());
            System.out.println("状态: " + result.getState());
            System.out.println("跳转领取页面信息: " + result.getPackageInfo());

        } catch (WxPayException e) {
            System.err.println("转账失败: " + e.getMessage());
            System.err.println("错误代码: " + e.getErrCode());
            System.err.println("错误描述: " + e.getErrCodeDes());
        }
    }

    /**
     * 通过商户单号查询转账结果
     */
    public void queryByOutBillNoExample() {
        try {
            String outBillNo = "TRANSFER_1642567890123";
            TransferBillsGetResult result = transferService.getBillsByOutBillNo(outBillNo);

            System.out.println("=== 查询转账结果（商户单号）===");
            System.out.println("商户单号: " + result.getOutBillNo());
            System.out.println("微信转账单号: " + result.getTransferBillNo());
            System.out.println("状态: " + result.getState());
            System.out.println("转账金额: " + result.getTransferAmount() + "分");
            System.out.println("用户openid: " + result.getOpenid());
            System.out.println("转账备注: " + result.getTransferRemark());

        } catch (WxPayException e) {
            System.err.println("查询失败: " + e.getMessage());
        }
    }

    /**
     * 通过微信转账单号查询转账结果
     */
    public void queryByTransferBillNoExample() {
        try {
            String transferBillNo = "1000000000000000000000000001";
            TransferBillsGetResult result = transferService.getBillsByTransferBillNo(transferBillNo);

            System.out.println("=== 查询转账结果（微信单号）===");
            System.out.println("微信转账单号: " + result.getTransferBillNo());
            System.out.println("状态: " + result.getState());
            System.out.println("失败原因: " + result.getFailReason());

        } catch (WxPayException e) {
            System.err.println("查询失败: " + e.getMessage());
        }
    }

    /**
     * 撤销转账示例
     * 注意：只有在特定状态下才能撤销
     */
    public void cancelTransferExample() {
        try {
            String outBillNo = "TRANSFER_1642567890123";
            TransferBillsCancelResult result = transferService.transformBillsCancel(outBillNo);

            System.out.println("=== 撤销转账结果 ===");
            System.out.println("商户单号: " + result.getOutBillNo());
            System.out.println("状态: " + result.getState());
            System.out.println("更新时间: " + result.getUpdateTime());

        } catch (WxPayException e) {
            System.err.println("撤销失败: " + e.getMessage());
        }
    }

    /**
     * 处理转账回调通知示例
     * 这个方法通常在您的Web服务器的回调接口中调用
     */
    public void handleNotifyExample(String notifyData, String timestamp, String nonce, String signature, String serial) {
        try {
            // 构建签名头信息
            SignatureHeader header = new SignatureHeader();
            header.setTimeStamp(timestamp);
            header.setNonce(nonce);
            header.setSignature(signature);
            header.setSerial(serial);

            // 解析并验签回调数据
            TransferBillsNotifyResult notifyResult = transferService.parseTransferBillsNotifyResult(notifyData, header);

            System.out.println("=== 处理转账回调通知 ===");
            System.out.println("商户单号: " + notifyResult.getResult().getOutBillNo());
            System.out.println("微信转账单号: " + notifyResult.getResult().getTransferBillNo());
            System.out.println("状态: " + notifyResult.getResult().getState());
            System.out.println("转账金额: " + notifyResult.getResult().getTransferAmount() + "分");
            System.out.println("更新时间: " + notifyResult.getResult().getUpdateTime());

            // 根据状态处理业务逻辑
            switch (notifyResult.getResult().getState()) {
                case "SUCCESS":
                    System.out.println("转账成功，进行业务处理...");
                    // 更新订单状态、发送通知等
                    break;
                case "FAIL":
                    System.out.println("转账失败，失败原因: " + notifyResult.getResult().getFailReason());
                    // 处理失败逻辑
                    break;
                default:
                    System.out.println("其他状态: " + notifyResult.getResult().getState());
            }

        } catch (WxPayException e) {
            System.err.println("回调处理失败: " + e.getMessage());
        }
    }

    /**
     * 批量转账示例（使用传统API）
     * 注意：新商户可能无法使用此API，建议使用新版单笔转账API
     */
    public void batchTransferExample() {
        try {
            // 构建转账明细列表
            TransferBatchesRequest.TransferDetail detail1 = TransferBatchesRequest.TransferDetail.newBuilder()
                .outDetailNo("DETAIL_" + System.currentTimeMillis() + "_1")
                .transferAmount(100)  // 1元
                .transferRemark("佣金1")
                .openid("oUpF8uMuAJO_M2pxb1Q9zNjWeS6o")
                .userName("张三")
                .build();

            TransferBatchesRequest.TransferDetail detail2 = TransferBatchesRequest.TransferDetail.newBuilder()
                .outDetailNo("DETAIL_" + System.currentTimeMillis() + "_2")
                .transferAmount(200)  // 2元
                .transferRemark("佣金2")
                .openid("oUpF8uMuAJO_M2pxb1Q9zNjWeS6p")
                .userName("李四")
                .build();

            // 构建批量转账请求
            TransferBatchesRequest batchRequest = TransferBatchesRequest.newBuilder()
                .appid("wx1234567890123456")
                .outBatchNo("BATCH_" + System.currentTimeMillis())
                .batchName("佣金批量发放")
                .batchRemark("2024年1月佣金")
                .totalAmount(300)  // 总金额：3元
                .totalNum(2)       // 总笔数：2笔
                .transferDetailList(java.util.Arrays.asList(detail1, detail2))
                .transferSceneId("1005")  // 转账场景ID
                .build();

            // 发起批量转账
            TransferBatchesResult batchResult = transferService.transferBatches(batchRequest);

            System.out.println("=== 批量转账发起成功 ===");
            System.out.println("商户批次单号: " + batchResult.getOutBatchNo());
            System.out.println("微信批次单号: " + batchResult.getBatchId());
            System.out.println("批次状态: " + batchResult.getBatchStatus());

        } catch (WxPayException e) {
            System.err.println("批量转账失败: " + e.getMessage());
        }
    }

    /**
     * 使用免确认收款授权模式进行转账示例
     * 注意：使用此模式前，用户需要先进行授权
     */
    public void transferWithNoConfirmAuthModeExample() {
        try {
            // 构建转账请求，使用免确认收款授权模式
            TransferBillsRequest request = TransferBillsRequest.newBuilder()
                .appid("wx1234567890123456")
                .outBillNo("NO_CONFIRM_" + System.currentTimeMillis()) // 商户转账单号
                .transferSceneId("1005")                       // 转账场景ID（佣金报酬）
                .openid("oUpF8uMuAJO_M2pxb1Q9zNjWeS6o")       // 收款用户的openid
                .transferAmount(200)                           // 转账金额，单位：分（此处为2元）
                .transferRemark("免确认收款转账")               // 转账备注
                .receiptAuthorizationMode(WxPayConstants.ReceiptAuthorizationMode.NO_CONFIRM_RECEIPT_AUTHORIZATION)
                .userRecvPerception("Y")                       // 用户收款感知
                .build();

            // 发起转账
            TransferBillsResult result = transferService.transferBills(request);

            System.out.println("=== 免确认授权模式转账成功 ===");
            System.out.println("商户单号: " + result.getOutBillNo());
            System.out.println("微信转账单号: " + result.getTransferBillNo());
            System.out.println("状态: " + result.getState());
            System.out.println("说明: 使用免确认授权模式，转账直接到账，无需用户确认");

        } catch (WxPayException e) {
            System.err.println("免确认授权转账失败: " + e.getMessage());
            System.err.println("错误代码: " + e.getErrCode());
            
            // 可能的错误原因
            if ("USER_NOT_AUTHORIZED".equals(e.getErrCode())) {
                System.err.println("用户未授权免确认收款，请先引导用户进行授权");
            }
        }
    }

    /**
     * 使用需确认收款授权模式进行转账示例（默认模式）
     */
    public void transferWithConfirmAuthModeExample() {
        try {
            // 构建转账请求，显式设置为需确认收款授权模式
            TransferBillsRequest request = TransferBillsRequest.newBuilder()
                .appid("wx1234567890123456")
                .outBillNo("CONFIRM_" + System.currentTimeMillis()) // 商户转账单号
                .transferSceneId("1005")                       // 转账场景ID
                .openid("oUpF8uMuAJO_M2pxb1Q9zNjWeS6o")       // 收款用户的openid
                .transferAmount(150)                           // 转账金额，单位：分（此处为1.5元）
                .transferRemark("需确认收款转账")               // 转账备注
                .receiptAuthorizationMode(WxPayConstants.ReceiptAuthorizationMode.CONFIRM_RECEIPT_AUTHORIZATION)
                .userRecvPerception("Y")                       // 用户收款感知
                .build();

            // 发起转账
            TransferBillsResult result = transferService.transferBills(request);

            System.out.println("=== 需确认授权模式转账成功 ===");
            System.out.println("商户单号: " + result.getOutBillNo());
            System.out.println("微信转账单号: " + result.getTransferBillNo());
            System.out.println("状态: " + result.getState());
            System.out.println("packageInfo: " + result.getPackageInfo());
            System.out.println("说明: 使用需确认授权模式，用户需要手动确认才能收款");

        } catch (WxPayException e) {
            System.err.println("需确认授权转账失败: " + e.getMessage());
        }
    }

    /**
     * 权限模式对比示例
     * 展示两种权限模式的区别和使用场景
     */
    public void authModeComparisonExample() {
        System.out.println("\n=== 收款授权模式对比 ===");
        System.out.println("1. 需确认收款授权模式 (CONFIRM_RECEIPT_AUTHORIZATION):");
        System.out.println("   - 这是默认模式");
        System.out.println("   - 用户收到转账后需要手动点击确认才能到账");
        System.out.println("   - 适用于一般的转账场景");
        System.out.println("   - 转账状态可能包含 WAIT_USER_CONFIRM 等待确认状态");
        
        System.out.println("\n2. 免确认收款授权模式 (NO_CONFIRM_RECEIPT_AUTHORIZATION):");
        System.out.println("   - 用户事先授权后，转账直接到账，无需确认");
        System.out.println("   - 提升用户体验，减少操作步骤");
        System.out.println("   - 适用于高频转账场景，如佣金发放等");
        System.out.println("   - 需要用户先进行授权，否则会返回授权错误");
        
        System.out.println("\n使用建议:");
        System.out.println("- 高频业务场景推荐使用免确认模式，提升用户体验");
        System.out.println("- 首次使用需引导用户进行授权");
        System.out.println("- 处理授权相关异常，提供友好的错误提示");
    }

    /**
     * 使用配置示例
     */
    public static void main(String[] args) {
        // 配置微信支付参数
        WxPayConfig config = new WxPayConfig();
        config.setAppId("wx1234567890123456");          // 应用ID
        config.setMchId("1234567890");                  // 商户ID
        config.setApiV3Key("your_api_v3_key_32_chars"); // APIv3密钥
        config.setPrivateKeyPath("path/to/private.pem"); // 商户私钥文件路径
        config.setCertSerialNo("your_certificate_serial"); // 商户证书序列号

        // 创建示例实例
        NewTransferApiExample example = new NewTransferApiExample(config);

        // 权限模式对比说明
        example.authModeComparisonExample();

        // 运行示例
        System.out.println("新版商户转账API使用示例");
        System.out.println("===============================");

        // 1. 发起转账（使用免确认授权模式）
        // example.transferWithNoConfirmAuthModeExample();
        
        // 2. 发起转账（使用需确认授权模式）
        // example.transferWithConfirmAuthModeExample();

        // 3. 发起单笔转账（默认模式）
        example.transferExample();

        // 4. 查询转账结果
        // example.queryByOutBillNoExample();

        // 5. 撤销转账
        // example.cancelTransferExample();

        // 6. 批量转账（传统API）
        // example.batchTransferExample();
    }
}