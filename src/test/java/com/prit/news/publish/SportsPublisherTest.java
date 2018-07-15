package com.prit.news.publish;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.prit.news.configuration.SpringBeanConfig;
import com.prit.news.data.NewsArtical;
import com.prit.news.data.NewsArtical.ContentTypes;
import com.prit.news.data.NewsArtical.NewsPriority;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringBeanConfig.class})
public class SportsPublisherTest {
	 @Autowired
	 @Qualifier("sports")
	 private PublishCatagary catagory;
	 
	 @Test
	 public void sendReceiveNewsWrong() {
		 NewsArtical artical = new NewsArtical();
		 artical.setContent("News Content");
		 artical.setHeading("Heading");
		 catagory.accept(artical);
		 catagory.accept(null);
		 artical.setContentType(ContentTypes.FINANCE);
		 catagory.accept(artical);
		 artical.setPriority(NewsPriority.GENERAL);
		 catagory.accept(artical);
		 List<NewsArtical> list = catagory.getNews();
		 assertTrue(list.size()==0);
	}
	
	@Test
	public void sendReceiveNewsPositive() {
		NewsArtical artical = new NewsArtical();
		 artical.setContent("News Content");
		 artical.setHeading("Heading");
		 artical.setContentType(ContentTypes.SPORTS);
		 artical.setPriority(NewsPriority.GENERAL);
		 artical.setPublishTime(new Date());
		 catagory.accept(artical);
		 List<NewsArtical> list = catagory.getNews();
		 assertTrue(list.size()==1);
	}
}
