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

        /*
        CASO FALLO 1: El programa permite ingresa cuando los campos de usuario y contraseña estan vacios.
        if(user.equals(userBD) && password.equals(passwordBD)){
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
        */



        /*
        CASO FALLO 2: El programa a pesar de ingresar con las credenciales correctas muestra un mensaje de error
        if(user.equals(userBD) && password.equals(passwordBD) && !user.isEmpty() && !password.isEmpty()){
            try{
                Stage loginStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("FXMLVista.fxml"));
                Scene scene = new Scene(root);
                loginStage.setScene(scene);
                loginStage.show();
                JOptionPane.showMessageDialog(null, "Las credenciales ingresadas son incorrectas", "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
                ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

            }catch (Exception e){
            }
        }else{
            JOptionPane.showMessageDialog(null, "Las credenciales ingresadas son incorrectas", "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
        }

        */


        /*
        CORRECCION FALLO 1: Cuando se envian campos vacíos no permite ingresar a la aplicación

        Este problema detallado en el CASO FALLO 1 se soluciona al momento de colocar las condiciones correctas dentro del "if" respectivo, tal cual como se
        muestra en el código funcional a continuación.
        
        CORRECCION FALLO 2: Cuando ingreso a la aplicación de forma correcta no me muestra ningún mensaje de error.

        Este problema detallado en el CASO FALLO 2 se soluciona al eliminar la linea de código:

        "JOptionPane.showMessageDialog(null, "Las credenciales ingresadas son incorrectas", "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);"

        Esta linea de código al estar presente en el caso correcto del condicional tambien se mostraba generando un defecto del sistema

         */

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
