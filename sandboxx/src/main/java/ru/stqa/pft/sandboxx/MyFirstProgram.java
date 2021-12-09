package ru.stqa.pft.sandboxx;

public class MyFirstProgram {

  public static void main(String[] args) {
    System.out.println("Hello, world!");

    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(4, 6);
    System.out.println("Площадь прямоугольника со стороной " + r.a + " и "+ r.b + " = " + r.area());

    Point p1 = new Point(0,0);
    Point p2 = new Point(2,2);
    System.out.println("Расстояние между точками " +
            "(" + p1.x +", "+ p1.y + ")" + " и " +
            "(" + p2.x +", "+ p2.y + ")" +
                    " = "+ p1.distance(p2));
  }

}
