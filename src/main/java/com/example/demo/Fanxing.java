package com.example.demo;

import com.example.demo.db.FangxingMysql;
import com.example.demo.db.Mysql;
import com.example.demo.model.FangXingBaoInfo;
import com.example.demo.model.JqBxInfo;
import com.google.gson.internal.$Gson$Preconditions;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Fanxing implements PageProcessor {
  private Site site = Site.me().setRetryTimes(5000).setSleepTime(0);
  /**
   * process the page, extract urls to fetch, extract the data and store
   *
   * @param page page
   */
  @Override
  public void process(Page page) {
    FangXingBaoInfo fangXingBaoInfo = new FangXingBaoInfo();
    // 获取姓名
    String name = page.getHtml().css("div.box1").xpath("/div/table/tbody/tr/td[1]/h2/a/text()").toString();
    fangXingBaoInfo.setName(name);
    // 获取保险公司
    String company = page.getHtml().css("div.box1").xpath("/div/table/tbody/tr/td[3]/text()").toString();
    fangXingBaoInfo.setCompany(company);
    // 工作地点
    String localhost = page.getHtml().css("div.box1").xpath("/div/table/tbody/tr/td[4]/text()").toString();
    fangXingBaoInfo.setLocalhost(localhost);
    // 从业年限
    String workingYears = page.getHtml().css("div.box2").xpath("/div/table/tbody/tr/td/div/text()").toString();
    fangXingBaoInfo.setWorkingYears(workingYears);
    // 上一职业
    String previousCareer = page.getHtml().css("div.box2").xpath("/div/table/tbody/tr[2]/td/div/text()").toString();
    fangXingBaoInfo.setPreviousPost(previousCareer);
    // 星座
    String constellation = page.getHtml().css("div.box2").xpath("/div/table/tbody/tr[3]/td[2]/div/text()").toString();
    fangXingBaoInfo.setConstellation(constellation);
    // 擅长领域
    String[] areasExpertise = page.getHtml().css("div.box2").xpath("/div/table[2]/tbody/tr/td[2]/div/a/span/text()").all().toArray(new String[0]);
    fangXingBaoInfo.setAreasExpertise(areasExpertise);
    //System.out.println(Arrays.toString(areasExpertise));
    // 获取头像
    String ArerUrl = page.getHtml().css("div.xqTop").xpath("/div/div/div/img").xpath("//img/@src").toString();
    fangXingBaoInfo.setUrl(ArerUrl);


    // 获取个人介绍，个人介绍
    String personalIntroduction = page.getHtml().css("div.grjs").xpath("/div/dl/dd/text()").toString();
    //System.out.println(personalIntroduction);
    fangXingBaoInfo.setPersonalIntroduction(personalIntroduction);
    //System.out.println(fangXingBaoInfo);

    // 获取个人介绍，特长爱好信息
    String hobby = page.getHtml().css("div.grjs").xpath("/div/dl[2]/dd/text()").toString();
    fangXingBaoInfo.setPersonalHobby(hobby);

    // 获取个人介绍之所获得的荣誉
    String honor = page.getHtml().css("div.jmtj").replace("<div class=\"jmtj\"> \n" +
            " ", "").replace("<ul> \n" +
            "  ", "").replace("<li class=\"top1\">", "").
            replace("  <li class=\"top2\">", "").
            replace("  <li class=\"top3\">", "").
            replace("</li>", "").
            replace("  \n" +
                    " </ul> ", "").
            replace("\n" +
                    "</div>", "").
            replace(" \n" +
                    " </ul> ", "").
            replace("<li class=\"top3\">", "").
            replace("\"", "").
            replace("“", "")
            .replace("”", "")
            .replace("\'", "")
            .toString();
    fangXingBaoInfo.setHonor(honor);
    System.out.println(fangXingBaoInfo);
    // 判断是否为空，跳过
    if(fangXingBaoInfo.getName() == null || fangXingBaoInfo.getName() == ""){
      page.setSkip(true);
    }

    // 获取url数字
    String urlString = page.getUrl().toString().split("/")[4];
    int intInteger = Integer.parseInt(urlString.replace(".html", "").trim());

    // 拼接url
    String tmpUrl = "http://www.fangxinbao.com/yingxiaoyuan/" +  ++intInteger  +".html";

    page.putField("fangXingBaoInfo", fangXingBaoInfo);
    page.addTargetRequests(Collections.singletonList(tmpUrl));
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
    Spider.create(new Fanxing())
            .addUrl("http://www.fangxinbao.com/yingxiaoyuan/92916" +
                    ".html")
           .addPipeline(new FangxingMysql())
            .thread(5000)
            .run();
  }
}
