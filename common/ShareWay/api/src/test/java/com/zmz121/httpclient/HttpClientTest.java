package com.zmz121.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

public class HttpClientTest
{
    @Test
    public void test01() throws IOException
    {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://www.csdn.net");
        CloseableHttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String s = EntityUtils.toString(entity, "UTF-8");
        System.out.println(s);
        response.close();
        client.close();
    }
}
