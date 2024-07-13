package model;

import config.ItemType;

import static config.constant.*;

public class ItemTypeImported extends Item{

    private double calculatedFinalPrice;

    ItemTypeImported() {
        super();
    }

    ItemTypeImported(String name, ItemType type, int price) {
        super(name, type, price);
    }

    @Override
    Item calculateFinalPrice() {
        double priceAfterImport = price + price * tax;

        if(priceAfterImport <= 100){
            calculatedFinalPrice = priceAfterImport + SURCHARGE_UPTO_100;
        }else if( priceAfterImport<=200){
            calculatedFinalPrice = priceAfterImport + SURCHARGE_BETWEEN_100_TO_200;
        }else{
            calculatedFinalPrice = priceAfterImport + priceAfterImport * SURCHARGE_PERCENT_AFTER_200;
        }
        setFinalPrice();
        return this;
    }

    @Override
    public Item setType() {
        super.type=ItemType.IMPORTED;
        return this;
    }

    @Override
    public Item setTax() {
        tax=IMPORT_DUTY;
        return this;
    }

    @Override
    public void setFinalPrice() {
        finalPrice = calculatedFinalPrice;
    }
}
