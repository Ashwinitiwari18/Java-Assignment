package assignment;

import assignment.controller.InputOutput;
import assignment.model.Student;
import assignment.model.User;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
  private User user;
  private InputOutput io;

  @BeforeEach
  void setUp() {
    user = new User();
    io = new InputOutput();
  }

  @Test
  void testAddStudent() {
    // Simulate user input
    String simulatedInput = "John Doe\n20\n123 Main St\n1\nA B C D\n";
    InputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
    System.setIn(in);

    io.addStudent(user);

    List<Student> students = user.displayUserDetails("name","1");
    assertEquals(1, students.size());
    Student student = students.iterator().next();
    assertEquals("John Doe", student.getFullName());
    assertEquals(20, student.getAge());
    assertEquals("123 Main St", student.getAddress());
    assertEquals(1, student.getRollNumber());
    assertTrue(student.getCourses().contains("A"));
    assertTrue(student.getCourses().contains("B"));
    assertTrue(student.getCourses().contains("C"));
    assertTrue(student.getCourses().contains("D"));
  }

//  @Test
  void testDisplayUserData() {
    // Add a student directly to user
    Student student = new Student().setFullName("John Doe")
        .setAge(20)
        .setAddress("123 Main St")
        .setRollNumber(1)
        .setCourses(Set.of("A", "B", "C", "D"));
    user.addUserDetails(student);

    // Simulate user input for display
    String simulatedInput = "name\n";
    InputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
    System.setIn(in);

    // Redirect System.out to a ByteArrayOutputStream to capture the output
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outputStream));

    // Display user data
    io.displayUserData(user);

    // Restore original System.out
    System.setOut(originalOut);

    // Verify output
    String output = outputStream.toString();
    assertTrue(output.contains("John Doe"));
    assertTrue(output.contains("20"));
    assertTrue(output.contains("123 Main St"));
    assertTrue(output.contains("1"));
  }

  @Test
  void testDeleteUserData() {
    // Add a student directly to user
    Student student = new Student().setFullName("John Doe")
        .setAge(20)
        .setAddress("123 Main St")
        .setRollNumber(1)
        .setCourses(Set.of("A", "B", "C", "D"));
    user.addUserDetails(student);

    // Simulate user input for deletion
    String simulatedInput = "1\n";
    InputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
    System.setIn(in);

    // Delete student
    io.deleteUserData(user);

    // Verify student deleted
    List<Student> students = user.displayUserDetails("name","1");
    assertTrue(students.isEmpty());
  }

  @Test
  void testSaveAndLoadUserDetails() {
    // Add a student directly to user
    Student student = new Student().setFullName("John Doe")
        .setAge(20)
        .setAddress("123 Main St")
        .setRollNumber(1)
        .setCourses(Set.of("A", "B", "C", "D"));
    user.addUserDetails(student);

    // Save user details
    user.saveUserDetails();

    // Load user details
    User loadedUser = new User().getPreviousData();

    // Verify loaded user details
    assertNotNull(loadedUser);
    List<Student> students = loadedUser.displayUserDetails("name","1");
    assertEquals(1, students.size());
    Student loadedStudent = students.iterator().next();
    assertEquals("John Doe", loadedStudent.getFullName());
    assertEquals(20, loadedStudent.getAge());
    assertEquals("123 Main St", loadedStudent.getAddress());
    assertEquals(1, loadedStudent.getRollNumber());
    assertTrue(loadedStudent.getCourses().contains("A"));
    assertTrue(loadedStudent.getCourses().contains("B"));
    assertTrue(loadedStudent.getCourses().contains("C"));
    assertTrue(loadedStudent.getCourses().contains("D"));
  }
}



/*
private InputOutput inputOutput;
  private User user;
  private Student student;

  @BeforeEach
  public void setUp() {
    inputOutput = new InputOutput();
    user = new User();
    student = new Student().setFullName("Alice").setAge(20).setAddress("123 Street").setRollNumber(1);
    user.addUserDetails(student);
  }

*  public void testDisplayUserData() {
    String input = "name\r\n";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    inputOutput.displayUserData(user);
  } */