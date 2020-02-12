package guiPackage;

import processing.core.*;


public class guiClass extends PApplet {

	private String URL="https://4.imimg.com/data4/HD/EU/MY-13906151/studio-background-500x500.jpg";
	private PImage p;
	public void setup()
	{
		size(200,200);
		p=loadImage(URL,"jpg");
		//background(150,100,100);
		image(p,0,0);

	}
	
	public void draw()
	{
		p.resize(width,height);
		image(p,0,0);
		int[] rgb=new int[3];
		rgb=secCal(second());
		fill(rgb[0],rgb[1],0);
		ellipse(width/2,height/2.5f,width/5,height/5);
	}
	
	public int[] secCal(float seconds)
	{
		float diffrom30=Math.abs(30-seconds);
		float ratiofrom30=diffrom30/30;
		int[] rgb=new int[3];
		rgb[0]=(int) ((int)255*ratiofrom30);
		rgb[1]=(int)(255*ratiofrom30);
		rgb[2]=0;
		return rgb;
	}
}
