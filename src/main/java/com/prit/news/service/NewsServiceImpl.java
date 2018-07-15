package com.prit.news.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.prit.news.data.NewsArtical;
import com.prit.news.publish.PublishCatagary;

@Transactional
public class NewsServiceImpl implements NewsService {

	@Autowired
	List<PublishCatagary> publishCatagory;
	
	public void publishArticle(NewsArtical artical) {
		if(publishCatagory!= null) {
			for(PublishCatagary catagory:publishCatagory) {
				catagory.accept(artical);
			}
		}
	}

}
