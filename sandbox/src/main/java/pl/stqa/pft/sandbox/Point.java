package pl.stqa.pft.sandbox;

public class Point {
  public double x, y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double distance(Point p) {

    return Math.sqrt(Math.pow(p.x - this.x, 2.0) + Math.pow(p.y - this.y, 2.0));

  }
}

