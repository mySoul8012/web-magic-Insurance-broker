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
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant;

import java.util.List;


public class vobao implements PageProcessor {
  private Site site = Site.me().setRetryTimes(50000).setSleepTime(0).addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");


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
    // 页数加上
    //requestInfo.setArea1List1((Integer.parseInt(requestInfo.getArea1List1()) + 1) + "");
    requestInfo.setArea2List2((Integer.parseInt(requestInfo.getArea2List2()) + 1) + "");
    System.out.println("Area2List2---------------" + requestInfo.getArea2List2());
    String resNewJson = gson.toJson(requestInfo);
    //System.out.println(resNewJson);

    Request request = new Request("https://member.vobao.com/Member/SellerList?Length=6");
    request.setMethod(HttpConstant.Method.POST);
    request.setRequestBody(HttpRequestBody.json(
            resNewJson,
            "utf-8"));

    // 获取页面url
    List<String> urls = page.getHtml().links().regex("(http://www.(.*).cn)").all();
    System.out.println(requestInfo.getArea2List2().equals("300"));
    if(urls == null || urls.size() == 0 ){
      page.setSkip(true);
    }

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
      Request request = new Request("https://member.vobao.com/Member/SellerList?Length=6");
      request.setMethod(HttpConstant.Method.POST);
      request.setRequestBody(HttpRequestBody.json(
              "{\n" +
                      "    \"Area1List1\":\" +  "  +  count   +  "  +\",\n" +
                      "    \"Area2List2\":\"1\",\n" +
                      "    \"Search.KeyWord\":\"\",\n" +
                      "    \"Search.AreaID1\":\"-100\",\n" +
                      "    \"Search.AreaID2\":\"0\",\n" +
                      "    \"Search.AreaID3\":\"0\",\n" +
                      "    \"Search.PyCode\":\"0\",\n" +
                      "    \"Search.CompanySymbol\":\"0\",\n" +
                      "    \"Search.Year\":\"0\",\n" +
                      "    \"Search.OrderTp\":\"1\",\n" +
                      "    \"Search.SearchTp\":\"1\",\n" +
                      "    \"Search.UserLevel\":\"0\",\n" +
                      "    \"Search.SortRule\":\"\",\n" +
                      "    \"Search.PageIndex\":\"2\",\n" +
                      "    \"Search.PageSize\":\"10\",\n" +
                      "    \"Search.LastHash\":\"0\",\n" +
                      "    \"Search.TotalPages\":\"8\",\n" +
                      "    \"Search.TotalCount\":\"80\",\n" +
                      "    \"sex\":\"1\",\n" +
                      "    \"X-Requested-With\":\"XMLHttpRequest\"\n" +
                      "}",
              "utf-8"));
      Spider.create(new vobao())
              .addRequest(request)
              .addPipeline(new vobaoMysql())
              .thread(50000)
              .run();
    }
  }
}
