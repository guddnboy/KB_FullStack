package 심화;

public  class CalculateArea {
    public static void main(String[] args) {
        float top = 5;
        float bottom = 10;
        float height = 7;
        float area = (top + bottom) * height / 2;

        System.out.printf("%-10.2f",area);
    }
}