package com.aikoder.spring.swagger.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aikoder.spring.swagger.model.Student;
import com.aikoder.spring.swagger.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Student v1", description = "Student management APIs v1")
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/v1")
public class StudentController {

  private final StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @Operation(summary = "Create a new Student", tags = { "students", "post" })
  @ApiResponses({
      @ApiResponse(responseCode = "201", content = {
          @Content(schema = @Schema(implementation = Student.class), mediaType = "application/json") }),
      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
  @PostMapping("/students")
  public ResponseEntity<Student> createStudent(@RequestBody Student student) {
    try {
      Student _student = studentService
          .save(new Student(student.getName(), student.getDescription(), student.isActive()));
      return ResponseEntity.status(HttpStatus.CREATED).body(_student);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body(null);
    }
  }

  @Operation(summary = "Retrieve all Students", tags = { "students", "get", "filter" })
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
          @Content(schema = @Schema(implementation = Student.class), mediaType = "application/json") }),
      @ApiResponse(responseCode = "204", description = "There are no Students", content = {
          @Content(schema = @Schema()) }),
      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
  @GetMapping("/students")
  public ResponseEntity<List<Student>> getAllStudents(@RequestParam(required = false) String name) {
    try {
      List<Student> students = new ArrayList<Student>();

      if (name == null)
        studentService.findAll().forEach(students::add);
      else
        studentService.findByNameContaining(name).forEach(students::add);

      if (students.isEmpty()) {
        return ResponseEntity.noContent().build();
      }

      return ResponseEntity.ok(students);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body(null);
    }
  }

  @Operation(
      summary = "Retrieve a Student by Id",
      description = "Get a Student object by specifying its id. The response is Student object with id, name, description and published status.",
      tags = { "students", "get" })
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Student.class), mediaType = "application/json") }),
      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
  @GetMapping("/students/{id}")
  public ResponseEntity<Student> getStudentById(@PathVariable("id") long id) {
    Student student = studentService.findById(id);

    if (student != null) {
      return ResponseEntity.ok(student);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @Operation(summary = "Update a Student by Id", tags = { "students", "put" })
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
          @Content(schema = @Schema(implementation = Student.class), mediaType = "application/json") }),
      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }),
      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }) })
  @PutMapping("/students/{id}")
  public ResponseEntity<Student> updateStudent(@PathVariable("id") long id, @RequestBody Student student) {
    Student _student = studentService.findById(id);

    if (_student != null) {
      _student.setName(student.getName());
      _student.setDescription(student.getDescription());
      _student.setIsActive(student.isActive());
      return ResponseEntity.ok(studentService.save(_student));
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @Operation(summary = "Delete a Student by Id", tags = { "students", "delete" })
  @ApiResponses({ @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
  @DeleteMapping("/students/{id}")
  public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") long id) {
    try {
      studentService.deleteById(id);
      return ResponseEntity.noContent().build();
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @Operation(summary = "Delete all Students", tags = { "students", "delete" })
  @ApiResponses({ @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
  @DeleteMapping("/students")
  public ResponseEntity<HttpStatus> deleteAllStudents() {
    try {
      studentService.deleteAll();
      return ResponseEntity.noContent().build();
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }

  }

  @Operation(summary = "Retrieve all active Students", tags = { "students", "get", "filter" })
  @GetMapping("/students/active")
  public ResponseEntity<List<Student>> findByActive() {
    try {
      List<Student> students = studentService.findByIsActive(true);

      if (students.isEmpty()) {
        return ResponseEntity.noContent().build();
      }
      return ResponseEntity.ok(students);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }
}
