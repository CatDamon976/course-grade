package com.jccc.myclass;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a course at JCCC with its associated assignments. The JcccCourse
 * class captures the details of a course, including its ID, name, and a list of
 * assignments related to the course. It provides functionality to calculate the
 * cumulative grade for all assignments in the course.
 *
 * @author Katie Cooper
 *
 */

public class JcccCourse {

  private int courseId;
  private String courseName;
  private List<Assignment> assignment;

  /**
   * An all args contructor of the JcccCourse object.
   */

  public JcccCourse(int courseId, String courseName) {
    super();
    this.courseId = courseId;
    this.courseName = courseName;
    this.assignment = new ArrayList<>();
  }

  public JcccCourse(String courseName) {
    this(0, courseName);
  }

  public int getCourseId() {
    return courseId;
  }

  public void setCourseId(int courseId) {
    this.courseId = courseId;
  }

  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  public List<Assignment> getAssignment() {
    return assignment;
  }

  public void setAssignment(List<Assignment> assignment) {
    this.assignment = assignment;
  }

  /**
   * CalculateCourseGrade method takes totalRecievedPoints divided by
   * totalTotalPoints. formatting the output to 2 decimals and returning the
   * grade.
   *
   * @return a String variable called grade with an output of 2 decimals.
   *
   */

  public double calculateCourseGrade() {
    double totalReceivedPoints = 0;
    double totalTotalPoints = 0;

    for (Assignment assignment : assignment) {
      totalReceivedPoints += assignment.getReceivedPoints();
      totalTotalPoints += assignment.getTotalPoints();
    }

    double grade = (totalReceivedPoints / totalTotalPoints);

    return grade;
  }

  @Override
  public String toString() {
    return "JcccCourse [courseId=" + courseId + ", courseName=" 
        + courseName + ", assignment=" + assignment + "]";
  }

}
