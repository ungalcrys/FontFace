package fontFace.util;

public class QuickSort {

	public static final boolean ORDER_ASCENDING = true;

	public static final boolean ORDER_DESCENDING = false;

	public static void sort(String[] array, int start, int end, boolean order) {
		// more than one element
		if (end > start) {
			String pivot = array[start];
			int left = start + 1;
			int right = end;

			boolean finished = false;
			while (!finished) {
				if (!order) {
					while (left <= end && array[left].compareToIgnoreCase(pivot) >= 0) {
						++left;
					}
					while (right > start && array[right].compareToIgnoreCase(pivot) < 0) {
						--right;
					}
				} else {
					while (left <= end && array[left].compareToIgnoreCase(pivot) <= 0) {
						++left;
					}
					while (right > start && array[right].compareToIgnoreCase(pivot) > 0) {
						--right;
					}
				}

				if (left >= right) {
					finished = true;
				} else {
					swap(array, left, right);
				}
			}

			// update pivot
			swap(array, start, right);
			// sort left and right partition
			sort(array, start, right - 1, order);
			sort(array, right + 1, end, order);
		}
	}

	public static void swap(String[] a, int x, int y) {
		String t = a[x];
		a[x] = a[y];
		a[y] = t;
	}

	public static void sort(String[] array, boolean order) {
		sort(array, 0, array.length - 1, order);
	}
}
