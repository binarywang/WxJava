package me.chanjar.weixin.cp.config.impl;

import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;

/**
 * Test for toString() method of Redis-based WxCp config implementations
 * to ensure they don't throw StackOverflowError
 *
 * @author AI Assistant
 */
public class WxCpRedisConfigToStringTest {

    @Test
    public void testWxCpRedissonConfigImplToString() {
        // Test that toString() doesn't throw StackOverflowError
        RedissonClient redissonClient = mock(RedissonClient.class);
        WxCpRedissonConfigImpl config = new WxCpRedissonConfigImpl(redissonClient, "test:");
        config.setCorpId("testCorpId");
        config.setAgentId(1000);
        config.setCorpSecret("testSecret");

        // This should not throw StackOverflowError
        try {
            String result = config.toString();
            Assert.assertNotNull(result);
            Assert.assertFalse(result.isEmpty());
        } catch (StackOverflowError e) {
            Assert.fail("toString() should not throw StackOverflowError", e);
        }
    }

    @Test
    public void testWxCpRedisTemplateConfigImplToString() {
        // Test that toString() doesn't throw StackOverflowError
        StringRedisTemplate stringRedisTemplate = mock(StringRedisTemplate.class);
        WxCpRedisTemplateConfigImpl config = new WxCpRedisTemplateConfigImpl(stringRedisTemplate, "test:");
        config.setCorpId("testCorpId");
        config.setAgentId(1000);
        config.setCorpSecret("testSecret");

        // This should not throw StackOverflowError
        try {
            String result = config.toString();
            Assert.assertNotNull(result);
            Assert.assertFalse(result.isEmpty());
        } catch (StackOverflowError e) {
            Assert.fail("toString() should not throw StackOverflowError", e);
        }
    }

    @Test
    public void testWxCpDefaultConfigImplToString() {
        // Test baseline case - this should work fine
        WxCpDefaultConfigImpl config = new WxCpDefaultConfigImpl();
        config.setCorpId("testCorpId");
        config.setAgentId(1000);
        config.setCorpSecret("testSecret");

        // This should work without issues
        try {
            String result = config.toString();
            Assert.assertNotNull(result);
            Assert.assertFalse(result.isEmpty());
        } catch (StackOverflowError e) {
            Assert.fail("toString() should not throw StackOverflowError", e);
        }
    }
}