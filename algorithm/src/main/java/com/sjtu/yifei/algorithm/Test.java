package com.sjtu.yifei.algorithm;

/**
 * [description]
 * author: yifei
 * created at 18/12/14 下午10:29
 */
public class Test {

    private static final int TIMES = 1000000;

    public static void main(String[] strings) {
        System.out.print("冒泡排序");
        testSort(0);
        System.out.print("选择排序");
        testSort(1);
        System.out.print("插入排序");
        testSort(2);
        System.out.print("希尔排序");
        testSort(3);
        System.out.print("归并排序");
        testSort(4);
    }

    /**
     * 测试排序算法
     */
    private static void testSort(int sort_type) {
        int[] arr = getTestData();
        long time = System.currentTimeMillis();
        for (int i = 0; i < TIMES; i ++) {
            arr = getTestData();
            if (sort_type == 0) { // 冒泡排序
                SortAlgorithm.getInstance().bubbleSort(arr);
            } else if (sort_type == 1) {// 选择排序
                SortAlgorithm.getInstance().selectionSort(arr);
            } else if (sort_type == 2) {//插入排序
                SortAlgorithm.getInstance().insertionSort(arr);
            } else if (sort_type == 3) {// 希尔排序
                SortAlgorithm.getInstance().shellSort(arr);
            } else if (sort_type == 4) {// 归并排序
                arr = SortAlgorithm.getInstance().mergeSort(arr);
            }
        }
        System.out.print(" cost time ->" + (System.currentTimeMillis() - time));
        printArr(arr);
    }

    private static int[] getTestData() {
        return new int[]{2, 5, 10, 9, 1, 30, 11, 4, 12, 31, 0, 19, 21, 6, 13, 18, 7, 15, 20, 8};
    }

    private static void printArr(int[] arr) {
        System.out.print("，排序结果：");
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
