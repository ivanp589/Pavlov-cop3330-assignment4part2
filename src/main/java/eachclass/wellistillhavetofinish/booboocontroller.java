package eachclass.wellistillhavetofinish;


import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ResourceBundle;

import static eachclass.wellistillhavetofinish.AddList.*;
import static java.lang.System.out;

/**
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Ivan Pavlov
 */

public class booboocontroller implements Initializable {

//    public static ObservableList<Item> lister;//lister is the global list name,
    // each list gets its own individual list name which lister than stores

    Stage stage;
    public static AddList bist;

    @FXML public TableView<Items> tableView= new TableView<>();

//    @FXML public TableColumn<Items,Boolean> CompColumn;
//
//    @FXML public TableColumn<Items, LocalDate> DateColumn;
//
//    @FXML public TableColumn<Items,String> DescColumn;

    @FXML public TabPane Tabpane = new TabPane();
    @FXML public CheckBox Complete;
    @FXML public CheckBox Incomplete;



    //find the number of initially open tabs
    //for(i=0; i < tabs open;i++)
    //store the  name of each tab
    //create an emptylist for each tab
    //add an element to the hashmap for each tab
    //end for
//    public void makeMapOfLists(){//creates a todolist list
//        int size = Tabpane.getTabs().size();
//
//        for(int i=0;i<size;i++){
//            String listName = Tabpane.getTabs().get(i).getText();
//            ObservableList<Items> list= FXCollections.observableArrayList();
//
////            new AddList(listName,list);
//        }
//    }

    //determine the tab that is currently being viewed
    //return the observable list located in the hashMap
//    public ObservableList<Items> getCurrentListnotworking(){
//        Tab currentTab = Tabpane.getTabs().get(Tabpane.getTabs().size());
////        Tab currentTab = determineTab();
//
//        String Tabname = currentTab.getText();
//        out.println(Tabname);//line to see if the tab is properly identified
//
//        return bist.getMap().get(Tabname);
//    }



