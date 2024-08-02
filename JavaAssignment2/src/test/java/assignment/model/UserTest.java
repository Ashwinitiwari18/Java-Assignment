package assignment.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

  private User user;
  private Student student1;
  private Student student2;

  @BeforeEach
  public void setUp() {
    user = new User();
    student1 = new Student().setFullName("Alice").setAge(20).setAddress("123 Street").setRollNumber(1);
    student2 = new Student().setFullName("Bob").setAge(21).setAddress("456 Avenue").setRollNumber(2);
  }

  @Test
  public void testAddUserDetails() {
    user.addUserDetails(student1);
//    assertEquals(1, user.getUserSetSize());
    user.addUserDetails(student2);
//    assertEquals(2, user.getUserSetSize());
  }

  @Test
  public void testAddDuplicateUserDetails() {
    user.addUserDetails(student1);
//    assertEquals(1, user.getUserSetSize());
    user.addUserDetails(student1);
//    assertEquals(1, user.getUserSetSize());
  }

  @Test
  public void testDisplayUserDetailsByName() {
    user.addUserDetails(student1);
    user.addUserDetails(student2);
    List<Student> studentsByName = user.displayUserDetails("name","1");
    assertEquals(2, studentsByName.size());
    assertTrue(studentsByName.contains(student1));
    assertTrue(studentsByName.contains(student2));
  }

  @Test
  public void testDisplayUserDetailsByAge() {
    user.addUserDetails(student1);
    user.addUserDetails(student2);
    List<Student> studentsByAge = user.displayUserDetails("age","1");
    assertEquals(2, studentsByAge.size());
    assertTrue(studentsByAge.contains(student1));
    assertTrue(studentsByAge.contains(student2));
  }

  @Test
  public void testDeleteUserDetails() {
    user.addUserDetails(student1);
    user.addUserDetails(student2);
//    assertEquals(2, user.getUserSetSize());
    user.deleteUserDetails(1);
//    assertEquals(1, user.getUserSetSize());
    assertFalse(user.displayUserDetails("name","1").contains(student1));
  }

  @Test
  public void testDeleteNonExistentUserDetails() {
    user.addUserDetails(student1);
//    assertEquals(1, user.getUserSetSize());
    user.deleteUserDetails(2);
//    assertEquals(1, user.getUserSetSize());
  }

//  @Test
  public void testSaveAndReadUserDetails() {
    user.addUserDetails(student1);
    user.addUserDetails(student2);
    user.saveUserDetails();

    User newUser = null;
    try (FileInputStream file = new FileInputStream("studentData.ser");
         ObjectInputStream in = new ObjectInputStream(file)) {
      newUser = (User) in.readObject();
    } catch (Exception e) {
      fail("Exception during deserialization: " + e.getMessage());
    }

    assertNotNull(newUser);
//    assertEquals(2, newUser.getUserSetSize());
    assertTrue(newUser.displayUserDetails("name","1").contains(student1));
    assertTrue(newUser.displayUserDetails("name","1").contains(student2));
  }
}
