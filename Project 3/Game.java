import java.io.*;
import java.util.Scanner;

public class Game
{
    // Globals
    public static final boolean DEBUGGING =false;                               // Debugging flag.
    public static final int MAX_LOCALES = 8;                                    // Total number of rooms/locations we have in the game.
    public static Locations currentLocation = null;                             // Locations is a reference to the Locale class.Player starts in locale 0.
    public static String command;                                               // What the player types as he or she plays the game.
    public static boolean stillPlaying = true;                                  // Controls the game loop.
    public static int moves = 0;                                                // Counter of the player's moves.
    public static int score = 0;                                                // Tracker of the player's score.
    public static float achievement = 0;                                        // This is the ratio of scores to moves.
    public static  Item [] playerItem;                                          // An uninitialized array of type Item.
    public static  Item [] inventory = new Item[5];                             // An array of items that the player took from the locations.
    public static  MagicShoppeItem [] magicInventory = new MagicShoppeItem [10]; //An array of items that the player took from the MagicShoppe.
    public static int takenItem = 0;                                             // Number of items in the playerTook list.
    public static int takenMagicItem = 0;                                        // Number of items in the playerTook MagicShoppeList
    public static double money = 0;                                              //Money the user gets in each Location.
    //declaring each location to null before game start
    public static Locations ls0 = null;
    public static Locations ls1 = null;
    public static Locations ls2 = null;
    public static Locations ls3 = null;
    public static Locations ls4 = null;
    public static Locations ls5 = null;
    public static Locations ls6 = null;
    public static Locations ls7 = null;
    public static Locations ls8 = null; //
    public static MagicShoppeList m1 = new MagicShoppeList();                     //A list instance of the MagicShoppeList class.


    public static void main(String[] args)
    {
        if (DEBUGGING)
        {
            // Display the command line args.
            System.out.println("Starting with args:");
            System.out.println("Title of Game -- SIMPLE GAME");
            System.out.println("This Game starts from the Desert. You are currently in the Desert.");

            for (int i = 0; i < args.length; i++)
            {
                System.out.println(i + ":" + args[i]);
            }
        }

        // Set starting locale, if it was provided as a command line parameter.
        if (args.length > 0)
        {
            try
            {
                int startLocation = Integer.parseInt(args[0]);
                // Check that the passed-in value for startLocation is within the range of actual locations.
                if (startLocation >= 0 && startLocation <= MAX_LOCALES)
                {
                    if (startLocation==0) currentLocation= ls0;
                    if (startLocation==1) currentLocation= ls1;
                    if (startLocation==2) currentLocation= ls2;
                    if (startLocation==3) currentLocation= ls3;
                    if (startLocation==4) currentLocation= ls4;
                    if (startLocation==5) currentLocation= ls5;
                    if (startLocation==6) currentLocation= ls6;
                    if (startLocation==7) currentLocation= ls7;
                    if (startLocation==8) currentLocation= ls8;
                }
            } catch (NumberFormatException ex)
            {   // catch(Exception ex)
                System.out.println("Warning: invalid starting location parameter: " + args[0]);
                if (DEBUGGING)
                {
                    System.out.println(ex.toString());
                }
            }
        }

        // Get the game started.
        init();
        updateDisplay();

        // Game Loop
        while (stillPlaying)
        {
            getCommand();
            navigate();
            updateDisplay();
        }

        // Code to execute when user types quit or q to end the game.
        System.out.println("Thank you for playing.");
    }

