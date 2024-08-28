package assignment.async;

import assignment.model.Item;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

  BlockingQueue<Item> items;
  Connection connection = null;
  Statement statement = null;

  public Consumer(BlockingQueue<Item> items) {
    this.items = items;
  }

  @Override
  public void run() {
    System.out.println("Here are your items: ");
    try {
      connection = DriverManager.getConnection("jdbc:sqlite:myDB.db");
      statement = connection.createStatement();
      try {
        while (true) {
          Item item = items.take();
          if (item instanceof PoisonPill) {
            return;
          }
          item.setTax().calculateFinalPrice();
          System.out.println("Name: " + item.getName() + "  ");
          System.out.println("Price: " + item.getPrice() + "  ");
          System.out.println("Type: " + item.getType() + "  ");
          System.out.println("Quantity: " + item.getQuantity() + "  ");
          System.out.println("Tax: " + item.getTax() + "  ");
          System.out.println("Final Price: " + item.getFinalPrice() + "  ");
          System.out.println("item read");
          System.out.println("------------------------");
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
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
    System.out.println("Thank you for using our service");
  }
}
