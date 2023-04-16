import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.animation.PauseTransition;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.animation.SequentialTransition;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Duration;


public class JavaFXTemplate extends Application {

	private Stage main;
	//welcome page
	//txt for menu: rule, odds
	private TextField t1,t2;
	private MenuBar menu;
	private Menu m;
	private MenuItem rules;
	private MenuItem odds;
	private MenuItem exit;
	//after play
	private MenuItem newLook;

	private Button play, ruleBack, oddBack;


	//select spot and drawing
	private RadioButton oneSpot, fourSpots, eightSpots, tenSpots;
	private RadioButton oneDraw, twoDraws, threeDraws, fourDraws;

	private Button ball,userball, quickPick, clear, startDrawing, nextDrawing, startNewGame;
	private Button pause;

	private GridPane grid;
	private GridPane usergrid;
	private GridPane drawinggrid;


	private HashMap<String,Scene> SceneMap = new HashMap<>();
	private BorderPane gamePane, rulesPane, oddsPane, drawPane,userchoice;
	private VBox choice;

	private EventHandler<ActionEvent> handler, quickpickhandler;

	private boolean playScene = false;
	private boolean drawScene = false;

	private Player player;
	private KenoGame keno;
	private BetCard[][] betCards;
	private int spots, draw,s;
	private BorderPane mainPane;

	public static void main(String[] args){
		launch();
	}

	//welcome page
	//defines a JavaFX BorderPane layout
	//it returns BorderPane object
	//it displays a welcome message and a button to start playing a game.
	public BorderPane mainp(){

		//add image for better visaulization
		Image image = new Image("file:src/main/resources/keno_sol_1.png");
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(500);
		imageView.setFitHeight(400);

		//set up the play button
		play = new Button("Play");
		play.setPrefSize(100, 60);
		play.setStyle("-fx-font-family: 'Arial Rounded MT Bold';" + "-fx-text-fill: #fff8dc;" + "-fx-background-color: #ff8c00;" + "-fx-font-size:20" );
		play.setAlignment(Pos.CENTER);

		//create the BorderPane and also set its properties
		BorderPane pane =  new BorderPane();
		pane.setPrefSize(1000,800);
		//padding around the edges of the pane to 30 pixels on all sides
		pane.setPadding(new Insets(30));

		//set the region and the alignment for the button: place it at bottom center
		BorderPane.setMargin(play,new Insets(50));
		BorderPane.setAlignment(play, Pos.BOTTOM_CENTER);

		pane.setCenter(imageView);
		pane.setBottom(play);
		pane.setStyle("-fx-background-color: #eed868;");

		return pane;
	}


	//Creates a scene that displays the rules of the game
	public Scene displayRule(){
		Text header = new Text ("\n\nRules\n\n");
		Text intro = new Text("Carolina Keno is a fast-paced lottery draw-style game that's easy to play,"+"" +
				"with a chance to win great cash prizes every 4 minutes. For each Keno drawing,"+
				"20 numbers out of 80 will be selected as winning numbers." +"" +
				"You can decide how many of these numbers (called Spots) and exactly which numbers you will try to match."+
				"There are more than 300 drawings daily so you will never need to wait long until the next Keno drawing.\n\n"+
				"1.Select how many consecutive draws to play. Pick up to 20.\n"+
				"2.Select how many numbers to match from 1 to 10. In Keno, these are called Spots." +
				"  The number of Spots you choose and the amount you play per draw will determine the amount you could win.\n"+
				"3.Pick as many numbers as you did Spots. You can select numbers from 1 to 80 or " +
				"  choose Quick Pick and let the computer terminal randomly pick some or all of these numbers for you.\n"
		);
		header.setFill(Color.TOMATO);
		header.setFont(Font.font("Andale Mono",FontWeight.BOLD,40));
		header.setTextAlignment(TextAlignment.CENTER);
		intro.setFont(Font.font("Andale Mono",20));
		intro.setFill(Color.CADETBLUE);

		//use JUSTIFY text alignment to ensure that the text fills the full width of the space
		intro.setTextAlignment(TextAlignment.JUSTIFY);

		//added to the 2 Text objects to a TextFlow object
		TextFlow textFlow = new TextFlow( header, intro);
		BorderPane.setAlignment(ruleBack, Pos.CENTER_LEFT);
		BorderPane.setAlignment(textFlow, Pos.CENTER);

		BorderPane.setMargin(textFlow, new Insets(10));
		rulesPane = new BorderPane();
		rulesPane.setPrefSize(1000,750);
		rulesPane.setPadding(new Insets(30));
		rulesPane.setTop(ruleBack);
		rulesPane.setCenter(textFlow);
		rulesPane.setStyle("-fx-background-color: #fffacd;");

		VBox pc = new VBox(rulesPane);

		return new Scene(pc, 1000, 750);

	}

