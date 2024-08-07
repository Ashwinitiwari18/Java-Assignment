package assignment;

import assignment.async.Consumer;
import assignment.async.PoisonPill;
import assignment.async.Producer;
import assignment.model.Item;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MyDataBase {

  BlockingQueue<Item> items = new LinkedBlockingQueue<>();
  Connection connection = null;
  Statement statement = null;

  /**
   * Inserts an item into the database.
   *
   * @param item The item to be inserted into the database.
   */
  public void insertInDb(Item item) {
    try {
      connection = DriverManager.getConnection("jdbc:sqlite:myDB.db");
      statement = connection.createStatement();
      // Create table SQL statement
      String createTableSql = "CREATE TABLE IF NOT EXISTS Items ("
          + "name TEXT NOT NULL, "
          + "type TEXT NOT NULL, "
          + "quantity INTEGER, "
          + "price INTEGER)";
      statement.execute(createTableSql);
      String insertSql = "INSERT INTO Items (name, type, quantity, price) VALUES (?, ?, ?, ?)";
      PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
      preparedStatement.setString(1,item.getName());
      preparedStatement.setString(2, item.getType().toString());
      preparedStatement.setInt(3, item.getQuantity());
      preparedStatement.setInt(4, item.getPrice());
      preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
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

  /**
   * Starts the producer and consumer threads to process the items from the database.
   * The producer reads items from the database and puts them into a BlockingQueue.
   * The consumer takes items from the BlockingQueue and processes them.
   * A PoisonPill is used to signal the consumer to stop after the producer is done.
   */
  public void getOutput() {
    Producer producer = new Producer(items);
    Consumer consumer = new Consumer(items);

    Thread producerThread = new Thread(producer);
    Thread consumerThread = new Thread(consumer);

    producerThread.start();
    consumerThread.start();

    try {
      producerThread.join();
      consumerThread.join();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
