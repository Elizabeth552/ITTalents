package main;

public abstract class Person {

    private String name;
    private String phone;

    public Person(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
