/**
 * 
 */
package wz.article.mapper;

import org.apache.ibatis.annotations.Param;
import wz.article.model.ArticleModel;
import wz.article.model.SiteCountModel;
import wz.article.model.SiteKeywordCountModel;

import java.util.List;

/**
 * @author jade
 *
 */
public interface ArticleBriefMapper {
	int deleteByPrimaryKey(Integer id);

    int insert(ArticleModel record);

    ArticleModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(ArticleModel record);

	List<ArticleModel> findAll();
	
	ArticleModel selectByArticleUrl(String articleUrl);

    List<SiteCountModel> selectSiteType();

    List<SiteKeywordCountModel> selectSiteKeyword(String siteType);

    List<ArticleModel> selectBySiteAndKeyword(@Param("siteType") String siteType,
                                              @Param("keyword") String keyword,
                                              @Param("start") int start,
                                              @Param("offset") int offset);

    int selectArticleCount(@Param("siteType") String siteType,
                           @Param("keyword") String keyword);
}
