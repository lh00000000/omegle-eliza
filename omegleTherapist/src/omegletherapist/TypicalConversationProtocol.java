package omegletherapist;

import java.util.ArrayList;

public class TypicalConversationProtocol implements ConversationProtocol {
	ArrayList<ConversationStage> stages;
	public TypicalConversationProtocol() {
		stages.add(new ConversationStage("waiting for patient to say first message", 400));
		stages.add(new ConversationStage("eliza thinking her first message", 400));
		stages.add(new ConversationStage("waiting for patient to respond to eliza", 400));
		stages.add(new ConversationStage("eliza thinking of her respond to last message from patient", 400));		
	}
}