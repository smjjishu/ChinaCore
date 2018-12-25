package com.example.project;


import com.example.project.tool.CommTool;
import com.example.project.tool.GsonUtil;
import com.example.project.tool.HttpProxy;
import com.example.project.tool.Md5Util;
import com.example.project.wechat.WeChatService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 *  第三方接口调用功能
 */
@RunWith(value=SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ChinaCoreApplication.class)
public class ThirdPartyTests {

    @Autowired
    private WeChatService weChatService;

    @Test
    public void thirdPartyTest() throws Exception  {

        String requestUrl = "http://172.0.0.40:5555/api/Tool/CompressString";
        Map<String, String> map = new HashMap<String, String>(16);
        map.put("info", "中华人民共和国");
        String result =HttpProxy.post(requestUrl,map);


        String reqUrl = "http://172.0.0.40:5555/api/Tool/DecompressString";
        Map<String, Object> map1 = new HashMap<String, Object>(16);
        String charParam10 = "{\"info\":\"" + "中华人民共和国" + "\"}";
        map.put("param", charParam10);
        String result1 = HttpProxy.sendFormPost(reqUrl, map1);

    }

    public static String jsonPost(String strURL, Map<String, String> params) {
        try {
            URL url = new URL(strURL);// 创建连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            connection.connect();
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
            out.append(GsonUtil.toJson(params));
            out.flush();
            out.close();

            int code = connection.getResponseCode();
            InputStream is = null;
            if (code == 200) {
                is = connection.getInputStream();
            } else {
                is = connection.getErrorStream();
            }

            // 读取响应
            int length = (int) connection.getContentLength();// 获取长度
            if (length != -1) {
                byte[] data = new byte[length];
                byte[] temp = new byte[512];
                int readLen = 0;
                int destPos = 0;
                while ((readLen = is.read(temp)) > 0) {
                    System.arraycopy(temp, 0, data, destPos, readLen);
                    destPos += readLen;
                }
                String result = new String(data, "UTF-8"); // utf-8编码
                return result;
            }

        } catch (IOException e) {

        }
        return "error"; // 自定义错误信息
    }


    //---------------------------------------------------------------------
    //---------------------------------------------------------------------
    //---------------------------------------------------------------------
    // todo:app接口调用
    private void Test1() throws Exception {
        long dt = new Date().getTime();
        String requestUrl = "http://localhost:8116/app/api/test/testDepart";
        String signKey = "YB_APP_API_V1";
        String appKey = "1!2@3#4$5%6^";
        String timeStamp =CommTool.dateToString(new Date(),CommTool.YYYYMMDDHHMMSS);
        String charParam = "{\"timespan\":\"" + timeStamp + "\"}";//"{}"; //
        String sign = Md5Util.getMD5(signKey + appKey + timeStamp);
        Map<String, String> map = new HashMap<String, String>(16);
        map.put("appKey", appKey);
        map.put("timestamp", timeStamp);
        map.put("sign", sign.toUpperCase());
        map.put("param", charParam);
        String result = HttpProxy.post(requestUrl, map);

    }

    // todo:长连接转短连接
    private String getSortLink(String longLink) throws Exception {
        String urlFormat = URLEncoder.encode(longLink, "UTF-8");
        String url = "http://api.t.sina.com.cn/short_url/shorten.json?source=3170051572&url_long=" + urlFormat;
        String result = "";
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    // todo:CSP接口调用
    private String postCSPInfo(String requestUrl, String charParam) throws IOException {
        //{"http://172.0.0.42:8116/", /", "YB_APP_API_V1", "1!2@3#4$5%6^"};
        String signKey = "YB_APP_API_V1";
        String appKey = "1!2@3#4$5%6^";
        String timeStamp = CommTool.dateToString(new Date(),CommTool.YYYYMMDDHHMMSS);
        String sign = Md5Util.getMD5(signKey + appKey + timeStamp);
        Map<String, String> map = new HashMap<String, String>(16);
        map.put("appKey", appKey);
        map.put("timestamp", timeStamp);
        map.put("sign", sign.toUpperCase());
        map.put("param", charParam);
        String result = HttpProxy.post(requestUrl, map);
        return result;
    }

    // todo:DCP接口调用
    private String postDCPInfo(String postUrl, String charParam) {
        String medicalReportUrl = "http://10.50.50.14:8082/api/CheckResult/PostDownCheckResultList";
        charParam = "{\"Mobilphone\":\"" + "13888888888" + "\",\"CustomerName\":\"" + "张震" + "\"}";
        String signKey = "haozhuo@2015TEST";
        String appKey = "haozhuohealth";
        long dt = new Date().getTime();
        String timeStamp = CommTool.dateToString(new Date(),CommTool.YYYYMMDDHHMMSS);
        String sign = Md5Util.getMD5(signKey + appKey + timeStamp);
        Map<String, Object> map = new HashMap<String, Object>(16);
        map.put("appKey", appKey);
        map.put("timestamp", timeStamp);
        map.put("sign", sign.toUpperCase());
        map.put("param", charParam);
        String result = HttpProxy.sendFormPost(medicalReportUrl, map);
        return result;
    }

    // todo:微信公众号接口调用
    private void weChatTest() {
        weChatService.getAccessToken("", "");
    }
}
