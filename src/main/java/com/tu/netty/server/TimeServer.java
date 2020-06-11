package com.tu.netty.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description bio server
 * @Classname TimeServer
 * @Date 2019/6/11 16:16
 * @Created by tuyongjian
 */
public class TimeServer {

    public static void main(String[] args) throws IOException {
       int port = 8080;
       if (args!=null && args.length>0){
           port = Integer.parseInt(args[0]);
       }
        ServerSocket server = null;
       try {
           server = new ServerSocket(port);
           System.out.println("this timeserver is start in port :"+port);
           Socket socket = null;
           TimeServerHandlerExecutePool executePool = new TimeServerHandlerExecutePool(50,100);
           while (true){
               socket = server.accept();
               executePool.execute(new TimeServerHandler(socket));
               //new Thread(new TimeServerHandler(socket)).start();
           }

       }catch (Exception e){

       }finally {
           if(server!=null){
               System.out.println("this timeserver is close");
               server.close();
               server = null;
           }
       }
    }
}