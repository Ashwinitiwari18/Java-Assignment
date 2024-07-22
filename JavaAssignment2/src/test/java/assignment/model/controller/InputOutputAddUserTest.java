package assignment.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class InputOutputAddUserTest {

  private InputOutputAddUser addUser;

  @BeforeEach
  public void setUp() {
    addUser = new InputOutputAddUser();
  }

  @Test
  public void testTakeFullName() {
    String simulatedInput = "John Doe\n";
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

    String fullName = addUser.takeFullName();
    assertEquals("John Doe", fullName);
  }

  @Test
  public void testTakeAge() {
    String simulatedInput = "20\n";
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

    int age = addUser.takeAge();
    assertEquals(20, age);
  }

  @Test
  public void testTakeAddress() {
    String simulatedInput = "123 Street\n";
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

    String address = addUser.takeAddress();
    assertEquals("123 Street", address);
  }

  @Test
  public void testTakeRollNumber() {
    String simulatedInput = "1\n";
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

    int rollNumber = addUser.takeRollNumber();
    assertEquals(1, rollNumber);
  }

  @Test
  public void testTakeCourses() {
    String simulatedInput = "A B C D\n";
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

    Set<String> courses = addUser.takeCourses();
    assertEquals(new HashSet<>(Set.of("A", "B", "C", "D")), courses);
  }
}