	//Creates a scene that displays the odds of the game
	public Scene displayOdds(){
		Text headerodd = new Text ("\nOdds for $1 Play\n\n");
		Text intro = new Text("1 Spot Game: Overall Odd is 1 in 4.00\n"+
				"4 Spot Game: Overall Odd is 1 in 3.86\n"+
				"8 Spot Game: Overall Odd is 1 in 9.77\n"+
				"10 Spot Game: Overall Odd is 1 in 9.05\n"
		);

		headerodd.setFill(Color.TOMATO);
		headerodd.setFont(Font.font("Andale Mono", FontWeight.BOLD,25));
		headerodd.setTextAlignment(TextAlignment.CENTER);
		intro.setFont(Font.font("Andale Mono",20));
		intro.setTextAlignment(TextAlignment.JUSTIFY);
		intro.setFill(Color.CADETBLUE);

		//add rule and prize image for better visualization
		Image image = new Image("file:src/main/resources/IMG_0018.png");
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(450);
		imageView.setFitHeight(450);


		TextFlow textFlow = new TextFlow(headerodd, intro);
		BorderPane.setAlignment(textFlow, Pos.CENTER);
		BorderPane.setAlignment(oddBack, Pos.CENTER_LEFT);


		BorderPane.setMargin(textFlow, new Insets(10));

		oddsPane = new BorderPane();
		oddsPane.setPrefSize(1000,750);
		oddsPane.setPadding(new Insets(30));
		oddsPane.setTop(oddBack);
		oddsPane.setStyle("-fx-background-color: #fffacd;");
		//set the center node of a layout pane, also set a little bit spacing
		oddsPane.setCenter(new VBox(20,textFlow, imageView));

		VBox paneCenter = new VBox(oddsPane);

		return new Scene(paneCenter, 1000, 750);
	}


