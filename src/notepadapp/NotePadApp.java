/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepadapp;

import java.awt.Desktop;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author mshawkat
 */
public class NotePadApp extends Application {

    MenuBar menuBar;
    Menu file;
    MenuItem newItem;
    MenuItem openItem;
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
    String InternalClipBorard = "";
    String oldText = "";

    @Override
    public void init() throws Exception {
        menuBar = new MenuBar();

        // Menu File Intialization
        file = new Menu("File");
        newItem = new MenuItem("New");
        openItem = new MenuItem("Open");
        saveItem = new MenuItem("Save");
        exitItem = new MenuItem("Exit");
        file.getItems().addAll(newItem, openItem, saveItem, new SeparatorMenuItem(), exitItem);

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

        super.init();
    }

    public void start(Stage primaryStage) {

        //Event Handling
        //New Item Handling
        newItem.setAccelerator(KeyCombination.keyCombination("Alt+N"));
        newItem.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textArea.clear();
            }

        });

        //New Item Handling
        newItem.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textArea.clear();
            }

        });

        //Open Item Handling
        openItem.setAccelerator(KeyCombination.keyCombination("Alt+O"));
        openItem.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textArea.clear();
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                File file = fileChooser.showOpenDialog(primaryStage);
                if (file != null) {
                    try {
                        DataInputStream dis = new DataInputStream(new FileInputStream(file));
                        String temp = "";
                        while (dis.available() > 0) {
                            textArea.appendText(dis.readLine().toString());
                            textArea.appendText("\n");
                        }

                    } catch (FileNotFoundException ex) {
                        System.out.println(ex.getMessage());
                    } catch (IOException ex) {
                        Logger.getLogger(NotePadApp.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                }
            }

        });

        //Save Item Handling
        saveItem.setAccelerator(KeyCombination.keyCombination("Alt+S"));
        saveItem.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                File file = fileChooser.showSaveDialog(primaryStage);
                try {
                    PrintStream ps = new PrintStream(new FileOutputStream(file));
                    String temp = "";
                    ps.print(textArea.getText());
                } catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                }
            }

        });

        //Undo Item Handling
        undoItem.setAccelerator(KeyCombination.keyCombination("Alt+U"));
        undoItem.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textArea.undo();
                //textArea.setText(oldText);
            }

        });

        //Copy Item Handling
        copyItem.setAccelerator(KeyCombination.keyCombination("Alt+C"));
        copyItem.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                InternalClipBorard = textArea.getSelectedText();
            }

        });

        //Cut Item Handling
        cutItem.setAccelerator(KeyCombination.keyCombination("Alt+M"));
        cutItem.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                InternalClipBorard = textArea.getSelectedText();
                textArea.deleteText(textArea.getSelection());
            }

        });

        //Paste Item Handling
        pasteItem.setAccelerator(KeyCombination.keyCombination("Alt+P"));
        pasteItem.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textArea.insertText(textArea.getCaretPosition(), InternalClipBorard);
            }

        });

        //SelectAll Item Handling
        selectAllItem.setAccelerator(KeyCombination.keyCombination("Alt+A"));
        selectAllItem.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textArea.selectAll();

            }
        });

        textArea.textProperty().addListener((observable, oldValue, newValue) -> {
            oldText = oldValue;
        });
        //Show Help Item Handling
        aboutNoteItem.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("NotePad HELP");
                alert.setHeaderText("Info");
                alert.setContentText("Mohamed Shawkat" + "\n" + "email: mohamedshawkat95@gmail.com" + "\n" + "linkedin :www.linkedin.com/in/mohshawkat");
                alert.showAndWait();
            }
        });
        exitItem.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });

        primaryStage.setTitle("Note Pad");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
