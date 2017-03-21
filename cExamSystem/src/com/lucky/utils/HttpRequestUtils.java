/*
*Created by liulei on 2016/5/14.
*/
package com.lucky.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * Created by liulei on 2016/5/14.
 */
public class HttpRequestUtils {

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param urlNameString
     *            发送请求的URL
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String urlNameString) {
        String result = "";
        BufferedReader in = null;
        try {
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("contentType", "UTF-8");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += new String(line.getBytes("GBK"),"UTF-8");
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

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.addRequestProperty("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(),"utf-8"));
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(),"utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }


    /**
     * 发送请求，带参数
     * @param url
     * @param requestType
     * @param params
     */
    public static void sendHttpRequest(String url,String requestType,String params){

        StringBuffer bufferRes = new StringBuffer();
        try {
            URL realUrl = new URL(url);

            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();

            // 连接超时
            conn.setConnectTimeout(25000);
            // 读取超时 --服务器响应比较慢,增大时间
            conn.setReadTimeout(25000);
            HttpURLConnection.setFollowRedirects(true);
            // 请求方式
            conn.setRequestMethod(requestType);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:21.0) Gecko/20100101 Firefox/21.0");
            conn.setRequestProperty("Referer", "https://api.weixin.qq.com/");
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setRequestProperty("contentType", "UTF-8");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.connect();

            // 获取URLConnection对象对应的输出流
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            // 发送请求参数
            out.write(URLEncoder.encode(params,"UTF-8"));
            //out.write(params);
            out.flush();
            out.close();
            InputStream in = conn.getInputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(in,"UTF-8"));
            String valueString = null;
            while ((valueString=read.readLine())!=null){
                bufferRes.append(valueString);
            }
            System.out.println(bufferRes.toString());
            in.close();
            if (conn != null) {
                // 关闭连接
                conn.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
