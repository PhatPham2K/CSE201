import java.io.*;
import java.util.*;

class EIAPPLEBOX {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int t = sc.nextInt();

        for (int i = 0; i < t; i++) {
            int N = sc.nextInt();
            long A = sc.nextLong();
            long P = sc.nextLong();

            long[] array = generateArray(N, A, P);
            int[] intArray = Arrays.stream(array).mapToInt(x -> (int) x).toArray();

            long result = countInversions(intArray);
            System.out.println(result);
        }
    }

    private static long[] generateArray(int N, long A, long P) {
        long[] array = new long[N];
        array[0] = (A * A) % P;

        for (int i = 1; i < N; i++) {
            array[i] = (array[i - 1] * A) % P;
        }
        return array;
    }

    public static long countInversions(int[] array) {
        int[] temp = Arrays.copyOf(array, array.length);
        return mergeSortAndCount(array, temp, 0, array.length - 1);
    }

    public static long mergeSortAndCount(int[] array, int[] temp, int left, int right) {
        long count = 0;
        if (left < right) {
            int mid = (left + right) / 2;
            count += mergeSortAndCount(array, temp, left, mid);
            count += mergeSortAndCount(array, temp, mid + 1, right);
            count += mergeAndCount(array, temp, left, mid, right);
        }
        return count;
    }

    public static long mergeAndCount(int[] array, int[] temp, int left, int mid, int right) {
        long count = 0;
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
                count += (mid - i + 1);
            }
        }

        while (i <= mid) {
            temp[k++] = array[i++];
        }

        while (j <= right) {
            temp[k++] = array[j++];
        }

        System.arraycopy(temp, left, array, left, right - left + 1);

        return count;
    }
}


//EIUMEDARRAY4
import java.io.*;
import java.util.*;

class EIUMEDARRAY4 {
	static StringBuffer sb = new StringBuffer();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int t = sc.nextInt();

		for (int i = 0; i < t; i++) {
			int N = sc.nextInt();
			long A = sc.nextLong();
			long P = sc.nextLong();
			int K = sc.nextInt();

			long[] array = generateArray(N, A, P);

			long result = kthSmallestElement(array, K);
			System.out.println(result);
		}

	}

	private static long[] generateArray(int N, long A, long P) {
		long[] array = new long[N];
		array[0] = (A * A) % P;

		for (int i = 1; i < N; i++) {
			array[i] = (array[i - 1] * A) % P;
		}
		return array;
	}

	private static int countLessEqual(long[] array, long value) {
		int count = 0;
		for (long e : array) {
			if (e <= value) {
				count++;
			}
		}
		return count;
	}

	private static long kthSmallestElement(long[] array, int K) {
		long left = Long.MIN_VALUE, right = Long.MAX_VALUE;
		while (left < right) {
			long middle = (left + right) / 2;
			int count = countLessEqual(array, middle);
			if (count < K) {
				left = middle + 1;
			} else {
				right = middle;
			}
		}
		return left;
	}
}
// MergeSort 
import java.io.*;
import java.util.*;

class EIUMERSORT {
	static StringBuffer sb = new StringBuffer();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		int n = sc.nextInt();
		int[] array = new int[n];
		for (int i = 0; i < n; i++) {
			array[i] = sc.nextInt();
		}
		mergeSort(array);
		for (int i : array) {
			sb.append(i).append("\n");
		}
		System.out.println(sb);
	}

	public static void mergeSort(int[] array) {
		if (array.length <= 1) {
			return;
		}
		int mid = array.length / 2;
		int[] left = Arrays.copyOfRange(array, 0, mid);
		int[] right = Arrays.copyOfRange(array, mid, array.length);
		mergeSort(left);
		mergeSort(right);
		merge(array, left, right);
	}

	public static void merge(int[] array, int[] left, int[] right) {
		int i = 0, j = 0, k = 0;
		while (i < left.length && j < right.length) {
			if (left[i] <= right[j]) {
				array[k++] = left[i++];
			} else {
				array[k++] = right[j++];
			}
		}
		while (i < left.length) {
			array[k++] = left[i++];
		}
		while (j < right.length) {
			array[k++] = right[j++];
		}
	}
}


      StringBuilder sb = new StringBuilder();
        InputReader reader = new InputReader(System.in);

        int n = reader.nextInt();
        DecimalFormat df = new DecimalFormat("0.##");
        Map<String, Member> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String id = reader.next();
            double money = reader.nextDouble();
            if (map.containsKey(id)) {
				Member member = map.get(id);
				member.discount(money);
			}else {
				Member member = new Member(id);
				member.total = money;
				map.put(id, member);
			}
        }
        double total =0;
        for (String v : map.keySet()) {
			Member member = map.get(v);
			total += member.totalDiscount;
		}
        if(total == (int)total) {
        	sb.append((int)total);
        }else {
        	sb.append(df.format(total));
        }

        System.out.println(sb);
    }

	static class Member {
		String id;
		double totalDiscount;
		double total;

		public Member(String id) {
			super();
			this.id = id;
		}

		public void discount(double money) {
			double rate = 0;
			if (total < 1000000) {
				rate = 0;
			} else if (total < 20000000) {
				rate = money * 2 / 100;
			} else if (total < 50000000) {
				rate = money * 3 / 100;
			} else if (total < 200000000) {
				rate = money * 5 / 100;
			} else if (total >= 200000000) {
				rate = money * 7 / 100;
			}
			total += money;
			totalDiscount += rate;
		}
	}
