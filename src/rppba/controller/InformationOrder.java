package rppba.controller;

import java.util.Objects;

public class InformationOrder {
    private String orderNumber;
    private String price;
    private String address;
    private String clientLast;
    private String viewDelivery;
    private String viewStates;
    private String date;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getClientLast() {
        return clientLast;
    }

    public void setClientLast(String clientLast) {
        this.clientLast = clientLast;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getViewDelivery() {
        return viewDelivery;
    }

    public void setViewDelivery(String viewDelivery) {
        this.viewDelivery = viewDelivery;
    }

    public String getViewStates() {
        return viewStates;
    }

    public void setViewStates(String viewStates) {
        this.viewStates = viewStates;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "InformationOrder{" +
                "orderNumber='" + orderNumber + '\'' +
                ", price='" + price + '\'' +
                ", address='" + address + '\'' +
                ", clientLast='" + clientLast + '\'' +
                ", viewDelivery='" + viewDelivery + '\'' +
                ", viewStates='" + viewStates + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InformationOrder informationOrder = (InformationOrder) o;
        return Objects.equals(orderNumber, informationOrder.orderNumber) &&
                Objects.equals(price, informationOrder.price) &&
                Objects.equals(address, informationOrder.address) &&
                Objects.equals(clientLast, informationOrder.clientLast) &&
                Objects.equals(viewDelivery, informationOrder.viewDelivery) &&
                Objects.equals(viewStates, informationOrder.viewStates) &&
                Objects.equals(date, informationOrder.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber, price, address, clientLast, viewDelivery, viewStates, date);
    }
}
