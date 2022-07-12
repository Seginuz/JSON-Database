import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String s = scanner.next();
        int n = scanner.nextInt();

        if (n <= s.length() && n >= 0) {
            String firstPart = s.substring(n);
            String secondPart = s.substring(0, n);

            System.out.println(firstPart + secondPart);
        } else {
            System.out.println(s);
        }
    }
}