public class UnknownCommand implements Command {


    @Override
    public void onRun(Carl carl, String[] args) {

        String itemName = args[0];
        carl.addItemToList(new Item(itemName));
        System.out.println("added: " + itemName);
    }
}
