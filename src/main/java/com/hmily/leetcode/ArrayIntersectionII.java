package com.hmily.leetcode;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 交集，但是
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2,2]
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [4,9]
 */
public class ArrayIntersectionII {
    public static void main(String[] args) {
//        int[] res = intersection(new int[]{1, 2, 2, 1}, new int[]{2, 2});
        int[] res = intersection(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4});
        for (int num : res) {
            System.out.println(num);
        }
    }

    public static int[] intersection(int[] arr1, int[] arr2){
        HashMap<Integer, Integer> record = new HashMap<>(arr1.length);
        for (int current : arr1) {
            if (record.containsKey(current)){
                record.put(current, record.get(current) + 1);
            } else {
                record.put(current, 0);
            }
        }
        ArrayList<Integer> resTemp = new ArrayList<Integer>();
        for (int current : arr2) {
            if (record.containsKey(current)){
                resTemp.add(current);
                if (record.get(current) > 0){
                    record.put(current, record.get(current) - 1);
                } else {
                    record.remove(current);
                }
            }
        }
        Integer[] resInteger = resTemp.toArray(new Integer[]{});
        int[] result = new int[resInteger.length];
        for (int i = 0; i < resInteger.length; i++) {
            result[i] = resInteger[i].intValue();
        }
        return result;
    }
}
