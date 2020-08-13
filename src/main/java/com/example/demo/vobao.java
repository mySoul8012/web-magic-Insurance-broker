package com.example.demo;

import com.example.demo.db.PingAnMysql;
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

import java.util.List;


public class vobao implements PageProcessor {
  private Site site = Site.me().setRetryTimes(50000).setSleepTime(0);
          //.addHeader(":authority:", "m.vobao.com")
          //.addHeader(":method", "POST")
          //.addHeader(":path:", "/Agent/AgentPartial2")
          //.addHeader(":scheme", "https")
          //.addHeader("accept", "text/plain, */*; q=0.01")
          //.addHeader("accept-encoding", "gzip, deflate, br")
          //.addHeader("accept-language", "zh,zh-CN;q=0.9")
         // .addHeader("content-length","112")
         // .addHeader("content-type", "application/x-www-form-urlencoded; charset=UTF-8");
          //.addHeader("cookie", "User_Behavior_Cache=RN626Z7MRTTFH3PZCGH517GOR8CLRAUD; UM_distinctid=173b3ac024e36a-0d4633b2932dc1-3323765-144000-173b3ac024f5ca; ASP.NET_SessionId=jh3dn0x5tls0e3lcckayiaaw; xccMyCptbSession=id=151067292601201; Hm_lvt_489bc42f6321c679104f900d0989523b=1597163967,1597201713; Hm_lpvt_489bc42f6321c679104f900d0989523b=1597201713; SERVERID=286d376d14216a74976378fcd80be34f|1597201713|1597201705")
          //.addHeader("dnt", "1")
          ////.addHeader("origin", "https://m.vobao.com")
          //.addHeader("referer", "https://m.vobao.com/member/Beijing-BeiJing")
          //.addHeader("sec-fetch-dest", "empty")
          //.addHeader("sec-fetch-mode", "cors")
          //.addHeader("sec-fetch-site", "same-origin")
          //.addHeader("user-agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.105 Mobile Safari/537.36")
          //.addHeader("x-requested-with", "XMLHttpRequest");


  /**
   * process the page, extract urls to fetch, extract the data and store
   *
   * @param page page
   */
  @Override
  public void process(Page page) {
    // 从body中获取inta和intb
    String jsonRes = new String(page.getRequest().getRequestBody().getBody());
    //System.out.println(jsonRes);
    Gson gson = new Gson();
    RequestInfo requestInfo = gson.fromJson(jsonRes, RequestInfo.class);
//    // 页数加上
//    //requestInfo.setArea1List1((Integer.parseInt(requestInfo.getArea1List1()) + 1) + "");
//    requestInfo.setArea2List2((Integer.parseInt(requestInfo.getArea2List2()) + 1) + "");
//    System.out.println("Area2List2---------------" + requestInfo.getArea2List2());
    requestInfo.setPageindex(((Integer.parseInt(requestInfo.getPageindex()) + 1 )+ ""));
    String resNewJson = gson.toJson(requestInfo);
    //System.out.println(resNewJson);

    Request request = new Request("https://m.vobao.com/Agent/AgentPartial2");
    request.setMethod(HttpConstant.Method.POST);
    request.setRequestBody(HttpRequestBody.json(
            resNewJson,
            "utf-8"));
   // System.out.println(resNewJson);
    // 获取页面url
    List<String> urls = page.getHtml().links().regex("http://m.(.*).cn").all();
  //  System.out.println(requestInfo.getPageindex().equals("300"));
    System.out.println(requestInfo.getPageindex());
    if(urls == null || urls.size() == 0 || requestInfo.getPageindex().equals("30000000")){
      page.setSkip(true);
    }

    System.out.println(urls.size());

    page.putField("url", urls);
    page.addTargetRequest(request);
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
    int count = 1;
    for(count = 1; count <= 100; count++) {
      System.out.println("count: -----------------" +  count);
      Request request = new Request("https://m.vobao.com/Agent/AgentPartial2");
      request.setMethod(HttpConstant.Method.POST);
      request.setRequestBody(HttpRequestBody.json(
              "{\n" +
                      "    \"Search.Pagesize\": \"10000\",\n" +
                      "    \"Search.Pageindex\": \"1" +
                      "\",\n" +
                      "    \"Search.CompSymbol\": \"\",\n" +
                      "    \"Search.CitySymbol\": \"\",\n" +
                      "    \"Search.ProvinceSymbol\": \"\"\n" +
                      "}",
              "utf-8"));
      HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
      httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("125.92.100.104",23564)));
            Spider.create(new vobao())
              .addRequest(request)
             // .setDownloader(httpClientDownloader)
              .addPipeline(new vobaoMysql())
              .thread(50000)
              .run();
    }
  }
}
