package com.example.expand.rpcFrame;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yz.m
 * @version v1.0.0
 * @description:
 * @date 2021/2/19 17:26
 */
public class RPCServer {

    // 定义存储暴露的接口
    HashMap<String, Object> hashMap = new HashMap<>();
    // 定义一个线程池
    ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(8, 20, 200 , TimeUnit.MICROSECONDS,
            new ArrayBlockingQueue(10)
    );

    //  暴露服务的接口
    public void publicServiceAPI(Class<?> clazz, Object instance){
        this.hashMap.put(clazz.getName(), instance);
    }

    // 发布服务的方法
    public void start(int port){
        // 创建网络服务端
        try {
            ServerSocket serverSocket = new ServerSocket();
            //绑定端口哦
            serverSocket.bind(new InetSocketAddress(port));
            System.out.println("===================RPC Server服务端启动成功===================");

            // 创建客户端处理请求的线程
            while (true){
                poolExecutor.execute(new ServerTask(serverSocket.accept()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private class ServerTask implements Runnable{
        private Socket socket;
        public ServerTask(Socket socket){
            this.socket = socket;

        }
        @Override
        public void run() {
            // try(){
            // } try括号内内容 如果捕获异常 则括号内资源自动关闭
            try(
                    ObjectInputStream deserialize = new ObjectInputStream(socket.getInputStream());
                    ObjectOutputStream serialize = new ObjectOutputStream(socket.getOutputStream())
            ){
                // 反序列获取客户端的协议
                RPCProtocol rpcProtocol = (RPCProtocol) deserialize.readObject();
                // 通过客户端传来的值获取对应的接口
                System.out.println(rpcProtocol.getInterfaceClassName());
                Object instance = hashMap.get(rpcProtocol.getInterfaceClassName());
                // 利用反射通过实例以及参数获取调用的具体方法
                Method method = instance.getClass().getDeclaredMethod(rpcProtocol.getMethodName(),
                        rpcProtocol.getParameterTypes());
                //  执行方法获取对应的结果
                Object result =  method.invoke(instance,rpcProtocol.getParameterValues());// param1 :代理类的实例对象 param2： 具体的代理的方法参数
                // 将执行的结果序列化，传回给客户端
                serialize.writeObject(result);

            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
