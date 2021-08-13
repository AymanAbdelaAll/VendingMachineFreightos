import org.jetbrains.annotations.NotNull;


import java.util.ArrayList;
import java.util.HashMap;

public class VendingMachine {
    private HashMap<Item,Integer> items =new HashMap<Item,Integer>();
    private Order order=new Order();
    private Currency currency;
    private Cash vendingCardBalance;
    private HashMap<Coins,Integer> coins=new HashMap<Coins,Integer>();


    public VendingMachine() {
    }

    public VendingMachine(@NotNull HashMap<Item, Integer> items,@NotNull Order order,
                          @NotNull Currency currency,@NotNull Cash vendingCardBalance,
                          @NotNull HashMap<Coins, Integer> coins) {
        this.items = items;
        this.order = order;
        this.currency = currency;
        this.vendingCardBalance = vendingCardBalance;
        this.coins = coins;
    }

    public HashMap<Item, Integer> getItems() {
        return items;
    }

    public void setItems(@NotNull HashMap<Item, Integer> items) {
        this.items = items;
    }

    public boolean addItem(@NotNull Item item,@NotNull Integer count){
        if (count<0||count>15){
            System.out.println("you should Enter The count of Item between 0-15 .");
            return false;
        }else if (!items.containsKey(item)){
            if (items.size()<5){
            items.put(item,count);
            System.out.println("The Item has been added");
            return true;
            } else{
                System.out.println("OOPs ,The Vending Machine Is Full !!");
                return false;
            }

        }else {
            items.put(item, (items.get(item)+count));
            System.out.println("The Item has been added");
            return true;
        }
    }

