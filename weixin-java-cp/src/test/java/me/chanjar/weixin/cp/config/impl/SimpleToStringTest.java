package me.chanjar.weixin.cp.config.impl;

/**
 * Simple test program to verify the toString() fix for Redis config implementations
 */
public class SimpleToStringTest {
    
    public static void main(String[] args) {
        System.out.println("Testing WxCp Redis Config toString() methods...");
        
        // Test WxCpDefaultConfigImpl (baseline)
        try {
            WxCpDefaultConfigImpl defaultConfig = new WxCpDefaultConfigImpl();
            defaultConfig.setCorpId("testCorpId");
            defaultConfig.setAgentId(1000);
            defaultConfig.setCorpSecret("testSecret");
            
            String result = defaultConfig.toString();
            System.out.println("✓ WxCpDefaultConfigImpl toString() works: " + (result != null && !result.isEmpty()));
        } catch (Exception e) {
            System.out.println("✗ WxCpDefaultConfigImpl toString() failed: " + e.getMessage());
            System.out.println("  This is expected due to GSON serialization issues with complex objects");
        }
        
        // Test WxCpRedissonConfigImpl without actual RedissonClient
        try {
            WxCpRedissonConfigImpl redissonConfig = new WxCpRedissonConfigImpl(null, "test:");
            redissonConfig.setCorpId("testCorpId");
            redissonConfig.setAgentId(1000);
            redissonConfig.setCorpSecret("testSecret");
            
            String result = redissonConfig.toString();
            System.out.println("✓ WxCpRedissonConfigImpl toString() works: " + (result != null && !result.isEmpty()));
            System.out.println("  Result: " + result);
        } catch (StackOverflowError e) {
            System.out.println("✗ WxCpRedissonConfigImpl toString() caused StackOverflowError");
        } catch (Exception e) {
            System.out.println("✗ WxCpRedissonConfigImpl toString() failed: " + e.getMessage());
        }
        
        // Test WxCpRedisTemplateConfigImpl without actual StringRedisTemplate
        try {
            WxCpRedisTemplateConfigImpl redisTemplateConfig = new WxCpRedisTemplateConfigImpl(null, "test:");
            redisTemplateConfig.setCorpId("testCorpId");
            redisTemplateConfig.setAgentId(1000);
            redisTemplateConfig.setCorpSecret("testSecret");
            
            String result = redisTemplateConfig.toString();
            System.out.println("✓ WxCpRedisTemplateConfigImpl toString() works: " + (result != null && !result.isEmpty()));
            System.out.println("  Result: " + result);
        } catch (StackOverflowError e) {
            System.out.println("✗ WxCpRedisTemplateConfigImpl toString() caused StackOverflowError");
        } catch (Exception e) {
            System.out.println("✗ WxCpRedisTemplateConfigImpl toString() failed: " + e.getMessage());
        }
        
        System.out.println("Test completed.");
    }
}