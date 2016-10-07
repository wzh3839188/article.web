# article.web
工程包含两个部分:1、通过Gecco爬虫，对CSDN，51CTO和ITEYE的文章进行了爬取。通过访问API触发爬虫。2、为一个简单的web界面提供API访问接口。
# Description
将https://github.com/wzh3839188/gecco-article工程的爬虫方法一直到web工程中，可以通过请求http://localhost:8080/gecco/crawler?keyword={keyword}来启动。
数据自动存入数据库，具体配置在jdbc.properties中。
文章的展示在工程https://github.com/wzh3839188/article.angular，配合使用，可以在web端查看具体爬取的文章。
