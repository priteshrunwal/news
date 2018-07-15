package com.prit.news.publish;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import com.prit.news.data.NewsArtical;

public class BreakingNewsPublisher implements PublishCatagary{

	private TreeSet<NewsArtical> cache = new TreeSet<>((NewsArtical o1, NewsArtical o2) -> o1.getPublishTime().compareTo(o2.getPublishTime()));
	private static int cacheSize = 100;
	public void accept(NewsArtical artical) {
		if(artical != null && NewsArtical.NewsPriority.BREAKING.equals(artical.getPriority()) && 
				artical.getPublishTime()!= null && artical.getHeading()!= null) {
			synchronized (cache) {
				cache.add(artical);
				if(cache.size()>cacheSize) {
					cache.remove(cache.last());
				}
			}
		}
	}

	public List<NewsArtical> getNews() {
		return new ArrayList<>(cache);
	}

}
