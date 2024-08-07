package assignment.async;

import assignment.model.Item;
import assignment.model.ItemTypeImported;
import assignment.model.ItemTypeManufactured;
import assignment.model.ItemTypeRaw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

  BlockingQueue<Item> items;
  Connection connection = null;
  Statement statement = null;
  Map<String, Item> itemMap = new HashMap<>();

  /**
   * Producer class responsible for reading items from the database
   * and putting them into the BlockingQueue.
   */
  public Producer(BlockingQueue<Item> items) {
    this.items = items;
    itemMap.put("raw", new ItemTypeRaw());
    itemMap.put("imported", new ItemTypeImported());
    itemMap.put("manufactured", new ItemTypeManufactured());
  }

  @Override
  public void run() {
    try {
      connection = DriverManager.getConnection("jdbc:sqlite:myDB.db");
      statement = connection.createStatement();
      String selectSql = "SELECT * FROM Items";
      ResultSet resultSet = statement.executeQuery(selectSql);
      while (resultSet.next()) {
        Item item = itemMap
            .get(resultSet.getString("type").toLowerCase())
            .setName(resultSet.getString("name"))
            .setType()
            .setPrice(resultSet.getInt("price"))
            .setQuantity(resultSet.getInt("quantity"));
        if (item != null) {
          System.out.println("item enter");
          items.put(item);
          Thread.sleep(1000);
        }
      }
      items.put(new PoisonPill());
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (items.isEmpty()) {
        try {
          if (statement != null) {
            statement.close();
          }
          if (connection != null) {
            connection.close();
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }
}
