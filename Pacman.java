import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.media.AudioClip;
import java.net.URL;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.*;
import java.util.*;

public class Pacman extends Application implements EventHandler < InputEvent > {
 int ghostEaten = 0;
 URL resource = getClass().getResource("startSound.wav");
 AudioClip startSound = new AudioClip(getClass().getResource("startSound.wav").toString());
 int chompTime = 0;
 AudioClip deathPSound = new AudioClip(getClass().getResource("deathPSound.wav").toString());
 AudioClip chompSound = new AudioClip(getClass().getResource("chompSound.wav").toString());
 AudioClip deadGSound = new AudioClip(getClass().getResource("deadGSound.wav").toString());
 //AudioClip eatFSound = new AudioClip(getClass().getResource("eatFSound.wav").toString());
 int timeline = 0;
 boolean spacepressed = false;
 Food cherry = new Food(new Image("cherry.png"), "notonstage");
 int foodCounter = 0;
 Rectangle2D foodRect;
 GraphicsContext gc;
 Image maze;
 ArrayList < Rectangle2D > bounos = new ArrayList < Rectangle2D > ();
 int bounosTimer = 0;
 int bounosModeTimer = 0;
 Image pacman;
 Rectangle2D pacmanRect;
 int xSpeed;
 int ySpeed;
 int foodEaten = 0;
 int pointsFromFood = 500;
 int points = 0;
 int numberOfTimesFoodEaten = 0;
 ArrayList < Rectangle2D > regRec = new ArrayList < Rectangle2D > ();
 ArrayList < Boolean > regRecFill = new ArrayList < Boolean > ();
 String mode = "startscreen";
 int startscreenCounter = 0;
 int x = 213;
 int y = 264;
 AnimateObjects animate;
 AudioClip clip;
 Image startscreen;
 boolean canTravelUp;
 boolean intersectUpBound;
 ArrayList < Rectangle2D > topBounds = new ArrayList < Rectangle2D > ();

 boolean canTravelDown;
 boolean intersectDownBound;
 ArrayList < Rectangle2D > bottomBounds = new ArrayList < Rectangle2D > ();

 boolean canTravelLeft;
 boolean intersectLeftBound;
 ArrayList < Rectangle2D > leftBounds = new ArrayList < Rectangle2D > ();

 boolean canTravelRight;
 boolean intersectRightBound;
 ArrayList < Rectangle2D > rightBounds = new ArrayList < Rectangle2D > ();

 Image red;
 double currentRedX = 210;
 double currentRedY = 170;
 Rectangle2D redRect;
 boolean redIntersectRightBound;
 boolean redIntersectLeftBound;
 boolean redIntersectTopBound;
 boolean redIntersectBottomBound;
 boolean redCanTravelRight;
 boolean redCanTravelLeft = true;
 boolean redCanTravelUp;
 boolean redCanTravelDown;
 String redMode = "reg";

 Image yellow;
 double currentYellowX = 210;
 double currentYellowY = 170;
 Rectangle2D yellowRect;
 boolean yellowIntersectRightBound;
 boolean yellowIntersectLeftBound;
 boolean yellowIntersectTopBound;
 boolean yellowIntersectBottomBound;
 boolean yellowCanTravelRight;
 boolean yellowCanTravelLeft = true;
 boolean yellowCanTravelUp;
 boolean yellowCanTravelDown;
 String yellowMode = "reg";

 Image pink;
 double currentPinkX = 210;
 double currentPinkY = 170;
 Rectangle2D pinkRect;
 boolean pinkIntersectRightBound;
 boolean pinkIntersectLeftBound;
 boolean pinkIntersectTopBound;
 boolean pinkIntersectBottomBound;
 boolean pinkCanTravelRight = true;
 boolean pinkCanTravelLeft;
 boolean pinkCanTravelUp;
 boolean pinkCanTravelDown;
 String pinkMode = "reg";

 Image blue;
 double currentBlueX = 210;
 double currentBlueY = 170;
 Rectangle2D blueRect;
 boolean blueIntersectRightBound;
 boolean blueIntersectLeftBound;
 boolean blueIntersectTopBound;
 boolean blueIntersectBottomBound;
 boolean blueCanTravelRight = true;
 boolean blueCanTravelLeft;
 boolean blueCanTravelUp;
 boolean blueCanTravelDown;
 String blueMode = "reg";

 double ghostSpeed = 1.5;

