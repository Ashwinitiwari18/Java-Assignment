package assignment.model;

import assignment.config.Constant;
import assignment.config.ItemType;

public class ItemTypeManufactured extends Item {

  private double calculatedFinalPrice;

  public ItemTypeManufactured() {
    super();
  }

  public ItemTypeManufactured(String name, ItemType type, int price) {
    super(name, type, price);
  }

  @Override
  public Item calculateFinalPrice() {
    double priceAfterTax = price + price * tax;
    double taxAfterImport = priceAfterTax * 0.02;
    calculatedFinalPrice = priceAfterTax + taxAfterImport;
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
    tax = Constant.RAW_AND_MANUFACTURE_TAX;
    return this;
  }

  @Override
  public void setFinalPrice() {
    finalPrice = calculatedFinalPrice;
  }
}
