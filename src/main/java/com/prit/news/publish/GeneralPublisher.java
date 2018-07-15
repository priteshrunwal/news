package com.prit.news.publish;

import javax.transaction.Transactional;

import com.prit.news.data.NewsArtical.ContentTypes;
@Transactional
public class GeneralPublisher extends AbstractDBBasePublisher {

	@Override
	public ContentTypes getAcceptedType() {
		return ContentTypes.GENERAL;
	}

}
