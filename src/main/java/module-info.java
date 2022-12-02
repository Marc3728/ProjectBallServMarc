module com.example.projectballservmarc {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projectballservmarc to javafx.fxml;
    exports com.example.projectballservmarc;
}