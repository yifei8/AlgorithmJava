package com.sjtu.yifei.algorithm;

import java.util.HashMap;

/**
 * [description]
 * author: yifei
 * created at 18/12/21 下午11:42
 */
public class Test {

    public static void main(String[] strings) {
        int a[] = {10, -2, 5, 8, -4, 2, -3, 7, 12, -88, -23, 35};
//        int a[] = {-2, 10, 5, 8, -4, 2, -3, 7, 12, -88, -23, 35};
//        int a[] = {-2, 10, 5, 8, -4, 2, -3, 7, 12, -88, -23, 35};
//        a = setParted1(a);
        setParted3(a);
        printArr(a);
        System.out.println("expect result : {-2,-4,-3,-88,-23,10,5,8,2,7,12,35}");

    }

    private static int[] setParted1(int[] a) {
        int[] above0 = new int[a.length];
        int[] below0 = new int[a.length];
        int ai = 0, bi = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] < 0) {
                below0[ai++] = a[i];
            } else {
                above0[bi++] = a[i];
            }
        }
        for (int j = 0; j < bi; j++) {
            below0[ai++] = above0[j];
        }
        return below0;
    }

    public static void setParted2(int[] a) {
        int temp = 0;
        int border = -1;

        for (int i = 0; i < a.length; i++) {
            if (a[i] < 0 && i > border) {
                temp = a[i];
                a[i] = a[border + 1];
                a[border + 1] = temp;
                border++;
            }
        }


    }

    private static void setParted3(int[] a) {
        int i = 0;//指向当前大于0 的第一个数的下标
        int j = -1;//指向当前小于0 的第一个数的下标
        while (i < a.length) {
            if (a[i] < 0) {
                swap(a, i, j + 1);
                j ++;
            }
            i++;
        }
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void printArr(int[] arr) {
        System.out.print(" truth result : {");
        for (int i : arr) {
            System.out.print(i + ",");
        }
        System.out.println("}");
    }
}
