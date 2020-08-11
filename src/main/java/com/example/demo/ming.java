package com.example.demo;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;

public class ming extends Thread  {
  /**
   * When an object implementing interface <code>Runnable</code> is used
   * to create a thread, starting the thread causes the object's
   * <code>run</code> method to be called in that separately executing
   * thread.
   * <p>
   * The general contract of the method <code>run</code> is that it may
   * take any action whatsoever.
   *
   * @see Thread#run()
   */
  @Override
  public void run() {
    super.run();
    int i = 0;
    while(true){
      HttpClient httpClient = new HttpClient();
      GetMethod method = new GetMethod("http://www.lingyunbx.com/dailiren/show-193.html");
      try {
        i++;
        int code = httpClient.executeMethod(method);
        System.out.println(code + " " + i);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
