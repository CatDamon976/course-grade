package com.jccc.myclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * GradeManagerFileImpl implements GradeManager.
 *
 * @author Katie Cooper
 */

public class GradeManagerFileImpl implements GradeManager {

  private static int id = 1;
  private final String assignmentMapFile = "AssignmentFile.dat";
  private final String courseFile = "CourseFile.dat";

  /**
   * WriteAssignment method uses an ObjectOutputStream to write the Map to a file.
   * 
   */

  private void writeAssignmentMapToFile(Map<Integer, List<Assignment>> assignmentMap) {
    try (ObjectOutputStream output = new ObjectOutputStream(
          new FileOutputStream(assignmentMapFile))) {
      output.writeObject(assignmentMap);
    } catch (Exception e) {
      System.out.println("Could not write to stream." + e.getMessage());
    }
  }

  /**
   * readAssignmentMapFromFile method uses an ObjectInputStream to read the map
   * from a file.
   *
   */

  private Map<Integer, List<Assignment>> readAssignmentMapFromFile() {

    File file = new File(assignmentMapFile);
    if (!file.exists()) {
      return new HashMap<>();
    }
    
    try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(file))) {
      return (Map<Integer, List<Assignment>>) input.readObject();

    } catch (IOException | ClassNotFoundException e) {
      System.out.println("Could not read from file." + e.getMessage());
      return new HashMap<>();
    }
  }

  /**
   * ReadBytes method.
   */

  private String readBytes(RandomAccessFile input, int length) throws IOException {
    byte[] byteArray = new byte[length];
    for (int i = 0; i < length; i++) {
      byteArray[i] = input.readByte();
    }
    return new String(byteArray).trim();
  }

  /**
   * WriteBytes method.
   */
  
  private void writeBytes(RandomAccessFile output, String value, int length) throws IOException {
    StringBuilder builder = new StringBuilder();
    builder.append(value);
    builder.setLength(length);
    output.writeBytes(builder.toString());
  }

  /**
   * AddCourse method writes to the end of file using RAF. Verifies that the
   * JcccCourse is not empty and prints a warning statement if so.
   * 
   */

  @Override
  public void addCourse(JcccCourse course) {
    if (course.getCourseId() != 0) {
      System.out.println("To add a Course the courseId must be zero");
      return;
    }
    if (!course.getAssignment().isEmpty()) {
      System.out.println("To add a Course there must be no assignments associate with it");
      return;
    }
    course.setCourseId(id++);
    try (RandomAccessFile raf = new RandomAccessFile(courseFile, "rw")) {
      raf.seek(raf.length());
      raf.writeInt(course.getCourseId());
      writeBytes(raf, course.getCourseName(), 50);
    } catch (IOException e) {
      System.out.println("Can not write to file" + e.getMessage());

    }
    Map<Integer, List<Assignment>> assignmentMap = readAssignmentMapFromFile();
    assignmentMap.put(course.getCourseId(), new ArrayList<>());
    writeAssignmentMapToFile(assignmentMap);

  }

  /**
   * getCourseByCourseName method returns a new object JcccCourse.
   * 
   */

  @Override
  public JcccCourse getCourseByCourseName(String courseName) {
    
    Map<Integer, List<Assignment>> assignmentMap = readAssignmentMapFromFile();

    
    if (courseName == null || courseName.isEmpty()) {
      System.out.println("Course name provided is null or empty.");
      return null;
    }

    
    File file = new File(courseFile);
    if (!file.exists()) {
      System.out.println("The course file does not exist.");
      return null;
    }

    try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
      
      while (raf.getFilePointer() < raf.length()) {
        int courseId = raf.readInt();
        String name = readBytes(raf, 50).trim();

        
        if (courseName.trim().equals(name)) {
          JcccCourse course = new JcccCourse(courseId, name);

          
          List<Assignment> assignmentList = assignmentMap.get(courseId);
          if (assignmentList == null) {
            assignmentList = new ArrayList<>();
            assignmentMap.put(courseId, assignmentList);
          }
          
          course.setAssignment(assignmentList);
          return course;
        }
      }
    } catch (IOException e) {
      System.out.println("Could not read from file: " + e.getMessage());
    }

    // Course not found
    System.out.println("Course not found: " + courseName);
    return null;
  }

  /**
   * getCourse method reads all the courses from an RAF creates a new JcccCourse
   * for each course.
   */

  @Override
  public List<JcccCourse> getCourses() {
    List<JcccCourse> courseList = new ArrayList<>();
    Map<Integer, List<Assignment>> assignmentMap = readAssignmentMapFromFile();
    try (RandomAccessFile raf = new RandomAccessFile(courseFile, "r")) {
      while (raf.getFilePointer() < raf.length()) {
        int id = raf.readInt();
        String name = readBytes(raf, 50);
        JcccCourse createdCourse = new JcccCourse(id, name.trim());
        List<Assignment> assignmentList = new ArrayList<>();
        assignmentList = assignmentMap.get(id);
        if (assignmentList != null) {
          createdCourse.setAssignment(assignmentList);
        }
        courseList.add(createdCourse);
      }
    } catch (IOException e) {
      System.out.println("Count not find file.");
    }

    return courseList;
  }

  /**
   * addAssignment method creates a new Course by calling the
   * getCourseByCourseName() method. Adds the new course to an assignment List.
   */

  @Override
  public boolean addAssignment(String courseName, Assignment assignment) {
    JcccCourse addCourse = getCourseByCourseName(courseName);
    if (addCourse == null) {
      System.out.println("Course not found: " + courseName);
      return false;
    }
    List<Assignment> assignmentList = addCourse.getAssignment();
    if (assignmentList == null) {
      assignmentList = new ArrayList<>();

    }
    assignmentList.add(assignment);

    Map<Integer, List<Assignment>> assignmentMap = readAssignmentMapFromFile();
    assignmentMap.put(addCourse.getCourseId(), assignmentList);
    writeAssignmentMapToFile(assignmentMap);
    return true;
  }

}
