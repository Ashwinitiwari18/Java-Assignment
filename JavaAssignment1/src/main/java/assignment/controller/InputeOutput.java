package assignment.controller;

import assignment.model.Item;

import java.util.ArrayList;

public interface InputeOutput {
  Item input(String[] arg);

  void output(ArrayList<Item> items);
}
