package ch09.sec07.exam03;
public class Button {
    //정적멤버인터페이스
    public static interface ClickListener {
        //추상메소드
        void onClick();
    }
    //필드
    private ClickListener clickListener;
    //메소드
    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }
    public void click() {
        this.clickListener.onClick();
    }
}