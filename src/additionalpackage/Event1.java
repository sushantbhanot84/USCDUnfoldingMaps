package additionalpackage;
import processing.core.*;
public class Event1 extends PApplet {

	private int i=255;
	private int j=0;
	private int k=0;
	public void setup()
	{
		size(800,500,P2D);
	}
	public void draw()
	{
		background(i,j,k);
	}
	public void keyPressed()
	{
		if(key=='w')
		{
			i=0;j=255;	// sets color of the background to green
		}
	}
}
