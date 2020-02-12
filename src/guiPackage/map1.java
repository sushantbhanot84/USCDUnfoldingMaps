package guiPackage;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.*;
import java.net.URL;
import java.net.URLConnection;


import java.io.IOException;
import java.net.MalformedURLException;

public class map1 extends PApplet {

	public static String mbTilesString = "blankLight-1-3.mbtiles";
	// MBTilesMapProvider jdbc connection string (db address for maps to run offline)
	// taken as argument by constructor
	private UnfoldingMap map1;
	private  boolean offline;
	public void setup()
	{
		size(950,600,OPENGL);
		// code to check if the map is working online or offline

		try {
			URL u=new URL("http://www.google.com");
			URLConnection uc=u.openConnection();
			uc.connect();
			offline=false;
		}
		catch(MalformedURLException e) {offline=true;}catch(IOException i) {offline=true;}
		
		// if online use GoogleMapProvider else use MBTilesMapProvider
		
		if(offline)
		{
			map1=new UnfoldingMap(this,200,50,400,400,new MBTilesMapProvider(mbTilesString));
		}
		else
		{
			map1=new UnfoldingMap(this,200,50,400,400,new Google.GoogleMapProvider());
		}
		map1.zoomToLevel(2);
		MapUtils.createDefaultEventDispatcher(this, map1);
		Location l1=new Location(-38.14f,-73.03f);
		/* 
		Marker marker=new SimplePointMarker(l1);
		map1.addMarker(marker);*/
		
		Feature loc1=new PointFeature(l1);
		loc1.addProperty("title","location 1");
		loc1.addProperty("magnitude", 9.5);
		loc1.addProperty("Year", "1960");
		
		
		Marker marker=new SimplePointMarker(l1,loc1.getProperties());
		map1.addMarker(marker);
		marker.setColor(20);
	}
	public void draw()
	{
		
		background(10);
		map1.draw();
		addKey();
	}
	
	public void addKey() {}
	
}
