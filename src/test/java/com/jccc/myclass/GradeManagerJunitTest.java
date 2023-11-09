package com.jccc.myclass;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;


/**
 * JUnit test for GradeManager.
 */

class GradeManagerJunitTest {

  private File testCoursesFile;
  private File testAssignmentFile;

  @BeforeEach
  void setUp() throws IOException {
    testCoursesFile = new File("CourseFile.dat");
    if (testCoursesFile.exists()) {
      testCoursesFile.delete();
    }
    testCoursesFile.createNewFile();
    testAssignmentFile = new File("AssignmentFile.dat");
    if (testAssignmentFile.exists()) {
      testAssignmentFile.delete();
    }
    testAssignmentFile.createNewFile();
  }

  @AfterEach
  void tearDown() {
    if (testCoursesFile != null && testCoursesFile.exists()) {
      testCoursesFile.delete();

    }
    if (testAssignmentFile != null && testAssignmentFile.exists()) {
      testAssignmentFile.delete();

    }

  }

  @Test
  public void testAddCourseWithIdNotZero() {
    GradeManager manager = new GradeManagerFileImpl();
    JcccCourse course = new JcccCourse(1, "Java Test");
    manager.addCourse(course);
    assertTrue(manager.getCourses().isEmpty());
  
  }
  
  @Test
  public void testAddCourseWithAssignment() {
    GradeManager manager = new GradeManagerFileImpl();
    Assignment assignment = new Assignment("assignement", 8, 10);
    List<Assignment> assignmentList = new ArrayList<>();
    JcccCourse course = new JcccCourse("Math");
    
    assignmentList.add(assignment);
    course.setAssignment(assignmentList);
    manager.addCourse(course);
    
    assertTrue(manager.getCourses().isEmpty());
    
  }
  
  @Test
  public void testaddCourse() {
    GradeManager manager = new GradeManagerFileImpl();
    JcccCourse course = new JcccCourse("Java Test");
    manager.addCourse(course);
    
    assertTrue(!manager.getCourses().isEmpty());

  }

  @Test
  public void testAddAssignmentIfCourseIsNull() {
    GradeManager manager = new GradeManagerFileImpl();
    Assignment assignment = new Assignment("paper", 7, 10);
    
    assertFalse(manager.addAssignment("", assignment));
  }
  
  @Test
  public void testAddAssignement() {
    GradeManager manager = new GradeManagerFileImpl();
    Assignment assignment = new Assignment("paper", 7, 10);
    JcccCourse course = new JcccCourse("Java Test");
    manager.addCourse(course);
    
    assertTrue(manager.addAssignment("Java Test", assignment));
    
    
  }

  @Test
  public void testgetCourseByCourseName() {
    GradeManager manager = new GradeManagerFileImpl();
    JcccCourse course = new JcccCourse("Java Test");
    manager.addCourse(course);
    assertEquals(JcccCourse.class, manager.getCourseByCourseName("Java Test").getClass());
    
  }

  @Test
  public void testgetCourses() {
    GradeManager manager = new GradeManagerFileImpl();
    JcccCourse course = new JcccCourse("Java Test");
    manager.addCourse(course);
    
    List<JcccCourse> courseLists = manager.getCourses();
    JcccCourse courseList = courseLists.get(0);
    assertEquals(course.getCourseId(), courseList.getCourseId());
    assertEquals(course.getCourseName(), courseList.getCourseName());
    
    

  }
}
