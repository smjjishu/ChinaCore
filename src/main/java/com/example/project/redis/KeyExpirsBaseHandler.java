package com.example.project.redis;

public interface KeyExpirsBaseHandler {
    boolean matchKey(String key);

    void handle(String key);
}
