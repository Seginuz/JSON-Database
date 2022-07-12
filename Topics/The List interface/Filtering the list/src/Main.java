import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] inputArray = scanner.nextLine().split("\\s");

        List<Integer> inputList = Arrays.stream(inputArray)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Integer> outputList = new ArrayList<>();

        for (int i = 0; i < inputList.size(); i++) {
            if (i % 2 != 0) {
                outputList.add(inputList.get(i));
            }
        }

        for (int i = outputList.size() - 1; i >= 0; i--) {
            System.out.print(outputList.get(i));
            System.out.print(" ");
        }
    }
}