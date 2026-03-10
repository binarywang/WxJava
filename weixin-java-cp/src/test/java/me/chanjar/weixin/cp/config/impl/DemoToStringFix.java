package me.chanjar.weixin.cp.config.impl;

import me.chanjar.weixin.common.redis.WxRedisOps;

/**
 * Demonstration of the fix for toString() StackOverflowError issue
 */
public class DemoToStringFix {
    
    public static void main(String[] args) {
        System.out.println("=== Demonstrating toString() Fix for WxCp Redis Config ===");
        
        // Create a simple stub WxRedisOps implementation for testing
        WxRedisOps stubRedisOps = new WxRedisOps() {
            @Override
            public String getValue(String key) { return null; }
            @Override
            public void setValue(String key, String value, int expire, java.util.concurrent.TimeUnit timeUnit) {}
            @Override
            public Long getExpire(String key) { return null; }
            @Override
            public void expire(String key, int expire, java.util.concurrent.TimeUnit timeUnit) {}
            @Override
            public java.util.concurrent.locks.Lock getLock(String key) { return null; }
        };
        
        // Test AbstractWxCpInRedisConfigImpl directly with our stub
        AbstractWxCpInRedisConfigImpl config = new AbstractWxCpInRedisConfigImpl(stubRedisOps, "demo:") {
            // Anonymous class to test the abstract parent
        };
        
        config.setCorpId("demoCorpId");
        config.setAgentId(1001);
        
        System.out.println("Testing toString() method:");
        try {
            String result = config.toString();
            System.out.println("✓ Success! toString() returned: " + result);
            System.out.println("✓ No StackOverflowError occurred");
            
            // Verify the result contains expected information and excludes redisOps
            boolean containsCorpId = result.contains("demoCorpId");
            boolean containsAgentId = result.contains("1001");
            boolean containsKeyPrefix = result.contains("demo:");
            boolean excludesRedisOps = !result.contains("redisOps") && !result.contains("WxRedisOps");
            
            System.out.println("✓ Contains corpId: " + containsCorpId);
            System.out.println("✓ Contains agentId: " + containsAgentId);
            System.out.println("✓ Contains keyPrefix: " + containsKeyPrefix);
            System.out.println("✓ Excludes redisOps: " + excludesRedisOps);
            
            if (containsCorpId && containsAgentId && containsKeyPrefix && excludesRedisOps) {
                System.out.println("✓ All validations passed!");
            } else {
                System.out.println("✗ Some validations failed");
            }
            
        } catch (StackOverflowError e) {
            System.out.println("✗ StackOverflowError still occurred - fix failed");
        } catch (Exception e) {
            System.out.println("✗ Unexpected error: " + e.getMessage());
        }
        
        System.out.println("\n=== Demo completed ===");
    }
}