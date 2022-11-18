package com.clarity.apiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.clarity.apiclientsdk.model.User;
import com.clarity.apiclientsdk.utils.SignUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用 Hutool 工具类，测试接口
 *
 * @author: scott
 * @date: 2022年11月10日 15:17
 */
public class ClarityClient {

    // ak
    private String accessKey;

    // sk
    private String secretKey;

    public ClarityClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getNameByGet(String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", "Clarity");
        String result= HttpUtil.get("http://localhost:8082/api/name/", paramMap);
        System.out.println(result);
        return result;
    }

    public String getNameByPost(String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", "Clarity");
        String result= HttpUtil.post("http://localhost:8082/api/name/", paramMap);
        System.out.println(result);
        return result;
    }

    private Map<String, String> getHeaderMap(String body) {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("accessKey", accessKey);
        // 一定不能直接发送
        // hashMap.put("secretKey", secretKey);
        // 用户参数
        hashMap.put("body", body);
        // 随机数
        hashMap.put("nonce", RandomUtil.randomNumbers(4));
        // 时间戳
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        // 签名
        hashMap.put("sign", SignUtils.getSign(body, secretKey));
        return hashMap;
    }

    public String getUsernameByPost(User user) {
        String json = JSONUtil.toJsonStr(user);
        HttpResponse httpResponse = HttpRequest.post("http://localhost:8082/api/name/user")
                .addHeaders(getHeaderMap(json))
                .body(json)
                .execute();
        System.out.println(httpResponse.getStatus());
        String result = httpResponse.body();
        System.out.println(result);
        return result;
    }
}
