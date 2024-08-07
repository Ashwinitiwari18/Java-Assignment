package assignment;

import assignment.controller.InputOutput;

public class Main {

  /**
   * The main method is the entry point of the application.
   * It creates an instance of InputOutput and runs a loop to take user input for items.
   * The loop continues to take input until the user chooses to stop.
   * After taking input, it outputs the processed item details.
   *
   * @param args Command line arguments (not used).
   */
  public static void main(String[] args) {
    InputOutput inOut = new InputOutput();
    int i = 0;
    while (i++ == 0 || inOut.takeInput().equalsIgnoreCase("y")){}
    inOut.output();
  }
}
