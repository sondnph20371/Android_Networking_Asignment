package com.example.asignment_application.model;

public class Sach {
    private String _id, name;
    private String price;

    public Sach() {
    }

    public Sach(String id, String name, String price) {
        this._id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
