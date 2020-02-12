package module1;

import java.awt.Dimension;

import processing.core.PApplet;
import processing.core.PImage;



public class Firse extends PApplet {
	
	private String URL="https://images.pexels.com/photos/414612/pexels-photo-414612.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500";
	private PImage bg;
	Dimension appsize;
	public void setup()
	{
		size(200,200);
		bg=loadImage(URL,"jpg");
	}
	
	public void draw()
	{
		appsize=this.getSize();
		image(bg,0,0);
		// 0,0 is position of image from the top left corner

		bg.resize(appsize.width, appsize.height);
		//Dimension fetches the h & w of the applet 
		// height and width are dynamic variables and this task can also be executed using them
		//bg.resize(width, height)
		
		ellipse(width/4,height/5,width/5,height/5);	
		
	}
	
}
