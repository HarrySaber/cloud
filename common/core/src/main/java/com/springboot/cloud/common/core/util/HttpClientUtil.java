package com.springboot.cloud.common.core.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * HttpClientUtil
 *
 * @author D.jin
 * @date 2019/9/11
 */
@Slf4j
public class HttpClientUtil {

	public static String doPost(String url, String json) {
		return doPost(url, json, null);
	}

	public static String doPost(String url, String json, Map<String, String> header) {
		CloseableHttpClient httpclient = null;
		String resData = null;
		CloseableHttpResponse response = null;

		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(240000) //
				.setConnectTimeout(240000).setConnectionRequestTimeout(240000).build();

		try {
			httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(requestConfig);
			if (!StringUtils.isEmpty(json)) {
				// ByteArrayEntity entity = new ByteArrayEntity(json.getBytes(),
				// ContentType.APPLICATION_JSON);
				StringEntity entity = new StringEntity(json, "UTF-8");
				entity.setContentType("application/json");

				httpPost.setEntity(entity);
			}
			if (header != null) {
				for (String key : header.keySet()) {
					httpPost.setHeader(key, header.get(key));
				}
			}
			response = httpclient.execute(httpPost);
			int status = response.getStatusLine().getStatusCode();
			if (status == 200) {
				resData = EntityUtils.toString(response.getEntity(), "UTF-8");
			} else {
				log.error("http post error status [ " + status + "]");
			}
		} catch (Exception ex) {
			log.error("http post Exception.", ex);
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				if (httpclient != null) {
					httpclient.close();
				}
			} catch (IOException e) {
				log.error("http post IOException.", e);
			}
		}
		return resData;
	}

	/**
	 * 通过xml 请求数据
	 * 
	 * @param url
	 * @param xmlData
	 * @param header
	 * @return
	 */
	public static String doPostXml(String url, String xmlData, Map<String, String> header) {
		CloseableHttpClient httpclient = null;
		String resData = null;
		CloseableHttpResponse response = null;

		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(240000) //
				.setConnectTimeout(240000).setConnectionRequestTimeout(240000).build();

		try {
			httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(requestConfig);
			if (!StringUtils.isEmpty(xmlData)) {
				// ByteArrayEntity entity = new ByteArrayEntity(json.getBytes(),
				// ContentType.APPLICATION_JSON);
				StringEntity entity = new StringEntity(xmlData, "UTF-8");
				entity.setContentType("application/xml");

				httpPost.setEntity(entity);
			}
			if (header != null) {
				for (String key : header.keySet()) {
					httpPost.setHeader(key, header.get(key));
				}
			}
			response = httpclient.execute(httpPost);
			int status = response.getStatusLine().getStatusCode();
			if (status == 200) {
				resData = EntityUtils.toString(response.getEntity(), "UTF-8");
			} else {
				log.error("http post error status [ " + status + "]");
			}
		} catch (Exception ex) {
			log.error("http post Exception.", ex);
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				if (httpclient != null) {
					httpclient.close();
				}
			} catch (IOException e) {
				log.error("http post IOException.", e);
			}
		}
		return resData;
	}

	public static String doGet(String url,Map<String, String> header) {
		// long starttime = System.currentTimeMillis();
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String resData = null;
		CloseableHttpResponse response = null;

		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(180000) //
				.setConnectTimeout(180000).setConnectionRequestTimeout(180000).build();

		try {
			HttpGet httpGet = new HttpGet(url);
			httpGet.setConfig(requestConfig);
			if (header != null) {
				for (String key : header.keySet()) {
					httpGet.setHeader(key, header.get(key));
				}
			}
			response = httpclient.execute(httpGet);
			int status = response.getStatusLine().getStatusCode();
			if (status == 200) {
				resData = EntityUtils.toString(response.getEntity());
			} else {
				log.error("http doGet error status [ " + status + "]");
			}
		} catch (Exception ex) {
			log.error("http doGet Exception ", ex);
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				if (httpclient != null) {
					httpclient.close();
				}
			} catch (IOException e) {
				log.error("http doGet IOException ", e);
			}
		}
		return resData;
	}

	public static byte[] doGetForByte(String url) {
		// long starttime = System.currentTimeMillis();
		CloseableHttpClient httpclient = HttpClients.createDefault();
		byte[] resData = null;
		CloseableHttpResponse response = null;
		try {
			HttpGet httpGet = new HttpGet(url);
			response = httpclient.execute(httpGet);
			int status = response.getStatusLine().getStatusCode();
			if (status == 200) {
				resData = EntityUtils.toByteArray(response.getEntity());
				// resData = EntityUtils.toString(response.getEntity());
			} else {
				log.error("http doGet error status [ " + status + "]");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error("http doGet Exception [ " + ex + "]");
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				if (httpclient != null) {
					httpclient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				log.error("http doGet IOException [ " + e + "]");
			}
		}
		// System.err.println("pot,url:" + url + ",time:"
		// + (System.currentTimeMillis() - starttime));
		return resData;
	}

	public static String doPut(String url, String json) {
		CloseableHttpClient httpclient = null;
		String resData = null;
		CloseableHttpResponse response = null;
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(180000) //
				.setConnectTimeout(180000).setConnectionRequestTimeout(180000).build();
		try {
			httpclient = HttpClients.createDefault();
			HttpPut httpPut = new HttpPut(url);
			httpPut.setConfig(requestConfig);
			if (!StringUtils.isEmpty(json)) {
				StringEntity entity = new StringEntity(json, "utf-8");
				entity.setContentType("application/json");
				httpPut.setEntity(entity);
			}
			response = httpclient.execute(httpPut);
			int status = response.getStatusLine().getStatusCode();
			if (status == 200) {
				resData = EntityUtils.toString(response.getEntity());
			} else {
				log.error("http put error status [ " + status + "]");
			}
		} catch (Exception ex) {
			log.error("http put Exception.", ex);
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				if (httpclient != null) {
					httpclient.close();
				}
			} catch (IOException e) {
				log.error("http put IOException.", e);
			}
		}
		return resData;
	}

	public static String doPostForm(String url, Map<String, Object> paramMap) {
		CloseableHttpClient httpclient = null;
		String resData = null;
		CloseableHttpResponse response = null;

		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(240000) //
				.setConnectTimeout(240000).setConnectionRequestTimeout(240000).build();
		try {
			httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(requestConfig);
			httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");

			if (null != paramMap && paramMap.size() > 0) {
				List<NameValuePair> nvps = new ArrayList<NameValuePair>();
				// 通过map集成entrySet方法获取entity
				/*
				 * Set<Map.Entry<String, Object>> entrySet = paramMap.entrySet(); // 循环遍历，获取迭代器
				 * Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator(); while
				 * (iterator.hasNext()) { Map.Entry<String, Object> mapEntry = iterator.next();
				 * nvps.add(new BasicNameValuePair(mapEntry.getKey(),
				 * mapEntry.getValue().toString())); }
				 */
				paramMap.forEach((k, v) -> {
					nvps.add(new BasicNameValuePair(k, v.toString()));
				});
				// 为httpPost设置封装好的请求参数
				httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			}
			response = httpclient.execute(httpPost);
			int status = response.getStatusLine().getStatusCode();
			if (status == 200) {
				resData = EntityUtils.toString(response.getEntity(), "UTF-8");
			} else {
				log.error("http post error status [ " + status + "]");
			}
		} catch (Exception ex) {
			log.error("http post Exception.", ex);
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				if (httpclient != null) {
					httpclient.close();
				}
			} catch (IOException e) {
				log.error("http post IOException.", e);
			}
		}
		return resData;
	}
}
