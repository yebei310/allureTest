package com.fc.headNet;

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

//房源卡片
public class RoomCard extends TestBase {

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
        testCaseExecel = System.getProperty("user.dir")+prop.getProperty("RoomCard");
    }
    @DataProvider(name = "postData")
    public Object[][] post() throws IOException{
        return  dtt(testCaseExecel,0);
    }
    @Test(dataProvider = "postData" ,description = "总网：房源卡片")
    public void filterCon(String contenx)throws Exception{

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost("https://www.izhiliao.com/esf/api/roomCard");
        post.setConfig(requestConfig);
        //设置头信息
//        String cookies ="secondCityArr=[{\"tableName\":null,\"id\":3066,\"name\":\"å\u008C\u0097äº¬\",\"logo\":null,\"domain\":\"bj\",\"isProxy\":1,\"letter\":\"B\",\"isHot\":0,\"url\":\"https://bj.izhiliao.com/\",\"status\":1,\"abroad\":0,\"sort\":1,\"createTime\":1532921163},{\"tableName\":null,\"id\":36688,\"name\":\"æ·±å\u009C³\",\"logo\":null,\"domain\":\"sz\",\"isProxy\":0,\"letter\":\"S\",\"isHot\":0,\"url\":\"https://m.izhiliao.com/sz\",\"status\":1,\"abroad\":0,\"sort\":4,\"createTime\":1532921163}]; Hm_lvt_becf67d756bfea5219f687e0ce01da80=1571814467; IFH_CLUSTER_SID=45320113A0064A15A55E27699B470338; izl_site=3066_bj; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2216dbed44a7115a-07077dfe5e9183-3a614f0b-2073600-16dbed44a72546%22%2C%22%24device_id%22%3A%2216dbed44a7115a-07077dfe5e9183-3a614f0b-2073600-16dbed44a72546%22%2C%22props%22%3A%7B%22%24latest_referrer%22%3A%22https%3A%2F%2Fhouse.ifeng.com%2F%22%2C%22%24latest_referrer_host%22%3A%22house.ifeng.com%22%2C%22%24latest_traffic_source_type%22%3A%22%E5%BC%95%E8%8D%90%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC%22%7D%7D; Hm_lvt_8ddeae4dc7e58e29c04151fd536bd57a=1571810512,1572312444; Hm_lpvt_becf67d756bfea5219f687e0ce01da80=1572315111; firstLogin=false; nickName=\\u69\\u66\\u65\\u6e\\u67\\u5f\\u32\\u34\\u35\\u33; izl_sid=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJJRkgiLCJleHAiOjE1NzI4NjUzMzMsImluZm8iOiIxMzcxODI0MjQ1MyxodHRwOi8vczAuaWZlbmdpbWcuY29tLzIwMTcvMDkvMDYvbWFsZV8wZDhjNTI4YS5wbmcsaWZlbmdfdWNfMTczODAwNjgsaXVfMTczODAwNjgsaWZlbmdfMjQ1MyJ9.HAXLVRoe095D9VPxU3vmSoVTKv77CdtKwij1CtgV3pg; aaaaaaaaaaa=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE1NzI4NzIzMzUsImlzcyI6ImF1dGgwIiwibW9iaWxlIjoiMTM3MTgyNDI0NTMiLCJleHAiOjE1NzMxMzE1MzV9.M2vpPjbGdu6Tqtdx4MKVA2U-hUYtflIKkd0wT_rUQeI; 300_house_basic_list_url=/house/basic/list; 300_community_basic_list_url=/community/basic/list; 95_house_basic_list_url=\"/house/basic/list?propertyCategory=&locationId=2&areaId=177&name=&id=&showType=&grade=&saleStatus=&sTime=&eTime=&adminName=\"";
        post.setHeader("Content-Type","application/json");
//        post.setHeader("Cookie",cookies);
       Map<String,String> map = new HashMap<String,String>();
        map.put("roomId","19");
        StringEntity s = new StringEntity(JSON.toJSONString(map));
        s.setContentEncoding("UTF-8");
        post.setEntity(s);

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
                    JSONObject jsonpObject1 = jsonObject.getJSONObject("data");
                    String totalPriceUnit= jsonpObject1.getString("totalPriceUnit");
                    String spectName="万";
                    if (totalPriceUnit.contains(spectName)){
                        System.out.println("总网房源卡片：获取到楼盘总价单位"+spectName);
                        Assert.assertTrue(true);
                    }else {
                        System.out.println("没有找到对应楼盘信息");
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





























