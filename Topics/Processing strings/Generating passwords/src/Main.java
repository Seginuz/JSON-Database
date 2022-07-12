import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        int n = scanner.nextInt();

        char[] charArray = new char[n];

        // Fill with random uppercase letters
        for (int i = 0; i < n; i++) {
            char ch = (char) random.nextInt(65, 91);

            if (i != 0 && ch == charArray[i - 1]) {
                i--;
            } else {
                charArray[i] = ch;
            }
        }

        // Lowercase letters
        for (int i = 0; i < b; i++) {
            char ch = (char) random.nextInt(97, 123);

            if (i != 0 && ch == charArray[i + a - 1]) {
                i--;
            } else {
                charArray[i + a] = ch;
            }
        }

        // Digits
        for (int i = 0; i < c; i++) {
            char ch = (char) random.nextInt(48, 58);
            if (i != 0 && ch == charArray[i + a + b - 1]) {
                i--;
            } else {
                charArray[i + a + b] = ch;
            }
        }

        System.out.println(charArray);
    }
}