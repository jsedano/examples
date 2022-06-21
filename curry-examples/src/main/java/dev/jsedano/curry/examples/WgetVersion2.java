package dev.jsedano.curry.examples;

import dev.jsedano.curry.annotation.Curry;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public final class WgetVersion2 {

  @Curry
  public static String wget(
      int connectionTimeout,
      int readTimeout,
      boolean followRedirects,
      String requestMethod,
      String address) {
    try {
      URL url = new URL(address);
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod(requestMethod);
      con.setConnectTimeout(connectionTimeout);
      con.setReadTimeout(readTimeout);
      con.setInstanceFollowRedirects(followRedirects);
      BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
      String inputLine;
      StringBuffer content = new StringBuffer();
      while ((inputLine = in.readLine()) != null) {
        content.append(inputLine);
      }
      in.close();
      return address + " " + content.toString();
    } catch (Exception e) {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      e.printStackTrace(pw);
      String stackTrace = sw.toString();
      return address + " " + stackTrace.substring(0, stackTrace.indexOf("\n"));
    }
  }

  public static void main(String[] args) {
    var get =
        WgetVersion2Curryer.wget(WgetVersion2::wget)
            .apply(100)
            .apply(100)
            .apply(false)
            .apply("GET");

    List.of(
            "https://www.google.com",
            "https://www.wikipedia.org",
            "asdf",
            "https://docs.oracle.com/javase/10/docs/api/java/net/package-summary.html",
            "https://jsedano.dev",
            "https://raw.githubusercontent.com/center-key/clabe-validator/main/clabe.ts")
        .parallelStream()
        .map(get)
        .forEach(System.out::println);
  }
}
