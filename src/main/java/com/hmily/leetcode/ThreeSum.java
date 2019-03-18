package com.hmily.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ,
 * 找出所有满足条件且不重复的三元组
 *
 * 解题思路： 定义两个指针，将给定数组nums重新排列后
 *         定义start为起始指针，end为结束指针,假设i为两指针相加后的相反数
 *         for(int i = 0 ; i < nums.legth - 2){
 *         在其指针相间的数进行遍历,其起始的值start=i+1
 *         if nums[start] + nums[end] == -nums[i] , List添加这组值,
 *         不然 如果 3个值的和大于0时，end--使其值降低
 *         其中如果nums[start]++==nums[start]，则直接start++，end则是end--同理
 *         3值和小于0时，start++
 *          不然 start++,end-- ;直到 start指针 >= end指针后
 *          i++也要判断是否重复值;
 *         }
 *
 */
public class ThreeSum {

    public static void main(String[] args) {
        int nums[] = {-4, -2, 1, -5, -4, -4, 4, -2, 0, 4, 0, -2, 3, 1, -5, 0};
        List<List<Integer>> ends = threeSum(nums);
        for (List<Integer> i : ends) {
            System.out.println(i);
        }
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        //先给定的数组重排序
        Arrays.sort(nums);
        List<List<Integer>> allList = new ArrayList<List<Integer>>();
        //假设i为start指针与end指针的和
        for (int i = 0; i < nums.length - 2; ) {
            //start指针对应起始位置
            int start = i + 1;
            //end指针对应结束位置
            int end = nums.length - 1;
            while (start < end) {
                if (nums[start] + nums[end] == -nums[i]) {
                    List<Integer> list = new ArrayList<>(3);
                    list.add(nums[i]);
                    list.add(nums[start]);
                    list.add(nums[end]);
                    allList.add(list);
                    start++;
                    end--;
                    //除去end指针的重复值
                    while (nums[end] == nums[end + 1] && start < end) {
                        end--;
                    }
                    //除去start指针的重复值
                    while (nums[start] == nums[start - 1] && start < end) {
                        start++;
                    }
                }
                //3值的和大于0时，重新检测end指针是否重复后降值
                else if (nums[start] + nums[end] > -nums[i]) {
                    end--;
                    while (nums[end] == nums[end + 1] && start < end) {
                        end--;
                    }
                }
                //3值的和小于0时，重新检测start指针是否重复后升值
                else {
                    start++;
                    while (nums[start] == nums[start - 1] && start < end) {
                        start++;
                    }
                }
            }

            i++;
            while (nums[i] == nums[i - 1] && i < nums.length - 2) {
                i++;
            }
        }
        return allList;
    }
}
