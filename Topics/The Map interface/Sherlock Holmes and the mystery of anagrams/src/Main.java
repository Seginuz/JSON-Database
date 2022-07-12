import java.util.HashMap;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        var input1 = scanner.nextLine().toLowerCase().toCharArray();
        var input2 = scanner.nextLine().toLowerCase().toCharArray();

        var map1 = new HashMap<Character, Integer>();
        var map2 = new HashMap<Character, Integer>();

        for (char c : input1) {
            map1.put(c, map1.getOrDefault(c, 0) + 1);
        }
        for (char c : input2) {
            map2.put(c, map2.getOrDefault(c, 0) + 1);
        }

        if (map1.equals(map2)) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}