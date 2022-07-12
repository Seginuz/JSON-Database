import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> inputList = Arrays.asList(scanner.nextLine().split("\\s"));

        System.out.println(inputList);
    }
}