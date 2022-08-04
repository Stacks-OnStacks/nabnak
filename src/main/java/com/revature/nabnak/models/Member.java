package com.revature.nabnak.models;

public class Member { // classes are simply blueprints for that object

    // Objects - instances of a class in memory

    // When making a custom class note you generally have 3 vital sections
    // 1. Fields/Attributes/State/Data
    // 2. Constructors (default is a No-Arg Constructor)
    // 3. Methods

    // Attributes
    private String email;
    private String fullName;
    private int experienceMonths;
    private String registrationDate;
    private String password; // access modifier private privatizes the variable to only be accessible by the class

    // Constructor
    // Polymorphism - Overloading: that the same method or constructor can take in a variety of arugments and still work
    // Default no-arg (don't specify if you're not making any custom constructors)
    public Member(){
        super(); //super is the class this is inheriting from
        // this is due to the root object of class called Object
        // Object oriented programming comes from this root
        // Inheritance - Pillar of OOP
        // this allows any class to extend another class and inherit it's attributes, constructors and methods
        // All class in java inherit information from the Object class
    }

    public Member(String email, String fullName, int experienceMonths, String registrationDate){
        // email, fullName, etc is only available to the scope of this Constructor. This is known as shadowing.
        this.email = email;
        this.fullName = fullName;
        this.experienceMonths = experienceMonths;
        this.registrationDate = registrationDate;
    }

    // the constructor is requiring 4 parameteres
    // parameter definition requires the Datatype to proceed tghe variable name, much like declaractor of variable
    public Member(String email, String fullName, int experienceMonths, String registrationDate, String password){
        // email, fullName, etc is only available to the scope of this Constructor. This is known as shadowing.
        this.email = email;
        this.fullName = fullName;
        this.experienceMonths = experienceMonths;
        this.registrationDate = registrationDate;
        this.password = password;
    }

    // Methods!!!
    // public allows all instances of this class to view the method
    // String is the return datatype
    // writeToString is the methods name

    // Enscapsulation - this privatizes the variables to prevent random reassignment and hide any important information for users


    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setExperienceMonths(int experienceMonths) {
        this.experienceMonths = experienceMonths;
    }

    public int getExperienceMonths() {
        return experienceMonths;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setPassword(String password){
        this.password = password; // this.password is the value of the classes password where = password is the argument that was provided
    }

    public String getPassword(){
        return password; // this is inside of the scope for the object
    }

    public String writeToFile(){
        return email + "," + fullName + "," + experienceMonths + "," + registrationDate + "," + password + "\n";
    }

    // Polymorphism - Overriding section of polymorphism pillar of OOP
    // means that you're changing the initial method's actions
    @Override // this is annotation, it's metadata
    public String toString() {
        return "Member{" +
                "email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", experienceMonths=" + experienceMonths +
                ", registrationDate='" + registrationDate + '\'' +
                '}';
    }
}
