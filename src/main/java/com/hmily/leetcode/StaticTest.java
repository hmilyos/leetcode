package com.hmily.leetcode;

/**
 * 赋值顺序：
 * 父类的静态变量赋值
 * 自身的静态变量赋值
 * 父类成员变量赋值和父类块赋值
 * 父类构造函数赋值
 * 自身成员变量赋值和自身块赋值
 * 自身构造函数赋值
 * 但是：实例初始化不一定要在类初始化结束之后才开始初始化
 * https://mp.weixin.qq.com/s/HvElZRVf_iFAh24cwuFu-Q
 *
 * 2
 * 3
 * a=110,b=0
 * 1
 * 4
 */
public class StaticTest {
    public static void main(String[] args) {
        staticFunction();
    }
    static StaticTest st = new StaticTest();
    static {
        System.out.println("1");
    }
    {
        System.out.println("2");
    }
    StaticTest() {
        System.out.println("3");
        System.out.println("a=" + a + ",b=" + b);
    }
    public static void staticFunction() {
        System.out.println("4");
    }
    int a = 110;
    static int b = 112;
}
