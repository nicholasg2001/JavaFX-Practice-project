/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.csc311.hw;


import com.google.gson.annotations.SerializedName;

/**
 *
 * @author ngoot
 */
public class BookInformation {
    @SerializedName("ID")
    private int ID;
    @SerializedName("Title")
    private String Title;
    @SerializedName("Price")
    private double Price;

    public int getID() {
        return ID;
    }

    public void setId(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    } 
}
