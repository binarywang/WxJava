package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.marketing.payroll.*;
import com.github.binarywang.wxpay.bean.result.WxPayApplyBillV3Result;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.PayrollService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.v3.util.RsaCryptoUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.IllegalBlockSizeException;

/**
 * 微信支付-微工卡
 *
 * @author xiaoqiang
 * created on  2021/12/2
 */
@Slf4j
@RequiredArgsConstructor
public class PayrollServiceImpl implements PayrollService {

  private static final Gson GSON = new GsonBuilder().create();

  private final WxPayService payService;

    /**
     * 生成授权token
     * 适用对象：服务商
     * 请求URL：https://api.mch.weixin.qq.com/v3/payroll-card/tokens
     * 请求方式：POST
     *
     * @param request 请求参数
     * @return 返回数据
     * @throws WxPayException the wx pay exception
     */
    @Override
    public TokensResult payrollCardTokens(TokensRequest request) throws WxPayException {
        String url = String.format("%s/v3/payroll-card/tokens", payService.getPayBaseUrl());
        try {
            String userName = RsaCryptoUtil.encryptOAEP(request.getUserName(), payService.getConfig().getVerifier().getValidCertificate());
            request.setUserName(userName);
            String idCardNumber = RsaCryptoUtil.encryptOAEP(request.getIdCardNumber(), payService.getConfig().getVerifier().getValidCertificate());
            request.setIdCardNumber(idCardNumber);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException("加密异常!", e);
        }
        String response = payService.postV3(url, GSON.toJson(request));
        return GSON.fromJson(response, TokensResult.class);
    }

    /**
     * 查询微工卡授权关系API
     * 适用对象：服务商
     * 请求URL：https://api.mch.weixin.qq.com/v3/payroll-card/relations/{openid}
     * 请求方式：GET
     *
     * @param request 请求参数
     * @return 返回数据
     * @throws WxPayException the wx pay exception
     */
    @Override
    public RelationsResult payrollCardRelations(RelationsRequest request) throws WxPayException {
        String url = String.format("%s/v3/payroll-card/relations/%s",
                payService.getPayBaseUrl(), request.getOpenid());
        String query = String.format("?sub_mchid=%s", request.getSubMchid());
        if (StringUtils.isNotEmpty(request.getAppid())) {
            query += "&appid=" + request.getAppid();
        }
        if (StringUtils.isNotEmpty(request.getSubAppid())) {
            query += "&sub_appid=" + request.getSubAppid();
        }
        String response = payService.getV3(url + query);
        return GSON.fromJson(response, RelationsResult.class);
    }

    /**
     * 微工卡核身预下单API
     * 适用对象：服务商
     * 请求URL：https://api.mch.weixin.qq.com/v3/payroll-card/authentications/pre-order
     * 请求方式：POST
     *
     * @param request 请求参数
     * @return 返回数据
     * @throws WxPayException the wx pay exception
     */
    @Override
    public PreOrderResult payrollCardPreOrder(PreOrderRequest request) throws WxPayException {
        String url = String.format("%s/v3/payroll-card/authentications/pre-order", payService.getPayBaseUrl());
        String response = payService.postV3(url, GSON.toJson(request));
        return GSON.fromJson(response, PreOrderResult.class);
    }

    /**
     * 获取核身结果API
     * 适用对象：服务商
     * 请求URL：https://api.mch.weixin.qq.com/v3/payroll-card/authentications/{authenticate_number}
     * 请求方式：GET
     *
     * @param subMchid           子商户号
     * @param authenticateNumber 商家核身单号
     * @return 返回数据
     * @throws WxPayException the wx pay exception
     */
    @Override
    public AuthenticationsResult payrollCardAuthenticationsNumber(String subMchid, String authenticateNumber) throws WxPayException {
        String url = String.format("%s/v3/payroll-card/authentications/%s", payService.getPayBaseUrl(), authenticateNumber);
        String query = String.format("?sub_mchid=%s", subMchid);
        String response = payService.getV3(url + query);
        return GSON.fromJson(response, AuthenticationsResult.class);
    }

