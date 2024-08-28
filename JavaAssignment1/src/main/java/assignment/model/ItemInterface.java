package assignment.model;

import assignment.config.ItemType;

public interface ItemInterface {
  Item setName(String name);

  Item setPrice(int price);

  Item setQuantity(int quantity);

  String getName();

  ItemType getType();

  int getPrice();

  int getQuantity();

  double getTax();

  double getFinalPrice();
}
