package eachclass.wellistillhavetofinish;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TabTester {
    @FXML
    public TabPane TabpaneTest;
    @FXML public Tab Tab1;
    @FXML public TabPane TesterPane;

    @FXML
    public void tabtester() throws IOException {
        TabpaneTest = new TabPane();
        Stage TestStage = new Stage();
        FXMLLoader fxmlLoaders = new FXMLLoader(HelloApplication.class.getResource("TabPaneTester.fxml"));
        Scene scenes = new Scene(fxmlLoaders.load(), 605, 400);
        TestStage.setTitle("Hello!");
        TestStage.setScene(scenes);
        TestStage.show();
    }
    @FXML
    public void LoadsTab(){
////        TabpaneTest = new TabPane();
//        Node init = new booboocontroller().initTabAndList();
////        Tabpane.getTabs().add(init);
//        TesterPane.getTabs().get(0).setContent(init);
//        TesterPane.getTabs().get(0).contentProperty().set(init);
//        TabpaneTest.getTabs().get(0).setContent(new booboocontroller().initTabAndList());

    }
}
