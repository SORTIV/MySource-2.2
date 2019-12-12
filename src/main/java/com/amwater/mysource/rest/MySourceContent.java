package com.amwater.mysource.rest;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amwater.mysource.dto.ArticleSource;
import com.amwater.mysource.dto.IntranetArticle;
import com.amwater.mysource.dto.SearchRequest;
import com.amwater.mysource.service.MySourceContentSearchService;
import com.amwater.mysource.service.MySourceContentService;
import com.apporchid.common.utils.JsonUtils;
import com.apporchid.elasticsearch.dto.SearchResponse;
import com.apporchid.foundation.mso.IMSODataObject;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/intranet")
@Api(value = "amwater.com content", tags = "My Source(amwater.com) - Content", description = "REST endpoints to intranet")
public class MySourceContent {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Inject
	MySourceContentService contentService;
	
	@Inject
	MySourceContentSearchService contentSearchService;
	


	@PostMapping({ "/create" })
	@ApiOperation(value = "Index a Document", tags = "My Source(amwater.com) - Content", notes = "Index a new Document", response = IntranetArticle.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Document creation successful"),
			@ApiResponse(code = 500, message = "Document creation error") })
	public ResponseEntity<?> create(
			@ApiParam(value = "Content Document", required = true) @RequestBody IntranetArticle article) {

		try {
			String articleId=article.getId();
			if(StringUtils.isNotEmpty(articleId)) {
				 IMSODataObject articleResponse = contentService.getArticleById(articleId,null);
				if(articleResponse == null) {
					contentService.createArticleIndex(article);
				}else {
					contentService.updateArticleIndex(article);
				}
			}
			//contentService.addTagsForArticles(article);
			  IMSODataObject articleResponse = contentService.getArticleById(articleId,null);
			  if (articleResponse != null) {
				  return new ResponseEntity<IMSODataObject>( articleResponse, HttpStatus.OK);
			  } else {
			  return new ResponseEntity<IMSODataObject>(HttpStatus.NOT_FOUND); }
			 
		} catch (Exception e) {
			logger.error("Error while indexing a document ",e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		//return new  ResponseEntity<>( HttpStatus.OK); 
	}
	
	
	@PutMapping({ "/update" })
	@ApiOperation(value = "Update Indexes for an existing document", tags = "ContentIndex", notes = "Update document based on document Id and Content", response = IntranetArticle.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Document update successful"),	
			@ApiResponse(code = 500, message = "Document update error") })
	public ResponseEntity<?> update(@ApiParam(value = "Update Article Request", required = true) @RequestBody IntranetArticle request) {
		try {
			IntranetArticle response=null;
			if(request.getArticleSource() == null || ArticleSource.Intranet.equals(request.getArticleSource())) {//need to verify what is the difference  
				response=contentService.updateArticleIndex(request);
			}else {
				response=contentService.updateArticleIndex(request);
			}
			
			if(response.isStatus()) {
				IMSODataObject articleResponse = contentService.getArticleById(request.getId(),null);
			//tagService.addTagsForArticles(request);
				return new ResponseEntity<>(articleResponse, HttpStatus.ACCEPTED);
			}else {
				return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		} catch (Exception e) {
			logger.error("Error while indexing a document ",e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		//return new ResponseEntity<>(request, HttpStatus.ACCEPTED);
	}
	@ApiOperation(value = "Get  Document", tags = "ContentIndex", notes = "get document based on document Id and Content", response = IntranetArticle.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Article found"),
			@ApiResponse(code = 404, message = "Article not found") })
	@GetMapping({ "/{articleId}" })
	public ResponseEntity<IMSODataObject> getContent(
			@ApiParam(value = "Article Id", required = true) @PathVariable(name="articleId") String articleId) { //,		@ApiParam(value = "UserId", required = true) @RequestParam(value = "userId", required = true) String userId		
		IMSODataObject article;
		try {
			article = contentService.getArticleById(articleId, null);
			if (article != null) {
				return new ResponseEntity<IMSODataObject>(article, HttpStatus.OK);
			} else {
				return new ResponseEntity<IMSODataObject>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			logger.error("Error while getting content ",e);
			return new ResponseEntity<IMSODataObject>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@ApiOperation(value = "Get documents based on user's search query", tags = "Search", notes = "", response = SearchResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Search found"),
			@ApiResponse(code = 404, message = "No Documents found") })
	@PostMapping({ "/search" })
	public ResponseEntity<?> search(
			@ApiParam(value = "Search Request", required = true) @RequestBody SearchRequest searchRequest) {
		SearchResponse response;
		try {
			logger.debug("Search Request : " + JsonUtils.toJson(searchRequest));
			
			if(!(StringUtils.isBlank(searchRequest.getArticleSource()) || 
					ArticleSource.Intranet.name().equalsIgnoreCase(searchRequest.getArticleSource())
					|| ArticleSource.Internet.name().equalsIgnoreCase(searchRequest.getArticleSource()))){
				return new ResponseEntity<String>("ArticleSource should be Intranet/Internet", HttpStatus.BAD_REQUEST);
			}

			response = contentSearchService.search(searchRequest);
			logger.debug("Search Response : " + JsonUtils.toJson(response));

			return new ResponseEntity<SearchResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			if(e.getMessage().equals("invalidSearchData")) {
				return new ResponseEntity<>("Query and UserId are mandatory for search", HttpStatus.BAD_REQUEST);
			}
			logger.error("Failed Search Response : ", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
