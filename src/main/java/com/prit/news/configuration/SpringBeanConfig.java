package com.prit.news.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.prit.news.publish.BreakingNewsPublisher;
import com.prit.news.publish.FinancePublisher;
import com.prit.news.publish.GeneralPublisher;
import com.prit.news.publish.PublishCatagary;
import com.prit.news.publish.SportsPublisher;
import com.prit.news.service.NewsService;
import com.prit.news.service.NewsServiceImpl;

@Configuration
@ComponentScan(basePackages = {"com.prit.news"})
@EnableTransactionManagement
public class SpringBeanConfig {

	@Bean
	public NewsService getNewsService() {
		return new NewsServiceImpl();
	}
	
	@Bean(name="sports")
	public PublishCatagary getSportsPublisher() {
		return new SportsPublisher();
	}
	
	@Bean(name="breaking_news")
	public PublishCatagary getBreakingNewPublisher() {
		return new BreakingNewsPublisher();
	}
	
	@Bean(name="finance")
	public PublishCatagary getFinancePublisher() {
		return new FinancePublisher();
	}
	
	@Bean(name="general")
	public PublishCatagary getGeneralPublisher() {
		return new GeneralPublisher();
	}
	
	@Bean
	public List<PublishCatagary> getListPublisher(){
		List<PublishCatagary> publisher = new ArrayList<>();
		publisher.add(getBreakingNewPublisher());
		publisher.add(getFinancePublisher());
		publisher.add(getGeneralPublisher());
		publisher.add(getSportsPublisher());
		return publisher;
	}

	@Bean
	public RequestMappingHandlerAdapter getRequestMappingHandlerAdapter() {
		RequestMappingHandlerAdapter handler = new RequestMappingHandlerAdapter();
		List<HttpMessageConverter<?>> convertor = new ArrayList<>();
		convertor.add(new MappingJackson2HttpMessageConverter());
		handler.setMessageConverters(convertor);
		return handler;
	}
	
	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
		factory.setDataSource(getDataSource());
		Properties prop = new Properties();
		prop.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		prop.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		factory.setHibernateProperties(prop);
		factory.setPackagesToScan("com.prit.news.data");
		return factory;
	}
	
	@Bean
	public DriverManagerDataSource getDataSource() {
		DriverManagerDataSource manager = new DriverManagerDataSource();
		manager.setDriverClassName("org.hsqldb.jdbc.JDBCDriver");
		manager.setUrl("jdbc:hsqldb:mem:prit;");
		return manager;
	}

	@Bean
	public HibernateTransactionManager getTransactionManager() {
		HibernateTransactionManager manager = new HibernateTransactionManager();
		manager.setSessionFactory(getSessionFactory().getObject());
		return manager;
	}
}
