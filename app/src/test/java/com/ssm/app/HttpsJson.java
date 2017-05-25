package com.ssm.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class HttpsJson {

    /**
     * 发送https请求共用体
     */
    public static JSONObject sendPost(String url, String params, String method) throws IOException,
            KeyManagementException, NoSuchAlgorithmException,
            NoSuchProviderException {
        // 请求结果
        JSONObject json = new JSONObject();
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        URL realUrl;
        HttpsURLConnection conn;
        // 请求参数获取
        // 字符串请求参数
        if (StringUtils.isNotBlank(params)) {
            url = url + "?" +params;
        }
        // 查询地址
        String queryString = url;
        // map格式的请求参数
        SSLSocketFactory ssf = BZX509TrustManager.getSSFactory();
        try {
            realUrl = new URL(queryString);
            conn = (HttpsURLConnection) realUrl.openConnection();
            conn.setSSLSocketFactory(ssf);
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            if ("get".equals(method)) {
                conn.connect();
            } else {
                 // 获取URLConnection对象对应的输出流
                out = new PrintWriter(conn.getOutputStream());
                // 发送请求参数
                out.print(params);
                // flush输出流的缓冲
                out.flush();
            }
            in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            json = JSONObject.fromObject(result);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return json;
    }

    public static void main(String[] args) {
        try {
            JSONObject json = new JSONObject();
            json = HttpsJson.sendPost("https://kyfw.12306.cn/otn/leftTicket/query?leftTicketDTO.train_date=2017-04-13&leftTicketDTO.from_station=SHH&leftTicketDTO.to_station=HZH&purpose_codes=ADULT", null, "get");
            JSONObject shtojhjson = new JSONObject();
            shtojhjson = HttpsJson.sendPost("https://kyfw.12306.cn/otn/leftTicket/query?leftTicketDTO.train_date=2017-04-13&leftTicketDTO.from_station=SHH&leftTicketDTO.to_station=RNH&purpose_codes=ADULT", null, "get");
            JSONArray jharray = (JSONArray) shtojhjson.get("data");
            JSONArray arry =  (JSONArray) json.get("data");
            Iterator<Object> it = arry.iterator();
            while (it.hasNext()) {
            	JSONObject obhz = (JSONObject) it.next();
            	JSONObject obhzData = obhz.getJSONObject("queryLeftNewDTO");
            	String station_train_codehz = obhzData.get("station_train_code").toString();
            	Iterator<Object> it2 = jharray.iterator();
            	while (it2.hasNext()) {
					JSONObject objh = (JSONObject) it2.next();
					JSONObject objhData = objh.getJSONObject("queryLeftNewDTO");
					String station_train_codejh = objhData.get("station_train_code").toString();
					//System.out.println(station_train_codejh);  
					if (station_train_codejh.equals(station_train_codehz)) {
						System.out.println("code:" + station_train_codehz + " " + obhzData.getString("from_station_name") + " " + obhzData.getString("start_time"));
					}
            	}
            }
        } catch (KeyManagementException | NoSuchAlgorithmException
                | NoSuchProviderException | IOException e) {
            e.printStackTrace();
        }
    }

}