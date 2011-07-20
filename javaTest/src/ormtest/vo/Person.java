package ormtest.vo;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class Person {

  int age;
  String name;
  String addr;
  
  public int getAge() {
    return age;
  }
  public void setAge(int age) {
    this.age = age;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getAddr() {
    return addr;
  }
  public void setAddr(String addr) {
    this.addr = addr;
  }
  
  public String toString(){
    return ReflectionToStringBuilder.toString(this);
  }
  
  public static void main(String[] args) {
    Person p = new Person();
    p.setAge(4);
    p.setName("123");
    p.setAddr("444");
    
    System.out.println(p.toString());
  }
}
