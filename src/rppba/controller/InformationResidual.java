package rppba.controller;

import java.util.Objects;

public class InformationResidual {
    private String model;
    private String residual;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getResidual() {
        return residual;
    }

    public void setResidual(String residual) {
        this.residual = residual;
    }

    @Override
    public String toString() {
        return "InformationResidual{" +
                "model='" + model + '\'' +
                ", residual='" + residual + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InformationResidual informationResidual = (InformationResidual) o;
        return Objects.equals(model, informationResidual.model) &&
                Objects.equals(residual, informationResidual.residual);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, residual);
    }
}
