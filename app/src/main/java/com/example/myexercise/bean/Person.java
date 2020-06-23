package com.example.myexercise.bean;

public class Person {
    //属性
    private String name;
    private int img;

    //构造方法

    public Person(String name, int img) {
        this.name = name;
        this.img = img;
    }
    //set,get
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
