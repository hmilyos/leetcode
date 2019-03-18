package com.hmily.leetcode;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度
 *
 * 建立一个 256 位大小的整型数组 freg ，用来建立字符和其出现位置之间的映射。
 * 维护一个滑动窗口，窗口内的都是没有重复的字符，去尽可能的扩大窗口的大小，窗口不停的向右滑动。
 * （1）如果当前遍历到的字符从未出现过，那么直接扩大右边界；
 * （2）如果当前遍历到的字符出现过，则缩小窗口（左边索引向右移动），然后继续观察当前遍历到的字符；
 * （3）重复（1）（2），直到左边索引无法再移动；
 * （4）维护一个结果 res，每次用出现过的窗口大小来更新结果 res ，最后返回 res 获取结果。
 */
public class MaxString {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcfabcdbb"));
    }
    public static int lengthOfLongestSubstring(String s) {
        int[] freq = new int[256];
        int left = 0, right = -1;
        int res = 0;
        while (left < s.length()){
            if (right + 1 < s.length() && freq[s.charAt(right + 1)] == 0){
                right++;
                freq[s.charAt(right)]++;
            } else {
                freq[s.charAt(left)]--;
                left++;
            }
            res = right - left + 1 > res ? right - left + 1 : res;
        }
        return res;
    }

}
