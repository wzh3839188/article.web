/**
 * 
 */
package wz.article.service;

import wz.article.model.ArticleModel;
import wz.article.model.SiteCountModel;
import wz.article.model.SiteKeywordCountModel;

import java.util.List;

/**
 * @author jade
 *
 */
public interface ArticleBriefService {
	void save(ArticleModel article);
    
    List<ArticleModel> findAll();
    
    ArticleModel selectByArticleUrl(String articleUrl);

    List<SiteCountModel> selectSiteType();

    List<SiteKeywordCountModel> selectSiteKeyword(String siteType);

    List<ArticleModel> selectBySiteAndKeyword(String siteType, String keyword, int start, int offset);

    int selectArticleCount(String siteType, String keyword);
}