	@Override
	public void start(Stage primaryStage) throws Exception {

		main = primaryStage;
		primaryStage.setTitle("Keno Game");

		player = new Player(10);
		keno = new KenoGame();

		mainPane = mainp();

		//main scene
		//#####set up the menu pane########
		menu = new MenuBar();
		menu.setPrefSize(50,50);
		m = new Menu("Menu");
		m.setStyle("-fx-font-family: 'Arial Rounded MT Bold';");
		rules = new MenuItem("Rules");
		rules.setStyle("-fx-font-family: 'Arial Rounded MT Bold';");
		odds = new MenuItem("Odds");
		odds.setStyle("-fx-font-family: 'Arial Rounded MT Bold';");
		exit = new MenuItem("Exit");
		exit.setStyle("-fx-font-family: 'Arial Rounded MT Bold';");

		newLook = new MenuItem("New Look");
		m.getItems().addAll(rules,odds,exit);
		menu.getMenus().add(m);

		mainPane.setTop(menu);

		//add back button for going back to the previos page
		ruleBack = new Button("Back");
		ruleBack.setStyle("-fx-font-family: 'Arial Rounded MT Bold';");
		oddBack = new Button("Back");
		oddBack.setStyle("-fx-font-family: 'Arial Rounded MT Bold';");

		//here will all be in play scene##################################################//
		oneSpot = new RadioButton("1");
		fourSpots = new RadioButton("4");
		eightSpots = new RadioButton("8");
		tenSpots = new RadioButton("10");

		oneDraw = new RadioButton("1");
		twoDraws = new RadioButton("2");
		threeDraws = new RadioButton("3");
		fourDraws = new RadioButton("4");

		//some other buttons
		quickPick = new Button("QuickPick");
		quickPick.setStyle("-fx-font-family: 'Arial Rounded MT Bold';");
		clear = new Button("Clear Section");
		clear.setStyle("-fx-font-family: 'Arial Rounded MT Bold';");
		startDrawing = new Button("Start Drawing");
		startDrawing.setStyle("-fx-font-family: 'Arial Rounded MT Bold';");
		nextDrawing = new Button("Next Drawing");
		nextDrawing.setStyle("-fx-font-family: 'Arial Rounded MT Bold';");
		startNewGame = new Button("Start a New Game");
		startNewGame.setStyle("-fx-font-family: 'Arial Rounded MT Bold';");

		pause = new Button("Start a New Game");
		pause.setStyle("-fx-font-family: 'Arial Rounded MT Bold';");


		//create a Grid Pane for bet cards
		betCards = new BetCard[10][8];
		grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		int betcardnum =1;
		int numBallsSelected = 0;
		for(int i =0; i<10;i++){
			for(int j = 0; j<8; j++){
				Button ball = new Button(Integer.toString(betcardnum));
				BetCard betCard = new BetCard(betcardnum);
				ball.setStyle("-fx-font-family: 'Arial Rounded MT Bold';"+
						" -fx-background-radius: 10em;" +
						"-fx-min-width: 40px; " +
						"-fx-min-height: 40px; " +
						"-fx-max-width: 40px; " +
						"-fx-max-height: 40px;"+
						"-fx-background-color: #b6aeae;" );

				ball.setOnAction(event -> {
					clear.setDisable(false);
					quickPick.setDisable(false);
					if (numBallsSelected < player.getNumSpots()) {
						boolean isSelected = betCard.getSelected();
						betCard.setSelected(!isSelected);
						//Update button appearance based on selected state
						//ball was previously unselected, now selected
						if (!isSelected) {
							player.select(betCard);
							ball.setStyle("-fx-font-family: 'Arial Rounded MT Bold';" +
									" -fx-background-radius: 10em;" +
									"-fx-min-width: 40px; " +
									"-fx-min-height: 40px; " +
									"-fx-max-width: 40px; " +
									"-fx-max-height: 40px;" +
									"-fx-background-color: rgba(20,108,43,0.77);");

							int index = player.getSelectedNums().size() - 1;
							Button userball = (Button) usergrid.getChildren().get(index);
							userball.setText(Integer.toString(betCard.getCard()));
							userball.setStyle("-fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 10px;" +
									" -fx-background-radius: 10em;" +
									"-fx-min-width: 30px; " +
									"-fx-min-height: 30px; " +
									"-fx-max-width: 30px; " +
									"-fx-max-height: 30px;" +
									"-fx-background-color: rgba(20,108,43,0.77);");
							//ball was previously selected, now unselected
						} else{
							player.removeSelectNumber(betCard);
							ball.setStyle("-fx-font-family: 'Arial Rounded MT Bold';" +
									" -fx-background-radius: 10em;" +
									"-fx-min-width: 40px; " +
									"-fx-min-height: 40px; " +
									"-fx-max-width: 40px; " +
									"-fx-max-height: 40px;" +
									"-fx-background-color: #d2c9c9;");
							int index = 0;
							while (index < spots) {
								Button userball = (Button) usergrid.getChildren().get(index);
								if (userball.getText().equals(Integer.toString(betCard.getCard()))) { // find the button with the unselected ball
									userball.setText("?");
									userball.setStyle("-fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 10px;" +
											" -fx-background-radius: 10em;" +
											"-fx-min-width: 30px; " +
											"-fx-min-height: 30px; " +
											"-fx-max-width: 30px; " +
											"-fx-max-height: 30px;" +
											"-fx-background-color: rgb(182,174,174);");
									break;
								}
								index++;
							}
						}
					}

				});
				grid.add(ball, j, i);
				betcardnum ++;
				ball.setDisable(false);

			}
		}
		grid.setHgap(20);
		grid.setVgap(20);



		drawinggrid = new GridPane();
		drawinggrid.setAlignment(Pos.CENTER);
		int betcardnum1 =1;
		for(int p =0; p<10;p++){
			for(int a = 0; a<8; a++){
				Button drawingball = new Button(Integer.toString(betcardnum1));
				drawingball.setStyle("-fx-font-family: 'Arial Rounded MT Bold';"+
						" -fx-background-radius: 10em;" +
						"-fx-min-width: 40px; " +
						"-fx-min-height: 40px; " +
						"-fx-max-width: 40px; " +
						"-fx-max-height: 40px;"+
						"-fx-background-color: #d2c9c9;" );
				drawinggrid.add(drawingball, a, p);
				betcardnum1 ++;
				drawingball.setDisable(false);
			}
		}
		drawinggrid.setHgap(20);
		drawinggrid.setVgap(20);

		SceneMap.put("rules", displayRule());
		SceneMap.put("odds", displayOdds());


		//for easily switch between different scene
		//rules is associated with the value returned by the displayRule()
		SceneMap.put("rules", displayRule());
		//odds is associated with the value returned by the displayOdd()
		SceneMap.put("odds", displayOdds());

		//###########################
		oneSpot.setOnAction(e->{
			spots = usernumSpot(player);
			{
				if(spots == 1) {
					Alert alert = new Alert(Alert.AlertType.NONE);
					alert.setTitle("");
					alert.setHeaderText(null);
					alert.setContentText("Play " + Integer.toString(spots) + " spot.");
					alert.getDialogPane().lookup(".content.label").setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px;");
					Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
					okButton.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px;");
					alert.show();
				}
			}
		});
		fourSpots.setOnAction(e->{
			spots = usernumSpot(player);
			{
				if(spots == 4) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("");
					alert.setHeaderText(null);
					alert.setContentText("Play " + Integer.toString(player.getNumSpots()) + " spots.");
					alert.getDialogPane().lookup(".content.label").setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px;");
					Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
					okButton.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px;");
					alert.show();
				}
			}

		});
		eightSpots.setOnAction(e->{
			spots = usernumSpot(player);
			{
				if(spots == 8) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("");
					alert.setHeaderText(null);
					alert.setContentText("Play " + Integer.toString(spots) + " spots.");
					alert.getDialogPane().lookup(".content.label").setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px;");
					Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
					okButton.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px;");
					alert.show();
				}
			}
		});
		tenSpots.setOnAction(e->{
			spots = usernumSpot(player);
			{
				if(spots == 10) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("");
					alert.setHeaderText(null);
					alert.setContentText("Play " + Integer.toString(spots) + " spots.");
					alert.getDialogPane().lookup(".content.label").setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px;");
					Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
					okButton.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px;");
					alert.show();
				}
			}
		});

		//###########################
		oneDraw.setOnAction(e->{
			draw = usernumDraw(player);
			{
				if(draw == 1) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("");
					alert.setHeaderText(null);
					alert.setContentText("Play " + Integer.toString(draw) + " draw.");
					alert.getDialogPane().lookup(".content.label").setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px;");
					Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
					okButton.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px;");
					alert.show();
				}
			}
		});
		twoDraws.setOnAction(e->{
			draw = usernumDraw(player);
			{
				if(draw == 2) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("");
					alert.setHeaderText(null);
					alert.setContentText("Play " + Integer.toString(draw) + " draws.");
					alert.getDialogPane().lookup(".content.label").setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px;");
					Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
					okButton.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px;");
					alert.show();
				}
			}
		});
		threeDraws.setOnAction(e->{
			draw = usernumDraw(player);
			{
				if(draw == 3) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("");
					alert.setHeaderText(null);
					alert.setContentText("Play " + Integer.toString(draw) + " draws.");
					alert.getDialogPane().lookup(".content.label").setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px;");
					Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
					okButton.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px;");
					alert.show();
				}
			}
		});
		fourDraws.setOnAction(e->{
			draw = usernumDraw(player);
			{
				if(draw == 4) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("");
					alert.setHeaderText(null);
					alert.setContentText("Play " + Integer.toString(draw) + " draws.");
					alert.getDialogPane().lookup(".content.label").setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px;");
					Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
					okButton.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px;");
					alert.show();
				}
			}
		});
		//############################################
		rules.setOnAction(e->{
			main.setScene(SceneMap.get("rules"));
		});

		odds.setOnAction(e->{
			main.setScene(SceneMap.get("odds"));
		});

		exit.setOnAction(e->{
			Platform.exit();
			System.exit(0);
		});

		play.setOnAction(e-> {
			playScene = true;
			SceneMap.put("play", gameScene());
			main.setScene(SceneMap.get("play"));
		});


		startDrawing.setOnAction(startdrawingrun);
		nextDrawing.setOnAction(startdrawingrun);
		clear.setOnAction(clearall);


		usergrid = new GridPane();
		usergrid.setAlignment(Pos.CENTER);
		for(int k=0; k< 5;k++) {
			for (int g = 0; g < 2;g++){
				Button userball = new Button("?");
				userball.setStyle("-fx-font-family: 'Arial Rounded MT Bold';" +
						" -fx-background-radius: 10em;" +
						"-fx-min-width: 30px; " +
						"-fx-min-height: 30px; " +
						"-fx-max-width: 30px; " +
						"-fx-max-height: 30px;" +
						"-fx-background-color: #d2c9c9;");


				usergrid.add(userball, k, g);
				usergrid.setDisable(false);
			}
		}
		usergrid.setHgap(20);
		usergrid.setVgap(20);

		//eventHandler for handling back buttom
		EventHandler<ActionEvent> handlerforBack = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				//check which scene is in now since we have menu in each home and play scene
				if(playScene) {
					//if it is in play scene, when you click back, it will take you back to where it suppose to be
					main.setScene(SceneMap.get("play"));
				}else {
					Scene homeScene = new Scene(new VBox(20,menu, mainPane), 1000,750);
					main.setScene(homeScene);
				}

				if(drawScene){
					main.setScene(SceneMap.get("drawing"));
				}
			}
		};

		nextDrawing.setDisable(true);

		ruleBack.setOnAction(handlerforBack);
		oddBack.setOnAction(handlerforBack);

		quickPick.setOnAction(quickpickk);

		//set main box as root node
		VBox mainbox = new VBox(30,menu, mainPane);
		Scene scene = new Scene(mainbox, 1000,750);
		//set Scene object as the root of the main stage
		main.setScene(scene);
		//feel free to comment this out
		main.setResizable(false);
		//displays the main Stage window to the user.
		main.show();

	}


	//Creare another scene for gaming part
	public Scene gameScene(){
		//like the main scene, we also need a menu here with an addition "New Look" option
		m.getItems().clear();
		menu.getMenus().clear();
		m.getItems().addAll(rules, odds, newLook, exit);
		menu.getMenus().add(m);

		//prompt part: ask user to choose how many spots they want to play
		//             and                how many draw they want to play
		Text spot,draws, numberselected, run;
		spot = new Text("Choose the number of spot you want to play.");
		spot.setStyle("-fx-font-family: 'Arial Rounded MT Bold';" + "-fx-text-fill: #8b4513;");
		draws = new Text("Choose number of consecutive drawing");
		draws.setStyle("-fx-font-family: 'Arial Rounded MT Bold';" + "-fx-text-fill: #8b4513;");
		numberselected = new Text("Select your number from the left\n");
		numberselected.setStyle("-fx-font-family: 'Arial Rounded MT Bold';" + "-fx-text-fill: #8b4513;");
		run = new Text("\n");

		//use togglegroup here to make sure that only one of the options can be selected at any given time
		ToggleGroup numspots = new ToggleGroup();
		oneSpot.setToggleGroup(numspots);
		oneSpot.setStyle("-fx-font-family: 'Arial Rounded MT Bold';" + "-fx-text-fill: #8b4513;");
		fourSpots.setToggleGroup(numspots);
		fourSpots.setStyle("-fx-font-family: 'Arial Rounded MT Bold';" + "-fx-text-fill: #8b4513;");
		eightSpots.setToggleGroup(numspots);
		eightSpots.setStyle("-fx-font-family: 'Arial Rounded MT Bold';" + "-fx-text-fill: #8b4513;");
		tenSpots.setToggleGroup(numspots);
		tenSpots.setStyle("-fx-font-family: 'Arial Rounded MT Bold';" + "-fx-text-fill: #8b4513;");

		oneSpot.setDisable(false);
		fourSpots.setDisable(false);
		eightSpots.setDisable(false);
		tenSpots.setDisable(false);

		ToggleGroup numdraws = new ToggleGroup();
		oneDraw.setToggleGroup(numdraws);
		oneDraw.setStyle("-fx-font-family: 'Arial Rounded MT Bold';" + "-fx-text-fill: #8b4513;");
		twoDraws.setToggleGroup(numdraws);
		twoDraws.setStyle("-fx-font-family: 'Arial Rounded MT Bold';" + "-fx-text-fill: #8b4513;");
		threeDraws.setToggleGroup(numdraws);
		threeDraws.setStyle("-fx-font-family: 'Arial Rounded MT Bold';" + "-fx-text-fill: #8b4513;");
		fourDraws.setToggleGroup(numdraws);
		fourDraws.setStyle("-fx-font-family: 'Arial Rounded MT Bold';" + "-fx-text-fill: #8b4513;");

		oneDraw.setDisable(false);
		twoDraws.setDisable(false);
		threeDraws.setDisable(false);
		fourDraws.setDisable(false);

		newLook.setOnAction(e->{
			spot.setFont(Font.font("Verdana", FontWeight.BOLD,14));
			spot.setFill(Color.MEDIUMPURPLE);
			draws.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
			draws.setFill(Color.MEDIUMPURPLE);
			numberselected.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
			numberselected.setFill(Color.MEDIUMPURPLE);
			oneSpot.setStyle("-fx-text-fill: #efebeb; ");
			oneSpot.setFont(Font.font("Verdana"));
			fourSpots.setStyle("-fx-text-fill: #efebeb; ");
			fourSpots.setFont(Font.font("Verdana"));
			eightSpots.setStyle("-fx-text-fill: #efebeb; ");
			eightSpots.setFont(Font.font("Verdana"));
			tenSpots.setStyle("-fx-text-fill: #efebeb; ");
			tenSpots.setFont(Font.font("Verdana"));
			oneDraw.setStyle("-fx-text-fill: #efebeb; ");
			oneDraw.setFont(Font.font("Verdana"));
			twoDraws.setStyle("-fx-text-fill: #efebeb; ");
			twoDraws.setFont(Font.font("Verdana"));
			threeDraws.setStyle("-fx-text-fill: #efebeb; ");
			threeDraws.setFont(Font.font("Verdana"));
			fourDraws.setStyle("-fx-text-fill: #efebeb; ");
			fourDraws.setFont(Font.font("Verdana"));
			gamePane.setStyle("-fx-background-color: #20282d;");

		});

		//####################
		HBox spotbox = new HBox(50);
		HBox drawbox = new HBox(50);
		HBox useraction = new HBox(50);

		spotbox.setPadding(new Insets(10,10,10,10));
		spotbox.getChildren().addAll(oneSpot, fourSpots, eightSpots, tenSpots);

		drawbox.setPadding(new Insets(10,10,10,10));
		drawbox.getChildren().addAll(oneDraw, twoDraws, threeDraws, fourDraws);

		useraction.setPadding(new Insets(10));
		useraction.getChildren().addAll(clear,quickPick);

		spotbox.setAlignment(Pos.CENTER);
		drawbox.setAlignment(Pos.CENTER);
		useraction.setAlignment(Pos.CENTER);
		usergrid.setAlignment(Pos.CENTER);

		useraction.setDisable(false);

		startDrawing.setDisable(false);

		choice = new VBox(10);
		choice.setPadding(new Insets(10,10,10,10));
		choice.setAlignment(Pos.CENTER);

		startDrawing.setPrefSize(155,55);
		choice.getChildren().addAll(spot,spotbox,draws, drawbox,numberselected,usergrid, useraction,run, startDrawing);

		userchoice = new BorderPane();
		userchoice.setPadding(new Insets(20));
		userchoice.setLeft(choice);

		//#############
		gamePane = new BorderPane();
		gamePane.setPrefSize(700,800);
		gamePane.setPadding(new Insets(30));
		gamePane.setLeft(userchoice);
		gamePane.setRight(grid);
		gamePane.setStyle("-fx-background-color: #fffacd;");

		VBox pc = new VBox(20,menu, gamePane);

		return new Scene(pc, 1000, 750);
	}

	public Scene drwaingScene(){
		//like the main scene, we also need a menu here with an addition "New Look" option
		m.getItems().clear();
		menu.getMenus().clear();
		m.getItems().addAll(rules, odds, newLook, exit);
		menu.getMenus().add(m);

		Text result = new Text("Here is your result: ");
		result.setStyle("-fx-font-family: 'Arial Rounded MT Bold';" + "-fx-text-fill: #8b4513;");
		ArrayList<BetCard> selectedNums = player.getSelectedNums();
		StringBuilder numsStrBuilder = new StringBuilder();
		for (BetCard betCard : selectedNums) {
			numsStrBuilder.append(betCard.getCard()).append(", ");
		}
		String numsStr = numsStrBuilder.toString();
		if (!selectedNums.isEmpty()) {
			// remove the last comma and space
			numsStr = numsStr.substring(0, numsStr.length() - 2);
		}
		Text usernumber = new Text("You Played: " + numsStr);

		usernumber.setStyle("-fx-font-family: 'Arial Rounded MT Bold';" + "-fx-text-fill: #8b4513;");
		Text drwaingnums = new Text("Number of draws left: "+ (player.getNumDrawings()-1));
		drwaingnums.setStyle("-fx-font-family: 'Arial Rounded MT Bold';" + "-fx-text-fill: #8b4513;");


		Text balance2 = new Text("Your Balance now: " + Double.toString(player.getBalance()+player.getTotalwining()-1));
		balance2.setStyle("-fx-font-family: 'Arial Rounded MT Bold';" + "-fx-text-fill: #8b4513;");

		newLook.setOnAction(e->{
			result.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
			result.setFill(Color.MEDIUMPURPLE);
			usernumber.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
			usernumber.setFill(Color.MEDIUMPURPLE);
			drwaingnums.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
			drwaingnums.setFill(Color.MEDIUMPURPLE);
//			matches.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
//			matches.setFill(Color.MEDIUMPURPLE);
//			matchNum.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
//			matchNum.setFill(Color.MEDIUMPURPLE);
//			prize.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
//			prize.setFill(Color.MEDIUMPURPLE);
			balance2.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
			balance2.setFill(Color.MEDIUMPURPLE);
			drawPane.setStyle("-fx-background-color: #20282d;");

		});


		VBox displaybox = new VBox();
		displaybox.setPadding(new Insets(10,10,10,10));
		displaybox.setAlignment(Pos.CENTER_LEFT);
		displaybox.getChildren().addAll(result,usernumber,drwaingnums,balance2);


		nextDrawing.setPrefSize(155,55);

		drawPane = new BorderPane();
		drawPane.setPrefSize(700,800);
		drawPane.setPadding(new Insets(30));
		drawPane.setLeft(drawinggrid);
		drawPane.setRight(displaybox);
		drawPane.setBottom(nextDrawing);
		drawPane.setAlignment(nextDrawing, Pos.BOTTOM_RIGHT);
		drawPane.setStyle("-fx-background-color: #fffacd;");

		VBox pc = new VBox(20,menu, drawPane);

		return new Scene(pc, 1000, 750);
	}

	public int usernumSpot(Player user){
		if(oneSpot.isSelected()) {
			user.setNumSpots(1);
		}
		else if(fourSpots.isSelected()) {
			user.setNumSpots(4);
		}
		else if(eightSpots.isSelected()) {
			user.setNumSpots(8);
		}
		else if(tenSpots.isSelected()) {
			user.setNumSpots(10);
		}


		if(user.getNumSpots() == 0){
			grid.setDisable(true);
		}
		if(user.isDrawingStarted()) {
			oneSpot.setDisable(true);
			fourSpots.setDisable(true);
			eightSpots.setDisable(true);
			tenSpots.setDisable(true);
		}

		return user.getNumSpots();
	}


	public int usernumDraw(Player user){
		if(oneDraw.isSelected()) {
			user.setNumDrawings(1);
		}
		else if(twoDraws.isSelected()) {
			user.setNumDrawings(2);
		}
		else if (threeDraws.isSelected()) {
			user.setNumDrawings(3);
		}
		else if (fourDraws.isSelected()) {
			user.setNumDrawings(4);
		}

		if(user.isDrawingStarted()) {
			oneDraw.setDisable(true);
			twoDraws.setDisable(true);
			threeDraws.setDisable(true);
			fourDraws.setDisable(true);
		}

		return user.getNumDrawings();
	}


	EventHandler<ActionEvent> startdrawingrun = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			drawScene = true;
			SceneMap.put("drawing", drwaingScene());
			main.setScene(SceneMap.get("drawing"));
			nextDrawing.setDisable(true);

			for(int xx = 0; xx<8; xx++) {
				for(int ii = 0; ii<10; ii++) {
					Button bb = (Button) drawinggrid.getChildren().get(xx*10+ii);
					bb.setStyle("-fx-font-family: 'Arial Rounded MT Bold';"+
							" -fx-background-radius: 10em;" +
							"-fx-min-width: 40px; " +
							"-fx-min-height: 40px; " +
							"-fx-max-width: 40px; " +
							"-fx-max-height: 40px;"+
							"-fx-background-color: #d2c9c9;");
				}
			}

			player.play(keno);

			int time = 500;
			int draw = player.getNumDrawings();
			if (player.getNumDrawings() >= 0) {
				ArrayList<BetCard>drawNum = keno.getDrawingNums();
				int i = 0;
				while (i < 20) {
					int row = drawNum.get(i).getRow();
					int col = drawNum.get(i).getColumn();
					Button bcard = (Button) drawinggrid.getChildren().get((row*10+col)-1);
					PauseTransition p = new PauseTransition(Duration.millis(time));
					p.setOnFinished(m->{
						bcard.setStyle("-fx-font-family: 'Arial Rounded MT Bold';"+
								" -fx-background-radius: 10em;" +
								"-fx-min-width: 40px; " +
								"-fx-min-height: 40px; " +
								"-fx-max-width: 40px; " +
								"-fx-max-height: 40px;"+
								"-fx-background-color: #eed868;");

					});
					p.play();
					time += 700;
					i++;

				}
				draw--;
				PauseTransition rs = new PauseTransition(Duration.millis(time));
				rs.setOnFinished(x->{
					resultmsg();
				});
				rs.play();
				PauseTransition nextDrawPause = new PauseTransition(Duration.millis(time));
				nextDrawPause.setOnFinished(x->{
					nextDrawing.setDisable(false);
				});
				nextDrawPause.play();



			}

			if(player.getNumDrawings() == 0){
				PauseTransition ppp = new PauseTransition(Duration.millis(time));
				ppp.setOnFinished(x->{
					nextDrawing.setText("Done");
					nextDrawing.setDisable(false);
				});
				ppp.play();
			}

			if(player.getNumDrawings() == -1) {
				time += 500;
//				PauseTransition rss = new PauseTransition(Duration.millis(time));
//				rss.setOnFinished(x->{
//					resultmsg2();
//				});
//				rss.play();
				PauseTransition nextFinalPause = new PauseTransition(Duration.millis(time));
				nextFinalPause.setOnFinished(x->{
					nextDrawing.setDisable(true);
					resultmsg2();
				});
				nextFinalPause.play();
			}
		}

	};

	EventHandler<ActionEvent> quickpickk = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {

			player.quickPick(spots);
			ArrayList<BetCard>selection = player.getSelectedNums();
			int time = 500;
			int index = 0;
			while (index < spots) {
				int row = selection.get(index).getRow();
				int col = selection.get(index).getColumn();
				Button betcard = (Button) grid.getChildren().get((row*10+col)-1);
				if(selection.get(index).getSelected()) {
					PauseTransition p = new PauseTransition(Duration.millis(time));
					p.setOnFinished(m->{
						betcard.setStyle("-fx-font-family: 'Arial Rounded MT Bold';"+
								" -fx-background-radius: 10em;" +
								"-fx-min-width: 40px; " +
								"-fx-min-height: 40px; " +
								"-fx-max-width: 40px; " +
								"-fx-max-height: 40px;"+
								"-fx-background-color: rgba(20,108,43,0.77);");
					});
					p.play();
				} else {
					betcard.setDisable(true);
				}
				index++;
				time += 1000;
			}
			index = 0;
			time = 500;
			while (index < spots) {
				Button userball = (Button) usergrid.getChildren().get(index);
				int selectedNum = selection.get(index).getCard();
				PauseTransition p = new PauseTransition(Duration.millis(time));
				p.setOnFinished(m->{
					userball.setText(Integer.toString((selectedNum)));
					userball.setStyle("-fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 10px;"+
							" -fx-background-radius: 10em;" +
							"-fx-min-width: 30px; " +
							"-fx-min-height: 30px; " +
							"-fx-max-width: 30px; " +
							"-fx-max-height: 30px;"+
							"-fx-background-color: rgba(20,108,43,0.77);");

				});
				p.play();
				index++;
				time += 1000;
			}

			PauseTransition p = new PauseTransition(Duration.millis(time));
			p.setOnFinished(x->{
				startDrawing.setDisable(false);
			});
			p.play();

			quickPick.setDisable(true);
			clear.setDisable(false);
			grid.setDisable(true);

		}

	};

	EventHandler<ActionEvent> clearall = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {

			ArrayList<BetCard> selection = player.getSelectedNums();
			int time = 500;
			int index = 0;
			while (index < spots) {
				int row = selection.get(index).getRow();
				int col = selection.get(index).getColumn();
				Button betcard = (Button) grid.getChildren().get((row*10+col)-1);
				if (selection.get(index).getSelected()) {
					PauseTransition p = new PauseTransition(Duration.millis(time));
					p.setOnFinished(m -> {
						betcard.setStyle("-fx-font-family: 'Arial Rounded MT Bold';" +
								" -fx-background-radius: 10em;" +
								"-fx-min-width: 40px; " +
								"-fx-min-height: 40px; " +
								"-fx-max-width: 40px; " +
								"-fx-max-height: 40px;" +
								"-fx-background-color: rgb(182,174,174);");
					});
					p.play();
				} else {
					betcard.setDisable(true);
				}
				index++;
				time += 1000;
			}
			index = 0;
			time = 500;
			while (index < spots) {
				Button userball = (Button) usergrid.getChildren().get(index);
				int selectedNum = selection.get(index).getCard();
				PauseTransition p = new PauseTransition(Duration.millis(time));
				p.setOnFinished(m -> {
					userball.setText("?");
					userball.setStyle("-fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 10px;" +
							" -fx-background-radius: 10em;" +
							"-fx-min-width: 30px; " +
							"-fx-min-height: 30px; " +
							"-fx-max-width: 30px; " +
							"-fx-max-height: 30px;" +
							"-fx-background-color: rgb(210,201,201);");
				});
				p.play();
				index++;
				time += 1000;
			}

			PauseTransition p = new PauseTransition(Duration.millis(time));
			p.setOnFinished(x -> {
				startDrawing.setDisable(false);
			});
			p.play();

			player.clear();
			grid.setDisable(false);
			clear.setDisable(false);
			quickPick.setDisable(false);

		}


	};


	public void resultmsg() {
		Stage result_ = new Stage();
		result_.setTitle("Result");
		Button ok = new Button("OK");

		ArrayList<BetCard> drawr = keno.getDrawingNums();
		StringBuilder sb = new StringBuilder();
		for (BetCard c : drawr) {
			sb.append(c.getCard()).append(", ");
		}
		String nss = sb.toString();
		if (!drawr.isEmpty()) {
			// remove the last comma and space
			nss = nss.substring(0, nss.length() - 2);
		}
		Text drawingN = new Text("DRAW: \n" + nss);
		drawingN.setStyle("-fx-font-family: 'Arial Rounded MT Bold';" + "-fx-text-fill: #8b4513;");

		Text matches = new Text("How many numbers match: " + Integer.toString(player.getMatched().size()));
		matches.setStyle("-fx-font-family: 'Arial Rounded MT Bold';" + "-fx-text-fill: #8b4513;");
		ArrayList<BetCard>matchedNumber = player.getMatched();
		StringBuilder tostring = new StringBuilder();
		for(BetCard b: matchedNumber){
			tostring.append(b.getCard()).append(",");
		}
		String ns = tostring.toString();
		if(!matchedNumber.isEmpty()){
			ns=ns.substring(0,ns.length()-2);
		}
		Text matchNum = new Text("Number matched: " + ns);
		matchNum.setStyle("-fx-font-family: 'Arial Rounded MT Bold';" + "-fx-text-fill: #8b4513;");
		Text prize = new Text("Yon have won: " + Double.toString(player.getWining()));
		prize.setStyle("-fx-font-family: 'Arial Rounded MT Bold';" + "-fx-text-fill: #8b4513;");

		drawingN.setTextAlignment(TextAlignment.JUSTIFY);
		matches.setTextAlignment(TextAlignment.JUSTIFY);
		matchNum.setTextAlignment(TextAlignment.JUSTIFY);
		prize.setTextAlignment(TextAlignment.JUSTIFY);
		VBox l = new VBox(20, drawingN,matches,matchNum, prize, ok);
		l.setAlignment(Pos.CENTER);

		ok.setStyle("-fx-font-family: 'Arial Rounded MT Bold';");
		l.setStyle("-fx-font-family: 'Arial Rounded MT Bold';");
		result_.setResizable(false);


		ok.setOnAction(e-> {
			result_.close();
		});

		Scene messageScene = new Scene(l, 500, 300);
		result_.setScene(messageScene);
		result_.show();
	}


	public void resultmsg2() {
		Stage result_ = new Stage();
		result_.setTitle("Result");
		Button playagain = new Button("Play Again");
		Button  exit = new Button("Bye");


		ArrayList<BetCard> drawr = keno.getDrawingNums();
		StringBuilder sb = new StringBuilder();
		for (BetCard c : drawr) {
			sb.append(c.getCard()).append(", ");
		}
		String nss = sb.toString();
		if (!drawr.isEmpty()) {
			// remove the last comma and space
			nss = nss.substring(0, nss.length() - 2);
		}
		Text drawingN = new Text("DRAW: \n" + nss);
		drawingN.setStyle("-fx-font-family: 'Arial Rounded MT Bold';" + "-fx-text-fill: #8b4513;");

		Text matches = new Text("Matches: " + Integer.toString(player.getMatched().size()));
		matches.setStyle("-fx-font-family: 'Arial Rounded MT Bold';" + "-fx-text-fill: #8b4513;");
		ArrayList<BetCard>matchedNumber = player.getMatched();
		StringBuilder tostring = new StringBuilder();
		for(BetCard b: matchedNumber){
			tostring.append(b.getCard()).append(",");
		}
		String ns = tostring.toString();
		if(!matchedNumber.isEmpty()){
			ns=ns.substring(0,ns.length()-2);
		}
		Text matchNum = new Text("Matched Number: " + ns);
		matchNum.setStyle("-fx-font-family: 'Arial Rounded MT Bold';" + "-fx-text-fill: #8b4513;");
		Text prize = new Text("Yon totally won: $" + Double.toString(player.getTotalwining()));
		prize.setStyle("-fx-font-family: 'Arial Rounded MT Bold';" + "-fx-text-fill: #8b4513;");

		Text thankyou = new Text("\n\nThank You For Playing KENO!!");
		thankyou.setStyle("-fx-font-family: 'Arial Rounded MT Bold';" + "-fx-text-fill: #ce1334;");

		drawingN.setTextAlignment(TextAlignment.JUSTIFY);
		matches.setTextAlignment(TextAlignment.JUSTIFY);
		matchNum.setTextAlignment(TextAlignment.JUSTIFY);
		prize.setTextAlignment(TextAlignment.JUSTIFY);
		thankyou.setTextAlignment(TextAlignment.JUSTIFY);
		HBox h = new HBox(30, playagain, exit);
		VBox l = new VBox(20,  prize,thankyou, h);
		h.setAlignment(Pos.CENTER);
		l.setAlignment(Pos.CENTER);

		playagain.setStyle("-fx-font-family: 'Arial Rounded MT Bold';");
		l.setStyle("-fx-font-family: 'Arial Rounded MT Bold';");
		result_.setResizable(false);


		playagain.setOnAction(e-> {
			result_.close();
			Scene homeScene = new Scene(new VBox(20,menu, mainPane), 1000,750);
			main.setScene(homeScene);
		});

		exit.setOnAction(e-> {
			Platform.exit();
			System.exit(0);
		});



		Scene messageScene = new Scene(l, 500, 300);
		result_.setScene(messageScene);
		result_.show();
	}























}
