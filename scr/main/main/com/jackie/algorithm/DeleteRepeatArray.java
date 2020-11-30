package main.com.jackie.algorithm;

/**
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2gy9m/
 * 来源：力扣（LeetCode）
 */
public class DeleteRepeatArray {
    public static void main(String[] args) {
         int[] array = new int[]{0,0,1,1,1,2,2,3,3,4};
         int [] test2 = new int[]{1,1,2};
         int result ;
         result = removeDuplicates(array);
        System.out.println(result);
    }

    /**
     *  [0,0,1,1,1,2,2,3,3,4]  [1,1,2]
     *  算法过程：双指针
     *  快指针比慢指针快一步，当快指针指向了不同的元素，则把他添加到慢指针上
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        int fast = 1;
        int slow = 0;
        while(fast < nums.length){
            if(nums[fast] == nums[slow]){
                fast ++;
            } else {
                nums[++ slow ] = nums[fast];
            }
        }

        return slow ;
    }
}
