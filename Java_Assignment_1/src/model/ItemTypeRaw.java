package model;

import config.ItemType;

import static config.constant.RAW_AND_MANUFACTURE_TAX;

public class ItemTypeRaw extends Item{

    private double calculatedFinalPrice;

    ItemTypeRaw() {
        super();
    }

    ItemTypeRaw(String name, ItemType type, int price) {
        super(name, type, price);
    }

    @Override
    Item calculateFinalPrice() {
        calculatedFinalPrice = price + price * tax;
        setFinalPrice();
        return this;
    }

    @Override
    public Item setType() {
        type=ItemType.RAW;
        return this;
    }

    @Override
    public Item setTax() {
        tax= RAW_AND_MANUFACTURE_TAX;
        return this;
    }

    @Override
    public void setFinalPrice() {
        finalPrice=calculatedFinalPrice;
    }
}
