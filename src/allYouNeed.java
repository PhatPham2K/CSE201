import java.util.*;
import java.io.*;

/*
 * https://visualgo.net/en  
 * https://www.spoj.com/EIUPROGR/status/
 * https://www.geeksforgeeks.org/complete-guide-to-dsa-for-beginners/?ref=shm
 * https://www.geeksforgeeks.org/data-structures/?ref=shm
 * https://www.geeksforgeeks.org/explore?page=1&difficulty=Easy,Medium&sprint=a663236c31453b969852f9ea22507634&sortBy=submissions&sprint_name=SDE%20Sheet
 * https://codelearn.io/learning/thuat-toan-can-ban
 * https://codelearn.io/learning/thuat-toan-nang-cao?scheduleId=466266d3-8ac1-4465-8bb5-bf46c2b5144a
 */
class SampleProgram {

    static InputReader reader = new InputReader(System.in);

    public static void main(String[] args) {
        int n = reader.nextInt(), m = reader.nextInt();

        // Primitive types; wrapper types
        // int +=2*10^9, long +=1*10^18, char, float, double; Integer, Long, Character,
        // Float, Double
        Double.compare(0.1, (double) 10 / 100);
        // Utilities: Math, StringBuffer/StringBuilder, Arrays
        StringBuilder outBuffer = new StringBuilder();
        for (int i = 0; i < n; i++) {
            outBuffer.append(i + "\n");
        }
        System.out.println(outBuffer);

        Integer[] prices = new Integer[n];
        Arrays.sort(prices); // Sort increasing order

        // Sort descreasing order ???
        Arrays.sort(prices, (p1, p2) -> p2 - p1);

        // Algorithms: Sort, BinarySearch
        Comparator<Double> compareDouble = (n1, n2) -> n2.compareTo(n1);
        Double[] numbers = new Double[1000];
        Arrays.sort(numbers);
        Arrays.sort(numbers, 10, 100, compareDouble);
        Double key = 10d;
        Arrays.binarySearch(numbers, key, compareDouble);

        // Builtin Data Structures: List, ArrayList, ArrayList<T>, HashMap<K, V>...
        List<Long> weights = new ArrayList<Long>();
        for (long i = 0; i < n; i++) {
            weights.add(i);
            long weight = weights.get(0);
            weights.set(0, weight);
            System.out.println(weight);
        }
        weights.sort((w1, w2) -> w2.compareTo(w1));
        Collections.binarySearch(weights, 10, Comparator.comparingLong(w -> (long) w));
        weights.sort(Comparator.reverseOrder());
        weights.sort(Comparator.comparingLong(w -> (long) w).reversed());
        // weights.sort(Comparator.comparingLong(w -> (long)w).thenComparingLong(w ->
        // (long)w).reversed());

        List<Integer> values = new ArrayList<>();
        values.add(10);
        values.sort((n1, n2) -> n2.compareTo(n1));
        Collections.binarySearch(values, 10, (n1, n2) -> n2.compareTo(n1));

        HashMap<Integer, Boolean> map = new HashMap<>();
        map.put(10, true);
        map.get(10);
        map.containsKey(10);

        Dictionary<Integer, Boolean> map2 = new Hashtable<>();
        for (var i = 0; i < 10; i++) {
            map2.put(i, true);
        }

        HashMap<Integer, Integer> map3 = new HashMap<>();
        for (var i = 0; i < 10; i++) {
            var key1 = reader.nextInt();
            map3.put(key1, map3.getOrDefault(key1, 0) + 1);
        }

        map3.forEach((key1, count) -> outBuffer.append(key1 + ": " + count));

        var keys = new ArrayList<>(map3.keySet());
        keys.sort((k1, k2) -> k2 - k1);
        keys.forEach((key1) -> outBuffer.append(key1 + ": " + map3.get(key1)));

        System.out.println(outBuffer);

        Stack<Integer> stack = new Stack<>();
        Queue<Integer> queue = new ArrayDeque<>();

        ArrayList<Student> students = new ArrayList<>();
        students.sort(Comparator.comparing(Student::getName).reversed().thenComparing(Student::getId));
        var studentComparor = Comparator.comparing(Student::getName).reversed().thenComparing(Student::getId);
        students.sort(studentComparor);
        Collections.sort(students, studentComparor);
        Collections.binarySearch(students, new Student(1, "A"), studentComparor);

        Comparator<Student> studentComparator2 = (s1, s2) -> {
            var compare = s1.getName().compareTo(s2.getName());
            if (compare == 0) {
                compare = s1.getId() - s2.getId();
            }
            return compare;
        };

        var ids = students.stream().sorted(studentComparator2)
                .filter(s -> s.getId() > 10)
                .map(s -> s.getName() + " " + s.getId())
                .toArray(String[]::new);
        System.out.println(String.join(" ", ids));
    }

    static class Student implements Comparable<Student> {
        public int id;
        public String name;

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        public Student(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return id + " " + name;
        }

        @Override
        public int compareTo(Student o) {
            var compare = name.compareTo(o.name);
            if (compare == 0) {
                compare = id - o.id;
            }
            return compare;
        }
    }

    static class InputReader {
        StringTokenizer tokenizer;
        BufferedReader reader;
        String token;
        String temp;

        public InputReader(InputStream stream) {
            tokenizer = null;
            reader = new BufferedReader(new InputStreamReader(stream));
        }

        public InputReader(FileInputStream stream) {
            tokenizer = null;
            reader = new BufferedReader(new InputStreamReader(stream));
        }

        public String nextLine() throws IOException {
            return reader.readLine();
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    if (temp != null) {
                        tokenizer = new StringTokenizer(temp);
                        temp = null;
                    } else {
                        tokenizer = new StringTokenizer(reader.readLine());
                    }
                } catch (IOException e) {
                }
            }
            return tokenizer.nextToken();
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}