package com.jccc.myclass;

import java.text.DecimalFormat;

/**
 * GradeManagerTest tests the methods from GradeManagerFileImpl. Ensures program
 * is formated and running correctly.
 *
 * @author Katie Cooper
 */

public class GradeManagerTest {
  
  /**
   * Creates a manager object, a course object and an assignment object.
   * Add's to all three, testing the methods created in GradeManagerFileImpl.
   *  
   */

  public static void main(String[] args) {
    GradeManager manager = new GradeManagerFileImpl();
    JcccCourse trigonometry = new JcccCourse(" Trigonometry ");
    manager.addCourse(trigonometry);
    JcccCourse java = new JcccCourse(" Advanced Topics in Java ");
    manager.addCourse(java);
    Assignment nameJava = new Assignment(" Java Job Search ", 9, 10);
    manager.addAssignment(" Advanced Topics in Java ", nameJava);
    Assignment mathClass = new Assignment(" Review College Algebra ", 10, 10);
    manager.addAssignment(" Trigonometry ", mathClass);
    Assignment cis = new Assignment(" CS 205 Review Part One ", 8, 10);
    manager.addAssignment(" Advanced Topics in Java ", cis);
    System.out.println(manager.getCourseByCourseName(" Trigonometry "));
    formatOutput(manager);
  }

  private static void formatOutput(GradeManager manager) {
    DecimalFormat gradeFormat = new DecimalFormat("##0.00%");
    String format = "%-7s| %-24s | %-24s| %7s";
    String dashLine = "-------------------------------------------------------------------";
    System.out.println("id     | Class Name               | Assignment              |  %  ");
    System.out.println(dashLine);
    for (JcccCourse course : manager.getCourses()) {
      System.out.printf(format, course.getCourseId(), course.getCourseName(), "",
          gradeFormat.format(course.calculateCourseGrade()));
      System.out.println();
      for (Assignment assignment : course.getAssignment()) {
        System.out.printf(format, "", "", assignment.getName(),
            gradeFormat.format(assignment.calculateGrade()));
        System.out.println();
      }
      System.out.println(dashLine);
    }
  }

}
