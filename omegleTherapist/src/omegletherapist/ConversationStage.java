package omegletherapist;

public class ConversationStage {
	String name;
	int id;
	int duration;
	int elapsed;
	int idOfNextStage;
	boolean finished;
	boolean success;
	
	
	public ConversationStage(String _name, int id, int _duration) {
		name = _name;
		duration = _duration;
		elapsed = 0;
		finished = false;
		success = false;
	}
	
	public void use() {
		elapsed++;
		if (elapsed > duration) 
			finished = true;
	}
	
	public int getId() {
		return id;
	}
	
	public void endPrematurely() {
		finished = true;
	}
	
	public boolean isFinished() {
		return finished; 
	}
	
	public boolean wasSuccess() {
		return success;
	}
	
	public String getStageName() {
		return name;
	}
}
