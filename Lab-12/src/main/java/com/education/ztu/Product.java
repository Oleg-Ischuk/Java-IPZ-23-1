package com.education.ztu;

public class Product implements Comparable<Product> {
    private String brand;
    private String model;
    private int year;
    private double price;

    public Product(String brand, String model, int year, double price) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" + brand + " " + model + ", " + year + ", $" + price + "}";
    }

    @Override
    public int compareTo(Product other) {
        return Double.compare(this.price, other.price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product p = (Product) o;
        return brand.equals(p.brand)
                && model.equals(p.model)
                && year == p.year
                && price == p.price;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(brand, model, year, price);
    }
}
