package com.techelevator;

import javax.swing.text.StyledEditorKit;
import java.math.BigDecimal;

public class VendingMachine {
    private BigDecimal balance;
    private Inventory inventory;
    private BigDecimal totalAmountSpent;

    public VendingMachine() {
        this.balance = new BigDecimal("0.00");
        this.inventory = new Inventory();
        inventory.createItems();
        this.totalAmountSpent = new BigDecimal("0.00");
    }

    public void addBalance(BigDecimal amountToAdd) {
        this.balance = balance.add(amountToAdd);
    }

    public void subtractBalance(Item itemToPurchase) {
        this.balance = balance.subtract(new BigDecimal(itemToPurchase.getPrice()));
    }

    public boolean purchase(Item itemToPurchase) {
        boolean successfulPurchase = false;

        if (balance.compareTo(new BigDecimal(itemToPurchase.getPrice())) < 0) {
            System.out.println("Not enough funds");
        } else {
            if (inventory.getAmountPerItem().get(itemToPurchase) < 1) {
                System.out.println("Sorry, the item is sold out");
            } else {
                subtractBalance(itemToPurchase);
                inventory.subtractItem(itemToPurchase);
                totalAmountSpent = totalAmountSpent.add(new BigDecimal(itemToPurchase.getPrice()));
                successfulPurchase = true;
                switch(itemToPurchase.getCategory()){
                    case "Chip":
                        System.out.println("Crunch Crunch, Yum!");
                        break;
                    case "Candy":
                        System.out.println("Munch Munch, Yum!");
                        break;
                    case "Drink":
                        System.out.println("Glug Glug, Yum!");
                        break;
                    case "Gum":
                        System.out.println("Chew Chew, Yum!");
                        break;
                    default:
                        System.out.println("Unknown Category");
                        break;
                }
            }
        }
        return successfulPurchase;
    }

    public String calculateChange(){
        int quarters;
        int dimes;
        int nickels;
        BigDecimal remainder;
        quarters = balance.divide(new BigDecimal("0.25")).intValue();
        remainder = balance.remainder(new BigDecimal("0.25"));

        dimes = remainder.divide(new BigDecimal("0.10")).intValue();
        remainder = remainder.remainder(new BigDecimal("0.10"));

        nickels = remainder.divide(new BigDecimal("0.05")).intValue();

        balance = BigDecimal.ZERO;
        return ("Quarters: " + quarters + " Dimes: " + dimes + " Nickels: " + nickels);

    }

    public Inventory getInventory(){
        return inventory;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public BigDecimal getTotalAmountSpent() {
        return totalAmountSpent;
    }
}
