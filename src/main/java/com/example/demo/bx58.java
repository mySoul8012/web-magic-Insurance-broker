package com.example.demo;

import com.example.demo.db.*;
import com.example.demo.model.Bx58Bean;
import com.example.demo.model.JqBxInfo;
import com.google.gson.reflect.TypeToken;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;
import us.codecraft.webmagic.utils.HttpConstant;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.db.vobaoMysql;
import com.example.demo.model.JqBxInfo;
import com.example.demo.model.RequestInfo;
import com.google.gson.Gson;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;
import us.codecraft.webmagic.utils.HttpConstant;

public class bx58 implements PageProcessor {
  private Site site = Site.me().setRetryTimes(5000).setSleepTime(0);
  /**
   * process the page, extract urls to fetch, extract the data and store
   *
   * @param page page
   */
  @Override
  public void process(Page page) {
    // 获取json数据
    String json = page.getHtml().xpath("/html/body/text()").toString();
    json = json.substring(8);
    json = json.substring(0, json.indexOf("]") + 1);
    System.out.println(json);
    // 数据解析成为javabean
    Gson gson = new Gson();
    List<Bx58Bean> bx58Beans = new ArrayList<>();
    bx58Beans = gson.fromJson(json, new TypeToken<List<Bx58Bean>>(){}.getType());
    // 插入库中
    page.putField("bx", bx58Beans);
  }

  /**
   * get the site settings
   *
   * @return site
   * @see Site
   */
  @Override
  public Site getSite() {
    return this.site;
  }


  public static void main(String[] args){
    for(int i = 4; i <= 100; i++) {
      for(int j = 0; j <= 100; j++) {
        for(int a = 0; a <= 100; a++) {
          System.out.println(i);
          System.out.println(j);
          System.out.println(a);
          Request request = new Request("https://m.bx58.com/Ajax/AjaxAgentPepoleList");
          request.setMethod(HttpConstant.Method.POST);
          request.setRequestBody(HttpRequestBody.json(
                  "{\n" +
                          "    \"page\": 1,\n" +
                          "    \"size\": 100,\n" +
                          "    \"proid\": " + i + ",\n" +
                          "    \"cityid\": " + j + ",\n" +
                          "    \"gsid\": "+ a +"\n" +
                          "}", "utf-8"
          ));
          Spider.create(new bx58())
                  .addRequest(request)
                  .addPipeline(new PipBx58())
                  .thread(5)
                  .run();
        }
      }
    }
  }
}
