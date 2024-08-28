package assignment.controller;

import assignment.model.User;

public interface InputOutputInterface {
  void addStudent(User user);

  void displayUserData(User user);

  void deleteUserData(User user);

  void exit(User user);
}
