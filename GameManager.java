package application;

public class GameManager {

	private StoryNode currentNode;
	private final StoryNode rootNode; // Keep a reference to the start to allow restarting.

	public GameManager(StoryNode rootNode) {
		if (rootNode == null) {
			throw new IllegalArgumentException("Root node cannot be null.");
		}
		this.rootNode = rootNode;
		this.currentNode = rootNode; // Start the game at the root node
	}

	/**
	 * Returns the current story node the player is on. The GUI will use this to get
	 * the text to display (story text, choice texts).
	 * 
	 * @return The current Core.StoryNode.
	 */
	public StoryNode getCurrentNode() {
		return this.currentNode;
	}

	/**
	 * Processes the player's choice and updates the current node.
	 * 
	 * @param choice The choice made by the player (1 for choice A, 2 for choice B).
	 *               It's assumed the UI layer will translate its input (e.g.,
	 *               button click) into this integer format.
	 */
	public void makeChoice(int choice) {
		if (currentNode == null) {
			System.err.println("Error: Current node is null. Cannot make a choice.");
			return;
		}

		// If the current node is an ending node, no more choices can be made.
		if (currentNode.isEnding) {
			System.out.println("Game has ended. No more choices can be made. Restart to play again.");
			return;
		}

		StoryNode nextNode = null;
		if (choice == 1) { // Corresponds to choice A
			nextNode = currentNode.choiceA;
		} else if (choice == 2) { // Corresponds to choice B
			nextNode = currentNode.choiceB;
		} else {
			// Handle invalid choice integer (not 1 or 2)
			System.err.println(
					"Error: Invalid choice identifier: " + choice + ". Please use 1 for Choice A or 2 for Choice B.");
			return; // Do not change currentNode if choice identifier is invalid
		}

		// If the chosen path leads to a valid next node, update the current node.
		if (nextNode != null) {
			currentNode = nextNode;
		} else {
			// This case means choice was 1 or 2, but the corresponding
			// Core.StoryNode.choiceA or Core.StoryNode.choiceB was null.
			System.err.println("Error: Story branch not defined for choice " + choice + " at node: '"
					+ currentNode.storyText.substring(0, Math.min(currentNode.storyText.length(), 30)) + "...'");
			// or handle this as a specific game ending/error state.
			// For now, currentNode remains unchanged.
		}
	}

	// Resets the game to the very beginning by setting the current node
	public void restartGame() {
		this.currentNode = this.rootNode;
		System.out.println("Game restarted.");
	}

	// Checks if the current node is an ending node.

	public boolean isGameEnding() {
		if (this.currentNode == null) {
			return true; // Or throw an exception, as this state should ideally not be reached.
		}
		return this.currentNode.isEnding;
	}
}
