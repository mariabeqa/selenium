package ru.srqa.training.selenium.model;

public class UserData {
    private String firstName;

    private String lastName;

    private String address;

    private String postcode;

    private String city;

    private String country;

    private String state;

    private String email;

    private String phone;

    public UserData withFisrtName(String first) {
        this.firstName = first;
        return this;
    }

    public UserData withLastName(String last) {
        this.lastName = last;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public UserData withAddress(String address) {
        this.address = address;

        return this;
    }

    public UserData withPostcode(String postcode) {
        this.postcode = postcode;
        return this;
    }

    public UserData withCity(String city) {
        this.city = city;
        return this;
    }

    public UserData withCountry(String country) {
        this.country = country;
        return this;
    }

    public UserData withState(String state) {
        this.state = state;
        return this;
    }

    public UserData withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserData withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }
}
