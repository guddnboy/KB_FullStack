public class Max_04 {
    public static void main(String[] args) {
        int [] array = {1,5, 3, 8, 2};
        int max = 0;
        for (int i : array) {
            if (i > max) {
                max = i;
            }
        }
        System.out.println(max);

        int [][] array2 ={{95, 86}, {83, 92, 96}, {78, 83, 93, 87, 88}};
        int sum = 0;
        int average = 0;
        int cnt = 0;
        for (int i=0; i < 3; i++){
            for(int j : array2[i]){
                sum += j;
                cnt += 1;
            }
        }
        average = sum/cnt;
        System.out.println(sum);
        System.out.println(average);
    }
}