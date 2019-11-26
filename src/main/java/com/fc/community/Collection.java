package com.fc.community;

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

//13 收藏与取消收藏1收藏 2 取消收藏
public class Collection extends TestBase {
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
        testCaseExecel = System.getProperty("user.dir")+prop.getProperty("Collection");
    }
    @DataProvider(name = "postData")
    public Object[][] post() throws IOException{
        return  dtt(testCaseExecel,0);
    }
    @Test(dataProvider = "postData",description = "收藏")
    public void filterCon(String contenx)throws Exception{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost("https://www.izhiliao.com/community/api/loupan/collect/op?type=1&lpId=501364&status=1");
        post.setConfig(requestConfig);
        //设置头信息
        post.setHeader("Content-Type","application/x-www-form-urlencoded");
        post.setHeader("Cookie","IFH_CLUSTER_SID=45320113A0064A15A55E27699B470338; firstLogin=false; nickName=\\u69\\u66\\u65\\u6e\\u67\\u5f\\u32\\u34\\u35\\u33; 300_community_basic_list_url=/community/basic/list; Hm_lvt_becf67d756bfea5219f687e0ce01da80=1571814467,1572967097; izl_plus_manager_sid=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE1NzQxMjk0NzksImlzcyI6ImF1dGgwIiwibW9iaWxlIjoiMTM3MTgyNDI0NTMiLCJleHAiOjE1NzQzODg2Nzl9.XWYTSAdvKQPlWqY2__OvCzwuBEtp4uJTXJrqXKk4wOM; Hm_lvt_8ddeae4dc7e58e29c04151fd536bd57a=1572967066,1573526595,1574667951; izl_site=3066_bj; sajssdk_2015_cross_new_user=1; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2216ea5c43e848a-08e5daf1447152-3a614f0b-2073600-16ea5c43e85ac7%22%2C%22%24device_id%22%3A%2216ea5c43e848a-08e5daf1447152-3a614f0b-2073600-16ea5c43e85ac7%22%2C%22props%22%3A%7B%22%24latest_referrer%22%3A%22https%3A%2F%2Fhouse.ifeng.com%2F%22%2C%22%24latest_referrer_host%22%3A%22house.ifeng.com%22%2C%22%24latest_traffic_source_type%22%3A%22%E5%BC%95%E8%8D%90%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC%22%7D%7D; aaaaaaaaaaa=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE1NzQ3NTA1MjEsImlzcyI6ImF1dGgwIiwibW9iaWxlIjoiMTM3MTgyNDI0NTMiLCJleHAiOjE1NzUwMDk3MjF9.wxgb_cnhZbps3jrHjV4B_WCAHH7bME-Zuq0UYpr1-3U; 300_house_basic_list_url=/house/basic/list; izl_sid=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJJRkgiLCJleHAiOjE1NzUwMTAwNjgsImluZm8iOiIxMzcxODI0MjQ1MyxodHRwOi8vczAuaWZlbmdpbWcuY29tLzIwMTcvMDkvMDYvbWFsZV8wZDhjNTI4YS5wbmcsaWZlbmdfdWNfMTczODAwNjgsaXVfMTczODAwNjgsaWZlbmdfMjQ1MyJ9.pvHptab_k9YdD81CHcN-kVb4grb5R2l0SmnZ_mTqaBM; Hm_lpvt_8ddeae4dc7e58e29c04151fd536bd57a=1574750871");
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
                    Assert.assertTrue(true);
                } else {
                        System.out.println("收藏失败");
                        Reporter.log("收藏失败");
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





























