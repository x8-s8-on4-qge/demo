package com.example.demo.webservice.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

	@Bean
	ServletRegistrationBean<MessageDispatcherServlet> registHelloWorldServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<>(servlet, "/helloWorld/ws/*");
	}

	@Bean
	ServletRegistrationBean<MessageDispatcherServlet> registProductServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<>(servlet, "/product/ws/*");
	}

	@Bean(name = "HelloWorld")
	DefaultWsdl11Definition helloWorldWsdl11Definition(XsdSchema helloWorldSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("HelloWorldPort");
		wsdl11Definition.setLocationUri("/helloWorld/ws");
		wsdl11Definition.setTargetNamespace("https://demo-latest-owxa.onrender.com");
		wsdl11Definition.setSchema(helloWorldSchema);
		return wsdl11Definition;
	}

	@Bean(name = "Product")
	DefaultWsdl11Definition productWsdl11Definition(XsdSchema productSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("ProductPort");
		wsdl11Definition.setLocationUri("/product/ws");
		wsdl11Definition.setTargetNamespace("https://demo-latest-owxa.onrender.com");
		wsdl11Definition.setSchema(productSchema);
		return wsdl11Definition;
	}

	@Bean
	XsdSchema helloWorldSchema() {
		return new SimpleXsdSchema(new ClassPathResource("helloWorld.xsd"));
	}

	@Bean
	XsdSchema productSchema() {
		return new SimpleXsdSchema(new ClassPathResource("product.xsd"));
	}
}
