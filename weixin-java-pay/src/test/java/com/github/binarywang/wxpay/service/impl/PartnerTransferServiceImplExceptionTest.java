package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.ecommerce.FundBalanceResult;
import com.github.binarywang.wxpay.bean.ecommerce.enums.SpAccountTypeEnum;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.EcommerceService;
import com.github.binarywang.wxpay.service.WxPayService;
import me.chanjar.weixin.common.error.WxRuntimeException;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.testng.Assert.assertEquals;

public class PartnerTransferServiceImplExceptionTest {

  @Test
  public void testSpDayEndBalancePreservesPayException() {
    WxPayException failure = new WxPayException("balance query failed");
    WxPayService payService = new WxPayServiceImpl() {
      @Override
      public EcommerceService getEcommerceService() {
        return new EcommerceServiceImpl(this) {
          @Override
          public FundBalanceResult spDayEndBalance(SpAccountTypeEnum accountType, String date)
            throws WxPayException {
            assertEquals(accountType, SpAccountTypeEnum.BASIC);
            assertEquals(date, "2020-09-11");
            throw failure;
          }
        };
      }
    };

    assertThatThrownBy(() -> new PartnerTransferServiceImpl(payService)
      .spDayEndBalance(SpAccountTypeEnum.BASIC, "2020-09-11"))
      .isInstanceOf(WxRuntimeException.class).hasCause(failure);
  }
}
