package com.algorithm;
import java.util.HashSet;
import java.util.Set;

/**
 * 只出现一次的数字
 *
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 示例 1:
 *
 * 输入: [2,2,1]
 * 输出: 1
 * 示例 2:
 * 输入: [4,1,2,1,2]
 * 输出: 4
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x21ib6/
 */
public class OnlyOnceNumber {
    /**
     * 使用额外的空间
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {
        int result = nums[0];
        if(nums.length <2)return result;
        Set<Integer> sets = new HashSet<Integer>();
        for(int i =0;i<nums.length;i++){
            sets.add(nums[i]);
        }
        for(int i=0;i<nums.length;i++){

        }
        return 0;
    }

    public static int singleNumber2(int[] nums) {
        int answer = nums[0];
        if(nums.length < 2)return answer;
        for(int i=1;i<nums.length;i++){
            answer ^= nums[i];
        }
        return answer;
    }
}
