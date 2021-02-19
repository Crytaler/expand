package com.example.expand.rpcFrame.service;

import com.example.expand.rpcFrame.RPCClient;
import com.example.expand.rpcFrame.RPCServer;

import java.net.InetSocketAddress;

/**
 * @author yz.m
 * @version v1.0.0
 * @description:
 * @date 2021/2/19 17:53
 */
public class RPCClientTest {

    public static void main( String[] args )
    {
        UserService userService = RPCClient.getRemoteProxy(UserService.class,new InetSocketAddress("127.0.0.1",8201));
        String result = userService.addUserName("王五");
        System.out.println(result);
    }
}
