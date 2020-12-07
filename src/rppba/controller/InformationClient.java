package rppba.controller;

import java.util.Objects;

public class InformationClient {
    private String orderNumber;
    private String nameClient;
    private String surname;
    private String numberTel;
    private String email;
    private String viewLoylty;
    private double boundary;

    public double getBoundary() {
        return boundary;
    }

    public void setBoundary(double boundary) {
        this.boundary = boundary;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNumberTel() {
        return numberTel;
    }

    public void setNumberTel(String numberTel) {
        this.numberTel = numberTel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getViewLoylty() {
        return viewLoylty;
    }

    public void setViewLoylty(String viewLoylty) {
        this.viewLoylty = viewLoylty;
    }

    @Override
    public String toString() {
        return "InformationClient{" +
                "orderNumber='" + orderNumber + '\'' +
                ", nameClient='" + nameClient + '\'' +
                ", surname='" + surname + '\'' +
                ", numberTel='" + numberTel + '\'' +
                ", email='" + email + '\'' +
                ", viewLoylty='" + viewLoylty + '\'' +
                ", boundary='" + boundary + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InformationClient informationClient = (InformationClient) o;
        return Objects.equals(orderNumber, informationClient.orderNumber) &&
                Objects.equals(nameClient, informationClient.nameClient) &&
                Objects.equals(surname, informationClient.surname) &&
                Objects.equals(numberTel, informationClient.numberTel) &&
                Objects.equals(email, informationClient.email) &&
                Objects.equals(viewLoylty, informationClient.viewLoylty) &&
                Objects.equals(boundary, informationClient.boundary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber, nameClient, surname, numberTel, email, viewLoylty, boundary);
    }
}
