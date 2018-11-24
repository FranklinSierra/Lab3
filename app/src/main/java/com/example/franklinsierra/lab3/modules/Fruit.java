package com.example.franklinsierra.lab3.modules;

public class Fruit {

    //atributos
    private String name;
    private String description;
    private int icon;
    private int imgBack;
    private int quantity;

    //atributos publicos
    public static final int minQuantity=0;
    public static final int maxQuantity=10;

    public Fruit(String name, String description, int icon, int imgBack, int quantity) {
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.imgBack = imgBack;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getImgBack() {
        return imgBack;
    }

    public void setImgBack(int imgBack) {
        this.imgBack = imgBack;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    //metodo de adicionar cantidad

    public void addQuantity(int addQuantity){
        if(this.quantity+addQuantity<=maxQuantity){
            this.quantity=quantity+addQuantity;
        }
    }

    //metodo de reestablecer quantity a cero

    public void restartQuantity() {
        this.quantity=minQuantity;
    }


}
