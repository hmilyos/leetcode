package com.hmily.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 所有 DNA 由一系列缩写为 A，C，G 和 T 的核苷酸组成，例如：“ACGAATTCCG”。
 * 在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。
 * 编写一个函数来查找 DNA 分子中所有出现超过一次的 10 个字母长的序列（子串）
 *
 * 首先，先将  A , C , G , T 的 ASCII 码用二进制来表示：
 * A: 0100 0001　　C: 0100 0011　　G: 0100 0111　　T: 0101 0100
 * 通过观察发现每个字符的后三位都不相同，因此可以用末尾的三位来区分这四个字符。
 * 题目要求是查找 10 个字母长的序列，这里我们将每个字符用三位来区分的话，10 个字符就需要 30 位 ，在32位机上也 OK 。
 * 为了提取出后 30 位，需要使用 mask ，取值为 0x7ffffff（二进制表示含有 27 个 1） ，先用此 mask 可取出整个序列的后 27 位，然后再向左平移三位可取出 10 个字母长的序列 （ 30 位）。
 * 为了保存子串的频率，这里使用哈希表。
 * 首先当取出第十个字符时，将其存在哈希表里，和该字符串出现频率映射，之后每向左移三位替换一个字符，查找新字符串在哈希表里出现次数，如果之前刚好出现过一次，则将当前字符串存入返回值的数组并将其出现次数加一，如果从未出现过，则将其映射到 1。
 */
public class RepeatedDNA {

    public static void main(String[] args) {
        List<String> res = findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT");
        for (String s : res) {
            System.out.println(s);
        }
    }

    public static List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<>();
        Map<Integer, Integer> sumMap = new HashMap<>();
        int sum = 0;
        for(int i=0; i<s.length(); i++) {
            sum = ((sum << 3) | (s.charAt(i) & 7)) & 0x3FFFFFFF;
            if(i < 9) continue;
            Integer cnt = sumMap.get(sum);
            if(cnt != null && cnt == 1) {
                result.add(s.substring(i-9, i+1));
            }
            sumMap.put(sum, cnt != null ? cnt+1 : 1);
        }
        return result;
    }
}
