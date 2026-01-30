package com.aikoder.spring.swagger.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.aikoder.spring.swagger.model.Student;

@Service
public class StudentService {

  static List<Student> students = new ArrayList<Student>();
  static long id = 0;

  public List<Student> findAll() {
    return students;
  }

  public List<Student> findByNameContaining(String name) {
    return students.stream().filter(student -> student.getName().contains(name)).toList();
  }

  public Student findById(long id) {
    return students.stream().filter(student -> id == student.getId()).findAny().orElse(null);
  }

  public Student save(Student student) {
    // update Student
    if (student.getId() != 0) {
      long _id = student.getId();

      for (int idx = 0; idx < students.size(); idx++)
        if (_id == students.get(idx).getId()) {
          students.set(idx, student);
          break;
        }

      return student;
    }

    // create new Student
    student.setId(++id);
    students.add(student);
    return student;
  }

  public void deleteById(long id) {
    students.removeIf(student -> id == student.getId());
  }

  public void deleteAll() {
    students.removeAll(students);
  }

  public List<Student> findByIsActive(boolean isActive) {
    return students.stream().filter(student -> isActive == student.isActive()).toList();
  }
}