    /**
     * 查询核身记录API
     * 适用对象：服务商
     * 请求URL：https://api.mch.weixin.qq.com/v3/payroll-card/authentications
     * 请求方式：GET
     *
     * @param request 请求参数
     * @return 返回数据
     * @throws WxPayException the wx pay exception
     */
    @Override
    public AuthRecordResult payrollCardAuthentications(AuthRecordRequest request) throws WxPayException {
        String url = String.format("%s/v3/payroll-card/authentications", payService.getPayBaseUrl());
        String query = String.format("?openid=%s&sub_mchid=%s&authenticate_date=%s",
                request.getOpenid(), request.getAppid(), request.getSubMchid(), request.getAuthenticateDate());
        if (StringUtils.isNotEmpty(request.getAppid())) {
            query += "&appid=" + request.getAppid();
        }
        if (StringUtils.isNotEmpty(request.getAppid())) {
            query += "&sub_appid=" + request.getSubAppid();
        }
        if (StringUtils.isNotEmpty(request.getAuthenticateState())) {
            query += "&authenticate_state=" + request.getAuthenticateState();
        }
        String response = payService.getV3(url + query);
        return GSON.fromJson(response, AuthRecordResult.class);
    }

    /**
     * 微工卡核身预下单（流程中完成授权）
     * 适用对象：服务商
     * 请求URL：https://api.mch.weixin.qq.com/v3/payroll-card/authentications/pre-order-with-auth
     * 请求方式：POST
     *
     * @param request 请求参数
     * @return 返回数据
     * @throws WxPayException the wx pay exception
     */
    @Override
    public PreOrderWithAuthResult payrollCardPreOrderWithAuth(PreOrderWithAuthRequest request) throws WxPayException {
        String url = String.format("%s/v3/payroll-card/authentications/pre-order-with-auth", payService.getPayBaseUrl());
        try {
            String userName = RsaCryptoUtil.encryptOAEP(request.getUserName(), payService.getConfig().getVerifier().getValidCertificate());
            request.setUserName(userName);
            String idCardNumber = RsaCryptoUtil.encryptOAEP(request.getIdCardNumber(), payService.getConfig().getVerifier().getValidCertificate());
            request.setIdCardNumber(idCardNumber);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException("敏感信息加密异常!", e);
        }
        String response = payService.postV3(url, GSON.toJson(request));
        return GSON.fromJson(response, PreOrderWithAuthResult.class);
    }

    /**
     * 按日下载提现异常文件API
     * 适用对象：服务商
     * 请求URL：https://api.mch.weixin.qq.com/v3/merchant/fund/withdraw/bill-type/{bill_type}
     * 请求方式：GET
     *
     * @param billType 账单类型
     *                 NO_SUCC：提现异常账单，包括提现失败和提现退票账单。
     *       示例值：NO_SUCC
     * @param billDate 账单日期 表示所在日期的提现账单，格式为YYYY-MM-DD。
     *                 例如：2008-01-01日发起的提现，2008-01-03日银行返回提现失败，则该提现数据将出现在bill_date为2008-01-03日的账单中。
     *       示例值：2019-08-17
     * @return 返回数据
     * @throws WxPayException the wx pay exception
     */
    @Override
    public WxPayApplyBillV3Result merchantFundWithdrawBillType(String billType, String billDate, String tarType) throws WxPayException {
        String url = String.format("%s/v3/merchant/fund/withdraw/bill-type/%s", payService.getPayBaseUrl(), billType);
        String query = String.format("?bill_date=%s", billDate);
        if (StringUtils.isNotBlank(tarType)) {
            query += String.format("&tar_type=%s", tarType);
        }
        String response = payService.getV3(url + query);
        return GSON.fromJson(response, WxPayApplyBillV3Result.class);
    }

    /**
     * 微工卡批量转账API
     * 适用对象：服务商
     * 请求URL：https://api.mch.weixin.qq.com/v3/payroll-card/transfer-batches
     * 请求方式：POST
     *
     * @param request 请求参数
     * @return 返回数据
     * @throws WxPayException the wx pay exception
     */
    @Override
    public PayrollTransferBatchesResult payrollCardTransferBatches(PayrollTransferBatchesRequest request) throws WxPayException {
        String url = String.format("%s/v3/payroll-card/transfer-batches", payService.getPayBaseUrl());
        // 对敏感信息进行加密
        if (request.getTransferDetailList() != null && !request.getTransferDetailList().isEmpty()) {
            for (PayrollTransferBatchesRequest.TransferDetail detail : request.getTransferDetailList()) {
                RsaCryptoUtil.encryptFields(detail, payService.getConfig().getVerifier().getValidCertificate());
            }
        }
        String response = payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
        return GSON.fromJson(response, PayrollTransferBatchesResult.class);
    }

}
