import java.util.*;

class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        int d = SCANNER.nextInt();
        SCANNER.nextLine();

        HashSet<String> knownWords = new HashSet<>();
        for (; d != 0; d--) {
            knownWords.add(SCANNER.nextLine().toLowerCase());
        }

        int l = SCANNER.nextInt();
        SCANNER.nextLine();
        HashSet<String> words = new HashSet<>();
        for (; l != 0; l--) {
            words.addAll(Arrays.asList(SCANNER.nextLine().split(" ")));
        }

        for (String word : words) {
            if (!knownWords.contains(word.toLowerCase())) {
                System.out.println(word);
            }
        }
    }
}