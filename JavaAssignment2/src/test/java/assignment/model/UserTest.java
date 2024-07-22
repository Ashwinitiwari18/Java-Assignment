package assignment.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

  private User user;
  private File tempFile;

  @BeforeEach
  public void setUp() {
    user = new User();
    tempFile = new File("tempStudentData.dat");
  }

  @Test
  public void testAddUserDetails() {
    Student student = new Student().setFullName("John Doe").setAge(20).setAddress("123 Street").setRollNumber(1).setCourses(Set.of("A", "B", "C", "D"));
    user.addUserDetails(student);

    Set<Student> students = user.displayUserDetails("name");
    assertEquals(1, students.size());

    Student addedStudent = students.iterator().next();
    assertEquals("John Doe", addedStudent.getFullName());
    assertEquals(20, addedStudent.getAge());
    assertEquals("123 Street", addedStudent.getAddress());
    assertEquals(1, addedStudent.getRollNumber());
    assertEquals(new HashSet<>(Set.of("A", "B", "C", "D")), addedStudent.getCourses());
  }

  @Test
  public void testDeleteUserDetails() {
    Student student = new Student().setFullName("John Doe").setAge(20).setAddress("123 Street").setRollNumber(1).setCourses(Set.of("A", "B", "C", "D"));
    user.addUserDetails(student);

    user.deleteUserDetails(1);

    Set<Student> students = user.displayUserDetails("name");
    assertEquals(0, students.size());
  }

  @Test
  public void testSaveUserDetails() throws IOException {
    Student student = new Student().setFullName("John Doe").setAge(20).setAddress("123 Street").setRollNumber(1).setCourses(Set.of("A", "B", "C", "D"));
    user.addUserDetails(student);

    user.saveUserDetails();

    // Load the data from the file to verify
    User newUser = new User();
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("tempStudentData.dat"))) {
      newUser = (User) ois.readObject();
    } catch (Exception e) {
      fail("Exception occurred while reading from file");
    }

    Set<Student> students = newUser.displayUserDetails("name");
    assertEquals(1, students.size());

    Student savedStudent = students.iterator().next();
    assertEquals("John Doe", savedStudent.getFullName());
    assertEquals(20, savedStudent.getAge());
    assertEquals("123 Street", savedStudent.getAddress());
    assertEquals(1, savedStudent.getRollNumber());
    assertEquals(new HashSet<>(Set.of("A", "B", "C", "D")), savedStudent.getCourses());

    // Clean up
    if (tempFile.exists()) {
      tempFile.delete();
    }
  }
}
