package assignment.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class
StudentTest {

  private Student student;

  @BeforeEach
  public void setUp() {
    student = new Student();
  }

  @Test
  public void testDefaultConstructor() {
    assertEquals("", student.getFullName());
    assertEquals(0, student.getAge());
    assertEquals("", student.getAddress());
    assertEquals(0, student.getRollNumber());
    assertTrue(student.getCourses().isEmpty());
  }

  @Test
  public void testSetAndGetFullName() {
    student.setFullName("John Doe");
    assertEquals("John Doe", student.getFullName());
  }

  @Test
  public void testSetAndGetAge() {
    student.setAge(25);
    assertEquals(25, student.getAge());
  }

  @Test
  public void testSetAndGetAddress() {
    student.setAddress("123 Main St");
    assertEquals("123 Main St", student.getAddress());
  }

  @Test
  public void testSetAndGetRollNumber() {
    student.setRollNumber(101);
    assertEquals(101, student.getRollNumber());
  }

  @Test
  public void testSetAndGetCourses() {
    Set<String> courses = new HashSet<>();
    courses.add("Math");
    courses.add("Science");
    student.setCourses(courses);
    assertEquals(courses, student.getCourses());
  }
}