 boolean gameOver = false;
 int starttextCounter = 0;
 public static void main(String[] args) {
  launch();
 }
 public void start(Stage stage) {
  //URL resource = getClass().getResource("test.wav");
  //clip = new AudioClip(resource.toString());
  //clip.play();
  stage.setTitle("Pacman");
  Group root = new Group();
  Canvas canvas = new Canvas(448, 536);
  root.getChildren().add(canvas);
  Scene scene = new Scene(root);
  scene.addEventHandler(KeyEvent.KEY_PRESSED, this);
  scene.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
  stage.setScene(scene);
  gc = canvas.getGraphicsContext2D();

  maze = new Image("maze.png");
  pacman = new Image("pacmanRight.png");
  startscreen = new Image("startscreen.png");
  red = new Image("red.png");
  pink = new Image("pink.png");
  blue = new Image("blue.png");
  yellow = new Image("yellow.png");

  topBounds.add(new Rectangle2D(6 + 4, 6, 212 - 8, 2));
  topBounds.add(new Rectangle2D(40 + 4, 70, 48 - 8, 2));
  topBounds.add(new Rectangle2D(120 + 4, 70, 64 - 8, 2));
  topBounds.add(new Rectangle2D(216 + 4, 70, 8, 2));
  topBounds.add(new Rectangle2D(40 + 4, 118, 48 - 8, 2));
  topBounds.add(new Rectangle2D(168 + 4, 118, 50 - 8, 2));
  topBounds.add(new Rectangle2D(134 + 4, 166, 50 - 8, 2));
  topBounds.add(new Rectangle2D(216 + 4, 166, 8, 2));
  topBounds.add(new Rectangle2D(120 + 4, 212, 16 - 8, 2));
  topBounds.add(new Rectangle2D(216 + 4, 70, 8, 2));
  topBounds.add(new Rectangle2D(0 + 4, 214, 88 - 8, 2));
  topBounds.add(new Rectangle2D(168 + 4, 262, 55 - 8, 2));
  topBounds.add(new Rectangle2D(6 + 4, 310, 82 - 8, 2));
  topBounds.add(new Rectangle2D(120 + 4, 310, 16 - 8, 2));
  topBounds.add(new Rectangle2D(168 + 4, 310, 55 - 8, 2));
  topBounds.add(new Rectangle2D(40 + 4, 358, 34 - 8, 2));
  topBounds.add(new Rectangle2D(120 + 4, 358, 64 - 8, 2));
  topBounds.add(new Rectangle2D(216 + 4, 357, 8, 2));
  topBounds.add(new Rectangle2D(6 + 4, 406, 34 - 8, 2));
  topBounds.add(new Rectangle2D(71 + 4, 406, 16 - 8, 2));
  topBounds.add(new Rectangle2D(168 + 4, 406, 55 - 8, 2));
  topBounds.add(new Rectangle2D(40 + 4, 454, 145 - 8, 2));
  topBounds.add(new Rectangle2D(216 + 4, 454, 8, 2));

  int topBoundsCurrentSize = topBounds.size();
  for (int x = 0; x < topBoundsCurrentSize; x++) {
   topBounds.add(new Rectangle2D(((224 - ((topBounds.get(x)).getMinX())) + 224) - (topBounds.get(x)).getWidth(), (topBounds.get(x)).getMinY(), (topBounds.get(x)).getWidth(), (topBounds.get(x)).getHeight()));
  }

  bottomBounds.add(new Rectangle2D(40 + 4, 40, 48 - 8, 2));
  bottomBounds.add(new Rectangle2D(120 + 4, 40, 64 - 8, 2));
  bottomBounds.add(new Rectangle2D(40 + 4, 104, 48 - 8, 2));
  bottomBounds.add(new Rectangle2D(120 + 4, 104, 16 - 8, 2));
  bottomBounds.add(new Rectangle2D(168 + 4, 104, 55 - 8, 2));
  bottomBounds.add(new Rectangle2D(6 + 4, 152, 82 - 8, 2));
  bottomBounds.add(new Rectangle2D(134 + 4, 152, 50 - 8, 2));
  bottomBounds.add(new Rectangle2D(168 + 4, 200, 40 - 8, 2));
  bottomBounds.add(new Rectangle2D(6 + 4, 152, 82 - 8, 2));
  bottomBounds.add(new Rectangle2D(0 + 4, 248, 88 - 8, 2));
  bottomBounds.add(new Rectangle2D(120 + 4, 248, 16 - 8, 2));
  bottomBounds.add(new Rectangle2D(168 + 4, 296, 55 - 8, 2));
  bottomBounds.add(new Rectangle2D(40 + 4, 344, 48 - 8, 2));
  bottomBounds.add(new Rectangle2D(120 + 4, 344, 64 - 8, 2));
  bottomBounds.add(new Rectangle2D(6 + 4, 392, 34 - 8, 2));
  bottomBounds.add(new Rectangle2D(120 + 4, 392, 16 - 8, 2));
  bottomBounds.add(new Rectangle2D(168 + 4, 392, 55 - 8, 2));
  bottomBounds.add(new Rectangle2D(40 + 4, 440, 145 - 8, 2));
  bottomBounds.add(new Rectangle2D(6 + 4, 488, 218 - 8, 2));

  bottomBounds.add(new Rectangle2D(203 + 4, 200, 46 - 8, 2));

  int bottomBoundsCurrentSize = bottomBounds.size();
  for (int x = 0; x < bottomBoundsCurrentSize; x++) {
   bottomBounds.add(new Rectangle2D(((224 - ((bottomBounds.get(x)).getMinX())) + 224) - (bottomBounds.get(x)).getWidth(), (bottomBounds.get(x)).getMinY(), (bottomBounds.get(x)).getWidth(), (bottomBounds.get(x)).getHeight()));
  }
  leftBounds.add(new Rectangle2D(7, 6 + 4, 2, 143 - 8));
  leftBounds.add(new Rectangle2D(86, 40 + 4, 2, 32 - 8));
  leftBounds.add(new Rectangle2D(86, 40 + 4, 2, 32 - 8));
  leftBounds.add(new Rectangle2D(182, 40 + 4, 2, 32 - 8));
  leftBounds.add(new Rectangle2D(86, 104 + 4, 2, 16 - 8));
  leftBounds.add(new Rectangle2D(134, 104 + 4, 2, 113 - 8));
  leftBounds.add(new Rectangle2D(86, 152 + 4, 2, 64 - 8));
  leftBounds.add(new Rectangle2D(182, 153 + 4, 2, 16 - 8));
  leftBounds.add(new Rectangle2D(86, 248 + 4, 2, 64 - 8));
  leftBounds.add(new Rectangle2D(134, 248 + 4, 2, 64 - 8));
  leftBounds.add(new Rectangle2D(6, 314 + 4, 2, 190 - 8));
  leftBounds.add(new Rectangle2D(86, 344 + 4, 2, 64 - 8));
  leftBounds.add(new Rectangle2D(182, 344 + 4, 2, 16 - 8));
  leftBounds.add(new Rectangle2D(38, 392 + 4, 2, 16 - 8));
  leftBounds.add(new Rectangle2D(134, 392 + 4, 2, 50 - 8));
  leftBounds.add(new Rectangle2D(182, 440 + 4, 2, 16 - 8));
  //leftBounds.add(new Rectangle2D(6,309+4,2,180-8));

  int leftBoundsCurrentSize = leftBounds.size();
  for (int x = 0; x < leftBoundsCurrentSize; x++) {
   rightBounds.add(new Rectangle2D(((224 - ((leftBounds.get(x)).getMinX())) + 224) - (leftBounds.get(x)).getWidth(), (leftBounds.get(x)).getMinY(), (leftBounds.get(x)).getWidth(), (leftBounds.get(x)).getHeight()));
  }
  rightBounds.add(new Rectangle2D(40, 40 + 4, 2, 32 - 8));
  rightBounds.add(new Rectangle2D(120, 40 + 4, 2, 32 - 8));
  rightBounds.add(new Rectangle2D(216, 7 + 4, 2, 66 - 8));
  rightBounds.add(new Rectangle2D(40, 104 + 4, 2, 16 - 8));
  rightBounds.add(new Rectangle2D(168, 104 + 4, 2, 16 - 8));
  rightBounds.add(new Rectangle2D(120, 113 + 4, 2, 16 - 8));
  rightBounds.add(new Rectangle2D(216, 118 + 4, 2, 50 - 8));
  rightBounds.add(new Rectangle2D(168, 200 + 4, 2, 64 - 8));
  rightBounds.add(new Rectangle2D(120, 248 + 4, 2, 64 - 8));
  rightBounds.add(new Rectangle2D(168, 296 + 4, 2, 16 - 8));
  rightBounds.add(new Rectangle2D(216, 310 + 4, 2, 50 - 8));
  rightBounds.add(new Rectangle2D(122, 344 + 4, 2, 16 - 8));
  rightBounds.add(new Rectangle2D(40, 344 + 4, 2, 16 - 8));
  rightBounds.add(new Rectangle2D(40, 440 + 4, 2, 16 - 8));
  rightBounds.add(new Rectangle2D(168, 392 + 4, 2, 16 - 8));
  rightBounds.add(new Rectangle2D(120, 392 + 4, 2, 50 - 8));
  rightBounds.add(new Rectangle2D(216, 406 + 4, 2, 50 - 8));
  rightBounds.add(new Rectangle2D(120, 104 + 4, 2, 112 - 8));
  rightBounds.add(new Rectangle2D(72, 358 + 4, 2, 50 - 8));

  //int rightBoundsCurrentSize = rightBounds.size();
  for (int x = leftBoundsCurrentSize; x < rightBounds.size(); x++) {
   leftBounds.add(new Rectangle2D(((224 - ((rightBounds.get(x)).getMinX())) + 224) - (rightBounds.get(x)).getWidth(), (rightBounds.get(x)).getMinY(), (rightBounds.get(x)).getWidth(), (rightBounds.get(x)).getHeight()));
  }
  regRec.add(new Rectangle2D(15, 45, 20, 20));
  regRec.add(new Rectangle2D(415, 45, 20, 20));
  regRec.add(new Rectangle2D(15, 367, 20, 20));
  regRec.add(new Rectangle2D(415, 367, 20, 20));
  for (int x = 21; x <= 213 - 16; x += 16)
   regRec.add(new Rectangle2D(x, 21, 6, 6));
  for (int x = 21; x <= 229; x += 16)
   regRec.add(new Rectangle2D(x, 85, 6, 6));
  for (int x = 21; x <= 101; x += 16)
   regRec.add(new Rectangle2D(x, 133, 6, 6));
  for (int x = 149; x <= 213; x += 16)
   regRec.add(new Rectangle2D(x, 133, 6, 6));
  for (int y = 37; y <= 149; y += 16)
   regRec.add(new Rectangle2D(21, y, 6, 6));
  for (int y = 37; y <= 421; y += 16)
   regRec.add(new Rectangle2D(101, y, 6, 6));
  for (int y = 37; y <= 85; y += 16)
   regRec.add(new Rectangle2D(197, y, 6, 6));
  for (int y = 101; y <= 117; y += 16)
   regRec.add(new Rectangle2D(149, y, 6, 6));
  for (int x = 21; x <= 213; x += 16)
   regRec.add(new Rectangle2D(x, 325, 6, 6));
  for (int x = 21; x <= 229; x += 16)
   regRec.add(new Rectangle2D(x, 469, 6, 6));
  for (int x = 37; x <= 53; x += 16)
   regRec.add(new Rectangle2D(x, 373, 6, 6));
  for (int x = 117; x <= 213; x += 16)
   regRec.add(new Rectangle2D(x, 373, 6, 6));
  for (int x = 21; x <= 117; x += 16)
   regRec.add(new Rectangle2D(x, 421, 6, 6));
  for (int x = 149; x <= 149 + (16 * 3); x += 16)
   regRec.add(new Rectangle2D(x, 421, 6, 6));
  for (int y = 341; y <= 341 + (16 * 2); y += 16)
   regRec.add(new Rectangle2D(21, y, 6, 6));
  for (int y = 341; y <= 341 + (16 * 2); y += 16)
   regRec.add(new Rectangle2D(197, y, 6, 6));
  for (int y = 437; y <= 437 + (16 * 2); y += 16)
   regRec.add(new Rectangle2D(21, y, 6, 6));
  for (int y = 389; y <= 389 + (16 * 2); y += 16)
   regRec.add(new Rectangle2D(53, y, 6, 6));
  for (int y = 389; y <= 389 + (16 * 2); y += 16)
   regRec.add(new Rectangle2D(149, y, 6, 6));
  for (int y = 437; y <= 437 + (16 * 1); y += 16)
   regRec.add(new Rectangle2D(197, y, 6, 6));
  int regRecHalfSize = regRec.size();
  for (int x = 0; x < regRecHalfSize; x++)
   regRec.add(new Rectangle2D(((224 - ((regRec.get(x)).getMinX())) + 224) - 6, (regRec.get(x)).getMinY(), 6, 6));


  for (int x = 0; x < regRec.size(); x++)
   regRecFill.add(false);

  bounos.add(new Rectangle2D(15, 45, 20, 20));
  bounos.add(new Rectangle2D(415, 45, 20, 20));
  bounos.add(new Rectangle2D(15, 367, 20, 20));
  bounos.add(new Rectangle2D(415, 367, 20, 20));

  animate = new AnimateObjects();
  animate.start();
  stage.show();

 }
 public void handle(final InputEvent event) {
  if (event instanceof KeyEvent) {
   if (((KeyEvent) event).getCode() == KeyCode.LEFT) {
    xSpeed = -2;
    ySpeed = 0;
   }

   if ((((KeyEvent) event).getCode() == KeyCode.UP)) {
    ySpeed = -2;
    xSpeed = 0;
   }

   if (((KeyEvent) event).getCode() == KeyCode.RIGHT) {
    xSpeed = 2;
    ySpeed = 0;
   }


   if (((KeyEvent) event).getCode() == KeyCode.DOWN) {
    ySpeed = 2;
    xSpeed = 0;
   }
   if (((KeyEvent) event).getCode() == KeyCode.ENTER && mode.equals("startscreen")) {
    mode = "reg";

   }
   if (((KeyEvent) event).getCode() == KeyCode.SPACE && gameOver == true) {
    mode = "reg";
    spacepressed = true;

   }

  }

  if (event instanceof MouseEvent) {
   System.out.println(((MouseEvent) event).getX());
   System.out.println(((MouseEvent) event).getY());
  }
 }
 public class AnimateObjects extends AnimationTimer {
  public void handle(long now) {

   if (mode.equals("startscreen")) {

    startscreenCounter++;
    gc.drawImage(startscreen, 0, 0);
    //gc.drawImage(new Image("black.png"),0,496);
    //if(startscreenCounter%30 > 10)
    //		gc.drawImage(new Image("black.png"),0,350);
    //Image death = new Image("cherry.png");
    //							gc.drawImage(death,0,0);

   } else if (gameOver == false && mode.equals("reg") || mode.equals("bonous")) {
    gc.drawImage(maze, 0, 0);
    //gc.drawImage(new Image("black.png"),0,496);
    gc.setFill(Color.BLACK);

    pacmanRect = new Rectangle2D(x, y, pacman.getWidth(), pacman.getHeight());
    //gc.fillRect(x+4,y+3,pacman.getWidth()-5,pacman.getHeight()-3);
    bounosModeTimer++;
    bounosTimer++;
    if (starttextCounter >= 0 && starttextCounter <= 180) {
     gc.drawImage(new Image("starttext.png"), 177, 225);
     starttextCounter++;
    }
    if (starttextCounter == 1)
     startSound.play();
    if (bounosTimer % 10 < 5) {
     gc.fillRect(15, 45, 20, 20);
     gc.fillRect(415, 45, 20, 20);
     gc.fillRect(15, 367, 20, 20);
     gc.fillRect(415, 367, 20, 20);
    }
    for (int x = 0; x < bounos.size(); x++) {
     if (bounos.get(x).intersects(pacmanRect)) {
      bounos.remove(x);
      mode = "bonous";
      redMode = "bonous";
      yellowMode = "bonous";
      blueMode = "bonous";
      pinkMode = "bonous";
      break;
     } else if (bounosModeTimer > 600) {
      mode = "reg";
      redMode = "reg";
      yellowMode = "reg";
      blueMode = "reg";
      pinkMode = "reg";
     }
    }
    if (mode.equals("bonous") && bounosModeTimer < 600) {
     ghostSpeed = 1;
    } else {
     bounosModeTimer = 0;
     mode = "reg";
     redMode = "reg";
     yellowMode = "reg";
     blueMode = "reg";
     pinkMode = "reg";
     ghostSpeed = 2;
    }
    for (int x = 0; x < regRec.size(); x++) {
     if (regRec.get(x).intersects(pacmanRect)) {
      regRecFill.set(x, true);

     }
    }
    for (int x = 0; x < regRecFill.size(); x++) {
     if (regRecFill.get(x) == true) {
      points += 10;
      gc.fillRect((regRec.get(x)).getMinX(), (regRec.get(x)).getMinY(), regRec.get(x).getWidth(), regRec.get(x).getHeight());

     }
    }
    chompTime++;
    if (chompTime % 60 == 0) {
     chompSound.play();
     chompTime = 0;
    }

    for (int x = 0; x < regRecFill.size(); x++) {
     if (regRecFill.get(x) == true)
      foodEaten++;
    }

    if (foodEaten % 50 == 0 && foodEaten != 0) {
     cherry.setState("onstage");
     foodRect = new Rectangle2D(cherry.getX(), cherry.getY(), cherry.getImage().getWidth(), cherry.getImage().getHeight());
    }
    if (cherry.getState().equals("onstage") && foodCounter < 1000 && !foodRect.intersects(pacmanRect)) {
     gc.drawImage(new Image("cherry.png"), cherry.getX(), cherry.getY());
     foodCounter++;

    } else if (cherry.getState().equals("onstage") && foodRect.intersects(pacmanRect)) {
     //eatFSound.play();
     cherry.setState("notonstage");
     numberOfTimesFoodEaten++;
     gc.setFill(Color.YELLOW); //Fills the text in yellow
     gc.setStroke(Color.BLACK); //Changes the outline the black
     gc.setLineWidth(1); //How big the black lines will be
     Font font = Font.font("Arial", FontWeight.BOLD, 20);
     gc.setFont(font);
     gc.fillText("500", cherry.getX(), cherry.getY()); //draws the yellow part of the text
     gc.strokeText("500", cherry.getX(), cherry.getY()); //draws the outline part of the text
    } else {
     cherry.setState("notonstage");
    }
    if(foodEaten == regRec.size()){
		gameOver = true;
	}
    points = foodEaten * 10 + pointsFromFood * numberOfTimesFoodEaten + 200 * ghostEaten;
    gc.setFill(Color.YELLOW); //Fills the text in yellow
    gc.setStroke(Color.BLACK); //Changes the outline the black
    gc.setLineWidth(1); //How big the black lines will be
    Font font = Font.font("Arial", FontWeight.BOLD, 25);
    gc.setFont(font);
    gc.setFill(Color.BLACK);
    gc.fillRect(10, 500, 100, 100);
    gc.setFill(Color.YELLOW);
    gc.fillText("" + points, 10, 520); //draws the yellow part of the text
    gc.strokeText("" + points, 10, 520); //draws the outline part of the text
    foodEaten = 0;
    if (numberOfTimesFoodEaten == 1)
     gc.drawImage(new Image("cherry.png"), 410, 505);

    for (int x = 0; x < topBounds.size(); x++) {
     if (topBounds.get(x).intersects(pacmanRect)) {
      intersectUpBound = true;
      break;
     } else
      intersectUpBound = false;
    }
    if (intersectUpBound) {
     canTravelUp = false;
     if (ySpeed == -2)
      ySpeed = 0;
    } else {
     canTravelUp = true;
     //ySpeed = -2;
    }

    //
    //
    for (int x = 0; x < bottomBounds.size(); x++) {
     if (bottomBounds.get(x).intersects(pacmanRect)) {
      intersectDownBound = true;
      break;
     } else
      intersectDownBound = false;
    }
    if (intersectDownBound) {
     canTravelDown = false;
     if (ySpeed == 2)
      ySpeed = 0;
    } else {
     canTravelDown = true;
     //ySpeed = 2;
    }
    //
    for (int x = 0; x < leftBounds.size(); x++) {
     if (leftBounds.get(x).intersects(pacmanRect)) {
      intersectLeftBound = true;
      break;
     } else
      intersectLeftBound = false;
    }
    if (intersectLeftBound) {
     canTravelLeft = false;
     if (xSpeed == -2)
      xSpeed = 0;
    } else {
     canTravelLeft = true;
     //xSpeed = -2;
    }
    //
    //
    for (int x = 0; x < rightBounds.size(); x++) {
     if (rightBounds.get(x).intersects(pacmanRect)) {
      intersectRightBound = true;
      break;
     } else
      intersectRightBound = false;
    }
    if (intersectRightBound) {
     canTravelRight = false;
     if (xSpeed == 2)
      xSpeed = 0;
    } else {
     canTravelRight = true;
     //xSpeed = 2;
    }
    //

    if (new Rectangle2D(0, 216, 1, 33).intersects(pacmanRect) && xSpeed == -2) {
     x = 446;
     System.out.println("kk");
    } else if (new Rectangle2D(446, 216, 1, 33).intersects(pacmanRect) && x < 445 && xSpeed == 2) {
     x = 0;
    }
    x += xSpeed;
    y += ySpeed;

    if (xSpeed == -2)
     pacman = new Image("pacmanLeft.png");
    else if (ySpeed == -2)
     pacman = new Image("pacmanUp.png");
    else if (ySpeed == 2)
     pacman = new Image("pacmanDown.png");
    else if (xSpeed == 2)
     pacman = new Image("pacmanRight.png");
    gc.drawImage(pacman, x, y);


    makeRedMove();
    makePinkMove();
    makeBlueMove();
    makeYellowMove();

    if (pacmanRect.intersects(redRect) && redMode.equals("reg")) {
     deathPSound.play();
     //gc.drawImage(new Image("pacmanDeath.gif"),x,y);

     gameOver = true;
     timeline = 1;
    } else if (pacmanRect.intersects(redRect) && redMode.equals("bonous")) {
     deadGSound.play();
     ghostEaten++;
     points += Math.pow(200, ghostEaten);
     currentRedX = 210;
     currentRedY = 170;
     redMode = "reg";
    }
    if (pacmanRect.intersects(pinkRect) && pinkMode.equals("reg")) {
     deathPSound.play();
     //gc.drawImage(new Image("pacmanDeath.gif"),x,y);

     gameOver = true;
     timeline = 1;

    } else if (pacmanRect.intersects(pinkRect) && pinkMode.equals("bonous")) {
     deadGSound.play();
     ghostEaten++;
     points += Math.pow(200, ghostEaten);
     currentPinkX = 210;
     currentPinkY = 170;
     pinkMode = "reg";

    }

    if (pacmanRect.intersects(yellowRect) && yellowMode.equals("reg")) {
     deathPSound.play();
     //gc.drawImage(new Image("pacmanDeath.gif"),x,y);

     gameOver = true;
     timeline = 1;

    } else if (pacmanRect.intersects(yellowRect) && yellowMode.equals("bonous")) {
     deadGSound.play();
     ghostEaten++;
     points += Math.pow(200, ghostEaten);
     currentYellowX = 210;
     currentYellowY = 170;
     yellowMode = "reg";
    }
    if (pacmanRect.intersects(blueRect) && blueMode.equals("reg")) {
     deathPSound.play();
     //gc.drawImage(new Image("pacmanDeath.gif"),x,y);

     gameOver = true;
     timeline = 1;

    } else if (pacmanRect.intersects(blueRect) && blueMode.equals("bonous")) {
     deadGSound.play();
     ghostEaten++;
     points += Math.pow(200, ghostEaten);
     currentBlueX = 210;
     currentBlueY = 170;
     blueMode = "reg";
    }

   } else if (gameOver == true && mode.equals("reg")) {
    timeline++;
    //gc.drawImage(new Image("death.gif"),0,0);
    if (timeline > 60 && timeline <= 150) {
     gc.drawImage(new Image("gameOver.png"), 175, 225);
    } else if (timeline > 150) {
     gc.setFill(Color.YELLOW); //Fills the text in yellow
     gc.setStroke(Color.BLACK); //Changes the outline the black
     gc.setLineWidth(1); //How big the black lines will be
     Font font = Font.font("Arial", FontWeight.BOLD, 25);
     gc.setFont(font);
     gc.fillText("SPACE TO RESTART", 100, 525); //draws the yellow part of the text
     gc.strokeText("SPACE TO RESTART", 100, 525); //draws the outline part of the text
     timeline++;
    }
    if (spacepressed) {
     mode = "reg";
     gameOver = false;
     x = 213;
     y = 264;
     currentRedX = 143;
     currentRedY = 170;
     currentYellowX = 143;
     currentYellowY = 170;
     currentBlueX = 281;
     currentBlueY = 170;
     currentPinkX = 283;
     currentPinkY = 170;
     spacepressed = false;
     timeline = 0;
     points = 0;
     ghostEaten = 0;
     gc.setFill(Color.BLACK);
     gc.fillRect(100, 500, 400, 525);
     ySpeed = 0;
     xSpeed = 0;
     numberOfTimesFoodEaten = 0;
     chompTime = 0;
     starttextCounter = 0;
     regRec.clear();
     regRecFill.clear();

     regRec.add(new Rectangle2D(15, 45, 20, 20));
     regRec.add(new Rectangle2D(415, 45, 20, 20));
     regRec.add(new Rectangle2D(15, 367, 20, 20));
     regRec.add(new Rectangle2D(415, 367, 20, 20));
     for (int x = 21; x <= 213 - 16; x += 16)
      regRec.add(new Rectangle2D(x, 21, 6, 6));
     for (int x = 21; x <= 229; x += 16)
      regRec.add(new Rectangle2D(x, 85, 6, 6));
     for (int x = 21; x <= 101; x += 16)
      regRec.add(new Rectangle2D(x, 133, 6, 6));
     for (int x = 149; x <= 213; x += 16)
      regRec.add(new Rectangle2D(x, 133, 6, 6));
     for (int y = 37; y <= 149; y += 16)
      regRec.add(new Rectangle2D(21, y, 6, 6));
     for (int y = 37; y <= 421; y += 16)
      regRec.add(new Rectangle2D(101, y, 6, 6));
     for (int y = 37; y <= 85; y += 16)
      regRec.add(new Rectangle2D(197, y, 6, 6));
     for (int y = 101; y <= 117; y += 16)
      regRec.add(new Rectangle2D(149, y, 6, 6));
     for (int x = 21; x <= 213; x += 16)
      regRec.add(new Rectangle2D(x, 325, 6, 6));
     for (int x = 21; x <= 229; x += 16)
      regRec.add(new Rectangle2D(x, 469, 6, 6));
     for (int x = 37; x <= 53; x += 16)
      regRec.add(new Rectangle2D(x, 373, 6, 6));
     for (int x = 117; x <= 213; x += 16)
      regRec.add(new Rectangle2D(x, 373, 6, 6));
     for (int x = 21; x <= 117; x += 16)
      regRec.add(new Rectangle2D(x, 421, 6, 6));
     for (int x = 149; x <= 149 + (16 * 3); x += 16)
      regRec.add(new Rectangle2D(x, 421, 6, 6));
     for (int y = 341; y <= 341 + (16 * 2); y += 16)
      regRec.add(new Rectangle2D(21, y, 6, 6));
     for (int y = 341; y <= 341 + (16 * 2); y += 16)
      regRec.add(new Rectangle2D(197, y, 6, 6));
     for (int y = 437; y <= 437 + (16 * 2); y += 16)
      regRec.add(new Rectangle2D(21, y, 6, 6));
     for (int y = 389; y <= 389 + (16 * 2); y += 16)
      regRec.add(new Rectangle2D(53, y, 6, 6));
     for (int y = 389; y <= 389 + (16 * 2); y += 16)
      regRec.add(new Rectangle2D(149, y, 6, 6));
     for (int y = 437; y <= 437 + (16 * 1); y += 16)
      regRec.add(new Rectangle2D(197, y, 6, 6));
     int regRecHalfSize = regRec.size();
     for (int x = 0; x < regRecHalfSize; x++)
      regRec.add(new Rectangle2D(((224 - ((regRec.get(x)).getMinX())) + 224) - 6, (regRec.get(x)).getMinY(), 6, 6));


     for (int x = 0; x < regRec.size(); x++)
      regRecFill.add(false);
     bounos.clear();
     bounos.add(new Rectangle2D(15, 45, 20, 20));
     bounos.add(new Rectangle2D(415, 45, 20, 20));
     bounos.add(new Rectangle2D(15, 367, 20, 20));
     bounos.add(new Rectangle2D(415, 367, 20, 20));
    }
   }
   /*
   gc.setFill(Color.RED);
   for(int x = 0; x < rightBounds.size(); x++){
   	gc.fillRect(rightBounds.get(x).getMinX(),rightBounds.get(x).getMinY(),rightBounds.get(x).getWidth(),rightBounds.get(x).getHeight());
   }
   for(int x = 0; x < leftBounds.size(); x++){
   	gc.fillRect(leftBounds.get(x).getMinX(),leftBounds.get(x).getMinY(),leftBounds.get(x).getWidth(),leftBounds.get(x).getHeight());
   }
   for(int x = 0; x < topBounds.size(); x++){
   	gc.fillRect(topBounds.get(x).getMinX(),topBounds.get(x).getMinY(),topBounds.get(x).getWidth(),topBounds.get(x).getHeight());
   }
   for(int x = 0; x < bottomBounds.size(); x++){
   	gc.fillRect(bottomBounds.get(x).getMinX(),bottomBounds.get(x).getMinY(),bottomBounds.get(x).getWidth(),bottomBounds.get(x).getHeight());
   }

   */


   //
   /*if(x > -50){
   	//x++;
   	if(x >= 600)
   		x = -180;
   	gc.drawImage(trooper,180+x,100+y);
   	Rectangle2D rect1 = new Rectangle2D( 400,100,100,100 );
   	gc.fillRect(400,100,100,100);
   	Rectangle2D rect2 = new Rectangle2D( 180+x,100,trooper.getWidth(),trooper.getHeight() );
   	if (rect1.intersects(rect2)){
   		canTravelUp = true;
   		System.out.println("HIT");
   	}
   	else
   		canTravelUp = false;

   }
   else{
   	gc.setFill( Color.YELLOW); //Fills the text in yellow
   	gc.setStroke( Color.BLACK ); //Changes the outline the black
   	gc.setLineWidth(1); //How big the black lines will be
   	Font font = Font.font( "Arial", FontWeight.NORMAL, 48 );
   	gc.setFont( font );
   	gc.fillText( "Game Over", 100, 50 ); //draws the yellow part of the text
   	gc.strokeText( "Game Over", 100, 50 ); //draws the outline part of the text
   }*/
  }
  public void makeRedMove() {
   for (int x = 0; x < rightBounds.size(); x++) {
    if (rightBounds.get(x).intersects(redRect)) {
     redIntersectRightBound = true;
     break;
    } else
     redIntersectRightBound = false;
   }
   if (redIntersectRightBound) {
    int rand = (int)(Math.random() * 2) + 1;
    if (rand == 1) {
     redCanTravelUp = true;
     redCanTravelDown = false;
     redCanTravelRight = false;
     redCanTravelLeft = false;
     currentRedX--;
    } else {
     redCanTravelUp = false;
     redCanTravelRight = false;
     redCanTravelDown = true;
     redCanTravelLeft = false;
     currentRedX--;
    }
   }
   for (int x = 0; x < leftBounds.size(); x++) {
    if (leftBounds.get(x).intersects(redRect)) {
     redIntersectLeftBound = true;
     break;
    } else
     redIntersectLeftBound = false;
   }
   if (redIntersectLeftBound) {
    int rand = (int)(Math.random() * 2) + 1;
    if (rand == 1) {
     redCanTravelUp = true;
     redCanTravelDown = false;
     redCanTravelRight = false;
     redCanTravelLeft = false;
     currentRedX++;
    } else {
     redCanTravelUp = false;
     redCanTravelRight = false;
     redCanTravelDown = true;
     redCanTravelLeft = false;
     currentRedX++;
    }
   }

   for (int x = 0; x < topBounds.size(); x++) {
    if (topBounds.get(x).intersects(redRect)) {
     redIntersectTopBound = true;
     break;
    } else
     redIntersectTopBound = false;
   }
   if (redIntersectTopBound) {
    int rand = (int)(Math.random() * 2) + 1;
    if (rand == 1) {
     redCanTravelUp = false;
     redCanTravelDown = false;
     redCanTravelRight = true;
     redCanTravelLeft = false;
     currentRedY++;
    } else {
     redCanTravelUp = false;
     redCanTravelRight = false;
     redCanTravelDown = false;
     redCanTravelLeft = true;
     currentRedY++;
    }
   }
   for (int x = 0; x < bottomBounds.size(); x++) {
    if (bottomBounds.get(x).intersects(redRect)) {
     redIntersectBottomBound = true;
     break;
    } else
     redIntersectBottomBound = false;
   }
   if (redIntersectBottomBound) {
    int rand = (int)(Math.random() * 2) + 1;
    if (rand == 1) {
     redCanTravelUp = false;
     redCanTravelDown = false;
     redCanTravelRight = true;
     redCanTravelLeft = false;
     currentRedY--;
    } else {
     redCanTravelUp = false;
     redCanTravelRight = false;
     redCanTravelDown = false;
     redCanTravelLeft = true;
     currentRedY--;
    }
   }
   if (redCanTravelUp)
    currentRedY -= ghostSpeed;
   else if (redCanTravelDown)
    currentRedY += ghostSpeed;
   else if (redCanTravelLeft)
    currentRedX -= ghostSpeed;
   else if (redCanTravelRight)
    currentRedX += ghostSpeed;
   redRect = new Rectangle2D(currentRedX, currentRedY, red.getWidth(), red.getHeight());
   if (redMode.equals("bonous"))
    red = new Image("hollow.png");
   else if (redMode.equals("reg"))
    red = new Image("red.png");
   gc.drawImage(red, currentRedX, currentRedY);

  }
  public void makePinkMove() {
   for (int x = 0; x < rightBounds.size(); x++) {
    if (rightBounds.get(x).intersects(pinkRect)) {
     pinkIntersectRightBound = true;
     break;
    } else
     pinkIntersectRightBound = false;
   }
   if (pinkIntersectRightBound) {
    int rand = (int)(Math.random() * 2) + 1;
    if (rand == 1) {
     pinkCanTravelUp = true;
     pinkCanTravelDown = false;
     pinkCanTravelRight = false;
     pinkCanTravelLeft = false;
     currentPinkX--;
    } else {
     pinkCanTravelUp = false;
     pinkCanTravelRight = false;
     pinkCanTravelDown = true;
     pinkCanTravelLeft = false;
     currentPinkX--;
    }
   }
   for (int x = 0; x < leftBounds.size(); x++) {
    if (leftBounds.get(x).intersects(pinkRect)) {
     pinkIntersectLeftBound = true;
     break;
    } else
     pinkIntersectLeftBound = false;
   }
   if (pinkIntersectLeftBound) {
    int rand = (int)(Math.random() * 2) + 1;
    if (rand == 1) {
     pinkCanTravelUp = true;
     pinkCanTravelDown = false;
     pinkCanTravelRight = false;
     pinkCanTravelLeft = false;
     currentPinkX++;
    } else {
     pinkCanTravelUp = false;
     pinkCanTravelRight = false;
     pinkCanTravelDown = true;
     pinkCanTravelLeft = false;
     currentPinkX++;
    }
   }

   for (int x = 0; x < topBounds.size(); x++) {
    if (topBounds.get(x).intersects(pinkRect)) {
     pinkIntersectTopBound = true;
     break;
    } else
     pinkIntersectTopBound = false;
   }
   if (pinkIntersectTopBound) {
    int rand = (int)(Math.random() * 2) + 1;
    if (rand == 1) {
     pinkCanTravelUp = false;
     pinkCanTravelDown = false;
     pinkCanTravelRight = true;
     pinkCanTravelLeft = false;
     currentPinkY++;
    } else {
     pinkCanTravelUp = false;
     pinkCanTravelRight = false;
     pinkCanTravelDown = false;
     pinkCanTravelLeft = true;
     currentPinkY++;
    }
   }
   for (int x = 0; x < bottomBounds.size(); x++) {
    if (bottomBounds.get(x).intersects(pinkRect)) {
     pinkIntersectBottomBound = true;
     break;
    } else
     pinkIntersectBottomBound = false;
   }
   if (pinkIntersectBottomBound) {
    int rand = (int)(Math.random() * 2) + 1;
    if (rand == 1) {
     pinkCanTravelUp = false;
     pinkCanTravelDown = false;
     pinkCanTravelRight = true;
     pinkCanTravelLeft = false;
     currentPinkY--;
    } else {
     pinkCanTravelUp = false;
     pinkCanTravelRight = false;
     pinkCanTravelDown = false;
     pinkCanTravelLeft = true;
     currentPinkY--;
    }
   }
   if (pinkCanTravelUp)
    currentPinkY -= ghostSpeed;
   else if (pinkCanTravelDown)
    currentPinkY += ghostSpeed;
   else if (pinkCanTravelLeft)
    currentPinkX -= ghostSpeed;
   else if (pinkCanTravelRight)
    currentPinkX += ghostSpeed;
   pinkRect = new Rectangle2D(currentPinkX, currentPinkY, pink.getWidth(), pink.getHeight());
   if (pinkMode.equals("bonous"))
    pink = new Image("hollow.png");
   else if (pinkMode.equals("reg"))
    pink = new Image("pink.png");
   gc.drawImage(pink, currentPinkX, currentPinkY);

  }
  public void makeYellowMove() {
   for (int x = 0; x < rightBounds.size(); x++) {
    if (rightBounds.get(x).intersects(yellowRect)) {
     yellowIntersectRightBound = true;
     break;
    } else
     yellowIntersectRightBound = false;
   }
   if (yellowIntersectRightBound) {
    int rand = (int)(Math.random() * 2) + 1;
    if (rand == 1) {
     yellowCanTravelUp = true;
     yellowCanTravelDown = false;
     yellowCanTravelRight = false;
     yellowCanTravelLeft = false;
     currentYellowX--;
    } else {
     yellowCanTravelUp = false;
     yellowCanTravelRight = false;
     yellowCanTravelDown = true;
     yellowCanTravelLeft = false;
     currentYellowX--;
    }
   }
   for (int x = 0; x < leftBounds.size(); x++) {
    if (leftBounds.get(x).intersects(yellowRect)) {
     yellowIntersectLeftBound = true;
     break;
    } else
     yellowIntersectLeftBound = false;
   }
   if (yellowIntersectLeftBound) {
    int rand = (int)(Math.random() * 2) + 1;
    if (rand == 1) {
     yellowCanTravelUp = true;
     yellowCanTravelDown = false;
     yellowCanTravelRight = false;
     yellowCanTravelLeft = false;
     currentYellowX++;
    } else {
     yellowCanTravelUp = false;
     yellowCanTravelRight = false;
     yellowCanTravelDown = true;
     yellowCanTravelLeft = false;
     currentYellowX++;
    }
   }

   for (int x = 0; x < topBounds.size(); x++) {
    if (topBounds.get(x).intersects(yellowRect)) {
     yellowIntersectTopBound = true;
     break;
    } else
     yellowIntersectTopBound = false;
   }
   if (yellowIntersectTopBound) {
    int rand = (int)(Math.random() * 2) + 1;
    if (rand == 1) {
     yellowCanTravelUp = false;
     yellowCanTravelDown = false;
     yellowCanTravelRight = true;
     yellowCanTravelLeft = false;
     currentYellowY++;
    } else {
     yellowCanTravelUp = false;
     yellowCanTravelRight = false;
     yellowCanTravelDown = false;
     yellowCanTravelLeft = true;
     currentYellowY++;
    }
   }
   for (int x = 0; x < bottomBounds.size(); x++) {
    if (bottomBounds.get(x).intersects(yellowRect)) {
     yellowIntersectBottomBound = true;
     break;
    } else
     yellowIntersectBottomBound = false;
   }
   if (yellowIntersectBottomBound) {
    int rand = (int)(Math.random() * 2) + 1;
    if (rand == 1) {
     yellowCanTravelUp = false;
     yellowCanTravelDown = false;
     yellowCanTravelRight = true;
     yellowCanTravelLeft = false;
     currentYellowY--;
    } else {
     yellowCanTravelUp = false;
     yellowCanTravelRight = false;
     yellowCanTravelDown = false;
     yellowCanTravelLeft = true;
     currentYellowY--;
    }
   }
   if (yellowCanTravelUp)
    currentYellowY -= ghostSpeed;
   else if (yellowCanTravelDown)
    currentYellowY += ghostSpeed;
   else if (yellowCanTravelLeft)
    currentYellowX -= ghostSpeed;
   else if (yellowCanTravelRight)
    currentYellowX += ghostSpeed;
   yellowRect = new Rectangle2D(currentYellowX, currentYellowY, yellow.getWidth(), yellow.getHeight());
   if (yellowMode.equals("bonous"))
    yellow = new Image("hollow.png");
   else if (yellowMode.equals("reg"))
    yellow = new Image("yellow.png");
   gc.drawImage(yellow, currentYellowX, currentYellowY);

  }
  public void makeBlueMove() {
   for (int x = 0; x < rightBounds.size(); x++) {
    if (rightBounds.get(x).intersects(blueRect)) {
     blueIntersectRightBound = true;
     break;
    } else
     blueIntersectRightBound = false;
   }
   if (blueIntersectRightBound) {
    int rand = (int)(Math.random() * 2) + 1;
    if (rand == 1) {
     blueCanTravelUp = true;
     blueCanTravelDown = false;
     blueCanTravelRight = false;
     blueCanTravelLeft = false;
     currentBlueX--;
    } else {
     blueCanTravelUp = false;
     blueCanTravelRight = false;
     blueCanTravelDown = true;
     blueCanTravelLeft = false;
     currentBlueX--;
    }
   }
   for (int x = 0; x < leftBounds.size(); x++) {
    if (leftBounds.get(x).intersects(blueRect)) {
     blueIntersectLeftBound = true;
     break;
    } else
     blueIntersectLeftBound = false;
   }
   if (blueIntersectLeftBound) {
    int rand = (int)(Math.random() * 2) + 1;
    if (rand == 1) {
     blueCanTravelUp = true;
     blueCanTravelDown = false;
     blueCanTravelRight = false;
     blueCanTravelLeft = false;
     currentBlueX++;
    } else {
     blueCanTravelUp = false;
     blueCanTravelRight = false;
     blueCanTravelDown = true;
     blueCanTravelLeft = false;
     currentBlueX++;
    }
   }

   for (int x = 0; x < topBounds.size(); x++) {
    if (topBounds.get(x).intersects(blueRect)) {
     blueIntersectTopBound = true;
     break;
    } else
     blueIntersectTopBound = false;
   }
   if (blueIntersectTopBound) {
    int rand = (int)(Math.random() * 2) + 1;
    if (rand == 1) {
     blueCanTravelUp = false;
     blueCanTravelDown = false;
     blueCanTravelRight = true;
     blueCanTravelLeft = false;
     currentBlueY++;
    } else {
     blueCanTravelUp = false;
     blueCanTravelRight = false;
     blueCanTravelDown = false;
     blueCanTravelLeft = true;
     currentBlueY++;
    }
   }
   for (int x = 0; x < bottomBounds.size(); x++) {
    if (bottomBounds.get(x).intersects(blueRect)) {
     blueIntersectBottomBound = true;
     break;
    } else
     blueIntersectBottomBound = false;
   }
   if (blueIntersectBottomBound) {
    int rand = (int)(Math.random() * 2) + 1;
    if (rand == 1) {
     blueCanTravelUp = false;
     blueCanTravelDown = false;
     blueCanTravelRight = true;
     blueCanTravelLeft = false;
     currentBlueY--;
    } else {
     blueCanTravelUp = false;
     blueCanTravelRight = false;
     blueCanTravelDown = false;
     blueCanTravelLeft = true;
     currentBlueY--;
    }
   }
   if (blueCanTravelUp)
    currentBlueY -= ghostSpeed;
   else if (blueCanTravelDown)
    currentBlueY += ghostSpeed;
   else if (blueCanTravelLeft)
    currentBlueX -= ghostSpeed;
   else if (blueCanTravelRight)
    currentBlueX += ghostSpeed;
   blueRect = new Rectangle2D(currentBlueX, currentBlueY, blue.getWidth(), blue.getHeight());
   if (blueMode.equals("bonous"))
    blue = new Image("hollow.png");
   else if (blueMode.equals("reg"))
    blue = new Image("blue.png");
   gc.drawImage(blue, currentBlueX, currentBlueY);

  }
 }
}