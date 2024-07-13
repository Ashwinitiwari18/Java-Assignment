package model;

import config.ItemType;

import static config.constant.RAW_AND_MANUFACTURE_TAX;

public class ItemTypeManufactured extends Item{

    private double calculatedFinalPrice;

    ItemTypeManufactured() {
        super();
    }

    ItemTypeManufactured(String name, ItemType type, int price) {
        super(name, type, price);
    }

    @Override
    Item calculateFinalPrice() {
        double priceAfterTax = price + price * tax;
        double taxAfterImport = priceAfterTax * 0.02 ;
        calculatedFinalPrice = priceAfterTax +taxAfterImport;
        setFinalPrice();
        return this;
    }

    @Override
    public Item setType() {
        this.type = ItemType.MANUFACTURED;
        return this;
    }

    @Override
    public Item setTax() {
        tax = RAW_AND_MANUFACTURE_TAX;
        return this;
    }

    @Override
    public void setFinalPrice() {
        finalPrice = calculatedFinalPrice;
    }
}
