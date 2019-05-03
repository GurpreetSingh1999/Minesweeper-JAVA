import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Seventh extends Application {
	Label label = new Label();
	Stage newStage = new Stage();
	BorderPane pane = new BorderPane();
	GameButton gameMiddle = new GameButton();
	public Stage theStage;

	int length = 8; // when we are going to make the difficulty levels then
					// change it.
	int width = 8; // when we are going to make the difficulty levels then
					// change it.
	int totalPossibility = 10; // when we are going to make the difficulty
	// levels then change it.

	int values[][] = new int[length][width];
	MineButtons button[][] = new MineButtons[length][width];
	boolean gameOver = false;
	int minesLeft = totalPossibility;
	boolean flagged = false;
	boolean mineFound = false;
	int rightState = 0;
	int countState = 0;
	boolean firstClick = false;

	public static void main(String[] args) {
		launch(args);

	}

	public void start(Stage theStage) {
		
		gameOver = false;
		BorderPane pane = new BorderPane();
		pane.setTop(getVBox());
		pane.setCenter(getHBox());
		pane.setBottom(getGridPane());
		getVBox();
		theStage.setTitle("Mine Sweeper");
		Scene scene = new Scene(pane);
		theStage.setScene(scene);
		theStage.show();
		gameMiddle.setOnAction(e -> {
			minesLeft = totalPossibility;
			restart();
			start(theStage);
		});

	}

	public void restart() {
		gameOver = false;
		firstClick = false;

		for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < values.length; j++) {
				values[i][j] = 0;
			}
		}

	}
	// levels changes.
	public VBox getVBox() {
		VBox vb = new VBox();
		pane.setTop(vb);
		MenuBar menu = new MenuBar();
		Menu game = new Menu("Game Levels");
		menu.getMenus().add(game);
		MenuItem beginner = new MenuItem("Beginner");
		MenuItem intermediate = new MenuItem("Intermediate");
		MenuItem expert = new MenuItem("Expert");
		game.getItems().addAll(beginner, intermediate, expert);
		vb.getChildren().add(menu);
        int x = 0;
        int y = 0;
		beginner.setOnAction(e -> {
			length = 8;
			width = 8;
			totalPossibility = 10;
			restart();
			setVal(x,y);
			start(theStage);
		});
		intermediate.setOnAction(e -> {
			length = 16;
			width = 16;
			totalPossibility = 16;
			restart();
			setVal(x,y);
			start(theStage);
		});
		expert.setOnAction(e -> {
			length = 16;
			width = 32;
			totalPossibility = 99;
			restart();
			setVal(x,y);
			start(theStage);
		});
		return vb;

	}

	public void setVal(int x, int y) {

		int countMinesPlaced = 0;
		int totalMinesPlaced = 0;

		//change the values when making values.
		if ((x >= 0 && x <= 7) && (y >= 0 && y <= 7)) {
			button[x][y].value = 0;
			button[x][y].zeroValue = 1;
		}
		if ((x > 0 && x <= 7) && (y >= 0 && y <= 7)) {
			button[x - 1][y].value = 0;
			button[x - 1][y].zeroValue = 1;

		}
		if ((x >= 0 && x < 7) && (y >= 0 && y <= 7)) {

			button[x + 1][y].value = 0;
			button[x + 1][y].zeroValue = 1;

		}
		if ((x >= 0 && x <= 7) && (y > 0 && y <= 7)) {
			button[x][y - 1].value = 0;
			button[x][y - 1].zeroValue = 1;

		}
		if ((x >= 0 && x <= 7) && (y >= 0 && y < 7)) {
			button[x][y + 1].value = 0;
			button[x][y + 1].zeroValue = 1;

		}
		if ((x >= 0 && x < 7) && (y > 0 && y <= 7)) {
			button[x + 1][y - 1].value = 0;
			button[x + 1][y - 1].zeroValue = 1;

		}
		if ((x > 0 && x <= 7) && (y >= 0 && y < 7)) {
			button[x - 1][y + 1].value = 0;
			button[x - 1][y + 1].zeroValue = 1;

		}
		if ((x >= 0 && x < 7) && (y >= 0 && y < 7)) {

			button[x + 1][y + 1].value = 0;
			button[x + 1][y + 1].zeroValue = 1;

		}
		if ((x > 0 && x <= 7) && (y > 0 && y <= 7)) {
			button[x - 1][y - 1].value = 0;
			button[x - 1][y - 1].zeroValue = 1;

		}

			int randomX, randomY;
			
			while (totalMinesPlaced < totalPossibility) {
			randomX = (int) (Math.random() * (length));
			randomY = (int) (Math.random() * (width));
			if (button[randomX][randomY].zeroValue == 0) {
				if (values[randomX][randomY] == 0) {
					values[randomX][randomY] = 9;
					countMinesPlaced++;
					totalMinesPlaced = countMinesPlaced;
				}
			}
		}

		// here change the length and width accordingly in the if..else
		// statement when introducing difficulty.

		for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < values.length; j++) {

				if (values[i][j] == 9) {

					if ((i > 0 && i <= 7) && (j >= 0 && j <= 7)) {

						if (values[i - 1][j] >= 0 && values[i - 1][j] <= 7) {
							values[i - 1][j]++;

						}
					}

					if ((i >= 0 && i < 7) && (j >= 0 && j <= 7)) {

						if (values[i + 1][j] >= 0 && values[i + 1][j] <= 7) {
							values[i + 1][j]++;

						}
					}

					if ((i >= 0 && i <= 7) && (j > 0 && j <= 7)) {

						if (values[i][j - 1] >= 0 && values[i][j - 1] <= 7) {
							values[i][j - 1]++;

						}
					}
					if ((i >= 0 && i <= 7) && (j >= 0 && j < 7)) {
						if (values[i][j + 1] >= 0 && values[i][j + 1] <= 7) {
							values[i][j + 1]++;

						}
					}

					if ((i >= 0 && i < 7) && (j >= 0 && j < 7)) {

						if (values[i + 1][j + 1] >= 0 && values[i + 1][j + 1] <= 7) {
							values[i + 1][j + 1]++;
						}
					}

					if ((i > 0 && i <= 7) && (j > 0 && j <= 7)) {

						if (values[i - 1][j - 1] >= 0 && values[i - 1][j - 1] <= 7) {
							values[i - 1][j - 1]++;

						}
					}

					if ((i > 0 && i <= 7) && (j >= 0 && j < 7)) {
						if (values[i - 1][j + 1] >= 0 && values[i - 1][j + 1] <= 7) {
							values[i - 1][j + 1]++;

						}
					}

					if ((i >= 0 && i < 7) && (j > 0 && j <= 7)) {
						if (values[i + 1][j - 1] >= 0 && values[i + 1][j - 1] <= 7) {
							values[i + 1][j - 1]++;

						}
					}

				}
			}
		}
	}

	private GridPane getGridPane() {
		GridPane gPane = new GridPane();
		int rightState = 0;
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {
				button[i][j] = new MineButtons(values[i][j], i, j, gameMiddle, flagged, rightState);
				gPane.setPadding(new Insets(0, 0, 0, 0));
				gPane.add(button[i][j], i, j);
				MineButtons button1;
				button1 = button[i][j];
				button1.setGraphic(new ImageView(new Image("file:Resc/cover.png")));

				button[i][j].setOnAction(button[i][j]);

				button[i][j].addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent mouseEvent) {
						if (!gameOver && !button1.flagged) {
							gameMiddle.setGraphic(new ImageView(new Image("file:Resc/face-O.png")));
						}
					}
				});
				button[i][j].addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent mouseEvent) {
						if (!gameOver) {
							gameMiddle.setGraphic(new ImageView(new Image("file:Resc/face-smile.png")));
						}
					}
				});

				button[i][j].addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent mouseEvent) {
						if (!gameOver && button1.state == 0 && !button1.flagged) {
							button1.setGraphic(new ImageView(new Image("file:Resc/gp.png")));
						}
					}
				});

				button[i][j].addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent mouseEvent) {
						if (!gameOver && button1.state == 0 && !button1.flagged) {
							button1.setGraphic(new ImageView(new Image("file:Resc/cover.png")));
						}
					}
				});

				button1.setOnMouseClicked(e -> {

					MouseButton button = e.getButton();
					if (button == MouseButton.SECONDARY) {
						if (!gameOver) {
							button1.rightState++;
							if (button1.state == 0) {
								if (button1.rightState % 2 == 1 && minesLeft > 0) {
									button1.setGraphic(new ImageView(new Image("file:Resc/flag.png")));
									button1.flagged = true;
									minesLeft--;
									label.setText("     Mines left :    " + minesLeft);
									mineFound = true;

								} else if (button1.rightState % 2 == 0) {
									button1.state = 0;
									minesLeft++;
									label.setText("     Mines left :    " + minesLeft);
									button1.setGraphic(new ImageView(new Image("file:Resc/cover.png")));
									button1.flagged = false;

								}
							} else {

							}
						}

					}
				});

			}

		}
		return gPane;
	}

	//make time changes.
	private HBox getHBox() {
		HBox pane = new HBox(2);
		ImageView imageCover = new ImageView(new Image("file:Resc/face-smile.png"));
		pane.setPadding(new Insets(0, 0, 0, 0));
		gameMiddle.setGraphic(imageCover);
		pane.getChildren().add(0, new Label("           001           "));
		pane.getChildren().add(1, gameMiddle);
		if (flagged && mineFound) {
			minesLeft--;
		}
		label.setText("     Mines left :    " + minesLeft);
		pane.getChildren().add(label);
		return pane;

	}

	class GameButton extends Button {
		ImageView imageCover = new ImageView(new Image("file:Resc/face-smile.png"));

		public GameButton() {
			setPadding(new Insets(0, 0, 0, 0));
			setGraphic(imageCover);
		}
	}

	class MineButtons extends Button implements EventHandler<ActionEvent> {
		int state;
		int value;
		int x, y;
		int countMines = 0;
		int totalMines = 0;
		int countNonMines = 0;
		int totalNonMines = 0;
		int totalMinesCountSimple = 0;
		int mineCountSimple = 0;
		int totalNonMinesCountSimple = 0;
		int nonMineCountSimple = 0;
		boolean set = false;
		boolean opened = false;
		public boolean flagged;
		public int rightState;
		public int zeroValue;

		ImageView imageCover, image, bombImage, bombred;
		GameButton gameMiddle;

		public MineButtons(int value, int x, int y, GameButton gameMiddle, boolean flagged, int rightState) {
			state = 0;
			this.x = x;
			this.y = y;
			this.value = value;
			this.gameMiddle = gameMiddle;
			this.flagged = flagged;
			this.rightState = rightState;
			setPadding(new Insets(0, 0, 0, 0));
			imageCover = new ImageView(new Image("file:Resc/cover.png"));
			bombImage = new ImageView(new Image("file:Resc/bombrevealed-bicubic.png"));
			bombred = new ImageView(new Image("file:Resc/mine-red.png"));
			
			setGraphic(imageCover);

		}

		@Override
		public void handle(ActionEvent e) {
			
			state++;
			if (value != 9) {
				image = new ImageView(new Image("file:Resc/" + value + ".png"));
			}
			
			if (!firstClick) {
				firstClick = true;
				setVal(x, y);
				for (int i = 0; i < length; i++) {
					for (int j = 0; j < width; j++) { 
						button[i][j].value = values[i][j];
					}
				}
			}
			
			for (int i = 0; i < length; i++) {
				for (int j = 0; j < width; j++) {
					System.out.print(button[j][i].value);
				}
				System.out.println();
			}

			if (!gameOver) {
				switch (state) {
				case 0:
					setGraphic(imageCover);
					break;
					
				case 1:
					seeWins();
					if (set) {
						gameMiddle.setGraphic(new ImageView(new Image("file:Resc/face-win.png")));
					}
					
					if (!flagged) {
						state1Opened();
						if (value == 9) {
							mineFoundActivity();
						} else if (value != 9) {
							setGraphic(image);
							if (value == 0) {
								setGraphic(image);
								openButtons();
							}
						}
						break;	
					} else if (flagged) {
						state = 0;
					}
				case 2:
					if (flagged) {
						state = 0;
					} else if (!flagged) {
						state1Opened();
					}
				}
			} else {
				
			}
		}

		public void mineFoundActivity() {
			gameOver = true;
			setGraphic(bombred);
			gameMiddle.setGraphic(new ImageView(new Image("file:Resc/face-dead.png")));
			for (int x = 0; x < button.length; x++) {
				for (int y = 0; y < button.length; y++) {
					if (button[x][y].value == 9 && button[x][y].state == 0) {
						button[x][y].setGraphic(new ImageView(new Image("file:Resc/mine-grey.png")));
					}
					if(button[x][y].value!= 9 && button[x][y].flagged){
					button[x][y].setGraphic(new ImageView(new Image("file:Resc/mine-misflagged.png")));

					}
				}
			}
		}

		public void openButtons() {
			
			if (value != 9) {
				image = new ImageView(new Image("file:Resc/" + value + ".png"));
				state = 1;
			}

			if ((x > 0 && x <= 7) && (y >= 0 && y <= 7)) {
				button[x - 1][y].setGraphic(new ImageView(new Image("file:Resc/" + button[x - 1][y].value + ".png")));
				if (button[x - 1][y].value == 0 && button[x - 1][y].state == 0) {
					button[x - 1][y].openButtons();
				}
				button[x - 1][y].state = 1;
			}

			if ((x >= 0 && x < 7) && (y >= 0 && y <= 7)) {
				button[x + 1][y].setGraphic(new ImageView(new Image("file:Resc/" + button[x + 1][y].value + ".png")));
				if (button[x + 1][y].value == 0 && button[x + 1][y].state == 0) {
					button[x + 1][y].openButtons();
				}
				button[x + 1][y].state = 1;
			}

			if ((x >= 0 && x <= 7) && (y > 0 && y <= 7)) {
				button[x][y - 1].setGraphic(new ImageView(new Image("file:Resc/" + button[x][y - 1].value + ".png")));
				if (button[x][y - 1].value == 0 && button[x][y - 1].state == 0) {
					button[x][y - 1].openButtons();
				}
				button[x][y - 1].state = 1;
			}

			if ((x >= 0 && x <= 7) && (y >= 0 && y < 7)) {
				button[x][y + 1].setGraphic(new ImageView(new Image("file:Resc/" + button[x][y + 1].value + ".png")));
				if (button[x][y + 1].value == 0 && button[x][y + 1].state == 0) {
					button[x][y + 1].openButtons();
				}
				button[x][y + 1].state = 1;
			}

			if ((x >= 0 && x < 7) && (y >= 0 && y < 7)) {
				button[x + 1][y + 1]
						.setGraphic(new ImageView(new Image("file:Resc/" + button[x + 1][y + 1].value + ".png")));
				if (button[x + 1][y + 1].value == 0 && button[x + 1][y + 1].state == 0) {
					button[x + 1][y + 1].openButtons();
				}
				button[x + 1][y + 1].state = 1;
			}

			if ((x > 0 && x <= 7) && (y > 0 && y <= 7)) {
				button[x - 1][y - 1]
						.setGraphic(new ImageView(new Image("file:Resc/" + button[x - 1][y - 1].value + ".png")));
				if (button[x - 1][y - 1].value == 0 && button[x - 1][y - 1].state == 0) {
					button[x - 1][y - 1].openButtons();
				}
				button[x - 1][y - 1].state = 1;
			}

			if ((x > 0 && x <= 7) && (y >= 0 && y < 7)) {
				button[x - 1][y + 1]
						.setGraphic(new ImageView(new Image("file:Resc/" + button[x - 1][y + 1].value + ".png")));
				if (button[x - 1][y + 1].value == 0 && button[x - 1][y + 1].state == 0) {
					button[x - 1][y + 1].openButtons();
				}
				button[x - 1][y + 1].state = 1;
			}

			if ((x >= 0 && x < 7) && (y > 0 && y <= 7)) {
				button[x + 1][y - 1]
						.setGraphic(new ImageView(new Image("file:Resc/" + button[x + 1][y - 1].value + ".png")));
				if (button[x + 1][y - 1].value == 0 && button[x + 1][y - 1].state == 0) {
					button[x + 1][y - 1].openButtons();
				}
				button[x + 1][y - 1].state = 1;
			}
		}

		public void state1Opened() {
			
			int noOfFlags = 0;

			if ((x > 0 && x <= 7) && (y >= 0 && y <= 7)) {
				if (button[x - 1][y].flagged) {
					noOfFlags++;
				}
			}

			if ((x >= 0 && x < 7) && (y >= 0 && y <= 7)) {
				if (button[x + 1][y].flagged) {
					noOfFlags++;
				}
			}

			if ((x >= 0 && x <= 7) && (y > 0 && y <= 7)) {

				if (button[x][y - 1].flagged) {
					noOfFlags++;
				}
			}

			if ((x >= 0 && x <= 7) && (y >= 0 && y < 7)) {

				if (button[x][y + 1].flagged) {
					noOfFlags++;
				}
			}

			if ((x >= 0 && x < 7) && (y >= 0 && y < 7)) {

				if (button[x + 1][y + 1].flagged) {
					noOfFlags++;
				}
			}

			if ((x > 0 && x <= 7) && (y > 0 && y <= 7)) {

				if (button[x - 1][y - 1].flagged) {
					noOfFlags++;
				}
			}

			if ((x > 0 && x <= 7) && (y >= 0 && y < 7)) {

				if (button[x - 1][y + 1].flagged) {
					noOfFlags++;
				}
			}

			if ((x >= 0 && x < 7) && (y > 0 && y <= 7)) {

				if (button[x + 1][y - 1].flagged) {
					noOfFlags++;
				}
			}

			if (noOfFlags >= button[x][y].value) {
				state1OpenedAction();
			} else if (noOfFlags < button[x][y].value) {

			}

		}

		public void state1OpenedAction() {
			if ((x > 0 && x <= 7) && (y >= 0 && y <= 7)) {
				if (!button[x - 1][y].flagged) {
					if (button[x - 1][y].value != 0) {
						button[x - 1][y].state = 1;
					}
					 if (button[x - 1][y].value != 9) {
						button[x - 1][y]
								.setGraphic(new ImageView(new Image("file:Resc/" + button[x - 1][y].value + ".png")));
						if(button[x-1][y].value == 0){
							button[x-1][y].openButtons();
						}
					} else if (button[x - 1][y].value == 9) {
						button[x - 1][y].mineFoundActivity();
						button[x - 1][y].setGraphic(new ImageView(new Image("file:Resc/mine-red.png")));
					}
				}
			}

			if ((x >= 0 && x < 7) && (y >= 0 && y <= 7)) {
				if (!button[x + 1][y].flagged) {
					if (button[x + 1][y].value != 0) {
						button[x + 1][y].state = 1;
					}
					if (button[x + 1][y].value != 9) {
						button[x + 1][y]
								.setGraphic(new ImageView(new Image("file:Resc/" + button[x + 1][y].value + ".png")));
						if(button[x+1][y].value == 0){
							button[x+1][y].openButtons();
						}

					} else if (button[x + 1][y].value == 9) {
						button[x + 1][y].mineFoundActivity();
						button[x + 1][y].setGraphic(new ImageView(new Image("file:Resc/mine-red.png")));
					}
					
				}
			}

			if ((x >= 0 && x <= 7) && (y > 0 && y <= 7)) {
				if (!button[x][y - 1].flagged) {
					if (button[x][y - 1].value != 0) {
						button[x][y - 1].state = 1;
					}
					if (button[x][y - 1].value != 9) {
						button[x][y - 1]
								.setGraphic(new ImageView(new Image("file:Resc/" + button[x][y - 1].value + ".png")));
						if(button[x][y - 1].value == 0){
							button[x][y - 1].openButtons();
						}
					} else if (button[x][y - 1].value == 9) {
						button[x][y - 1].mineFoundActivity();
						button[x][y - 1].setGraphic(new ImageView(new Image("file:Resc/mine-red.png")));
					}
					
				}
			}

			if ((x >= 0 && x <= 7) && (y >= 0 && y < 7)) {
				if (!button[x][y + 1].flagged) {
					if (button[x][y + 1].value != 0) {
						button[x][y + 1].state = 1;
					}
					 if (button[x][y + 1].value != 9) {
						button[x][y + 1]
								.setGraphic(new ImageView(new Image("file:Resc/" + button[x][y + 1].value + ".png")));
						if(button[x][y + 1].value == 0){
							button[x][y + 1].openButtons();
						}

					} else if (button[x][y + 1].value == 9) {
						button[x][y + 1].mineFoundActivity();
						button[x][y + 1].setGraphic(new ImageView(new Image("file:Resc/mine-red.png")));
					}
					
				}
			}

			if ((x >= 0 && x < 7) && (y >= 0 && y < 7)) {
				if (!button[x + 1][y + 1].flagged) {
					if (button[x + 1][y + 1].value != 0) {
						button[x + 1][y + 1].state = 1;
					}
	 
					if (button[x + 1][y + 1].value != 9) {
						button[x + 1][y + 1].setGraphic(
								new ImageView(new Image("file:Resc/" + button[x + 1][y + 1].value + ".png")));
						if(button[x + 1][y + 1].value == 0){
							button[x + 1][y + 1].openButtons();
						}
					} else if (button[x + 1][y + 1].value == 9) {
						button[x + 1][y + 1].mineFoundActivity();
						button[x + 1][y + 1].setGraphic(new ImageView(new Image("file:Resc/mine-red.png")));
					}
					
				}
			}

			if ((x > 0 && x <= 7) && (y > 0 && y <= 7)) {
				if (!button[x - 1][y - 1].flagged) {
					if (button[x - 1][y - 1].value != 0) {
						button[x - 1][y - 1].state = 1;
					}
					 
					if (button[x - 1][y - 1].value != 9) {
						button[x - 1][y - 1].setGraphic(
								new ImageView(new Image("file:Resc/" + button[x - 1][y - 1].value + ".png")));
						if(button[x - 1][y - 1].value == 0){
							button[x - 1][y - 1].openButtons();
						}
					} else if (button[x - 1][y - 1].value == 9) {
						button[x - 1][y - 1].mineFoundActivity();
						button[x - 1][y - 1].setGraphic(new ImageView(new Image("file:Resc/mine-red.png")));
					}
					
				}
			}

			if ((x > 0 && x <= 7) && (y >= 0 && y < 7)) {
				if (!button[x - 1][y + 1].flagged) {
					if (button[x - 1][y + 1].value != 0) {
						button[x - 1][y + 1].state = 1;
					}
					
					 if (button[x - 1][y + 1].value != 9) {
						button[x - 1][y + 1].setGraphic(
								new ImageView(new Image("file:Resc/" + button[x - 1][y + 1].value + ".png")));
						if(button[x - 1][y + 1].value == 0){
							button[x - 1][y + 1].openButtons();
						}
					} else if (button[x - 1][y + 1].value == 9) {
						button[x - 1][y + 1].mineFoundActivity();
						button[x - 1][y + 1].setGraphic(new ImageView(new Image("file:Resc/mine-red.png")));
					}
					
				}
			}

			if ((x >= 0 && x < 7) && (y > 0 && y <= 7)) {
				if (!button[x + 1][y - 1].flagged) {
					if (button[x + 1][y - 1].value != 0) {
						button[x + 1][y - 1].state = 1;
					}
					
					 if (button[x + 1][y - 1].value != 9) {
						button[x + 1][y - 1].setGraphic(
								new ImageView(new Image("file:Resc/" + button[x + 1][y - 1].value + ".png")));
						if(button[x + 1][y - 1].value == 0){
							button[x + 1][y - 1].openButtons();
						}
					} else if (button[x + 1][y - 1].value == 9) {
						button[x + 1][y - 1].mineFoundActivity();
						button[x + 1][y - 1].setGraphic(new ImageView(new Image("file:Resc/mine-red.png")));
					}
				}
			}
		}

		public void seeWins() {
			for (int i = 0; i < button.length; i++) {
				for (int j = 0; j < button.length; j++) {
					if (button[i][j].value == 9) {
						mineCountSimple++;
						if (button[i][j].state == 1) {
							countMines++;
						}
					} else if (button[i][j].value != 9 || button[i][j].value != 0) {
						nonMineCountSimple++;
						if (button[i][j].state >= 1) {
							countNonMines++;
						}
					}
				}
			}
			if (countNonMines == nonMineCountSimple && countMines == 0) {
				gameOver = true;
				set = true;
			}
		}

	}
}
