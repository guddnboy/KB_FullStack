package ch14.sec00;

public class Bridge {
    int counter;
    String name;
    String address;

    public Bridge(){
        counter = 0;
        name = "ㅇㅏ무개";
        address = "모름";
    }

    public void across(String name, String address){
        counter++;
        this.name = name;
        this.address = address;
        check();
    }

    public void check(){
        if(name.charAt(0) != address.charAt(0)){
            System.out.println("문제 발생!!!"+ this);
        }
    }

    @Override
    public String toString() {
        return "Bridge{" +
                "counter = " + counter +
                ", name = '" + name + '\'' +
                ", address = '" + address + '\'' +
                '}';
    }
}