package com.github.imloama.vertxim;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.ext.web.Router;

import java.util.HashMap;
import java.util.Map;


public class ImVerticle extends AbstractVerticle {

    private Map<String, ServerWebSocket> localMap = new HashMap<>(16);

    @Override
    public void start() throws Exception {
        EventBus eventBus = vertx.eventBus();

        // http server
        HttpServer httpServer = vertx.createHttpServer();
        Router router = Router.router(vertx);
        router.get().handler(event -> {
            HttpServerRequest request = event.request();
            request.bodyHandler(t -> {
                System.out.println(t.toString());
            });
            event.response().end("hello");
        });

        httpServer.requestHandler(router);

        // websocket server
        httpServer.webSocketHandler(serverWebSocket -> {
            String id = serverWebSocket.binaryHandlerID();
            System.out.println("========建立websocket连接========="+id);
            if(!checkID(id)){
                localMap.put(id, serverWebSocket);
            }
            //每个请求
            serverWebSocket.frameHandler(handler -> {
                System.out.println("===========server socket handler==========");
                if(handler.isClose()){
                    return;
                }
                System.out.println(handler.textData());
                for (Map.Entry<String, ServerWebSocket> entry : localMap.entrySet()) {
                    if (id.equals(entry.getKey())) {
                        continue;
                    }
                    entry.getValue().writeTextMessage(handler.textData());
                }
            });


            serverWebSocket.closeHandler(handler->{
                System.out.println("客户端关闭连接了!");
                localMap.remove(id);
            });

        });


        httpServer.listen(8000);

    }

    public boolean checkID(String id) {
        return localMap.containsKey(id);
    }
}
