package com.happy.smilely.main_project.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpbitApiService {

    @Value("${upbit.public-key-path}")
    private String PUBLIC_KEY_PATH;

    @Value("${upbit.secret-key-path}")
    private String SECRET_KEY_PATH;
    
    public String getCandleData() throws Exception {
		
		String secretKey = readProfileFile("profile/" + SECRET_KEY_PATH);
		String publicKey = readProfileFile("profile/" + PUBLIC_KEY_PATH);
        String queryString = "";

        String token = generateJwtToken(publicKey, queryString, secretKey);
        //String token = "";

        // API 호출
		//String candleInterval = "30m";
        //String apiUrl = "https://api.upbit.com/v1/candles/" + candleInterval;
        List<String> queryParamsList = new ArrayList<>();
        queryParamsList.add("market=KRW-BTC");
        queryParamsList.add("count=200");
        String apiUrl = "https://api.upbit.com/v1/candles/days?" + String.join("&", queryParamsList);
        String response = sendApiRequest(apiUrl, token);
        System.out.println("API Response:\n" + response);

        return response;
    }

    private String readProfileFile(String filePath) throws IOException {
        Resource resource = new ClassPathResource(filePath);
        StringBuilder content = new StringBuilder();
    
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line.trim()).append("\n");
            }
        }
    
        return content.toString().trim();
    }

    private String generateJwtToken(String accessKey, String queryString, String secretKey) throws Exception {

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(queryString.getBytes("utf8"));

        String queryHash = String.format("%0128x", new BigInteger(1, md.digest()));

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        String jwtToken = JWT.create()
                             .withClaim("access_key", accessKey)
                             .withClaim("nonce", UUID.randomUUID().toString())
                             .withClaim("query_hash", queryHash)
                             .withClaim("query_hash_alg", "SHA512")
                             .sign(algorithm);
                             
        return jwtToken;
    }

    private String sendApiRequest(String apiUrl, String token) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(apiUrl);

        httpGet.addHeader("Authorization", "Bearer " + token);
        httpGet.setHeader("Content-Type", "application/json");

        CloseableHttpResponse response = httpClient.execute(httpGet);

        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }

        response.close();
        httpClient.close();

        return result.toString();
    }


}

