package wz.web.entity;

import wz.article.model.ArticleModel;

import java.util.List;

/**
 * Created by zhuowang on 16/10/6.
 */
public class ArticleListEntity {
    private int totalCount;
    private int page;
    private int pageSize;
    private List<ArticleModel> articleModelList;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<ArticleModel> getArticleModelList() {
        return articleModelList;
    }

    public void setArticleModelList(List<ArticleModel> articleModelList) {
        this.articleModelList = articleModelList;
    }
}
