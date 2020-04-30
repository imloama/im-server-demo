package com.github.imloama.vertxim;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.net.NetServer;
import lombok.extern.slf4j.Slf4j;

/**
 * 同时支付socket与websocket
 */
@Slf4j
public class SocketAndWebSocketVerticle extends AbstractVerticle {

    int port = 8000;

    @Override
    public void start() throws Exception {

        NetServer server = vertx.createNetServer();
        server.connectHandler(socket -> {
            socket.handler(buffer -> {
                log.debug("socket read data: " + buffer.toString());
                socket.write(buffer);
            });
        }).listen(port);

//        HttpServer httpServer = vertx.createHttpServer();
//        httpServer.webSocketHandler(websocket -> {
//
//        });
//        httpServer.listen()




    }
}
