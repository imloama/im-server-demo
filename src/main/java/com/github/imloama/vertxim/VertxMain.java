package com.github.imloama.vertxim;

import io.vertx.core.Vertx;

public class VertxMain {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
//        vertx.deployVerticle(ImVerticle.class.getName());
        vertx.deployVerticle(SocketAndWebSocketVerticle.class.getName());
    }


}
