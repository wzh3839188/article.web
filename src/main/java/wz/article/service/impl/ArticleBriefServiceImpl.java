/**
 * 
 */
package wz.article.service.impl;

import wz.article.mapper.ArticleBriefMapper;
import wz.article.model.ArticleModel;
import wz.article.model.SiteCountModel;
import wz.article.model.SiteKeywordCountModel;
import wz.article.service.ArticleBriefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jade
 *
 */
@Service
public class ArticleBriefServiceImpl implements ArticleBriefService {

	@Autowired
    private ArticleBriefMapper articleBriefMapper;
	
	@Override
	public void save(ArticleModel article) {
		articleBriefMapper.insert(article);
	}

	@Override
	public List<ArticleModel> findAll() {
		return articleBriefMapper.findAll();
	}

	@Override
	public ArticleModel selectByArticleUrl(String articleUrl) {
		return articleBriefMapper.selectByArticleUrl(articleUrl);
	}

	@Override
	public List<SiteCountModel> selectSiteType() {
		return articleBriefMapper.selectSiteType();
	}

	@Override
	public List<SiteKeywordCountModel> selectSiteKeyword(String siteType) {
		return articleBriefMapper.selectSiteKeyword(siteType);
	}

	@Override
	public List<ArticleModel> selectBySiteAndKeyword(String siteType, String keyword, int start, int offset) {
		return articleBriefMapper.selectBySiteAndKeyword(siteType, keyword, start, offset);
	}

	@Override
	public int selectArticleCount(String siteType, String keyword) {
		return articleBriefMapper.selectArticleCount(siteType, keyword);
	}

}
