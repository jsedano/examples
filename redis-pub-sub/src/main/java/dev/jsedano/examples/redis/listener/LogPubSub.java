package dev.jsedano.examples.redis.listener;

import redis.clients.jedis.JedisPubSub;

public class LogPubSub extends JedisPubSub {

    private String name;

    public LogPubSub(String name){
        this.name = name;
    }

    public void onMessage(String channel, String message) {
        System.out.printf("name: %s method: %s channel: %s message: %s\n", name, "onMessage", channel, message);
        switch (message){
            case "ping":
                this.ping();
            break;
            case "exit":
                this.unsubscribe(channel);
                break;
            default:
                if(message.indexOf("ping") == 0 && message.indexOf(" ") == 4){
                    this.ping(message.substring(5));
                }
        }
    }

    public void onPMessage(String pattern, String channel, String message) {
        System.out.printf("name: %s method: %s pattern: %s channel: %s message: %s\n", name, "onPMessage", pattern, channel, message);
        switch (message){
            case "ping":
                this.ping();
                break;
            case "exit":
                this.punsubscribe(pattern);
                break;
            default:
                if(message.indexOf("ping") == 0 && message.indexOf(" ") == 4){
                    this.ping(message.substring(5));
                }
        }

    }

    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.printf("name: %s method: %s channel: %s subscribedChannels: %d\n", name, "onSubscribe", channel, subscribedChannels);
    }

    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.printf("name: %s method: %s channel: %s subscribedChannels: %d\n", name, "onUnsubscribe", channel, subscribedChannels);

    }

    public void onPUnsubscribe(String pattern, int subscribedChannels) {
        System.out.printf("name: %s method: %s patten: %s subscribedChannels: %d\n", name, "onPUnsubscribe", pattern, subscribedChannels);
    }

    public void onPSubscribe(String pattern, int subscribedChannels) {
        System.out.printf("name: %s method: %s patten: %s subscribedChannels: %d\n", name, "onPSubscribe", pattern, subscribedChannels);
    }

    public void onPong(String message) {
        System.out.printf("name: %s method: %s message: %s\n", name, "onPong", message);
    }


}
