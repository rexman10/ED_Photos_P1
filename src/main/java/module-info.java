module com.mycompany.proyecto_ed_photos_p1 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.proyecto_ed_photos_p1 to javafx.fxml;
    exports com.mycompany.proyecto_ed_photos_p1;
}
