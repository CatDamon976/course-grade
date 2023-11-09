package com.jccc.myclass;

import java.io.Serializable;

/**
 * Represents an assignment with a name, points received, and total points.
 * This class is serializable, allowing its instances to be easily saved and 
 * restored in a binary format.
 *  Assignment class provides the ability to calculate the grade 
 * as a fraction of received points out of total points. The grade is 
 * calculated by dividing the received points by the total points.
 *
 * @author Katie Cooper
 */

public class Assignment implements Serializable {
  
  
  private static final long serialVersionUID = 1L;
  private String name;
  private double receivedPoints;
  private double totalPoints;
  
  /**
   * Constructs a new Assignment with the specified details.
   *
   * @param name the name of the Assignment
   * @param receivedPoints the number of points received for the Assignment
   * @param totalPoints the total number of points possible for the Assignment
   */

  public Assignment(String name, double receivedPoints, double totalPoints) {
    super();
    this.name = name;
    this.receivedPoints = receivedPoints;
    this.totalPoints = totalPoints;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getReceivedPoints() {
    return receivedPoints;
  }

  public void setReceivedPoints(double receivedPoints) {
    this.receivedPoints = receivedPoints;
  }

  public double getTotalPoints() {
    return totalPoints;
  }

  public void setTotalPoints(double totalPoints) {
    this.totalPoints = totalPoints;
  }
  
  /**
   * Calculates the grade for this assignment by dividing the received points by the total points.
   * The result is a decimal value representing the grade, where 1 is 100%, 0.5 is 50%, etc.
   *
   * @return the calculated grade as a double
   */
  
  public double calculateGrade() {
    double grade = (receivedPoints / totalPoints);
    
    return grade;
  }

  @Override
  public String toString() {
    return "Assignment [name=" + name + ", receivedPoints=" 
        + receivedPoints + ", totalPoints=" + totalPoints + "]";
  }
  

}
