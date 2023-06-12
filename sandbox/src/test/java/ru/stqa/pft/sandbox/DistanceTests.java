package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceTests {

  @Test
  public void testDistance () {
    Point p1 = new Point(3, 8);
    Point p2 = new Point(2, 3);
    Assert.assertEquals(p1.distance(p2), 5.0990195135927845);
  }

  @Test
  public void testDistance1 () {
    Point p1 = new Point(2, 4);
    Point p2 = new Point(8, 7);
    Assert.assertEquals(p1.distance(p2), 6.708203932499369);
  }

  @Test
  public void testDistance2 () {
    Point p1 = new Point(-3, -8);
    Point p2 = new Point(2, 3);
    Assert.assertEquals(p1.distance(p2), 12.083045973594572);
  }

  @Test
  public void testDistance3 () {
    Point p1 = new Point(8, 3);
    Point p2 = new Point(-2, -3);
    Assert.assertEquals(p1.distance(p2), 11.661903789690601);
  }

}
