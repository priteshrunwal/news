package com.prit.news.publish;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.prit.news.data.NewsArtical;

public abstract class AbstractDBBasePublisher implements PublishCatagary{

	@Autowired
	protected SessionFactory sessionFactory;
	@Transactional
	public void accept(NewsArtical artical) {
		if(artical != null &&  getAcceptedType().equals(artical.getContentType()) &&
				artical.getPublishTime()!= null && artical.getHeading()!= null) {
			sessionFactory.getCurrentSession().saveOrUpdate(artical);
		}
	}

	@Transactional
	public List<NewsArtical> getNews() {
		List<NewsArtical> result = 
				sessionFactory.getCurrentSession().getNamedQuery("NewsArtical.find").
				 setParameter("contentType", getAcceptedType()).list();
		return new ArrayList<>(result);
	}

	public abstract NewsArtical.ContentTypes getAcceptedType();
}
