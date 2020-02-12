package additionalpackage;

import processing.core.*;

public class Additional extends PApplet {

	private String URL="https://images.unsplash.com/photo-1503023345310-bd7c1de61c7d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80";
	private PImage a;
	public void setup()
	{
		size(200,200);
		a=loadImage(URL,"jpg");
		image(a,0,0);

	}
	 
	public void draw()
	{
		a.resize(width, height);
		ellipse(width/4,height/4,width/4,height/4);
	}
}
