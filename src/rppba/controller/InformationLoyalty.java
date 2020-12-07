package rppba.controller;

import java.util.Objects;

public class InformationLoyalty {
    private String numberLoyalty;
    private String nameLoyalty;
    private double saleLoyalty;
    private String boundaryLoyalty;

    public String getNumberLoyalty() {
        return numberLoyalty;
    }

    public void setNumberLoyalty(String numberLoyalty) {
        this.numberLoyalty = numberLoyalty;
    }

    public String getNameLoyalty() {
        return nameLoyalty;
    }

    public void setNameLoyalty(String nameLoyalty) {
        this.nameLoyalty = nameLoyalty;
    }

    public double getSaleLoyalty() {
        return saleLoyalty;
    }

    public void setSaleLoyalty(double saleLoyalty) {
        this.saleLoyalty = saleLoyalty;
    }

    public String getBoundaryLoyalty() {
        return boundaryLoyalty;
    }

    public void setBoundaryLoyalty(String boundaryLoyalty) {
        this.boundaryLoyalty = boundaryLoyalty;
    }

    @Override
    public String toString() {
        return "InformationLoyalty{" +
                "numberLoyalty='" + numberLoyalty + '\'' +
                ", nameLoyalty='" + nameLoyalty + '\'' +
                ", saleLoyalty='" + saleLoyalty + '\'' +
                ", boundaryLoyalty='" + boundaryLoyalty + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InformationLoyalty informationLoyalty = (InformationLoyalty) o;
        return Objects.equals(numberLoyalty, informationLoyalty.numberLoyalty) &&
                Objects.equals(nameLoyalty, informationLoyalty.nameLoyalty) &&
                Objects.equals(saleLoyalty, informationLoyalty.saleLoyalty) &&
                Objects.equals(boundaryLoyalty, informationLoyalty.boundaryLoyalty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberLoyalty, nameLoyalty, saleLoyalty, boundaryLoyalty);
    }
}
