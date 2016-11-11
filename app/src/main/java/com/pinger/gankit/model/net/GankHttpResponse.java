package com.pinger.gankit.model.net;/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.model.net
 *  @文件名:   GankHttpResponse
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/3 14:47
 *  @描述：    gank请求结果类
 */

public class GankHttpResponse<T> {

    private boolean error;
    private T results;

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    public boolean getError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
