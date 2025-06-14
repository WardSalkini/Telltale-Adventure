package application;

// يجب أن تكون جميع عبارات الاستيراد (imports) في أعلى الملف، بعد عبارة الـ package وقبل تعريف أي class.
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority; // تأكد من استيراد Priority
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

// هذا هو الكلاس الرئيسي لتطبيقك. يجب أن يكون public وأن يطابق اسم الملف (GUI.java).
public class GUI extends Application {

	private GameManager gameManager; // تأكد أن GameManager موجود
	private ImageView storyImageView;
	private Label storyTextLabel;
	private Button leftChoiceButton;
	private Button rightChoiceButton;
	private Button restartButton;
	private StackPane imageContainer;

	@Override
	public void start(Stage primaryStage) {

		// تأكد أن StoryTeller موجود
		StoryTeller storyTeller = new StoryTeller();
		gameManager = new GameManager(storyTeller.getRoot());

		setupUI(primaryStage);

		updateUI();

		primaryStage.setTitle("Interactive Story Game");
		primaryStage.show();
	}

	private void setupUI(Stage primaryStage) {
		BorderPane root = new BorderPane();
		root.setStyle("-fx-background: linear-gradient(to bottom, #1a1a2e, #16213e);");

		setupImageArea();
		setupTextArea();
		setupButtons();
		setupRestartButton();

		// **تعديل: تغليف storyTextLabel في HBox للسماح بالتوسع الأفقي**
		HBox textLabelWrapper = new HBox();
		textLabelWrapper.setAlignment(Pos.CENTER);
		textLabelWrapper.setPadding(new Insets(0, 20, 0, 20)); // padding أفقي للحواف
		textLabelWrapper.getChildren().add(storyTextLabel);
		HBox.setHgrow(storyTextLabel, Priority.ALWAYS); // يجعل الـ Label يتوسع أفقياً داخل الـ HBox

		VBox centerContent = new VBox(20);
		centerContent.setAlignment(Pos.CENTER);
		// **تعديل: إضافة padding علوي لـ centerContent لدفع الصورة للأسفل**
		centerContent.setPadding(new Insets(20, 0, 0, 0)); // 20 بكسل من الأعلى

		centerContent.getChildren().addAll(imageContainer, textLabelWrapper);
		VBox.setVgrow(textLabelWrapper, Priority.ALWAYS);

		HBox buttonContainer = new HBox(50);
		buttonContainer.setAlignment(Pos.CENTER);
		buttonContainer.getChildren().addAll(leftChoiceButton, rightChoiceButton);

		VBox bottomContainer = new VBox(20);
		bottomContainer.setAlignment(Pos.CENTER);
		bottomContainer.getChildren().addAll(buttonContainer, restartButton);

		root.setCenter(centerContent);
		root.setBottom(bottomContainer);
		BorderPane.setMargin(bottomContainer, new Insets(10, 0, 10, 0));

		Scene scene = new Scene(root, 1000, 700);
		primaryStage.setScene(scene);
	}

	private void setupImageArea() {
		storyImageView = new ImageView();
		storyImageView.setFitWidth(480);
		storyImageView.setFitHeight(360);
		storyImageView.setPreserveRatio(true);

		DropShadow shadow = new DropShadow();
		shadow.setColor(Color.BLACK);
		shadow.setOffsetX(0);
		shadow.setOffsetY(0);
		shadow.setRadius(25);
		storyImageView.setEffect(shadow);

		imageContainer = new StackPane();
		// **تعديل: لضمان أن الـ StackPane نفسه له حواف مستديرة ويحتوي الظل بشكل صحيح**
		imageContainer.setStyle("-fx-background-radius: 30; -fx-border-radius: 30;");
		imageContainer.getChildren().add(storyImageView);
	}

