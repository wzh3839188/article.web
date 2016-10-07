/**
 * 
 */
package wz.article.pipeline;

import wz.article.crawler.ITEYESearchPage;
import wz.article.entity.ITETYEArticleListEntity;
import wz.article.model.ArticleModel;
import wz.article.service.ArticleBriefService;
import com.geccocrawler.gecco.pipeline.Pipeline;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jade
 *
 */
@Service("ITEYESearchListPipeline")
public class ITEYESearchListPipeline implements Pipeline<ITEYESearchPage> {

	@Resource(name="articleBriefServiceImpl")
	private ArticleBriefService articleBriefService;
	
	@Override
	public void process(ITEYESearchPage bean) {
		List<ITETYEArticleListEntity> articleList = bean.getArticleList();
		if(articleList != null){
			for(ITETYEArticleListEntity entity : articleList){
				if(entity.getArticleTitle() != null){
					ArticleModel model = articleBriefService.selectByArticleUrl(entity.getArticleUrl());
					if(model == null){
						//插入数据库
						model = entity.generateModel();
						model.setKeyword(bean.getKeyword());
						model.setSiteType(bean.getSiteType());
						articleBriefService.save(model);
						//TODO 爬取子页面，即文章详情页
					}
				}
			}
		}
	}

}
