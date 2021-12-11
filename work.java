package com.example;
import org.apache.commons.codec.digest.HmacUtils;
import static org.apache.commons.codec.digest.HmacAlgorithms.HMAC_SHA_256;

/** Manage all the security mechanism required by Coinbase to call protected resources */
public class work {

  private static Void aVoid;
  {
    final long ut2 = System.currentTimeMillis() / 1000L;
    final String timeStamp = String.valueOf(ut2);

    final String secret = "qN5YDI34x6w3GPv1nqdVuU2u74Y8cn4j7mTosc8ACcT+G+lZcSG4yhvYqKlcxJjEnVGeAJlngKMbg+IVaSbqSA=="; 
    final String httpMethod = "GET";
    final String httpPath = "/v2/exchange-rates?currency=USD.";

    String message = timeStamp + httpMethod + httpPath;
    String signature = new HmacUtils(HMAC_SHA_256, secret.getBytes()).hmacHex(message);
    System.out.println(signature);
  }
} 

