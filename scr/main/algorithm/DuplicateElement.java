package algorithm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 存在重复元素
 *
 * 给定一个整数数组，判断是否存在重复元素。
 * 如果任意一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
 * 示例 1:
 * 输入: [1,2,3,1]
 * 输出: true
 * 示例 2:
 * 输入: [1,2,3,4]
 * 输出: false
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x248f5/
 * 来源：力扣（LeetCode）
 */
public class DuplicateElement {
    /**
     * 哈希表解法
     * @param nums
     * @return
     */
    public static boolean containsDuplicate(int[] nums) {
        if(nums.length <2)return false;
        Set<Integer> set = new HashSet<Integer>();
        for(int i=0;i<nums.length;i++){
            if(set.contains(nums[i]))return true;
            set.add(nums[i]);
        }
        return false;
    }

    public static boolean containsDuplicate2(int[] nums){
        if(nums.length < 2)return false;
        Arrays.sort(nums);
        int duplicate = nums[0];
        for(int i=1;i< nums.length;i++){
            if(duplicate == nums[i]){
                return true;
            } else {
                duplicate = nums[i];
            }

        }
        return false;
    }
}
