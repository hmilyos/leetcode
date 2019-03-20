package com.hmily.leetcode;

import java.util.HashMap;

/**
 * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
 * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。
 * 所有整数的范围在 -2^28 到 2^28- 1 之间，最终结果不会超过 2^31 - 1
 *
 * 把 A 和 B 的两两之和都求出来，在哈希表中建立两数之和与其出现次数之间的映射；
 * 遍历 C 和 D 中任意两个数之和，只要看哈希表存不存在这两数之和的相反数就行了。
 */
public class FourSum {

    public static void main(String[] args) {
        System.out.println(fourSumCount(new int[]{1, 2}, new int[]{-2, -1}, new int[]{-1, 2}, new int[]{0, 2}));
    }

    public static int fourSumCount(int[] a, int[] b, int[] c, int[] d) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int curI : a) {
            for (int curJ : b) {
                map.put(curI + curJ, map.getOrDefault(curI + curJ, 0) + 1);
            }
        }
        int count = 0;
        for (int curI : c) {
            for (int curJ : d) {
                count += map.getOrDefault(-curI - curJ, 0);
            }
        }
        return count;
    }
}
