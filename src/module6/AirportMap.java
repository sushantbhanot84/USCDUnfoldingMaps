package module6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import controlP5.*;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.data.ShapeFeature;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import de.fhpotsdam.unfolding.geo.Location;
import parsing.ParseFeed;
import processing.core.PApplet;

/** An applet that shows airports (and routes)
 * on a world map.  
 * @author Adam Setters and the UC San Diego Intermediate Software Development
 * MOOC team
 *
 */
public class AirportMap extends PApplet {
	
	UnfoldingMap map;
	private List<Marker> airportList,srcList,destList;
	List<Marker> routeList;
	ControlP5 cp5;
	String src,destin;
	
	public void setup() {
		// setting up PAppler
		size(900,700, OPENGL);
		
		// setting up map and default events
		map = new UnfoldingMap(this, 250, 50, 600, 550,new Google.GoogleMapProvider());
		MapUtils.createDefaultEventDispatcher(this, map);
		map.zoomLevel(0);
		
		// get features from airport data
		List<PointFeature> features = ParseFeed.parseAirports(this, "airports.dat");
		
		// list for markers, hashmap for quicker access when matching with routes
		airportList = new ArrayList<Marker>();
		HashMap<Integer, Location> airports = new HashMap<Integer, Location>();
		
		// create markers from features
		for(PointFeature feature : features) {
			AirportMarker m = new AirportMarker(feature);
			//System.out.println(feature.properties.toString());
			//System.out.println(feature.getProperty("country"));
			m.setRadius(5);
			airportList.add(m);
			
			// put airport in hashmap with OpenFlights unique id for key
			airports.put(Integer.parseInt(feature.getId()), feature.getLocation());
		
		}
		
		
		// parse route data
		List<ShapeFeature> routes = ParseFeed.parseRoutes(this, "routes.dat");
		routeList = new ArrayList<Marker>();
		for(ShapeFeature route : routes) {
			
			// get source and destination airportIds
			int source = Integer.parseInt((String)route.getProperty("source"));
			int dest = Integer.parseInt((String)route.getProperty("destination"));
			
			// get locations for airports on route
			if(airports.containsKey(source) && airports.containsKey(dest)) {
				route.addLocation(airports.get(source));
				route.addLocation(airports.get(dest));
			}
			
			SimpleLinesMarker sl = new SimpleLinesMarker(route.getLocations(), route.getProperties());
		
			//System.out.println(sl.getProperties());
			
			//UNCOMMENT IF YOU WANT TO SEE ALL ROUTES
			routeList.add(sl);
			
			
		}
		cp5=new ControlP5(this);
		cp5.addTextfield("Enter Source").setPosition(10,100).setSize(100,50).setAutoClear(true);
		cp5.addTextfield("Enter Dest").setPosition(120,100).setSize(100,50).setAutoClear(true);
		
		//UNCOMMENT IF YOU WANT TO SEE ALL ROUTES
		//map.addMarkers(routeList);
		
		//map.addMarkers(airportList);
		
	}
	
	
	public void draw() {
		background(220,200,50);
		drawUI();
		
		map.draw();
		
	}
	
	@Override
	public void mouseClicked()
	{
		checkButtonClick();
	}
	
	
	void checkButtonClick()
	{
		if(mouseX>=30 && mouseY>=170 && mouseX<=180 && mouseY<=220)
		{
			map.addMarkers(airportList);
		}
		if(mouseX>=30&& mouseY>=240 && mouseX<=180 && mouseY<=290) {
			src=cp5.get(Textfield.class,"Enter Source").getText();
			destin=cp5.get(Textfield.class,"Enter Dest").getText();
			System.out.println(src+" "+destin);
			showCountries();
		}
	}	
	
	void drawUI()
	{
		fill(220,200,100);
		rect(30,170,150,50);	
		fill(0);
		textSize(16);
		text("view airports",55,200);
		
		fill(220,200,100);
		rect(30,240,150,50);
		fill(0);
		textSize(16);
		text("View All Ports",50,270);
	}
	
	void showCountries()
	{
		
List<PointFeature> features = ParseFeed.parseAirports(this, "airports.dat");
		
		// list for markers, hashmap for quicker access when matching with routes
		srcList = new ArrayList<Marker>();
		destList = new ArrayList<Marker>();
		HashMap<Integer, Location> airports = new HashMap<Integer, Location>();
		HashMap<Integer,String> airport_country=new HashMap<Integer,String>();
		String country_val,x;
		// create markers from features
		for(PointFeature feature : features) {
			
			//System.out.println(feature.properties.toString());
			System.out.println(src+" "+destin);
			//System.out.println(feature.getProperty("country").toString());
			x=feature.getProperty("country").toString();
			country_val=x.substring(1, x.length()-1);
			System.out.println(country_val);
			
			//System.out.println(feature.getProperty("country"));
			if(src.equals(country_val))
			{
				System.out.println("True");
				AirportMarker m = new AirportMarker(feature);
				srcList.add(m);
			}
			else if(destin.equals(country_val))
			{
				System.out.println("True");
				AirportMarker m = new AirportMarker(feature);
				destList.add(m);
			}
			else
			{
				continue;
			}
			
			
			// put airport in hashmap with OpenFlights unique id for key
			airports.put(Integer.parseInt(feature.getId()), feature.getLocation());
			airport_country.put(Integer.parseInt(feature.getId()),country_val);
		
		}
		
		if(airports.isEmpty())
		{
			System.out.println("I'm Done");
		}
		else System.out.println("LOL");
		
		// parse route data
		List<ShapeFeature> routes = ParseFeed.parseRoutes(this, "routes.dat");
		routeList = new ArrayList<Marker>();
		for(ShapeFeature route : routes) {
			
			// get source and destination airportIds
			int source = Integer.parseInt((String)route.getProperty("source"));
			int dest = Integer.parseInt((String)route.getProperty("destination"));
			//System.out.println(route.properties);
			// get locations for airports on route
			if(airports.containsKey(source) && airports.containsKey(dest))
			{
				System.out.println(airport_country.get(source).toString()+" "+airport_country.get(dest).toString());
				if(airport_country.get(source).toString().equals(airport_country.get(dest).toString()))
				{
					System.out.println("equal");
					continue;
				}
				route.addLocation(airports.get(source));
				route.addLocation(airports.get(dest));
			}
			
			SimpleLinesMarker sl = new SimpleLinesMarker(route.getLocations(), route.getProperties());
		
			//System.out.println(sl.getProperties());
			
			//UNCOMMENT IF YOU WANT TO SEE ALL ROUTES
			routeList.add(sl);
			
			
		}
		
		//UNCOMMENT IF YOU WANT TO SEE ALL ROUTES
		map.addMarkers(routeList);
		
		map.addMarkers(srcList);
		map.addMarkers(destList);
	}

}
