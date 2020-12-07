package rppba.controller;

import java.util.Objects;

public class InformationProduction {
    private String idProduction;
    private String model;
    private String count;

    public String getIdProduction() {
        return idProduction;
    }

    public void setIdProduction(String idProduction) {
        this.idProduction = idProduction;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "InformationProduction{" +
                "idProduction='" + idProduction + '\'' +
                ", model='" + model + '\'' +
                ", count='" + count + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InformationProduction informationProduction = (InformationProduction) o;
        return Objects.equals(idProduction, informationProduction.idProduction) &&
                Objects.equals(model, informationProduction.model) &&
                Objects.equals(count, informationProduction.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduction, model, count);
    }
}
