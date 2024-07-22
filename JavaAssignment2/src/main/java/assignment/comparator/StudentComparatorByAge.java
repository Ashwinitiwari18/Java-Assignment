package assignment.comparator;

import assignment.model.Student;

import java.util.Comparator;

public class StudentComparatorByAge implements Comparator<Student> {

  @Override
  public int compare(Student s1, Student s2) {
    return Integer.compare(s1.getAge(),s2.getAge());
  }
}
