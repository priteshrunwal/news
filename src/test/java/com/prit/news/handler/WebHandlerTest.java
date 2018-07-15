package com.prit.news.handler;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.prit.news.Constant;
import com.prit.news.data.NewsArtical;
import com.prit.news.data.NewsArtical.ContentTypes;
import com.prit.news.data.NewsArtical.NewsPriority;

import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class WebHandlerTest {
	
	@Autowired
	private WebTestClient webClient;
	
	@Test
	public void getPost() {
		NewsArtical artical = new NewsArtical();
		 artical.setContent("News Content");
		 artical.setHeading("Heading");
		 artical.setContentType(ContentTypes.FINANCE);
		 artical.setPriority(NewsPriority.GENERAL);
		 artical.setPublishTime(new Date());
		 
		this.webClient.post().uri(Constant.POST_ARTICAL).contentType(MediaType.APPLICATION_JSON).body(Mono.just(artical),NewsArtical.class)
		.exchange().expectStatus().isOk();
		this.webClient.get().uri(Constant.GET_ARTICAL+"finance").exchange().expectStatus().isOk()
		.expectBody(List.class);
	}

}
