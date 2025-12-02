package me.chanjar.weixin.cp.demo;

import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.oa.WxCpApprovalDetailResult;
import me.chanjar.weixin.cp.bean.oa.WxCpApprovalInfo;
import me.chanjar.weixin.cp.bean.oa.WxCpOaApplyEventRequest;
import me.chanjar.weixin.cp.bean.oa.WxCpOaApprovalTemplateResult;
import me.chanjar.weixin.cp.bean.oa.applydata.ApplyDataContent;
import me.chanjar.weixin.cp.bean.oa.applydata.ContentValue;

import java.util.Arrays;
import java.util.Date;

/**
 * 企业微信流程审批功能演示代码
 * WeChat Enterprise Workflow Approval Demo
 * 
 * 演示如何使用WxJava SDK中已实现的完整审批流程功能
 * Demonstrates how to use the comprehensive approval workflow features already implemented in WxJava SDK
 * 
 * 文档参考 Documentation Reference: https://work.weixin.qq.com/api/doc/90000/90135/91853
 */
public class WxCpApprovalWorkflowDemo {

    private WxCpService wxCpService;

    public WxCpApprovalWorkflowDemo(WxCpService wxCpService) {
        this.wxCpService = wxCpService;
    }

    /**
     * 示例1: 提交审批申请
     * Example 1: Submit Approval Application
     * API: /cgi-bin/oa/applyevent (Document 91853)
     */
    public String submitApprovalApplication() throws Exception {
        // 构建审批申请请求
        WxCpOaApplyEventRequest request = new WxCpOaApplyEventRequest()
            .setCreatorUserId("creator_user_id")          // 申请人userid
            .setTemplateId("3Tka1eD6v6JfzhDMqPd3aMkFdxqtJMc2ZRioUBGCNS") // 模板id
            .setUseTemplateApprover(0)                    // 不使用模板中的审批流
            .setApprovers(Arrays.asList(
                new WxCpOaApplyEventRequest.Approver()
                    .setAttr(2)                           // 审批类型: 或签
                    .setUserIds(new String[]{"approver1", "approver2"})
            ))
            .setNotifiers(new String[]{"notifier1", "notifier2"}) // 抄送人
            .setNotifyType(1)                             // 抄送方式: 提单时抄送
            .setApplyData(new WxCpOaApplyEventRequest.ApplyData()
                .setContents(Arrays.asList(
                    // 文本控件
                    new ApplyDataContent()
                        .setControl("Text")
                        .setId("Text-1234567890")
                        .setValue(new ContentValue().setText("这是一个审批申请的文本内容")),
                    
                    // 数字控件
                    new ApplyDataContent()
                        .setControl("Number")
                        .setId("Number-1234567890")
                        .setValue(new ContentValue().setNewNumber("1000")),
                    
                    // 金额控件
                    new ApplyDataContent()
                        .setControl("Money")
                        .setId("Money-1234567890")
                        .setValue(new ContentValue().setNewMoney("10000"))
                ))
            );

        // 提交审批申请
        String spNo = wxCpService.getOaService().apply(request);
        System.out.println("审批申请提交成功，审批单号: " + spNo);
        
        return spNo;
    }

    /**
     * 示例2: 获取审批申请详情
     * Example 2: Get Approval Application Details
     * API: /cgi-bin/oa/getapprovaldetail
     */
    public void getApprovalDetails(String spNo) throws Exception {
        WxCpApprovalDetailResult result = wxCpService.getOaService().getApprovalDetail(spNo);
        
        WxCpApprovalDetailResult.WxCpApprovalDetail detail = result.getInfo();
        
        System.out.println("审批单号: " + detail.getSpNo());
        System.out.println("审批名称: " + detail.getSpName());
        System.out.println("审批状态: " + detail.getSpStatus());
        System.out.println("申请人: " + detail.getApplier().getUserId());
        System.out.println("申请时间: " + detail.getApplyTime());
        
        // 打印审批记录
        if (detail.getSpRecords() != null) {
            Arrays.stream(detail.getSpRecords()).forEach(record -> {
                System.out.println("审批节点状态: " + record.getStatus());
                System.out.println("审批人: " + record.getDetails());
            });
        }
    }

    /**
     * 示例3: 批量获取审批单号
     * Example 3: Batch Get Approval Numbers
     * API: /cgi-bin/oa/getapprovalinfo
     */
    public void batchGetApprovalInfo() throws Exception {
        // 获取最近7天的审批单
        Date startTime = new Date(System.currentTimeMillis() - 7 * 24 * 60 * 60 * 1000L);
        Date endTime = new Date();
        
        WxCpApprovalInfo approvalInfo = wxCpService.getOaService()
            .getApprovalInfo(startTime, endTime, "0", 100, null);
        
        System.out.println("获取到的审批单数量: " + (approvalInfo.getSpNoList() != null ? approvalInfo.getSpNoList().size() : 0));
        
        // 遍历审批单号
        if (approvalInfo.getSpNoList() != null) {
            approvalInfo.getSpNoList().forEach(spNo -> {
                System.out.println("审批单号: " + spNo);
                // 可以进一步调用 getApprovalDetails 获取详情
            });
        }
    }

    /**
     * 示例4: 模板管理
     * Example 4: Template Management
     */
    public void templateManagement() throws Exception {
        // 获取模板详情
        String templateId = "3Tka1eD6v6JfzhDMqPd3aMkFdxqtJMc2ZRioUBGCNS";
        WxCpOaApprovalTemplateResult templateResult = wxCpService.getOaService().getTemplateDetail(templateId);
        
        System.out.println("模板名称: " + templateResult.getTemplateNames());
        System.out.println("模板内容: " + templateResult.getTemplateContent());
    }

    /**
     * 完整的审批流程演示
     * Complete Approval Workflow Demo
     */
    public void completeWorkflowDemo() {
        try {
            System.out.println("=== 企业微信流程审批完整演示 ===");
            
            // 1. 提交审批申请
            System.out.println("\n1. 提交审批申请...");
            String spNo = submitApprovalApplication();
            
            // 2. 获取审批详情
            System.out.println("\n2. 获取审批详情...");
            getApprovalDetails(spNo);
            
            // 3. 批量获取审批信息
            System.out.println("\n3. 批量获取审批信息...");
            batchGetApprovalInfo();
            
            // 4. 模板管理
            System.out.println("\n4. 模板管理...");
            templateManagement();
            
            System.out.println("\n=== 演示完成 ===");
            System.out.println("WxJava SDK 已经完整支持企业微信流程审批功能！");
            
        } catch (Exception e) {
            System.err.println("演示过程中发生错误: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // 注意: 这里需要配置真实的企业微信服务
        // Note: You need to configure real WeChat Enterprise service here
        
        System.out.println("企业微信流程审批功能演示");
        System.out.println("该演示代码展示了WxJava SDK中已经完整实现的审批流程功能");
        System.out.println("包括文档91853中描述的所有核心功能");
        System.out.println("");
        System.out.println("主要功能:");
        System.out.println("- 提交审批申请 (/cgi-bin/oa/applyevent)");
        System.out.println("- 获取审批详情 (/cgi-bin/oa/getapprovaldetail)"); 
        System.out.println("- 批量获取审批单号 (/cgi-bin/oa/getapprovalinfo)");
        System.out.println("- 模板管理功能");
        System.out.println("- 审批流程引擎支持");
        System.out.println("");
        System.out.println("如需运行演示，请配置正确的企业微信服务参数。");
    }
}