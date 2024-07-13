package model;

import config.ItemType;

public abstract class Item{
    String name;
    ItemType type;
    int price;
    int quantity;
    double tax;
    double finalPrice;

    Item(){
        name = null;
        type = ItemType.NOT_ASSIGN;
        price = 0;
    }

    Item(String name, ItemType type, int price){
        this.name=name;
        this.type=type;
        this.price=price;
    }

    abstract Item calculateFinalPrice();
    public Item setName(String name) {
        this.name=name;
        return this;
    }
    public abstract Item setType();

    public Item setPrice(int price) {
        this.price=price;
        return this;
    }

    public Item setQuantity(int quantity) {
        this.quantity=quantity;
        return this;
    }

    public abstract Item setTax();
    public abstract void setFinalPrice();

    public String getName() {
        return name;
    }

    public ItemType getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTax() {
        return tax;
    }

    public double getFinalPrice() {
        return finalPrice;
    }
}
