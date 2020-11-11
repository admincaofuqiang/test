package com.example.test.service;

public class StudentService {
    private String name;
    private String sex;

    public StudentService(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }
    public String sayHello(String name){
        return "HeLlo"+name;
    }
    public String sayHelloTest(){
        return name+"  "+sex;
    }
}
