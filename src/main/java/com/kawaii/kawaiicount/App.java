package com.kawaii.kawaiicount;

import com.kawaii.kawaiicount.utilities.DatabaseInitializer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application
{
    // GLOBAL VARIABLES THAT SPECIFY THE WIDTH AND HEIGHT OF THE SCENE
    // LOGIN PAGE DIMENSIONS
    public static final int HEIGHT = 576;
    public static final int WIDTH = 768;
    // MAIN SCENE DIMENSIONS
    public static final int MAIN_HEIGHT = 720;
    public static final int MAIN_WIDTH = 1280;

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException
    {
        initialize();
        scene = new Scene(loadFXML("startup-animation"), WIDTH, HEIGHT);

        stage.setTitle("Kawaii Count");
        stage.setResizable(false);
        stage.centerOnScreen();

        stage.setScene(scene);
        stage.show();
    }

    // PUBLIC FUNCTION TO SWITCH BETWEEN SCENES
    public static void setRoot(String fxml, int width, int height) throws IOException
    {
        Stage stage = (Stage) scene.getWindow();
        scene = new Scene(loadFXML(fxml), width, height);

        stage.setScene(scene);
        stage.sizeToScene();
    }

    private static Parent loadFXML(String fxml) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    private void initialize()
    {
        // INITIALIZE THE DATABASE FIRST
        DatabaseInitializer.initialize();
        // INITIALIZE FONT FACES
        // POPPINS
        Font.loadFont(getClass().getResourceAsStream("fonts/poppins/Poppins-Regular.ttf"), 14);
        Font.loadFont(getClass().getResourceAsStream("fonts/poppins/Poppins-Medium.ttf"), 14);
        Font.loadFont(getClass().getResourceAsStream("fonts/poppins/Poppins-SemiBold.ttf"), 14);
        Font.loadFont(getClass().getResourceAsStream("fonts/poppins/Poppins-Bold.ttf"), 14);
        Font.loadFont(getClass().getResourceAsStream("fonts/poppins/Poppins-Black.ttf"), 14);
        // PLAYFAIR DISPLAY
        Font.loadFont(getClass().getResourceAsStream("fonts/playfair/PlayfairDisplay-Italic.ttf"), 14);
        Font.loadFont(getClass().getResourceAsStream("fonts/playfair/PlayfairDisplay-BoldItalic.ttf"), 14);
        Font.loadFont(getClass().getResourceAsStream("fonts/playfair/PlayfairDisplay-BlackItalic.ttf"), 14);
    }
}
