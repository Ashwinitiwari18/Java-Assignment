package assignment.controller;

import assignment.model.Student;
import assignment.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class InputOutputTest {

  private InputOutput inputOutput;
  private User user;
  private ByteArrayOutputStream outputStream;
  private PrintStream originalOut;

  @BeforeEach
  public void setUp() {
    inputOutput = new InputOutput();
    user = new User();
    outputStream = new ByteArrayOutputStream();
    originalOut = System.out;
    System.setOut(new PrintStream(outputStream));
  }

  @Test
  public void testAddStudent() {
    String simulatedInput = "John Doe\n20\n123 Street\n1\nA B C D\n";
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

    inputOutput.addStudent(user);

    Set<Student> students = user.displayUserDetails("name");
    assertEquals(1, students.size());

    Student student = students.iterator().next();
    assertEquals("John Doe", student.getFullName());
    assertEquals(20, student.getAge());
    assertEquals("123 Street", student.getAddress());
    assertEquals(1, student.getRollNumber());
    assertEquals(new HashSet<>(Set.of("A", "B", "C", "D")), student.getCourses());
  }

  @Test
  public void testDisplayUserData() {
    Student student = new Student().setFullName("John Doe").setAge(20).setAddress("123 Street").setRollNumber(1).setCourses(Set.of("A", "B", "C", "D"));
    user.addUserDetails(student);
    String simulatedInput = "name\n";
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

    inputOutput.displayUserData(user);

    String output = outputStream.toString();
    assertTrue(output.contains("John Doe"));
    assertTrue(output.contains("20"));
    assertTrue(output.contains("123 Street"));
    assertTrue(output.contains("A, B, C, D"));
  }

  @Test
  public void testDeleteUserData() {
    Student student = new Student()
        .setFullName("John Doe")
        .setAge(20)
        .setAddress("123 Street")
        .setRollNumber(1)
        .setCourses(Set.of("A", "B", "C", "D"));
    user.addUserDetails(student);

    final String[] simulatedInput = {"1"};
    final String input = String.join("\n", simulatedInput);
    System.setIn(new ByteArrayInputStream(input.getBytes()));

    inputOutput.deleteUserData(user);

//    Set<Student> students = user.displayUserDetails("name");
    assertEquals(0, user.getUserSetSize());
  }

  @Test
  public void testExit() {
    Student student = new Student().setFullName("John Doe").setAge(20).setAddress("123 Street").setRollNumber(1).setCourses(Set.of("A", "B", "C", "D"));
    user.addUserDetails(student);

    String simulatedInput = "y\n";
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

    assertDoesNotThrow(() -> inputOutput.exit(user));
  }
}
