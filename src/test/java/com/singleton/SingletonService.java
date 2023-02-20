package com.singleton;

public class SingletonService {

    // static 까지 붙여줌으로써
    // 인스턴스 까지 만들어 놓고 data 영역에 저장되어 있는 상태
    // 저장되어 있는 주소가 instance 라는 변수에 저장되어 있다.
    private static final SingletonService instance = new SingletonService();

    // 저장해 놓은 instance를 조회하기 => 때문에 항상 같은 인스턴스를 조회할 것이다
    public static SingletonService getInstance() {
        return instance;
    }

    // 1개의 객체만 만들어 놓고
    // 다른 클래스에서 인스턴스를 생성하지 못하도록 하기 위해
    // private을 사용한다.
    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글통 객체 로직 호출");
    }

}
