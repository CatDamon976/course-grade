package com.jccc.myclass;

import java.util.List;



/**
 * The GradeManager interface provides a contract for managing and tracking courses
 * and assignments for students. Implementations of this interface are expected to
 * provide concrete logic for adding courses, adding assignments to specific courses,
 * retrieving a course by its name, and getting a list of all tracked courses.
 *
 * @author Katie Cooper
 * @version 1.0
 */

public interface GradeManager {
  
  /**
   * Adds the given course so it can be tracked by the student.
   *
   * @param course the JcccCourse to be added
   */
  
  public void addCourse(JcccCourse course);
  
  /**
   * Adds the assignment to the JcccCourse with the courseName parameter.
   * Searches through all the JcccCourses to find the courseName, then adds
   * the Assignment to the JcccCourse. If the JcccCourse is not found, 
   * does nothing with the Assignment and returns false, otherwise returns true.
   *
   * @param courseName the name of the JcccCourse to which the assignment should be added
   * @param assignment the Assignment object to be added
   * @return true if the assignment was added successfully, false otherwise
   */
  
  public boolean addAssignment(String courseName, Assignment assignment);
  
  /**
   * Searches through all the JcccCourses to find the JcccCourses using the courseName.
   * If the JcccCourse is found, returns it, otherwise returns null.
   *
   * @param courseName the name of the JcccCourse to be fetched
   * @return the JcccCourse object if found, null otherwise
   */
  
  public JcccCourse getCourseByCourseName(String courseName);
  
  /**
  * Retrieves a list of JcccCourse courses.
  *
  * @return a list of all JcccCourses.
  *
  */
  
  public List<JcccCourse> getCourses();
  
  
  
}