    private static void init()
    {
        // Initialize any uninitialized globals.
        command = new String();
        stillPlaying = true;

        // Make the list of items in the magicShoppe
        m1.setName("Magic Items");
        m1.setDesc("These are some of my favorite things.");


        //location instances of the Locale class and Inventory instances of the Item Class.
        Locale loc0 = new Locale(0);
        loc0.setName("Desert");
        loc0.setDesc("It is windy on the Desert.");
        loc0.setNext("You can go east or south but cannot go west or north."); // where user can move to from currentLocation.

        Item item0 = new Item(0, "map");
        item0.setName("map");
        item0.setDesc("You can use the map for further directions. ");
        item0.setItem(loc0);

        Locale loc1 = new Locale(1);
        loc1.setName("The Jungle");
        loc1.setDesc("You have to be careful, there are some elephants here.");
        loc1.setNext("You can go west, east or south but cannot go north.");

        Item item1 = new Item(1,"");
        item1.setName("");
        item1.setDesc("");
        item1.setItem(loc1);


        Locale loc2 = new Locale(2);
        loc2.setName("The Cave");
        loc2.setDesc("The cave is safe");
        loc2.setNext("You can go west or south but cannot go east or north.");

        Item item2 = new Item(2,"Gold Coin");
        item2.setName("Gold Coin");
        item2.setDesc("This is a prestigious coin");
        item2.setItem(loc2);

        Locale loc3 = new Locale(3);
        loc3.setName("Red Sea");
        loc3.setDesc("You are few miles away from Egypt.");
        loc3.setNext("You can go north, south or east but cannot go west.");

        Item item3 = new Item(3,"");
        item3.setName("");
        item3.setDesc("");
        item3.setItem(loc3);

        Locale loc4 = new Locale(4);
        loc4.setName("Magic Shoppe");
        loc4.setDesc("This is your magic center ");
        loc4.setNext("You can go north, south, east or west");

        Item item4 = new Item(4,"");
        item4.setName("");
        item4.setDesc("There are magic items for sale");
        item4.setItem(loc4);

        Locale loc5 = new Locale(5);
        loc5.setName("Rain Forest");
        loc5.setDesc("It is raining here.");
        loc5.setNext("You can go north, south or west but cannot go east");

        Item item5 = new Item(5,"Umbrella");
        item5.setName("Umbrella");
        item5.setDesc("It is raining,you can take the umbrella ");
        item5.setItem(loc5);

        Locale loc6 = new Locale(6);
        loc6.setName("Island");
        loc6.setDesc("Welcome to the mysterious Island.");
        loc6.setNext("You can go north and east but cannot go south or west");

        Item item6 = new Item(6,"Boat");
        item6.setName("Boat");
        item6.setDesc("There is a nice boat on this Island ");
        item6.setItem(loc6);

        Locale loc7 = new Locale(7);
        loc7.setName("Lake");
        loc7.setDesc("You are at the sacred Lake.");
        loc7.setNext("You can go north, west or east but cannot go south");

        Item item7= new Item(0,"");
        item7.setName("");
        item7.setDesc("");
        item7.setItem(loc7);

        Water loc8 = new Water(8); // Locale(8);
        loc8.setName("River");
        loc8.setDesc("Welcome to the deepest river in the world.");
        loc8.setNext("You can go north or west but cannot go south or east");
        loc8.setNearestLagoon("Kole Lagoon");

        Item item8 = new Item(8,"");
        item8.setName("");
        item8.setDesc("");
        item8.setItem(loc8);

        // Items in each location
        playerItem = new Item[9];
        playerItem[0] = item0;  // Map
        playerItem[1] = item1; //null
        playerItem[2] = item2; //Gold Coin
        playerItem[3] = item3; //null
        playerItem[4] = item4; //MagicShoppeItems
        playerItem[5] = item5;  // Umbrella
        playerItem[6] = item6;  //Boat
        playerItem[7] = item7;  //null
        playerItem[8] = item8;  //null

        // Set up the location.
        ls0= new Locations(loc0);
        ls1= new Locations(loc1);
        ls2= new Locations(loc2);
        ls3= new Locations(loc3);
        ls4= new Locations(loc4);
        ls5= new Locations(loc5);
        ls6= new Locations(loc6);
        ls7= new Locations(loc7);
        ls8= new Locations(loc8);

        //for Locations 0
        ls0.setSouth(ls3);
        ls0.setEast(ls1);

        //for Locations 1
        ls1.setSouth(ls4);
        ls1.setEast(ls2);
        ls1.setWest(ls0);

        //for Locations 2
        ls2.setSouth(ls5);
        ls2.setWest(ls1);

        //for Locations 3
        ls3.setNorth(ls0);
        ls3.setSouth(ls6);
        ls3.setEast(ls4);

        // for Locations 4
        ls4.setNorth(ls1);
        ls4.setSouth(ls7);
        ls4.setEast(ls5);
        ls4.setWest(ls3);

        ///for Locations 5
        ls5.setNorth(ls2);
        ls5.setSouth(ls8);
        ls5.setWest(ls4);

        //for Locations 6
        ls6.setNorth(ls3);
        ls6.setEast(ls7);

        //for Locations 7
        ls7.setNorth(ls4);
        ls7.setWest(ls6);
        ls7.setEast(ls8);

        //for Locations 8
        ls8.setNorth(ls5);
        ls8.setWest(ls7);

        if (currentLocation==null) currentLocation = ls0;  //where the user will start the game.
    }
    //
    // Private
    //
    private static MagicShoppeItem sequentialSearch(MagicShoppeList m1,
                                                    String target) {
        String fileName = "magic.txt";
        readMagicItemsFromFile(fileName, m1);

        MagicShoppeItem retVal = null;
        boolean stillShopping=true;
        while(stillShopping) {

            System.out.println("Searching for " + target + ".");
            int counter = 0;
            MagicShoppeItem currentItem = new MagicShoppeItem();
            currentItem = m1.getHead();
            boolean isFound = false;
            while ( (!isFound) && (currentItem != null) )
            {
                counter = counter +1;
                if (currentItem.getName().equalsIgnoreCase(target))
                {
                    // We found it!
                    isFound = true;
                    retVal = currentItem;
                    if (money > currentItem.getPrice())
                    {
                        money = money - currentItem.getPrice();
                        System.out.println("You bought " + currentItem + "from the magicShoppe and you have "+ "$" + money + " left.");
                        magicInventory[takenMagicItem]= currentItem;
                        takenMagicItem = takenMagicItem + 1;
                    }
                    else{
                        System.out.println("You do not have enough money to buy this item");
                        System.out.println("You only have $" + money + " left.");
                    }
                } else
                {
                    // Keep looking.
                    currentItem = currentItem.getNext();
                }
            }
            if (isFound)
            {
                System.out.println("Found " + target + " after " + counter + " comparisons.");
                return  currentItem;
            } else
            {
                System.out.println("Could not find " + target + " in " + counter + " comparisons.");
                System.out.println("Do you still want to shop? (Yes) or (No) ");
                Scanner inputReader = new Scanner(System.in);
                target= inputReader.nextLine();
                if(target.equalsIgnoreCase("No"))
                    stillShopping=false;
                else{
                    readMagicItemsFromFile(fileName, m1);
                    // Display the list of items in magicShoppe.
                    System.out.println(m1.toString());
                    System.out.println("Enter the item you want to buy again");
                    target= inputReader.nextLine();
                }
                System.out.println();
            }
        }
        return retVal;
    }