    //parse the sent string
    //create a new item with the parsed values
    //add the item to the current list
    public void addItem(String s, String s1, String s2) throws ParseException {
        LocalDate day = null;
        day = LocalDate.parse(s1);
        Boolean complete = Boolean.parseBoolean(s2);
        Items addItem = new Items(s,day,complete);
        getCurrentList().add(addItem);
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
            out.println(currentTab.getText());
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

    //initialize the TabPane
    //make a hashMap of all open Lists
//    public void ListfxmlFileConteroller() {
//        bist = new AddList();
//        Tabpane = new TabPane();
//        makeMapOfLists();
//    }


    //get the item that is passed to this function and add it to the list
    //determine list that is open
    //add the item to the list that is open.
    //end
//    @FXML
    public static void receiveData(Items u) {
//        currentTab = Tabpane.getSelectionModel().getSelectedItem();
        TABSandTheirLists.get(currentTab.getText()).AddAnItem(u);
        out.println(currentTab.getText());
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

//        Tab blew = CreateNewTab();
//        currentTab = blew;
//        Tabpane.getTabs().add(blew);
//        out.println(Tabpane==null);
//        booboocontroller.Tabpane.getTabs().add(blew);



    }

    //test code start

    public static HashMap<String, AddList> TABSandTheirLists = new HashMap<String,AddList>();
    public static Tab currentTab;
//    public TableView<Items> editableTable = currentTab.getContent().isPressed();



    public void editTable(){

    }

//    public Tab CreateNewTab(){
//        int x =0;//need to make global
//        Tab NewestTab = new Tab("Tab "+x);
//        x++;
//        AddList Hist = new AddList(NewestTab.getText(),FXCollections.observableArrayList());
//        TABSandTheirLists.put(NewestTab.getText(),Hist);
//        Hist.AddAnItem(new Items("Descriptions",LocalDate.of(2021,9,13),false));
//
//        NewestTab.setContent(createNewNode(Hist.TabList));
//
//        return NewestTab;
//    }

    public ObservableList<Items> getCurrentList(){
        String CurrentTabName = currentTab.getText();//gets the name of the tab

        //name of the tab can be used to find the tab in the map list
        ObservableList<Items> CurrentList =  TABSandTheirLists.get(CurrentTabName).getTabList();//mapOfTabAndItems.get(CurrentTabName);//current list is null
        return CurrentList;

    }


    public Items add(){
        return new Items("flank",LocalDate.of(2021,9,13),true);

    }

    public Node createNewNode(ObservableList<Items> LIST){
        TableView newTable = new TableView<Items>();
        newTable.setId(String.valueOf("Tab"+TABSandTheirLists.size()));
        newTable.setEditable(true);
//        newTable.addEventHandler(SingleSelectionModel<Items>(
//                ) );


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


        newTable.setItems(LIST);
        return newTable;
    }

    //test code end


    //button to manually refresh the table
    //set the list to the table
    //set checkboxes off.       //to not confuse the user
    //end
    @FXML void Refresher(){
        Tab CurrentTab = currentTab;//determineCurrentTab();
        ObservableList<Items> ThisList = TABSandTheirLists.get(CurrentTab.getText()).getTabList();
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


    //all code after may have to be edited to work properly

//    public ObservableList<Items> getCompleted(){
//        //create a newItemList and populate it only with completed items
//        //for(i=0,i<sizeof( original list );i++)
//        //if(originalList.get(i).getComplete() == true)
//        //newItemList.add(original list element i)
//        //else continue
//        //return newItemList
//        ObservableList<Items> completeList= FXCollections.observableArrayList();
//        int size = getCurrentList().size();
//        for(int i=0; i<size;i++){
//            ObservableList<Items> current = getCurrentList();
//            if(current.get(i).Complete == true){
//                completeList.add(current.get(i));
//            }
//        }
//        return completeList;
//    }
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
            ObservableList<Items> ThisList = TABSandTheirLists.get(currentab.getText()).getCompleteList();
            currentTab.setContent(createNewNode(ThisList));
        }

    }
//    public ObservableList<Items> getIncomplete(){
//        //create a newItemList and populate it with only incomplete items
//        //for(i=0,i<sizeof(originalList);i++)
//        //if(originalList.get(i).getComplete() == false)
//        //newItem.add(original list element i)
//        //else continue
//        //return newItemList
//        ObservableList<Items> incompleteList= FXCollections.observableArrayList();
//        int size = getCurrentList().size();
//        for(int i=0; i<size;i++){
//            ObservableList<Items> current = getCurrentList();
//            if(current.get(i).Complete == false){
//                incompleteList.add(current.get(i));
//            }
//        }
//        return incompleteList;
//    }

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
            ObservableList<Items> ThisList = TABSandTheirLists.get(currentab.getText()).getIncompleteList();
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






//        out.println(newtab);
        newtab.setContent(NewNode);
        AddList NewestList = new AddList(newtab.getText(),newList);
        Items adder = add();
        NewestList.AddAnItem(adder);
//        NewestList.AddAnItem(adder);
//        out.println(NewestList.getTabList().get(0).getDate());
//            boolean vel =NewestList.getTabList().contains(adder);
//            out.println(String.format("does tablist contain the value? "+vel));
    //        newList.add(add());
        TABSandTheirLists.put(newtab.getText(),NewestList);
        Tabpane.getTabs().add(newtab);
        currentTab = newtab;

    }

    @FXML
    void RenameList(ActionEvent event){
        //open a new window which asks for a new list name
        //store this list name as the name of the list in ListData
        //setListName();
        //CALL saveCurrentList()


            Tab workspaceTab = new Tab();
            workspaceTab.setText("New Workspace");
            Tabpane.getTabs().addAll(workspaceTab);
            Tabpane.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);




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
            TABSandTheirLists.get(currentTab.getText()).RemoveAnItem(item);
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

