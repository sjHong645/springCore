package com.lifeCycle;

public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);

        // 아래 부분은 afterPropertySet() 메소드에 담았다.
        /*connect();
        call("초기화 연결 메시지");*/

    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect:  " + url);

    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    // 서비스 종료시 호출
    public void disConnect() {
        System.out.println("close:  " + url);
    }

    // 의존관계 주입이 끝나면 호출하는 메서드
    public void init() {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

    // 빈이 종료될 때 호출하는 메서드
    public void close() {
        System.out.println("NetworkClient.destroy");
        disConnect();
    }
}
