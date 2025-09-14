package com.binarywang.spring.starter.wxjava.miniapp.configuration.services;

import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaRedisBetterConfigImpl;
import com.binarywang.spring.starter.wxjava.miniapp.properties.WxMaMultiProperties;
import com.binarywang.spring.starter.wxjava.miniapp.service.WxMaMultiServices;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.redis.RedisTemplateWxRedisOps;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 自动装配基于 redisTemplate 策略配置
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>  2025/9/10
 */
@Configuration
@ConditionalOnProperty(prefix = WxMaMultiProperties.PREFIX + ".config-storage", name = "type", havingValue = "redis_template")
@RequiredArgsConstructor
public class WxMaInRedisTemplateConfiguration extends AbstractWxMaConfiguration {
    private final WxMaMultiProperties wxMaMultiProperties;
    private final ApplicationContext applicationContext;

    @Bean
    public WxMaMultiServices wxMaMultiServices() {
        return this.wxMaMultiServices(wxMaMultiProperties);
    }

    @Override
    protected WxMaDefaultConfigImpl wxMaConfigStorage(WxMaMultiProperties wxMaMultiProperties) {
        return this.configRedisTemplate(wxMaMultiProperties);
    }

    private WxMaDefaultConfigImpl configRedisTemplate(WxMaMultiProperties wxMaMultiProperties) {
        StringRedisTemplate redisTemplate = applicationContext.getBean(StringRedisTemplate.class);
        RedisTemplateWxRedisOps wxRedisOps = new RedisTemplateWxRedisOps(redisTemplate);
        return new WxMaRedisBetterConfigImpl(wxRedisOps, wxMaMultiProperties.getConfigStorage().getKeyPrefix());
    }

}
