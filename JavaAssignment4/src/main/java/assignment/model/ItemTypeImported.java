package assignment.model;

import assignment.config.Constant;
import assignment.config.ItemType;

public class ItemTypeImported extends Item {

  private double calculatedFinalPrice;

  public ItemTypeImported() {
    super();
  }

  public ItemTypeImported(String name, ItemType type, int price) {
    super(name, type, price);
  }

  @Override
  public Item calculateFinalPrice() {
    double priceAfterImport = price + price * tax;

    if (priceAfterImport <= 100) {
      calculatedFinalPrice = priceAfterImport + Constant.SURCHARGE_UPTO_100;
    } else if (priceAfterImport <= 200) {
      calculatedFinalPrice = priceAfterImport + Constant.SURCHARGE_BETWEEN_100_TO_200;
    } else {
      calculatedFinalPrice = priceAfterImport
                              + priceAfterImport * Constant.SURCHARGE_PERCENT_AFTER_200;
    }
    setFinalPrice();
    return this;
  }

  @Override
  public Item setType() {
    super.type = ItemType.IMPORTED;
    return this;
  }

  @Override
  public Item setTax() {
    tax = Constant.IMPORT_DUTY;
    return this;
  }

  @Override
  public void setFinalPrice() {
    finalPrice = calculatedFinalPrice;
  }
}
