/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepadapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author mshawkat
 */
public class NotePadApp extends Application {

    MenuBar menuBar;
    Menu file;
    MenuItem newItem;
    MenuItem OpenItem;
    MenuItem saveItem;
    MenuItem exitItem;    
    Menu edit;
    MenuItem undoItem;
    MenuItem cutItem;
    MenuItem copyItem;
    MenuItem pasteItem;
    MenuItem selectAllItem;
    Menu help;
    MenuItem aboutNoteItem;
    @Override
    public void start(Stage primaryStage) {
        menuBar = new MenuBar();
        file = new Menu("File");
        edit = new Menu("Edit");
        newItem = new MenuItem("New");
        OpenItem = new MenuItem("Open");
        saveItem = new MenuItem("Save");
        exitItem = new MenuItem("Exit");
        file.getItems().addAll(newItem, OpenItem, saveItem, new SeparatorMenuItem(), exitItem);
        menuBar.getMenus().add(file);
        BorderPane root = new BorderPane();
        root.setTop(menuBar);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
