package com.asion.springboot_demol1.configer;

import com.asion.springboot_demol1.component.LoginHandlerIntercepter;
import com.asion.springboot_demol1.component.MyLocaleResolver;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
//@EnableAutoConfiguration
public class MyMvcCoonfig extends WebMvcConfigurerAdapter{
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    //浏览器发送/atguigu请求时  进入templates 文件夹下的success页面
        registry. addViewController("/atguigu").setViewName("suceess");
     //   registry.addViewController("/index").setViewName("index");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       // super.addInterceptors(registry);
        registry.addInterceptor(new LoginHandlerIntercepter()).addPathPatterns("/**")
                .excludePathPatterns("/","/index.html","/user/login","/asserts/**");
    }

    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter=new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/login.html").setViewName("login");
                registry.addViewController("/list.html").setViewName("list");
                registry.addViewController("/main.html").setViewName("dashboard");
            }
        };

        return adapter;
    }
    @Bean
    public LocaleResolver myLocaleResolver(){
        MyLocaleResolver v=new MyLocaleResolver();
        return v;
    }
}
