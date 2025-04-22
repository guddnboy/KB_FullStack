package ch09.sec06.exam01;

public class Button {
    public static interface ClickListener {
        void onClick();
    }

    class OKListener implements Button.ClickListener{
        @Override
        public void onClick() {}
    }

}