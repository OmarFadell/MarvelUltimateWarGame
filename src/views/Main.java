package views;

import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;

import model.abilities.AreaOfEffect;
import engine.Game;
import engine.Player;
import engine.PriorityQueue;
import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.InvalidTargetException;
import exceptions.LeaderAbilityAlreadyUsedException;
import exceptions.LeaderNotCurrentException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;
import model.abilities.Ability;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.effects.Effect;
import model.world.AntiHero;
import model.world.Champion;
import model.world.Cover;
import model.world.Damageable;
import model.world.Direction;
import model.world.Hero;
import model.world.Villain;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Main extends Application {

	private Stage window;
	private Scene scene1, scene2, scene3, scene4, scene5, scene6;
	// private String p1name,p2name;
	private TextField name1, name2;
	// Button
	// cap2,deadpool2,electro2,ghostrider2,hela2,hulk2,iceman2,ironman2,loki2,quicksilver2,spiderman2,strange2,thor2,venom2,yellowjacket2;
	private HBox Champions11, layout3, layout32, layout5, layout6, layout5playernames;

	// static int vboxnum = 0;

	private ArrayList<String> champions = new ArrayList<>();

	private Player p1, p2;
	private Game g;
	private VBox choose, layout4, choose1;
	private Label CL, CL2, player1name, player2name;
	private VBox Layout5, AppliedEffectsp1, AppliedEffectsp2, first, second, inAppEff1, inAppEff2;
	
	//private Point p = new Point(-1,-1);
	
	private boolean flagST = false;
	private Ability tempa;
	private TabPane TBP1,TBP2, AppEff1, AppEff2;
	//private Font Comic;
	private MediaPlayer mp;
	private Font av20,av30,avengebutton;
	private BackgroundImage gamebg;
	private BorderPane layout51;
	private ArrayList<ImageView> champs;
	

	public void start(Stage primaryStage) throws IOException {

		window = primaryStage;
		window.setResizable(false);
		
		//window.setResizable(false);
	//primaryStage.setFullScreen(true);
	
		music();
		final Font avenge = Font.loadFont(new FileInputStream("C:\\Users\\omrhs\\Documents\\College\\Semester 4\\Game\\M3\\final\\src\\AVENGEANCE HEROIC AVENGER BD.ttf"), 60);
		avengebutton = Font.loadFont(new FileInputStream("C:\\Users\\omrhs\\Documents\\College\\Semester 4\\Game\\M3\\final\\src\\AVENGEANCE HEROIC AVENGER BD.ttf"), 40);
		av20 = Font.loadFont(new FileInputStream("C:\\Users\\omrhs\\Documents\\College\\Semester 4\\Game\\M3\\final\\src\\AVENGEANCE HEROIC AVENGER BD.ttf"), 20);
		 av30 = Font.loadFont(new FileInputStream("C:\\Users\\omrhs\\Documents\\College\\Semester 4\\Game\\M3\\final\\src\\AVENGEANCE HEROIC AVENGER BD.ttf"), 30);
		
		// --------------------------------------------------------------------------------------------------------------CREATE BACKGROUND -----------------------------------
		 
		 champs = new ArrayList<>();
		
		BackgroundImage myBI= new BackgroundImage(new Image("bg.png"),BackgroundRepeat.NO_REPEAT, 
                BackgroundRepeat.NO_REPEAT, 
                BackgroundPosition.DEFAULT, 
                   BackgroundSize.DEFAULT);
		//then you set to your node
		
		
		BackgroundImage sczero= new BackgroundImage(new Image("ws.png"),BackgroundRepeat.NO_REPEAT, 
                BackgroundRepeat.NO_REPEAT, 
                BackgroundPosition.DEFAULT, 
                   BackgroundSize.DEFAULT);
		
		BackgroundImage gamebg= new BackgroundImage(new Image("gamebg.png"),BackgroundRepeat.REPEAT, 
                BackgroundRepeat.REPEAT, 
                BackgroundPosition.DEFAULT, 
                   BackgroundSize.DEFAULT);
		
		VBox zero = new VBox(20);
		zero.setAlignment(Pos.CENTER_LEFT);
		zero.setBackground(new Background(sczero));
		
		Label welcome = new Label("W E L C O M E  T O");
		welcome.setTextFill(Color.WHITE);
		welcome.setFont(Font.font("Calibri",FontWeight.BOLD,25));
		welcome.setTextAlignment(TextAlignment.CENTER);
		welcome.setAlignment(Pos.CENTER);
		//welcome.setTextFill(Color.YELLOW);
		welcome.setStyle("-fx-stroke: red; -fx-stroke-width: 10;");
		Label MUW = new Label("MARVEL: ULTIMATE WAR");
		MUW.setTextFill(Color.WHITE);
		MUW.setFont(avenge);
		Button cont = new Button("Begin");
		cont.setAlignment(Pos.CENTER);
		cont.setFont(avengebutton);
		cont.setTextFill(Color.WHITE);
		cont.setStyle(" -fx-background-color: transparent;");
		cont.setOnAction(e ->window.setScene(scene1));
		cont.setOnMouseEntered(e-> cont.setTextFill(Color.YELLOW));
		cont.setOnMouseExited(e-> cont.setTextFill(Color.WHITE));
		
		zero.getChildren().addAll(welcome,MUW,cont);
		Scene scene0 = new Scene(zero,1200,591);
		
		
		
	
		
		// -------------------First Player LOGIN --------------------
		Label Label1 = new Label("Enter Player 1 name");
		Label1.setFont(avenge);
	//	Label1.setStyle("background.css");
		Label1.setTextFill(Color.WHITE);
		name1 = new TextField();
		name1.setFont(av30);
		name1.setStyle("-fx-background-color: transparent;-fx-border-color: yellow;-fx-text-fill: #FFFF00;");
		
		//name1.setStyle("-fx-background-color: #000000;-fx-border-color: yellow;-fx-text-fill: #FFFFFF;");
		name1.setOnAction(e -> {
			
			if (name1.getText().isEmpty()) {
				AlertBox.display("Error", "Please enter a valid name!");
			
			} else {
				
			
		goToScene2();
			}
		});
		name1.setMaxWidth(300);
		
		
		Button button1 = new Button("Continue");
		button1.setAlignment(Pos.CENTER);
		button1.setFont(avengebutton);
		button1.setTextFill(Color.WHITE);
		button1.setStyle(" -fx-background-color: transparent;");
		button1.setOnAction(e -> {
			
			if (name1.getText().isEmpty()) {
				AlertBox.display("Error", "Please enter a valid name!");
			
			} else {
				
			
		goToScene2();
			}
		});
		button1.setOnMouseEntered(e-> button1.setTextFill(Color.YELLOW));
		button1.setOnMouseExited(e-> button1.setTextFill(Color.WHITE));

		VBox layout1 = new VBox(20);
		layout1.getChildren().addAll(Label1, name1, button1);
		

		layout1.setBackground(new Background(myBI));
		
		scene1 = new Scene(layout1, 1200, 591);
		
		layout1.setAlignment(Pos.CENTER);

	
		// ---------------Second Player Login ------------------------

		Label Label2 = new Label("Enter Player 2 name");
		Label2.setFont(avenge);
		Label2.setTextFill(Color.WHITE);
		name2 = new TextField();
		name2.setFont(av20);
		name2.setStyle("-fx-background-color: transparent;-fx-border-color: yellow;-fx-text-fill: #FFFF00;");
		name2.setOnAction(e -> {

			
			if (name2.getText().isEmpty()) {
				AlertBox.display("Error", "Please enter a valid name!");
			} else {
			
			try {
				goToScene3();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
			});
		name2.setMaxWidth(300);
		
		Button button2 = new Button("Continue");
		button2.setAlignment(Pos.CENTER);
		button2.setFont(avengebutton);
		button2.setTextFill(Color.WHITE);
		button2.setStyle(" -fx-background-color: transparent;");
		button2.setOnMouseEntered(e-> button2.setTextFill(Color.YELLOW));
		button2.setOnMouseExited(e-> button2.setTextFill(Color.WHITE));

		button2.setOnAction(e -> {

			
			if (name2.getText().isEmpty()) {
				AlertBox.display("Error", "Please enter a valid name!");
			} else {
			
			try {
				goToScene3();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
			});
		VBox layout2 = new VBox(20);
		layout2.setBackground(new Background(myBI));
		//layout2.setStyle("-fx-background-color: #000000;");
		layout2.getChildren().addAll(Label2, name2, button2);
		scene2 = new Scene(layout2, 1200, 591);
		layout2.setAlignment(Pos.CENTER);

		
	
		// -------------------------Create Buttons(Champions)---------

		Button thor = createHeroButton("thor.png");
		Button thor2 = createHeroButton("thor.png");

		Button cap = createHeroButton("cap.png");
		Button cap2 = createHeroButton("cap.png");

		Button electro = createHeroButton("electro.png");
		Button electro2 = createHeroButton("electro.png");

		Button loki = createHeroButton("loki.png");
		Button loki2 = createHeroButton("loki.png");

		Button hulk = createHeroButton("hulk.png");
		Button hulk2 = createHeroButton("hulk.png");

		Button deadpool = createHeroButton("deadpool.png");
		Button deadpool2 = createHeroButton("deadpool.png");

		Button ghostrider = createHeroButton("ghostrider.png");
		Button ghostrider2 = createHeroButton("ghostrider.png");

		Button iceman = createHeroButton("iceman.png");
		Button iceman2 = createHeroButton("iceman.png");

		Button quicksilver = createHeroButton("quicksilver.png");
		Button quicksilver2 = createHeroButton("quicksilver.png");

		Button venom = createHeroButton("venom.png");
		Button venom2 = createHeroButton("venom.png");

		Button strange = createHeroButton("strange.png");
		Button strange2 = createHeroButton("strange.png");

		Button hela = createHeroButton("hela.png");
		Button hela2 = createHeroButton("hela.png");

		Button ironman = createHeroButton("ironman.png");
		Button ironman2 = createHeroButton("ironman.png");

		Button spiderman = createHeroButton("spiderman.png");
		Button spiderman2 = createHeroButton("spiderman.png");

		Button yellowjacket = createHeroButton("yellowjacket.png");
		Button yellowjacket2 = createHeroButton("yellowjacket.png");
		// ----------------------- fill Player 1 choice screen
		// ----------------------------

		thor.setOnAction(e -> disableAndGo(thor2, thor, "Thor", p1));
		thor.setOnMouseEntered(e -> displayInfo("Thor"));
		thor.setOnMouseExited(e -> undisplayInfo());

		cap.setOnAction(e -> disableAndGo(cap2, cap, "Captain America", p1));
		cap.setOnMouseEntered(e -> displayInfo("Captain America"));
		cap.setOnMouseExited(e -> undisplayInfo());

		electro.setOnAction(e -> disableAndGo(electro2, electro, "Electro", p1));
		electro.setOnMouseEntered(e -> displayInfo("Electro"));
		electro.setOnMouseExited(e -> undisplayInfo());

		loki.setOnAction(e -> disableAndGo(loki, loki2, "Loki", p1));
		loki.setOnMouseEntered(e -> displayInfo("Loki"));
		loki.setOnMouseExited(e -> undisplayInfo());

		hulk.setOnAction(e -> disableAndGo(hulk, hulk2, "Hulk", p1));
		hulk.setOnMouseEntered(e -> displayInfo("Hulk"));
		hulk.setOnMouseExited(e -> undisplayInfo());

		Champions11 = new HBox(20);
		Champions11.getChildren().addAll(cap, electro, hulk, loki, thor);
		Champions11.setAlignment(Pos.CENTER);

		deadpool.setOnAction(e -> disableAndGo(deadpool, deadpool2, "Deadpool",
				p1));
		deadpool.setOnMouseEntered(e -> displayInfo("Deadpool"));
		deadpool.setOnMouseExited(e -> undisplayInfo());

		ghostrider.setOnAction(e -> disableAndGo(ghostrider, ghostrider2,
				"Ghost Rider", p1));
		ghostrider.setOnMouseEntered(e -> displayInfo("Ghost Rider"));
		ghostrider.setOnMouseExited(e -> undisplayInfo());

		iceman.setOnAction(e -> disableAndGo(iceman, iceman2, "Iceman", p1));
		iceman.setOnMouseEntered(e -> displayInfo("Iceman"));
		iceman.setOnMouseExited(e -> undisplayInfo());

		quicksilver.setOnAction(e -> disableAndGo(quicksilver, quicksilver2,
				"Quicksilver", p1));
		quicksilver.setOnMouseEntered(e -> displayInfo("Quicksilver"));
		quicksilver.setOnMouseExited(e -> undisplayInfo());

		venom.setOnAction(e -> disableAndGo(venom, venom2, "Venom", p1));
		venom.setOnMouseEntered(e -> displayInfo("Venom"));
		venom.setOnMouseExited(e -> undisplayInfo());

		HBox Champions12 = new HBox(20);
		Champions12.getChildren().addAll(deadpool, ghostrider, iceman,
				quicksilver, venom);
		Champions12.setAlignment(Pos.CENTER);

		strange.setOnAction(e -> disableAndGo(strange, strange2, "Dr Strange",
				p1));
		strange.setOnMouseEntered(e -> displayInfo("Dr Strange"));
		strange.setOnMouseExited(e -> undisplayInfo());

		hela.setOnAction(e -> disableAndGo(hela, hela2, "Hela", p1));
		hela.setOnMouseEntered(e -> displayInfo("Hela"));
		hela.setOnMouseExited(e -> undisplayInfo());

		ironman.setOnAction(e -> disableAndGo(ironman, ironman2, "Ironman", p1));
		ironman.setOnMouseEntered(e -> displayInfo("Ironman"));
		ironman.setOnMouseExited(e -> undisplayInfo());

		spiderman.setOnAction(e -> disableAndGo(spiderman, spiderman2,
				"Spiderman", p1));
		spiderman.setOnMouseEntered(e -> displayInfo("Spiderman"));
		spiderman.setOnMouseExited(e -> undisplayInfo());

		yellowjacket.setOnAction(e -> disableAndGo(yellowjacket, yellowjacket2,
				"Yellow Jacket", p1));
		yellowjacket.setOnMouseEntered(e -> displayInfo("Yellow Jacket"));
		yellowjacket.setOnMouseExited(e -> undisplayInfo());

		HBox Champions13 = new HBox(20);
		Champions13.getChildren().addAll(strange, hela, ironman, spiderman,
				yellowjacket);
		Champions13.setAlignment(Pos.CENTER);

		VBox AllChampions = new VBox(20);
		AllChampions.getChildren()
				.addAll(Champions11, Champions12, Champions13);

		Label player1 = new Label("Player 1");
		// Label player1 = new Label(p1.getName());
		player1.setFont(av20);
		player1.setTextFill(Color.RED);

		Label Choose = new Label("Choose your fighters");
		Choose.setFont(av30);
		Choose.setTextFill(Color.WHITE);

		CL = new Label("Choose your Leader!");
		CL.setFont(av30);
		CL.setTextFill(Color.YELLOW);
		
		choose = new VBox(10);
		choose.getChildren().addAll(player1, Choose, CL, AllChampions);
		choose.setAlignment(Pos.CENTER);

		layout3 = new HBox(10);
		layout3.getChildren().add(choose);
		layout3.setAlignment(Pos.CENTER_LEFT);
		layout3.setBackground(new Background(myBI));
		
		layout3.setBackground(new Background(myBI));

		scene3 = new Scene(layout3, 1200, 591);
		
		// layout3.setAlignment(Pos.CENTER);

		// ----------------------------------------------------------------------------------------
		// ----------------------- fill Player 2 choice screen
		// ----------------------------

		thor2.setOnAction(e -> disableAndGo2(thor, thor2, "Thor", p2));
		thor2.setOnMouseEntered(e -> displayInfo2("Thor"));
		thor2.setOnMouseExited(e -> undisplayInfo2());

		cap2.setOnAction(e -> disableAndGo2(cap, cap2, "Captain America", p2));
		cap2.setOnMouseEntered(e -> displayInfo2("Captain America"));
		cap2.setOnMouseExited(e -> undisplayInfo2());

		electro2.setOnAction(e -> disableAndGo2(electro, electro2, "Electro",
				p2));
		electro2.setOnMouseEntered(e -> displayInfo2("Electro"));
		electro2.setOnMouseExited(e -> undisplayInfo2());

		loki2.setOnAction(e -> disableAndGo2(loki, loki2, "Loki", p2));
		loki2.setOnMouseEntered(e -> displayInfo2("Loki"));
		loki2.setOnMouseExited(e -> undisplayInfo2());

		hulk2.setOnAction(e -> disableAndGo2(hulk, hulk2, "Hulk", p2));
		hulk2.setOnMouseEntered(e -> displayInfo2("Hulk"));
		hulk2.setOnMouseExited(e -> undisplayInfo2());

		HBox Champions21 = new HBox(20);
		Champions21.getChildren().addAll(cap2, electro2, hulk2, loki2, thor2);
		Champions21.setAlignment(Pos.CENTER);

		deadpool2.setOnAction(e -> disableAndGo2(deadpool, deadpool2,
				"Deadpool", p2));
		deadpool2.setOnMouseEntered(e -> displayInfo2("Deadpool"));
		deadpool2.setOnMouseExited(e -> undisplayInfo2());

		ghostrider2.setOnAction(e -> disableAndGo2(ghostrider, ghostrider2,
				"Ghost Rider", p2));
		ghostrider2.setOnMouseEntered(e -> displayInfo2("Ghost Rider"));
		ghostrider2.setOnMouseExited(e -> undisplayInfo2());

		iceman2.setOnAction(e -> disableAndGo2(iceman, iceman2, "Iceman", p2));
		iceman2.setOnMouseEntered(e -> displayInfo2("Iceman"));
		iceman2.setOnMouseExited(e -> undisplayInfo2());

		quicksilver2.setOnAction(e -> disableAndGo2(quicksilver, quicksilver2,
				"Quicksilver", p2));
		quicksilver2.setOnMouseEntered(e -> displayInfo2("Quicksilver"));
		quicksilver2.setOnMouseExited(e -> undisplayInfo2());

		venom2.setOnAction(e -> disableAndGo2(venom, venom2, "Venom", p2));
		venom2.setOnMouseEntered(e -> displayInfo2("Venom"));
		venom2.setOnMouseExited(e -> undisplayInfo2());

		HBox Champions22 = new HBox(20);
		Champions22.getChildren().addAll(deadpool2, ghostrider2, iceman2,
				quicksilver2, venom2);
		Champions22.setAlignment(Pos.CENTER);

		strange2.setOnAction(e -> disableAndGo2(strange, strange2,
				"Dr Strange", p2));
		strange2.setOnMouseEntered(e -> displayInfo2("Dr Strange"));
		strange2.setOnMouseExited(e -> undisplayInfo2());

		hela2.setOnAction(e -> disableAndGo2(hela, hela2, "Hela", p2));
		hela2.setOnMouseEntered(e -> displayInfo2("Hela"));
		hela2.setOnMouseExited(e -> undisplayInfo2());

		ironman2.setOnAction(e -> disableAndGo2(ironman, ironman2, "Ironman",
				p2));
		ironman2.setOnMouseEntered(e -> displayInfo2("Ironman"));
		ironman2.setOnMouseExited(e -> undisplayInfo2());

		spiderman2.setOnAction(e -> disableAndGo2(spiderman, spiderman2,
				"Spiderman", p2));
		spiderman2.setOnMouseEntered(e -> displayInfo2("Spiderman"));
		spiderman2.setOnMouseExited(e -> undisplayInfo2());

		yellowjacket2.setOnAction(e -> disableAndGo2(yellowjacket,
				yellowjacket2, "Yellow Jacket", p2));
		yellowjacket2.setOnMouseEntered(e -> displayInfo2("Yellow Jacket"));
		yellowjacket2.setOnMouseExited(e -> undisplayInfo2());

		HBox Champions23 = new HBox(20);
		Champions23.getChildren().addAll(strange2, hela2, ironman2, spiderman2,
				yellowjacket2);
		Champions23.setAlignment(Pos.CENTER);

		VBox AllChampions2 = new VBox(20);
		AllChampions2.getChildren().addAll(Champions21, Champions22,
				Champions23);

		Label player2 = new Label("Player 2");
		player2.setFont(av20);
		player2.setTextFill(Color.LIGHTBLUE);

		Label Choose2 = new Label("Choose your fighters");
		Choose2.setFont(av30);
		Choose2.setTextFill(Color.WHITE);

		CL2 = new Label("Choose your leader!");
		CL2.setFont(av30);
		CL2.setTextFill(Color.YELLOW);

		/*
		 * layout4 = new VBox(100); layout4.getChildren().addAll(player2,
		 * Choose2,CL2, AllChampions2); layout4.setAlignment(Pos.CENTER);
		 * 
		 * scene4 = new Scene(layout4, 1024, 1024);
		 */

		choose1 = new VBox(10);
		choose1.getChildren().addAll(player2, Choose2, CL2, AllChampions2);
		choose1.setAlignment(Pos.CENTER);

		layout32 = new HBox(10);
		layout32.getChildren().add(choose1);
		layout32.setAlignment(Pos.CENTER_LEFT);
		//layout32.setStyle("-fx-background-color: #000000;");
		layout32.setBackground(new Background(myBI));

		scene4 = new Scene(layout32, 1200, 591);
		
		Layout5 = new VBox(50);
		
		layout5playernames = new HBox(200);
		
		Layout5.getChildren().add(layout5playernames);
		
		scene5 = new Scene(Layout5,1400,1000);
		
		layout51 = new BorderPane();
		layout51.setBackground(new Background(gamebg));
	//	layout51.setStyle("background.css");
		
		
		window.setScene(scene0);
		window.setTitle("Marvel: Ultimate War");
		window.show();
	}

	

	
	public void music() {
		
		String s = "C:\\Users\\omrhs\\Documents\\College\\Semester 4\\Game\\M3\\final\\src\\avengers.wav";
		Media h = new Media(Paths.get(s).toUri().toString());
		mp = new MediaPlayer(h);
		mp.setOnEndOfMedia(new Runnable() {
		       public void run() {
		         mp.seek(Duration.ZERO);
		       }
		   });
		mp.play();
	}
	
	
	public void disableAndGo(Button b, Button b1, String name, Player p) {
		if (g.getTurnOrder().size() < 6) {
			b.setDisable(true);
			b1.setDisable(true);
			champions.add(b.getId());
			window.setScene(scene4);

			for (Champion c : Game.getAvailableChampions()) {
				if (c.getName().equals(name)) {
					if (g.getTurnOrder().size() == 0)
						p1.setLeader(c);

					p1.getTeam().add(c);
					g.getTurnOrder().insert(c);

				}
			}

			if (g.getTurnOrder().size() >= 1)
				choose.getChildren().remove(CL);
		} else {
			while (!(g.getTurnOrder()).isEmpty())
				System.out.println(((Champion) (g.getTurnOrder().remove()))
						.getName());
		}

		// System.out.println();

		// System.out.println(((Champion) p1.getLeader()).getName());
	}

	public void disableAndGo2(Button b, Button b1, String name, Player p) {
		if(g.getTurnOrder().size() == 5){
			for (Champion c : Game.getAvailableChampions()) {

				if (c.getName().equals(name)) {
					p2.getTeam().add(c);
					g.getTurnOrder().insert(c);
				}

			}
//			while (!(g.getTurnOrder()).isEmpty())
//				System.out.println(((Champion) (g.getTurnOrder().remove()))
//						.getName());
			goToScene5();
			return;
		}
		if (g.getTurnOrder().size() < 6) {
			b.setDisable(true);
			b1.setDisable(true);
			champions.add(b.getId());
			window.setScene(scene3);
			for (Champion c : Game.getAvailableChampions()) {

				if (c.getName().equals(name)) {
					if (g.getTurnOrder().size() == 1)
						p2.setLeader(c);

					p2.getTeam().add(c);
					g.getTurnOrder().insert(c);
				}

			}

			if (g.getTurnOrder().size() >= 2)
				choose1.getChildren().remove(CL2);

			// System.out.println();
			// System.out.println(((Champion) p2.getLeader()).getName());
		} /*else {
			goToScene5();
		}*/
		
		
		
		
		
		
		
	}

	public void displayInfo(String s) {
		for (Champion c : Game.getAvailableChampions()) {
			if (c.getName().equals(s)) {
				Label name = new Label("Name: " + c.getName());
				name.setFont(av30);
				name.setTextFill(Color.WHITE);
				Label type = new Label();
				type.setFont(av30);
				type.setTextFill(Color.WHITE);
				if (c instanceof Hero)
					type.setText("Type: Hero");
				if (c instanceof Villain)
					type.setText("Type: Villain");
				if (c instanceof AntiHero)
					type.setText("Type: AntiHero");
				Label maxHP = new Label("Max HP: "
						+ Integer.toString(c.getMaxHP()));
				maxHP.setTextFill(Color.WHITE);
				maxHP.setFont(av30);
				Label Mana = new Label("Mana: " + Integer.toString(c.getMana()));
				Mana.setFont(av30);
				Mana.setTextFill(Color.WHITE);
				Label attackRange = new Label("Attack Range: "
						+ Integer.toString(c.getAttackRange()));
				attackRange.setTextFill(Color.WHITE);
				attackRange.setFont(av30);
				Label attackDamage = new Label("Attack Damage: "
						+ Integer.toString(c.getAttackDamage()));
				attackDamage.setTextFill(Color.WHITE);
				attackDamage.setFont(av30);
				Label speed = new Label("Speed: "
						+ Integer.toString(c.getSpeed()));
				speed.setTextFill(Color.WHITE);
				speed.setFont(av30);
				Label abilities = new Label("Champion Abilities:- ");
				abilities.setTextFill(Color.WHITE);
				abilities.setFont(av30);
				VBox info = new VBox(1);
				info.getChildren().addAll(name, type, maxHP, Mana, attackRange,
						attackDamage, speed, abilities);
				
				for (int i = 0; i < c.getAbilities().size(); i++) {
					Ability a = c.getAbilities().get(i);
					Label ability = new Label("Ability " + (i + 1) + ": "
							+ a.getName());
					ability.setTextFill(Color.WHITE);
					ability.setFont(av30);
					info.getChildren().add(ability);
					
				}
			
				info.setStyle("-fx-background-color: rgba(110, 154, 199, 0.39); -fx-background-radius: 10");
				
				layout3.getChildren().add(info);
				return; 
			}
		}
	}

	public void undisplayInfo() {

		layout3.getChildren().remove(1);
		VBox one = (VBox) layout3.getChildren().get(0);

		one.setAlignment(Pos.CENTER);

	}

	public void displayInfo2(String s) {
		for (Champion c : Game.getAvailableChampions()) {
			if (c.getName().equals(s)) {
				Label name = new Label("Name: " + c.getName());
				name.setFont(av30);
				name.setTextFill(Color.WHITE);
				Label type = new Label();
				type.setFont(av30);
				type.setTextFill(Color.WHITE);
				if (c instanceof Hero)
					type.setText("Type: Hero");
				if (c instanceof Villain)
					type.setText("Type: Villain");
				if (c instanceof AntiHero)
					type.setText("Type: AntiHero");
				Label maxHP = new Label("Max HP: "
						+ Integer.toString(c.getMaxHP()));
				maxHP.setTextFill(Color.WHITE);
				maxHP.setFont(av30);
				Label Mana = new Label("Mana: " + Integer.toString(c.getMana()));
				Mana.setFont(av30);
				Mana.setTextFill(Color.WHITE);
				Label attackRange = new Label("Attack Range: "
						+ Integer.toString(c.getAttackRange()));
				attackRange.setTextFill(Color.WHITE);
				attackRange.setFont(av30);
				Label attackDamage = new Label("Attack Damage: "
						+ Integer.toString(c.getAttackDamage()));
				attackDamage.setTextFill(Color.WHITE);
				attackDamage.setFont(av30);
				Label speed = new Label("Speed: "
						+ Integer.toString(c.getSpeed()));
				speed.setTextFill(Color.WHITE);
				speed.setFont(av30);
				Label abilities = new Label("Champion Abilities:- ");
				abilities.setTextFill(Color.WHITE);
				abilities.setFont(av30);
				VBox info = new VBox(0);
				info.getChildren().addAll(name, type, maxHP, Mana, attackRange,
						attackDamage, speed, abilities);
				
				for (int i = 0; i < c.getAbilities().size(); i++) {
					Ability a = c.getAbilities().get(i);
					Label ability = new Label("Ability " + (i + 1) + ": "
							+ a.getName());
					ability.setTextFill(Color.WHITE);
					ability.setFont(av30);
					info.getChildren().add(ability);
					
				}
			
				info.setStyle("-fx-background-color: rgba(110, 154, 199, 0.39); -fx-background-radius: 10");
				
				layout32.getChildren().add(info);
				return; 
			}
		}
	}

	public void undisplayInfo2() {

		layout32.getChildren().remove(1);
		VBox one = (VBox) layout32.getChildren().get(0);

		one.setAlignment(Pos.CENTER);

	}

	public void goToScene2() {
		// p1name = name1.getText();
		// System.out.println(p1name);
		window.setScene(scene2);
		//scene2.getStylesheets().addAll(this.getClass().getResource("background.css").toExternalForm());
		
		String p1name = name1.getText();
		p1 = new Player(p1name);
		// System.out.print(p1.getName());
		player1name = new Label((String) p1name);
		layout5playernames.getChildren().add(player1name);
		player1name.setAlignment(Pos.TOP_LEFT);
		
	}

	public void goToScene3() throws IOException {
		// p2name = name2.getText();

		window.setScene(scene3);
		String p2name = name2.getText();
		p2 = new Player(p2name);
		g = new Game(p1, p2);
		player2name = new Label((String) p2name);
		layout5playernames.getChildren().add(player2name);
		player2name.setAlignment(Pos.TOP_RIGHT);
		// System.out.print(g.getAvailableChampions().toString());

	}

	public void goToScene5() {
		game();
		//window.setScene(scene5);
	}

	public void goToScene6(Champion c, Player p1) {
		p1.setLeader(c);
		window.setScene(scene6);
	}

	public void lay4fillheros() {

	}

	public void goToScene1() {
		// p2name = name2.getText();
		// System.out.println(p2name);
		window.setScene(scene1);
	}

	public void addToTeam(Button hulk) {

		champions.add("HULK");
		System.out.println(champions.toString());

	}

	public Button createHeroButton(String path) {
		Image image = new Image(path);
		final ImageView imageView = new ImageView();
		imageView.setImage(image);
		imageView.setFitHeight(100);
		imageView.setFitWidth(100);
		Button buttonx = new Button();
		buttonx.setGraphic(imageView);
		buttonx.setStyle("-fx-border-color: yellow;  -fx-background-color: transparent;");
//		buttonx.setMinWidth(100);
//		buttonx.setMinHeight(100);

		return buttonx;
	}

	public void game(){
//		
		
		
		
		
		
		
		
		//VBox first2 = new VBox();
		
		 first = new VBox(2);
		
		Label fpname = new Label(p1.getName().toUpperCase());
		fpname.setFont(av30);
		fpname.setTextFill(Color.rgb(128, 255, 00));
		
		
		
		
		
		//fpname.setb
	//	fpname.setStyle("-fx-text-fill: #ff0000; -fx-font-weight: bold; -fx-font-family: Congenial");
		
		first.getChildren().add(fpname);
		//first.setStyle(" -fx-background-color: transparent;");
		
		TBP1 = new TabPane();
		//first2.getChildren().add(fpname);
		//TBP1.setTabMinWidth(150);
		//TBP1.setTabMinHeight(33);
		TBP1.setTabMaxWidth(50);
		TBP1.getStylesheets().add(Main.class.getResource("tabpane.css").toExternalForm());
		
		for(int i = 0; i<3; i++) {
			
			Tab tab = new Tab(""+p1.getTeam().get(i).getName().toUpperCase());
			Champion c = p1.getTeam().get(i);
			tab.setClosable(false);
			VBox ChampDetails = new VBox();
			
			HBox Hname = new HBox(2);
			
			Label name = new Label("NAME: ");
			name.setFont(Font.font("Impact", 15));
			name.setTextFill(Color.YELLOW);
			Label cname = new Label(c.getName().toUpperCase());
			cname.setFont(Font.font("Impact", 15));
			cname.setTextFill(Color.WHITE);
			Hname.getChildren().addAll(name,cname);
			
			
			
			HBox Htype = new HBox(2);
			
			Label ctype = new Label("TYPE: ");
			ctype.setFont(Font.font("Impact", 15));
			ctype.setTextFill(Color.YELLOW);
			
			Label type = new Label();
			type.setFont(Font.font("Impact", 15));
			type.setTextFill(Color.WHITE);
			
			if(c instanceof Hero)
				type.setText("HERO");
			if(c instanceof AntiHero)
				type.setText("ANTI-HERO");
			if(c instanceof Villain)
				type.setText("VILLAIN");
			
			Htype.getChildren().addAll(ctype,type);
			
			
			HBox Hhp = new HBox(2);
			
			Label hp = new Label("HEALTH POINTS: ");
			hp.setFont(Font.font("Impact", 15));
			hp.setTextFill(Color.YELLOW);
			
			Label chp = new Label(""+c.getCurrentHP());
			chp.setFont(Font.font("Impact", 15));
			chp.setTextFill(Color.WHITE);
			
			Hhp.getChildren().addAll(hp,chp);

			
			HBox Hmana = new HBox(2);
			
			Label mana = new Label("MANA: ");
			mana.setFont(Font.font("Impact", 15));
			mana.setTextFill(Color.YELLOW);
			
			Label cmana = new Label("" + c.getMana());
			cmana.setFont(Font.font("Impact", 15));
			cmana.setTextFill(Color.WHITE);
			
			Hmana.getChildren().addAll(mana,cmana);
			
			
			HBox Hactionpts = new HBox(2);
			
			Label actionpts = new Label("ACTION POINTS: ");
			actionpts.setFont(Font.font("Impact", 15));
			actionpts.setTextFill(Color.YELLOW);
			
			Label cactionpts = new Label("" + c.getCurrentActionPoints());
			cactionpts.setFont(Font.font("Impact", 15));
			cactionpts.setTextFill(Color.WHITE);
			
			Hactionpts.getChildren().addAll(actionpts,cactionpts);
			
			
			Label section2 = new Label("CHAMPION ABILITIES:- ");
			section2.setFont(Font.font("Impact", 15));
			section2.setTextFill(Color.YELLOW);
			
			ChampDetails.getChildren().addAll(Hname,Htype,Hhp,Hmana,Hactionpts,section2);
			
			TabPane ChampAb = new TabPane();
			ChampAb.setMaxWidth(250);
			ChampDetails.getChildren().add(ChampAb);
			tab.setContent(ChampDetails);
			TBP1.getTabs().add(tab);
			
			for(int y = 0; y<3; y++) {
				
				
				Ability a = c.getAbilities().get(y);
				
				Tab AbTab = new Tab(""+a.getName().toUpperCase());
				AbTab.setClosable(false);
				
				VBox AbDetails = new VBox();
				
				HBox Haname = new HBox(2);
				
				Label aname = new Label("NAME: ");
				aname.setFont(Font.font("Impact", 15));
				aname.setTextFill(Color.YELLOW);
				Label caname = new Label(""+ a.getName().toUpperCase());
				caname.setFont(Font.font("Impact", 15));
				caname.setTextFill(Color.YELLOW);
				
				Haname.getChildren().addAll(aname,caname);
				
				
				
				HBox Hatype = new HBox(2);
				
				Label catype = new Label("TYPE: ");
				catype.setFont(new Font("Impact",15));
				catype.setTextFill(Color.YELLOW);
				
				Label atype = new Label();
				atype.setFont(new Font("Impact",15));
				atype.setTextFill(Color.WHITE);
			
				Hatype.getChildren().addAll(catype,atype);
				
				
				HBox Hcustom = new HBox(2);
				
				Label acustom = new Label("DAMAGE AMOUNT: ");
				acustom.setFont(Font.font("Impact", 15));
				acustom.setTextFill(Color.YELLOW);
				
				Label custom = new Label();
				custom.setFont(Font.font("Impact", 15));
				custom.setTextFill(Color.WHITE);
			
				if(a instanceof DamagingAbility){
					atype.setText("DAMAGING");
					
					custom.setText(""+((DamagingAbility)a).getDamageAmount());
				}
				if(a instanceof HealingAbility){
					atype.setText("HEALING");
					custom.setText(""+((HealingAbility)a).getHealAmount());
				}
				if(a instanceof CrowdControlAbility){
					atype.setText("CROWD CONTROL");
					custom.setText(""+((CrowdControlAbility)a).getEffect().getName());
				}
				
				Hcustom.getChildren().addAll(acustom,custom);
				
				
				HBox Haof = new HBox(2);
				
				Label aof = new Label("AREA OF EFFECT: ");
				aof.setFont(Font.font("Impact", 15));
				aof.setTextFill(Color.YELLOW);
				Label caof = new Label("" + a.getCastArea());
				caof.setFont(Font.font("Impact", 15));
				caof.setTextFill(Color.WHITE);
				
				Haof.getChildren().addAll(aof,caof);
				
				
				
				HBox Harange = new HBox(2);
				
				Label carange = new Label("CAST RANGE: ");
				carange.setFont(Font.font("Impact", 15));
				carange.setTextFill(Color.YELLOW);
				Label arange = new Label("" + a.getCastRange());
				arange.setFont(Font.font("Impact", 15));
				arange.setTextFill(Color.WHITE);
				
				Harange.getChildren().addAll(carange,arange);
				
				
				HBox Hamana = new HBox(2);
				
				Label camana = new Label("MANA: ");
				camana.setFont(Font.font("Impact", 15));
				camana.setTextFill(Color.YELLOW);
				Label amana = new Label("" + a.getManaCost());
				amana.setFont(Font.font("Impact", 15));
				amana.setTextFill(Color.WHITE);
				
				Hamana.getChildren().addAll(camana,amana);
				
				
				
				HBox Haactionpts = new HBox(2);
				
				Label caactionpts = new Label("ACTION POINTS COST: ");
				caactionpts.setFont(Font.font("Impact", 15));
				caactionpts.setTextFill(Color.YELLOW);
				Label aactionpts = new Label("" + a.getRequiredActionPoints());
				aactionpts.setFont(Font.font("Impact", 15));
				aactionpts.setTextFill(Color.WHITE);
				
				Haactionpts.getChildren().addAll(caactionpts,aactionpts);
				
				
				HBox HacurrCD = new HBox(2);
				
				Label cacurrCD = new Label("CURRENT COOLDOWN: ");
				cacurrCD.setFont(Font.font("Impact", 15));
				cacurrCD.setTextFill(Color.YELLOW);
				Label acurrCD = new Label("" + a.getCurrentCooldown());
				acurrCD.setFont(Font.font("Impact", 15));
				acurrCD.setTextFill(Color.WHITE);
				
				HacurrCD.getChildren().addAll(cacurrCD,acurrCD);
				
			
				
				HBox HabaseCD = new HBox(2);
				
				Label abaseCD = new Label("BASE COOLDOWN: ");
				abaseCD.setFont(Font.font("Impact", 15));
				abaseCD.setTextFill(Color.YELLOW);
				Label cabaseCD = new Label("" + a.getBaseCooldown());
				cabaseCD.setFont(Font.font("Impact", 15));
				cabaseCD.setTextFill(Color.WHITE);
				
				HabaseCD.getChildren().addAll(abaseCD,cabaseCD);
				
				
				HBox Hattackdmg = new HBox(2);
				
				Label attackdmg = new Label("ATTACK DAMAGE: ");
				attackdmg.setFont(Font.font("Impact", 15));
				attackdmg.setTextFill(Color.YELLOW);
				Label cattackdmg = new Label("" + c.getAttackDamage());
				cattackdmg.setFont(Font.font("Impact", 15));
				cattackdmg.setTextFill(Color.WHITE);
				
				Hattackdmg.getChildren().addAll(attackdmg,cattackdmg);
			
			
				HBox Hattackrange = new HBox(2);
				
				Label attackrange = new Label("ATTACK RANGE: ");
				attackrange.setFont(Font.font("Impact", 15));
				attackrange.setTextFill(Color.YELLOW);
				Label cattackrange = new Label("" + c.getAttackDamage());
				cattackrange.setFont(Font.font("Impact", 15));
				cattackrange.setTextFill(Color.WHITE);
				
				Hattackrange.getChildren().addAll(attackrange,cattackrange);
				
				
				
				
				
				AbDetails.getChildren().addAll(Haname,Hatype,Haof,Harange,Hamana,Haactionpts,HacurrCD,HabaseCD,Hcustom,Hattackdmg,Hattackrange);
				AbTab.setContent(AbDetails);
				ChampAb.getTabs().add(AbTab);
				
			}
			
				
				
			}
			
		
		
		
	
		first.getChildren().add(TBP1);
		
		
		
		

		 second = new VBox(2);
		
		Label spname2 = new Label(p2.getName().toUpperCase());
		spname2.setFont(av30);
		spname2.setTextFill(Color.RED);
		
		
		
		second.getChildren().add(spname2);
		
		TBP2 = new TabPane();
		TBP2.getStylesheets().add(Main.class.getResource("tabpane.css").toExternalForm());
		AppliedEffectsp1 = new VBox(2);
		
		//first2.getChildren().add(fpname);
		//TBP2.setTabMinWidth(150);
		//TBP1.setTabMinHeight(33);
		
		for(int i = 0; i<3; i++) {
			
			Tab tab = new Tab(""+p2.getTeam().get(i).getName().toUpperCase());
			Champion c = p2.getTeam().get(i);
			tab.setClosable(false);
			VBox ChampDetails = new VBox();
			
			HBox Hname = new HBox(2);
			
			Label name = new Label("NAME: ");
			name.setFont(Font.font("Impact", 15));
			name.setTextFill(Color.YELLOW);
			Label cname = new Label(c.getName().toUpperCase());
			cname.setFont(Font.font("Impact", 15));
			cname.setTextFill(Color.WHITE);
			Hname.getChildren().addAll(name,cname);
			
			
			
			HBox Htype = new HBox(2);
			
			Label ctype = new Label("TYPE: ");
			ctype.setFont(Font.font("Impact", 15));
			ctype.setTextFill(Color.YELLOW);
			
			Label type = new Label();
			type.setFont(Font.font("Impact", 15));
			type.setTextFill(Color.WHITE);
			
			if(c instanceof Hero)
				type.setText("HERO");
			if(c instanceof AntiHero)
				type.setText("ANTI-HERO");
			if(c instanceof Villain)
				type.setText("VILLAIN");
			
			Htype.getChildren().addAll(ctype,type);
			
			
			HBox Hhp = new HBox(2);
			
			Label hp = new Label("HEALTH POINTS: ");
			hp.setFont(Font.font("Impact", 15));
			hp.setTextFill(Color.YELLOW);
			
			Label chp = new Label(""+c.getCurrentHP());
			chp.setFont(Font.font("Impact", 15));
			chp.setTextFill(Color.WHITE);
			
			Hhp.getChildren().addAll(hp,chp);

			
			HBox Hmana = new HBox(2);
			
			Label mana = new Label("MANA: ");
			mana.setFont(Font.font("Impact", 15));
			mana.setTextFill(Color.YELLOW);
			
			Label cmana = new Label("" + c.getMana());
			cmana.setFont(Font.font("Impact", 15));
			cmana.setTextFill(Color.WHITE);
			
			Hmana.getChildren().addAll(mana,cmana);
			
			
			HBox Hactionpts = new HBox(2);
			
			Label actionpts = new Label("ACTION POINTS: ");
			actionpts.setFont(Font.font("Impact", 15));
			actionpts.setTextFill(Color.YELLOW);
			
			Label cactionpts = new Label("" + c.getCurrentActionPoints());
			cactionpts.setFont(Font.font("Impact", 15));
			cactionpts.setTextFill(Color.WHITE);
			
			Hactionpts.getChildren().addAll(actionpts,cactionpts);
			
			
			
			
			
			Label section2 = new Label("CHAMPION ABILITIES:- ");
			section2.setFont(Font.font("Impact", 15));
			section2.setTextFill(Color.YELLOW);
			
			ChampDetails.getChildren().addAll(Hname,Htype,Hhp,Hmana,Hactionpts,section2);
			
			// -----------------------------------------------------------------------------------------------------------APPLIED EFFECTS
			
				
			TabPane ChampAb = new TabPane();
			ChampAb.setMaxWidth(250);
			ChampDetails.getChildren().add(ChampAb);
			tab.setContent(ChampDetails);
			TBP2.getTabs().add(tab);
			
			
		//	second.getChildren().add(second1);
			
			for(int y = 0; y<3; y++) {
				
				
				Ability a = c.getAbilities().get(y);
				
				Tab AbTab = new Tab(""+a.getName().toUpperCase());
				AbTab.setClosable(false);
				
				VBox AbDetails = new VBox();
				
				HBox Haname = new HBox(2);
				
				Label aname = new Label("NAME: ");
				aname.setFont(Font.font("Impact", 15));
				aname.setTextFill(Color.YELLOW);
				Label caname = new Label(""+ a.getName().toUpperCase());
				caname.setFont(Font.font("Impact", 15));
				caname.setTextFill(Color.YELLOW);
				
				Haname.getChildren().addAll(aname,caname);
				
				
				
				HBox Hatype = new HBox(2);
				
				Label catype = new Label("TYPE: ");
				catype.setFont(new Font("Impact",15));
				catype.setTextFill(Color.YELLOW);
				
				Label atype = new Label();
				atype.setFont(new Font("Impact",15));
				atype.setTextFill(Color.WHITE);
			
				Hatype.getChildren().addAll(catype,atype);
				
				
				HBox Hcustom = new HBox(2);
				
				Label acustom = new Label("DAMAGE AMOUNT: ");
				acustom.setFont(Font.font("Impact", 15));
				acustom.setTextFill(Color.YELLOW);
				
				Label custom = new Label();
				custom.setFont(Font.font("Impact", 15));
				custom.setTextFill(Color.WHITE);
			
				if(a instanceof DamagingAbility){
					atype.setText("DAMAGING");
					
					custom.setText(""+((DamagingAbility)a).getDamageAmount());
				}
				if(a instanceof HealingAbility){
					atype.setText("HEALING");
					custom.setText(""+((HealingAbility)a).getHealAmount());
				}
				if(a instanceof CrowdControlAbility){
					atype.setText("CROWD CONTROL");
					custom.setText(""+((CrowdControlAbility)a).getEffect().getName());
				}
				
				Hcustom.getChildren().addAll(acustom,custom);
				
				
				HBox Haof = new HBox(2);
				
				Label aof = new Label("AREA OF EFFECT: ");
				aof.setFont(Font.font("Impact", 15));
				aof.setTextFill(Color.YELLOW);
				Label caof = new Label("" + a.getCastArea());
				caof.setFont(Font.font("Impact", 15));
				caof.setTextFill(Color.WHITE);
				
				Haof.getChildren().addAll(aof,caof);
				
				
				
				HBox Harange = new HBox(2);
				
				Label carange = new Label("CAST RANGE: ");
				carange.setFont(Font.font("Impact", 15));
				carange.setTextFill(Color.YELLOW);
				Label arange = new Label("" + a.getCastRange());
				arange.setFont(Font.font("Impact", 15));
				arange.setTextFill(Color.WHITE);
				
				Harange.getChildren().addAll(carange,arange);
				
				
				HBox Hamana = new HBox(2);
				
				Label camana = new Label("MANA: ");
				camana.setFont(Font.font("Impact", 15));
				camana.setTextFill(Color.YELLOW);
				Label amana = new Label("" + a.getManaCost());
				amana.setFont(Font.font("Impact", 15));
				amana.setTextFill(Color.WHITE);
				
				Hamana.getChildren().addAll(camana,amana);
				
				
				
				HBox Haactionpts = new HBox(2);
				
				Label caactionpts = new Label("ACTION POINTS COST: ");
				caactionpts.setFont(Font.font("Impact", 15));
				caactionpts.setTextFill(Color.YELLOW);
				Label aactionpts = new Label("" + a.getRequiredActionPoints());
				aactionpts.setFont(Font.font("Impact", 15));
				aactionpts.setTextFill(Color.WHITE);
				
				Haactionpts.getChildren().addAll(caactionpts,aactionpts);
				
				
				HBox HacurrCD = new HBox(2);
				
				Label cacurrCD = new Label("CURRENT COOLDOWN: ");
				cacurrCD.setFont(Font.font("Impact", 15));
				cacurrCD.setTextFill(Color.YELLOW);
				Label acurrCD = new Label("" + a.getCurrentCooldown());
				acurrCD.setFont(Font.font("Impact", 15));
				acurrCD.setTextFill(Color.WHITE);
				
				HacurrCD.getChildren().addAll(cacurrCD,acurrCD);
				
			
				
				HBox HabaseCD = new HBox(2);
				
				Label abaseCD = new Label("BASE COOLDOWN: ");
				abaseCD.setFont(Font.font("Impact", 15));
				abaseCD.setTextFill(Color.YELLOW);
				Label cabaseCD = new Label("" + a.getBaseCooldown());
				cabaseCD.setFont(Font.font("Impact", 15));
				cabaseCD.setTextFill(Color.WHITE);
				
				HabaseCD.getChildren().addAll(abaseCD,cabaseCD);
				
				
				HBox Hattackdmg = new HBox(2);
				
				Label attackdmg = new Label("ATTACK DAMAGE: ");
				attackdmg.setFont(Font.font("Impact", 15));
				attackdmg.setTextFill(Color.YELLOW);
				Label cattackdmg = new Label("" + c.getAttackDamage());
				cattackdmg.setFont(Font.font("Impact", 15));
				cattackdmg.setTextFill(Color.WHITE);
				
				Hattackdmg.getChildren().addAll(attackdmg,cattackdmg);
			
			
				HBox Hattackrange = new HBox(2);
				
				Label attackrange = new Label("ATTACK RANGE: ");
				attackrange.setFont(Font.font("Impact", 15));
				attackrange.setTextFill(Color.YELLOW);
				Label cattackrange = new Label("" + c.getAttackDamage());
				cattackrange.setFont(Font.font("Impact", 15));
				cattackrange.setTextFill(Color.WHITE);
				
				Hattackrange.getChildren().addAll(attackrange,cattackrange);
				
				
				
				
				
				AbDetails.getChildren().addAll(Haname,Hatype,Haof,Harange,Hamana,Haactionpts,HacurrCD,HabaseCD,Hcustom,Hattackdmg,Hattackrange);
				AbTab.setContent(AbDetails);
				ChampAb.getTabs().add(AbTab);
				
			}
			
				
				
			}
		
		
		second.getChildren().addAll(TBP2); 
		
		
		
		
		//--------------------------------
		HBox turn = new HBox(10);
		
	//	turn.setStyle("-fx-border-color: #ffff00; -fx-border-width: 1px 1px 1px 1px;");
		Label upNxt = new Label("Up next: ");
		
		upNxt.setFont(av30);
		upNxt.setTextFill(Color.YELLOW);
		
		turn.getChildren().add(upNxt);
		ArrayList<Object> temp = new ArrayList<Object>();
		while(!g.getTurnOrder().isEmpty()){
			Label currChamp = new Label(((Champion)g.getTurnOrder().peekMin()).getName());
			currChamp.setFont(av30);
			currChamp.setTextFill(Color.WHITE);
			temp.add(g.getTurnOrder().remove());
			turn.getChildren().add(currChamp);
		}
		for(int i = 0; i < temp.size(); i++)
			g.getTurnOrder().insert((Comparable) temp.remove(i));

		g.placeChampions();
		//-------------------------------------------------------------------------------------BOARD PICS
		
		Image caps = new Image("C:\\Users\\omrhs\\Documents\\College\\Semester 4\\Game\\M3\\final\\src\\board\\CaptainAmerica.png");
		final ImageView CaptainAmerica = new ImageView();
		CaptainAmerica.setImage(caps);
		CaptainAmerica.setFitHeight(100);
		CaptainAmerica.setFitWidth(100);
	
		Image ded = new Image("C:\\Users\\omrhs\\Documents\\College\\Semester 4\\Game\\M3\\final\\src\\board\\Deadpool.png");
		final ImageView Deadpool = new ImageView();
		Deadpool.setImage(ded);
		Deadpool.setFitHeight(100);
		Deadpool.setFitWidth(100);
		
		
		
		Image drst = new Image("C:\\Users\\omrhs\\Documents\\College\\Semester 4\\Game\\M3\\final\\src\\board\\DrStrange.png");
		final ImageView DrStrange = new ImageView();
		DrStrange.setImage(drst);
		DrStrange.setFitHeight(100);
		DrStrange.setFitWidth(100);
		
		Image elec = new Image("C:\\Users\\omrhs\\Documents\\College\\Semester 4\\Game\\M3\\final\\src\\board\\Electro.png");
		final ImageView Electro = new ImageView();
		Electro.setImage(elec);
		Electro.setFitHeight(100);
		Electro.setFitWidth(100);
		
		Image ghst = new Image("C:\\Users\\omrhs\\Documents\\College\\Semester 4\\Game\\M3\\final\\src\\board\\GhostRider.png");
		final ImageView GhostRider = new ImageView();
		GhostRider.setImage(ghst);
		GhostRider.setFitHeight(100);
		GhostRider.setFitWidth(100);
		
		Image helas = new Image("C:\\Users\\omrhs\\Documents\\College\\Semester 4\\Game\\M3\\final\\src\\board\\Hela.png");
		final ImageView Hela = new ImageView();
		Hela.setImage(helas);
		Hela.setFitHeight(100);
		Hela.setFitWidth(100);
		
		Image hulks = new Image("C:\\Users\\omrhs\\Documents\\College\\Semester 4\\Game\\M3\\final\\src\\board\\Hulk.png");
		final ImageView Hulk = new ImageView();
		Hulk.setImage(hulks);
		Hulk.setFitHeight(100);
		Hulk.setFitWidth(100);
		
		Image ice = new Image("C:\\Users\\omrhs\\Documents\\College\\Semester 4\\Game\\M3\\final\\src\\board\\Iceman.png");
		final ImageView Iceman = new ImageView();
		Iceman.setImage(ice);
		Iceman.setFitHeight(100);
		Iceman.setFitWidth(100);
		
		Image iron = new Image("C:\\Users\\omrhs\\Documents\\College\\Semester 4\\Game\\M3\\final\\src\\board\\Ironman.png");
		final ImageView Ironman = new ImageView();
		Ironman.setImage(iron);
		Ironman.setFitHeight(100);
		Ironman.setFitWidth(100);
		
		Image loksi = new Image("C:\\Users\\omrhs\\Documents\\College\\Semester 4\\Game\\M3\\final\\src\\board\\Loki.png");
		final ImageView Loki = new ImageView();
		Loki.setImage(loksi);
		Loki.setFitHeight(100);
		Loki.setFitWidth(100);
		
		Image quick = new Image("C:\\Users\\omrhs\\Documents\\College\\Semester 4\\Game\\M3\\final\\src\\board\\Quicksilver.png");
		final ImageView Quicksilver = new ImageView();
		Quicksilver.setImage(quick);
		Quicksilver.setFitHeight(100);
		Quicksilver.setFitWidth(100);
		
		Image spider = new Image("C:\\Users\\omrhs\\Documents\\College\\Semester 4\\Game\\M3\\final\\src\\board\\Spiderman.png");
		final ImageView Spiderman = new ImageView();
		Spiderman.setImage(spider);
		Spiderman.setFitHeight(100);
		Spiderman.setFitWidth(100);

		Image thors = new Image("C:\\Users\\omrhs\\Documents\\College\\Semester 4\\Game\\M3\\final\\src\\board\\Thor.png");
		final ImageView Thor = new ImageView();
		Thor.setImage(thors);
		Thor.setFitHeight(100);
		Thor.setFitWidth(100);
		
		Image venosm = new Image("C:\\Users\\omrhs\\Documents\\College\\Semester 4\\Game\\M3\\final\\src\\board\\Venom.png");
		final ImageView Venom = new ImageView();
		Venom.setImage(venosm);
		Venom.setFitHeight(100);
		Venom.setFitWidth(100);
		
		Image yel = new Image("C:\\Users\\omrhs\\Documents\\College\\Semester 4\\Game\\M3\\final\\src\\board\\YellowJacket.png");
		final ImageView YellowJacket = new ImageView();
		YellowJacket.setImage(yel);
		YellowJacket.setFitHeight(100);
		YellowJacket.setFitWidth(100);
		
		//-------------------------------------------------------------------------------------BOARD GRID --------------------------------------------------------==================================================
		GridPane grid = new GridPane();
		for(int i = 0; i < Game.getBoardwidth(); i++){
			for(int j = 0; j < Game.getBoardheight(); j++){
				Button b = new Button("Empty");
				b.setStyle(" -fx-border-width: 1px; -fx-background-color: rgb(153,153,255); -fx-text-fill: #ffffff");
				if(g.getBoard()[i][j] instanceof Cover) {
				
					Image cover = new Image("C:\\Users\\omrhs\\Documents\\College\\Semester 4\\Game\\M3\\final\\src\\board\\cover.png");
					final ImageView cov = new ImageView();
					cov.setImage(cover);
					cov.setFitHeight(100);
					cov.setFitWidth(100);
					
					b.setGraphic(cov);
					Tooltip info = new Tooltip("Cover" + "\n" + "Current HP: " + ((Cover)g.getBoard()[i][j]).getCurrentHP());
					b.setTooltip(info);
					
					//b.setText("Cover" + "\n" + "Current HP: " + ((Cover)g.getBoard()[i][j]).getCurrentHP());
				}
				if(g.getBoard()[i][j] instanceof Champion) {
					
					Tooltip info = new Tooltip(((Champion)g.getBoard()[i][j]).getName() + "\n" + "Current HP:" + ((Champion)g.getBoard()[i][j]).getCurrentHP() + "\n" + "Action Points: " + ((Champion)g.getBoard()[i][j]).getCurrentActionPoints() + "\n" + "Mana: " + ((Champion)g.getBoard()[i][j]).getMana()  ) ;
					
					b.setTooltip(info);
					
					
					String name = ((Champion)g.getBoard()[i][j]).getName();
					
					if(name.equals("Captain America")) {
						b.setGraphic(CaptainAmerica);
					} else if (name.equals("Deadpool")) {
						b.setGraphic(Deadpool);
					}else if(name.equals("Electro")) {
						b.setGraphic(Electro);
					}else if(name.equals("Ghost Rider")) {
						b.setGraphic(GhostRider);
					}else if(name.equals("Hela")) {
						b.setGraphic(Hela);
					}else if(name.equals("Hulk")) {
						b.setGraphic(Hulk);
					}else if(name.equals("Iceman")) {
						b.setGraphic(Iceman);
					}else if(name.equals("Ironman")) {
						b.setGraphic(Ironman);
					}else if(name.equals("Loki")) {
						b.setGraphic(Loki);
					}else if(name.equals("Quicksilver")) {
						b.setGraphic(Quicksilver);
					}else if(name.equals("Spiderman")) {
						b.setGraphic(Spiderman);
					}else if(name.equals("Thor")) {
						b.setGraphic(Thor);
					}else if(name.equals("Venom")) {
						b.setGraphic(Venom);
					}else if(name.equals("Dr Strange")) {
						b.setGraphic(DrStrange);
					}else {
						b.setGraphic(YellowJacket);
					}
					
					
				}
				//b.setShape(new Rectangle());
				//b.setAlignment(Pos.CENTER);
				b.setMinWidth(110);
				b.setMinHeight(120);
				b.setMaxWidth(110);
				b.setMaxHeight(120);
				Point p = new Point(i,j);
				b.setOnAction(e -> {
					if(flagST == true){
						applyCastAbilitySingleTarget(tempa, p, grid);
					}
					flagST = false;
					tempa = null;
				});
				grid.add(b, j, i);
				//System.out.println(grid.getRowIndex(b));
			}
		}
		//---------------------------------------------
		HBox actions = new HBox(5);
		Button move = new Button("Move");
		
		move.setFont(av20);
		move.setTextFill(Color.WHITE);
		move.setStyle(" -fx-background-color: transparent;");
		move.setOnMouseEntered(e-> move.setTextFill(Color.YELLOW));
		move.setOnMouseExited(e-> move.setTextFill(Color.WHITE));

		move.setOnAction(e -> {
			Button up = new Button("Down");
			Button down = new Button("Up");
			Button right = new Button("Right");
			Button left = new Button("Left");
			Button done = new Button("Done");
			actions.getChildren().addAll(down,up,right,left, done);
//			up.setOnAction(e1 -> applyMove("up"));
			up.setOnAction(e1 -> applyMove(Direction.UP, grid));
			down.setOnAction(e1 -> applyMove(Direction.DOWN, grid));
			right.setOnAction(e1 -> applyMove(Direction.RIGHT, grid));
			left.setOnAction(e1 -> applyMove(Direction.LEFT, grid));
			done.setOnAction(e1 -> {
				actions.getChildren().removeAll(up, down, right, left, done);
			});
		});
		Button attack = new Button("Attack");
		attack.setFont(av20);
		attack.setTextFill(Color.WHITE);
		attack.setStyle(" -fx-background-color: transparent;");
		attack.setOnMouseEntered(e-> attack.setTextFill(Color.YELLOW));
		attack.setOnMouseExited(e-> attack.setTextFill(Color.WHITE));

		attack.setOnAction(e -> {
			Button up = new Button("Down");
			Button down = new Button("Up");
			Button right = new Button("Right");
			Button left = new Button("Left");
			Button done = new Button("Done");
			actions.getChildren().addAll(down,up,right,left, done);
//			up.setOnAction(e1 -> applyMove("up"));
			up.setOnAction(e1 -> applyAttack(Direction.UP, grid));
			down.setOnAction(e1 -> applyAttack(Direction.DOWN, grid));
			right.setOnAction(e1 -> applyAttack(Direction.RIGHT, grid));
			left.setOnAction(e1 -> applyAttack(Direction.LEFT, grid));
			done.setOnAction(e1 -> {
				actions.getChildren().removeAll(up, down, right, left, done);
			});
		});
		Button cast = new Button("Cast an Ability");
		cast.setFont(av20);
		cast.setTextFill(Color.WHITE);
		cast.setStyle(" -fx-background-color: transparent;");
		cast.setOnMouseEntered(e-> cast.setTextFill(Color.YELLOW));
		cast.setOnMouseExited(e-> cast.setTextFill(Color.WHITE));
//		cast.setMinWidth(250);
//		cast.setMinHeight(200);
//		cast.setMaxWidth(250);
//		cast.setMaxHeight(200);
		cast.setOnAction(e -> {
			Button a1 = new Button(g.getCurrentChampion().getAbilities().get(0).getName());
			Button a2 = new Button(g.getCurrentChampion().getAbilities().get(1).getName());
			Button a3 = new Button(g.getCurrentChampion().getAbilities().get(2).getName());
			actions.getChildren().addAll(a1,a2,a3);
			a1.setOnAction(e1 -> {
				Ability chosena = g.getCurrentChampion().getAbilities().get(0);
				if(chosena.getCastArea() == AreaOfEffect.DIRECTIONAL){
					actions.getChildren().removeAll(a1,a2,a3);
					Button up = new Button("Down");
					Button down = new Button("Up");
					Button right = new Button("Right");
					Button left = new Button("Left");
					Button done = new Button("Done");

					actions.getChildren().addAll(down,up,right,left, done);
//					up.setOnAction(e1 -> applyMove("up"));
					up.setOnAction(e2 -> applyCastAbilityDirectional(chosena,Direction.UP, grid));
					down.setOnAction(e2 -> applyCastAbilityDirectional(chosena,Direction.DOWN, grid));
					right.setOnAction(e2 -> applyCastAbilityDirectional(chosena,Direction.RIGHT, grid));
					left.setOnAction(e2 -> applyCastAbilityDirectional(chosena,Direction.LEFT, grid));
					done.setOnAction(e2 -> {
						actions.getChildren().removeAll(up, down, right, left, done);
					});
				}
				else{
					if(chosena.getCastArea() == AreaOfEffect.SINGLETARGET){
						actions.getChildren().removeAll(a1,a2,a3);
						flagST = true;
						tempa = chosena;
					}
					else{
						actions.getChildren().removeAll(a1,a2,a3);
						applyCastAbility(chosena,grid);
					}
				}
			});
			
			a2.setOnAction(e1 -> {
				Ability chosena = g.getCurrentChampion().getAbilities().get(1);
				if(chosena.getCastArea() == AreaOfEffect.DIRECTIONAL){
					actions.getChildren().removeAll(a1,a2,a3);
					Button up = new Button("Down");
					Button down = new Button("Up");
					Button right = new Button("Right");
					Button left = new Button("Left");
					Button done = new Button("Done");

					actions.getChildren().addAll(down,up,right,left, done);
//					up.setOnAction(e1 -> applyMove("up"));
					up.setOnAction(e2 -> applyCastAbilityDirectional(chosena,Direction.UP, grid));
					down.setOnAction(e2 -> applyCastAbilityDirectional(chosena,Direction.DOWN, grid));
					right.setOnAction(e2 -> applyCastAbilityDirectional(chosena,Direction.RIGHT, grid));
					left.setOnAction(e2 -> applyCastAbilityDirectional(chosena,Direction.LEFT, grid));
					done.setOnAction(e2 -> {
						actions.getChildren().removeAll(up, down, right, left, done);
					});
				}
				else{
					if(chosena.getCastArea() == AreaOfEffect.SINGLETARGET){
						actions.getChildren().removeAll(a1,a2,a3);
						flagST = true;
						tempa = chosena;
					}
					else{
						actions.getChildren().removeAll(a1,a2,a3);
						applyCastAbility(chosena,grid);
					}
				}
			});
			
			a3.setOnAction(e1 -> {
				Ability chosena = g.getCurrentChampion().getAbilities().get(2);
				if(chosena.getCastArea() == AreaOfEffect.DIRECTIONAL){
					actions.getChildren().removeAll(a1,a2,a3);
					Button up = new Button("Down");
					Button down = new Button("Up");
					Button right = new Button("Right");
					Button left = new Button("Left");
					Button done = new Button("Done");

					actions.getChildren().addAll(down,up,right,left, done);
//					up.setOnAction(e1 -> applyMove("up"));
					up.setOnAction(e2 -> applyCastAbilityDirectional(chosena,Direction.UP, grid));
					down.setOnAction(e2 -> applyCastAbilityDirectional(chosena,Direction.DOWN, grid));
					right.setOnAction(e2 -> applyCastAbilityDirectional(chosena,Direction.RIGHT, grid));
					left.setOnAction(e2 -> applyCastAbilityDirectional(chosena,Direction.LEFT, grid));
					done.setOnAction(e2 -> {
						actions.getChildren().removeAll(up, down, right, left, done);
					});
				}
				else{
					if(chosena.getCastArea() == AreaOfEffect.SINGLETARGET){
						actions.getChildren().removeAll(a1,a2,a3);
						flagST = true;
						tempa = chosena;
					}
					else{
						actions.getChildren().removeAll(a1,a2,a3);
						applyCastAbility(chosena,grid);
					}
				}
			});
			
		});
		
		
		
		
		
		Button endTurn = new Button("End Turn");
		endTurn.setFont(av20);
		endTurn.setTextFill(Color.WHITE);
		endTurn.setStyle(" -fx-background-color: transparent;");
		endTurn.setOnMouseEntered(e-> endTurn.setTextFill(Color.YELLOW));
		endTurn.setOnMouseExited(e-> endTurn.setTextFill(Color.WHITE));
//		endTurn.setMinWidth(250);
//		endTurn.setMinHeight(200);
//		endTurn.setMaxWidth(250);
//		endTurn.setMaxHeight(200);
		
		endTurn.setOnAction(e -> {
			
			g.endTurn();
			updateUpNext(turn);
			
//			for ( int i = 1; i<AppliedEffectsp1.getChildren().size(); i++) {
//				
//				AppliedEffectsp1.getChildren().remove(i);
//				
//			}
			
			
			if(first.getChildren().get(first.getChildren().size()-1) instanceof VBox) {
				
				first.getChildren().remove(first.getChildren().size()-1);
				
			}
			
			
			AppliedEffectsp1 = new VBox(2);
			AppliedEffectsp1.setStyle(" -fx-padding: 5; -fx-border-color: yellow; ");
			Label curapef = new Label("Current Champion Applied Effects: ");
			curapef.setFont(Font.font("Impact", 15));
			curapef.setTextFill(Color.YELLOW);
			AppliedEffectsp1.getChildren().add(curapef);
			first.getChildren().addAll(AppliedEffectsp1 );
			
			if(second.getChildren().get(second.getChildren().size()-1) instanceof VBox) {
				
				second.getChildren().remove(second.getChildren().size()-1);
				
			}
			
			
			AppliedEffectsp2 = new VBox(2);
			AppliedEffectsp2.setStyle(" -fx-padding: 5; -fx-border-color: yellow; ");
			Label curapef1 = new Label("Current Champion Applied Effects: ");
			curapef1.setFont(Font.font("Impact", 15));
			curapef1.setTextFill(Color.YELLOW);
			AppliedEffectsp2.getChildren().add(curapef1);
			second.getChildren().addAll(AppliedEffectsp2);
			
			
			if (p1.getTeam().contains(g.getCurrentChampion())) {
				
			
			
			for ( Effect ef: g.getCurrentChampion().getAppliedEffects() ) {
				
				
				
				Label eff = new Label( "Name: "+ ef.getName() + " Duration: "+ ef.getDuration());
				eff.setFont(Font.font("Impact", 15));
				eff.setTextFill(Color.WHITE);
				
				
				
				AppliedEffectsp1.getChildren().add(eff);
				
			}
			
			} else {
			
			for ( Effect eff: g.getCurrentChampion().getAppliedEffects() ) {
				
				
				Label efff = new Label("Name: "+ eff.getName() + " Duration: "+ eff.getDuration());
				efff.setFont(Font.font("Impact", 15));
				efff.setTextFill(Color.WHITE);
				
				
				
				AppliedEffectsp2.getChildren().add(efff);
				
				
			}
			}
			
			
		});
		
		Button useLeaderAbility = new Button("Use Leader Ability");
		
		useLeaderAbility.setFont(av20);
		useLeaderAbility.setTextFill(Color.WHITE);
		useLeaderAbility.setStyle(" -fx-background-color: transparent;");
		useLeaderAbility.setOnMouseEntered(e-> useLeaderAbility.setTextFill(Color.YELLOW));
		useLeaderAbility.setOnMouseExited(e-> useLeaderAbility.setTextFill(Color.WHITE));
	
		useLeaderAbility.setOnAction(e -> applyUseLeaderAbility(grid));
		
		
		
		actions.getChildren().addAll(move, attack, cast, useLeaderAbility, endTurn);
		//----------------------------------------------
		turn.setAlignment(Pos.CENTER);
		actions.setAlignment(Pos.CENTER);
		layout51.setTop(turn);
		layout51.setLeft(first);
		layout51.setRight(second);
		layout51.setCenter(grid);
		layout51.setBottom(actions);
		
		//layout51.setBackground(new Background(gamebg));//=======================================================================================================================================================
		Scene scene51 = new Scene(layout51,1100,700);
		
		window.setScene(scene51);
		//scene51.setres
	}
	
	
	
	public void applyUseLeaderAbility(GridPane grid){
		try {
			g.useLeaderAbility();
			
			updateGrid(grid);
		} catch (LeaderNotCurrentException | LeaderAbilityAlreadyUsedException e) {
			if(e instanceof LeaderNotCurrentException)
				AlertBox.display("Leader Not Current Exception", "The current champion is not a leader.");
			if(e instanceof LeaderAbilityAlreadyUsedException)
				AlertBox.display("Leader Ability Already Used Exception", "The leader ability was already used.");
		}
		Player temp = g.checkGameOver();
		if(temp != null){
			AlertBox.display("GAME OVER", "Game over" + temp.getName() + " wins!");
			window.setScene(scene1);
		}
	}
	
	public void applyMove(Direction d, GridPane grid){
		try {
			g.move(d);
			
			updateGrid(grid);
		} catch (NotEnoughResourcesException | UnallowedMovementException e) {
			if(e instanceof NotEnoughResourcesException)
				AlertBox.display("Not Enough Resources", "Not enough action points");
			if(e instanceof UnallowedMovementException)
				AlertBox.display("Unallowed Movement", "The location you are trying to access is either occupied or out of bounds");
		}
	}
	
	public void applyAttack(Direction d, GridPane grid){
		try {
			g.attack(d);
			updateGrid(grid);
		} catch (NotEnoughResourcesException | ChampionDisarmedException
				| InvalidTargetException e) {
			if(e instanceof NotEnoughResourcesException)
				AlertBox.display("Not Enough Resources", "Not enough action points");
			if(e instanceof ChampionDisarmedException)
				AlertBox.display("Champion Disarmed", "Your current champion is disarmed and can not attack");
			if(e instanceof InvalidTargetException){
				AlertBox.display("Invalid Target", "You can not attack this target");
			}
		}
		
		Player temp = g.checkGameOver();
		if(temp != null){
			AlertBox.display("GAME OVER", "Game over" + temp.getName() + " wins!");
			window.setScene(scene1);
		}
	}
	
	public void applyCastAbilityDirectional(Ability a, Direction d, GridPane grid){
		try {
			g.castAbility(a, d);
			updateGrid(grid);
			UpdateTabs();
		} catch (NotEnoughResourcesException | AbilityUseException
				| CloneNotSupportedException e) {
			if(e instanceof NotEnoughResourcesException)
				AlertBox.display("Not Enough Resources", "Not enough action points");
			if(e instanceof AbilityUseException)
				AlertBox.display("Ability Use Exception", "The chosen ability can not be used");
			if(e instanceof CloneNotSupportedException)
				AlertBox.display("Clone Not Supported Exception", "Clone not supported");
		}
		Player temp = g.checkGameOver();
		if(temp != null){
			AlertBox.display("GAME OVER", "Game over" + temp.getName() + " wins!");
			window.setScene(scene1);
		}
	}
	
	public void applyCastAbility(Ability a, GridPane grid){
		try {
			g.castAbility(a);
			updateGrid(grid);
		} catch (NotEnoughResourcesException | AbilityUseException
				| CloneNotSupportedException e) {
			if(e instanceof NotEnoughResourcesException)
				AlertBox.display("Not Enough Resources", "Not enough action points");
			if(e instanceof AbilityUseException)
				AlertBox.display("Ability Use Exception", "The chosen ability can not be used");
			if(e instanceof CloneNotSupportedException)
				AlertBox.display("Clone Not Supported Exception", "Clone not supported");
		}
		Player temp = g.checkGameOver();
		if(temp != null){
			AlertBox.display("GAME OVER", "Game over" + temp.getName() + " wins!");
			window.setScene(scene1);
		}
	}
	
	public void applyCastAbilitySingleTarget(Ability a, Point p, GridPane grid){
		try {
			g.castAbility(a, p.x, p.y);
			updateGrid(grid);
		} catch (NotEnoughResourcesException | AbilityUseException
				| InvalidTargetException | CloneNotSupportedException e) {
			if(e instanceof NotEnoughResourcesException)
				AlertBox.display("Not Enough Resources", "Not enough action points");
			if(e instanceof AbilityUseException)
				AlertBox.display("Ability Use Exception", "The chosen ability can not be used");
			if(e instanceof CloneNotSupportedException)
				AlertBox.display("Clone Not Supported Exception", "Clone not supported");
		}
		Player temp = g.checkGameOver();
		if(temp != null){
			AlertBox.display("GAME OVER", "Game over" + temp.getName() + " wins!");
			window.setScene(scene1);
		}
	}
	//---------------------------------------------------------------------------------------------------------------------------------------------UPDATE TABS
	public void UpdateTabs() {
		
		for (int i = 1; i<first.getChildren().size(); i++) {
			first.getChildren().remove(i);
		}
		
		for (int j = 1; j<second.getChildren().size(); j++) {
			
			second.getChildren().remove(j);
			
		}
		
		
		TBP1 = new TabPane();
		//first2.getChildren().add(fpname);
		//TBP1.setTabMinWidth(150);
		//TBP1.setTabMinHeight(33);
		TBP1.setTabMaxWidth(50);
		TBP1.getStylesheets().add(Main.class.getResource("tabpane.css").toExternalForm());
		
		for(int i = 0; i<3; i++) {
			
			Tab tab = new Tab(""+p1.getTeam().get(i).getName().toUpperCase());
			Champion c = p1.getTeam().get(i);
			tab.setClosable(false);
			VBox ChampDetails = new VBox();
			
			HBox Hname = new HBox(2);
			
			Label name = new Label("NAME: ");
			name.setFont(Font.font("Impact", 15));
			name.setTextFill(Color.YELLOW);
			Label cname = new Label(c.getName().toUpperCase());
			cname.setFont(Font.font("Impact", 15));
			cname.setTextFill(Color.WHITE);
			Hname.getChildren().addAll(name,cname);
			
			
			
			HBox Htype = new HBox(2);
			
			Label ctype = new Label("TYPE: ");
			ctype.setFont(Font.font("Impact", 15));
			ctype.setTextFill(Color.YELLOW);
			
			Label type = new Label();
			type.setFont(Font.font("Impact", 15));
			type.setTextFill(Color.WHITE);
			
			if(c instanceof Hero)
				type.setText("HERO");
			if(c instanceof AntiHero)
				type.setText("ANTI-HERO");
			if(c instanceof Villain)
				type.setText("VILLAIN");
			
			Htype.getChildren().addAll(ctype,type);
			
			
			HBox Hhp = new HBox(2);
			
			Label hp = new Label("HEALTH POINTS: ");
			hp.setFont(Font.font("Impact", 15));
			hp.setTextFill(Color.YELLOW);
			
			Label chp = new Label(""+c.getCurrentHP());
			chp.setFont(Font.font("Impact", 15));
			chp.setTextFill(Color.WHITE);
			
			Hhp.getChildren().addAll(hp,chp);

			
			HBox Hmana = new HBox(2);
			
			Label mana = new Label("MANA: ");
			mana.setFont(Font.font("Impact", 15));
			mana.setTextFill(Color.YELLOW);
			
			Label cmana = new Label("" + c.getMana());
			cmana.setFont(Font.font("Impact", 15));
			cmana.setTextFill(Color.WHITE);
			
			Hmana.getChildren().addAll(mana,cmana);
			
			
			HBox Hactionpts = new HBox(2);
			
			Label actionpts = new Label("ACTION POINTS: ");
			actionpts.setFont(Font.font("Impact", 15));
			actionpts.setTextFill(Color.YELLOW);
			
			Label cactionpts = new Label("" + c.getCurrentActionPoints());
			cactionpts.setFont(Font.font("Impact", 15));
			cactionpts.setTextFill(Color.WHITE);
			
			Hactionpts.getChildren().addAll(actionpts,cactionpts);
			
			
			
			
			Label section2 = new Label("CHAMPION ABILITIES:- ");
			section2.setFont(Font.font("Impact", 15));
			section2.setTextFill(Color.YELLOW);
			
			
			ChampDetails.getChildren().addAll(Hname,Htype,Hhp,Hmana,Hactionpts,section2);
			
			TabPane ChampAb = new TabPane();
			ChampAb.setMaxWidth(250);
			ChampDetails.getChildren().add(ChampAb);
			tab.setContent(ChampDetails);
			TBP1.getTabs().add(tab);
			
			for(int y = 0; y<3; y++) {
				
				
				Ability a = c.getAbilities().get(y);
				
				Tab AbTab = new Tab(""+a.getName().toUpperCase());
				AbTab.setClosable(false);
				
				VBox AbDetails = new VBox();
				
				HBox Haname = new HBox(2);
				
				Label aname = new Label("NAME: ");
				aname.setFont(Font.font("Impact", 15));
				aname.setTextFill(Color.YELLOW);
				Label caname = new Label(""+ a.getName().toUpperCase());
				caname.setFont(Font.font("Impact", 15));
				caname.setTextFill(Color.YELLOW);
				
				Haname.getChildren().addAll(aname,caname);
				
				
				
				HBox Hatype = new HBox(2);
				
				Label catype = new Label("TYPE: ");
				catype.setFont(new Font("Impact",15));
				catype.setTextFill(Color.YELLOW);
				
				Label atype = new Label();
				atype.setFont(new Font("Impact",15));
				atype.setTextFill(Color.WHITE);
			
				Hatype.getChildren().addAll(catype,atype);
				
				
				HBox Hcustom = new HBox(2);
				
				Label acustom = new Label("DAMAGE AMOUNT: ");
				acustom.setFont(Font.font("Impact", 15));
				acustom.setTextFill(Color.WHITE);
				
				Label custom = new Label();
				custom.setFont(Font.font("Impact", 15));
				custom.setTextFill(Color.YELLOW);
			
				if(a instanceof DamagingAbility){
					atype.setText("DAMAGING");
					
					custom.setText(""+((DamagingAbility)a).getDamageAmount());
				}
				if(a instanceof HealingAbility){
					atype.setText("HEALING");
					custom.setText(""+((HealingAbility)a).getHealAmount());
				}
				if(a instanceof CrowdControlAbility){
					atype.setText("CROWD CONTROL");
					custom.setText(""+((CrowdControlAbility)a).getEffect().getName());
				}
				
				Hcustom.getChildren().addAll(acustom,custom);
				
				
				HBox Haof = new HBox(2);
				
				Label aof = new Label("AREA OF EFFECT: ");
				aof.setFont(Font.font("Impact", 15));
				aof.setTextFill(Color.YELLOW);
				Label caof = new Label("" + a.getCastArea());
				caof.setFont(Font.font("Impact", 15));
				caof.setTextFill(Color.WHITE);
				
				Haof.getChildren().addAll(aof,caof);
				
				
				
				HBox Harange = new HBox(2);
				
				Label carange = new Label("CAST RANGE: ");
				carange.setFont(Font.font("Impact", 15));
				carange.setTextFill(Color.YELLOW);
				Label arange = new Label("" + a.getCastRange());
				arange.setFont(Font.font("Impact", 15));
				arange.setTextFill(Color.WHITE);
				
				Harange.getChildren().addAll(carange,arange);
				
				
				HBox Hamana = new HBox(2);
				
				Label camana = new Label("MANA: ");
				camana.setFont(Font.font("Impact", 15));
				camana.setTextFill(Color.YELLOW);
				Label amana = new Label("" + a.getManaCost());
				amana.setFont(Font.font("Impact", 15));
				amana.setTextFill(Color.WHITE);
				
				Hamana.getChildren().addAll(camana,amana);
				
				
				
				HBox Haactionpts = new HBox(2);
				
				Label caactionpts = new Label("ACTION POINTS COST: ");
				caactionpts.setFont(Font.font("Impact", 15));
				caactionpts.setTextFill(Color.YELLOW);
				Label aactionpts = new Label("" + a.getRequiredActionPoints());
				aactionpts.setFont(Font.font("Impact", 15));
				aactionpts.setTextFill(Color.WHITE);
				
				Haactionpts.getChildren().addAll(caactionpts,aactionpts);
				
				
				HBox HacurrCD = new HBox(2);
				
				Label cacurrCD = new Label("CURRENT COOLDOWN: ");
				cacurrCD.setFont(Font.font("Impact", 15));
				cacurrCD.setTextFill(Color.YELLOW);
				Label acurrCD = new Label("" + a.getCurrentCooldown());
				acurrCD.setFont(Font.font("Impact", 15));
				acurrCD.setTextFill(Color.WHITE);
				
				HacurrCD.getChildren().addAll(cacurrCD,acurrCD);
				
			
				
				HBox HabaseCD = new HBox(2);
				
				Label abaseCD = new Label("BASE COOLDOWN: ");
				abaseCD.setFont(Font.font("Impact", 15));
				abaseCD.setTextFill(Color.YELLOW);
				Label cabaseCD = new Label("" + a.getBaseCooldown());
				cabaseCD.setFont(Font.font("Impact", 15));
				cabaseCD.setTextFill(Color.WHITE);
				
				HabaseCD.getChildren().addAll(abaseCD,cabaseCD);
				
				
				HBox Hattackdmg = new HBox(2);
				
				Label attackdmg = new Label("ATTACK DAMAGE: ");
				attackdmg.setFont(Font.font("Impact", 15));
				attackdmg.setTextFill(Color.YELLOW);
				Label cattackdmg = new Label("" + c.getAttackDamage());
				cattackdmg.setFont(Font.font("Impact", 15));
				cattackdmg.setTextFill(Color.WHITE);
				
				Hattackdmg.getChildren().addAll(attackdmg,cattackdmg);
			
			
				HBox Hattackrange = new HBox(2);
				
				Label attackrange = new Label("ATTACK RANGE: ");
				attackrange.setFont(Font.font("Impact", 15));
				attackrange.setTextFill(Color.YELLOW);
				Label cattackrange = new Label("" + c.getAttackDamage());
				cattackrange.setFont(Font.font("Impact", 15));
				cattackrange.setTextFill(Color.WHITE);
				
				Hattackrange.getChildren().addAll(attackrange,cattackrange);
				
				
				
				
				
				AbDetails.getChildren().addAll(Haname,Hatype,Haof,Harange,Hamana,Haactionpts,HacurrCD,HabaseCD,Hcustom,Hattackdmg,Hattackrange);
				AbTab.setContent(AbDetails);
				ChampAb.getTabs().add(AbTab);
				
			}
			
				
				
			}
			
		
		
		first.getChildren().add(TBP1);
		
		
		
		 
		
		
		
		

		VBox second = new VBox(2);
		
		Label spname2 = new Label(p2.getName().toUpperCase());
		spname2.setFont(new Font("Impact", 30));
		spname2.setTextFill(Color.LIGHTBLUE);
		
		
		
		second.getChildren().add(spname2);
		
		TBP2 = new TabPane();
		TBP2.getStylesheets().add(Main.class.getResource("tabpane.css").toExternalForm());
		AppliedEffectsp1 = new VBox(2);
		
		//first2.getChildren().add(fpname);
		//TBP2.setTabMinWidth(150);
		//TBP1.setTabMinHeight(33);
		
		for(int i = 0; i<3; i++) {
			
			Tab tab = new Tab(""+p2.getTeam().get(i).getName().toUpperCase());
			Champion c = p2.getTeam().get(i);
			tab.setClosable(false);
			VBox ChampDetails = new VBox();
			
			HBox Hname = new HBox(2);
			
			Label name = new Label("NAME: ");
			name.setFont(Font.font("Impact", 15));
			name.setTextFill(Color.YELLOW);
			Label cname = new Label(c.getName().toUpperCase());
			cname.setFont(Font.font("Impact", 15));
			cname.setTextFill(Color.WHITE);
			Hname.getChildren().addAll(name,cname);
			
			
			
			HBox Htype = new HBox(2);
			
			Label ctype = new Label("TYPE: ");
			ctype.setFont(Font.font("Impact", 15));
			ctype.setTextFill(Color.YELLOW);
			
			Label type = new Label();
			type.setFont(Font.font("Impact", 15));
			type.setTextFill(Color.WHITE);
			
			if(c instanceof Hero)
				type.setText("HERO");
			if(c instanceof AntiHero)
				type.setText("ANTI-HERO");
			if(c instanceof Villain)
				type.setText("VILLAIN");
			
			Htype.getChildren().addAll(ctype,type);
			
			
			HBox Hhp = new HBox(2);
			
			Label hp = new Label("HEALTH POINTS: ");
			hp.setFont(Font.font("Impact", 15));
			hp.setTextFill(Color.YELLOW);
			
			Label chp = new Label(""+c.getCurrentHP());
			chp.setFont(Font.font("Impact", 15));
			chp.setTextFill(Color.WHITE);
			
			Hhp.getChildren().addAll(hp,chp);

			
			HBox Hmana = new HBox(2);
			
			Label mana = new Label("MANA: ");
			mana.setFont(Font.font("Impact", 15));
			mana.setTextFill(Color.YELLOW);
			
			Label cmana = new Label("" + c.getMana());
			cmana.setFont(Font.font("Impact", 15));
			cmana.setTextFill(Color.WHITE);
			
			Hmana.getChildren().addAll(mana,cmana);
			
			
			HBox Hactionpts = new HBox(2);
			
			Label actionpts = new Label("ACTION POINTS: ");
			actionpts.setFont(Font.font("Impact", 15));
			actionpts.setTextFill(Color.YELLOW);
			
			Label cactionpts = new Label("" + c.getCurrentActionPoints());
			cactionpts.setFont(Font.font("Impact", 15));
			cactionpts.setTextFill(Color.WHITE);
			
			Hactionpts.getChildren().addAll(actionpts,cactionpts);
			
			
			Label section2 = new Label("CHAMPION ABILITIES:- ");
			section2.setFont(Font.font("Impact", 15));
			section2.setTextFill(Color.YELLOW);
			
			ChampDetails.getChildren().addAll(Hname,Htype,Hhp,Hmana,Hactionpts,section2);
			
			// -----------------------------------------------------------------------------------------------------------APPLIED EFFECTS
			
			
				
			TabPane ChampAb = new TabPane();
			ChampAb.setMaxWidth(250);
			ChampDetails.getChildren().add(ChampAb);
			tab.setContent(ChampDetails);
			TBP2.getTabs().add(tab);
			
			
		//	second.getChildren().add(second1);
			
			for(int y = 0; y<3; y++) {
				
				
				Ability a = c.getAbilities().get(y);
				
				Tab AbTab = new Tab(""+a.getName().toUpperCase());
				AbTab.setClosable(false);
				
				VBox AbDetails = new VBox();
				
				HBox Haname = new HBox(2);
				
				Label aname = new Label("NAME: ");
				aname.setFont(Font.font("Impact", 15));
				aname.setTextFill(Color.YELLOW);
				Label caname = new Label(""+ a.getName().toUpperCase());
				caname.setFont(Font.font("Impact", 15));
				caname.setTextFill(Color.YELLOW);
				
				Haname.getChildren().addAll(aname,caname);
				
				
				
				HBox Hatype = new HBox(2);
				
				Label catype = new Label("TYPE: ");
				catype.setFont(new Font("Impact",15));
				catype.setTextFill(Color.YELLOW);
				
				Label atype = new Label();
				atype.setFont(new Font("Impact",15));
				atype.setTextFill(Color.WHITE);
			
				Hatype.getChildren().addAll(catype,atype);
				
				
				HBox Hcustom = new HBox(2);
				
				Label acustom = new Label("DAMAGE AMOUNT: ");
				acustom.setFont(Font.font("Impact", 15));
				acustom.setTextFill(Color.WHITE);
				
				Label custom = new Label();
				custom.setFont(Font.font("Impact", 15));
				custom.setTextFill(Color.YELLOW);
			
				if(a instanceof DamagingAbility){
					atype.setText("DAMAGING");
					
					custom.setText(""+((DamagingAbility)a).getDamageAmount());
				}
				if(a instanceof HealingAbility){
					atype.setText("HEALING");
					custom.setText(""+((HealingAbility)a).getHealAmount());
				}
				if(a instanceof CrowdControlAbility){
					atype.setText("CROWD CONTROL");
					custom.setText(""+((CrowdControlAbility)a).getEffect().getName());
				}
				
				Hcustom.getChildren().addAll(acustom,custom);
				
				
				HBox Haof = new HBox(2);
				
				Label aof = new Label("AREA OF EFFECT: ");
				aof.setFont(Font.font("Impact", 15));
				aof.setTextFill(Color.YELLOW);
				Label caof = new Label("" + a.getCastArea());
				caof.setFont(Font.font("Impact", 15));
				caof.setTextFill(Color.WHITE);
				
				Haof.getChildren().addAll(aof,caof);
				
				
				
				HBox Harange = new HBox(2);
				
				Label carange = new Label("CAST RANGE: ");
				carange.setFont(Font.font("Impact", 15));
				carange.setTextFill(Color.YELLOW);
				Label arange = new Label("" + a.getCastRange());
				arange.setFont(Font.font("Impact", 15));
				arange.setTextFill(Color.WHITE);
				
				Harange.getChildren().addAll(carange,arange);
				
				
				HBox Hamana = new HBox(2);
				
				Label camana = new Label("MANA: ");
				camana.setFont(Font.font("Impact", 15));
				camana.setTextFill(Color.YELLOW);
				Label amana = new Label("" + a.getManaCost());
				amana.setFont(Font.font("Impact", 15));
				amana.setTextFill(Color.WHITE);
				
				Hamana.getChildren().addAll(camana,amana);
				
				
				
				HBox Haactionpts = new HBox(2);
				
				Label caactionpts = new Label("ACTION POINTS COST: ");
				caactionpts.setFont(Font.font("Impact", 15));
				caactionpts.setTextFill(Color.YELLOW);
				Label aactionpts = new Label("" + a.getRequiredActionPoints());
				aactionpts.setFont(Font.font("Impact", 15));
				aactionpts.setTextFill(Color.WHITE);
				
				Haactionpts.getChildren().addAll(caactionpts,aactionpts);
				
				
				HBox HacurrCD = new HBox(2);
				
				Label cacurrCD = new Label("CURRENT COOLDOWN: ");
				cacurrCD.setFont(Font.font("Impact", 15));
				cacurrCD.setTextFill(Color.YELLOW);
				Label acurrCD = new Label("" + a.getCurrentCooldown());
				acurrCD.setFont(Font.font("Impact", 15));
				acurrCD.setTextFill(Color.WHITE);
				
				HacurrCD.getChildren().addAll(cacurrCD,acurrCD);
				
			
				
				HBox HabaseCD = new HBox(2);
				
				Label abaseCD = new Label("BASE COOLDOWN: ");
				abaseCD.setFont(Font.font("Impact", 15));
				abaseCD.setTextFill(Color.YELLOW);
				Label cabaseCD = new Label("" + a.getBaseCooldown());
				cabaseCD.setFont(Font.font("Impact", 15));
				cabaseCD.setTextFill(Color.WHITE);
				
				HabaseCD.getChildren().addAll(abaseCD,cabaseCD);
				
				
				HBox Hattackdmg = new HBox(2);
				
				Label attackdmg = new Label("ATTACK DAMAGE: ");
				attackdmg.setFont(Font.font("Impact", 15));
				attackdmg.setTextFill(Color.YELLOW);
				Label cattackdmg = new Label("" + c.getAttackDamage());
				cattackdmg.setFont(Font.font("Impact", 15));
				cattackdmg.setTextFill(Color.WHITE);
				
				Hattackdmg.getChildren().addAll(attackdmg,cattackdmg);
			
			
				HBox Hattackrange = new HBox(2);
				
				Label attackrange = new Label("ATTACK RANGE: ");
				attackrange.setFont(Font.font("Impact", 15));
				attackrange.setTextFill(Color.YELLOW);
				Label cattackrange = new Label("" + c.getAttackDamage());
				cattackrange.setFont(Font.font("Impact", 15));
				cattackrange.setTextFill(Color.WHITE);
				
				Hattackrange.getChildren().addAll(attackrange,cattackrange);
				
				
				
				
				
				AbDetails.getChildren().addAll(Haname,Hatype,Haof,Harange,Hamana,Haactionpts,HacurrCD,HabaseCD,Hcustom,Hattackdmg,Hattackrange);
				AbTab.setContent(AbDetails);
				ChampAb.getTabs().add(AbTab);
				
			}
			
				
				
			}
		
		
		
		
		
		second.getChildren().addAll(TBP2); 
		
		
		
		
	}
	
	
	
	
	
	
	
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------UPDATE GRID================================
	public void updateGrid(GridPane grid){
		
		Image caps = new Image("C:\\Users\\omrhs\\Documents\\College\\Semester 4\\Game\\M3\\final\\src\\board\\CaptainAmerica.png");
		final ImageView CaptainAmerica = new ImageView();
		CaptainAmerica.setImage(caps);
		CaptainAmerica.setFitHeight(100);
		CaptainAmerica.setFitWidth(100);
	
		Image ded = new Image("C:\\Users\\omrhs\\Documents\\College\\Semester 4\\Game\\M3\\final\\src\\board\\Deadpool.png");
		final ImageView Deadpool = new ImageView();
		Deadpool.setImage(ded);
		Deadpool.setFitHeight(100);
		Deadpool.setFitWidth(100);
		
		
		
		Image drst = new Image("C:\\Users\\omrhs\\Documents\\College\\Semester 4\\Game\\M3\\final\\src\\board\\DrStrange.png");
		final ImageView DrStrange = new ImageView();
		DrStrange.setImage(drst);
		DrStrange.setFitHeight(100);
		DrStrange.setFitWidth(100);
		
		Image elec = new Image("C:\\Users\\omrhs\\Documents\\College\\Semester 4\\Game\\M3\\final\\src\\board\\Electro.png");
		final ImageView Electro = new ImageView();
		Electro.setImage(elec);
		Electro.setFitHeight(100);
		Electro.setFitWidth(100);
		
		Image ghst = new Image("C:\\Users\\omrhs\\Documents\\College\\Semester 4\\Game\\M3\\final\\src\\board\\GhostRider.png");
		final ImageView GhostRider = new ImageView();
		GhostRider.setImage(ghst);
		GhostRider.setFitHeight(100);
		GhostRider.setFitWidth(100);
		
		Image helas = new Image("C:\\Users\\omrhs\\Documents\\College\\Semester 4\\Game\\M3\\final\\src\\board\\Hela.png");
		final ImageView Hela = new ImageView();
		Hela.setImage(helas);
		Hela.setFitHeight(100);
		Hela.setFitWidth(100);
		
		Image hulks = new Image("C:\\Users\\omrhs\\Documents\\College\\Semester 4\\Game\\M3\\final\\src\\board\\Hulk.png");
		final ImageView Hulk = new ImageView();
		Hulk.setImage(hulks);
		Hulk.setFitHeight(100);
		Hulk.setFitWidth(100);
		
		Image ice = new Image("C:\\Users\\omrhs\\Documents\\College\\Semester 4\\Game\\M3\\final\\src\\board\\Iceman.png");
		final ImageView Iceman = new ImageView();
		Iceman.setImage(ice);
		Iceman.setFitHeight(100);
		Iceman.setFitWidth(100);
		
		Image iron = new Image("C:\\Users\\omrhs\\Documents\\College\\Semester 4\\Game\\M3\\final\\src\\board\\Ironman.png");
		final ImageView Ironman = new ImageView();
		Ironman.setImage(iron);
		Ironman.setFitHeight(100);
		Ironman.setFitWidth(100);
		
		Image loksi = new Image("C:\\Users\\omrhs\\Documents\\College\\Semester 4\\Game\\M3\\final\\src\\board\\Loki.png");
		final ImageView Loki = new ImageView();
		Loki.setImage(loksi);
		Loki.setFitHeight(100);
		Loki.setFitWidth(100);
		
		Image quick = new Image("C:\\Users\\omrhs\\Documents\\College\\Semester 4\\Game\\M3\\final\\src\\board\\Quicksilver.png");
		final ImageView Quicksilver = new ImageView();
		Quicksilver.setImage(quick);
		Quicksilver.setFitHeight(100);
		Quicksilver.setFitWidth(100);
		
		Image spider = new Image("C:\\Users\\omrhs\\Documents\\College\\Semester 4\\Game\\M3\\final\\src\\board\\Spiderman.png");
		final ImageView Spiderman = new ImageView();
		Spiderman.setImage(spider);
		Spiderman.setFitHeight(100);
		Spiderman.setFitWidth(100);

		Image thors = new Image("C:\\Users\\omrhs\\Documents\\College\\Semester 4\\Game\\M3\\final\\src\\board\\Thor.png");
		final ImageView Thor = new ImageView();
		Thor.setImage(thors);
		Thor.setFitHeight(100);
		Thor.setFitWidth(100);
		
		Image venosm = new Image("C:\\Users\\omrhs\\Documents\\College\\Semester 4\\Game\\M3\\final\\src\\board\\Venom.png");
		final ImageView Venom = new ImageView();
		Venom.setImage(venosm);
		Venom.setFitHeight(100);
		Venom.setFitWidth(100);
		
		Image yel = new Image("C:\\Users\\omrhs\\Documents\\College\\Semester 4\\Game\\M3\\final\\src\\board\\YellowJacket.png");
		final ImageView YellowJacket = new ImageView();
		YellowJacket.setImage(yel);
		YellowJacket.setFitHeight(100);
		YellowJacket.setFitWidth(100);
		
		
		
		
		for(int i = 0; i < Game.getBoardwidth(); i++){
			for(int j = 0; j < Game.getBoardheight(); j++){
				Button b = new Button("Empty");
				b.setStyle(" -fx-border-width: 1px; -fx-background-color: rgb(153,153,255); -fx-text-fill: #ffffff");
				if(g.getBoard()[i][j] instanceof Cover) {
				
					Image cover = new Image("C:\\Users\\omrhs\\Documents\\College\\Semester 4\\Game\\M3\\final\\src\\board\\cover.png");
					final ImageView cov = new ImageView();
					cov.setImage(cover);
					cov.setFitHeight(100);
					cov.setFitWidth(100);
					
					b.setGraphic(cov);
					Tooltip info = new Tooltip("Cover" + "\n" + "Current HP: " + ((Cover)g.getBoard()[i][j]).getCurrentHP());
					b.setTooltip(info);
					
				}
				
				if(g.getBoard()[i][j] instanceof Champion) {
Tooltip info = new Tooltip(((Champion)g.getBoard()[i][j]).getName() + "\n" + "Current HP:" + ((Champion)g.getBoard()[i][j]).getCurrentHP() + "\n" + "Action Points: " + ((Champion)g.getBoard()[i][j]).getCurrentActionPoints() + "\n" + "Mana: " + ((Champion)g.getBoard()[i][j]).getMana()  ) ;
					
					b.setTooltip(info);
					
					
					String name = ((Champion)g.getBoard()[i][j]).getName();
					
					if(name.equals("Captain America")) {
						b.setGraphic(CaptainAmerica);
					} else if (name.equals("Deadpool")) {
						b.setGraphic(Deadpool);
					}else if(name.equals("Electro")) {
						b.setGraphic(Electro);
					}else if(name.equals("Ghost Rider")) {
						b.setGraphic(GhostRider);
					}else if(name.equals("Hela")) {
						b.setGraphic(Hela);
					}else if(name.equals("Hulk")) {
						b.setGraphic(Hulk);
					}else if(name.equals("Iceman")) {
						b.setGraphic(Iceman);
					}else if(name.equals("Ironman")) {
						b.setGraphic(Ironman);
					}else if(name.equals("Loki")) {
						b.setGraphic(Loki);
					}else if(name.equals("Quicksilver")) {
						b.setGraphic(Quicksilver);
					}else if(name.equals("Spiderman")) {
						b.setGraphic(Spiderman);
					}else if(name.equals("Thor")) {
						b.setGraphic(Thor);
					}else if(name.equals("Venom")) {
						b.setGraphic(Venom);
					}else if(name.equals("Dr Strange")){
						b.setGraphic(DrStrange);
					}
					
					else{
						b.setGraphic(YellowJacket);
					}
					
				}
					
				//b.setShape(new Rectangle());
				//b.setAlignment(Pos.CENTER);
				b.setMinWidth(110);
				b.setMinHeight(120);
				b.setMaxWidth(110);
				b.setMaxHeight(120);	
				Point p = new Point(i,j);
				b.setOnAction(e -> {
					if(flagST == true){
						applyCastAbilitySingleTarget(tempa, p, grid);
					}
					flagST = false;
					tempa = null;
				});
				grid.add(b, j, i);
				//System.out.println(grid.getRowIndex(b));
			}
		}
	}
	
//	public void updateUpNext(HBox turn){
//		/*Node temp = turn.getChildren().remove(1);
//		turn.getChildren().add(temp);*/
//		turn.getChildren().clear();
//		Label upNxt = new Label("Up next: ");
//		turn.getChildren().add(upNxt);
//		ArrayList<Object> temp = new ArrayList<Object>();
//		while(!g.getTurnOrder().isEmpty()){
//			Label currChamp = new Label(((Champion)g.getTurnOrder().peekMin()).getName());
//			temp.add(g.getTurnOrder().remove());
//			turn.getChildren().add(currChamp);
//		}
//		for(int i = 0; i < temp.size(); i++)
//			g.getTurnOrder().insert((Comparable) temp.remove(i));
//	}
	
	public void updateUpNext(HBox turn){
		turn.getChildren().clear();
		Label cur = new Label("Currentl Playing: ");
		cur.setFont(av30);
		cur.setTextFill(Color.YELLOW);
		Label curr = new Label(""+g.getCurrentChampion().getName());
		curr.setFont(av30);
		curr.setTextFill(Color.WHITE);
		//curr.setStyle("-fx-font-size: 2em; -fx-text-fill: #ffff00 ");
		Champion temp = g.getCurrentChampion();
		Label UNX = new Label("Up Next: ");
		UNX.setFont(av30);
		UNX.setTextFill(Color.YELLOW);
		Label upNext = new Label("");
		upNext.setFont(av30);
		upNext.setTextFill(Color.WHITE);
		//upNext.setStyle("-fx-font-size: 2em; -fx-text-fill: #ffff00 ");
		if(!g.getTurnOrder().isEmpty()){
			g.getTurnOrder().remove();
			if(!g.getTurnOrder().isEmpty()){
				upNext.setText(((Champion)g.getTurnOrder().peekMin()).getName());
			}
			g.getTurnOrder().insert(temp);
		}
		turn.getChildren().addAll(cur,curr, UNX, upNext);
}
	
	
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
}