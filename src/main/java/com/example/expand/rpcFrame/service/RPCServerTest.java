package com.example.expand.rpcFrame.service;

import com.example.expand.rpcFrame.RPCServer;

/**
 * @author yz.m
 * @version v1.0.0
 * @description:
 * @date 2021/2/19 17:53
 */
public class RPCServerTest {

    public static void main( String[] args )
    {
        // 获取服务端
        RPCServer server = new RPCServer();
        // 暴露接口
        server.publicServiceAPI(UserService.class, new UserServiceImpl());
        // 发布服务
        server.start(8201);
    }
}
