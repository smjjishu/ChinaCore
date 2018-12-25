package com.example.project.wechat;

import com.example.project.comm.AccessToken;
import com.example.project.redis.RedisService;
import com.example.project.tool.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * 获取Accesstoken
 */
@Component
public class WeChatService {

	@Autowired
	private RedisService redisUtils;

	private String appid = "wxcf74aa25672903e7";

	private String appsecret = "ab08202500f349e292fe1b5165484004";

	// 保存到Redis里面的Key
	private String ACCESSTOKEN = "WECHAT_ACCESSTOKEN";
	
	/**
	 * 获取accessToken
	 * @param appID		微信公众号凭证
	 * @param appScret	微信公众号凭证秘钥
	 * @return AccessToken
	 */
	public  AccessToken getAccessToken(String appID, String appScret) {
		AccessToken token = new AccessToken();
		// 访问微信服务器
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appID + "&secret="
				+ appScret;
		try {
			URL getUrl = new URL(url);
			HttpURLConnection http = (HttpURLConnection) getUrl.openConnection();
			http.setRequestMethod("GET");
			http.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);

			http.connect();
			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] b = new byte[size];
			is.read(b);
			String message = new String(b, "UTF-8");
			token = GsonUtil.fromJson(message, AccessToken.class);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return token;
	}
	
	/**
	 * 获取Accesstoken
	 * @return AccessToken
	 */
	public AccessToken getAccessToken() {
		return getAccessToken(appid, appsecret);
	}

	public String getAccessTokenStr() {
		 Object oo= redisUtils.getValue(ACCESSTOKEN);
		if (oo!=null) {
			String accessTokenStr = redisUtils.getValue(ACCESSTOKEN).toString();
			return accessTokenStr;
		}
		AccessToken accessToken = getAccessToken(appid, appsecret);
		String accessTokenStr = accessToken.getAccess_token();
		redisUtils.setValue(ACCESSTOKEN, accessTokenStr, 2 * 60 * 60);
		return accessTokenStr;
	}
}
