package com.amwater.mysource.transformer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.safety.Whitelist;
import org.springframework.beans.factory.annotation.Value;

import com.amwater.mysource.constants.IMySourcePiplineConstants;
import com.amwater.mysource.dto.ArticleType;
import com.amwater.mysource.dto.IntranetArticle;
import com.apporchid.cloudseer.common.transformer.BaseTransformer;
import com.apporchid.common.utils.PdfUtils;
import com.apporchid.core.mso.util.MSODataObjectHelper;
import com.apporchid.foundation.mso.IMSODataObject;
import com.apporchid.foundation.transformer.exception.TransformerInitializationException;

public class ContentMSOEnricher extends BaseTransformer<ContentMSOEnricherProperties> {

	private static final long serialVersionUID = 1189420216750706600L;

	public ContentMSOEnricher(ContentMSOEnricherProperties taskProperties) {
		super(taskProperties);
	}

	@Value("${amwater.intranet.pdfContentBaseURL}")
	private String pdfContentBaseURL;
	
	@Value("${amwater.intranet.expiryMonths:2}")
	private int expiryMonths;
	
	@Override
	protected void initalizeTransformer() throws TransformerInitializationException {
		super.initalizeTransformer();
	}

	@Override
	public Object transform(Object data) {
		IMSODataObject inputData = MSODataObjectHelper.getInstance(IMySourcePiplineConstants.MSO_REFRENCE_INTRANET_ARTICLE);
		try {
			IntranetArticle article = (IntranetArticle)data;
			if (article.getType() == null) {
				inputData.setFieldValue("type", ArticleType.OtherDocument);
			} else if (article.getType().equals(ArticleType.PdfDocument)) {
				try { 
					if(pdfContentBaseURL.startsWith("http://") || pdfContentBaseURL.startsWith("https://")) {
						
						if(article.getArticleUrl().startsWith("http//") || article.getArticleUrl().startsWith("https//")) {
							inputData.setFieldValue("content", stripHtml(article.getArticleUrl()));
						} else {
							inputData.setFieldValue("content", PdfUtils.getContent(pdfContentBaseURL + article.getArticleUrl()));
						}
							
					} else {
						inputData.setFieldValue("content", "http://" + pdfContentBaseURL + article.getArticleUrl());
					}
				} catch (Exception e) {
					logger.error("Exception during pdf content extraction." + e.getMessage());
				}
			}
			performValidation( article) ;
			if(article.getPriority() == null) {
				inputData.setFieldValue("priority", article.getPriority());
			}
			// prepare LocationCategories and functionalCategories
			List<String> locationTopics = new ArrayList<>();
			List<String> functionalTopics = new ArrayList<>();
			inputData.setFieldValue("staticContent", false);
			prepareLocationAndFunctionalCategories(article.getTopics(), locationTopics, functionalTopics);
			inputData.setFieldValue("locationTopics", locationTopics);
			inputData.setFieldValue("functionalTopics", functionalTopics);
			for (String strTemp : functionalTopics)
			{
				if(strTemp.equals("Static_Content"))
				{
					inputData.setFieldValue("staticContent", true);
				}
			}
			//Set the original content.
			//inputData.setFieldValue("originalContent", article.getContent());
			inputData.setFieldValue("content", stripHtml(article.getContent()));
				//Set expiry date
				if(article.getExpirationDate() == null && article.getPublishedDate() != null) {
					if(StringUtils.isNotBlank(article.getTitle()) && 
							(article.getTitle().toUpperCase().contains("SPLASH")|| article.getTitle().contains("SPLASH POINTS")
									||article.getTitle().contains("SP") || article.getTitle().contains("SPLASHPOINTS"))) {
						Date expirationDate = new DateTime(article.getPublishedDate()).toDateTime(DateTimeZone.UTC)
								.plusMonths(expiryMonths).toDate();
						inputData.setFieldValue("expirationDate", expirationDate);
					}
				}else {
					inputData.setFieldValue("expirationDate", article.getExpirationDate() == null ? null
							: DateUtils.formatDate(article.getExpirationDate(), "yyyy-MM-dd HH:mm:ss"));
				}
			inputData.setFieldValue("additionalData", article.getAdditionalData());
			inputData.setFieldValue("articleSource", article.getArticleSource());
			inputData.setFieldValue("articleUrl", article.getArticleUrl());
			inputData.setFieldValue("bannerUrl", article.getBannerUrl());
			inputData.setFieldValue("topics", article.getTopics());
			
			inputData.setFieldValue("id", article.getId());
			inputData.setFieldValue("isFeatured",article.isFeatured());
			inputData.setFieldValue("lastUpdatedBy", article.getLastUpdatedBy());
			if(article.getLastUpdatedDate() != null) {
				inputData.setFieldValue("lastUpdatedDate", (article.getLastUpdatedDate() == null) ? null
						: DateUtils.formatDate(article.getLastUpdatedDate(), "yyyy-MM-dd HH:mm:ss"));
			} else if(this.transformerProperties.getAction() != null && "update".equals(this.transformerProperties.getAction())) {
				inputData.setFieldValue("lastUpdatedDate", Calendar.getInstance().getTime());
			}
			inputData.setFieldValue("publishedBy", article.getPublishedBy());
			if(article.getPublishedDate() != null) {
				inputData.setFieldValue("publishedDate", (article.getPublishedDate() == null) ? null
						: DateUtils.formatDate(article.getPublishedDate(), "yyyy-MM-dd HH:mm:ss"));
			} else if(this.transformerProperties.getAction() != null && "create".equals(this.transformerProperties.getAction())) {
				inputData.setFieldValue("publishedDate", Calendar.getInstance().getTime());
			}
			inputData.setFieldValue("summary", article.getSummary());
			inputData.setFieldValue("tags", article.getTags());
			//inputData.setFieldValue("suggestedTags", article.getSuggestedTags());
			inputData.setFieldValue("thumbNailUrl", article.getThumbNailUrl());
			inputData.setFieldValue("title", article.getTitle());
			//inputData.setFieldValue("displayOverlay", article.isDisplayOverlay());
			if (article.getOriginalContent() != null) {
				inputData.setFieldValue("original_content", article.getOriginalContent());
			}
			if (article.getPriorityTags() != null && !article.getPriorityTags().isEmpty())
				inputData.setFieldValue("priorityTags", article.getPriorityTags());
			if (article.getRelatedContents() != null && !article.getRelatedContents().isEmpty())
				inputData.setFieldValue("relatedContents", article.getRelatedContents());
			if (article.getRoleIds() != null && !article.getRoleIds().isEmpty())
				inputData.setFieldValue("roleIds", article.getRoleIds());
			//inputData.setFieldValue("source", article.getSource());
			inputData.setFieldValue("subTitle", article.getSubTitle());
			
		} catch (Exception e) {
			logger.error("Error while executing ContentMSOEnricher.transform ", e);
		}
		return inputData;
	}
	
