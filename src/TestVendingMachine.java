import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class TestVendingMachine {
    static Scanner input=new Scanner(System.in);
    public static void main(String[] args) {
        VendingMachine.VendingMachineBuilder vmBuilder= new VendingMachine().new VendingMachineBuilder();
        Currency currency=Currency.DOLLAR;
        vmBuilder.buildCurrruncy(currency);     // Set currency of vending machine

        HashMap<Coins,Integer> coins=new HashMap<Coins,Integer>(){{
            put(Coins.FIFTY_DOLLAR,2);
            put(Coins.TWENTY_DOLLAR,2);
            put(Coins.ONE_DOLLAR, 19);
            put(Coins.FIFTY, 1);
            put(Coins.TWENTY, 2);
            put(Coins.TEN, 3);
        }};

        vmBuilder.buildCoins(coins); // set coins to vending machine

        Random random=new Random();
        ArrayList<Cash> cash=new ArrayList<Cash>(){{
            add(new Cash(70,Currency.CENT));
            add(new Cash(30,Currency.CENT));
            add(new Cash(1,Currency.DOLLAR));
            add(new Cash(50,Currency.CENT));
            add(new Cash(10,Currency.CENT));
        }};

        ArrayList<Item> items=new ArrayList<Item>(){{
            add(new Item("Doritos",cash.get(0)));
            add(new Item("Lays",cash.get(1)));
            add(new Item("Oreo",cash.get(2)));
            add(new Item("m&m",cash.get(3)));
            add(new Item("Resees",cash.get(4)));
        }};

        HashMap<Item,Integer> vendingItems =new HashMap<Item,Integer>(){{
            put(items.get(0),random.nextInt(9)+1);
            put(items.get(1),random.nextInt(9)+1);
            put(items.get(2),random.nextInt(9)+1);
            put(items.get(3),random.nextInt(9)+1);
            put(items.get(4),random.nextInt(9)+1);
        }};

        vmBuilder.buildItems(vendingItems);

        Cash vendingCardBalance=new Cash(200,currency);     // here we can changed the balanced of vendingMachine card
        vmBuilder.buildVendingCardBalanaced(vendingCardBalance);

        VendingMachine vendingMachine= vmBuilder.build();

        boolean flage=true;
        while (flage){
            System.out.println("Welcome to Vending Machine : ");
            System.out.println("press 0 to end process .");
            System.out.println("press 1 to use Machine As Admin .");
            System.out.println("press 2 to use Machine As customer .");
            int typeOfUsing = input.nextInt();
            switch (typeOfUsing){
                case 0:
                    flage=false;
                    break;

                case 1:
                    adminUsing(vendingMachine);
                    break;
                case 2:
                    custmorUsing(vendingMachine);
                    break;
                default:
                    System.out.println("Enter The Valid number between choice .");
            }
        }
    }

    public static void custmorUsing(VendingMachine vendingMachine) {
        boolean flage = true;
        while (flage) {
            System.out.println("\nChoose The operation you want to do :");
            System.out.println("Enter 0 to End Operation .");
            System.out.println("Enter 1 to show Item . ");
            System.out.println("Enter 2 to buy Items .");
            System.out.println();
            int operation = input.nextInt();
            switch (operation) {
                case 0:
                    vendingMachine.endOperation();
                    flage = false;
                    break;

                case 1:
                    vendingMachine.showItems();
                    break;

                case 2:
                    System.out.println("Enter The id of Item you want to buy: ");
                    vendingMachine.showItems();
                    int itemId = input.nextInt();
                    Item orderItem = vendingMachine.getItem(itemId);
                    if (orderItem != null) {
                        System.out.println("\nchoose the a of pay money:");
                        System.out.println("Enter 1 to pay by Cash .");
                        System.out.println("Enter 2 to pay by Card .");
                        int paymentId = input.nextInt();
                        Payment orderPayment = null;

                        switch (paymentId) {
                            case 1:
                                orderPayment = new CashPayment();
                                HashMap<Coins, Integer> coinsEntered = Coins.EMPTY.getEmptyCoins();
                                double balanced = 0;
                                while (true) {
                                    if (balanced / 100 < 1)
                                        System.out.println("            EnteredBalanced= " + balanced + " c");
                                    else
                                        System.out.println("            EnteredBalanced = " + balanced / 100 + " $");

                                    System.out.println("\nEnter the amount of cash you want to Enter and -1 to exit :");
                                    Integer amount = input.nextInt();
                                    if (amount == -1) {
                                        break;
                                    } else {
                                        while (true) {
                                            System.out.println("Enter 1 for cent .");
                                            System.out.println("Enter 2 for dollar .");
                                            Integer curruncynum = input.nextInt();

                                            if (curruncynum == 1) {
                                                amount = amount;
                                                break;
                                            } else if (curruncynum == 2) {
                                                amount *= 100;
                                                break;
                                            }
                                        }
                                        if (containCoins(amount) != null) {
                                            Coins coin = containCoins(amount);
                                            coinsEntered.put(coin, coinsEntered.get(coin) + 1);
                                            balanced += amount;
                                        } else {
                                            System.out.println("You should Enter amount from these value" +
                                                    Coins.TEN.toString());
                                        }
                                    }
                                }

                                ((CashPayment) orderPayment).setCurrency(vendingMachine.getCurrency());
                                ((CashPayment) orderPayment).setCashEntered(coinsEntered);
                                break;

                            case 2:
                                orderPayment = new CardPayment();
                                break;
                            default:
                                System.out.println("Please Choose the valid number between choice to determine payment type .");
                        }

                        if (orderPayment != null) {
                            vendingMachine.setOrder(orderItem, orderPayment);
                            vendingMachine.push();
                        } else {
                            System.out.println("Please Enter The valid type of payment .");
                        }
                        break;
                    } else {
                        System.out.println("Please Enter The valid Id of Item .");
                    }
                default:
                    System.out.println("Please Enter the Valid number of choice .");
            }
        }
    }

    public static void adminUsing(VendingMachine vendingMachine){
        boolean flage=true;
        while (flage){
            System.out.println("\nChoose The Operation you want to do :");
            System.out.println("Enter 0 to end operation .");
            System.out.println("Enter 1 to add Item .");
            System.out.println("Enter 2 to delete item .");
            System.out.println("Enter 3 to add coins .");
            System.out.println("Enter 4 to show Item .");
            System.out.println("Enter 5 to show Coins .");
            System.out.println("Enter 6 to show Vending Card Balanced .");
            int operation=input.nextInt();
            switch (operation) {
                case 0:
                    flage = endOperationAdmin(vendingMachine);
                    break;
                case 1:
                    addItemAdmin(vendingMachine);
                    break;
                case 2:
                    deletItemAdmin(vendingMachine);
                    break;
                case 3:
                    addCoinsAdmin(vendingMachine);
                    break;
                case 4:
                    showItemAdmin(vendingMachine);
                    break;
                case 5:
                    vendingMachine.showCoins();
                    break;
                case 6:
                    System.out.println("The Vending Card Balanced ="+vendingMachine.getVendingCardBalance().toString());
                    break;
                default:
                    System.out.println("Please Enter the Number Between Choice .");
            }
        }
    }

    private static void showItemAdmin(VendingMachine vendingMachine) {
        int itemId=1;
        System.out.println("The Item In Vending Machine is :");
        HashMap<Item,Integer> items= vendingMachine.getItems();
        for (Item showItem:items.keySet()) {
            System.out.println(itemId+"- "+showItem.toString()+ " ,itemCount="+items.get(showItem)+" ]");
            itemId++;
        }
    }

    private static void addCoinsAdmin(VendingMachine vendingMachine) {
        System.out.println("Enter 1 to add 10c coins .");
        System.out.println("Enter 2 to add 20c coins .");
        System.out.println("Enter 3 to add 50c coins .");
        System.out.println("Enter 4 to add 1$ coins .");
        System.out.println("Enter 5 to add 20$ coins .");
        System.out.println("Enter 6 to add 50$ coins .");
        int addedcoins=input.nextInt();
        System.out.println("Enter the count of coins you want to add :");
        int countCoins=input.nextInt();
        if (addedcoins == 1){
            vendingMachine.addCoins(Coins.TEN,countCoins);
        }else
            if (addedcoins == 2){
            vendingMachine.addCoins(Coins.TWENTY,countCoins);
        }else
            if (addedcoins == 3){
            vendingMachine.addCoins(Coins.FIFTY,countCoins);
        }else
            if (addedcoins == 4){
            vendingMachine.addCoins(Coins.ONE_DOLLAR,countCoins);
        } else
            if (addedcoins==5) {
            vendingMachine.addCoins(Coins.TWENTY_DOLLAR,countCoins);
        } else
            if (addedcoins==6) {
        vendingMachine.addCoins(Coins.FIFTY_DOLLAR, countCoins);
        } else {
          System.out.println("You should add a number between choice");
        }
    }

    private static void deletItemAdmin(VendingMachine vendingMachine) {
        System.out.println("Enter the number Of Item you want to delete it :");
        vendingMachine.showItems();
        int itemDeleted=input.nextInt();
        vendingMachine.deleteItem(vendingMachine.getItem(itemDeleted));
    }

    private static void addItemAdmin(VendingMachine vendingMachine) {
        System.out.println("Enter the name of Item :");
        String itemName = input.next();
        input.nextLine();
        System.out.println("Enter the price of Item :");
        int itemPrice = input.nextInt();
        System.out.println("Enter the count of item you want to add :");
        int itemCount = input.nextInt();
        Currency currency;
        boolean f=true;
        while (f) {
            System.out.println("Enter 1 for Cent .");
            System.out.println("Enter 2 for Dollar .");
            int itemCurrnecy = input.nextInt();
            switch (itemCurrnecy){
                case 1:
                    currency=Currency.CENT;
                    f=false;
                    break;
                case 2:
                    currency=Currency.DOLLAR;
                    f=false;
                    break;
                default:
                    System.out.println("Please Enter 1 or 2 to set an item currency .");
            }
        }
        Item item=new Item(itemName,new Cash(itemPrice, vendingMachine.getCurrency()));
        vendingMachine.addItem(item,itemCount);
    }

    private static boolean endOperationAdmin(VendingMachine vendingMachine) {
        boolean flage;
        vendingMachine.endOperation();
        flage = false;
        return flage;
    }


    public static Coins containCoins(Integer value){
        ArrayList<Coins> coins=Coins.EMPTY.listCoins();
        for(Coins coin:coins) {
            if (coin.getCoins() == value)
                return coin;
        }
        return null;
    }

}
