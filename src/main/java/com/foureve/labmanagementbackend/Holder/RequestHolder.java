package com.foureve.labmanagementbackend.Holder;

import com.foureve.labmanagementbackend.domain.vo.req.RequestInfo;

public class RequestHolder {

    public static ThreadLocal<RequestInfo> threadLocal = new ThreadLocal<>();

    public static void set(RequestInfo requestInfo) {
        threadLocal.set(requestInfo);
    }

    public static RequestInfo get() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }

}
