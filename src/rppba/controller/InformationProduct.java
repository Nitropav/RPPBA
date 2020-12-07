package rppba.controller;

import java.util.Objects;

public class InformationProduct {
    private String numberProduct;
    private String modelProduct;
    private String priceProduct;
    private String typProduct;
    private String shellProduct;
    private String kerelProduct;

    public String getNumberProduct() {
        return numberProduct;
    }

    public void setNumberProduct(String numberProduct) {
        this.numberProduct = numberProduct;
    }

    public String getModelProduct() {
        return modelProduct;
    }

    public void setModelProduct(String modelProduct) {
        this.modelProduct = modelProduct;
    }

    public String getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(String priceProduct) {
        this.priceProduct = priceProduct;
    }

    public String getTypProduct() {
        return typProduct;
    }

    public void setTypProduct(String typProduct) {
        this.typProduct = typProduct;
    }

    public String getShellProduct() {
        return shellProduct;
    }

    public void setShellProduct(String shellProduct) {
        this.shellProduct = shellProduct;
    }

    public String getKerelProduct() {
        return kerelProduct;
    }

    public void setKerelProduct(String kerelProduct) {
        this.kerelProduct = kerelProduct;
    }

    @Override
    public String toString() {
        return "InformationProduct{" +
                "numberProduct='" + numberProduct + '\'' +
                ", modelProduct='" + modelProduct + '\'' +
                ", priceProduct='" + priceProduct + '\'' +
                ", typProduct='" + typProduct + '\'' +
                ", shellProduct='" + shellProduct + '\'' +
                ", kerelProduct='" + kerelProduct + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InformationProduct informationProduct = (InformationProduct) o;
        return Objects.equals(numberProduct, informationProduct.numberProduct) &&
                Objects.equals(modelProduct, informationProduct.modelProduct) &&
                Objects.equals(priceProduct, informationProduct.priceProduct) &&
                Objects.equals(typProduct, informationProduct.typProduct) &&
                Objects.equals(shellProduct, informationProduct.shellProduct) &&
                Objects.equals(kerelProduct, informationProduct.kerelProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberProduct, modelProduct, priceProduct, typProduct, shellProduct, kerelProduct);
    }
}
