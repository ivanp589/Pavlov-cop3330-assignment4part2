package eachclass.wellistillhavetofinish;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ResourceBundle;

import static java.lang.System.out;

/**
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Ivan Pavlov
 */

public class booboocontroller implements Initializable {

//    public static ObservableList<Item> lister;//lister is the global list name,
    // each list gets its own individual list name which lister than stores

    @FXML public TableView<Items> tableView= new TableView<>();

    @FXML public TabPane Tabpane = new TabPane();
    @FXML public CheckBox Complete;
    @FXML public CheckBox Incomplete;


    //parse the sent string
    //create a new item with the parsed values
    //add the item to the current list
    public void addItem(String s, String s1, String s2) throws ParseException, IOException {
        LocalDate day = null;
        day = LocalDate.parse(s1);
        Boolean complete = Boolean.parseBoolean(s2);
        Items addItem = new Items(s,day,complete);
        CreateNewList();
        out.println(currentTab.getText());
        out.println(Tabpane.getTabs().size());
        TABSandTheirLists.get(currentTab.getGraphic().toString()).AddAnItem(addItem);

    }

    @FXML
    //load fxml file
    //open a new scene from the file
    //set title to the new window
    //how the scene
    //set up a throw if something goes wrong
    //end
    public int actionToDo(ActionEvent event) {       //opens a new scene when the add button is pressed
        //ADD
        try {
//            currentTab = determineCurrentTab();
//            Tab crnttb = determineCurrentTab();
//            out.println(currentTab.getText());
            out.println("is null? ");
            out.println((currentTab ==null));
            out.println(currentTab.getGraphic().toString());
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddingScene.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage= new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Add Item");
            stage.initModality(Modality.WINDOW_MODAL);

            stage.initOwner(((Node)event.getSource()).getScene().getWindow());
            stage.show();

            return 0;
        } catch (IOException e) {e.printStackTrace();return -1;}
    }


    //get the item that is passed to this function and add it to the list
    //determine list that is open
    //add the item to the list that is open.
    //end
//    @FXML
    public static void receiveData(Items u) {
        TABSandTheirLists.get(currentTab.getGraphic().toString()).AddAnItem(u);
    }




    //initialize the table
    //populate the list with some data for testing purposes
    //set the table to be editable
    //for each column set the column to look for the proper data
    //i.e. set DescColumn to look for Desc data
    //for each column set the column to be editable on commit to update the item based on the users edit.
    //end
    @Override
    public void initialize(URL url, ResourceBundle rb){
                Tabpane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler() {
            @Override
            public void handle(Event event) {
                currentTab = Tabpane.getSelectionModel().getSelectedItem();
//                out.println(".");
            }
        });





    }

    //test code start

    public static HashMap<String, AddList> TABSandTheirLists = new HashMap<String,AddList>();//staticmap of tab and their corresponding lists
    public static Tab currentTab;   //keeps track of the tab that is currently open

    //employ currenTab
    //get the list associated with the name of the current tab
    //return this list
    public ObservableList<Items> getCurrentList(){
        String CurrentTabName = currentTab.getGraphic().toString();//gets the name of the tab

        //name of the tab can be used to find the tab in the map list
        ObservableList<Items> CurrentList =  TABSandTheirLists.get(CurrentTabName).getTabList();//mapOfTabAndItems.get(CurrentTabName);//current list is null
        return CurrentList;

    }



    //creates the node that will subsequently populate each new tab created
    public Node createNewNode(ObservableList<Items> LIST){
        TableView newTable = new TableView<Items>();
        newTable.setId(String.valueOf("Tab"+TABSandTheirLists.size()));


        newTable.setEditable(true);

        TableColumn comp = new TableColumn<Items,Boolean>("Completed:");//following code sets up the table
        comp.setPrefWidth(75.00);
        TableColumn date = new TableColumn<Items,LocalDate>("Due Date:");
        date.setPrefWidth(75.00);
        TableColumn desc = new TableColumn<Items,String>("Description:");
        desc.setPrefWidth(454.4000183105469);
        newTable.getColumns().add(comp);
        newTable.getColumns().add(date);
        newTable.getColumns().add(desc);

        desc.setCellValueFactory(new PropertyValueFactory<>("Desc"));
        date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        comp.setCellValueFactory(new PropertyValueFactory<>("Complete"));
        Label label = new Label();

        final TextField textField = new TextField();
        TextField tstField = new TextField();
        newTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount()==2) {
                    textField.setText(label.getText());
                    out.println(newTable.getEditingCell());
                        textField.selectAll();
                    textField.requestFocus();

                }
            }
        });


        textField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                label.setText(textField.getText());
