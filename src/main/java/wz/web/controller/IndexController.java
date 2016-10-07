package wz.web.controller;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.pipeline.PipelineFactory;
import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.request.HttpRequest;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import wz.web.entity.BaseResponse;
import wz.web.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

import static wz.web.constants.GeccoConstants.MAXPAGE;
import static wz.web.constants.GeccoConstants.SITELIST;

/**
 * Created by zhuowang on 16/9/27.
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    @RequestMapping("/user/info")
    @ResponseBody
    public BaseResponse getUserInfo(@RequestParam String uid){
        return new BaseResponse("0", "操作成功", new UserEntity("wz", 21, 1, false));
    }

    @RequestMapping("/info")
    public String getInfo(@RequestParam String uid){
        return "articleIndex";
    }

    @RequestMapping("/manage")
    public String manage(@RequestParam String uid){
        return "CrawlerIndex";
    }
}

