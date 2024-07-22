package assignment.comparator;

import assignment.model.Student;

import java.util.Comparator;

public class StudentComparatorByAddress implements Comparator<Student> {
  @Override
  public int compare(Student s1, Student s2) {
    return s1.getAddress().compareTo(s2.getAddress());
  }
}
