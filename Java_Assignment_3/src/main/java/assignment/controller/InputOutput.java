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
   * Checks if a node with the given ID is present in the graph.
   *
   * @param id the ID of the node to check
   * @return true if the node is present, false otherwise
   */
  boolean checkIdPresent(String id) {
    boolean flag = true;
    if (!mapIdToNode.containsKey(id)) {
      flag = false;
      System.out.println("Id not present in graph");
    }
    return flag;
  }

  /**
   * Finds and prints the relation (parents or children) of a node.
   *
   * @param id the ID of the node
   * @param relationGetter a function to get the relation (parents or children) of the node
   * @param relationType the type of relation (e.g., "Parents" or "Children")
   */
  private void findRelation(String id,Function<Node,
      Set<Node>> relationGetter, String relationType) {
    operations.getRelations(mapIdToNode.get(id),relationGetter,relationType);
  }

  /**
   * Finds and prints the parents of a node.
   */
  public void findParents() {
    System.out.print("Enter Id of node to find Immediate Parents : ");
    String id = sc.nextLine();
    if (checkIdPresent(id)) {
      findRelation(id,Node::getParents,"Parents");
    }
  }

  /**
   * Finds and prints the children of a node.
   */
  public void findChildren() {
    System.out.print("Enter Id of node to find Immediate Children : ");
    String id = sc.nextLine();
    if (checkIdPresent(id)) {
      findRelation(id,Node::getChildren,"Children");
    }
  }

  /**
   * Finds and prints the ancestors or descendants of a node.
   *
   * @param id the ID of the node
   * @param relationGetter a function to get the relation (ancestors or descendants) of the node
   */
  private void findAncestorsOrDescendants(String id,Function<Node,
      Set<Node>> relationGetter) {
    operations.getAncestorsOrDescendants(mapIdToNode.get(id),relationGetter);
  }

  /**
   * Finds and prints the ancestors of a node.
   */
  public void findAncestors() {
    System.out.print("Enter Id of node to find Ancestors : ");
    String id = sc.nextLine();
    if (checkIdPresent(id)) {
      findAncestorsOrDescendants(id,Node::getParents);
    }
  }

  /**
   * Finds and prints the descendants of a node.
   */
  public void findDescendants() {
    System.out.print("Enter Id of node to find Descendants : ");
    String id = sc.nextLine();
    if (checkIdPresent(id)) {
      findAncestorsOrDescendants(id,Node::getChildren);
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
    if (checkIdPresent(parentId) && checkIdPresent(childId)) {
      operations.deleteDependency(mapIdToNode.get(parentId), mapIdToNode.get(childId));
      System.out.println("Dependency deleted successfully");
    }
  }

  /**
   * Deletes a node from the graph.
   */
  public void deleteANode() {
    System.out.print("Enter Id of node to delete : ");
    String id = sc.nextLine();
    if (checkIdPresent(id)) {
      operations.deleteANode(mapIdToNode.get(id));
      System.out.println("Node deleted successfully");
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
    if (checkIdPresent(parentId) && checkIdPresent(childId)) {
      operations.addNewDependency(mapIdToNode.get(parentId), mapIdToNode.get(childId));
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
      addInfo(newNode);
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
