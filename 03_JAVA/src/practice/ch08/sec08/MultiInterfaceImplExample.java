package ch08.sec08;

public class MultiInterfaceImplExample{
    public static void main(String[] args){

        SmartTelevision rc =new SmartTelevision();

        rc.turnOn();
        rc.turnOff();

        Searchable searchable = new SmartTelevision();
        searchable.search("http://www.youtube.com");

    }
}