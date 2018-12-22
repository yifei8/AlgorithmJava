package com.sjtu.yifei.algorithm;

public class SortAlgorithm {

    private static SortAlgorithm instance;

    public static SortAlgorithm getInstance() {
        if (instance == null) {
            synchronized (SortAlgorithm.class) {
                if (instance == null) {
                    instance = new SortAlgorithm();
                }
            }
        }
        return instance;
    }

    /**
     * 计数排序
     * {4,-5,3,-1,6,3,5,1};
     *
     * @param arr
     */
    public int[] countSort(int arr[]) {
        int min = 0, max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        int C[] = new int[max - min + 1];
        for (int i = 0; i < arr.length; i++) {
            C[arr[i] - min]++;
        }

        int B[] = new int[arr.length];
        for (int i = 0, j = 0; i < C.length; i++) {
            while (C[i] > 0) {
                B[j ++] = i - min;
                C[i]--;
            }
        }
        return B;
    }

    /**
     * 快速排序
     *
     * @param arr
     */
    public void quickSort(int arr[]) {
        int left = 0;
        int right = arr.length - 1;
        quickSort(arr, left, right);
    }

    private void quickSort(int arr[], int left, int right) {
        if (right - left < 1) {
            return;
        }
        int baseIndex = partQuickSort(arr, left, right);
        quickSort(arr, left, baseIndex - 1);
        quickSort(arr, baseIndex + 1, right);
    }

    private int partQuickSort(int arr[], int left, int right) {
        int indicate = left;//切分的数字下标
        while (left < right) {
            if (indicate == left) {
                if (arr[right] > arr[indicate]) {
                    right--;
                } else {
                    swap(arr, indicate, right);
                    indicate = right;
                }
            } else {
                if (arr[left] < arr[indicate]) {
                    left++;
                } else {
                    swap(arr, indicate, left);
                    indicate = left;
                }
            }
        }
        return indicate;
    }

    /**
     * 归并排序（Merge Sort） O(n log n)
     * <p>
     * 归并排序是建立在归并操作上的一种有效的排序算法。
     * 该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
     * 将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，
     * 再使子序列段间有序。若将两个有序表合并成一个有序表，称为2-路归并。
     * <p>
     * 归并排序是一种稳定的排序方法。和选择排序一样，归并排序的性能不受
     * 输入数据的影响，但表现比选择排序好的多，因为始终都是O(nlogn）的
     * 时间复杂度。代价是需要额外的内存空间。
     *
     * @param arr
     * @return
     */
    public int[] mergeSort(int arr[]) {
        if (arr.length < 2) {
            return arr;
        }
        int slice = arr.length / 2;
        int[] leftArr = new int[slice];
        int[] rightArr = new int[arr.length - slice];
        for (int i = 0; i < arr.length; i++) {
            if (i < slice) {
                leftArr[i] = arr[i];
            } else {
                rightArr[i - slice] = arr[i];
            }
        }
        return merge(mergeSort(leftArr), mergeSort(rightArr));
    }

    private int[] merge(int[] leftArr, int[] rightArr) {
        int[] result = new int[leftArr.length + rightArr.length];
        int count = 0, i = 0, j = 0;
        for (; i < leftArr.length && j < rightArr.length; count++) {
            if (leftArr[i] < rightArr[j]) {
                result[count] = leftArr[i++];
            } else {
                result[count] = rightArr[j++];
            }
        }
        for (; i < leftArr.length; i++) {
            result[count] = leftArr[i];
            count++;
        }
        for (; j < rightArr.length; j++) {
            result[count] = rightArr[j];
            count++;
        }
        return result;
    }

    /**
     * 希尔排序，也称递减增量排序算法，实质是分组插入排序。
     * <p>
     * 希尔排序的基本思想是：将数组列在一个表中并对列分别进行插入排序，
     * 重复这过程，不过每次用更长的列（步长更长了，列数更少了）来进行。
     * 最后整个表就只有一列了。将数组转换至表是为了更好地理解这算法，算
     * 法本身还是使用数组进行排序。
     * <p>
     * 其时间复杂度为O(n^3/2),要好于直接插入排序的O(n^2)
     *
     * @param arr
     */
    public void shellSort(int arr[]) {
        for (int increment = arr.length / 2; increment > 0; increment = increment / 2) {
            for (int i = increment; i < arr.length; i++) {
                for (int j = i; j >= increment; j = j - increment) {
                    if (arr[j] < arr[j - increment]) {
                        swap(arr, j, j - increment);
                    } else {
                        break;
                    }
                }
            }
        }
    }

    /**
     * 插入排序
     * <p>
     * 插入排序（Insertion-Sort）的算法描述是一种简单直观的排序算法。
     * 它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从
     * 后向前扫描，找到相应位置并插入
     *
     * @param arr
     */
    public void insertionSort(int arr[]) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

    /**
     * 选择排序
     * <p>
     * 选择排序(Selection-sort)是一种简单直观的排序算法。它的工作原理：
     * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，再从
     * 剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，
     * 直到所有元素均排序完毕。
     *
     * @param arr
     */
    public void selectionSort(int[] arr) {
        if (arr.length > 0) {
            for (int i = 0; i < arr.length - 1; i++) {
                int smallestIndex = i;
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[j] < arr[smallestIndex]) {
                        smallestIndex = j;
                    }
                }
                swap(arr, i, smallestIndex);
            }
        }
    }

    /**
     * 冒泡排序
     * <p>
     * 冒泡排序是一种简单的排序算法。它重复地走访过要排序的数列，一次比较两个元素，
     * 如果它们的顺序错误就把它们交换过来。走访数列的工作是重复地进行直到没有再需
     * 要交换，也就是说该数列已经排序完成。这个算法的名字由来是因为越小的元素会经
     * 由交换慢慢“浮”到数列的顶端。
     *
     * @param arr
     */
    public void bubbleSort(int[] arr) {
        if (arr.length > 1) {
            for (int j = arr.length - 1; j > 0; j--) {
                for (int i = 0; i < j; i++) {
                    if (arr[i] > arr[i + 1]) {
                        swap(arr, i, i + 1);
                    }
                }
            }
        }
    }

    /**
     * 将数组arr中位置i和位置j的数据互换一下
     *
     * @param arr
     * @param i
     * @param j
     */
    private void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
