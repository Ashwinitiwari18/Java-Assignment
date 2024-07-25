package assignment.controller;

import static assignment.config.Constant.LENGTH_OF_KEY_VALUE_PAIR;

import assignment.model.GraphOperations;
import assignment.model.Node;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Function;

public class InputOutput {
  private final Scanner sc;
  Map<String, Node> mapIdToNode;
  GraphOperations operations;

  InputOutput() {
    sc = new Scanner(System.in);
    mapIdToNode = new HashMap<>();
    operations = new GraphOperations();
  }

  /**
   * Finds and prints the relations (immediate parents or children) of a node.
   *
   * @param relationGetter a function that gets the relations of the node
   * @param relationType   the type of relation (parents or children)
   */
  public void findRelation(Function<Node, Set<Node>> relationGetter, String relationType) {
    System.out.print("Enter Id of node to find " + relationType + " : ");
    String id = sc.nextLine();
    if (mapIdToNode.containsKey(id)) {
      operations.getRelations(mapIdToNode.get(id),relationGetter,relationType);
    } else {
      System.out.println("Id not present in graph");
    }
  }

  /**
   * Finds and prints all ancestors or descendants of a node.
   *
   * @param relationGetter a function that gets the ancestors or descendants of the node
   * @param relationType   the type of relation (ancestors or descendants)
   */
  public void findAncestorsOrDescendants(Function<Node,
      Set<Node>> relationGetter,String relationType) {
    System.out.print("Enter Id of node to find " + relationType + " : ");
    String id = sc.nextLine();
    if (mapIdToNode.containsKey(id)) {
      operations.getAncestorsOrDescendants(mapIdToNode.get(id),relationGetter);
    } else {
      System.out.println("Id not present in graph");
    }
  }

  /**
   * Deletes the dependency between a parent node and a child node.
   */
  public void deleteDependency() {
    System.out.print("Enter Id of parent node : ");
    String parentId = sc.nextLine();
    System.out.print("Enter Id of child node : ");
    String childId = sc.nextLine();
    if (mapIdToNode.containsKey(parentId) && mapIdToNode.containsKey(childId)) {
      operations.deleteDependency(mapIdToNode.get(parentId), mapIdToNode.get(childId));
      System.out.println("Dependency deleted successfully");
    } else {
      System.out.println("Ids not present in graph");
    }
  }

  /**
   * Deletes a node from the graph.
   */
  public void deleteANode() {
    System.out.print("Enter Id of node to delete : ");
    String id = sc.nextLine();
    if (mapIdToNode.containsKey(id)) {
      operations.deleteANode(mapIdToNode.get(id));
      System.out.println("Node deleted successfully");
    } else {
      System.out.println("Id not present in graph");
    }
  }

  /**
   * Adds a new dependency between a parent node and a child node.
   */
  public void addNewDependency() {
    System.out.print("Enter Id of parent node : ");
    String parentId = sc.nextLine();
    System.out.print("Enter Id of child node : ");
    String childId = sc.nextLine();
    if (mapIdToNode.containsKey(parentId) && mapIdToNode.containsKey(childId)) {
      operations.addNewDependency(mapIdToNode.get(parentId), mapIdToNode.get(childId));
      System.out.println("New Dependency added");
    } else {
      System.out.println("Ids not present in graph");
    }
  }

  /**
   * Adds a new node to the graph.
   */
  public void addNode() {
    try {
      System.out.print("Enter Id of node : ");
      int id = Integer.parseInt(sc.nextLine());
      System.out.print("Enter name of Node : ");
      String name = sc.nextLine();
      Node newNode = new Node(id,name);
      //  addInfo(newNode);
      mapIdToNode.put(String.valueOf(id),newNode);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  /**
   * Adds information (key-value pairs) to a node.
   *
   * @param node the node to which information will be added
   */
  private void addInfo(Node node) {
    try {
      System.out.println("Enter number of key-value pair to store : ");
      int count = Integer.parseInt(sc.nextLine());
      System.out.println("Enter Key-value pairs one-by-one.");
      for (int i = 0; i < count; i++) {
        String input = sc.nextLine();
        String[] keyValue = input.split(" ");
        if (keyValue.length == LENGTH_OF_KEY_VALUE_PAIR) {
          node.addInformation(keyValue[0], keyValue[1]);
        } else {
          System.out.println("Please Enter input in correct pair.");
          i--;
        }
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
