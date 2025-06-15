# Choose Your Adventure

Welcome to **Choose Your Adventure**, an interactive storytelling game built in Java using a binary tree structure! 🎮📖 This project allows players to shape their own narrative by making choices that lead to different story outcomes. Developed as part of my journey as a sophomore Systems and Biomedical Engineering student at Cairo University, this game showcases my skills in Java programming, JavaFX for GUI, and data structure implementation.

---

## 📖 About the Project

**Choose Your Adventure** is a text-based adventure game where players navigate a post-apocalyptic storyline by making critical decisions. Each choice corresponds to a node in a binary tree, guiding the player through a unique path with multiple possible endings. The game features a sleek JavaFX interface with dynamic images, animated transitions, and responsive buttons, creating an immersive experience.

### Key Features

- **Interactive Storytelling**: Players make choices that determine the story's direction, with 10 unique endings.
- **Binary Tree Structure**: The narrative is powered by a binary tree, ensuring efficient and organized story progression.
- **JavaFX GUI**: A modern, visually appealing interface with smooth animations and gradient styling.
- **Dynamic Media**: Story-specific images load dynamically based on the player's current node in the story.
- **Replayability**: A "Replay" button allows players to restart and explore different story paths.

---

## 🛠️ Technologies Used

- **Java**: Core programming language for game logic and binary tree implementation.
- **JavaFX**: Used for building the graphical user interface with animations and styling.
- **Binary Tree**: Data structure to manage the story's branching paths.
- **CSS**: Custom styling for the JavaFX interface.
- **Maven/Gradle** (optional): For managing dependencies.
---

## 🚀 Getting Started

### Prerequisites

- **Java Development Kit (JDK)**: Version 8 or higher.
- **JavaFX SDK**: Required for running the GUI components.
- **IDE**: IntelliJ IDEA, Eclipse, or any Java-compatible IDE.
- **Maven/Gradle** (optional): For managing dependencies.

### Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-username/choose-your-adventure.git
   cd choose-your-adventure
   ```

2. **Set up JavaFX**:
   - Download JavaFX SDK from [OpenJFX](https://openjfx.io/)
   - Add JavaFX modules to your project classpath

3. **Configure Image Path**:
   - Update the image directory path in `GUI.java` to match your local setup:
   ```java
   File imageFile = new File("path/to/your/photos/" + imageName + ".jpg");
   ```

4. **Run the Application**:
   ```bash
   java --module-path /path/to/javafx/lib --add-modules javafx.controls,javafx.fxml GUI.GUI
   ```

---

## 🏗️ Project Structure

```
choose-your-adventure/
├── src/
│   ├── Core/
│   │   ├── GameManager.java      # Manages game state and player choices
│   │   ├── StoryNode.java        # Represents nodes in the binary tree
│   │   └── StoryTeller.java      # Builds the complete story tree
│   └── GUI/
│       ├── GUI.java              # Main JavaFX application class
│       └── application.css       # CSS styling for the interface
├── photos/                       # Directory for story images
├── README.md
└── LICENSE
```

### Core Components

- **StoryNode**: Represents individual story points with text, choices, and connections to other nodes
- **StoryTeller**: Constructs the entire story tree with all possible paths and endings
- **GameManager**: Handles game state, validates choices, and manages progression through the tree
- **GUI**: Provides the JavaFX interface with animations, styling, and user interaction

---

## 🎯 How It Works

### Binary Tree Implementation

The game uses a binary tree where:
- Each **node** represents a story segment
- **Left child** corresponds to Choice A
- **Right child** corresponds to Choice B
- **Leaf nodes** represent story endings

```java
public class StoryNode {
    public String storyText;
    public String choiceAText;
    public String choiceBText;
    public StoryNode choiceA;  // Left child
    public StoryNode choiceB;  // Right child
    public String nodeId;
    public boolean isEnding;
}
```

### Game Flow

1. Player starts at the root node (nuclear explosion scenario)
2. Reads the story text and available choices
3. Selects a choice (left or right button)
4. Game transitions to the corresponding child node
5. Process repeats until reaching an ending node
6. Player can restart to explore different paths

---

## 📷 Project Photos

| | |
|:-------------------------:|:-------------------------:|
|<img width="500" alt="Game Start Screen" src="photos/E1.jpg">|<img width="500" alt="Choice Selection" src="photos/E2.jpg">|
|<img width="500" alt="Story Progression" src="photos/N5A.jpg">|<img width="500" alt="Game Ending" src="photos/N3A.jpg">|

---

## 🎮 Gameplay Features

### Story Elements

- **Post-Apocalyptic Setting**: Navigate a world after a nuclear explosion
- **Moral Choices**: Decisions between survival and helping others
- **Mystery Elements**: Discover the truth behind strange phenomena
- **Multiple Paths**: 10 different endings based on your choices

### Technical Features

- **Smooth Animations**: Fade and translate transitions between scenes
- **Responsive Design**: Buttons and text that adapt to content
- **Visual Effects**: Gradient backgrounds, drop shadows, and hover effects
- **Error Handling**: Graceful handling of missing images or resources

---

## 🎨 Customization

### Adding New Story Content

1. **Create New Nodes** in `StoryTeller.java`:
   ```java
   StoryNode newNode = new StoryNode("Your story text", "Choice A", nodeA, "Choice B", nodeB, false, "nodeId");
   ```

2. **Link to Existing Nodes**:
   ```java
   existingNode.choiceA = newNode;
   ```

3. **Add Corresponding Images** to the photos directory with matching node IDs

### Modifying Visual Style

- Update gradient colors in button styling methods
- Adjust animation durations in transition effects
- Modify CSS styling for different visual themes

---

## 🔧 Technical Highlights

### Data Structure Efficiency

- **O(1) Navigation**: Direct access to child nodes
- **Memory Efficient**: Each node stores only necessary story data
- **Scalable Design**: Easy to add new story branches

### JavaFX Implementation

- **Custom Styled Components**: Gradient buttons with hover effects
- **Animation System**: Smooth transitions between story nodes
- **Responsive Layout**: Adapts to different screen sizes
- **Event Handling**: Efficient mouse and keyboard input processing

---

## 🚧 Future Enhancements

- **Save/Load System**: Allow players to save progress
- **Multiple Stories**: Add different story themes and settings
- **Sound Effects**: Audio feedback for choices and transitions
- **Statistics Tracking**: Record player choices and popular paths
- **Multiplayer Mode**: Collaborative decision-making
