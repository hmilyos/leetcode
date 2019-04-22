package com.hmily.leetcode;

/**
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * 思路：
 * 使用快慢指针来记录遍历的坐标。
 * 开始时这两个指针都指向第一个数字
 * 如果两个指针指的数字相同，则快指针向前走一步
 * 如果不同，则两个指针都向前走一步
 * 当快指针走完整个数组后，慢指针当前的坐标加1就是数组中不同数字的个数
 */
public class RepaceArraySize {

    public static void main(String[] args) {
        System.out.println("size: " + removeDuplicates(new int[]{1, 2, 3, 4, 5}));
        System.out.println("size: " + removeDuplicates(new int[]{1, 2, 2, 4, 4}));
    }

    public static int removeDuplicates(int[] arr){
        if (arr.length == 0){
            return 0;
        }
        int pre =  0, cur = 0, n = arr.length;
        while (cur < n){
            if (arr[pre] == arr[cur]){
                cur++;
            } else {
                ++pre;
                arr[pre] = arr[cur];
                cur++;
            }

        }

        return pre + 1;
    }
}
