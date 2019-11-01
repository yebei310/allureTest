package com.qa.tests.community;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qa.base.TestBase;
import com.qa.restclient.RestClient;
import com.qa.util.TestUtil;
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

import static com.qa.util.TestUtil.dtt;

//7 小区列表(根据小区ID筛选)
public class ListsByLpIds extends TestBase {
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
        testCaseExecel = System.getProperty("user.dir")+prop.getProperty("testCase1data");
    }
    @DataProvider(name = "postData")
    public Object[][] post() throws IOException{
        return  dtt(testCaseExecel,0);
    }
    @Test(dataProvider = "postData")
    public void filterCon(String contenx,String loginUrl,String username,String password)throws Exception{

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost("https://www.izhiliao.com/community/api/loupan/listsByLpIds?ids=500989");
        post.setConfig(requestConfig);
        //设置头信息
        post.setHeader("Content-Type","application/x-www-form-urlencoded");
        post.setHeader("Cookie","esf_hideBottomAdv=true; ifh_agent_msid=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE1NzEwMjM4MTAsImlzcyI6ImF1dGgwIiwibW9iaWxlIjoiMTM3MTgyNDI0NTMiLCJleHAiOjE1NzEyODMwMTB9.6-h5xgJL8yK4ckcqENRCFoP5E-LYYrmGTWK3_QnPbcI; ck_login_id=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJJRkgiLCJtb2JpbGUiOiIxMzcxODI0MjQ1MyIsImV4cCI6MTU3MTExNzk0Nn0.YBotWKL7rERPZbg6We9QbKjs3mu6gGXBbWR2IwgWCTU; coupon_menu_status=0; izl_site=3066_bj; Hm_lvt_8ddeae4dc7e58e29c04151fd536bd57a=1571121794; prov=cn010; city=010; weather_city=bj; region_ip=114.253.10.118; region_ver=1.30; Hm_lvt_becf67d756bfea5219f687e0ce01da80=1571122198; IFH_CLUSTER_SID=6A3505F540984C218534A650522D2C07; Hm_lpvt_becf67d756bfea5219f687e0ce01da80=1571128203; Hm_lpvt_8ddeae4dc7e58e29c04151fd536bd57a=1571128229; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2216dbed44a7115a-07077dfe5e9183-3a614f0b-2073600-16dbed44a72546%22%2C%22%24device_id%22%3A%2216dbed44a7115a-07077dfe5e9183-3a614f0b-2073600-16dbed44a72546%22%2C%22props%22%3A%7B%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%7D%7D");
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
                if (msgValue.equals("操作成功")){
                    JSONArray objectLpIds=jsonObject.getJSONArray("data");
                    List<String> byNames = new ArrayList<String>();
                    for (int j=0;j<objectLpIds.size();j++){
                        String byname=objectLpIds.getJSONObject(j).getString("byname");
                        byNames.add(byname);
                        System.out.println("小区：================="+byname);
                    }
                    String expectByName="西罗园一区";
                    if (byNames.contains(expectByName)){
                        System.out.println("找到"+expectByName);
                        Reporter.log("找到"+expectByName);
                        Assert.assertTrue(true);
                    }else {
                        System.out.println("没有找到"+expectByName);
                        Reporter.log("没有找到"+expectByName);
                        Assert.assertTrue(false);
                    }
                }
                else {
                    Reporter.log("返回失败！");
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





























