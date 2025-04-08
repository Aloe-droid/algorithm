import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

class Main {
    static int N;
    static int[] ints;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        ints = new int[N];
        for(int i = 0; i < N; i++) ints[i] = Integer.parseInt(br.readLine());
        sort();

        for(int k : ints) sb.append(k).append("\n");
        System.out.println(sb);
    }

    public static void sort() {
        int key = new Random().nextInt(5);
        switch(key) {
            case 0 : bubbleSort();
            case 1 : selectionSort();
            case 2 : insertionSort();
            case 3 : mergeSort(0, N - 1, new int[N]);
            case 4 : quickSort(0, N - 1);
        }
    }

    // 버블 정렬 : 안정 정렬
    public static void bubbleSort() {
        // 시간 복잡도 O(N^2), 공간 복잡도 O(1)
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N - 1 - i; j++) {
                if(ints[j] > ints[j + 1]) {
                    int temp = ints[j];
                    ints[j] = ints[j + 1];
                    ints[j + 1] = temp;
                }
            }
        }
    }

    // 선택 정렬 : 불안정 정렬
    public static void selectionSort() {
        // 시간 복잡도 O(N^2), 공간 복잡도 O(1)
        for(int i = 0; i < N - 1; i++) {
            int minIdx = i;
            for(int j = i + 1; j < N; j++) {
                if(ints[j] < ints[minIdx]) minIdx = j;
            }
            int temp = ints[minIdx];
            ints[minIdx] = ints[i];
            ints[i] = temp;
        }
    }

    // 삽입 정렬 : 안정 정렬
    public static void insertionSort() {
        // 시간 복잡도 O(N^2), 공간 복잡도 O(1)
        for(int i = 1; i < N; i++) {
            int target = ints[i];
            int j = i - 1;
            while(j >= 0 && ints[j] > target) {
                ints[j + 1] = ints[j];
                j -= 1;
            }
            ints[j + 1] = target;
        }
    }

    // 병합 정렬 : 안정 정렬
    public static void mergeSort(int left, int right, int[] sorted) {
        // 시간 복잡도 O(NlogN), 공간 복잡도 O(N)
        if(left == right) return;

        int mid = (left + right) / 2;
        mergeSort(left, mid, sorted);
        mergeSort(mid + 1, right, sorted);

        int l = left;
        int r = mid + 1;
        int idx =  left;
        while(l <= mid && r <= right) {
            if(ints[l] <= ints[r]) sorted[idx++] = ints[l++];
            else sorted[idx++] = ints[r++];
        }

        while(l <= mid) sorted[idx++] = ints[l++];
        while(r <= right) sorted[idx++] = ints[r++];
        System.arraycopy(sorted, left, ints, left, right + 1 - left);
    }

    // 퀵 정렬 : 불안정 정렬
    public static void quickSort(int left, int right) {
        // 시간 복잡도 O(N^2), 공간 복잡도 O(logN)
        if(left >= right) return;

        int mid = (left + right) / 2;
        int pivot = ints[mid];
        int l = left;
        int r = right;

        while(l <= r) {
            while(ints[l] < pivot) l++;
            while(ints[r] > pivot) r--;
            if(l <= r) {
                int temp = ints[l];
                ints[l] = ints[r];
                ints[r] = temp;
                l++;
                r--;
            }
        }

        quickSort(left, r);
        quickSort(l, right);
    }
}
