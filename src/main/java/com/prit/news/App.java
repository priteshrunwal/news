package com.prit.news;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages = {"com.prit.news.configuration"})
@EnableAutoConfiguration(exclude=HibernateJpaAutoConfiguration.class)
public class App 
{
	
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    }
}