    private static void readMagicItemsFromFile(String fileName,
                                               MagicShoppeList m1)
    {
        File myFile = new File(fileName);
        try {
            Scanner input = new Scanner(myFile);
            while (input.hasNext())
            {
                // Read a line from the file.
                String itemName = input.nextLine();

                // Construct a new list item and set its attributes.
                MagicShoppeItem fileItem = new MagicShoppeItem();
                fileItem.setName(itemName);
                fileItem.setPrice(Math.random() * 100);
                fileItem.setNext(null); // Still redundant. Still safe.

                // Add the newly constructed item to the list.
                m1.add(fileItem);
            }
            // Close the file.
            input.close();
        } catch (FileNotFoundException ex){
            System.out.println("File not found. " + ex.toString());
        }
    }

    private static void updateDisplay()
    {
        System.out.println(currentLocation.getThisLocale().getName());
        System.out.println(currentLocation.getThisLocale().getDesc());
        System.out.println(currentLocation.getThisLocale().getNext());

        if (!playerItem[currentLocation.getThisLocale().getId()].getHasTaken())
        {
            System.out.println(playerItem[currentLocation.getThisLocale().getId()].getName());
            System.out.println(playerItem[currentLocation.getThisLocale().getId()].getDesc());

        }
    }


    private static void getCommand()
    {
        System.out.print("[" + moves + " moves, score " + score + "," + " achievement " + achievement + " Money $" + money + "] ");
        Scanner inputReader = new Scanner(System.in);
        command = inputReader.nextLine();  // command is global.
    }

