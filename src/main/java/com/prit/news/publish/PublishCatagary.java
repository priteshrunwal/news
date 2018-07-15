package com.prit.news.publish;

import java.util.List;

import com.prit.news.data.NewsArtical;

public interface PublishCatagary {
	void accept(NewsArtical artical);
	List<NewsArtical> getNews();
}
