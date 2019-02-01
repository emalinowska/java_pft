package pl.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistance() {

    Point point1 = new Point(0, -1);
    Point point2 = new Point(0, 2);
    Assert.assertEquals(point1.distance(point2), 3.0);
  }

  @Test
  public void testDistanceFailed() {

    Point point1 = new Point(2, 2);
    Point point2 = new Point(4, 7);
    Assert.assertEquals(point1.distance(point2), 3.0);

  }
}
