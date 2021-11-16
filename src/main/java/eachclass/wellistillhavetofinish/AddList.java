package eachclass.wellistillhavetofinish;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;

import java.util.HashMap;
/**
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Ivan Pavlov
 */

public class AddList {
public String TabName;
public static ObservableList<Items> TabList;
public ObservableList<Items> completeList;
public static ObservableList<Items> incompleteList;


    public String getTabName(){return this.TabName;}
    public ObservableList<Items> getTabList(){return this.TabList;}

    public void setTabName(String Name){this.TabName = Name;}
    public void setTabList(ObservableList<Items> FullList){ this.TabList = FullList;}

    public AddList(String Name,ObservableList<Items> FullList){
        this.TabName = Name;
        this.TabList = FullList;
        completeList= FXCollections.observableArrayList();
        incompleteList = FXCollections.observableArrayList();
        int size = FullList.size();
        for(int i=0; i<size;i++){
            if(FullList.get(i).Complete){
                this.completeList.add(FullList.get(i));
            }
            else{this.incompleteList.add(FullList.get(i));
        }

    }}

    public void AddAnItem(Items ItemToAdd){
        TabList.add(ItemToAdd);
        if(ItemToAdd.Complete==true){
            completeList.add(ItemToAdd);
        }
        if(ItemToAdd.Complete==false){
            incompleteList.add(ItemToAdd);
        }
    }

    public void newList(String Name){
        this.TabName = Name;
        this.TabList = FXCollections.observableArrayList();
    }

    public ObservableList<Items> getCompleteList(){
        return completeList;
    }

    public ObservableList<Items> getIncompleteList(){
        return incompleteList;
    }

    public AddList(){}

}
