import java.util.Scanner;


//for debuggingggggggg
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;
        double balance = 10.0;

        while (playAgain) {
            Player player = new Player(balance);
            // Prompt the user to select the number of spots and drawings to play
            System.out.println("How many spots do you want to play? (1, 4, 8, or 10)");
            int numSpots = scanner.nextInt();
            player.setNumSpots(numSpots);

            System.out.println("How many drawings do you want to play? (1, 2, 3, or 4)");
            int numDrawings = scanner.nextInt();
            player.setNumDrawings(numDrawings);

            // Prompt the user to select their numbers or choose automatic selection
            System.out.println("Do you want to choose your numbers manually or automatically? (M/A)");
            String selection = scanner.next().toUpperCase();

            if (selection.equals("M")) {
                // Allow the user to select their numbers manually
                System.out.println("Enter " + numSpots + " numbers between 1 and 80:");
                for (int i = 0; i < numSpots; i++) {
                    int num = scanner.nextInt();
                    player.select(new BetCard(num));
                }
            } else {
                // Automatically select numbers for the user
                player.quickPick(numSpots);
            }

            // Display the player's selected numbers
            System.out.println("Your selected numbers are:");
            for (BetCard betCard : player.getSelectedNums()) {
                System.out.print(betCard.getCard() + " ");
            }
            System.out.println();

            // Start the drawings
            for (int i = 1; i <= numDrawings; i++) {
                // Start a new drawing
                KenoGame game = new KenoGame();
                player.play(game);
                System.out.println("Drawing " + i + ":");
                for (BetCard betCard : game.getDrawingNums()) {
                    System.out.print(betCard.getCard() + " ");
                }
                System.out.println();
                System.out.println("You matched " + player.getMatched().size() + " numbers and won $" + player.getWining());
                System.out.println("Your matched numbers are:");
                for (BetCard betCard : player.getMatched()) {
                    System.out.print(betCard.getCard() + " ");
                }
                System.out.println();
                balance += player.getWining() - 1.0;
                System.out.println("Your balance after drawing " + i + " is $" + balance);
//                System.out.println("You still have drawing left(y to continue):");
//                String cont = scanner.next().toUpperCase();
//                if (cont.equals("Y")) {
//                }
                if (i < numDrawings) {
                    System.out.println("You still have drawing left(y to continue):");
                    String cont = scanner.next().toUpperCase();
                    if (!cont.equals("Y")) {
                        break;
                    }
                }
            }

            // Display the player's total winnings and allow them to play again or exit
            System.out.println("Your total winnings are $" + player.getTotalwining());
            System.out.println("Do you want to play again? (Y/N)");
            String response = scanner.next().toUpperCase();
            if (!response.equals("Y")) {
                playAgain = false;
            }
        }
        System.out.println("Thanks for playing!");

    }
}
