package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
    }

    public static void test1() {
        Integer money = 77;
        Map<Integer, Integer> revol = new HashMap<>();
        revol.put(1, 22);
        revol.put(2, 11);
        revol.put(3, 1);
        revol.put(4, 26);
        revol.put(5, 76);
        Map.Entry<Integer, Integer> maxPrice = null;
        for (Map.Entry<Integer, Integer> entry : revol.entrySet()) {
            if (maxPrice == null || entry.getValue() > maxPrice.getValue()) {
                maxPrice = entry;
            }
        }

        if (maxPrice.getValue() > money) {
            System.out.println(0);
        } else {
            if (money >= maxPrice.getValue()) {
                System.out.println(maxPrice.getValue());
            }
        }

    }

    public static void test2() {
        ArrayList<String> stroka = new ArrayList<>();
        stroka.add("asdfghshermnbvzcxvytriff");
        System.out.println(stroka.toString().toLowerCase());
        int count = 0;
        int index1 = 0;
        int index2 = 0;
        int index3 = 0;
        int index4 = 0;
        int index5 = 0;
        int index6 = 1;
        for (int i = 0; i < stroka.size(); i++) {
            index1 = stroka.get(i).toLowerCase().indexOf("s");
            index2 = stroka.get(i).toLowerCase().indexOf("h");
            index3 = stroka.get(i).toLowerCase().indexOf("e");
            index4 = stroka.get(i).toLowerCase().indexOf("r");
            index5 = stroka.get(i).toLowerCase().indexOf("i");
            index6 = stroka.get(i).toLowerCase().indexOf("f");
            if (index1 >= 0 && index2 >= 0 && index3 >= 0 && index4 >= 0 && index5 >=0 && index6 >= 1 ) {
                count++;
                System.out.println(count++);
            } else System.out.println(count);

     }
    }


        public static void test3() {
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt(); // Количество карт
            int[] a = new int[n]; // Последовательность ковбоя Джо
            int[] b = new int[n]; // Выигрышная последовательность

            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextInt();
            }

            for (int i = 0; i < n; i++) {
                b[i] = scanner.nextInt();
            }

            // Находим первое несовпадение между a и b
            int firstMismatch = -1;
            for (int i = 0; i < n; i++) {
                if (a[i] != b[i]) {
                    firstMismatch = i;
                    break;
                }
            }

            if (firstMismatch == -1) {
                // Все карты уже совпадают
                System.out.println("YES");
            } else {
                // Находим последнее несовпадение между a и b
                int lastMismatch = -1;
                for (int i = n - 1; i >= 0; i--) {
                    if (a[i] != b[i]) {
                        lastMismatch = i;
                        break;
                    }
                }

                // Проверяем, можно ли получить выигрышную последовательность
                boolean canObtainWinningSequence = true;
                for (int i = firstMismatch; i <= lastMismatch; i++) {
                    if (a[i] != b[lastMismatch - i + firstMismatch]) {
                        canObtainWinningSequence = false;
                        break;
                    }
                }

                if (canObtainWinningSequence) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }
    public static void test4() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // Необходимая сумма
        int m = scanner.nextInt(); // Количество номиналов купюр
        int[] denominations = new int[m]; // Номиналы купюр

        for (int i = 0; i < m; i++) {
            denominations[i] = scanner.nextInt();
        }

        // Сортируем номиналы купюр по убыванию, чтобы начать с самых крупных
        Arrays.sort(denominations);
        List<Integer> stolenDenominations = new ArrayList<>();

        for (int i = m - 1; i >= 0; i--) {
            while (n >= denominations[i]) {
                n -= denominations[i];
                stolenDenominations.add(denominations[i]);
            }
        }

        if (n == 0) {
            // Если сумма стала равной нулю, то Джо смог украсть необходимую сумму
            System.out.println(stolenDenominations.size());
            for (int denomination : stolenDenominations) {
                System.out.print(denomination + " ");
            }
        } else {
            // Если не удалось украсть необходимую сумму, выводим -1
            System.out.println("-1");
        }
    }

        public static void test5() {
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt(); // Количество городов
            int m = scanner.nextInt(); // Количество дорог

            List<List<Pair>> graph = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < m; i++) {
                int v = scanner.nextInt() - 1;
                int u = scanner.nextInt() - 1;
                int w = scanner.nextInt();
                graph.get(v).add(new Pair(u, w));
                graph.get(u).add(new Pair(v, w));
            }

            int initialStates = findInitialStates(graph);
            int left = 0;
            int right = (int) 1e9;

            while (left < right) {
                int mid = (left + right) / 2;
                if (isValid(graph, mid, initialStates)) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            System.out.println(left);
        }

        static class Pair {
            int first;
            int second;

            public Pair(int first, int second) {
                this.first = first;
                this.second = second;
            }
        }

        static int findInitialStates(List<List<Pair>> graph) {
            int n = graph.size();
            boolean[] visited = new boolean[n];
            int states = 0;

            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    dfs(graph, i, visited);
                    states++;
                }
            }

            return states;
        }

        static void dfs(List<List<Pair>> graph, int node, boolean[] visited) {
            visited[node] = true;

            for (Pair neighbor : graph.get(node)) {
                if (!visited[neighbor.first]) {
                    dfs(graph, neighbor.first, visited);
                }
            }
        }

        static boolean isValid(List<List<Pair>> graph, int xx, int initialStates) {
            int n = graph.size();
            boolean[] visited = new boolean[n];
            List<Set<Integer>> states = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    Set<Integer> state = new HashSet<>();
                    dfsWithLimit(graph, i, visited, xx, state);
                    states.add(state);
                }
            }

            return states.size() == initialStates;
        }

        static void dfsWithLimit(List<List<Pair>> graph, int node, boolean[] visited, int limit, Set<Integer> state) {
            visited[node] = true;
            state.add(node);

            for (Pair neighbor : graph.get(node)) {
                if (!visited[neighbor.first] && neighbor.second > limit) {
                    dfsWithLimit(graph, neighbor.first, visited, limit, state);
                }
            }
        }


    public static void test6() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // Количество духов
        int m = scanner.nextInt(); // Количество вопросов

        DSU dsu = new DSU(n);

        for (int i = 0; i < m; i++) {
            int type = scanner.nextInt();
            int x = scanner.nextInt();

            if (type == 1) {
                int y = scanner.nextInt();
                dsu.union(x, y);
            } else if (type == 2) {
                int y = scanner.nextInt();
                if (dsu.find(x) == dsu.find(y)) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            } else if (type == 3) {
                System.out.println(dsu.getSize(x));
            }
        }
    }

    static class DSU {
        private int[] parent;
        private int[] size;

        public DSU(int n) {
            parent = new int[n + 1];
            size = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int x) {
            if (parent[x] == x) {
                return x;
            }
            return parent[x] = find(parent[x]);
        }

        public void union(int x, int y) {
            x = find(x);
            y = find(y);

            if (x != y) {
                if (size[x] < size[y]) {
                    int temp = x;
                    x = y;
                    y = temp;
                }
                parent[y] = x;
                size[x] += size[y];
            }
        }

        public int getSize(int x) {
            return size[find(x)];
        }
    }
}





