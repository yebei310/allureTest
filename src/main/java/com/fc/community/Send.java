package com.fc.community;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fc.base.TestBase;
import com.fc.restclient.RestClient;
import com.fc.util.TestUtil;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
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
import java.util.Map;

import static com.fc.util.TestUtil.dtt;

//13 收藏与取消收藏1收藏 2 取消收藏
public class Send extends TestBase {
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
        HttpPost post = new HttpPost("http://172.17.3.51:8083/login/quick?mobile=13718242412&code=329137");
        post.setConfig(requestConfig);
        //设置头信息
        post.setHeader("Content-Type","application/x-www-form-urlencoded");
//        post.setHeader("Cookie","mdgxsend.ashx?mobile=13718242412&sn=SDK-BBX-010-24372&pwd=413032953A96039A8199C86946A7B673&content=%E3%80%90%E7%9F%A5%E4%BA%86%E6%89%BE%E6%88%BF%E3%80%91485601%EF%BC%88%E5%87%A4%E5%87%B0%E7%BD%91%E6%88%BF%E4%BA%A7%E6%89%8B%E6%9C%BA%E9%AA%8C%E8%AF%81%E7%A0%81%EF%BC%8C%E8%AF%B7%E5%AE%8C%E6%88%90%E9%AA%8C%E8%AF%81%EF%BC%89%E5%A6%82%E9%9D%9E%E6%9C%AC%E4%BA%BA%E6%93%8D%E4%BD%9C%EF%BC%8C%E8%AF%B7%E5%BF%BD%E7%95%A5%E6%9C%AC%E7%9F%AD%E4%BF%A1%E3%80%82&rrid=1574325787496");
//        Map<String,String> map = new HashMap<String,String>();
//        map.put("mobile","13718242412");
//        map.put("mobile","13718242412");
//        StringEntity s = new StringEntity(JSON.toJSONString(map));
//        s.setContentEncoding("UTF-8");
//        post.setEntity(s);

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





























