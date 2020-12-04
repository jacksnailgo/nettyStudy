package algorithm;

public class AlogrithmMain {
    public static void main(String[] args) {
        int[] array = new int[]{1,2,3,4,5,6,7,2};
        int result = 0;

        System.out.println("answer is :" +result);
        boolean result2 = DuplicateElement.containsDuplicate2(array);
        System.out.println(result2);

    }
}
