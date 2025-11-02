package collections.bigpractise.practise1.task3;

import java.util.Map;

public class Task3Runner {
    static void main() {
        Map<String, String> map = Map.of(
                "key1", "value1",
                "key2", "value1",
                "key3", "value3");
        System.out.println(MapUtils.isUniqueValues(map));
    }
}
