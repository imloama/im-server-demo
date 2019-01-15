/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.io_model.tradition_io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 传统IO服务端
 *
 * @author HaoBin
 * @version $Id: IOServer.java, v0.1 2019/1/15 16:30 HaoBin 
 */
public class IOServer {

    public static void main(String[] args) throws Exception{

        ServerSocket serverSocket = new ServerSocket(8000);
        // 接收连接请求，创建新线程
        new Thread(() -> {
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    // 每个新的连接创建一个线程处理请求
                    new Thread(() -> {
                        try {
                            int len;
                            byte[] data = new byte[1024];
                            InputStream inputStream = socket.getInputStream();
                            while ((len = inputStream.read(data)) != -1) {
                                System.out.println(new String(data, 0, len));
                            }
                        } catch (IOException ioex) {

                        }
                    }).start();
                } catch (IOException ioex) {

                }
            }
        }).start();
    }
}