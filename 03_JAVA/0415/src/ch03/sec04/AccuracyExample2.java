package ch03.sec04;
public class AccuracyExample2 {
    public static void main(String[] args) {
        int apple = 1;
        int totalPieces = apple * 10;
        int number = 7;
        int result = totalPieces - number;
        System.out.println("10조각에서남은조각: " + result);
        System.out.println("사과1개에서남은양: " + result/10.0);
    }
}