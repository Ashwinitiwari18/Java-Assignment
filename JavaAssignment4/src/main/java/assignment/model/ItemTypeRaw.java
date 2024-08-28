package assignment.model;

import static assignment.config.Constant.RAW_AND_MANUFACTURE_TAX;

import assignment.config.ItemType;

public class ItemTypeRaw extends Item {

  private double calculatedFinalPrice;

  public ItemTypeRaw() {
    super();
  }

  public ItemTypeRaw(String name, ItemType type, int price) {
    super(name, type, price);
  }

  @Override
  public Item calculateFinalPrice() {
    calculatedFinalPrice = price + price * tax;
    setFinalPrice();
    return this;
  }

  @Override
  public Item setType() {
    type = ItemType.RAW;
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
