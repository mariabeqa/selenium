package ru.srqa.training.selenium.model;

import java.io.File;

public class ProductData {
    private String name;

    private String code;

    private String category;

    private String quantity;

    private String photo;

    private String dateFrom;

    private String dateTo;

    private String manufacturer;

    private String shortDescription;

    private String description;

    private String purchasePrice;

    private String price;

    public ProductData withName(String name) {

        this.name = name;
        return this;
    }

    public ProductData withCode(String code) {
        this.code = code;
        return this;
    }

    public ProductData withCategory(String category) {
        this.category = category;
        return this;
    }

    public ProductData withQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public ProductData withDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public ProductData withDateTo(String dateTo) {
        this.dateTo = dateTo;
        return this;
    }

    public ProductData withImage(File photo) {
        this.photo = photo.getAbsolutePath();
        return this;
    }

    public ProductData withManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public ProductData withShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
        return this;
    }

    public ProductData withDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductData withPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
        return this;
    }

    public ProductData withPrice(String price) {
        this.price = price;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getCategory() {
        return category;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getPhoto() {
        return photo;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public String getPrice() {
        return price;
    }
}