    private static void navigate()
    {
        final Locations INVALID = null;
        int dir = -1;  // This will get set to a value > 0 if a direction command was entered.

        if (command.equalsIgnoreCase("north") || command.equalsIgnoreCase("n")) {
            dir = 0;
        } else if (command.equalsIgnoreCase("south") || command.equalsIgnoreCase("s")) {
            dir = 1;
        } else if (command.equalsIgnoreCase("west") || command.equalsIgnoreCase("w")) {
            dir = 2;
        } else if (command.equalsIgnoreCase("east") || command.equalsIgnoreCase("e")) {
            dir = 3;
        } else if (command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("q")) {
            quit();
        } else if (command.equalsIgnoreCase("help") || command.equalsIgnoreCase("h")) {
            help();
        } else if (command.equalsIgnoreCase("take") || command.equalsIgnoreCase("t")) {
            take();
        } else if (command.equalsIgnoreCase("inventory") || command.equalsIgnoreCase("i")) {
            invt(); magicInvt();
        } else if (command.equalsIgnoreCase("map") || command.equalsIgnoreCase("m")) {
            map();
        } else {
            System.out.println(" You entered an invalid command");
            System.out.println(" The valid commands are n/north to go north, s/south to go south ");
            System.out.println(" e/east to go east, w/west to go west. ");
            System.out.println(" h/help for help, q/quit to quit the game, m/map to display the map of the game ");
            System.out.println(" t/take to take an item into your inventory and i/inventory to view items in your inventory ");
            System.out.println(" ");

        }; // code to execute if user enters an invalid command

        if (dir > -1)
        {   // This means a dir was set.
            Locations newLocation = null;
            if(dir==0) newLocation=currentLocation.getNorth();
            if(dir==1) newLocation=currentLocation.getSouth();
            if(dir==2) newLocation=currentLocation.getWest();
            if(dir==3) newLocation=currentLocation.getEast();
            if (newLocation == INVALID) {
                System.out.println("You cannot go that way.");
            } else {
                currentLocation = newLocation;
                moves = moves + 1;
                if (!currentLocation.getThisLocale().getHasVisited())
                {
                    score = score + 5;
                    money = money + 50;
                    currentLocation.getThisLocale().setHasVisited(true);
                }

                achievement = (float)score / moves;

                if(currentLocation.getThisLocale().getId()== 4)
                {
                    // Ask player for an item in the MagicShoppe.
                    final  String fileName = "magic.txt";
                    readMagicItemsFromFile(fileName, m1);

                    // Display the list of items in magicShoppe.
                    System.out.println(m1.toString());

                    Scanner inputReader = new Scanner(System.in);
                    System.out.print("You are at the magicShoppe,what item would you like to buy? ");
                    String targetItem = new String();
                    targetItem = inputReader.nextLine();
                    System.out.println();

                    MagicShoppeItem ms = new MagicShoppeItem();
                    ms = sequentialSearch(m1, targetItem);
                    if (ms != null) {
                        System.out.println(ms.toString());
                    }
                }
            }
        }
    }

    private static void help()
    {
        System.out.println("The commands are as follows:");
        System.out.println("   n/north");
        System.out.println("   w/west");
        System.out.println("   e/east");
        System.out.println("   s/south");
        System.out.println("   m/map");
        System.out.println("   i/inventory");
        System.out.println("   t/take");
        System.out.println("   q/quit");
    }

    private static void map()
    {
        boolean hasMap=false;
        for (int i = 0; i <takenItem; i++)
        {
            if (inventory[i].getName().equalsIgnoreCase("map"))
                hasMap = true;
        }
        if (hasMap)
        {
            System.out.println("[" + ls0.getThisLocale().getName() + " ]-----[" + ls1.getThisLocale().getName() + "]-------[" +ls2.getThisLocale().getName()+ "]    ");
            System.out.println("  |         |          |     ");
            System.out.println("  |         |          |        ");
            System.out.println("[" + ls3.getThisLocale().getName() + "]-----[" + ls4.getThisLocale().getName() + "]-----[" + ls5.getThisLocale().getName() + "] ");
            System.out.println("  |        |          |               ");
            System.out.println("  |        |          |        ");
            System.out.println("[" + ls6.getThisLocale().getName() + " ]-----[" + ls7.getThisLocale().getName() + "]---------[" +ls8.getThisLocale().getName() + "]       ");
        }
    }


    private static void take()
    {

        if (!playerItem[currentLocation.getThisLocale().getId()].getHasTaken())
        {

            System.out.println( playerItem[currentLocation.getThisLocale().getId()].getName());

            inventory[takenItem] = playerItem[currentLocation.getThisLocale().getId()];
            takenItem = takenItem + 1;
            playerItem[currentLocation.getThisLocale().getId()].setHasTaken(true);

        }
    }

    private static void invt()
    {
        System.out.println("You have " + takenItem + " item(s) in your PlayerItem inventory" );
        System.out.println("Inventory : ");
        for (int i = 0; i <takenItem; i++) {
            System.out.print(i + ":" + inventory[i]);
        }
        System.out.println(" ");
    }
    private static void magicInvt()
    {
        System.out.println("You have " + takenMagicItem + " item(s) in your MagicShoppe inventory" );
        System.out.println("Inventory : ");
        for (int i = 0; i <takenMagicItem; i++) {
            System.out.print(i + ":" + magicInventory[i]);
        }

        System.out.println(" ");
    }
    private static void quit()
    {
        stillPlaying = false;
    }
}
