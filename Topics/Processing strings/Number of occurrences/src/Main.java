import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String string = scanner.nextLine();
        String substring = scanner.nextLine();

        int occurrences = 0;
        boolean exit = false;

        while (!exit) {
            String replacedString = string.replaceFirst(substring, "");

            if (!replacedString.equals(string)) {
                string = replacedString;
                occurrences++;
            } else {
                exit = true;
            }
        }

        System.out.println(occurrences);
    }
}