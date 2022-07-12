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
            map2.putIfAbsent(c, 0);
        }
        for (char c : input2) {
            map2.put(c, map2.getOrDefault(c, 0) + 1);
            map1.putIfAbsent(c, 0);
        }

        int diff = 0;
        for (char key : map1.keySet()) {
            diff += Math.abs(map1.getOrDefault(key, 0) - map2.getOrDefault(key, 0));
        }

        System.out.println(diff);
    }
}