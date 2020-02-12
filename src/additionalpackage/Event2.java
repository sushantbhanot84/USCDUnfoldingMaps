package additionalpackage;

import processing.core.*;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;
public class Event2 extends PApplet{

	UnfoldingMap mp;
	int i=255,j=0,k=0;
	public static String mbTilesString = "blankLight-1-3.mbtiles";

	public void setup()
	{
		size(800,800,OPENGL);
		mp=new UnfoldingMap(this,100,100,250,250,new  MBTilesMapProvider(mbTilesString));
		MapUtils.createDefaultEventDispatcher(this, mp);

	}
	
	public void draw()
	{	
		
		background(i,j,k);
		mp.draw();
		drawButtons();


	}
	void drawButtons()
	{
		fill(255,255,0);
		rect(100,100,25,25);
		fill(0,255,255);
		rect(300,300,100,100);
	}
	public void mouseClicked()
	{
		if(mouseX>=100&& mouseX<201 && mouseY>=100 && mouseY<=200)
		{
			j=255;
		}
	}
	
}
