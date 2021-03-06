package com.fc.newHouse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.fc.util.TestUtil.dtt;

//1 获取所有站点楼盘信息
public class GetSiteList extends TestBase {

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
        testCaseExecel = System.getProperty("user.dir")+prop.getProperty("GetSiteList");
    }
    @DataProvider(name = "postData")
    public Object[][] post() throws IOException{
        return  dtt(testCaseExecel,0);
    }
    @Test(dataProvider = "postData" ,description = "获取所有站点楼盘信息")
    public void filterCon(String contenx)throws Exception{

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost("https://dict.izhiliao.com/common/getSiteList");
        post.setConfig(requestConfig);
        //设置头信息
        // 测试环境cookie
//        String cookies ="secondCityArr=[{\"tableName\":null,\"id\":3066,\"name\":\"å\u008C\u0097äº¬\",\"logo\":null,\"domain\":\"bj\",\"isProxy\":1,\"letter\":\"B\",\"isHot\":0,\"url\":\"https://bj.izhiliao.com/\",\"status\":1,\"abroad\":0,\"sort\":1,\"createTime\":1532921163},{\"tableName\":null,\"id\":36688,\"name\":\"æ·±å\u009C³\",\"logo\":null,\"domain\":\"sz\",\"isProxy\":0,\"letter\":\"S\",\"isHot\":0,\"url\":\"https://m.izhiliao.com/sz\",\"status\":1,\"abroad\":0,\"sort\":4,\"createTime\":1532921163}]; Hm_lvt_becf67d756bfea5219f687e0ce01da80=1571814467; IFH_CLUSTER_SID=45320113A0064A15A55E27699B470338; izl_site=3066_bj; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2216dbed44a7115a-07077dfe5e9183-3a614f0b-2073600-16dbed44a72546%22%2C%22%24device_id%22%3A%2216dbed44a7115a-07077dfe5e9183-3a614f0b-2073600-16dbed44a72546%22%2C%22props%22%3A%7B%22%24latest_referrer%22%3A%22https%3A%2F%2Fhouse.ifeng.com%2F%22%2C%22%24latest_referrer_host%22%3A%22house.ifeng.com%22%2C%22%24latest_traffic_source_type%22%3A%22%E5%BC%95%E8%8D%90%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC%22%7D%7D; Hm_lvt_8ddeae4dc7e58e29c04151fd536bd57a=1571810512,1572312444; Hm_lpvt_becf67d756bfea5219f687e0ce01da80=1572315111; firstLogin=false; nickName=\\u69\\u66\\u65\\u6e\\u67\\u5f\\u32\\u34\\u35\\u33; izl_sid=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJJRkgiLCJleHAiOjE1NzI4NjUzMzMsImluZm8iOiIxMzcxODI0MjQ1MyxodHRwOi8vczAuaWZlbmdpbWcuY29tLzIwMTcvMDkvMDYvbWFsZV8wZDhjNTI4YS5wbmcsaWZlbmdfdWNfMTczODAwNjgsaXVfMTczODAwNjgsaWZlbmdfMjQ1MyJ9.HAXLVRoe095D9VPxU3vmSoVTKv77CdtKwij1CtgV3pg; aaaaaaaaaaa=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE1NzI4NzIzMzUsImlzcyI6ImF1dGgwIiwibW9iaWxlIjoiMTM3MTgyNDI0NTMiLCJleHAiOjE1NzMxMzE1MzV9.M2vpPjbGdu6Tqtdx4MKVA2U-hUYtflIKkd0wT_rUQeI; 300_house_basic_list_url=/house/basic/list; 300_community_basic_list_url=/community/basic/list; 95_house_basic_list_url=\"/house/basic/list?propertyCategory=&locationId=2&areaId=177&name=&id=&showType=&grade=&saleStatus=&sTime=&eTime=&adminName=\"";
        // 线上cookies
//        String cookies ="IFH_CLUSTER_SID=45320113A0064A15A55E27699B470338; firstLogin=false; nickName=\\u69\\u66\\u65\\u6e\\u67\\u5f\\u32\\u34\\u35\\u33; 300_community_basic_list_url=/community/basic/list; Hm_lvt_becf67d756bfea5219f687e0ce01da80=1571814467,1572967097; izl_plus_manager_sid=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE1NzQxMjk0NzksImlzcyI6ImF1dGgwIiwibW9iaWxlIjoiMTM3MTgyNDI0NTMiLCJleHAiOjE1NzQzODg2Nzl9.XWYTSAdvKQPlWqY2__OvCzwuBEtp4uJTXJrqXKk4wOM; Hm_lvt_8ddeae4dc7e58e29c04151fd536bd57a=1572967066,1573526595,1574667951; Hm_lpvt_8ddeae4dc7e58e29c04151fd536bd57a=1574667951; izl_site=3066_bj; sajssdk_2015_cross_new_user=1; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2216ea5c43e848a-08e5daf1447152-3a614f0b-2073600-16ea5c43e85ac7%22%2C%22%24device_id%22%3A%2216ea5c43e848a-08e5daf1447152-3a614f0b-2073600-16ea5c43e85ac7%22%2C%22props%22%3A%7B%22%24latest_referrer%22%3A%22https%3A%2F%2Fhouse.ifeng.com%2F%22%2C%22%24latest_referrer_host%22%3A%22house.ifeng.com%22%2C%22%24latest_traffic_source_type%22%3A%22%E5%BC%95%E8%8D%90%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC%22%7D%7D; aaaaaaaaaaa=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE1NzQ3NTA1MjEsImlzcyI6ImF1dGgwIiwibW9iaWxlIjoiMTM3MTgyNDI0NTMiLCJleHAiOjE1NzUwMDk3MjF9.wxgb_cnhZbps3jrHjV4B_WCAHH7bME-Zuq0UYpr1-3U; 300_house_basic_list_url=/house/basic/list";
        String cookies ="izl_plus_manager_sid=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE1NzY4MzYxMDYsImlzcyI6ImF1dGgwIiwibW9iaWxlIjoiMTM3MTgyNDI0NTMiLCJleHAiOjE1NzcwOTUzMDZ9.9kgDJI05z9V-oVw6xxc4CK-vjv4fD13uQctxERVFYYs; verifyCode=2B80D403D894BED44F2D236EAD90F936; ck_login_id=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJJRkgiLCJtb2JpbGUiOiIxMzcxODI0MjQ1MyIsImV4cCI6MTU3ODI3NjkyNH0.xxk1LrzNrWSoWzZ1ke6Uu2zfADmguVxqfEejC89Yc4U; coupon_menu_status=0; firstLogin=false; izl_sid=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJJRkgiLCJleHAiOjE1NzczMjY1NDEsImluZm8iOiIxMzcxODI0MjQ1MyxodHRwOi8vczAuaWZlbmdpbWcuY29tLzIwMTcvMDkvMDYvbWFsZV8wZDhjNTI4YS5wbmcsaWZlbmdfdWNfMTczODAwNjgsaXVfMTczODAwNjgsaWZlbmdfMjQ1MyJ9.tVkMwrfwmgUovXb6yFcKhcQzz3QDMTUTsEV2g-_TbxA; nickName=\\u69\\u66\\u65\\u6e\\u67\\u5f\\u32\\u34\\u35\\u33; ifh_agent_msid=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE1NzcwNjczNDcsImlzcyI6ImF1dGgwIiwibW9iaWxlIjoiMTM3MTgyNDI0NTMiLCJleHAiOjE1NzczMjY1NDd9.DDugRG_DqnczgYrntj-MdAXDfoqG_tskIr9QcK8mw1Y; ifh_agent_siteid=3066; Hm_lvt_8ddeae4dc7e58e29c04151fd536bd57a=1575359526,1576755086,1577067362; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2216f1dece74f13e-0c2b49d9a5375a-3a614f0b-2073600-16f1dece751bd0%22%2C%22%24device_id%22%3A%2216f1dece74f13e-0c2b49d9a5375a-3a614f0b-2073600-16f1dece751bd0%22%2C%22props%22%3A%7B%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%7D%7D; IFH_CLUSTER_SID=0A7BD443675F4032A9D28C74907F1B85; izl_site=3066_bj; Hm_lpvt_8ddeae4dc7e58e29c04151fd536bd57a=1577775918; prov=cn010; city=010; weather_city=bj; region_ip=114.253.10.118; region_ver=1.30; aaaaaaaaaaa=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE1Nzc3NzkyNzIsImlzcyI6ImF1dGgwIiwibW9iaWxlIjoiMTM3MTgyNDI0NTMiLCJleHAiOjE1NzgwMzg0NzJ9.RuDBJkIzcH-wUGwpd80-oHbONiyInHePbgHm4lPQnfw";
        post.setHeader("Content-Type","application/x-www-form-urlencoded");
        post.setHeader("Cookie",cookies);

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
                    JSONArray jsonArraySites = jsonObject.getJSONArray("data");
                    Reporter.log("返回jsonArraySites:"+jsonArraySites);

                    Reporter.log("返回jsonArraySites:"+jsonArraySites);
                    ArrayList<String> sites = new ArrayList<String>();
                    for (int i =0;i<jsonArraySites.size();i++){
                       String site =  jsonArraySites.getJSONObject(i).getString("name");
                        sites.add(site);
                        System.out.println("站点：================="+site);
                    }
                    String expectSite="长沙";
                    if (sites.contains(expectSite)){
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





























