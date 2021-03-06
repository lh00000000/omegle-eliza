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


public class OmegleEliza extends PApplet {
	Eliza eliza = new Eliza(this);
	Omegle omegle = new Omegle();
	String lastMessageReceived = "";
	OmegleSession session;
	int countsSinceLastReceivedMessage = 0;
	String drawnText = "";

	public void setup() {
		frameRate(1);	
		size(800,450);
		background(0);
		try {
			System.out.println("Opening session...");
			session = omegle.openSession(OmegleMode.NORMAL, new OmegleEventAdaptor() {
				@Override
				public void chatWaiting(OmegleSession session) {
					drawnText = "Waiting for chat...";
					System.out.println("Waiting for chat...");
				}

				@Override
				public void chatConnected(OmegleSession session) {
					drawnText = "ELIZA is now talking to a random stranger!";
					System.out
					.println("ELIZA is now talking to a random stranger!");
				}

				@Override
				public void chatMessage(OmegleSession session, String message) {
					drawnText = "Stranger: " + message;
					System.out.println("Stranger: " + message);
					lastMessageReceived = message;
					countsSinceLastReceivedMessage = 0;
				}

				@Override
				public void messageSent(OmegleSession session, String string) {
					drawnText = "ELIZA: " + string;
					System.out.println("ELIZA: " + string);
					lastMessageReceived = "";
				}

				@Override
				public void strangerDisconnected(OmegleSession session) {
					drawnText = "Stranger disconnected, goodbye!";
					System.out.println("Stranger disconnected, goodbye!");
					//System.exit(0);
				}

				@Override
				public void omegleError(OmegleSession session, String string) {
					drawnText = "ERROR! " + string;
					System.out.println("ERROR! " + string);
					//System.exit(1);
				}
			});
		} catch (OmegleException e) {
			e.printStackTrace();
		}
		drawText(drawnText);
	}

	public String elizaProcessOmegle(String s) {
		s = s.toLowerCase();
		if (	s.contains("hey") ||
				s.contains("hi") || 	
				s.contains("hii") || 	
				s.contains("m") || 	
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
		
		countsSinceLastReceivedMessage ++; 
		try {
			if (countsSinceLastReceivedMessage > 4 && lastMessageReceived != "") {
				session.send(elizaProcessOmegle(lastMessageReceived), true);
			}
		}
		catch (OmegleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (frameCount % 3 == 0) {
			clear();
			drawText(drawnText);
		}

	}

	public static void main(String _args[]) {
		PApplet.main(new String[] { main.OmegleEliza.class.getName() });
	}
}
