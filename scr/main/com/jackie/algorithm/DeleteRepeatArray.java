package main.com.jackie.algorithm;

/**
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2gy9m/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
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
     *  算法过程：
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        int result = 0;
        int removeTimes = 0;
        //考虑数组只有1个数字的情况
        int duplicate = nums[0];
        for(int i = 1;i<nums.length ; i++){
           if(duplicate == nums[i]){
               for(int j=i;j< nums.length; j++){
                   nums[i-1] = nums[j];
               }
               removeTimes ++;
           }
            duplicate = nums[i];
        }
        return nums.length - removeTimes;
    }
}
