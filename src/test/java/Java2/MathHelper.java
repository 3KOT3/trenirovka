package Java2;

public class MathHelper {

    public static int sumTotal(int a, int b) {
        int total = a + b;
        return total;
    }

    public static int maxNumber(int a, int b) {
        if (a < b) {
            return b;
        }
        return a;
    }

    public static boolean isEven(int number) {
        return (number % 2 == 0);
    }
}
