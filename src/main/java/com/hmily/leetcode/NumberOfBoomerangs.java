package com.hmily.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定平面上 n 对不同的点，“回旋镖” 是由点表示的元组 (i, j, k) ，
 * 其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。
 * 找到所有回旋镖的数量。你可以假设 n 最大为 500，所有点的坐标在闭区间 [-10000, 10000] 中。
 * 示例:
 * 输入:  [[0,0],[1,0],[2,0]]
 * 输出:  2
 * 解释: 两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
 *
 * 1.分别计算每两个点之间的距离，用二维数组存起来
 * 2.分别将每个点作为题目元组中的i，找到与i距离相等的点，
 * 如果距离相等的点个数count大于1，则总数加上A（count，2）（即为count*（count-1））
 */
public class NumberOfBoomerangs {
    public static void main(String[] args) {
        int[][] points = new int[][]{{0, 0}, {1, 0}, {2, 0}};
        System.out.println(numberOfBoomerangs(points));
    }

    public static int numberOfBoomerangs(int[][] points) {
        int n = points.length;//点的个数
        int[][] lens = new int[n][n];//点与点之间的距离
        int temp = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                temp = (int) (Math.pow(points[i][0] - points[j][0], 2) + Math.pow(points[i][1] - points[j][1], 2));
                lens[i][j] = temp;
                lens[j][i] = temp;
            }
        }
        //从第一个点开始
        int sum = 0;
        for (int i = 0; i < n; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < n; j++) {
                if (j == i)
                    continue;
//                System.out.println("i:"+i+" j:"+j+"  "+lens[i][j]);
                if (map.containsKey(lens[i][j])) {
                    map.put(lens[i][j], map.get(lens[i][j]).intValue() + 1);
                } else {
                    map.put(lens[i][j], 1);
                }
            }
            int count = 0;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                count = entry.getValue();
                if (count >= 2) {
                    sum += (count) * (count - 1);
                }
            }
        }
        return sum;
    }

}
