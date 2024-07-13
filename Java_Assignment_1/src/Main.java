import model.Input_Output;
import model.Item;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String isNextInput = "y";
        Input_Output in_out=new Input_Output();
        ArrayList<Item> items=new ArrayList<>();
        Scanner sc=new Scanner(System.in);
        do {
            Item item = in_out.input(args);
            if(item!=null){
                items.add(item);
            }
            System.out.println("Do you want to enter details of any other item (y/n):  ");
            isNextInput = sc.nextLine();
            if(isNextInput.equalsIgnoreCase("y")){
                String input = sc.nextLine();
                args = input.split(" ");
            }
        }while (isNextInput.equalsIgnoreCase("y"));
        in_out.output(items);
    }
}