    public void showCoins(){
        System.out.println("(Coins ,count ):");
        for (Coins coin:coins.keySet()) {
            if (coin.getCoins()<100)
                System.out.println("("+coin.getCoins()+"c"+" ,"+coins.get(coin)+" )");
            else{
                System.out.println("("+coin.getCoins()/100+"$"+" ,"+coins.get(coin)+" )");
            }
        }
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(@NotNull Order order) {
        this.order = order;
    }
    public void setOrder( Item item, Payment payment) {
        this.order = new Order(item,payment);
    }
    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(@NotNull Currency currency) {
        this.currency = currency;
    }

    public Cash getVendingCardBalance() {
        return vendingCardBalance;
    }

    public void setVendingCardBalance(@NotNull Cash vendingCardBalance) {
        this.vendingCardBalance = vendingCardBalance;
    }

    public HashMap<Coins, Integer> getCoins() {
        return coins;
    }

    public void setCoins(@NotNull HashMap<Coins, Integer> coins) {
        this.coins = coins;
    }

    public boolean deleteItem(@NotNull Item item){
        if (!items.containsKey(item)){
            System.out.println("The Item isn't in the inventory");
            return false;
        } else {
            items.remove(item);
            System.out.println("The Item Deleted .");
            return true;
        }
    }

    /**
     * it use to delete some count from specific Item
     * @param item, instance of Item class
     * @param count ,count of Item
     * @return ,true if item deleted and false if not
     */
    public boolean deleteItemCount(@NotNull Item item,@NotNull Integer count){
        if (count<0||count>15){
            System.out.println("you should Enter The count of Item between 0-15 .");
            return false;
        }else if (!items.containsKey(item)){
            System.out.println("The Item isn't in the inventory .");
            return false;
        }else {
            int countAfterDelete=items.get(item)-count;
            if (countAfterDelete<0){
                System.out.println("You should entered a valid count of item you want to delete.");
                return false;
            }else
                    items.put(item, countAfterDelete);
            return true;
        }
    }

    public boolean addCoins(@NotNull Coins coin,@NotNull Integer count){
        if (count <0){
            System.out.println("Enter The Valid count of Coins");
            return false;
        }else{
            if (coins.containsKey(coin)) {
                coins.put(coin, coins.get(coin) + count);
            }else{
                coins.put(coin,count);
            }
            System.out.println("Succesfully add coins .");
            return true;
        }
    }

    public boolean deleteCoins(@NotNull Coins coin,@NotNull Integer count){
        if (count <0){
            System.out.println("You don't Enter The Valid count of Coins .");
            return false;
        }else{
            int deletedCoin=coins.get(coin)-count;
            if (deletedCoin<0){
                System.out.println("you don't Enter The  count greater than  Coins count.");
                return false;
            }else{
                System.out.println("Successfully Deleted coins .");
                coins.put(coin,deletedCoin);
                return true;
            }
        }
    }

    public Integer checkOrder(){
        Item item=order.getItem();
        Payment payment=order.getPayment();
        Cash itemPrice=item.getPrice();

        if (itemPrice.getCurrency().getSymbol().equals("$"))
            itemPrice.setAmount(itemPrice.getAmount()*100);

        Cash paymentBalanced=payment.getAmount();

        if (itemPrice.getAmount()<=paymentBalanced.getAmount())
            return paymentBalanced.getAmount()-itemPrice.getAmount();
        else
            return -1;

    }

    public boolean isItemExisting(){
        if (items.containsKey(order.getItem()))
            return true;
        else
            return false;
    }

    public Integer getCountItems(){
        int count=0;
        for (Item item:items.keySet()){
            count+=items.get(item);
        }
        return count;
    }

    public void showItems(){
        int itemId=1;
        System.out.println("The Item In Vending Machine is :");

        for (Item item:items.keySet()){
            if (items.get(item)==0)
                continue;
            System.out.println(itemId+"- "+item.toString()+" ]");
            itemId++;
        }
    }

    public Item getItem(@NotNull int itemId){
        int count=1;
        for (Item item: items.keySet()) {
            if (count ==itemId)
                return item;
            count++;
        }
        return null;
    }


    public void setCashToCoins(@NotNull HashMap<Coins,Integer> cash){
        for (Coins coin:cash.keySet()){
            coins.put(coin,coins.get(coin)+cash.get(coin));
        }
    }


    public HashMap<Coins,Integer> canRemindCoins(@NotNull Integer price) {
        HashMap<Coins, Integer> remindCoins = Coins.EMPTY.getEmptyCoins();
        if (price <= 0)
            return null;
        ArrayList<Coins> coinsList=Coins.EMPTY.listCoins();

        for (Coins coin:coinsList) {
            if (price / coin.getCoins() != 0) {
                int numberOfCoins = price / coin.getCoins();
                if (coins.get(coin) >= numberOfCoins) {
                    remindCoins.put(coin, numberOfCoins);
                    price %= coin.getCoins();
                } else {
                    int num = numberOfCoins - coins.get(coin);
                    remindCoins.put(coin, coins.get(coin));
                    price = (price % coin.getCoins()) + (num * coin.getCoins());
                }
            }
        }
        if (price != 0) {
            return null;
        }
        for (Coins coin:coins.keySet()) {
            coins.put(coin, (coins.get(coin) - remindCoins.get(coin)));
        }
        return remindCoins;
    }
    public boolean push() {

        Integer  priceToRemind=checkOrder();
        if (priceToRemind>=0 && isItemExisting() ) {
            if (priceToRemind==0) {
            Item item = order.getItem();
            System.out.println("You buy a " + item.getName() + " with price =" + item.getPrice() );
            if (order.getPayment() instanceof CashPayment)
                setCashToCoins(((CashPayment) order.getPayment()).getCashEntered());
            else {
                Cash itemPrice=item.getPrice();
                vendingCardBalance.setAmount((vendingCardBalance.getAmount() + itemPrice.getAmount()));
            }
            Payment payment=order.getPayment();
            payment.pay(item.getPrice());
            deleteItemCount(item,1);
            endOperation();
            return true;
        }else {
            HashMap<Coins, Integer> remindCoins = canRemindCoins(priceToRemind);
            if (remindCoins != null) {
                Item item = order.getItem();
                if (order.getPayment() instanceof CashPayment) {
                    System.out.print("You buy a " + item.getName() + " with price =" + item.getPrice() + " and the remind money is [ ");
                    for (Coins coin : remindCoins.keySet()) {
                        if (remindCoins.get(coin) != 0) {
                            if (coin.getCoins() ==100 || coin.getCoins()==2000||coin.getCoins()==5000)
                                System.out.print(remindCoins.get(coin) + " from " + coin.getCoins()/100 + "" + "$" + ", ");
                            else
                                System.out.print(remindCoins.get(coin) + " from " + coin.getCoins() + "" + "c" + ", ");
                        }
                    }
                    System.out.println("]");
                    deleteItemCount(item,1);
                    setCashToCoins(((CashPayment) order.getPayment()).getCashEntered());
                }else{
                    System.out.println("You buy a " + item.getName() + " with price =" + item.getPrice() );
                    Cash itemPrice=item.getPrice();
                    vendingCardBalance.setAmount((vendingCardBalance.getAmount() + itemPrice.getAmount()));
                    deleteItemCount(item,1);
                }
                Payment payment=order.getPayment();
                payment.pay(item.getPrice());
                endOperation();
                return true;
            }else{
                System.out.println("You Can't Buy because there is no Coins to remind in Vending Machine. ");
                return false;
            }
        }
    } else {
        System.out.println("The Payment Balanced is not enought or the Item does not exist .");
        return false;
    }
}


    /**
     * to end the order and stop purchasing Item
     */
    public void endOperation(){
        System.out.println("The Vending Machine is ready to a new Operation .");
        order=null;
    }
    public class VendingMachineBuilder {
        private VendingMachine vendingMachine;

        public VendingMachineBuilder(){
            vendingMachine=new VendingMachine();
        }

        public  void buildCurrruncy(@NotNull Currency currency){
            vendingMachine.setCurrency(currency);
        }

        public  void buildItems(@NotNull HashMap<Item,Integer> vendingItems) {
            vendingMachine.setItems(vendingItems);
        }

        public  void buildCoins(@NotNull HashMap<Coins,Integer> coins){
            vendingMachine.setCoins(coins);
        }

        public  void buildVendingCardBalanaced(@NotNull Cash cardBalanced){
            vendingMachine.setVendingCardBalance(cardBalanced);
        }

        public VendingMachine build(){
            return vendingMachine;
        }
    }

}
