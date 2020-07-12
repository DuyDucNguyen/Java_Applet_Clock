import java.util.Date;
import processing.core.*;



public class MyPApplet extends PApplet {
	
	private PImage backgroundImg;
	private String URL = "https://photojournal.jpl.nasa.gov/jpegMod/PIA23405_modest.jpg";
	PFont f;
	Date date;

	int cx, cy;
	float secondsRadius;
	float minutesRadius;
	float hoursRadius;
	float clockDiameter;
	
	
	// all functions that are run once
	public void setup()
	{
		backgroundImg = loadImage(URL,"jpg");
		f = createFont("Arial", 20, true);
		//background(255);
		//loop();
		
		float radius = 120;
		secondsRadius = (float) (radius*0.72);
		minutesRadius = (float) (radius*0.60);
		hoursRadius = (float) (radius*0.50);
		clockDiameter = (float) (radius*1.8);
		  
		cx = 150;
		cy = height/2;
		
	}	
	
	public void settings() {
		size(904, 368);
	}
	
	
	public void draw()
	{
		//background(0);
		
		backgroundImg.resize(0, height);
		
		// Coordinates of top left corner of image
		image(backgroundImg, 0, 0);
		
		
				
		// add Digital Clock
		textFont(f); 
		date = new Date();
		textAlign(LEFT);
		text(date.toString(), cy+100, 100); 
		fill(127,104,104);  //Grey
		
				
		// Draw the clock background
		noStroke();
		ellipse(cx, cy, clockDiameter, clockDiameter);
		
		// select rgb color
		int[] color = sunColorSec(second());
		fill(255, 255, 6); //Yellow
		//fill(color[0], color[1], color[2]);
		
	  	// Angles for sin() and cos() start at 3 o'clock;
	  	// subtract HALF_PI to make them start at the top
	  	float s = map(second(), 0, 60, 0, TWO_PI) - HALF_PI;
	  	float m = map(minute() + norm(second(), 0, 60), 0, 60, 0, TWO_PI) - HALF_PI; 
	  	float h = map(hour() + norm(minute(), 0, 60), 0, 24, 0, TWO_PI * 2) - HALF_PI;
	  	
	  	// Draw the hands of the clock
	    stroke(255);
	    strokeWeight(1);
	    line(cx, cy, cx + cos(s) * secondsRadius, cy + sin(s) * secondsRadius);
	    strokeWeight(2);
	    line(cx, cy, cx + cos(m) * minutesRadius, cy + sin(m) * minutesRadius);
	    strokeWeight(4);
	    line(cx, cy, cx + cos(h) * hoursRadius, cy + sin(h) * hoursRadius);
	    
	    // Draw the minute ticks
	    strokeWeight(2);
	    beginShape(POINTS);
	    for (int a = 0; a < 360; a+=6) {
	      float angle = radians(a);
	      float x = cx + cos(angle) * secondsRadius;
	      float y = cy + sin(angle) * secondsRadius;
	      vertex(x, y);
	    }
	    endShape();
	}
	
	public int[] sunColorSec(float seconds) {
		int[] rgb = new int[3];
		float diffFrom30 = Math.abs(30-seconds);
		float ratio = diffFrom30/30;
		rgb[0] = (int)(255*ratio);
		rgb[1] = (int)(255*ratio);
		rgb[2] = 0;
		return rgb;
	}
	
	public static void main(String[] args)
	{
		PApplet.main("MyPApplet");
		
	}
}

