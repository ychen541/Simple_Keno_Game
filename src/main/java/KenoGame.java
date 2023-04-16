import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class KenoGame {

    public static final int KENO = 80;
    public static final int RANDOM_DRAWN = 20;
    private ArrayList<BetCard> drawingNums;
    private int howManyMatched;

    KenoGame(){
        drawingNums = new ArrayList<>();
        howManyMatched = 0;
    }


    //the odds and the prize for each spot
    public double getPrizeAndOdds(int numSpots, int matches){
        this.howManyMatched = matches;
        double prize =0;
        double odds = 0;
        switch(numSpots){
            //1 spot game: 1 match->prize $2
            case 1:
                if(howManyMatched ==1) {
                    prize = 2;
                    break;
                }
                odds = 1/4.0;
                break;
            //4 spot game: 2 match->prize $1,3 match->prize $5,4 match->prize $75
            case 4:
                switch(howManyMatched){
                    case 2:
                        prize = 1;
                        break;
                    case 3:
                        prize = 5;
                        break;
                    case 4:
                        prize = 75;
                        break;
                }
                odds = 1/3.86;
                break;
            //8 spot game:4 match->prize $2,5 match->prize $12,6 match->prize $50,
            //            7 match->prize $750,8 match->prize $10000
            case 8:
                switch(howManyMatched){
                    case 4:
                        prize = 2;
                        break;
                    case 5:
                        prize = 12;
                        break;
                    case 6:
                        prize = 50;
                        break;
                    case 7:
                        prize = 750;
                        break;
                    case 8:
                        prize = 10000;
                        break;
                }
                odds = 1/9.77;
                break;
            //10 spot game:0 match->prize $5,5 match->prize $2,6 match->prize $15,
            //             7 match->prize $40,8 match->prize $450,9 match->prize $4250,
            //             10 match->prize $100000
            case 10:
                switch(howManyMatched){
                    case 0:
                        prize = 5;
                        break;
                    case 5:
                        prize = 2;
                        break;
                    case 6:
                        prize = 15;
                        break;
                    case 7:
                        prize = 40;
                        break;
                    case 8:
                        prize = 450;
                        break;
                    case 9:
                        prize = 4250;
                        break;
                    case 10:
                        prize = 100000;
                        break;
            }
            odds = 1/9.05;
            break;
        }

        return prize;
    }

    //this function generate 20 random number for 1-80;
    public void startDrawing(){
        drawingNums.clear();
        howManyMatched = 0;
        Random random = new Random();
        for (int i = 0; i < RANDOM_DRAWN; i++) {
            int num;
            // ensure the same number is not drawn multiple times
            do {
                num = random.nextInt(KENO) + 1;
            } while (isNumberDrawn(num));
            drawingNums.add(new BetCard(num));
        }

    }

    public ArrayList<BetCard>getDrawingNums(){
        return this.drawingNums;
    }

    public boolean isNumberDrawn(BetCard num) {
        return drawingNums.contains(num);
    }


    public boolean isNumberDrawn(int num) {
        for (BetCard betCard : drawingNums) {
            if (betCard.getCard() == num) {
                return true;
            }
        }
        return false;
    }


}
