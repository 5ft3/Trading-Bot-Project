package com.example;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

import org.apache.commons.codec.digest.HmacUtils;
import org.json.JSONObject;

import static org.apache.commons.codec.digest.HmacAlgorithms.HMAC_SHA_256;


public final class App {

    public static void main(String[] args) throws IOException, InterruptedException
    {  

    
        String message = "{ \n"+
               " \"size\":\"1.00\", \n"+
               " \"price\":\"0.80\", \n"+
               " \"side\":\"buy\", \n"+
               " \"product_id\":\"BTC-USD\" \n"+
               "}"; 

        JSONObject json = new JSONObject(message);
        message = json.toString();

        //get timestamp and turn it into a string
        final long ut2 = System.currentTimeMillis() / 1000L;
        final String timeStamp = String.valueOf(ut2);
        System.out.println(timeStamp);

        //creating preHash string
        //String preHash = timeStamp + "POST" + "/orders" + message;
        String secretKey = "";
        //base64 secret key
        // byte[] decodedKey = Base64.getDecoder().decode(secretKey.getBytes());
        //  String decodedString = new String(decodedKey);
        //HMAC the secret key
        // String signature = hmacWithApacheCommons("HmacSHA256", decodedString, preHash);
        // // HmacUtils(HmacAlgorithms.HMAC_SHA_256, message).hmacHex(message);
        // String encodedString = Base64.getEncoder().encodeToString(signature.getBytes());
        String httpPath = "/v2/exchange-rates?currency=USD";

        final String message2 = timeStamp + "GET" + httpPath + "";
        final String signature1 = new HmacUtils(HMAC_SHA_256, secretKey.getBytes()).hmacHex(message2);
        //String encodedString = Base64.getEncoder().encodeToString(signature1.getBytes());
        System.out.println(signature1);

        
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://api-public.sandbox.exchange.coinbase.com"))
            .header("Accept", "application/json")
            .header("cb-access-key", "")
            .header("cb-access-passphrase", "")
            .header("cb-access-sign", signature1)
            .header("cb-access-timestamp", timeStamp)
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());



    }
    //MGVjMzBlNmZjYTk1NGI0NTM5MzFmNTEzOTRmZTk2YjcyNTgyNjE0NjIxNWQ1NmFjZmVjYzA0MjViZDUyODkyMA==
    // FvyOvhKP18B1INaGP3xSnbRndx3lDnZjlTLtYNCpFRs=
    //f6bb5462f794c2de1f9b4166d134b717f446c33b8303374c1d49cbda796b97da




    
}
