package com.example.expand.rpcFrame.service;

/**
 * @author yz.m
 * @version v1.0.0
 * @description:
 * @date 2021/2/19 17:52
 */
public class UserServiceImpl implements UserService {
    @Override
    public String addUserName(String user) {
        return "添加了" + user;
    }
}
