package MyGui;

import processing.core.*;

public class MyApplet extends PApplet{
	
	public void setup() 
	{
		//PImage img;
		//img = loadImage("/Users/muralikrishnaparimi/Movies/marriage/Photos/DSC_7689.JPG");
		size(600, 400);
		background(0,0,255);
		//stroke(204, 102, 0);
		//rect(30, 20, 55, 55);
	}
	
	public void draw() 
	{
		fill(255,255,0);
		ellipse(100, 100, 300, 300);
	}

}
