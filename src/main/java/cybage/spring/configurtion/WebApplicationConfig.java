package cybage.spring.configurtion;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles2.TilesConfigurer;

import cybage.spring.handler.DateInsertionInterceptor;
import cybage.spring.mvc.ErrorHandler;

@Configuration
@EnableWebMvc
//@ImportResource({ "/WEB-INF/spring/applicationContext.xml" })
public class WebApplicationConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addFormatters(FormatterRegistry registry) {
		// register converters and formatters...
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		// add message converters...
	}

	@Override
	public void addInterceptors(InterceptorRegistry reg) {
		reg.addInterceptor(dataHandler());
	}
	
	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		exceptionResolvers.add(errorHandler());
	}

//	@Override
	@Bean
	public Validator validator() {
		LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
		factory.setValidationMessageSource(messageSource());
		return factory;
	}
	
	
	@Bean
	public ErrorHandler errorHandler(){
		ErrorHandler handler = new ErrorHandler();
		return handler;
	}
		

	
	@Bean
	public InternalResourceViewResolver jspViewResolver() {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setPrefix("/WEB-INF/jsp/");
		bean.setSuffix(".jsp");
		return bean;
	}

	// -----------------------------------------//
	// Configures Tiles at application startup //
	// -----------------------------------------//
//	@Bean
//	public TilesConfigurer tilesConfigurer() {
//		TilesConfigurer configurer = new TilesConfigurer();
//		configurer.setDefinitions(new String[] { "/WEB-INF/layouts/tiles.xml"
//		// "/WEB-INF/views/**/tiles.xml"
//				});
//		configurer.setCheckRefresh(true);
//		return configurer;
//	}

	// bean id="validator"
	// class="org.springframework.validation.beanvalidation.LocalValidatorFactoryBean"
	// />

	/**
	 * <!-- JSR-303 --> <bean id="validator" class=
	 * "org.springframework.validation.beanvalidation.LocalValidatorFactoryBean"
	 * > <property name="validationMessageSource" ref="messageSource"/> </bean>
	 * 
	 * @return
	 */

	// @Bean
	// public LocalValidatorFactoryBean validator() {
	// return new LocalValidatorFactoryBean();
	// }

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setCacheSeconds(10);
		return messageSource;

	}

	@Bean
	public DateInsertionInterceptor dataHandler() {
		return new DateInsertionInterceptor();
	}

}
