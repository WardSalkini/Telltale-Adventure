# 📚 Interactive Story Game 🎮

## 📝 Project Description

Welcome to the Interactive Story Game! ✨ This project immerses you in a world of intriguing choices, where every decision you make shapes the narrative and determines its outcome. 📖 Enjoy a visually rich experience with dynamic images and interactive buttons that guide you through an unforgettable journey.

## 🌟 Key Features

* **Branching Storylines**: Your decisions are the key to different endings! 🗝️
* **Engaging User Interface**: A sleek and intuitive graphical interface built with JavaFX for a smooth user experience.
* **Dynamic Imagery**: Images intelligently update to match the current story scene, adding visual depth.
* **Stunning Visual Effects**: Smooth transitions between scenes and enhanced visual effects on images and buttons for an aesthetic touch.
* **Smart Text Display**: Text automatically adapts to screen size and wraps to display fully within buttons and the main story text area.
* **Restart Option**: Want to try a different path? 🔄 Restart the game at any time and explore new choices!

## 🛠️ Requirements

Ensure the following are available on your system:

* **Java Development Kit (JDK) 17 or later**: ☕ (Essential for running JavaFX applications).
* **JavaFX SDK**: 📦 (JavaFX SDK 24.0.1 was used in this project; downloading it is recommended).
* **Eclipse IDE (Recommended)**: 💡 An excellent development environment for easy project setup and execution.

## 🚀 How to Run

Follow these steps to get the game up and running:

1.  **Obtain the Project**:
    * Download the project files to your computer.

2.  **Set up JavaFX in Eclipse**:
    * Download the JavaFX SDK from the [official OpenJFX website](https://openjfx.io/openjfx-docs/#install-sdk).
    * In Eclipse, go to `Window` -> `Preferences` -> `Java` -> `Build Path` -> `User Libraries`.
    * Click `New...` and give the library a name (e.g., `JavaFX_Lib`).
    * Select the library you just created, click `Add External JARs...`, and then add **all** `.jar` files located in the `lib` folder within your downloaded JavaFX SDK.
    * Click `Apply and Close`.
    * Now, in your project, right-click on the project -> `Properties` -> `Java Build Path` -> `Libraries` -> `Add Library` -> `User Library`, then choose the JavaFX library you just created.

3.  **Configure VM Arguments**:
    * Right-click on your `GUI.java` file (or the main class containing the `main` method) in the Package Explorer.
    * Choose `Run As` -> `Run Configurations...`.
    * Navigate to the `Arguments` tab.
    * In the `VM arguments` box, add the following line (make sure to **adjust the path** to match the location of your JavaFX SDK's `lib` folder on your machine!):
        ```
        --module-path "D:\openjfx-24.0.1_windows-x64_bin-sdk\javafx-sdk-24.0.1\lib" --add-modules javafx.controls,javafx.fxml,javafx.graphics,javafx.web,javafx.media
        ```
    * Click `Apply` then `Run`. 🚀

4.  **Images Folder**:
    * **Crucial Step!** 📸 Ensure you have a folder named `photos` at the `D:/` path on your system (i.e., `D:/photos/`).
    * Place all images used in the story within this folder. The image filenames (with `.jpg` or `.png` extension) must precisely match what is specified in the `nodeImageMap` within the code, e.g., `root.jpg`, `N2A.png`.

## 📂 Project Structure

The project consists of the following main classes:

* `src/application/`:
    * `GUI.java`: 🖼️ The primary class that builds and displays the game's graphical user interface.
    * `GameManager.java`: 🕹️ Manages the game's progression logic and handles player choices.
    * `StoryNode.java`: 📝 Represents each "node" or segment of the story (containing text, available choices, and next nodes).
    * `StoryTeller.java`: ✍️ Initializes the basic structure of the story (the node tree) that the GameManager navigates.
