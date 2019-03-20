package com.hmily.leetcode;

import java.util.HashSet;

/**
 * 给定两个数组，编写一个函数来计算它们的交集(结果中不包含重复的)
 *
 * 遍历 num1，通过 set 容器 record 存储 num1 的元素
 * 遍历 num2，在 record 中查找是否有相同的元素，如果有，用 set 容器 resultSet 进行存储
 * 将 resultSet 转换为 int 类型
 */
public class ArrayIntersection {
    public static void main(String[] args) {
        int[] res = intersection(new int[]{1, 2, 3, 56, 34, 4123, 5342, 5, 2}, new int[]{2, 3, 4, 5});
        for (int num : res) {
            System.out.println(num);
        }
    }

    public static int[] intersection(int[] arr1, int[] arr2){
        HashSet<Integer> record = new HashSet<Integer>(arr1.length);
        for (int num: arr1) {
            record.add(num);
        }
        HashSet<Integer> res = new HashSet<Integer>(arr1.length);
        for (int current : arr2) {
            if (record.contains(current)) {
                res.add(current);
            }
        }
        Integer[] resInteger = res.toArray(new Integer[]{});
        int[] result = new int[resInteger.length];
        for (int i = 0; i < resInteger.length; i++) {
            result[i] = resInteger[i].intValue();
        }
        return result;
    }
}
