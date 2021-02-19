package com.example.expand.rpcFrame;

import java.io.Serializable;

/**
 * @author yz.m
 * @version v1.0.0
 * @description: 传输协议
 * @date 2021/2/19 16:49
 */
public class RPCProtocol implements Serializable {
    // 接口的全类名
    String interfaceClassName;
    // 方法名称
    String methodName;
    // 参数类型数组
    Class<?>[] parameterTypes;
    // 参数对象数组
    Object[] parameterValues;

    public String getInterfaceClassName() {
        return interfaceClassName;
    }

    public void setInterfaceClassName(String interfaceClassName) {
        this.interfaceClassName = interfaceClassName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getParameterValues() {
        return parameterValues;
    }

    public void setParameterValues(Object[] parameterValues) {
        this.parameterValues = parameterValues;
    }
}
