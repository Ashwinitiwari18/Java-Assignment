package assignment.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.function.Function;

public class GraphOperations {

  /**
   * Prints the relations of a node (immediate parents or children).
   *
   * @param node           the node whose relations are to be printed
   * @param relationGetter a function that gets the relations of the node
   * @param relationType   the type of relation (parents or children)
   */
  public void getRelations(Node node, Function<Node,
      Set<Node>> relationGetter, String relationType) {
    System.out.println(relationType + " of node " + node.getName() + " (" + node.getId() + ") :");
    for (Node relation : relationGetter.apply(node)) {
      relation.printNameId(false);
    }
  }

  /**
   * Prints all ancestors or descendants of a node.
   *
   * @param node           the node whose ancestors or descendants are to be printed
   * @param relationGetter a function that gets the ancestors or descendants of the node
   */
  public void getAncestorsOrDescendants(Node node,Function<Node,Set<Node>> relationGetter) {
    Queue<Node> queue = new LinkedList<>();
    Set<Integer> visited = new HashSet<>();
    queue.add(node);
    while (!queue.isEmpty()) {
      Node currentNode = queue.poll();
      visited.add(currentNode.getId());
      for (Node relation : relationGetter.apply(currentNode)) {
        if (!visited.contains(relation.getId())) {
          queue.add(relation);
          visited.add(relation.getId());
          relation.printNameId(false);
        }
      }
    }
  }

  /**
   * Deletes a parent-child dependency between two nodes.
   *
   * @param parent the parent node
   * @param child  the child node
   */
  public void deleteDependency(Node parent,Node child) {
    Set<Node> childrenOfParent = parent.getChildren();
    Set<Node> parentOfChildren = child.getParents();
    if (childrenOfParent.contains(child) && parentOfChildren.contains(parent)) {
      childrenOfParent.remove(child);
      parentOfChildren.remove(parent);
      System.out.println("Parent child relationship between "
          + "these two node has been deleted successfully");
    } else {
      System.out.println("There is no parent-child relationship between these two node");
    }
  }

  /**
   * Deletes a node from the graph, removing it from its parents' and children's sets.
   *
   * @param node the node to be deleted
   */
  public void deleteANode(Node node) {
    Set<Node> parents = node.getParents();
    for (Node parent : parents) {
      parent.removeChild(node);
    }
    Set<Node> children = node.getChildren();
    for (Node child : children) {
      child.removeParent(node);
    }
    parents.clear();
    children.clear();
  }

  /**
   * Checks if adding a new dependency creates a cycle.
   *
   * @param parent the parent node
   * @param child  the child node
   * @return true if a cycle is present, false otherwise
   */
  private boolean isCyclePresent(Node parent, Node child) {
    Queue<Node> queue = new LinkedList<>();
    Set<Integer> visited = new HashSet<>();
    queue.add(parent);
    visited.add(parent.getId());
    while (!queue.isEmpty()) {
      Node currentNode = queue.poll();
      for (Node temp : currentNode.getChildren()) {
        if (child.getId()==temp.getId()) {
          System.out.println(child.getId());
          return true;
        }

        if (!visited.contains(temp.getId())) {
          visited.add(temp.getId());
          queue.add(temp);
        }
      }
    }
    return false;
  }

  /**
   * Adds a new dependency between two nodes. If adding the dependency creates a cycle,
   * the dependency is removed.
   *
   * @param parent the parent node
   * @param child  the child node
   */
  public void addNewDependency(Node parent, Node child) {
    parent.addChild(child);
    child.addParent(parent);
    if (isCyclePresent(child,parent)) {
      System.out.println("If we add this edge then cycle will form.");
      parent.removeChild(child);
      child.removeParent(parent);
    } else {
      System.out.println("New Dependency added");
    }
  }
}