//                newTable.setGraphic(label);
            }
        });


        newTable.setItems(LIST);
        return newTable;
    }

    //test code end


    //button to manually refresh the table
    //set the list to the table
    //set checkboxes off.       //to not confuse the user
    //end
    @FXML void Refresher(){
        Complete.setSelected(false);
        Incomplete.setSelected(false);
        Tab CurrentTab = currentTab;//determineCurrentTab();
        ObservableList<Items> ThisList = TABSandTheirLists.get(CurrentTab.getGraphic().toString()).getTabList();
        currentTab.setContent(createNewNode(ThisList));
    }

    //Note: the edit functions work if a button is used to call them, but that seems inefficient
    //also not currently implemented that way

    //function to edit the description of an item
    //retrieve an item from the table
    //edit the value of the item retrieved
    //refresh the table
    public void editDescription(TableColumn.CellEditEvent<Items, String> todoItemStringCellEditEvent) {
        Items item = tableView.getSelectionModel().getSelectedItem();
        item.setDescription(todoItemStringCellEditEvent.getNewValue());
    }

    //function to edit the due date of an item
    //retrieve an item from the table
    //edit the value of the item retrieved
    //refresh the table
    public void editDueDate(TableColumn.CellEditEvent<Items, LocalDate> todoItemDateCellEditEvent) {
        Items item = tableView.getSelectionModel().getSelectedItem();
        item.setDueDate((todoItemDateCellEditEvent.getNewValue()));
    }

    //function to edit the completion status of an item
    //retrieve an item from the table
    //edit the value of the item retrieved
    //refresh the table
    public void editIsComplete(TableColumn.CellEditEvent<Items, Boolean> todoItemBooleanCellEditEvent) {
        Items item = tableView.getSelectionModel().getSelectedItem();
        item.setIsComplete((todoItemBooleanCellEditEvent.getNewValue()));
    }


    @FXML
    public void showCompleted(){
        //check if both checkboxes are on
        //if true set both to off
        //set the table with the completed items
        //tableView.setItems(getCompleted())
        //refresh the table
        if(Complete.isSelected() && Incomplete.isSelected()){
            Complete.selectedProperty().set(false);
            Incomplete.selectedProperty().set(false);
        }
        else if(Complete.isSelected()){
            Tab currentab = currentTab;//determineCurrentTab();
            ObservableList<Items> ThisList = TABSandTheirLists.get(currentab.getGraphic().toString()).getCompleteList();
            currentTab.setContent(createNewNode(ThisList));
        }

    }


    public void showIncomplete(){   //show incomplete checkbox triggers this
        //set the other checkbox to off or false
        //set the table with the incomplete items
        //tableView.setItems(getIncomplete())
        //refresh the table
        if(Complete.isSelected() && Incomplete.isSelected()){
            Complete.selectedProperty().set(false);
            Incomplete.selectedProperty().set(false);
        }
        else if(Incomplete.isSelected()) {
            Tab currentab = currentTab;//determineCurrentTab();
            ObservableList<Items> ThisList = TABSandTheirLists.get(currentab.getGraphic().toString()).getIncompleteList();
            currentTab.setContent(createNewNode(ThisList));
        }
    }

    @FXML
    public void CloseList() {       //close list button triggers this event
        //identify the tab that is currently open
        //close the tab that is currently open
        //note: could add saving functionality but not necessary
        currentTab = (Tabpane.getSelectionModel().getSelectedItem());
        TABSandTheirLists.remove(currentTab);
        Tabpane.getTabs().remove(currentTab);

    }

    @FXML
    public void CreateNewList() throws IOException {
        // create a Tab variable
        //initialize the tab variable...
        //set the name of the tab
        //create a new TableView
        //initialize the tableView to be consistent
        //set each column of the table
        //set the newTable to the content of the tab
        //add the newTab to the TabPane
        int X= TABSandTheirLists.size();
        ObservableList<Items> newList = FXCollections.observableArrayList();

        Node NewNode = createNewNode(newList);
        Tab newtab = new Tab();
        Label label = new Label("Tab "+X);//let the title of the tab be the name of the list we will be updating



        newtab.setGraphic(label);

        newtab.setContent(NewNode);
//        out.println(newtab.getGraphic().toString());
        AddList NewestList = new AddList(newtab.getGraphic().toString(),newList);
//        Items adder = add();
//        NewestList.AddAnItem(adder);
//        out.println(newtab.getGraphic().toString());
        final TextField textField = new TextField();
        TextField tstField = new TextField();
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount()==2) {
                    textField.setText(label.getText());
                    newtab.setGraphic(textField);
                    textField.selectAll();
                    textField.requestFocus();

                }
            }
        });


        textField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                label.setText(textField.getText());
                newtab.setGraphic(label);
            }
        });

        NewestList.RenameList(label.getText());
        TABSandTheirLists.put(newtab.getGraphic().toString(),NewestList);
        Tabpane.getTabs().add(newtab);
        currentTab = newtab;

    }

    @FXML
    void clearList(){
        //get the list of the list that is currently being viewed
        //clear this list, leave all others untouched
        getCurrentList().clear();
    }
    @FXML
    void removeItemFromList(){
        //have the user select an item from the table
        //store this item for removal
        //remove this item
        //leave all other items untouched
        if(Tabpane.getTabs().contains(currentTab)){
//            int i=0;//safe to delete
//            while(currentTab!= Tabpane.getTabs().get(i)){
//                i++;
//            }
            TableView<Items> items = (TableView<Items>) currentTab.getContent();

            Items item = items.getSelectionModel().getSelectedItem();//tableView.getSelectionModel().getSelectedItem();
            TABSandTheirLists.get(currentTab.getGraphic().toString()).RemoveAnItem(item);
        }
    }

    //following code is simplified to make this file smaller

    @FXML
    public void openList(){//might have to change
        //call openList in FileACtions
        ListFileActions actions = new ListFileActions();
        actions.openList();
    }




    @FXML
    void SaveCurrentList(){
        //call SaveCurrentList in FileActions
        ListFileActions actions = new ListFileActions();
        actions.SaveCurrentList();
    }

    @FXML
    void SaveAllOpenLists(){
        //call SaveAllOpenLists in FileActions
        ListFileActions actions = new ListFileActions();
        actions.SaveAllOpenLists();
    }


}

