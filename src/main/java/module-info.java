module eachclass.wellistillhavetofinish {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens eachclass.wellistillhavetofinish to javafx.fxml;
    exports eachclass.wellistillhavetofinish;
}