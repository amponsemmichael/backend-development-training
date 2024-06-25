module com.amponsem.textprocessingtool {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.amponsem.textprocessingtool to javafx.fxml;
    exports com.amponsem.textprocessingtool;
}