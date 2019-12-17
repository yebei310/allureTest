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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.fc.util.TestUtil.dtt;

//小区列表（wap pc）公用
public class CommunityList extends TestBase {
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
        testCaseExecel = System.getProperty("user.dir")+prop.getProperty("CommunityList");
    }
    @DataProvider(name = "postData")
    public Object[][] post() throws IOException{
        return  dtt(testCaseExecel,0);
    }
    @Test(dataProvider = "postData",description = "小区列表（wap pc）公用")
    public void filterCon(String contenx)throws Exception{

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost("https://www.izhiliao.com/community/api/loupan/list?type=1&pageIndex=1&siteId=1");
        post.setConfig(requestConfig);
        //设置头信息
        post.setHeader("Content-Type","application/x-www-form-urlencoded");
        post.setHeader("Cookie","sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2216c75704d3f3d0-0991cdae8aeea9-3a614f0b-2073600-16c75704d409e4%22%2C%22%24device_id%22%3A%2216c75704d3f3d0-0991cdae8aeea9-3a614f0b-2073600-16c75704d409e4%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%7D%7D; ifh_agent_msid=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE1NjkzMTUwNTQsImlzcyI6ImF1dGgwIiwibW9iaWxlIjoiMTM3MTgyNDI0NTMiLCJleHAiOjE1Njk1NzQyNTR9.wBAuPXGfw7rEvJuMfFsnVDXA0E-18VnAdvvEHDf6vnE; Hm_lvt_8ddeae4dc7e58e29c04151fd536bd57a=1569477603; izl_site=3066_bj; IFH_CLUSTER_SID=C0ABA12138D641F0A5F67432CC4A941E; Hm_lvt_becf67d756bfea5219f687e0ce01da80=1569482510; Hm_lpvt_becf67d756bfea5219f687e0ce01da80=1569554178; verifyCode=FC9B752D0D2755CEF7EFC0EA638A49BE; JSESSIONID=B76DA3E811DB8680F3EFA6464D28CD24; Hm_lpvt_8ddeae4dc7e58e29c04151fd536bd57a=1569567535; ifh_agent_siteid=27504; ck_login_id=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJJRkgiLCJtb2JpbGUiOiIxMzcxODI0MjQ1MyIsImV4cCI6MTU2OTY1NjA2M30.EjlXwK5j0MCD-HqJmYMH6wptuziLbosXNBG81UDozMc; Hm_lvt_4e508e074db803e7cfe17dc4c535f739=1569569162,1569569328,1569569549,1569569664; Hm_lvt_72f9fd87c51a333ceca5010d5c825a1d=1569569162,1569569328,1569569549,1569569664; Hm_lpvt_4e508e074db803e7cfe17dc4c535f739=1569569675; Hm_lpvt_72f9fd87c51a333ceca5010d5c825a1d=1569569675");
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
                   JSONObject jsonObjectData=  jsonObject.getJSONObject("data");
//                    JSONObject jsonObjectData1=  jsonObjectData.getJSONObject("data");
                    JSONArray jsonArrayIpName = jsonObjectData.getJSONArray("data");
                    Reporter.log("jsonArrayIpName:"+jsonArrayIpName);
                    List<String> ipNames = new ArrayList<String>();
                    for (int i =0;i<jsonArrayIpName.size();i++){
                        String ipName =  jsonArrayIpName.getJSONObject(i).getString("byname");
                        ipNames.add(ipName);
                        System.out.println("二手房小区列表：================="+ipName);
                    }
                    System.out.println("打印小区："+ipNames);
                    String expectSite="京溪小区";
                    if (ipNames.contains(expectSite)){
                        System.out.println("找到"+expectSite);
                        Reporter.log("找到"+expectSite);
                        Assert.assertTrue(true);

                    }else {
                        System.out.println("没有找到"+expectSite);
                        Reporter.log("没有找到"+expectSite);
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





























