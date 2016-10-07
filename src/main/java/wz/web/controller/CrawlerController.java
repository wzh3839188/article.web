package wz.web.controller;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.pipeline.PipelineFactory;
import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.request.HttpRequest;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import wz.web.entity.BaseResponse;

import java.util.ArrayList;
import java.util.List;

import static wz.web.constants.GeccoConstants.MAXPAGE;
import static wz.web.constants.GeccoConstants.SITELIST;

/**
 * Created by zhuowang on 16/10/5.
 */
@Controller
@RequestMapping(value="/gecco")
public class CrawlerController {

    @Autowired
    private PipelineFactory springPipelineFactory;

    @RequestMapping("/crawler")
    @ResponseBody
    public BaseResponse startCrawler(@RequestParam(value="keyword",required = true) String keyword){
        if(Strings.isNullOrEmpty(keyword)){
            return new BaseResponse("1", "keyword不能为空", null);
        }
        List<HttpRequest> requestList = new ArrayList<>();
        for(int i = 0;i < SITELIST.length;i++){
            String[] siteInfo = SITELIST[i];
            for(int page = 1;page <= MAXPAGE;page++){
                HttpGetRequest start = new HttpGetRequest(String.format(siteInfo[1], page, keyword));
                start.addParameter("siteType", siteInfo[0]);
                start.setCharset("UTF-8");
                requestList.add(start);
            }
        }
        GeccoEngine.create()
                .pipelineFactory(springPipelineFactory)
                .classpath("wz.article")
                .start(requestList)
                .run();
        return new BaseResponse();
    }
}
