package com.prit.news.publish;

import javax.transaction.Transactional;

import com.prit.news.data.NewsArtical.ContentTypes;
@Transactional
public class FinancePublisher extends AbstractDBBasePublisher {

	@Override
	public ContentTypes getAcceptedType() {
		return ContentTypes.FINANCE;
	}

}
