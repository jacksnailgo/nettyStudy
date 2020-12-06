package java.com.algorithm;

/**
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 *
 * 示例 1:
 *
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2skh7/
 */
public class RotateArray {

    public static void main(String[] args) {
        int[] array = new int[]{1,2,3,4,5,6,7};
        int [] test2 = new int[]{1,1,2};
         rotateArray(array,3);
        for(int i =0;i<array.length;i++){
            System.out.println(array[i]);
        }
    }

    private static void rotateArray(int[] array,int moveRightTimes) {
        if(moveRightTimes <=0) return;
        int len = array.length;
        if(len < 2)return;
        if(moveRightTimes > len){
            moveRightTimes %=len;
        } else if(moveRightTimes == len){
            return;
        }
        for(int i=0;i<moveRightTimes;i++){
            int last = array[len-1];
            for(int j=i+1;j<len;j++){

            }
            array[0] = last;
        }

    }
}
