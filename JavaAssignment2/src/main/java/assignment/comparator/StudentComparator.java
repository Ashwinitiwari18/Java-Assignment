package assignment.comparator;

import assignment.model.Student;

import java.io.Serializable;
import java.util.Comparator;

public class StudentComparator implements Comparator<Student>, Serializable {

  @Override
  public int compare(Student s1, Student s2) {
    if (s1.getFullName().equals(s2.getFullName())) {
      return Integer.compare(s1.getRollNumber(),s2.getRollNumber());
    }
    return s1.getFullName().compareTo(s2.getFullName());
  }
}
