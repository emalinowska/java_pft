package pl.stqa.pft.sandbox;

import static pl.stqa.pft.sandbox.Point.*;

public class Exercise2 {

  public static void main(String[] args) {

    Point point1 = new Point(0, -1);
    Point point2 = new Point(0, 2);

    System.out.println("Distance between points p1 and p2 = " + point1.distance(point2));

  }

}
