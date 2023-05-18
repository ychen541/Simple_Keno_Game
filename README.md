# Simple Keno Game

Reference: https://www.ctlottery.org/KENO

This project implements the popular casino and state lottery game, Keno. 
This is a simple game to understand and play which allows us to focus on learning GUI development in JavaFX and trying our hand at event driven programing.

## How to run:
mvn clean

mvn compile

mvn exec:java

## How the game is played:
Players wager by choosing a set amount of numbers( pick 2 numbers, pick 10 numbers, etc.) ranging from 1 to 80. After all players have made their wagers and picked their numbers, twenty numbers are drawn at random, between 1 and 80 with no duplicates. Players win by matching a set amount of their numbers to the numbers that are randomly drawn.


<img width="750" alt="Screen Shot 2023-05-17 at 7 47 53 PM" src="https://github.com/ychen541/Simple_Keno_Game/assets/122131966/975458f9-fdc0-4606-bc3f-84638b6d9a9d">


Once the player decides on how many spots they want to play, the user should now be able to select numbers they want with no duplicate numbers or select more spots than they decided originally.
Once a number is selected on the bet card, it will show that it has been chosen. Players can edit their choices as often as they want before the drawings occur. They can not change once the drawings begin.


<img width="750" alt="Screen Shot 2023-05-17 at 7 49 00 PM" src="https://github.com/ychen541/Simple_Keno_Game/assets/122131966/04fbca26-12e0-43f8-b8ad-13fb8d31fd4c">

By clicking "Quick Pick", the player can have their numbers chosen automatically and randomly for them if they don’t want to choose themselves.

<img width="750" alt="Screen Shot 2023-05-17 at 7 48 23 PM" src="https://github.com/ychen541/Simple_Keno_Game/assets/122131966/5ac8f0c3-2f29-4a01-8198-f492cfe7fd91">

The drawing displays 20, randomly selected numbers (1-80) with no duplicates, one by one with a pause in between selection.  After each drawing, the user will be able to see how many numbers they matched, which numbers they matched and how much they won in that particular drawing. They will also be able to see what they have won since the program was started.


<img width="750" alt="Screen Shot 2023-05-17 at 7 49 37 PM" src="https://github.com/ychen541/Simple_Keno_Game/assets/122131966/75a14671-3ed1-491d-afcc-1b1dbeb8ae2e">
<img width="750" alt="Screen Shot 2023-05-17 at 7 50 09 PM" src="https://github.com/ychen541/Simple_Keno_Game/assets/122131966/152679b9-1495-49a0-b82d-9d9538acdc38">


