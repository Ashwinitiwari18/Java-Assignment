package assignment.controller;

import assignment.model.Node;
import java.util.Scanner;

public class MenuOption {
  private final InputOutput inout;

  public MenuOption() {
    inout = new InputOutput();
  }

  /**
   * Displays the menu options to the user.
   *
   * @return the user's menu choice
   */
  public String showMenu() {
    System.out.println("Menu");
    System.out.println("1. Get Immediate Parents");
    System.out.println("2. Get Immediate Children");
    System.out.println("3. Get Ancestors");
    System.out.println("4. Get Descendants");
    System.out.println("5. Delete Dependency");
    System.out.println("6. Delete Node");
    System.out.println("7. Add Dependency");
    System.out.println("8. Add Node");
    System.out.print("Enter the type of operation you want to perform : ");
    Scanner sc = new Scanner(System.in);
    return sc.nextLine();
  }

  /**
   * Calls the appropriate method in the InputOutput class based on the user's menu selection.
   *
   * @param option the user's menu selection as a String.
   *               The options are:
   *               <ul>
   *                   <li>"1" - Find Immediate Parents</li>
   *                   <li>"2" - Find Immediate Children</li>
   *                   <li>"3" - Find Ancestors</li>
   *                   <li>"4" - Find Descendants</li>
   *                   <li>"5" - Delete Dependency</li>
   *                   <li>"6" - Delete Node</li>
   *                   <li>"7" - Add Dependency</li>
   *                   <li>"8" - Add Node</li>
   *               </ul>
   */
  public void callInputOutput(String option) {
    switch (option) {
      case "1":
        inout.findRelation(Node::getParents, "Immediate Parents");
        break;
      case "2":
        inout.findRelation(Node::getChildren, "Immediate Children");
        break;
      case "3":
        inout.findAncestorsOrDescendants(Node::getParents, "ancestors");
        break;
      case "4":
        inout.findAncestorsOrDescendants(Node::getChildren, "descendants");
        break;
      case "5":
        inout.deleteDependency();
        break;
      case "6":
        inout.deleteANode();
        break;
      case "7":
        inout.addNewDependency();
        break;
      case "8":
        inout.addNode();
        break;
      default :
        System.exit(0);
    }
  }
}
