package com.fc.qd;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fc.base.TestBase;
import com.fc.restclient.RestClient;
import com.fc.util.TestUtil;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.fc.util.TestUtil.dtt;

//奇点小程序：信息流
public class IndexNewslist extends TestBase {

    //設置请求超时时间
    RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(60000)
            .setConnectTimeout(60000)
            .setConnectionRequestTimeout(60000)
            .build();
    TestBase testBase;
    RestClient restClient;
    CloseableHttpResponse res;

    // header
    HashMap<String,String> postHeader = new HashMap<String, String>();
    @BeforeClass
    public void setUp(){
        testBase = new TestBase();
        restClient = new RestClient();
    }
    @Test(description = "奇点小程序：信息流")
    public void filterCon()throws Exception{

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost("https://api.house.ifeng.com/news/iqidian/applets/IndexNewslist?tabName=capital&pageId=1&siteId=3066");
        post.setConfig(requestConfig);
        post.setHeader("Content-Type","application/json");
        res = httpClient.execute(post);
        int statusCode = TestUtil.getStatusCode(res);
        Assert.assertEquals(statusCode,200);
        Reporter.log("状态码："+statusCode);
        System.out.println(System.currentTimeMillis()+"信息流展示------------------------------------------------------------");
        try{
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
               String result =  EntityUtils.toString(res.getEntity());
               Reporter.log("result:"+result);
               JSONObject jsonObject =JSONObject.parseObject(result);
               System.out.println("返回json:"+jsonObject);
               JSONArray jsonArray =jsonObject.getJSONArray("data");
               List<String> list = new ArrayList<>();
                for ( int i=0;i<jsonArray.size();i++){
                   String cate =jsonArray.getJSONObject(i).getString("cate");
                   list.add(cate);
               }
                if (list.contains("资本论")){
                    System.out.println("资本信息流展示正常");
                    Assert.assertTrue(true);
                }else {
                    System.out.println("资本信息流展示异常");
                    Assert.assertTrue(false);
                }
            }
        }catch (Exception e){
            System.out.println("发生异常："+e.getMessage());
            Assert.assertTrue(false);
        } finally {
            try {
                res.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}





























