package common.restclient;

import com.alibaba.fastjson.JSON;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RestClient {
    final static Logger Log = Logger.getLogger(RestClient.class.getClass());
    //设置请求超时时间
    RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(60000)
            .setConnectTimeout(60000)
            .setConnectionRequestTimeout(60000)
            .build();

    //get接口带header
    public CloseableHttpResponse getApi(String url , Map<String,String> map) throws IOException{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        for(Map.Entry<String,String> header: map.entrySet() ){
            get.addHeader(header.getKey(),header.getValue());
        }
        CloseableHttpResponse httpResponse = httpClient.execute(get);

        return httpResponse;
    }

    //普通get接口
    public CloseableHttpResponse getApi(String url) throws IOException{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse httpResponse = httpClient.execute(get);
        System.out.println(httpResponse);

        return httpResponse;
    }

    //post接口
    public CloseableHttpResponse postApi(String url, String entityString, HashMap<String,String> headermap) throws ClientProtocolException, IOException {

            //创建一个可关闭的HttpClient对象
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost post = new HttpPost("https://bj.izhiliao.com/broker/list");
            post.setConfig(requestConfig);
            //设置头信息
            post.setHeader("content-type", "application/json");
            post.setHeader("It-token", "sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2216c75704d3f3d0-0991cdae8aeea9-3a614f0b-2073600-16c75704d409e4%22%2C%22%24device_id%22%3A%2216c75704d3f3d0-0991cdae8aeea9-3a614f0b-2073600-16c75704d409e4%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%7D%7D; ifh_agent_msid=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE1NjkzMTUwNTQsImlzcyI6ImF1dGgwIiwibW9iaWxlIjoiMTM3MTgyNDI0NTMiLCJleHAiOjE1Njk1NzQyNTR9.wBAuPXGfw7rEvJuMfFsnVDXA0E-18VnAdvvEHDf6vnE; izl_site=3066_bj; Hm_lvt_8ddeae4dc7e58e29c04151fd536bd57a=1569319766; ifh_agent_siteid=27504; verifyCode=03A6E1B36D3E6562C1C3E319A224BF46; ck_login_id=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJJRkgiLCJtb2JpbGUiOiIxNTAxMDI2OTg5NiIsImV4cCI6MTU2OTQ2MDQzMn0.ui53J4ZSGWGasEjj9HcEo5T3Z1Wcu01Wfvledyuphlg; prov=cn010; city=010; weather_city=bj; region_ip=114.64.253.150; region_ver=1.30; Hm_lvt_becf67d756bfea5219f687e0ce01da80=1568597125,1569390212; Hm_lpvt_becf67d756bfea5219f687e0ce01da80=1569390242; IFH_CLUSTER_SID=DFE5C676C0384F5CA6C1A15E3D674816; Hm_lpvt_8ddeae4dc7e58e29c04151fd536bd57a=1569396168");
            //发送post请求
            CloseableHttpResponse httpResponse = httpclient.execute(post);

            return httpResponse;
    }
    //post接口
    public CloseableHttpResponse postApidetail(String url, String entityString, HashMap<String,String> headermap) throws ClientProtocolException, IOException {

        //创建一个可关闭的HttpClient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost post = new HttpPost("https://bj.izhiliao.com//broker/store/detail");
        post.setConfig(requestConfig);
        //设置头信息
        post.setHeader("content-type", "application/json");
        post.setHeader("It-token", "sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2216c75704d3f3d0-0991cdae8aeea9-3a614f0b-2073600-16c75704d409e4%22%2C%22%24device_id%22%3A%2216c75704d3f3d0-0991cdae8aeea9-3a614f0b-2073600-16c75704d409e4%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%7D%7D; ifh_agent_msid=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE1NjkzMTUwNTQsImlzcyI6ImF1dGgwIiwibW9iaWxlIjoiMTM3MTgyNDI0NTMiLCJleHAiOjE1Njk1NzQyNTR9.wBAuPXGfw7rEvJuMfFsnVDXA0E-18VnAdvvEHDf6vnE; verifyCode=232B813B99C072600F2A56887CDF08D4; Hm_lvt_8ddeae4dc7e58e29c04151fd536bd57a=1569477603; izl_site=3066_bj; prov=cn010; city=010; weather_city=bj; region_ip=114.64.253.150; region_ver=1.30; IFH_CLUSTER_SID=C0ABA12138D641F0A5F67432CC4A941E; Hm_lvt_becf67d756bfea5219f687e0ce01da80=1569482510; Hm_lpvt_becf67d756bfea5219f687e0ce01da80=1569482522; ifh_agent_siteid=3066; ck_login_id=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJJRkgiLCJtb2JpbGUiOiIxNTAxMDI2OTg5NiIsImV4cCI6MTU2OTU3NzA3Mn0.c4Eb2FD1mNPMMJiHWHWdJW6WrT2QyZ_F2vSTQxDkb8E; Hm_lpvt_8ddeae4dc7e58e29c04151fd536bd57a=1569496459");
        //发送post请求
        Map<String, String> map = new HashMap<String, String>();
        map.put("agentId", "3723539");
        //设置发送的数据
        StringEntity s = new StringEntity(JSON.toJSONString(map));
        s.setContentEncoding("UTF-8");
//            s.setContentType("application/json");//发送json数据需要设置contentType
        post.setEntity(s);
        CloseableHttpResponse httpResponse = httpclient.execute(post);

        return httpResponse;
    }



}
