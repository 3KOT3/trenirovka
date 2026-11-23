package Java2;

public class MathHelper {

    public static int sum(int a, int b) {
        int total = a + b;
        return total;
    }

    public static int max(int a, int b) {
        if (a < b) {
            return b;
        } else return a;
    }

    public static boolean isEven(int number) {
        boolean result;
        if (number % 2 == 0) {
            result = true;
        } else
            result = false;

        return result;
    }
}
