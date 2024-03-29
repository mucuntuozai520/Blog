package com.clf.blog.shiro.config;

import java.util.LinkedHashMap;
import java.util.Map;

import com.clf.blog.shiro.realm.AdminRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ShiroConfig {

    /**
     * 注入AdminRealm
     *
     * @return
     */
    @Bean
    public AdminRealm getUserRealm() {
        return new AdminRealm();
    }

    /**
     * 创建shiroFilterFactoryBean对象，设置安全管理器
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        //创建ShiroFilterFactoryBean对象
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //添加Shiro内置过滤器
        /*
         * Shiro内置过滤器，可以实现权限相关的拦截器
         * 常用的拦截器：
         * 		anon：无需认证或登录就可以访问
         * 		authc：必须认证才可以访问
         * 		user：如果使用rememberMe的功能可以直接访问
         * 		perms：该资源必须得到资源权限才可以访问
         * 		role：该资源必须得到角色权限才可以访问
         */
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        /*******************设置放行请求*******************/
        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/admin", "anon");
        filterChainDefinitionMap.put("/admin/login", "anon");
        /*******************设置身份验证请求*******************/
        //拦截/admin以下的所有请求
        filterChainDefinitionMap.put("/admin/**", "authc");
        //所有请求必须经过身份验证，此配置放最后
        //filterChainDefinitionMap.put("/**", "authc");
        //设置登录页，如果不设置默认会自动寻找web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/admin");
        //设置过滤器链
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        //返回shiroFilterFactoryBean对象
        return shiroFilterFactoryBean;

    }

    /**
     * 创建DefaultWebSecurityManager对象，关联realm
     *
     * @param adminRealm
     * @return
     */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(AdminRealm adminRealm) {
        //创建DefaultWebSecurityManager对象
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //设置关联的realm
        defaultWebSecurityManager.setRealm(adminRealm);
        //返回DefaultWebSecurityManager对象
        return defaultWebSecurityManager;

    }
}
