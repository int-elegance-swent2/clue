import java.util.*;

public class Clue {
    private String[] weapon_Names = {"Candlestick", "Dagger", "Lead Pipe", "Revolver", "Rope", "Spanner"};

    private String[] room_Names = {"Lounge", "Dining Room", "Kitchen", "Ballroom", "Conservatory",
            "Billiard Room", "Library", "Study Room", "Hall"};

    private String[] character_Names = {"Miss Scarlett", "Colonel Mustard", "Mrs White", "Mr Green",
            "Mrs Peacock", "Professor Plum"};

    private ArrayList<Weapon> weapons;
    private ArrayList<Room> rooms;
    private ArrayList<ClueCharacter> characters;

    private Suggestion suggestion;    //Cluedo.Suggestion changes every player so not final

    private Solution gameSolution;

    private static final Random randomize = new Random();

    ArrayList<Player> players;
    Queue<Player> playOrder;
    Card[][] board = new Card[24][25];

    private static final Scanner INPUT = new Scanner(System.in);

    public Clue(int n) {
        //SETUP
        loadGame();
        allocatePlayers(); /** TO DO*/
        makeSolution();
        dealCards();
        setPlayerLocations();  /** TO DO*/

        //GAME PLAY CAN BEGIN

    }

    public void loadGame() {
        for (String weapon : weapon_Names) weapons.add(new Weapon(weapon));
        for (String room : room_Names) rooms.add(new Room(room));
        for (String character : character_Names) characters.add(new ClueCharacter(character));
    }

    public static void main(String[] a) {

        //Welcome messege
        System.out.println("********** CLUE **********");

        // Getting number of players playing
        System.out.println("How many players?");
        int numberOfPlayers = validInputCheck( Integer.parseInt(INPUT.nextLine()) );

        //NEW GAME
        Clue clue = new Clue(numberOfPlayers);

        /**
         * TODO - Main Clue event loop
         * 1. Create Circumstance to be used as solution <character, weapon, room>
         * 2. Ask how many players (and their names?)
         * 3. Share out the remaining Cards
         * Loop through:
         *  1. Roll 2 dice and loop until all moves are over or they enter a room.
         *  // TODO - Have to make sure player doesn't move into an occupied room or space or impassable space.
         *  2. If player enters room, can break out of move loop
         *  3. Cluedo.Player can make an suggestion.
         *  4. Loop again through all players:
         *          1. Show the suggestion and the players cards.
         *          2. Allow player to decide if they want to refute or not
         *  5. Give player opportunity to accuse or not.
         *
         *
         *  That loop continues until all the players are gone or someone guesses correctly
         */

    }

    static int validInputCheck(int i) {
        while(true){
            if(i < 7 && i > 1){
                return i;
            }
            else {
                System.out.println("Number of players must be between 2 and 6...");
                return validInputCheck( Integer.parseInt(INPUT.nextLine()) );
            }
        }

    }

    public void allocatePlayers(){
        /*TO DO
        Players need to choose their characters
        Update playOrder??
        Decide who goes first??
        */
    }

    public void makeSolution() {
        // randomly choosing a murder weapon
        Weapon w = weapons.remove(randomize.nextInt(weapons.size()));

        // randomly choosing a murder room
        Room r = rooms.remove(randomize.nextInt(rooms.size()));

        // randomly choosing a murderer
        ClueCharacter c = characters.remove(randomize.nextInt(characters.size()));

        gameSolution = new Solution(w, r, c);
    }

    public void dealCards() {
        ArrayList <Card> toDeal = new ArrayList<>();

        //add all cards but solution cards to new deck
        for(Weapon w : weapons) toDeal.add(w);
        for(ClueCharacter c : characters) toDeal.add(c);
        for(Room r : rooms) toDeal.add(r);

        //shuffle said deck
        Collections.shuffle(toDeal);

        //deal between players
        while(!toDeal.isEmpty()) {
            for (Player p : players) {
                p.addToHand(toDeal.get(toDeal.size()));
                toDeal.remove(toDeal.size());
            }
        }

    }

    public void setPlayerLocations(){
        //players position on board??
    }

}
