package com.fc.newHouse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fc.base.TestBase;
import com.fc.base.TestBases;
import com.fc.restclient.RestClient;
import com.fc.util.TestUtils;
import com.google.common.collect.ArrayListMultimap;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.alibaba.fastjson.JSON.parseObject;
import static com.fc.util.TestUtil.dtt;
import static com.fc.util.TestUtils.dtts;

/**
 * 列表页筛选项接口(PC/WAP)
 */
public class HouseFilter extends TestBases {
    //設置请求超时时间
    RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(60000)
            .setConnectTimeout(60000)
            .setConnectionRequestTimeout(60000)
            .build();
    TestBase testBase;
    RestClient restClient;
    CloseableHttpResponse res;
    //host 根url
    String host;
    //excel 路劲
    String testCaseExecel;
    // header
    HashMap<String,String> postHeader = new HashMap<String, String>();
    @BeforeClass
    public void setUp(){
        testBase = new TestBase();
        restClient = new RestClient();
        postHeader.put("Content-type","application/json");
        //载入配置文件，接口endpoint
        host = prop.getProperty("Host");
        testCaseExecel = System.getProperty("user.dir")+prop.getProperty("HouseTrendList");
    }
    @DataProvider(name = "postData")

    public Object[][] post() throws IOException {
        return dtts(testCaseExecel,0);
    }
    @Test(dataProvider = "postData",description = "新房：列表页筛选项接口(PC/WAP)")
    public void filterCon(String content) throws Exception{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost  post = new HttpPost("https://dict.izhiliao.com/house/api/filter");
        post.setConfig(requestConfig);
        post.setHeader("Content-Type","application/json");

        Map<String,String> map = new HashMap<String,String>();
        map.put("siteId","3066");
        StringEntity s = new StringEntity(JSON.toJSONString(map));
        s.setContentEncoding("UTF-8");
        post.setEntity(s);

        //發送請求
       res =httpClient.execute(post);
       int statusCode=  TestUtils.getStatusCode(res);
        Assert.assertEquals(statusCode,200);
        try {
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String reslut = EntityUtils.toString(res.getEntity());
                JSONObject jsonObject =JSONObject.parseObject(reslut);
                System.out.println("返回json:"+jsonObject);
                String msgValue = jsonObject.getString("msg");
                if(msgValue.equals("操作成功")){
                    JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                    JSONArray  jsonArray = jsonObject1.getJSONArray("locationList");
                    ArrayList<String> arrayNames = new ArrayList<String>();
                    for (int i=0;i<jsonArray.size();i++){
                        String names=jsonArray.getJSONObject(i).getString("name");
                        arrayNames.add(names);
                    }
                    System.out.println("===");

                    String expectName="海淀";
                    if (arrayNames.contains(expectName)){
                        System.out.println("列表页筛选项接口包含： "+expectName);
                        Assert.assertTrue(true);
                    }else {
                        System.out.println("列表页筛选项接口包含： "+expectName);
                        Assert.assertTrue(false);
                    }
                }else {
                    System.out.println("返回失敗");
                    Assert.assertTrue(false);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                res.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }

}
