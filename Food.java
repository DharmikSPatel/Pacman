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
public class Food{
	private String state;
	private final int X = 210;
	private final int Y = 169;
	private Image image;
	public Food(Image i, String s){
		state = s;
		image = i;
	}
	public int getX(){
		return X;
	}
	public int getY(){
		return Y;
	}
	public String getState(){
		return state;
	}
	public void setState(String s){
		state = s;
	}
	public Image getImage(){
		return image;
	}




}