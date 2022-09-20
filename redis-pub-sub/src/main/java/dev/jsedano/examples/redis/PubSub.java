package dev.jsedano.examples.redis;

import dev.jsedano.examples.redis.listener.LogPubSub;
import redis.clients.jedis.JedisPooled;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PubSub {

  public static void main(String[] args) {
    JedisPooled jedis = new JedisPooled("localhost", 6379);
    ExecutorService executor = Executors.newFixedThreadPool(4);


    executor.execute(() -> jedis.subscribe(new LogPubSub("onlyOne"), "dev.one"));
    executor.execute(() -> jedis.subscribe(new LogPubSub("oneAndTwo"), "dev.one", "dev.two"));
    executor.execute(() -> jedis.psubscribe(new LogPubSub("pattern"), "dev.*"));


      String message = "";
      try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
        do {
          message = br.readLine();
          int firstSpace = message.indexOf(' ');
          if(firstSpace > 1){
            jedis.publish(message.substring(0, firstSpace), message.substring(firstSpace+1));
          }
        } while (!"close".equals(message));
        jedis.close();
        System.exit(0);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
  }




  }

