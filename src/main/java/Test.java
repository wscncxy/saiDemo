import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

public class Test {

    private static final String ALG_RSA = "RSA";
    private static final String ALG_DSA = "DSA";

    public static void main(String[] args) {
        new Test().sendTest();
    }

    public void sendTest() {
        Map<String, Object> param = new HashMap<>();
        param.put("partnerNo", "JDZAD");
        param.put("imeiNo", "WW3A60FB-3103-4090-8723-7E101DA1EC03");
        param.put("channelCode", "za");
        param.put("apiVersion", "1.0.0");
        param.put("reqNo", "202005232115255ec921ed994da");
        param.put("reqDate", "2020-05-23 21:15:25");

        Map<String, Object> context = new HashMap<>();
        context.put("id", "adChannelAPI.duplicate");
        context.put("param", param);

        String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAOeHYpOOxQZBm8h1+Q5NPX94ZejGBvGG2NZooXOGrcWyEHOcEvDUeX9WDcYcFyWazw6QnLeKtNfgelE+ChumGIkCAwEAAQ==";
        String biz= JSONObject.toJSONString(context);
        System.out.println("biz:"+biz);
        String bizParam = this.encrypt(biz, publicKey);


        Map<String, Object> request = new HashMap<>();
        request.put("apiKey", "ad.device.duplicate");
        request.put("bizParam", bizParam);
        System.out.println("request:"+JSONObject.toJSONString(request));

        String jsonObject = postByJson("https://finance-gateway.diandian.com.cn/fcpGateway", request);
        System.out.println(jsonObject);

    }

    public String encrypt(String context, String publicKey) {
        String encryptedContext = context;
        try {
            if (StringUtils.isEmpty(context)) {
                return encryptedContext;
            }

            encryptedContext = RSAEncrypt.encrypt(context, publicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedContext;
    }


    protected String  postByJson(String serverUrl, Object param) {
        String jsonStr = null;
        if (param != null) {
            jsonStr = JSON.toJSONString(param);
        }
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(serverUrl);
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        StringEntity entity = new StringEntity(jsonStr, "UTF-8");
        httpPost.setEntity(entity);
        String resp=null;
        CloseableHttpResponse response = null;
        try{
            response = httpClient.execute(httpPost);
            resp= EntityUtils.toString(response.getEntity());
        }catch (Exception e){
            e.printStackTrace();
        }
        return resp;
    }
}