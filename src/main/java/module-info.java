module com.mycompany.proyecto_ed_photos_p1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.proyecto_ed_photos_p1 to javafx.fxml;
    opens com.mycompany.modelo to javafx.base;
    exports com.mycompany.proyecto_ed_photos_p1;
}
