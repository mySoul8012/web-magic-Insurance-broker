package com.example.demo;

import com.example.demo.db.AxbxwMySql;
import com.example.demo.db.Mysql;
import com.example.demo.model.AxbxwInfo;
import com.example.demo.model.JqBxInfo;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.HashMap;
import java.util.Map;

public class axbxw implements PageProcessor {
  private Site site = Site.me().setRetryTimes(5000).setSleepTime(0);



  /**
   * process the page, extract urls to fetch, extract the data and store
   *
   * @param page page
   */
  @Override
  public void process(Page page) {
    // 进行赋值
    AxbxwInfo axbxwInfo = new AxbxwInfo();

    // 获取页面的信息
    String name = page.getHtml().css("table.tbinfo").xpath("/table/tbody/tr/td[2]/text()").replace(" ", "").toString();
    axbxwInfo.setName(name);
    // 获取性别
    String gender = page.getHtml().css("table.tbinfo").xpath("/table/tbody/tr[2]/td[2]/text()").replace(" ", "").toString();
    axbxwInfo.setGender(gender);
    // 保险资格证号
    String insuranceQualificationNumber = page.getHtml().css("table.tbinfo").xpath("/table/tbody/tr[3]/td[2]/text()").replace(" ", "").toString();
    axbxwInfo.setCertificate(insuranceQualificationNumber);
    // 资格证书状态
    String insuranceQualificationState = page.getHtml().css("table.tbinfo").xpath("/table/tbody/tr[4]/td[2]/text()").replace(" ", "").toString();
    axbxwInfo.setFicateStates(insuranceQualificationState);
    // 查询有效截止日期
    String insuranceQualificationDate = page.getHtml().css("table.tbinfo").xpath("/table/tbody/tr[5]/td[2]/text()").replace(" ", "").toString();
    axbxwInfo.setEndDate(insuranceQualificationDate);
    //查询证书范围
    String certificateScope = page.getHtml().xpath("/html/body/div[5]/div[2]/div[4]/div[2]/div/table/tbody/tr[6]/td[2]/text()").toString();
    axbxwInfo.setBusinessScope(certificateScope);
    // 查询销售区域
    String area = page.getHtml().xpath("/html/body/div[5]/div[2]/div[4]/div[2]/div/table/tbody/tr[7]/td[2]/text()").toString();
    axbxwInfo.setSalesArea(area);
    // 查询所属公司
    String company = page.getHtml().xpath("/html/body/div[5]/div[2]/div[4]/div[2]/div/table/tbody/tr[8]/td[2]/text()").toString();
    axbxwInfo.setCompany(company);
    // 查询头像
    String mUrl = page.getHtml().css("div.expert_tx").xpath("/div/a/img/@src").toString();
    axbxwInfo.setUrl(mUrl);
    System.out.println(axbxwInfo);
    // 获取url
    String tmpurl = page.getUrl().toString().split("/")[4].toString();
    int tmpInteger = Integer.parseInt(tmpurl.split("_")[0]);
    tmpurl = "http://www.axbxw.com/agent/" +  ++tmpInteger  + "_cert.html";
    try {
      for(;;) {
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(tmpurl);
        int statusCode = client.executeMethod(method);
        if (statusCode == 302) {
          // url 增加
          tmpurl = "http://www.axbxw.com/agent/" +  ++tmpInteger  + "_cert.html";
        }
        if(statusCode != 302){
          break;
        }
      }
    }catch (Exception e){

    }

    if(axbxwInfo.getName() == null){
      page.setSkip(true);
    }

    page.putField("axbxInfo", axbxwInfo);


    page.addTargetRequest(tmpurl);
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
    Map<String, JqBxInfo> map = new HashMap<String, JqBxInfo>();
    Spider.create(new axbxw())
            .addUrl("http://www.axbxw.com/agent/20584" +
                    "_cert.html")
            .addPipeline(new AxbxwMySql())
            .thread(5000)
            .run();
  }
}
