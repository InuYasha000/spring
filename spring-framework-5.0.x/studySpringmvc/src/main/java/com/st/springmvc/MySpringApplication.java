package com.st.springmvc;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import java.io.File;

// springboot 源码的做法， 初始化不是一个web环境， 而是通过java读写html文件， 彻底丢弃web.xml
public class MySpringApplication {
    // 不需要addWebapp, 而是直接手动添加我们的DispatcherServlet到tomcat的servlet中
    // 而不需要tomcat去回调onStartup方法（执行onstarup方法我们也是为了添加dispatcherServlet到tomcat的servlet）

    public static void run() throws ServletException, LifecycleException {
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        ac.register(AppConfig.class);

        // 这个refresh方法竟然可以不用手动调用！，在下面的init方法中会自动调用ac的refresh方法
		// 之所以不在这里调用而是在dispatcherServlet的init方法中调用
		// 是因为我们注册一些WebMvcConfig配置的时候需要使用EnableWebMvc注解， 而这个注解需要dispatcherServlet，但是此时dispatcherServlet还没有new出来
		// 如果要用@EnableWebMvc注解 就不能在这里调用ac.refresh
//        ac.refresh();

        DispatcherServlet dispatcherServlet = new DispatcherServlet(ac);

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8090);

        String sourcePath = MySpringApplication.class.getResource("/").getPath();
        File base = new File(System.getProperty("java.io.tmpdir"));
        Context rootCtx = tomcat.addContext("/",base.getAbsolutePath());

        // 直接在tomcat中addServlet
		// addServlet(dispatcherServlet)时会调用dispatcherServlet（实际是HttpServletBean的init方法）的init方法， 会调用ac.refresh方法
        Tomcat.addServlet(rootCtx, "name", dispatcherServlet).setLoadOnStartup(1);
        rootCtx.addServletMapping("/", "name");
        // 启动tomcat
        tomcat.start();
        tomcat.getServer().await();
    }


    public static void main(String[] args) throws ServletException, LifecycleException {
		MySpringApplication.run();
    }
}
