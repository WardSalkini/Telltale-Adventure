package application;

public class StoryTeller {
	private StoryNode root;

	public StoryTeller() {
		buildStory();
	}

	public StoryNode getRoot() {
		return root;
	}

	private void buildStory() {
		// #ENDINGS
		StoryNode E1 = new StoryNode("You ignored the voice. Days later, you run out of water and die alone.\n", "E1");

		StoryNode E2 = new StoryNode("You stayed safe in the basement. You survived, but you are now alone\n"
				+ " in a world that has changed forever without you.\n", "E2");

		StoryNode E3 = new StoryNode("You destroyed the device and stayed put. Nothing happens.\n"
				+ " You are safe, but you'll never know the truth.\n", "E3");

		StoryNode E4 = new StoryNode("You had no supplies and helped no one. You die of thirst after two days.\n",
				"E4");

		StoryNode E5 = new StoryNode("You helped your neighbor and reached the army's safe zone.\n"
				+ " You are now a refugee. You are safe, but you'll never know what the light was.\n", "E5");

		StoryNode E6 = new StoryNode("You left your neighbor to follow the light, but without a map or device,\n"
				+ " you get lost and die in the ruins of the city.\n", "E6");

		StoryNode E7 = new StoryNode("You tried to run, but the sewer tunnel collapsed on top of you.\n", "E7");

		StoryNode E8 = new StoryNode("You arrive on time with the device. It creates a shield around you\n"
				+ " as a new energy wave hits. A ship descends from the sky. You are saved.\n", "E8");

		StoryNode E9 = new StoryNode("You arrive on time, but without the device. You see the shield save the others.\n"
				+ " You survive, but are left behind to see the start of a new world.\n", "E9");

		StoryNode E10 = new StoryNode("You took the long path through the mall and arrived too late.\n"
				+ " You see the shield form, but you are on the outside as the energy wave hits.\n", "E10");

		// #NODE N6 - #THE ARRIVAL#
		StoryNode N6 = new StoryNode(
				"You reach the park just in time. The sky is glowing with a strange energy.\n"
						+ " You see a few other people there. You have less than a minute left.\n"
						+ " Your final fate will be decided now.\n",
				"Best Path (with Device)", E8, "Witness Path (without Device)", E9, true, "N6");

		// #NODE N5A - #THE TUNNELS#
		StoryNode N5A = new StoryNode(
				"The sewers are dark and scary. Suddenly, the tunnel starts to shake and collapse!\n"
						+ " You must escape now.\n",
				"Run for the Exit", E7, "Hide in a Pipe", N6, false, "N5A");

		// #NODE N5B - #THE MALL#
		StoryNode N5B = new StoryNode(
				"The mall is creepy and silent. You pass a pharmacy.\n"
						+ " You are low on time, but there might be medicine or food inside.\n"
						+ " Do you take a minute to search it?\n",
				"Search the Pharmacy", E10, "Keep Moving", N6, false, "N5B");

		// #NODE N4A - #THE SURVIVOR#
		StoryNode N4A = new StoryNode(
				"You open the door. It's your neighbor, and his leg is broken.\n"
						+ " He saw army trucks making a safe zone to the south and wants you to help him get there.\n"
						+ " At the same time, you see a strange light pulsing in the sky to the north.\n",
				"Go South: Help your neighbor get to the safe zone", E5, "Go North: Leave him and go towards the light",
				E6, false, "N4A");

		// #NODE N4B - #THE JOURNEY BEGINS#
		StoryNode N4B = new StoryNode(
				"You leave the basement. The city is in ruins and the main road is blocked.\n"
						+ " You can take a fast shortcut through the dark sewer tunnels,\n"
						+ " or a longer but safer path through the empty shopping mall.\n",
				"The Sewers: Fast and dangerous", N5A, "The Mall: Slow and safe", N5B, false, "N4B");

		// #NODE N3A - #BASEMENT WITH FOOD#
		StoryNode N3A = new StoryNode(
				"You make it to the basement with food and a flashlight.\n"
						+ " You are safe for now. Then, you hear a weak voice outside, crying for help.\n",
				"Help Them: Open the door", N4A, "Ignore Them: Stay hidden", E1, false, "N3A");

		// #NODE N3B - #BASEMENT WITH RADIO#
		StoryNode N3B = new StoryNode(
				"You get the radio to the basement. You turn the crank and hear a strange voice:\n"
						+ " 'Countdown: 2 hours. Go to coordinates. Zero Point is starting.'\n"
						+ " This wasn't just a bomb. It's a countdown to something else.\n",
				"Stay Put: It's too risky. The basement is safe", E2, "Go: I have to see what this is about", N4B,
				false, "N3B");

		// #NODE N3C - #BASEMENT WITH DEVICE#
		StoryNode N3C = new StoryNode(
				"In the safety of the basement, the pyramid in your pocket starts to glow.\n"
						+ " It projects a map on the wall. The map shows a location in the city\n"
						+ " and a timer, counting down from 2 hours.\n",
				"Destroy the Device: This is too strange to trust", E3, "Trust the Device: I'll go to the location",
				N4B, false, "N3C");

		// #NODE N3D - #BASEMENT EMPTY-HANDED#
		StoryNode N3D = new StoryNode(
				"You reach the basement, but you have no supplies and no information.\n"
						+ " You feel helpless. After an hour, you hear someone outside crying for help.\n",
				"Help Them: Maybe they have food", N4A, "Ignore Them: I can't risk it", E4, false, "N3D");

		// #NODE N2A - #THE PRAGMATIST'S PATH#
		StoryNode N2A = new StoryNode(
				"The sound doesn't matter now. Only survival does. You need supplies.\n"
						+ " Do you run to the kitchen for a flashlight and food,\n"
						+ " or risk the messy garage to find the emergency radio?\n",
				"The Kitchen: Get flashlight and food", N3A, "The Garage: Get the emergency radio", N3B, false, "N2A");

		// #NODE N2B - #THE INVESTIGATOR'S PATH#
		StoryNode N2B = new StoryNode(
				"That sound wasn't normal. You crawl to the broken TV.\n"
						+ " In the wires, you find a small, glowing metal pyramid.\n"
						+ " It's warm to the touch. This must be the source of the sound.\n",
				"Take the Device: It seems important", N3C, "Leave the Device: It looks dangerous", N3D, false, "N2B");

		// #THE GLITCH# ==THIS IS THE ROOT NODE==
		root = new StoryNode("You're on the couch, watching TV. Suddenly, a bright white flash fills the window.\n"
				+ " The power dies and everything goes silent. A moment later, a loud roar shakes the whole house.\n"
				+ " It's a nuclear explosion. You have to get to the basement.\n"
				+ " But right before the power cut, you heard a strange, sharp 'CHIRP' from the TV. What do you do?\n",
				"Survival: Forget the sound. Get to safety.", N2A, "Curiosity: Find out what made that sound.", N2B,
				false, "root");
	}
}
