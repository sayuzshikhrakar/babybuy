package com.example.babybuy.Model;



public class User {

    private String firstName;
    private String lastName;
    private String phoneNmber;
    private String Email;
    private String Password;
    private String userName;





    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNmber() {
        return phoneNmber;
    }

    public void setPhoneNmber(String phoneNmber) {
        this.phoneNmber = phoneNmber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public User(String firstName, String lastName, String phoneNmber, String Email, String Password, String userName)
    {
        this.firstName = firstName;
        this.lastName=lastName;
        this.phoneNmber=phoneNmber;
        this.Email=Email;
        this.Password=Password;
        this.userName=userName;
    }

    public User(){}

}

