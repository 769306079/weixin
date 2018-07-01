package com.winfo.util;

/****
 *author:lizhichao
 *date:2018-04-08 23:33
 *description
 **/

import org.apache.http.HttpStatus;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

import javax.net.ssl.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jane on 2015/9/10.
 */
public class JsoupHttpRequest {

    public static void main(String[] args) throws Exception {

        String token = WeChatApiUtil.getToken("wx2916c1c8e06cf752", "9426fafc350bde03d251925f228ff888");
        String url = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=" + token + "&type=image";
        File file = new File("C:\\Users\\76930\\Desktop\\CBE7D43DE4EDCDF6B56F310365F7E1C7.jpg");
        String fileRquestParam = "media";
        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("access_token", token);
        dataMap.put("type", "image");
        Response response = doPostFileRequest(url, dataMap, file, fileRquestParam);


        System.out.println(response.statusMessage() + "\n" + response.parse().text());
    }

    /**
     * @param url              请求的Url
     * @param paramMap         参数
     * @param file             文件
     * @param fileRequestParam form表单对应的文件name属性名
     * @return
     * @throws Exception
     */
    public static Response doPostFileRequest(String url, Map<String, String> paramMap, File file, String fileRequestParam) throws Exception {
       /* if (StringUtils.isBlank(url)) {
            throw new Exception("The request URL is blank.");
        }
        // Https请求
        if (StringUtils.startsWith(url, "https")) {
            trustEveryone();
        }*/
        trustEveryone();
        Connection connection = Jsoup.connect(url);
        connection.method(Connection.Method.POST);
        connection.timeout(12000);
        connection.header("Content-Type", "multipart/form-data");
        connection.header("Cache-Control", "no-cache");
        connection.header("Connection", "Keep-Alive");
        connection.ignoreHttpErrors(true);
        connection.ignoreContentType(true);
        if (paramMap != null && !paramMap.isEmpty()) {
            connection.data(paramMap);
        }
        try {
            FileInputStream fis = new FileInputStream(file);
            connection.data(fileRequestParam, file.getName(), fis, "image");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Response response = connection.execute();

            if (response.statusCode() != HttpStatus.SC_OK) {
                throw new Exception("http请求响应码:" + response.statusCode() + "");
            }
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解决Https请求,返回404错误
     */
    private static void trustEveryone() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {

                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[]{new X509TrustManager() {

                public void checkClientTrusted(X509Certificate[] chain, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}