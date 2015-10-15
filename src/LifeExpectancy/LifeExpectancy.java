package LifeExpectancy;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.data.*;
//import de.fhpotsdam.unfolding.events.*;
import processing.core.*;

import java.util.*;



public class LifeExpectancy extends PApplet {

	UnfoldingMap map;
	Map<String, Float> lifeExpByCountry;
	List<Feature> countries;
	List<Marker> countryMarkers;
	
	
	private Map<String, Float> loadLifeExpectancyFromCSV(String fileName) {
		
		Map<String, Float> lifeExpMap = new HashMap<String, Float> ();
		String[] rows = loadStrings(fileName);
		for (String row: rows) {
			String[] columns = row.split(",");
			if (columns[5] != null && ! columns[5].trim().contains("..") && ! columns[5].matches(".*[a-zA-Z]+.*") && Float.parseFloat(columns[5].trim()) >= 40.00f) {
				float value = Float.parseFloat(columns[5]);
				lifeExpMap.put(columns[4], value);
			}
			
		}
		
		return lifeExpMap;
	}
	
	private void shadeCountries() {
		// TODO Auto-generated method stub
		for (Marker marker: countryMarkers) {
			String countryId = marker.getId();
			
			if (lifeExpByCountry.containsKey(countryId)) {
				float lifeExp = lifeExpByCountry.get(countryId);
				int colorLevel = (int) map(lifeExp, 40, 90, 10, 255);
				marker.setColor(color(255-colorLevel,100,colorLevel));
			}
			else {
				marker.setColor(color(155,155,155));
			}
		}
		
	}
	
	public void setup() {
		
		size(800, 600, OPENGL);
		map = new UnfoldingMap(this, 50, 50, 700, 500, new Google.GoogleMapProvider());
		
		MapUtils.createDefaultEventDispatcher(this, map);
		lifeExpByCountry = loadLifeExpectancyFromCSV("/Users/muralikrishnaparimi/Documents/Java/Basic_Practice/UCSDUnfoldingMaps/data/LifeExpectancyWorldBank.csv");
		countries = GeoJSONReader.loadData(this, "/Users/muralikrishnaparimi/Documents/Java/Basic_Practice/UCSDUnfoldingMaps/data/countries.geo.json");
		countryMarkers = MapUtils.createSimpleMarkers(countries);
		//map.addMarker(countryMarkers);
		map.addMarkers(countryMarkers);
		shadeCountries();
	}
	
	
	public void draw() {
		map.draw();
		
	}
}
