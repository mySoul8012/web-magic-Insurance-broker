package com.example.demo;

import com.example.demo.db.Mysql;
import com.example.demo.model.JqBxInfo;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.*;

// 金泉保险
public class Jqbx implements PageProcessor {

  // 抓取网站的相关配置
  private Site site = Site.me().setRetryTimes(5000).setSleepTime(0);
  private Map<String, JqBxInfo> map ;

  public Jqbx(Map<String, JqBxInfo> map){
    this.map = map;
  }

  @Override
  public void process(Page page){


    // 创建保险经纪人的info
    List<String> listInfo = new ArrayList<>();
    // 获取div元素
    //System.out.println(page.getHtml().css("div.dlr-ci-body").xpath("/div/h3[1]").xpath("/h3/span[1]/text()").all());
    // 获取相关的数据
    JqBxInfo jqBxInfo = new JqBxInfo();
    // 获取页面url
    String url = page.getUrl().toString();
    // 进行拆分
    String[] urls = url.split("/");
    // 获取最后一个参数
    Integer integer = Integer.parseInt(urls[urls.length - 1]);
    // 输出
    //System.out.println(integer);
    // 名称
    String name = page.getHtml().css("dl.dl-detail").xpath("/dl/dd[1]/text()").get().toString();
   // System.out.println(name);
    jqBxInfo.setName(name);
    //System.out.println(jqBxInfo);
    // 获取性别
    String gender = page.getHtml().css("dl.dl-detail").xpath("/dl/dd[2]/text()").get().toString();
    //System.out.println(gender);
    jqBxInfo.setGender(gender);
    // 获取城市
    String city = page.getHtml().css("dl.dl-detail").xpath("/dl/dd[3]/text()").get().toString();
    //System.out.println(city);
    jqBxInfo.setCity(city);
    // 获取公司
    String company = page.getHtml().css("dl.dl-detail").xpath("/dl/dd[4]/text()").get().toString();
    //System.out.println(company);
    jqBxInfo.setCompany(company);
    // 获取vip等级
    String vipLevel = page.getHtml().css("dl.dl-detail").xpath("/dl/dd[5]/text()").get().toString();
    //System.out.println(vipLevel);
    jqBxInfo.setVipLevel(vipLevel);
    // 获取工作年限
    String workingYears = page.getHtml().css("dl.dl-detail").xpath("/dl/dd[7]/text()").get().toString();
   // System.out.println(workingYears);
    jqBxInfo.setWorkingYears(workingYears);
    // 获取擅长险种
    String goodAtInsurance = page.getHtml().css("dl.dl-detail").xpath("/dl/dd[8]/text()").get().toString();
    //System.out.println(goodAtInsurance);

    jqBxInfo.setGoodAtInsurance(goodAtInsurance);
    // 获取资格证号
    String qualificationNumber = page.getHtml().css("dl.dl-detail").xpath("/dl/dd[9]/text()").get().toString();
   // System.out.println(qualificationNumber);
    jqBxInfo.setQualificationNumber(qualificationNumber);
    // 获取从业经历
    String businessExperience = page.getHtml().css("ul.dlr-detail-jobs").xpath("/ul/").replace("<li>", "").replace("</li>", "").all().toString();
    jqBxInfo.setBusinessExperience(businessExperience);
    // 获取url
    String Avatarurl = page.getHtml().css("img.avatar-lg").xpath("//img/@src").toString();
    jqBxInfo.setUrl(Avatarurl);
    System.out.println(jqBxInfo);
    map.put(integer.toString(), jqBxInfo);
    //System.out.println(businessExperience);
    //System.out.println(businessExperience);
    //System.out.println(businessExperience);
    //jqBxInfo.setBusinessExperience(businessExperience);

    // 获取下一环
    String tmpUrl = ("http://www.jq-bx.com/dailiren/" + (integer + 1));



    try {
      for(;;) {
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(tmpUrl);
        integer++;
        int statusCode = client.executeMethod(method);
        if (statusCode == 500) {
          // url 增加
          tmpUrl = ("http://www.jq-bx.com/dailiren/" + (integer + 1));
        }
        if(statusCode != 500){
          break;
        }
      }
    }catch (Exception e){

    }

    // 数据添加
    page.putField("jqBxInfo", jqBxInfo);

    page.addTargetRequest(tmpUrl);


    // page.addTargetRequests(Collections.singletonList("http://www.jq-bx.com/dailiren/index?page=2"));
  }

  @Override
  public Site getSite() {
    return site;
  }



  public static void main(String[] args){
    Map<String, JqBxInfo>  map = new HashMap<String, JqBxInfo>();
    Spider.create(new Jqbx(map))
            .addUrl("http://www.jq-bx.com/dailiren/11500")
           .addPipeline(new Mysql())
            .thread(5000)
            .run();
  }
}
