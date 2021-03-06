package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Calendar;

import org.nikki.omegle.Omegle;
import org.nikki.omegle.core.OmegleException;
import org.nikki.omegle.core.OmegleMode;
import org.nikki.omegle.core.OmegleSession;
import org.nikki.omegle.event.OmegleEventAdaptor;

import codeanticode.eliza.Eliza;
import processing.core.PApplet;


public class OfflineEliza extends PApplet {
	Eliza eliza = new Eliza(this);
	Omegle omegle = new Omegle();
	String lastMessageReceived = "";
	OmegleSession session;
	int countsSinceLastReceivedMessage = 0;


	public void setup() {
		frameRate(1);
		size(800,450);
		background(0);
	}

	public String elizaProcessOmegle(String s) {
		s = s.toLowerCase();
		if (	s.contains("hey") ||
				s.contains("hi") || 				
				s.contains("/") ||
				s.contains("\\")) {
			return eliza.processInput("hello"); 
		} else if (	s.contains("asl")) {
			int year = Calendar.getInstance().get(Calendar.YEAR);
			int yearElizaWasBorn = 1966;
			int elizaAge = year - yearElizaWasBorn;
			return "f/" + elizaAge + "/boston";
		} else if (s.contains("cool")) { 
			return eliza.processInput("yes");
		} else if (s.contains("good")) { 
			return eliza.processInput("yes");
		} else if (s.contains("great")) { 
			return eliza.processInput("yes");
		} else if (s.contains("poop")) { 
			return eliza.processInput("no");
		} else if (s.contains("sry")) { 
			return eliza.processInput("sorry");
		} else if (s.equals("what")) { 
			return eliza.processInput("confusing input");
		} else {
			return eliza.processInput(s);
		}
	}

	public void drawText(String s) {
		textSize(16);
		textAlign(CENTER, CENTER);
		text(s, width/2 , height/2);
	}
	
	public void draw() {
		clear();
		drawText(elizaProcessOmegle("what"));
	}

	public static void main(String _args[]) {
		PApplet.main(new String[] { main.OfflineEliza.class.getName() });
	}
}
