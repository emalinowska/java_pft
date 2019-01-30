package pl.stqa.pft.sandbox;

/* klasy i obiekty */

public class MyFirstProgram {

    public static void main(String[] args) {
        hello("world");
        hello("user");
        hello("Eliza");

        Square s = new Square(5);
        System.out.println("square area with sides " + s.l + " = " + s.area() );

        Rectangle r = new Rectangle(4, 6);

        System.out.println("rectangle area with sides " + r.a + " and " + r.b + " = " + r.area() );
    }

    public static void hello(String somebody) {
        System.out.println("Hello," + somebody + "!");
            }

    public static double area(Rectangle r) {
        return r.a * r.b;
    }
}
