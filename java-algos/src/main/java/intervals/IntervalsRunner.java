package intervals;

import java.util.Arrays;

public class IntervalsRunner {

    static void main() {

        LT253_MeetingRoomsII task = new LT253_MeetingRoomsII();

        int[][][] tests = new int[][][]{
                // 1) Pusty input
                {},
                // 2) Jedno spotkanie
                {{5, 10}},
                // 3) Brak nakładania (po kolei)
                {{0, 10}, {10, 20}, {20, 30}},
                // 4) Proste nakładanie
                {{0, 30}, {5, 10}, {15, 20}},
                // 5) Wszystkie nakładają się
                {{1, 5}, {2, 6}, {3, 7}, {4, 8}},
                // 6) Identyczne przedziały
                {{1, 4}, {1, 4}, {1, 4}},
                // 7) Identyczne starty, różne endy
                {{1, 2}, {1, 3}, {1, 10}, {1, 5}},
                // 8) Zagnieżdżone
                {{1, 10}, {2, 3}, {4, 5}, {6, 7}, {8, 9}},
                // 9) Touching + overlap na końcu
                {{0, 5}, {5, 10}, {9, 12}},
                // 10) Mix
                {{0, 2}, {1, 3}, {3, 5}, {4, 6}, {6, 8}},
                // 11) Pik w środku
                {{0, 10}, {1, 9}, {2, 8}, {3, 7}, {10, 12}},
                // 12) Duże wartości
                {{1_000_000_000, 1_500_000_000}, {1_200_000_000, 1_300_000_000}, {0, 10}},
                // 13) Start==end (zero-length)
                {{1, 1}, {1, 2}, {2, 2}, {2, 3}},
                // 14) Niesortowane wejście
                {{7, 10}, {2, 4}, {3, 6}, {1, 3}, {5, 8}},
                // 15) Duża kolizja na starcie
                {{0, 100}, {0, 50}, {0, 25}, {0, 10}, {10, 20}, {20, 30}}
        };

        int[] expected = new int[]{
                0, 1, 1, 2, 4, 3, 4, 2, 2, 2, 4, 2, 1, 2, 4
        };

        for (int i = 0; i < tests.length; i++) {
            int[][] input = deepCopy(tests[i]); // żeby sortowanie w środku nie psuło testów
            int got = task.minMeetingRooms(input);
            int exp = expected[i];

            System.out.printf(
                    "TC%02d intervals=%s  got=%d  expected=%d  %s%n",
                    i + 1,
                    Arrays.deepToString(tests[i]),
                    got,
                    exp,
                    (got == exp ? "✅ PASS" : "❌ FAIL")
            );
        }
    }

    private static int[][] deepCopy(int[][] a) {
        int[][] copy = new int[a.length][];
        for (int i = 0; i < a.length; i++) {
            copy[i] = Arrays.copyOf(a[i], a[i].length);
        }
        return copy;
    }

}
