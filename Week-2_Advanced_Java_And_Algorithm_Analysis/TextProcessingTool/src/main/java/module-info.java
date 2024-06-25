

module com.amponsem.textprocessingtool {
        requires javafx.controls;
        requires javafx.fxml;

        opens com.amponsem.textprocessingtool to javafx.fxml;
        opens com.amponsem.textprocessingtool.controllers to javafx.fxml;

        exports com.amponsem.textprocessingtool;
        exports com.amponsem.textprocessingtool.controllers;
}
