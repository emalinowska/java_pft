package pl.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        hello("world");
        hello("user");
        hello("Eliza");

        double l = 5;
        System.out.println("square area with sides " + l + " = " + area(l) );

        double a = 4;
        double b = 6;

        System.out.println("rectangle area with sides " + a + " and " + b + " = " + area(a, b));
    }

    public static void hello(String somebody) {
        System.out.println("Hello," + somebody + "!");
            }

    public static double area(double len) {
        return len * len;
    }

    public static double area(double a, double b) {
        return a * b;
    }
}
