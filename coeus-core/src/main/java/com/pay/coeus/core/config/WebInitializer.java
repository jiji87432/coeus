package com.pay.coeus.core.config;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChange;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * web初始化
 *
 * @author yongda.ren
 */
public class WebInitializer implements WebApplicationInitializer {
    private static final Logger logger = LoggerFactory.getLogger(WebInitializer.class);

    @Override
    public void onStartup(ServletContext servletContext)
            throws ServletException {
        logger.info("WebInitializer init start");
        // 创建Spring上下文
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(SpringRootConfig.class);
        try {
            rootContext.getEnvironment()
                       .getPropertySources()
                       .addFirst(new ResourcePropertySource("classpath:/application.properties"));
            //读取apollo配置
            Config config = ConfigService.getAppConfig();
            Map map = new HashMap(64);
            for (String key : config.getPropertyNames()) {
                map.put(key, config.getProperty(key, ""));
            }
            JSONObject jsonObject = JSONObject.fromObject(map);
            logger.info("=====ConfigService.getAppConfig()对应application.properties===== {}", jsonObject);
            ConfigChangeListener changeListener = (changeEvent) -> {
                logger.info("Changes for namespace {}", changeEvent.getNamespace());
                for (String key : changeEvent.changedKeys()) {
                    ConfigChange change = changeEvent.getChange(key);
                    logger.info("Change - key: {}, oldValue: {}, newValue: {}, changeType: {}",
                            change.getPropertyName(), change.getOldValue(), change.getNewValue(),
                            change.getChangeType());
                }
            };
            config.addChangeListener(changeListener);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("", e);
        }
        // 管理Spring上下文的生命周期
        servletContext.addListener(new ContextLoaderListener(rootContext));
        // 字符编码过滤器
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        FilterRegistration.Dynamic characterEncodingFilter = servletContext.addFilter("characterEncodingFilter", encodingFilter);
        characterEncodingFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");
        logger.info("WebInitializer init end");
    }

}
