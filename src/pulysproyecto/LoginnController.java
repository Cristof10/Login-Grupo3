package pulysproyecto;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class LoginnController implements Initializable {

    @FXML
    private JFXButton btnIngresar;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsuario;


    public LoginnController() {
    }

    public void initialize(URL location, ResourceBundle resources){


    }

    @FXML
    void btnIngresarClick(MouseEvent event) {
        FXMLVistaController bdAccess = new FXMLVistaController();
        String user = txtUsuario.getText();
        String password = txtPassword.getText();
        String userBD="";
        String passwordBD="";


        try{

            Connection connection = DriverManager.getConnection(bdAccess.getUrl(), bdAccess.getUser(), bdAccess.getPassword());
            Statement stmt = connection.createStatement();
            PreparedStatement statement = connection.prepareStatement("SELECT Usuario, Contrasena " +
                    "FROM CredencialesLogIn " +
                    "WHERE Usuario=? AND Contrasena=?");
            statement.setString(1 , user);
            statement.setString(2 , password);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                 userBD = resultSet.getString("Usuario");
                 passwordBD =resultSet.getString("Contrasena");
            }

            resultSet.close();
            stmt.close();
            connection.close();

        }catch (SQLException e){
            System.out.println("NO CONEXION");
        }

        if(user.equals(userBD) && password.equals(passwordBD) && !user.isEmpty() && !password.isEmpty()){
            try{
                Stage loginStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("FXMLVista.fxml"));
                Scene scene = new Scene(root);
                loginStage.setScene(scene);
                loginStage.show();

                ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
            }catch (Exception e){
            }
        }else{
            JOptionPane.showMessageDialog(null, "Las credenciales ingresadas son incorrectas", "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
        }

    }






}
