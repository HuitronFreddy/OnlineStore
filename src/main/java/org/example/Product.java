package org.example;

public class Product {

//properties and fields

    private String sku;

    private String productName;

    private double price;

    private String department;

    public Product(String sku, String productName, double price, String department) {
        this.sku = sku;
        this.productName = productName;
        this.price = price;
        this.department = department;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    // i added this method here cause it will save us time to print out the list-bimal
    @Override
    public String toString(){
        return "Product Name: "+productName+"\n"+"price: "+price+"\n"+"Department: "+department+"\n";
    }

}
