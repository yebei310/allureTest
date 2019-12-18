package com.fc.community;

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
import java.util.HashMap;

import static com.fc.util.TestUtil.dtt;

//14 查看收藏状态
public class CollectionStatus extends TestBase {
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
        testCaseExecel = System.getProperty("user.dir")+prop.getProperty("CollectionStatus");
    }
    @DataProvider(name = "postData")
    public Object[][] post() throws IOException{
        return  dtt(testCaseExecel,0);
    }
    @Test(dataProvider = "postData",description = "查看收藏状态")
    public void filterCon(String contenx)throws Exception{

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost("https://www.izhiliao.com/community/api/loupan/collect/status?lpId=1078807");
        post.setConfig(requestConfig);
        //设置头信息
        post.setHeader("Content-Type","application/x-www-form-urlencoded");
        post.setHeader("Cookie","nickName=\\u69\\u66\\u65\\u6e\\u67\\u5f\\u32\\u34\\u35\\u33; 300_house_basic_list_url=/house/basic/list; izl_site=3066_bj; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2216eca3e6ea41ab-0f8f1ef3fe50c3-3a614f0b-2073600-16eca3e6ea5771%22%2C%22%24device_id%22%3A%2216eca3e6ea41ab-0f8f1ef3fe50c3-3a614f0b-2073600-16eca3e6ea5771%22%2C%22props%22%3A%7B%7D%7D; firstLogin=false; Hm_lvt_8ddeae4dc7e58e29c04151fd536bd57a=1575359526; ifh_agent_msid=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE1NzUzNjIyOTksImlzcyI6ImF1dGgwIiwibW9iaWxlIjoiMTM3MTgyNDI0NTMiLCJleHAiOjE1NzU2MjE0OTl9.0TY2rP3GKIwoCZlsRPuWZq0ektrlbvB5ikdrz0iQCQk; ifh_agent_siteid=3066; ck_login_id=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJJRkgiLCJtb2JpbGUiOiIxMzA3MDYzMTAwMiIsImV4cCI6MTU3NTQ1MDMxMX0._lA1DmGIa2SS8J9MBrWUKO1oAwBnV919ebmOHzMPTWk; 95_house_basic_list_url=/house/basic/list; 95_community_basic_list_url=/community/basic/list; Hm_lvt_becf67d756bfea5219f687e0ce01da80=1575614687; Hm_lpvt_becf67d756bfea5219f687e0ce01da80=1575614705; aaaaaaaaaaa=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE1NzY1NDU4MjcsImlzcyI6ImF1dGgwIiwibW9iaWxlIjoiMTM3MTgyNDI0NTMiLCJleHAiOjE1NzY4MDUwMjd9.NZR2zcReeIERNycE1tBcUoz0Md317AUY1yiIz48QtrQ; prov=cn010; city=010; weather_city=bj; region_ip=114.253.10.118; region_ver=1.30; izl_sid=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJJRkgiLCJleHAiOjE1NzY4MDUyMDksImluZm8iOiIxMzcxODI0MjQ1MyxodHRwOi8vczAuaWZlbmdpbWcuY29tLzIwMTcvMDkvMDYvbWFsZV8wZDhjNTI4YS5wbmcsaWZlbmdfdWNfMTczODAwNjgsaXVfMTczODAwNjgsaWZlbmdfMjQ1MyJ9.Eks60sPKRP8f8Owj7atwuiZF6pS9OWZ31vmubQR-o3g; Hm_lpvt_8ddeae4dc7e58e29c04151fd536bd57a=15765468");
        //发送post请求
        res = httpClient.execute(post);
        //从返回结果中获取状态码
        int statusCode = TestUtil.getStatusCode(res);
        Assert.assertEquals(statusCode,200);
        Reporter.log("状态码："+statusCode);
        try{
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                String result =  EntityUtils.toString(res.getEntity());
                Reporter.log("result:"+result);
                JSONObject jsonObject =JSONObject.parseObject(result);
                Reporter.log("返回json:"+jsonObject);
                System.out.println("返回json:"+jsonObject);
                String msgValue = jsonObject.getString("msg");
                Reporter.log("返回json:"+msgValue);
                if (msgValue.equals("操作成功")) {
                    JSONArray jsonObject1 = jsonObject.getJSONArray("data");
                    String status=null;
                    for (int i=0;i< jsonObject1.size();i++){
                        status = jsonObject1.getJSONObject(i).getString("status");
                    }

                    int stat=Integer.parseInt(status);
                    if (stat==1){
                        Reporter.log("状态是已经收藏");
                        System.out.println("--------------------------------------状态是已经收藏--------------------------------------");
                        Assert.assertTrue(true);
                    }else if (stat==2){
                        Reporter.log("状态是未收藏");
                        Assert.assertTrue(true);
                    }else {
                        Reporter.log("状态异常");
                        Assert.assertTrue(false);
                    }
                } else {
                        System.out.println("收藏状态异常");
                        Reporter.log("收藏状态异常");
                        Assert.assertTrue(false);
                    }
                }
                else {
                    Reporter.log("返回失败！");
                    Assert.assertTrue(false);
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





























