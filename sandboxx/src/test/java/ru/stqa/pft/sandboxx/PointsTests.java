package ru.stqa.pft.sandboxx;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointsTests {

  @Test
  public void testNullPoints() {
    Point p1 = new Point(0, 0);
    Point p2 = new Point(0, 0);
    Assert.assertEquals(p1.distance(p2), 0.0);
  }

  @Test
  public void testEqualsPoints() {
    Point p1 = new Point(3, 4);
    Point p2 = new Point(3, 4);
    Assert.assertEquals(p1.distance(p2), 0.0);
  }

  @Test
  public void testDifPoints() {
    Point p1 = new Point(30, 40);
    Point p2 = new Point(3, 4);
    Assert.assertEquals(p1.distance(p2), 45.0);
  }
}
