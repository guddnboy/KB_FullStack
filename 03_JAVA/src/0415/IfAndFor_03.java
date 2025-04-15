public class IfAndFor_03 {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (4 * i + 5 * j == 60){
                    System.out.printf("(%d, %d)\n", i, j);
                }
            }
        }
    }
}