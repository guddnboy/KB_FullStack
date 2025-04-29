package ch14.ex1;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Random;


@Data
@AllArgsConstructor
public class Player implements Runnable{
    private String name;

    private final Random random = new Random();

    @Override
    public void run() {
        try{
            int curr = Main.count;

            Thread.sleep(random.nextInt(5) * 200 + 1000);

            while(curr != Main.count){
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}