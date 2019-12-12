package com.amwater.mysource.service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.inject.Inject;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SynonymService implements InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}

	/*private ConcurrentMap<String,String> synonymMap ;
	@Inject
	SynonymsRepository synonymsRepository;
	
	@Scheduled(fixedRate = 3600000)
	public void sync() {
		List<Synonyms> synonyms = synonymsRepository.findAll();
		for(Synonyms synonym :synonyms ) {
			for(String word : synonym.getSynonyms()) {
				synonymMap.putIfAbsent(word.trim(), synonym.getKeyword());
			}
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if(null == synonymMap) {
			synonymMap = new ConcurrentHashMap<>();
		}
	}
	
	public String getSynonym(String query) {
		if(null == synonymMap) {
			synonymMap = new ConcurrentHashMap<>();
			sync();
		}
		String keyword = synonymMap.get(query);
		if(keyword != null) {
			return keyword;
		} else {
			return query;
		}
	}*/
}
