package generics.practise1;

public class PairRunner {
    static void main() {
        Pair<String, Integer> pair1 = new Pair<>("abc", 123);
        System.out.println(pair1);

        Pair<Integer, String> pair2 = PairUtil.swap(pair1);
        System.out.println(pair2);
    }
}