	public void prepareLocationAndFunctionalCategories(Collection<String> topics, List<String> locationTopics,
			List<String> functionalTopics) {

		if (topics != null && !topics.isEmpty()) {
			for (String topic : topics) {
				if (topic.startsWith("Business and Locations/")) {
					locationTopics.add(topic);
				} else if (!topic.equalsIgnoreCase("Business and Locations/")) {
					functionalTopics.add(topic);
				}
			}
		}

	}
	/**
	 * basic validations
	 * @param article
	 * @throws Exception
	 */
	protected void performValidation(IntranetArticle article) throws Exception {
		if(StringUtils.isEmpty(article.getId())||StringUtils.isEmpty(article.getTitle())
				//|| null == article.getPublishedOn()
				|| null == article.getArticleSource() || null == article.getType()){
			throw new Exception("invalidData");
		}
		if (article.getType().equals(ArticleType.HtmlDocument) && StringUtils.isEmpty(article.getContent())) {
				throw new Exception("invalidData");
		}
		if (!article.getType().equals(ArticleType.HtmlDocument) && StringUtils.isEmpty(article.getArticleUrl())) {
			throw new Exception("invalidData");
		}
	}
	protected String stripHtml(String originalText) {
		if (StringUtils.isNotBlank(originalText)) {
			Document document = Jsoup.parse(originalText);
			document.select("br").append(" ");
			document.select("p").prepend(" ");
			String formattedText = Jsoup.clean(document.text(), "", Whitelist.none(),
					new Document.OutputSettings().prettyPrint(false));
			formattedText = Parser.unescapeEntities(formattedText, false);
			formattedText = formattedText.replaceAll("[^\\p{ASCII}]", " ");
			formattedText = formattedText.replaceAll("[^a-zA-Z\n]", " ");
			return formattedText;
		} else {
			return originalText;
		}
	}
}
