package assignment.model;

import assignment.config.ItemType;

public abstract class Item implements ItemInterface {

  String name;
  ItemType type;
  int price;
  int quantity;
  double tax;
  double finalPrice;

  /**
   * Constructs a new Item with default values.
   * The default values are:
   * - name: null
   * - type: ItemType.NOT_ASSIGN
   * - price: 0
   */
  public Item() {
    name = null;
    type = ItemType.NOT_ASSIGN;
    price = 0;
  }

  /**
   * Constructs a new Item with the specified name, type, and price.
   *
   * @param name  The name of the item.
   * @param type  The type of the item
   *              (e.g., ItemType.RAW, ItemType.MANUFACTURED, ItemType.IMPORTED).
   * @param price The price of the item.
   */
  public Item(String name, ItemType type, int price) {
    this.name = name;
    this.type = type;
    this.price = price;
  }

  public abstract Item calculateFinalPrice();

  @Override
  public Item setName(String name) {
    this.name = name;
    return this;
  }

  public abstract Item setType();

  @Override
  public Item setPrice(int price) {
    this.price = price;
    return this;
  }

  @Override
  public Item setQuantity(int quantity) {
    this.quantity = quantity;
    return this;
  }

  public abstract Item setTax();

  public abstract void setFinalPrice();

  @Override
  public String getName() {
    return name;
  }

  @Override
  public ItemType getType() {
    return type;
  }

  @Override
  public int getPrice() {
    return price;
  }

  @Override
  public int getQuantity() {
    return quantity;
  }

  @Override
  public double getTax() {
    return tax;
  }

  @Override
  public double getFinalPrice() {
    return finalPrice;
  }
}
