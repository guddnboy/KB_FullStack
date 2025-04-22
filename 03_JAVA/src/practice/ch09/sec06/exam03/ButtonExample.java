package ch09.sec06.exam03;
public class ButtonExample {
    public static void main(String[] args) {
//Ok 버튼객체생성
        Button btnOk = new Button();
        Button.ClickListener clickListener = new Button.ClickListener() {
            @Override
            public void onClick() {
                System.out.println("OK 버튼을 클릭했습니다.");
            }
        };
        btnOk.setClickListener(clickListener);
//Ok 버튼클릭하기
        btnOk.click();
    }
}