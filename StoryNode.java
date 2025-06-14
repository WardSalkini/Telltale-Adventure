package application;

public class StoryNode {
	public String storyText;
	public String choiceAText;
	public String choiceBText;
	public String nodeId;
	public StoryNode choiceA;
	public StoryNode choiceB;
	public boolean isEnding;

	public StoryNode(String storyText, String Id) {
		this.storyText = storyText;
		this.nodeId = Id;
		this.isEnding = true;
	}

	public StoryNode(String storyText, String choiceAText, StoryNode choiceA, String choiceBText, StoryNode choiceB,
			boolean isEnding, String Id) {
		this.storyText = storyText;
		this.choiceAText = choiceAText;
		this.choiceBText = choiceBText;
		this.choiceA = choiceA;
		this.choiceB = choiceB;
		this.isEnding = isEnding;
		this.nodeId = Id;
	}
}