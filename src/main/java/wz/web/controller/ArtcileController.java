package wz.web.controller;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import wz.article.model.ArticleModel;
import wz.article.model.SiteCountModel;
import wz.article.model.SiteKeywordCountModel;
import wz.article.service.ArticleBriefService;
import wz.web.entity.ArticleListEntity;
import wz.web.entity.BaseResponse;

import java.util.List;

/**
 * Created by zhuowang on 16/10/5.
 */
@Controller
@RequestMapping("/article")
public class ArtcileController {

    @Autowired
    private ArticleBriefService articleBriefService;

    @RequestMapping("/sites")
    @ResponseBody
    public BaseResponse getSites(){
        List<SiteCountModel> siteCountModels = articleBriefService.selectSiteType();
        return new BaseResponse("0", "操作成功", siteCountModels);
    }

    @RequestMapping("/siteKeyword")
    @ResponseBody
    public BaseResponse getSiteKeyword(@RequestParam(required = true) String siteType){
        if(Strings.isNullOrEmpty(siteType)) return new BaseResponse("1", "参数siteType不可为空");
        List<SiteKeywordCountModel> keywordCountModels = articleBriefService.selectSiteKeyword(siteType);
        return new BaseResponse("0", "操作成功", keywordCountModels);
    }

    @RequestMapping("/articleBySiteAndKeyword")
    @ResponseBody
    public BaseResponse getArticlesBySiteAndKeyword(@RequestParam(required = true) String siteType,
                                                    @RequestParam(required = true) String keyword,
                                                    Integer page,
                                                    Integer pageSize){
        if(Strings.isNullOrEmpty(siteType)) return new BaseResponse("1", "参数siteType不可为空");
        if(Strings.isNullOrEmpty(keyword)) return new BaseResponse("1", "参数keyword不可为空");
        if(page == null || page <= 0) page = 1;
        if(pageSize == null || pageSize <= 0) pageSize = 10;
        ArticleListEntity entity = new ArticleListEntity();
        entity.setPage(page);
        entity.setPageSize(pageSize);
        int articleCount = articleBriefService.selectArticleCount(siteType, keyword);
        entity.setTotalCount(articleCount);
        if(articleCount > 0 && (page - 1) * pageSize <= articleCount) {
            List<ArticleModel> articleModels = articleBriefService.selectBySiteAndKeyword(siteType, keyword, (page - 1) * pageSize, pageSize);
            entity.setArticleModelList(articleModels);
        }
        return new BaseResponse("0", "操作成功", entity);
    }
}
