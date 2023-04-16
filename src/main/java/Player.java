
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public class Player {
    private int numSpots;
    private int numDrawings;
    private double balance;
    private double wining;
    private double totalwining;
    private ArrayList<BetCard> selectedNums;
    private ArrayList<BetCard> matched;

    private boolean autoSelect;
    private boolean drawingStarted;


    Player(){
        this.numSpots = 0;
        this.numDrawings = 0;
        this.balance = 10.0;
        this.wining = 0.0;
        this.totalwining = 0.0;
        this.selectedNums = new ArrayList<>();
        this.matched = new ArrayList<>();
        this.autoSelect = false;
        this.drawingStarted = false;
    }


    Player(double balance){
        this.numSpots = 0;
        this.numDrawings = 0;
        this.balance = balance;
        this.wining = 0.0;
        this.totalwining = 0.0;
        this.selectedNums = new ArrayList<>();
        this.matched = new ArrayList<>();
        this.autoSelect = false;
        this.drawingStarted = false;
    }

    //setter, set number of spots that user choose
    public void setNumSpots(int numSpots){
        this.numSpots = numSpots;
    }

    //setter, set number of drawing that user choose
    public void setNumDrawings(int numDrawings){
        this.numDrawings = numDrawings;
    }

    //
    //@param takes a number that user selected
    //if the number that user wants to select is already been selected, output msg
    //otherwise, add the user's selection to list and lock that betCard
    public void select(BetCard n){
        if (this.selectedNums.size() < this.numSpots && !this.selectedNums.contains(n)) {
            n.setSelected(true);
            this.selectedNums.add(n);
        }
        else{
            System.out.println("Invalid selection!");
        }
    }

    //if user wants let us decide numbers,auto select set to true
    public void setAutoSelect(boolean autoSelect) {
        this.autoSelect = autoSelect;
    }


    //generate the random number
    //@param number of spots
    //this function will generate the required amount of random num
    public void quickPick(int numSpots){
        this.numSpots = numSpots;
        if (this.selectedNums.size() == this.numSpots) {
            //msg for debugging
            System.out.println("You have already selected the maximum number of spots.");
            return;
        }

        //create instance of the Random class to generate random numbers.
        Random random= new Random();
        Set<Integer> selectedSet = new HashSet<>();
        for(int i=0; i<(this.numSpots-selectedNums.size()); i++){
            int num;
            //since Random class is not guaranteed to generate unique random numbers each time,
            //so we need to ensure before adding it
            do{
                //from 1 to 80
                num = random.nextInt(KenoGame.KENO)+1;
            }while(selectedSet.contains(num));

            selectedSet.add(num);
            //BetCard object is created with the selected number,
            //and we marked it as selected
            BetCard n = new BetCard(num);
            n.setSelected(true);

            this.selectedNums.add(n);
        }

    }

    //remove a particular selected number
    //if the user does not like the number, they are able to un-selected it
    public void removeSelectNumber(BetCard num){
        Iterator<BetCard> iterator = selectedNums.iterator();
        while (iterator.hasNext()) {
            BetCard n = iterator.next();
            if (n.getCard() == num.getCard()) {
                n.setSelected(false);
                iterator.remove();
                break;
            }
        }
    }

    //return how many number has been selected
    public int getSelectedNumSize(){
        return selectedNums.size();
    }

    //clear the entire list of selected number
    public void clear(){
        for(BetCard n: this.selectedNums){
            n.setSelected(false);
        }
        this.selectedNums.clear();
    }

    //get the list of user's selection
    public ArrayList<BetCard> getSelectedNums() {
        return this.selectedNums;
    }

    //how much they user won in a draw
    public double getWining(){
        return this.wining;
    }

    public double getBalance(){
        return this.balance;
    }

    public int getNumSpots(){
        return this.numSpots;
    }

    //once startDrawing
    //the balance -1 as each draw require 1 dollar
    //and compare the result with player's selection
    //add matched number to matches list
    //and count how many number matches
    //then update the wining prize by using getPrizeAndOdds
    //and update the balance with winning prize
    public void play(KenoGame keno){
        drawingStarted = true;
        keno.startDrawing();
        this.balance -= 1;
        // Clear the matched list for the new drawing
        this.matched.clear();

        for (BetCard drawnNum: keno.getDrawingNums()) {
            for (BetCard selectedNum: this.selectedNums) {
                if (drawnNum.getCard() == selectedNum.getCard()) {
                    this.matched.add(selectedNum);
                }
            }
        }

        int matches = matched.size();
        this.wining = keno.getPrizeAndOdds(this.numSpots, matches);
        this.totalwining += this.wining;
        this.balance += this.wining;
        this.numDrawings--;
        if(numDrawings != 0) {
            this.drawingStarted = false;
        }
    }

    public double getTotalwining() {
        return totalwining;
    }

    public ArrayList<BetCard> getMatched() {
        return this.matched;
    }

    public int getNumDrawings(){
        return this.numDrawings;
    }

    public boolean isDrawingStarted(){
        return this.drawingStarted;
    }
}

