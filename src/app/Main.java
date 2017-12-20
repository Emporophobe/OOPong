package app;

import Controller.KeyHandler;
import Model.Game;
import View.DrawGame;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class Main extends Application{
    private int WIDTH = PropertiesManager.getIntProperty("width");
    private int HEIGHT = PropertiesManager.getIntProperty("height");
    private int PLAYERS = PropertiesManager.getIntProperty("players");
    private int MAX_SCORE = PropertiesManager.getIntProperty("max_score");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Set up Javafx objects
        Group root = new Group();
        Scene theScene = new Scene(root);
        Canvas canvas = new Canvas(WIDTH, HEIGHT);

        root.getChildren().add(canvas);
        primaryStage.setScene(theScene);
        primaryStage.show();

        // the gc is what is drawn to
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Make the world and populate it
        Game g = new Game(WIDTH, HEIGHT, PLAYERS, MAX_SCORE);

        theScene.setOnKeyPressed(KeyHandler::handleKeyPressed);
        theScene.setOnKeyReleased(KeyHandler::handleKeyReleased);

        // Animation loop
        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                g.onTick();
                KeyHandler.processKeys(g);
                DrawGame.draw(g, gc);
            }
        }.start();
    }
}
