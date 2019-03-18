package com.hmily.leetcode;

import java.util.HashMap;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * 示例:
 *  给定 nums = [2, 7, 11, 15], target = 9
 *  因为 nums[0] + nums[1] = 2 + 7 = 9
 *  所以返回 [0, 1]
 */

public class TwoSum {

    public static void main(String[] args) {
        int[] res = twoSum(new int[]{1, 7, 3, 4, 8}, 9);
        System.out.println(res[0] + ", " + res[1]);
    }

    /**
     * 首先设置一个 map 容器 record 用来记录元素的值与索引，然后遍历数组 nums 。
     * 每次遍历时使用临时变量 complement 用来保存目标值与当前值的差值
     * 在此次遍历中查找 record ，查看是否有与 complement 一致的值，如果查找成功则返回查找值的索引值与当前变量的值i
     * 如果未找到，则在 record 保存该元素与索引值 i
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> tempNums = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (tempNums.containsKey(complement)){
                return new int[]{tempNums.get(complement), i};
            }
            tempNums.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum TwoSum");
    }
}

