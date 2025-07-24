package me.chanjar.weixin.channel.test;

import me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.League;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * WeChat League API URL Pattern Validation Test
 * 
 * This test verifies that all League API URLs follow consistent patterns
 * to avoid the 48001 error (no interface permission) mentioned in issue #3630.
 */
public class LeagueUrlPatternTest {

    @Test
    public void testWindowUrlPatternsAreConsistent() {
        // Verify that window-related URLs follow consistent patterns
        String listUrl = League.LIST_SUPPLIER_GOODS_URL;
        String detailUrl = League.GET_SUPPLIER_GOODS_URL;
        
        // List URLs should end with /list/get
        Assert.assertTrue(listUrl.endsWith("/list/get"), 
            "LIST_SUPPLIER_GOODS_URL should end with /list/get for consistency");
        
        // Detail/Get URLs should end with /get (but not /list/get)
        Assert.assertTrue(detailUrl.endsWith("/get"), 
            "GET_SUPPLIER_GOODS_URL should end with /get");
        Assert.assertFalse(detailUrl.contains("getdetail"), 
            "GET_SUPPLIER_GOODS_URL should not contain 'getdetail' pattern");
    }
    
    @Test 
    public void testFlowUrlPatternsAreConsistent() {
        // Verify that flow-related URLs follow the same pattern as other list/detail URLs
        String flowDetailUrl = League.GET_SUPPLIER_BALANCE_FLOW_DETAIL_URL;
        String flowListUrl = League.GET_SUPPLIER_BALANCE_FLOW_LIST_URL;
        
        // Flow detail URL should follow proper pattern
        Assert.assertTrue(flowDetailUrl.contains("/flow/detail/get"), 
            "Flow detail URL should use '/flow/detail/get' pattern");
        Assert.assertFalse(flowDetailUrl.contains("flowdetail"), 
            "Flow detail URL should not use 'flowdetail' pattern");
            
        // Flow list URL should follow proper pattern  
        Assert.assertTrue(flowListUrl.contains("/flow/list/get"),
            "Flow list URL should use '/flow/list/get' pattern");
        Assert.assertFalse(flowListUrl.contains("flowlist"),
            "Flow list URL should not use 'flowlist' pattern");
    }
    
    @Test
    public void testAllUrlsHaveCorrectBasePattern() {
        // Verify all supplier URLs start with the correct base path
        String expectedBase = "https://api.weixin.qq.com/channels/ec/league/headsupplier/";
        
        String[] supplierUrls = {
            League.ADD_SUPPLIER_GOODS_URL,
            League.LIST_SUPPLIER_GOODS_URL,
            League.REMOVE_SUPPLIER_GOODS_URL,
            League.GET_SUPPLIER_GOODS_URL,
            League.GET_SUPPLIER_AUTH_URL,
            League.GET_SUPPLIER_AUTH_STATUS_URL,
            League.GET_SUPPLIER_BALANCE_URL,
            League.GET_SUPPLIER_BALANCE_FLOW_DETAIL_URL,
            League.GET_SUPPLIER_BALANCE_FLOW_LIST_URL,
            League.GET_SUPPLIER_ITEM_URL,
            League.GET_SUPPLIER_ITEM_LIST_URL,
            League.GET_SUPPLIER_ORDER_URL,
            League.GET_SUPPLIER_ORDER_LIST_URL, // This was the working URL mentioned in the issue
            League.GET_SUPPLIER_SHOP_URL,
            League.GET_SUPPLIER_SHOP_LIST_URL
        };
        
        for (String url : supplierUrls) {
            Assert.assertTrue(url.startsWith(expectedBase), 
                "URL " + url + " should start with " + expectedBase);
        }
    }
    
    @Test
    public void testWorkingUrlPatternIsFollowed() {
        // The issue mentioned that GET_SUPPLIER_ORDER_LIST_URL works correctly
        // All similar URLs should follow the same pattern
        String workingUrl = League.GET_SUPPLIER_ORDER_LIST_URL;
        String workingPattern = "/order/list/get";
        
        Assert.assertTrue(workingUrl.endsWith(workingPattern), 
            "Working URL should end with " + workingPattern);
            
        // Other list URLs should follow the same pattern
        Assert.assertTrue(League.LIST_SUPPLIER_GOODS_URL.endsWith("/list/get"),
            "LIST_SUPPLIER_GOODS_URL should follow the same pattern as working URL");
        Assert.assertTrue(League.GET_SUPPLIER_ITEM_LIST_URL.endsWith("/list/get"), 
            "GET_SUPPLIER_ITEM_LIST_URL should follow the same pattern as working URL");
        Assert.assertTrue(League.GET_SUPPLIER_SHOP_LIST_URL.endsWith("/list/get"),
            "GET_SUPPLIER_SHOP_LIST_URL should follow the same pattern as working URL");
    }
}