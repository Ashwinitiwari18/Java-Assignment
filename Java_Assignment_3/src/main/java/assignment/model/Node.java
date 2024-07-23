package assignment.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Map;

@Getter
@Setter
@Accessors(chain = true)

public class Node {
  private int id;
  private String name;
  private Map<String,String> information;

  Node(int id,String name) {
    this.id = id;
    this.name = name;
  }

  public void addInformation(String key,String value) {
    if (information.containsKey(key)) {
      System.out.println("Key already exist");
    }else {
      information.put(key,value);
      System.out.println("Successfully key value pair added");
    }
  }
}
