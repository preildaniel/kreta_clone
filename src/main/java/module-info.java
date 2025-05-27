module com.example.kreta_clone {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.kreta_clone to javafx.fxml;
    exports com.example.kreta_clone;
}