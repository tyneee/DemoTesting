/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tblcar;

import java.io.Serializable;

/**
 *
 * @author USER
 */
public class TblCarDTO implements Serializable {

    private int carID;
    private String carName;//
    private String color;//
    private int yearOfCreate;//
    private int categoryID;
    private float price;//
    private int quantityOfRemain;//
    private String image;//
    private String description;//
    private String status;
    private int rate;
    private String pickUpDate;
    private String returnDate;

    public TblCarDTO() {
    }

    public TblCarDTO(int carID, String carName, String color, int yearOfCreate, int categoryID, float price, int quantityOfRemain, String image, String description, String status) {
        this.carID = carID;
        this.carName = carName;
        this.color = color;
        this.yearOfCreate = yearOfCreate;
        this.categoryID = categoryID;
        this.price = price;
        this.quantityOfRemain = quantityOfRemain;
        this.image = image;
        this.description = description;
        this.status = status;
    }

    public TblCarDTO(int carID, String carName, String color, int yearOfCreate, int categoryID, float price, int quantityOfRemain, String image, String description, String status, int rate) {
        this.carID = carID;
        this.carName = carName;
        this.color = color;
        this.yearOfCreate = yearOfCreate;
        this.categoryID = categoryID;
        this.price = price;
        this.quantityOfRemain = quantityOfRemain;
        this.image = image;
        this.description = description;
        this.status = status;
        this.rate = rate;
    }

    public TblCarDTO(int carID, String carName, int categoryID, float price, String image, String pickUpDate, String returnDate) {
        this.carID = carID;
        this.carName = carName;
        this.categoryID = categoryID;
        this.price = price;
        this.image = image;
        this.pickUpDate = pickUpDate;
        this.returnDate = returnDate;

    }

    public TblCarDTO(int carID, String carName, String color, int category, String description, String image, int year, float price, int quantity) {
        this.carID = carID;
        this.carName = carName;
        this.color = color;
        this.categoryID = category;
        this.description = description;
        this.image = image;
        this.yearOfCreate = year;
        this.price = price;
        this.quantityOfRemain = quantity;
  
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYearOfCreate() {
        return yearOfCreate;
    }

    public void setYearOfCreate(int yearOfCreate) {
        this.yearOfCreate = yearOfCreate;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantityOfRemain() {
        return quantityOfRemain;
    }

    public void setQuantityOfRemain(int quantityOfRemain) {
        this.quantityOfRemain = quantityOfRemain;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(String pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "TblCarDTO{" + "carID=" + carID + ", carName=" + carName + ", color=" + color + ", yearOfCreate=" + yearOfCreate + ", categoryID=" + categoryID + ", price=" + price + ", quantityOfRemain=" + quantityOfRemain + ", image=" + image + ", description=" + description + ", status=" + status + ", rate=" + rate + ", pickUpDate=" + pickUpDate + ", returnDate=" + returnDate + '}';
    }

}
