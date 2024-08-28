package assignment.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)

public class Node {
  private int id;
  private String name;
  private Map<String,String> information;
  private Set<Node> parents;
  private Set<Node> children;

  Node() {
    this.id = 0;
    this.name = "";
    information = new HashMap<>();
    parents = new HashSet<>();
    children = new HashSet<>();
  }

  /**
   * Constructor initializing the node with specified id and name.
   *
   * @param id   the id of the node
   * @param name the name of the node
   */
  public Node(int id, String name) {
    this.id = id;
    this.name = name;
    information = new HashMap<>();
    parents = new HashSet<>();
    children = new HashSet<>();
  }

  /**
   * Adds a key-value pair to the node's information map.
   *
   * @param key   the key to be added
   * @param value the value to be added
   */
  public void addInformation(String key,String value) {
    if (information.containsKey(key)) {
      System.out.println("Key already exist");
    } else {
      information.put(key,value);
      System.out.println("Successfully key value pair added");
    }
  }

  /**
   * Adds a child node to this node.
   *
   * @param child the child node to be added
   */
  public void addChild(Node child) {
    children.add(child);
  }

  /**
   * Adds a parent node to this node.
   *
   * @param parent the parent node to be added
   */
  public void addParent(Node parent) {
    parents.add(parent);
  }

  /**
   * Removes a child node from this node.
   *
   * @param child the child node to be removed
   */
  public void removeChild(Node child) {
    children.remove(child);
  }

  /**
   * Removes a parent node from this node.
   *
   * @param parent the parent node to be removed
   */
  public void removeParent(Node parent) {
    parents.remove(parent);
  }

  /**
   * Prints the name and id of the node.
   *
   * @param printInformation if true, also prints the node's information
   */
  public void printNameId(boolean printInformation) {
    System.out.println("Name of node " + name + " (" + id + ") : ");

    if (printInformation) {
      printInformation();
    }
  }

  /**
   * Prints the information of the node.
   */
  public void printInformation() {
    System.out.println("Information for node " + name + " (" + id + ") : ");
    for (Map.Entry<String, String> entry : information.entrySet()) {
      System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
    }
  }
}
