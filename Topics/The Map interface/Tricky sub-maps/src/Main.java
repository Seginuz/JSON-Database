import java.util.*;

class MapUtils {
    public static Map<Integer, String> getSubMap(TreeMap<Integer, String> map) {
        var subMap = new TreeMap<Integer, String>(Collections.reverseOrder());
        if (map.firstKey() % 2 != 0) {
            subMap.putAll(map.subMap(map.firstKey(), true, map.firstKey() + 4, true));
        } else {
            subMap.putAll(map.subMap(map.lastKey() - 4, true, map.lastKey(), true));
        }
        return subMap;
    }
}

/* Do not modify code below */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TreeMap<Integer, String> map = new TreeMap<>();
        Arrays.stream(scanner.nextLine().split("\\s")).forEach(s -> {
            String[] pair = s.split(":");
            map.put(Integer.parseInt(pair[0]), pair[1]);
        });

        Map<Integer, String> res = MapUtils.getSubMap(map);
        res.forEach((k, v) -> System.out.println(k + " : " + v));
    }
}