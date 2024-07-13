package model;

import exceptions.InvalidException;

import java.util.*;

public class Input_Output {

    public Item input(String [] arg){
        Map<String,String> isPresent=new HashMap<>();
        Item item = null;
        try{
            if (!arg[0].equalsIgnoreCase("-name")){
                throw new InvalidException("First Argument must be name");
            }
            int length = arg.length;
            if(length%2==1){
                throw new InvalidException("Incomplete argument exception");
            }
            for (int i = 0; i < length; i+=2){
                if(isPresent.containsKey(arg[i])){
                    throw new InvalidException("Duplicate field not allowed");
                }else if(arg[i].charAt(0)!='-'){
                    throw new InvalidException("Wrong field exception");
                }
                isPresent.put(arg[i],arg[i+1]);
            }
            if(isPresent.containsKey("-type")){
                String name=isPresent.get("-name");
                String itemType=isPresent.get("-type");
                int quantity=0;
                int price=0;
                if(isPresent.containsKey("-quantity")){
                    quantity= Integer.parseInt(isPresent.get("-quantity"));
                }
                if(isPresent.containsKey("-price")) {
                    price = Integer.parseInt(isPresent.get("-price"));
                }
                if (itemType.equalsIgnoreCase("raw")){
                    item = new ItemTypeRaw().setName(name).setPrice(price).setQuantity(quantity).setType().setTax().calculateFinalPrice();
                }else if(itemType.equalsIgnoreCase("manufactured")){
                    item = new ItemTypeManufactured().setName(name).setPrice(price).setQuantity(quantity).setType().setTax().calculateFinalPrice();
                }else if(itemType.equalsIgnoreCase("imported")){
                    item = new ItemTypeImported().setName(name).setPrice(price).setQuantity(quantity).setType().setTax().calculateFinalPrice();
                }else{
                    throw new InvalidException("Invalid value in Item");
                }
            }else{
                throw new InvalidException("Item type not Found");
            }
        }catch (InvalidException e){
            System.out.println(e);
        }catch (Exception e){
            System.out.println(e);
        }
        return item;
    }
    public void output(ArrayList<Item> items){
        int itemsLength = items.toArray().length;
        System.out.println("Here is your Items : ");
        for (int i = 0; i < itemsLength; i++){
            System.out.print("Name : "+items.get(i).getName()+"  ");
            System.out.print("Price : "+items.get(i).getPrice()+"  ");
            System.out.print("Type : "+items.get(i).getType()+"  ");
            System.out.print("Quantity : "+items.get(i).getQuantity()+"  ");
            System.out.print("Tax : "+items.get(i).getTax()+"  ");
            System.out.print("Final Price : "+items.get(i).getFinalPrice()+"  ");
            System.out.println();
        }
        System.out.println("Thank you for using our service");
    }
}
// java Main -name ayush -price 100 -type raw -quantity 19
// -name ash -price 400 -type manufactured -quantity 191
// -name sh -price 10 -type imported -quantity 119