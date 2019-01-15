/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.io_model.tradition_io;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 *
 *
 * @author HaoBin
 * @version $Id: IOClient.java, v0.1 2019/1/15 16:41 HaoBin 
 */
public class IOClient {

    public static void main(String[] args) throws Exception{
        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 8000);
                while (true) {
                    socket.getOutputStream().write((new Date() + ": hello world").getBytes());
                }
            } catch (IOException ioex) {

            }
        }).start();
    }
}