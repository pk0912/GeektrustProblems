package com.family.models;

public class Person implements Comparable<Person> {

    private final int id;
    private final String name;
    private final String gender;
    private final Person mother;
    private Person father = null;
    private Person spouse = null;

    public Person(int id, String name, String gender, Person mother) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.mother = mother;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public Person getFather() {
        return father;
    }

    public void setFather(Person father) {
        this.father = father;
    }

    public Person getMother() {
        return mother;
    }

    public Person getSpouse() {
        return spouse;
    }

    public void setSpouse(Person spouse) {
        this.spouse = spouse;
    }

    @Override
    public int compareTo(Person p) {
        int compareId = p.getId();
        return this.id - compareId;
    }
}
