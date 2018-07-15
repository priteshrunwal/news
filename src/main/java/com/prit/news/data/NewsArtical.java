package com.prit.news.data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="NewsArtical")
@NamedQuery(name="NewsArtical.find", query="SELECT p FROM NewsArtical p where p.contentType=:contentType ORDER BY p.publishTime DESC")
public class NewsArtical {
	
	@Enumerated(EnumType.STRING)
	private ContentTypes contentType;
	
	@Enumerated(EnumType.STRING)
	private NewsPriority priority;
	
	private Date publishTime;
	
	@Id
	private String heading;
	
	private String content;
	
	
	public ContentTypes getContentType() {
		return contentType;
	}
	public void setContentType(ContentTypes contentType) {
		this.contentType = contentType;
	}
	
	
	public NewsPriority getPriority() {
		return priority;
	}
	public void setPriority(NewsPriority priority) {
		this.priority = priority;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public String getHeading() {
		return heading;
	}
	public void setHeading(String heading) {
		this.heading = heading;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public enum ContentTypes{
		SPORTS,
		FINANCE,
		GENERAL
	}
	public enum NewsPriority{
		BREAKING,
		GENERAL
	}
}
