package com.prit.news.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prit.news.Constant;
import com.prit.news.data.NewsArtical;
import com.prit.news.publish.PublishCatagary;
import com.prit.news.service.NewsService;

@RestController
@RequestMapping("")
public class WebHandler {

	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private NewsService newsService;
	
	@RequestMapping(value = Constant.POST_ARTICAL, method = RequestMethod.POST)
	public void postNews(@RequestBody NewsArtical artical) {
		newsService.publishArticle(artical);
	}
	
	@RequestMapping(method = RequestMethod.GET, path=Constant.GET_ARTICAL+"{publisher}")
	public List<NewsArtical> getNewsArtical(@PathVariable("publisher")String publisher){
		PublishCatagary publish = (PublishCatagary) context.getBean(publisher);
		if(publish!= null) {
			return publish.getNews();
		}
		return new ArrayList<>();
	}
}
