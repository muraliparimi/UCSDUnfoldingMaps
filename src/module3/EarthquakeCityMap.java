package module3;

//Java utilities libraries
import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
import java.util.List;

//Processing library
import processing.core.PApplet;

//Unfolding libraries
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;

//Parsing library
import parsing.ParseFeed;

/** EarthquakeCityMap
 * An application with an interactive map displaying earthquake data.
 * Author: UC San Diego Intermediate Software Development MOOC team
 * @author Murali Krishna Parimi
 * Date: September 22, 2015
 * */
public class EarthquakeCityMap extends PApplet {

	// You can ignore this.  It's to keep eclipse from generating a warning.
	private static final long serialVersionUID = 1L;

	// IF YOU ARE WORKING OFFLINE, change the value of this variable to true
	private static final boolean offline = false;
	
	// Less than this threshold is a light earthquake
	public static final float THRESHOLD_MODERATE = 5;
	// Less than this threshold is a minor earthquake
	public static final float THRESHOLD_LIGHT = 4;

	/** This is where to find the local tiles, for working without an Internet connection */
	public static String mbTilesString = "blankLight-1-3.mbtiles";
	
	// The map
	private UnfoldingMap map;
	
	//feed with magnitude 2.5+ Earthquakes
	private String earthquakesURL = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";
			//"http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.atom";
			//

	
	public void setup() {
		size(950, 600, OPENGL);

		if (offline) {
		    map = new UnfoldingMap(this, 200, 50, 700, 500, new MBTilesMapProvider(mbTilesString));
		    earthquakesURL = "2.5_week.atom"; 	// Same feed, saved Aug 7, 2015, for working offline
		}
		else {
			map = new UnfoldingMap(this, 200, 50, 700, 500, new Google.GoogleMapProvider());
			// IF YOU WANT TO TEST WITH A LOCAL FILE, uncomment the next line
			//earthquakesURL = "2.5_week.atom";
		}
		
	    map.zoomToLevel(2);
	    MapUtils.createDefaultEventDispatcher(this, map);	
			
	    // The List you will populate with new SimplePointMarkers
	    List<Marker> markers = new ArrayList<Marker>();

	    //Use provided parser to collect properties for each earthquake
	    //PointFeatures have a getLocation method
	    List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);

	    
	    // These print statements show you (1) all of the relevant properties 
	    // in the features, and (2) how to get one property and use it
	    if (earthquakes.size() > 0) {
	    	PointFeature f = earthquakes.get(0);
	    	System.out.println(f.getProperties());
	    	//System.out.println(f.getLocation());
	    	Object magObj = f.getProperty("magnitude");
	    	float mag = Float.parseFloat(magObj.toString());
	    	//System.out.println(mag);
	    	// PointFeatures also have a getLocation method
	    }
	    
	    // Here is an example of how to use Processing's color method to generate 
	    // an int that represents the color yellow.  
	    int yellow = color(255, 255, 0);
	    int blue = color(0,0,255);
	    int red = color(255,0,0);
	    
	    for( PointFeature pf: earthquakes) {
	    	
	    	Object magnObj = pf.getProperty("magnitude");
	    	float mag = Float.parseFloat(magnObj.toString());
	    	SimplePointMarker sm = createMarker(pf);
	    	if (mag <= 4.0) {
	    	    sm.setColor(blue);
	    		sm.setRadius(6);
	    	}
	    	if (mag >4.0 && mag<=4.9) {
	    		sm.setColor(yellow);
	    		sm.setRadius(8);
	    	}
	    	if (mag >=5.0){
	    		sm.setColor(red);
	    		sm.setRadius(10);
	    	}
	    	//sm.setColor(arg0);
	    	markers.add(sm);
	    }
	    
	    map.addMarkers(markers);
	    
	    //TODO: Add code here as appropriate
	}
		
	// A suggested helper method that takes in an earthquake feature and 
	// returns a SimplePointMarker for that earthquake
	// TODO: Implement this method and call it from setUp, if it helps
	private SimplePointMarker createMarker(PointFeature feature)
	{
		// finish implementing and use this method, if it helps.
		
		return new SimplePointMarker(feature.getLocation());
	}
	
	public void draw() {
	    background(10);
	    map.draw();
	    addKey();
	}


	// helper method to draw key in GUI
	// TODO: Implement this method to draw the key
	private void addKey() 
	{	
		// Remember you can use Processing's graphics methods here
		
	
		//rect(25,50,150,250);
		//fill(224,224,224);
		//textSize(16);
		//fill(50);
		//text("Earthquake Key",30, 60);
		
		fill(255, 255, 240);

		rect(30, 50, 160, 250, 7);

		fill(0, 0, 0); //black

		textSize(16);

		text("Earthquake Key", 50, 70);
		
		fill(0, 0, 0); //black

		textSize(12);

		text("5+ Magnitude", 80, 100);

		fill(255, 0, 0); //red

		ellipse(60, 95, 12, 12);

		fill(0, 0, 0); //black

		textSize(12);

		text("4+ Magnitude", 80, 130);

		fill(255, 255, 0); //yellow

		ellipse(60, 125, 10, 10);
		
		fill(0, 0, 0); //black

		textSize(12);

		text("4- Magnitude", 80, 160);

		fill(0, 0, 255); //blue

		ellipse(60, 155, 8, 8);

		fill(0, 0, 0); //black

		textSize(12);

		text("Others", 80, 190);

		fill(255, 255, 255); //white

		ellipse(60, 185, 14, 14);
		
		
		//text("5.0+ Magnitude",40,75);
		//textSize(14);
		
		
		
	
	}
}
