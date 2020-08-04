public class Solution {
    private Weapon weapon;
    private Room room;
    private ClueCharacter character;

    public Solution(Weapon w, Room r, ClueCharacter c) {
        weapon = w;
        room = r;
        character = c;
    }

    boolean checkAgainstSuggestion(Suggestion s){
        if(s.character == this.character && s.weapon == this.weapon && s.room == this.room){
            return true;
        }
        return false;
    }
}
