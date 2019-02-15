package com.ceres.api.security;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;

/**
 * Utility class to sign messages using HMAC-SHA256.
 */
public class HmacSHA256Signer {

  /**
   * Sign the given message using the given secret.
   * @param message message to sign
   * @param secret secret key
   * @return a signed message
   */
  public static String sign(String message, String secret) {
    try {
      Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
      SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
      sha256_HMAC.init(secretKeySpec);
      return new String(Hex.encodeHex(sha256_HMAC.doFinal(message.getBytes())));
    } catch (Exception e) {
      throw new RuntimeException("Unable to sign message.", e);
    }
  }

  /**
   * SHA256 Defined By CC (not hmac)
   */
  public static String sign2(String message, String secret) {
    StringBuilder sb = new StringBuilder(message);
    sb.append("secret=").append(secret);
    try {
      MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
      messageDigest.update(sb.toString().getBytes("UTF-8"));
      return byte2Hex(messageDigest.digest());
    }catch (Exception e) {
      throw new RuntimeException("Unable to sign message.", e);
    }
  }

  /**
   * Convert Byte To Hexadecimal
   */
  private static String byte2Hex(byte[] bytes) {
    StringBuffer stringBuffer = new StringBuffer();
    String temp = null;
    for (int i = 0; i < bytes.length; i++) {
      temp = Integer.toHexString(bytes[i] & 0xFF);
      if (temp.length() == 1) {
        stringBuffer.append("0");
      }
      stringBuffer.append(temp);
    }
    return stringBuffer.toString();
  }
}
