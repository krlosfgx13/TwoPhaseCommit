package com.example.ddbproject.model;

public class Person {
    private int personId;
    private String dpi;
    private String firstName;
    private String secondName;
    private String thirdName;
    private String firstLastName;
    private String secondLastName;
    private String address;
    private String phoneNumber;
    private String homePhoneNumber;
    private String emailAddress;

    public int getPersonId() {
        return personId;
    }

    public String getDpi() {
        return dpi;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public String getFirstLastName() {
        return firstLastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

//    public void setDpi(Long dpi) {
//        this.dpi = dpi;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public void setSecondName(String secondName) {
//        this.secondName = secondName;
//    }
//
//    public void setThirdName(String thirdName) {
//        this.thirdName = thirdName;
//    }
//
//    public void setFirstLastName(String firstLastName) {
//        this.firstLastName = firstLastName;
//    }
//
//    public void setSecondLastName(String secondLastName) {
//        this.secondLastName = secondLastName;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public void setPhoneNumber(Long phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public void setHomePhoneNumber(Long homePhoneNumber) {
//        this.homePhoneNumber = homePhoneNumber;
//    }
//
//    public void setEmailAddress(String emailAddress) {
//        this.emailAddress = emailAddress;
//    }

    public static class Builder{
        private int personId;
        private String dpi;
        private String firstName;
        private String secondName;
        private String thirdName;
        private String firstLastName;
        private String secondLastName;
        private String address;
        private String phoneNumber;
        private String homePhoneNumber;
        private String emailAddress;

        public Builder(){

        }

        public Builder withId(int id){
            this.personId = id;
            return this;
        }

        public Builder withDpi(String dpi){
            this.dpi = dpi;
            return this;
        }

        public Builder withFirstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public Builder withSecondName(String secondName){
            this.secondName = secondName;
            return this;
        }

        public Builder withThirdName(String thirdName){
            this.thirdName = thirdName;
            return this;
        }

        public Builder withFirstLastName(String firstLastName){
            this.firstLastName = firstLastName;
            return this;
        }

        public Builder withSecondLastName(String secondLastName){
            this.secondLastName = secondLastName;
            return this;
        }

        public Builder withAddress(String address){
            this.address = address;
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber){
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder withHomePhoneNumber(String homePhoneNumber){
            this.homePhoneNumber = homePhoneNumber;
            return this;
        }

        public Builder withEmailAddress(String emailAddress){
            this.emailAddress = emailAddress;
            return this;
        }

        public Person build(){
            Person person = new Person();
            person.personId = this.personId;
            person.dpi = this.dpi;
            person.firstName = this.firstName;
            person.secondName = this.secondName;
            person.thirdName = this.thirdName;
            person.firstLastName = this.firstLastName;
            person.secondLastName = this.secondLastName;
            person.address = this.address;
            person.phoneNumber = this.phoneNumber;
            person.homePhoneNumber = this.homePhoneNumber;
            person.emailAddress = this.emailAddress;
            return person;
        }
    }
}
