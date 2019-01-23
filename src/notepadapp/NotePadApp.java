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
import javafx.scene.control.TextArea;
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
    TextArea textArea;
    BorderPane pane;
    Scene scene;

    @Override
    public void init() {
        menuBar = new MenuBar();

        // Menu File Intialization
        file = new Menu("File");
        newItem = new MenuItem("New");
        OpenItem = new MenuItem("Open");
        saveItem = new MenuItem("Save");
        exitItem = new MenuItem("Exit");
        file.getItems().addAll(newItem, OpenItem, saveItem, new SeparatorMenuItem(), exitItem);

        // Menu Edit Intialization
        edit = new Menu("Edit");
        undoItem = new MenuItem("Undo");
        cutItem = new MenuItem("Cut");
        copyItem = new MenuItem("Copy");
        pasteItem = new MenuItem("Paste");
        selectAllItem = new MenuItem("Select All");
        edit.getItems().addAll(undoItem, new SeparatorMenuItem(), cutItem, copyItem, pasteItem, new SeparatorMenuItem(), selectAllItem);

        // Menu Help Initalization
        help = new Menu("Help");
        aboutNoteItem = new MenuItem("About NotePad");
        help.getItems().add(aboutNoteItem);

        // add Menus to Menu Bar
        menuBar.getMenus().addAll(file, edit, help);

        // Text Area Intialization
        textArea = new TextArea();
        pane = new BorderPane();

        pane.setTop(menuBar);
        pane.setCenter(textArea);

        scene = new Scene(pane, 800, 600);
    }

    public void start(Stage primaryStage) {

        primaryStage.setTitle("Note Pad");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