	private void setupTextArea() {
		storyTextLabel = new Label();
		storyTextLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
		storyTextLabel.setTextFill(Color.WHITE);
		storyTextLabel.setMaxWidth(1000); // **زيادة أقصى عرض للنص للسماح بتمدد أكبر**
		storyTextLabel.setWrapText(false); // يسمح بلف النص
		storyTextLabel.setTextAlignment(TextAlignment.CENTER); // توسيط النص الملتف
		storyTextLabel.setAlignment(Pos.CENTER); // توسيط الـ Label نفسه
		storyTextLabel.setPadding(new Insets(20, 30, 20, 30)); // **زيادة padding أفقيًا للنص نفسه**

		storyTextLabel.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7); " + "-fx-background-radius: 15; "
				+ "-fx-border-radius: 15;");
		// **ملاحظة:** لا تستخدم setPrefSize هنا لـ storyTextLabel للسماح له بالتكيف.
	}

	private void setupButtons() {
		leftChoiceButton = createStyledButton("الخيار الأول: نص أطول قليلاً هنا لتجربة اللف والتمدد");
		rightChoiceButton = createStyledButton(
				"الخيار الثاني: نص آخر طويل جداً جداً جداً جداً جداً جداً جداً جداً جداً جداً");

		leftChoiceButton.setOnAction(e -> makeChoice(1));
		rightChoiceButton.setOnAction(e -> makeChoice(2));
	}

	private void setupRestartButton() {
		restartButton = new Button("Replay");
		restartButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		restartButton.setPrefSize(200, 40); // هذا الحجم مناسب لزر إعادة التشغيل

		LinearGradient restartGradient = new LinearGradient(0, 0, 0, 1, true, null, new Stop(0, Color.web("#ff6b6b")),
				new Stop(1, Color.web("#ee5a24")));

		restartButton.setStyle("-fx-background-color: linear-gradient(to bottom, #ff6b6b, #ee5a24); "
				+ "-fx-text-fill: white; " + "-fx-background-radius: 20; " + "-fx-border-radius: 20; "
				+ "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 5);");

		restartButton.setOnAction(e -> restartGame());

		restartButton.setOnMouseEntered(e -> {
			restartButton.setStyle("-fx-background-color: linear-gradient(to bottom, #ff7979, #fd79a8); "
					+ "-fx-text-fill: white; " + "-fx-background-radius: 20; " + "-fx-border-radius: 20; "
					+ "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.7), 15, 0, 0, 8); "
					+ "-fx-scale-x: 1.1; -fx-scale-y: 1.1;");
		});

		restartButton.setOnMouseExited(e -> {
			restartButton.setStyle("-fx-background-color: linear-gradient(to bottom, #ff6b6b, #ee5a24); "
					+ "-fx-text-fill: white; " + "-fx-background-radius: 20; " + "-fx-border-radius: 20; "
					+ "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 5); "
					+ "-fx-scale-x: 1.0; -fx-scale-y: 1.0;");
		});
	}

	private Button createStyledButton(String text) {
		Button button = new Button(text);
		button.setFont(Font.font("Arial", FontWeight.BOLD, 16));

		// **تعديلات لضمان ظهور النص كاملاً في الزرار (لا تستخدم setPrefSize هنا):**
		button.setWrapText(true); // يسمح بلف النص
		button.setTextAlignment(TextAlignment.CENTER); // توسيط النص الملتف
		button.setPadding(new Insets(15, 25, 15, 25)); // **زيادة padding لزيادة المساحة الداخلية**
		button.setMinWidth(280); // **زيادة العرض الأدنى قليلاً**
		button.setMaxWidth(400); // **يمكنك تحديد عرض أقصى لعدم تمدد الأزرار بشكل مفرط**
		// بما أنك لا تستخدم setPrefSize، فإن الارتفاع سيتكيف تلقائياً مع النص الملفوف.

		button.setStyle("-fx-background-color: linear-gradient(to bottom, #4834d4, #686de0); "
				+ "-fx-text-fill: white; " + "-fx-background-radius: 25; " + "-fx-border-radius: 25; "
				+ "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 5);");

		button.setOnMouseEntered(e -> {
			button.setStyle("-fx-background-color: linear-gradient(to bottom, #5f39f4, #7d5fff); "
					+ "-fx-text-fill: white; " + "-fx-background-radius: 25; " + "-fx-border-radius: 25; "
					+ "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.7), 15, 0, 0, 8); "
					+ "-fx-scale-x: 1.05; -fx-scale-y: 1.05;");
		});

		button.setOnMouseExited(e -> {
			button.setStyle("-fx-background-color: linear-gradient(to bottom, #4834d4, #686de0); "
					+ "-fx-text-fill: white; " + "-fx-background-radius: 25; " + "-fx-border-radius: 25; "
					+ "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 5); "
					+ "-fx-scale-x: 1.0; -fx-scale-y: 1.0;");
		});

		button.setOnMousePressed(e -> {
			button.setStyle("-fx-background-color: linear-gradient(to bottom, #3c29b8, #5a4fcf); "
					+ "-fx-text-fill: white; " + "-fx-background-radius: 25; " + "-fx-border-radius: 25; "
					+ "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 5, 0, 0, 2); "
					+ "-fx-scale-x: 0.98; -fx-scale-y: 0.98;");
		});

		return button;
	}

	private void makeChoice(int choice) {
		if (gameManager.isGameEnding()) {
			return;
		}

		TranslateTransition translateOut = new TranslateTransition(Duration.millis(500), storyImageView);
		FadeTransition fadeOut = new FadeTransition(Duration.millis(500), storyImageView);

		if (choice == 1) {
			translateOut.setToX(-500);
		} else {
			translateOut.setToX(500);
		}

		fadeOut.setToValue(0);

		ParallelTransition exitTransition = new ParallelTransition(translateOut, fadeOut);
		exitTransition.setOnFinished(e -> {
			gameManager.makeChoice(choice);

			storyImageView.setTranslateX(0);
			storyImageView.setOpacity(1);

			updateUI();

			storyImageView.setOpacity(0);
			FadeTransition fadeIn = new FadeTransition(Duration.millis(500), storyImageView);
			fadeIn.setFromValue(0);
			fadeIn.setToValue(1);
			fadeIn.play();
		});

		exitTransition.play();
	}

	private void restartGame() {
		gameManager.restartGame();

		FadeTransition fadeOut = new FadeTransition(Duration.millis(300), storyImageView);
		fadeOut.setToValue(0);
		fadeOut.setOnFinished(e -> {
			updateUI();
			FadeTransition fadeIn = new FadeTransition(Duration.millis(300), storyImageView);
			fadeIn.setFromValue(0);
			fadeIn.setToValue(1);
			fadeIn.play();
		});
		fadeOut.play();
	}

	private void updateUI() {
		StoryNode currentNode = gameManager.getCurrentNode();

		if (currentNode == null) {
			return;
		}

		storyTextLabel.setText(currentNode.storyText);
		updateImage(currentNode); // يتم تحديد الصورة في هذه الدالة

		if (currentNode.isEnding) {
			leftChoiceButton.setVisible(false);
			rightChoiceButton.setVisible(false);
			restartButton.setVisible(true);
		} else {
			leftChoiceButton.setVisible(true);
			rightChoiceButton.setVisible(true);
			restartButton.setVisible(false);

			if (currentNode.choiceAText != null) {
				leftChoiceButton.setText(currentNode.choiceAText);
			} else {
				leftChoiceButton.setText("");
			}
			if (currentNode.choiceBText != null) {
				rightChoiceButton.setText(currentNode.choiceBText);
			} else {
				rightChoiceButton.setText("");
			}
		}
	}

	private void updateImage(StoryNode node) {
		String imageName = findNodeId(node);

		if (imageName != null) {
			try {
				File imageFile = new File("D:/photos/" + imageName + ".jpg");
				if (!imageFile.exists()) {
					imageFile = new File("D:/photos/" + imageName + ".png");
				}

				if (imageFile.exists()) {
					Image image = new Image(imageFile.toURI().toString());
					storyImageView.setImage(image); // هذا هو السطر الذي يعين الصورة المرئية!
				} else {
					setDefaultImage();
				}
			} catch (Exception e) {
				e.printStackTrace();
				setDefaultImage();
			}
		} else {
			setDefaultImage();
		}
	}

	private void setDefaultImage() {
		Rectangle rect = new Rectangle(400, 300);
		rect.setFill(LinearGradient.valueOf("linear-gradient(to bottom, #667eea, #764ba2)"));
		rect.setArcWidth(30);
		rect.setArcHeight(30);
		storyImageView.setImage(null);
	}

	private String findNodeId(StoryNode node) {
		return node.nodeId;
	}

	public static void main(String[] args) {
		launch(args);
	}
}