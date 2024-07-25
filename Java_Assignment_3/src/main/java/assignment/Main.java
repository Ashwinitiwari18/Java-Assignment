package assignment;

import assignment.controller.MenuOption;

public class Main {

  /**
   * The main method which runs the application.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    MenuOption menuOption = new MenuOption();
    while (true) {
      menuOption.callInputOutput(menuOption.showMenu());
    }
  }
}