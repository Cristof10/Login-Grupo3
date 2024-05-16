/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template mensaje de prueba
 */
package pulysproyecto;
import java.text.DecimalFormat;
import java.time.LocalDate;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class FXMLVistaController implements Initializable {

    private String url ="jdbc:mysql://localhost:3306/pulys";

    private String user = "root";

    private String password = "root";

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;}


    LocalDate currentDate = LocalDate.now();
    String dateString = currentDate.toString();
    LocalTime currenttime = LocalTime.now();
    String timeString = currentDate.toString();
    servicios sv;
    servicios sv2;



    short numControlServicios = 0;
    short numControlClientes = 0;
    short numControlInventario = 0;
    short numControlCobros=0;
    short numControlParametros=0;
    double panelClientesPosicionX,
            panelServiciosPosicionX,
            panelInventarioPosicionX ,
            panelCobrosPosicionX ,
            panelParametrosPosicionX;
    int indexTablaClientesMascotas;

    int indexClientes;


    @FXML
    private ImageView menu;

    @FXML
    public AnchorPane pane1, pane2,panelPrincipal;

    @FXML
    private JFXButton btnClientes,btnCobros,btnInventario,btnParametros,btnServicios;

    //Modulo Clientes---------------------------------------------------------------------------------------------------
    @FXML
    private javafx.scene.control.TextField fieldFechaIngreso, fieldNombreCliente, fieldTelefono;
    @FXML
    private JFXButton btnRegistrarCliente,btnActualizarClienteMascota;

    @FXML
    private javafx.scene.control.TextField fieldNombreMascota, fieldPeso,fieldRaza,fieldObservaciones;
    @FXML
    private JFXComboBox<String> cbSexo;

    @FXML
    private TableView tableViewCliente;
    @FXML
    private TableView tableViewClientes;


    //Tabla ClienteMascotas
    @FXML
    private TableColumn<clienteMascota, Integer> idClienteMascotaColumna;
    @FXML
    private TableColumn<clienteMascota, String> fechaColumna;
    @FXML
    private TableColumn<clienteMascota, String> nombreClienteMascotaColumna;
    @FXML
    private TableColumn<clienteMascota, String> telefonoClienteMascotaColumna;
    @FXML
    private TableColumn<clienteMascota, String> nombrePacienteColumna;
    @FXML
    private TableColumn<clienteMascota, String> razaColumna;
    @FXML
    private TableColumn<clienteMascota, String> sexoColumna;
    @FXML
    private TableColumn<clienteMascota, Float> pesoColumna;
    @FXML
    private TableColumn<clienteMascota, String> observacionesColumna;
    @FXML
    private TableColumn<clienteMascota, Integer> idMascotaColumn;
    @FXML
    private javafx.scene.control.TextField fieldBuscarPorNombre;

    @FXML
    private javafx.scene.control.TextField fieldClienteActualizar,fieldTelefonoActualizar, fieldMascotaActualizar,fieldSexoActualizar, fieldRazaActualizar,
            fieldPesoActualizar,fieldObservacionActualizar;

    private int id_Cliente_Registrar_Mascota;



    //tabla Cliente
    @FXML
    private TableColumn<clienteRegistro, String> nombreClienteColumna;
    @FXML
    private TableColumn<clienteRegistro, String> telefonoColumna;
    @FXML
    private TableColumn<clienteRegistro, Integer> idClienteColumna;






    @FXML
    private AnchorPane paneClientes,paneInventario,paneServicios,paneCobros,paneParametros;



    //variables ingresos inventario

    @FXML
    private TableView tableViewEgreMotivo;
    @FXML
    private TableView tableViewIngMotivo;

    @FXML
    private TableView tableviewBalanEgre;

    @FXML
    private TableView tableViewBalanBalanc;

    @FXML
    private TableView tableViewBalanIng;


    @FXML
    private DatePicker dpFechaFin;

    @FXML
    private DatePicker dpFechaInicio;

    @FXML
    private JFXButton btnEditarEgreso;

    @FXML
    private JFXButton btnEditarIngreso;

    @FXML
    private JFXButton btneditarinv;
    @FXML
    private JFXButton btninvproductoscad;
    //Tabla Inventario
    @FXML
    private TableColumn<inventarioRegistro, Integer>idInventarioColumna;
    @FXML
    private TableColumn<inventarioRegistro, String>nombre_productoColumna;
    @FXML
    private TableColumn<inventarioRegistro, String>inventarioCategoriaColumna;
    @FXML
    private TableColumn<inventarioRegistro, String>inventarioPrecioColumna;
    @FXML
    private TableColumn<inventarioRegistro, String>inventarioFechaVencColumna;

    // Tabla Ingresos y Egresos
    @FXML
    private TableColumn<ingresoegresoRegistro, String> horaingcolumna;

    @FXML
    private TableColumn<ingresoegresoRegistro, String> fechaingrcolumna;
    @FXML
    private TableColumn<ingresoegresoRegistro, Float> montoingcolumna;
    @FXML
    private TableColumn<ingresoegresoRegistro, String> motivoingcolumna;
    @FXML
    private TableColumn<ingresoegresoRegistro, String> motivobalaning;
    @FXML
    private TableColumn<ingresoegresoRegistro, Float> montobalaning;
    @FXML
    private TableColumn<ingresoegresoRegistro, String> horabalaning;
    @FXML
    private TableColumn<ingresoegresoRegistro, String> fechabalaning;
    @FXML
    private TableColumn<ingresoegresoRegistro, String> motivobalanegr;
    @FXML
    private TableColumn<ingresoegresoRegistro, Float> montobalanegr;
    @FXML
    private TableColumn<ingresoegresoRegistro, String> horabalanegr;
    @FXML
    private TableColumn<ingresoegresoRegistro, String> fechabalanegr;

    @FXML
    private TableColumn< BalanceConsulta,String> fechabalancfin= new TableColumn<>("Fecha Balance");
    @FXML
    private TableColumn< BalanceConsulta,String> motivobalanfin= new TableColumn<>("Motivo Balance");
    @FXML
    private TableColumn< BalanceConsulta,String> balancebalancfin= new TableColumn<>("Monto Balance");



    @FXML
    private TableColumn<ingresoegresoRegistro, String> horaegrcolumna;

    @FXML
    private TableColumn<ingresoegresoRegistro, String> fechaegrcolumna;
    @FXML
    private TableColumn<ingresoegresoRegistro, Float> montoegrcolumna;
    @FXML
    private TableColumn<ingresoegresoRegistro, String> motivoegrcolumna;


    @FXML
    private DatePicker dpInvFechaVenc;

    //Recursos Inventario
    @FXML
    private TableView TableViewInventario;

    @FXML
    private JFXButton btnInvConsultarID;

    @FXML
    private JFXButton btnInvConsultarNombre;

    @FXML
    private JFXButton btnInvRegistrar;


    // Modulo Ingresos y egresos
    @FXML
    private TextArea areaEgreMotivo;

    @FXML
    private TextArea areaIngMotivo;

    @FXML
    private JFXButton btnBalancConsul;
    @FXML
    private JFXButton btnEgreRegistrar;

    @FXML
    private JFXButton btnIngRegistrar;


    @FXML
    private TextField fieldBalanFinal;

    @FXML
    private TextField fieldBalanInicio;


    @FXML
    private TextField fieldEgreFecha;

    @FXML
    private TextField fieldEgreHora;

    @FXML
    private TextField fieldEgreMonto;

    @FXML
    private TextField fieldIDInventario;

    @FXML
    private TextField fieldIngFecha;

    @FXML
    private TextField fieldIngHora;

    @FXML
    private TextField fieldIngIDMascota;

    @FXML
    private TextField fieldIngMonto;

    @FXML
    private TextField fieldInvCategoria;

    @FXML
    private TextField fieldInvConsultarID;

    @FXML
    private TextField fieldInvConsultarNombre;

    @FXML
    private TextField fieldInvCosto;

    @FXML
    private TextField fieldInvNombre;

    @FXML
    private TextField fieldInvVencimiento;

    int index;

    FadeTransition fadeTransition = new FadeTransition(javafx.util.Duration.seconds(0.1), pane1);

    public FXMLVistaController() {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String fechaActualFormateada = fechaActual.format(formatoFecha);
        fieldFechaIngreso.setText(fechaActualFormateada);
        fieldFechaIngreso.setEditable(false);
        btnEditarIngreso.setVisible(false);
        btnEditarEgreso.setVisible(false);

        pane1.setVisible(false);

        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();



        TranslateTransition translateTransition = new TranslateTransition(javafx.util.Duration.seconds(0.1), pane2);
        translateTransition.setByX(-600);
        translateTransition.play();

        TranslateTransition translateTransitionClientes = new TranslateTransition(javafx.util.Duration.seconds(0.1), paneClientes);
        translateTransitionClientes.setByX(+1000);
        translateTransitionClientes.play();

        TranslateTransition translateTransitionInventario = new TranslateTransition(javafx.util.Duration.seconds(0.1), paneInventario);
        translateTransitionInventario.setByX(+1000);
        translateTransitionInventario.play();

        TranslateTransition translateTransitionServicios = new TranslateTransition(javafx.util.Duration.seconds(0.1), paneServicios);
        translateTransitionServicios.setByX(+1000);
        translateTransitionServicios.play();

        TranslateTransition translateTransitionCobros = new TranslateTransition(javafx.util.Duration.seconds(0.1), paneCobros);
        translateTransitionCobros.setByX(+1000);
        translateTransitionCobros.play();

        TranslateTransition translateTransitionParametros = new TranslateTransition(javafx.util.Duration.seconds(0.1), paneParametros);
        translateTransitionParametros.setByX(+1000);
        translateTransitionParametros.play();


        menu.setOnMouseClicked(event -> {

            pane1.setVisible(true);

            FadeTransition fadeTransition1 = new FadeTransition(javafx.util.Duration.seconds(0.1), pane1);
            fadeTransition1.setFromValue(0);
            fadeTransition1.setToValue(0.15);
            fadeTransition1.play();

            TranslateTransition translateTransition1 = new TranslateTransition(javafx.util.Duration.seconds(0.1), pane2);
            translateTransition1.setByX(+600);
            translateTransition1.play();

        });

        pane1.setOnMouseClicked(event -> {

            FadeTransition fadeTransition2 = new FadeTransition(javafx.util.Duration.seconds(0.1), pane1);
            fadeTransition2.setFromValue(0.15);
            fadeTransition2.setToValue(0);
            fadeTransition2.play();

            fadeTransition2.setOnFinished(event1 -> {
                pane1.setVisible(false);
            });

            TranslateTransition translateTransition1 = new TranslateTransition(javafx.util.Duration.seconds(0.1), pane2);
            translateTransition1.setByX(-600);
            translateTransition1.play();
        });

        paneServicios.setVisible(true);
        paneInventario.setVisible(true);




    }

    @FXML
    void btnClientesClick(MouseEvent event) {
        animatePanelCliente();
        inicializarComboBoxModuloClientes();
        fieldMascotaActualizar.setDisable(true);
        fieldClienteActualizar.setDisable(true);
        fieldSexoActualizar.setDisable(true);
        fieldRazaActualizar.setDisable(true);

    }

    @FXML
    void btnServiciosClick(MouseEvent event) {

        animatePanelServicios();
        tabPane_servicios.setTabMinWidth(-1);
        tabPane_servicios.setTabMaxWidth(-1);
        tabPane_servicios.setTabMinHeight(-1);
        tabPane_servicios.setTabMaxHeight(-1);

    }
    @FXML
    private javafx.scene.control.TabPane tabPane_servicios;

    @FXML
    private javafx.scene.control.Tab tab_ListarServicios,tab_RegistroServicios,tab_ActualizacionServicios;
    @FXML
    void btnInventarioClick(MouseEvent event) {
        animatePanelInventario();

    }

    @FXML
    void btnCobrosClick(MouseEvent event) {
        animatePanelCobros();
        fieldEgreFecha.setText(currentDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        fieldEgreHora.setText(currenttime.format(DateTimeFormatter.ofPattern("HH:mm")));
        fieldIngFecha.setText(currentDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        fieldIngHora.setText((currenttime.format(DateTimeFormatter.ofPattern("HH:mm"))));
        fieldEgreHora.setEditable(false);
        fieldEgreFecha.setEditable(false);
        fieldIngHora.setEditable(false);
        fieldIngFecha.setEditable(false);

    }

    @FXML
    void btnParametrosClick(MouseEvent event) {
        pane1.setVisible(true);
        FadeTransition fadeTransition1 = new FadeTransition(javafx.util.Duration.seconds(0.1), pane1);
        fadeTransition1.setFromValue(0.15);
        fadeTransition1.setToValue(0);
        fadeTransition1.play();
        fadeTransition1.setOnFinished(event1 -> {
            pane1.setVisible(false);
        });
        TranslateTransition translateTransition1 = new TranslateTransition(javafx.util.Duration.seconds(0.3), pane2);
        translateTransition1.setByX(-600);
        translateTransition1.play();
        //-------------------------------------------------------------------------------------------------------------

        if ((numControlParametros % 2) == 0) {
            TranslateTransition translateTransitionParametros = new TranslateTransition(javafx.util.Duration.seconds(0.5), paneParametros);
            translateTransitionParametros.setByX(-1000);

            translateTransitionParametros.play();
        } else {
            TranslateTransition translateTransitionParametros = new TranslateTransition(javafx.util.Duration.seconds(0.5), paneParametros);
            translateTransitionParametros.setByX(+1000);
            translateTransitionParametros.play();
        }

        numControlParametros++;
        panelParametrosPosicionX=paneParametros.getTranslateX();


    }

    @FXML
    void btnRegistrarClienteClick(MouseEvent event) {
        int seleccion1 = JOptionPane.showConfirmDialog(null, "¿Desea guardar los datos del cliente " + fieldNombreCliente.getText() + "? ", "Crear Cliente", JOptionPane.YES_NO_OPTION);
        String fechaIngreso = dateString;
        String nombreCliente= fieldNombreCliente.getText();
        String telefono = fieldTelefono.getText();
        if             ( (validarllenos(fieldNombreCliente))
                && (validarllenos(fieldTelefono))
                && (validaralnumericoTelefono(fieldTelefono))
                && (validarAlfabetico(fieldNombreCliente))
                &&(seleccion1 == JOptionPane.YES_OPTION)

        ){
            try {

                Connection connection = DriverManager.getConnection(url, user, password);
                Statement stmt = connection.createStatement();
                System.out.println("asdasd");
                PreparedStatement statement = connection.prepareStatement("INSERT INTO Clients (Client_Name, Client_Phone, fecha_registro) VALUES (?, ?, ?)");

                statement.setString(1, nombreCliente);
                statement.setString(2, telefono);
                statement.setString(3, dateString);

                int rowsInserted = statement.executeUpdate();

                connection.close();

                fieldTelefono.setText("");
                fieldNombreCliente.setText("");
                JOptionPane.showMessageDialog(null, "El registro se realizó con éxito ");



            }catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos. Por favor, compruebe su conexión a internet y vuelva a intentarlo más tarde.", "Error de conexión", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Se produjo un error inesperado. Por favor, póngase en contacto con el administrador del sistema.", "Error desconocido", JOptionPane.ERROR_MESSAGE);
            }
        }
        else {

        }
    }
    @FXML
    void btnRegistrarMascotaClick(MouseEvent event) {

        int seleccion1 = JOptionPane.showConfirmDialog(null, "¿Desea guardar los datos de la mascota " + fieldNombreMascota.getText() + "? ", "Registrar Mascota", JOptionPane.YES_NO_OPTION);
        String nombreMascota= fieldNombreMascota.getText();

        String sexo = cbSexo.getValue().toString();
        String raza = fieldRaza.getText();
        String observaciones = fieldObservaciones.getText();

        if             ( (validarllenos(fieldNombreMascota))
                        &&validarAlfabetico(fieldRaza)
                        &&validarLongitud250Caracteres(fieldObservaciones)
                &&(seleccion1 == JOptionPane.YES_OPTION)


        ){
            try {
                float peso = Float.parseFloat(fieldPeso.getText());
                Connection connection = DriverManager.getConnection(url, user, password);
                Statement stmt = connection.createStatement();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO Pets (Client_Id, Pet_Name, Pet_Sexo, Pet_Raza, Pet_Weight,Pet_Observaciones) VALUES (?, ?, ?,?,?,?)");
                statement.setInt(1, id_Cliente_Registrar_Mascota);
                statement.setString(2, nombreMascota);
                statement.setString(3, sexo);
                statement.setString(4, raza);
                statement.setFloat(5, peso);
                statement.setString(6, observaciones);

                int rowsInserted = statement.executeUpdate();

                connection.close();


                fieldNombreMascota.setText("");
                fieldRaza.setText("");
                fieldPeso.setText("");
                fieldObservaciones.setText("");
                JOptionPane.showMessageDialog(null, "El registro se realizó con éxito ");

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "El valor ingresado en el campo de peso no es un número válido. Por favor, ingrese un número válido.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos. Por favor, compruebe su conexión a internet y vuelva a intentarlo más tarde.", "Error de conexión", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Se produjo un error inesperado. Por favor, póngase en contacto con el administrador del sistema.", "Error desconocido", JOptionPane.ERROR_MESSAGE);
            }
        }
        else {

        }
    }
    @FXML
    void btnActualizarTablaClick(MouseEvent event) {

        try{

            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stmt = connection.createStatement();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Clients");
            ResultSet resultSet = statement.executeQuery();
            ObservableList<clienteRegistro> clientes = FXCollections.observableArrayList();
            while (resultSet.next()) {
                int id_cliente=resultSet.getInt("Client_Id");
                String nombreCliente = resultSet.getString("Client_Name");
                String telefono =resultSet.getString("Client_Phone");


                clienteRegistro cliente = new clienteRegistro(id_cliente,nombreCliente,telefono);
                clientes.add(cliente);
            }

            resultSet.close();
            stmt.close();
            connection.close();



            tableViewCliente.getColumns().clear();


            idClienteColumna = new TableColumn<>("Id");
            idClienteColumna.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());

            nombreClienteColumna = new TableColumn<>("Cliente");
            nombreClienteColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre_cliente()));

            telefonoColumna = new TableColumn<>("Telefono");
            telefonoColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNumero_telefono()));

            tableViewCliente.getColumns().add(idClienteColumna);
            tableViewCliente.getColumns().add(nombreClienteColumna);
            tableViewCliente.getColumns().add(telefonoColumna);

            tableViewCliente.setItems(clientes);

        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos. Por favor, compruebe su conexión a internet y vuelva a intentarlo más tarde.", "Error de conexión", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Se produjo un error inesperado. Por favor, póngase en contacto con el administrador del sistema.", "Error desconocido", JOptionPane.ERROR_MESSAGE);
        }

    }

    @FXML
    void buscarNombreKeyRealesed(KeyEvent event) {

        String nombre = fieldBuscarPorNombre.getText();
        try{

            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stmt = connection.createStatement();
            PreparedStatement statement = connection.prepareStatement("select Clients.fecha_registro ,Clients.Client_Id, Clients.Client_Name, Clients.Client_Phone ,Pets.Pet_Id ,Pets.Pet_Name ,  Pets.Pet_Sexo, Pets.Pet_Raza, Pets.Pet_Weight, Pets.Pet_Observaciones\n" +
                    "from Clients\n" +
                    "left join Pets on Clients.Client_Id = Pets.Client_Id WHERE Client_Name LIKE ?");
            statement.setString(1, nombre + "%");
            ResultSet resultSet = statement.executeQuery();
            ObservableList<clienteMascota> clienteMascotaColeccion = FXCollections.observableArrayList();

            while (resultSet.next()) {
                int idCliente = resultSet.getInt("Client_Id");
                String fechaIngreso = resultSet.getString("fecha_registro");
                String nombrePaciente =resultSet.getString("Pet_Name");
                String raza= resultSet.getString("Pet_Raza");
                String nombreCliente= resultSet.getString("Client_Name");
                String telefono = resultSet.getString("Client_phone");
                String sexo = resultSet.getString("Pet_Sexo");
                float peso = resultSet.getFloat("Pet_Weight");
                int petId = resultSet.getInt("Pet_Id");
                String observaciones = resultSet.getString("Pet_Observaciones");

                clienteMascota clienteMascotaObject = new clienteMascota(fechaIngreso,idCliente,nombreCliente,telefono,petId,nombrePaciente,sexo,raza, peso,observaciones);
                clienteMascotaColeccion.add(clienteMascotaObject);
            }

            resultSet.close();
            stmt.close();
            connection.close();

            tableViewClientes.getColumns().clear();

            fechaColumna = new TableColumn<>("Fecha de ingreso");
            fechaColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate()));

            idClienteMascotaColumna = new TableColumn<>("Id Cliente");
            idClienteMascotaColumna.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIdCliente()).asObject());

            nombreClienteMascotaColumna= new TableColumn<>("Cliente");
            nombreClienteMascotaColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombreCliente()));

            telefonoClienteMascotaColumna= new TableColumn<>("Teléfono");
            telefonoClienteMascotaColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()));

            idMascotaColumn = new TableColumn<>("Id Mascota");
            idMascotaColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIdMascota()).asObject());

            nombrePacienteColumna = new TableColumn<>("Mascota");
            nombrePacienteColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombreMascota()));

            sexoColumna = new TableColumn<>("Sexo");
            sexoColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSexo()));

            razaColumna = new TableColumn<>("Raza");
            razaColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRaza()));


            pesoColumna = new TableColumn<>("Peso (Kg)");
            pesoColumna.setCellValueFactory(cellData -> {
                float peso = cellData.getValue().getPeso();
                return new SimpleObjectProperty<Float>(peso);
            });

            observacionesColumna = new TableColumn<>("Observaciones");
            observacionesColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getObservaciones()));


            tableViewClientes.getColumns().add(fechaColumna);
            tableViewClientes.getColumns().add(idClienteMascotaColumna);
            tableViewClientes.getColumns().add(nombreClienteMascotaColumna);
            tableViewClientes.getColumns().add(telefonoClienteMascotaColumna);
            tableViewClientes.getColumns().add(nombrePacienteColumna);
            tableViewClientes.getColumns().add(sexoColumna);
            tableViewClientes.getColumns().add(razaColumna);
            tableViewClientes.getColumns().add(pesoColumna);
            tableViewClientes.getColumns().add(observacionesColumna);

            tableViewClientes.setItems(clienteMascotaColeccion);

        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos. Por favor, compruebe su conexión a internet y vuelva a intentarlo más tarde.", "Error de conexión", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Se produjo un error inesperado. Por favor, póngase en contacto con el administrador del sistema.", "Error desconocido", JOptionPane.ERROR_MESSAGE);
        }


    }
    @FXML
    void fieldBuscarPorNombreClick(MouseEvent event) {

        String nombre = fieldBuscarPorNombre.getText();
        try{

            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stmt = connection.createStatement();
            PreparedStatement statement = connection.prepareStatement("select Clients.fecha_registro ,Clients.Client_Id,Pets.Pet_Id ,Clients.Client_Name, Clients.Client_Phone , Pets.Pet_Name ,  Pets.Pet_Sexo, Pets.Pet_Raza, Pets.Pet_Weight, Pets.Pet_Observaciones\n" +
                    "from Clients\n" +
                    "left join Pets on Clients.Client_Id = Pets.Client_Id WHERE Client_Name LIKE ?");
            statement.setString(1, nombre + "%");
            ResultSet resultSet = statement.executeQuery();
            ObservableList<clienteMascota> clienteMascotaColeccion = FXCollections.observableArrayList();

            while (resultSet.next()) {
                int idCliente = resultSet.getInt("Client_Id");
                String fechaIngreso = resultSet.getString("fecha_registro");
                String nombrePaciente =resultSet.getString("Pet_Name");
                String raza= resultSet.getString("Pet_Raza");
                String nombreCliente= resultSet.getString("Client_Name");
                String telefono = resultSet.getString("Client_phone");
                String sexo = resultSet.getString("Pet_Sexo");
                float peso = resultSet.getFloat("Pet_Weight");
                int idMascota = resultSet.getInt("Pet_Id");
                String observaciones = resultSet.getString("Pet_Observaciones");

                clienteMascota clienteMascotaObject = new clienteMascota(fechaIngreso,idCliente,nombreCliente,telefono,idMascota,nombrePaciente,sexo,raza, peso,observaciones);
                clienteMascotaColeccion.add(clienteMascotaObject);
            }

            resultSet.close();
            stmt.close();
            connection.close();

            tableViewClientes.getColumns().clear();

            fechaColumna = new TableColumn<>("Fecha de ingreso");
            fechaColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate()));

            idClienteMascotaColumna = new TableColumn<>("Id Cliente");
            idClienteMascotaColumna.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIdCliente()).asObject());

            nombreClienteMascotaColumna= new TableColumn<>("Cliente");
            nombreClienteMascotaColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombreCliente()));

            telefonoClienteMascotaColumna= new TableColumn<>("Teléfono");
            telefonoClienteMascotaColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()));

            idMascotaColumn = new TableColumn<>("Id Mascota");
            idMascotaColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIdMascota()).asObject());

            nombrePacienteColumna = new TableColumn<>("Mascota");
            nombrePacienteColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombreMascota()));

            sexoColumna = new TableColumn<>("Sexo");
            sexoColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSexo()));

            razaColumna = new TableColumn<>("Raza");
            razaColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRaza()));


            pesoColumna = new TableColumn<>("Peso (Kg)");
            pesoColumna.setCellValueFactory(cellData -> {
                float peso = cellData.getValue().getPeso();
                return new SimpleObjectProperty<Float>(peso);
            });

            observacionesColumna = new TableColumn<>("Observaciones");
            observacionesColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getObservaciones()));


            tableViewClientes.getColumns().add(fechaColumna);
            tableViewClientes.getColumns().add(idClienteMascotaColumna);
            tableViewClientes.getColumns().add(nombreClienteMascotaColumna);
            tableViewClientes.getColumns().add(telefonoClienteMascotaColumna);
            tableViewClientes.getColumns().add(idMascotaColumn);
            tableViewClientes.getColumns().add(nombrePacienteColumna);
            tableViewClientes.getColumns().add(sexoColumna);
            tableViewClientes.getColumns().add(razaColumna);
            tableViewClientes.getColumns().add(pesoColumna);
            tableViewClientes.getColumns().add(observacionesColumna);

            tableViewClientes.setItems(clienteMascotaColeccion);

        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos. Por favor, compruebe su conexión a internet y vuelva a intentarlo más tarde.", "Error de conexión", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Se produjo un error inesperado. Por favor, póngase en contacto con el administrador del sistema.", "Error desconocido", JOptionPane.ERROR_MESSAGE);
        }

    }

    @FXML
    void tableViewClientesClick(MouseEvent event) {

        indexTablaClientesMascotas = tableViewClientes.getSelectionModel().getSelectedIndex();
        if(indexTablaClientesMascotas <=-1){
            return;
        }

        Object cellData;

// Actualizar campo de nombre de cliente
        cellData = nombreClienteMascotaColumna.getCellData(indexTablaClientesMascotas);
        if (cellData != null) {
            fieldClienteActualizar.setText(cellData.toString());
        } else {
            fieldClienteActualizar.setText("");
        }

// Actualizar campo de teléfono de cliente
        cellData = telefonoClienteMascotaColumna.getCellData(indexTablaClientesMascotas);
        if (cellData != null) {
            fieldTelefonoActualizar.setText(cellData.toString());
        } else {
            fieldTelefonoActualizar.setText("");
        }

// Actualizar campo de nombre de mascota
        cellData = nombrePacienteColumna.getCellData(indexTablaClientesMascotas);
        if (cellData != null) {
            fieldMascotaActualizar.setText(cellData.toString());
        } else {
            fieldMascotaActualizar.setText("");
        }

// Actualizar campo de sexo
        cellData = sexoColumna.getCellData(indexTablaClientesMascotas);
        if (cellData != null) {
            fieldSexoActualizar.setText(cellData.toString());
        } else {
            fieldSexoActualizar.setText("");
        }

// Actualizar campo de raza
        cellData = razaColumna.getCellData(indexTablaClientesMascotas);
        if (cellData != null) {
            fieldRazaActualizar.setText(cellData.toString());
        } else {
            fieldRazaActualizar.setText("");
        }

// Actualizar campo de peso
        cellData = pesoColumna.getCellData(indexTablaClientesMascotas);
        if (cellData != null) {
            fieldPesoActualizar.setText(cellData.toString());
        } else {
            fieldPesoActualizar.setText("");
        }

// Actualizar campo de observaciones
        cellData = observacionesColumna.getCellData(indexTablaClientesMascotas);
        if (cellData != null) {
            fieldObservacionActualizar.setText(cellData.toString());
        } else {
            fieldObservacionActualizar.setText("");
        }
    }
    @FXML
    void tableViewClienteClick(MouseEvent event) {

        indexClientes = tableViewCliente.getSelectionModel().getSelectedIndex();
        if(indexClientes <=-1){
            return;
        }
        id_Cliente_Registrar_Mascota = Integer.parseInt(String.valueOf(idClienteColumna.getCellData(indexClientes)));

    }
    @FXML
    void btnActualizarClienteMascotaClick(MouseEvent event) {
        int seleccion = JOptionPane.showConfirmDialog(null, "¿Desea guardar los cambios realizados?", "Guardar cambios", JOptionPane.YES_NO_OPTION);
        Object cellData;

        String telefonoAfter = fieldTelefonoActualizar.getText();
        String observacionAfter = fieldObservacionActualizar.getText();
        float pesoAfter=0;
        try{
            pesoAfter = Float.parseFloat(fieldPesoActualizar.getText());

        }catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El valor ingresado en el campo de peso no es un número válido. Por favor, ingrese un número válido.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if( (validarllenos(fieldTelefonoActualizar)
                && (validaralnumericoTelefono(fieldTelefonoActualizar))
                && (validarLongitud250Caracteres(fieldObservacionActualizar))
                &&(seleccion == JOptionPane.YES_OPTION))
        ) {
            int id = idClienteMascotaColumna.getCellData(indexTablaClientesMascotas);
            int idMascota = idMascotaColumn.getCellData(indexTablaClientesMascotas);

            String telefonoBefore;
            String observacionBefore;
            float pesoBefore;

            //telefono comprobacion de null
            cellData= telefonoClienteMascotaColumna.getCellData(indexTablaClientesMascotas);
            if (cellData != null) {
                telefonoBefore=(cellData.toString());
            } else {
                telefonoBefore="";
            }
            //observaciones comprobacion null
            cellData= observacionesColumna.getCellData(indexTablaClientesMascotas);
            if (cellData != null) {
                observacionBefore=(cellData.toString());
            } else {
                observacionBefore="";
            }
            //peso comprobacion null
            cellData= pesoColumna.getCellData(indexTablaClientesMascotas);
            if (cellData != null) {
                pesoBefore=Float.parseFloat(cellData.toString());
            } else {
                pesoBefore=0;
            }

            if(telefonoBefore.equals(telefonoAfter)){

            }else{
                //Actualiza la tabla Clients
                try {
                    Connection connection = DriverManager.getConnection(url, user, password);
                    Statement stmt = connection.createStatement();
                    PreparedStatement statement = connection.prepareStatement("UPDATE Clients SET Client_Phone = ? WHERE Client_Id = ?");
                    statement.setString(1, telefonoAfter);
                    statement.setInt(2, id);

                    int rowsInserted = statement.executeUpdate();

                    connection.close();
                    fieldTelefonoActualizar.setText("");
                    JOptionPane.showMessageDialog(null, "La actualización se realizó con éxito");

                } catch (SQLException e) {

                    System.out.println("NO CONEXION");
                }

            }

            if(pesoBefore==pesoAfter && observacionBefore.equals(observacionAfter)){
            }else{
                //Actualiza tabla Pets

                try {
                    Connection connection = DriverManager.getConnection(url, user, password);
                    Statement stmt = connection.createStatement();
                    PreparedStatement statement = connection.prepareStatement("UPDATE Pets SET Pet_Weight = ? , Pet_Observaciones=? WHERE Pet_Id = ?");
                    statement.setFloat(1, pesoAfter);
                    statement.setString(2, observacionAfter);
                    statement.setInt(3, idMascota);

                    int rowsInserted = statement.executeUpdate();

                    connection.close();
                    fieldObservacionActualizar.setText("");
                    fieldPesoActualizar.setText("");
                    JOptionPane.showMessageDialog(null, "La actualización se realizó con éxito ");




                } catch (SQLException e) {

                    System.out.println("NO CONEXION");
                }


            }


        }else {
            System.out.println("Error ");

        }

    }




    public void animatePanelCliente() {
        pane1.setVisible(true);
        FadeTransition fadeTransition1 = new FadeTransition(javafx.util.Duration.seconds(0.1), pane1);
        fadeTransition1.setFromValue(0.15);
        fadeTransition1.setToValue(0);
        fadeTransition1.play();
        fadeTransition1.setOnFinished(event1 -> {
            pane1.setVisible(false);
        });

        TranslateTransition translateTransition1 = new TranslateTransition(javafx.util.Duration.seconds(0.3), pane2);
        translateTransition1.setByX(-600);
        translateTransition1.play();

        if (panelServiciosPosicionX != 0) {
            TranslateTransition translateTransitionServicios = new TranslateTransition(javafx.util.Duration.seconds(0.5), paneServicios);
            translateTransitionServicios.setByX(+1000);
            translateTransitionServicios.play();
            panelServiciosPosicionX = 0;
            numControlServicios++;
        }
        if (panelCobrosPosicionX != 0) {
            TranslateTransition translateTransitionCobros = new TranslateTransition(javafx.util.Duration.seconds(0.5), paneCobros);
            translateTransitionCobros.setByX(+1000);
            translateTransitionCobros.play();
            panelCobrosPosicionX = 0;
            numControlCobros++;
        }
        if (panelInventarioPosicionX != 0) {
            TranslateTransition translateTransitionInventario = new TranslateTransition(javafx.util.Duration.seconds(0.5), paneInventario);
            translateTransitionInventario.setByX(+1000);
            translateTransitionInventario.play();
            panelInventarioPosicionX = 0;
            numControlInventario++;
        }
        if (panelParametrosPosicionX != 0) {
            TranslateTransition translateTransitionParametros = new TranslateTransition(javafx.util.Duration.seconds(0.5), paneParametros);
            translateTransitionParametros.setByX(+1000);
            translateTransitionParametros.play();
            panelParametrosPosicionX = 0;
            numControlParametros++;
        }

        if ((numControlClientes % 2) == 0) {
            TranslateTransition translateTransitionClientes = new TranslateTransition(javafx.util.Duration.seconds(0.5), paneClientes);
            translateTransitionClientes.setByX(-1000);
            translateTransitionClientes.play();
        } else {
            TranslateTransition translateTransitionClientes = new TranslateTransition(javafx.util.Duration.seconds(0.5), paneClientes);
            translateTransitionClientes.setByX(+1000);
            translateTransitionClientes.play();
        }
        numControlClientes++;
        panelClientesPosicionX=paneClientes.getTranslateX();

    }

    public void animatePanelServicios(){
        //Animacion menu desplegable izquierda-----
        pane1.setVisible(true);
        FadeTransition fadeTransition1 = new FadeTransition(javafx.util.Duration.seconds(0.1), pane1);
        fadeTransition1.setFromValue(0.15);
        fadeTransition1.setToValue(0);
        fadeTransition1.play();

        fadeTransition1.setOnFinished(event1 -> {
            pane1.setVisible(false);
        });

        TranslateTransition translateTransition1 = new TranslateTransition(javafx.util.Duration.seconds(0.3), pane2);
        translateTransition1.setByX(-600);
        translateTransition1.play();
        //-------------------------------------------------------------------------------------------------------------

        //Animacion panel servicios\



        FadeTransition fadeTransitionServicios = new FadeTransition(javafx.util.Duration.seconds(0.1), paneServicios);
        fadeTransitionServicios.setFromValue(0);
        fadeTransitionServicios.setToValue(1);
        fadeTransitionServicios.play();

        if (panelClientesPosicionX != 0) {
            TranslateTransition translateTransitionServicios = new TranslateTransition(javafx.util.Duration.seconds(0.5), paneClientes);
            translateTransitionServicios.setByX(+1000);
            translateTransitionServicios.play();
            panelClientesPosicionX = 0;
            numControlClientes++;
        }
        if (panelCobrosPosicionX != 0) {
            TranslateTransition translateTransitionCobros = new TranslateTransition(javafx.util.Duration.seconds(0.5), paneCobros);
            translateTransitionCobros.setByX(+1000);
            translateTransitionCobros.play();
            panelCobrosPosicionX = 0;
            numControlCobros++;
        }
        if (panelInventarioPosicionX != 0) {
            TranslateTransition translateTransitionInventario = new TranslateTransition(javafx.util.Duration.seconds(0.5), paneInventario);
            translateTransitionInventario.setByX(+1000);
            translateTransitionInventario.play();
            panelInventarioPosicionX = 0;
            numControlInventario++;
        }
        if (panelParametrosPosicionX != 0) {
            TranslateTransition translateTransitionParametros = new TranslateTransition(javafx.util.Duration.seconds(0.5), paneParametros);
            translateTransitionParametros.setByX(+1000);
            translateTransitionParametros.play();
            panelParametrosPosicionX = 0;
            numControlParametros++;
        }

        if ((numControlServicios % 2) == 0) {
            TranslateTransition translateTransitionServicios = new TranslateTransition(javafx.util.Duration.seconds(0.5), paneServicios);
            translateTransitionServicios.setByX(-1000);

            translateTransitionServicios.play();
        } else {
            TranslateTransition translateTransitionServicios = new TranslateTransition(javafx.util.Duration.seconds(0.5), paneServicios);
            translateTransitionServicios.setByX(+1000);
            translateTransitionServicios.play();
        }


        numControlServicios++;
        panelServiciosPosicionX=paneServicios.getTranslateX();
    }

    public void animatePanelInventario(){
        //Animacion menu desplegable izquierda
        pane1.setVisible(true);
        FadeTransition fadeTransition1 = new FadeTransition(javafx.util.Duration.seconds(0.1), pane1);
        fadeTransition1.setFromValue(0.15);
        fadeTransition1.setToValue(0);
        fadeTransition1.play();

        fadeTransition1.setOnFinished(event1 -> {
            pane1.setVisible(false);
        });
        TranslateTransition translateTransition1 = new TranslateTransition(javafx.util.Duration.seconds(0.3), pane2);
        translateTransition1.setByX(-600);
        translateTransition1.play();
        //-------------------------------------------------------------------------------------------------------------

        //Animacion panel inventario

        FadeTransition fadeTransitionServicios = new FadeTransition(javafx.util.Duration.seconds(0.1), paneInventario);
        fadeTransitionServicios.setFromValue(0);
        fadeTransitionServicios.setToValue(1);
        fadeTransitionServicios.play();

        if (panelClientesPosicionX != 0) {
            TranslateTransition translateTransitionServicios = new TranslateTransition(javafx.util.Duration.seconds(0.5), paneClientes);
            translateTransitionServicios.setByX(+1000);
            translateTransitionServicios.play();
            panelClientesPosicionX = 0;
            numControlClientes++;
        }
        if (panelCobrosPosicionX != 0) {
            TranslateTransition translateTransitionCobros = new TranslateTransition(javafx.util.Duration.seconds(0.5), paneCobros);
            translateTransitionCobros.setByX(+1000);
            translateTransitionCobros.play();
            panelCobrosPosicionX = 0;
            numControlCobros++;
        }
        if (panelServiciosPosicionX != 0) {
            TranslateTransition translateTransitionInventario = new TranslateTransition(javafx.util.Duration.seconds(0.5), paneServicios);
            translateTransitionInventario.setByX(+1000);
            translateTransitionInventario.play();
            panelServiciosPosicionX = 0;
            numControlServicios++;
        }
        if (panelParametrosPosicionX != 0) {
            TranslateTransition translateTransitionParametros = new TranslateTransition(javafx.util.Duration.seconds(0.5), paneParametros);
            translateTransitionParametros.setByX(+1000);
            translateTransitionParametros.play();
            panelParametrosPosicionX = 0;
            numControlParametros++;
        }

        if ((numControlInventario % 2) == 0) {
            TranslateTransition translateTransitionInventario = new TranslateTransition(javafx.util.Duration.seconds(0.5), paneInventario);
            translateTransitionInventario.setByX(-1000);

            translateTransitionInventario.play();
        } else {
            TranslateTransition translateTransitionInventario = new TranslateTransition(javafx.util.Duration.seconds(0.5), paneInventario);
            translateTransitionInventario.setByX(+1000);
            translateTransitionInventario.play();
        }


        numControlInventario++;
        panelInventarioPosicionX=paneInventario.getTranslateX();
    }

    public void animatePanelCobros(){
        pane1.setVisible(true);
        FadeTransition fadeTransition1 = new FadeTransition(javafx.util.Duration.seconds(0.1), pane1);
        fadeTransition1.setFromValue(0.15);
        fadeTransition1.setToValue(0);
        fadeTransition1.play();

        fadeTransition1.setOnFinished(event1 -> {
            pane1.setVisible(false);
        });
        TranslateTransition translateTransition1 = new TranslateTransition(javafx.util.Duration.seconds(0.3), pane2);
        translateTransition1.setByX(-600);
        translateTransition1.play();
        //-------------------------------------------------------------------------------------------------------------

        if (panelClientesPosicionX != 0) {
            TranslateTransition translateTransitionServicios = new TranslateTransition(javafx.util.Duration.seconds(0.5), paneClientes);
            translateTransitionServicios.setByX(+1000);
            translateTransitionServicios.play();
            panelClientesPosicionX = 0;
            numControlClientes++;
        }
        if (panelServiciosPosicionX != 0) {
            TranslateTransition translateTransitionCobros = new TranslateTransition(javafx.util.Duration.seconds(0.5), paneServicios);
            translateTransitionCobros.setByX(+1000);
            translateTransitionCobros.play();
            panelServiciosPosicionX = 0;
            numControlServicios++;
        }
        if (panelInventarioPosicionX != 0) {
            TranslateTransition translateTransitionInventario = new TranslateTransition(javafx.util.Duration.seconds(0.5), paneInventario);
            translateTransitionInventario.setByX(+1000);
            translateTransitionInventario.play();
            panelInventarioPosicionX = 0;
            numControlInventario++;
        }
        if (panelParametrosPosicionX != 0) {
            TranslateTransition translateTransitionParametros = new TranslateTransition(javafx.util.Duration.seconds(0.5), paneParametros);
            translateTransitionParametros.setByX(+1000);
            translateTransitionParametros.play();
            panelParametrosPosicionX = 0;
            numControlParametros++;
        }

        if ((numControlCobros % 2) == 0) {
            TranslateTransition translateTransitionCobros = new TranslateTransition(javafx.util.Duration.seconds(0.5), paneCobros);
            translateTransitionCobros.setByX(-1000);
            translateTransitionCobros.play();
        } else {
            TranslateTransition translateTransitionCobros = new TranslateTransition(javafx.util.Duration.seconds(0.5), paneCobros);
            translateTransitionCobros.setByX(+1000);
            translateTransitionCobros.play();
        }

        numControlCobros++;
        panelCobrosPosicionX=paneCobros.getTranslateX();
    }

    public boolean validarAlfabetico(TextField probar) {
        String texto = probar.getText();
        if (texto.matches("^[a-zA-ZñÑáéíóú ]{1,50}$")) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "El texto "+ probar.getText()+ " posee un carácter no permitido o es una cadena larga ","Error de validación",  JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }


    public boolean validaralnumericoTelefono(TextField probar){
        String texto = probar.getText();
        if (texto.matches("^[0-9+/]{1,10}$")) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "El texto "+ probar.getText()+ " posee un carácter no permitido ","Error de validación",  JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    public boolean validarllenos(TextField probar){
        if (probar != null) {
            String texto = probar.getText();
            if (!texto.isEmpty()){
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Uno de los campos se encuentra vacio /s por favor revise si todos los campos se encuentran llenos ",
                        "Error de validación",  JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "El campo de texto está vacío",
                    "Error de validación",  JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    public boolean validarLlenosd(DatePicker datePicker) {
 LocalDate fechaSeleccionada = datePicker.getValue();
 if (fechaSeleccionada != null) {
 return true;
 } else {
JOptionPane.showMessageDialog(null, "La fecha no ha sido seleccionada. Por favor, seleccione una fecha.",
                     "Error de validación", JOptionPane.ERROR_MESSAGE);
 return false;
 }
    }
    public boolean validarLongitudMaxima(TextField textField) {
        String texto = textField.getText();
        if (texto.length() <= 50) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "El texto "+textField.getText()+" excede el límite de caracteres permitidos.", "Error de validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }


    //SERVICIOS GARY

    @FXML
    private JFXButton btn_BuscarCita,btn_NuevaCita,btn_ActualizarCita,btn_EliminarCita,btn_GuardarCita,btn_CancelarCita;

    @FXML
    private javafx.scene.control.TextField txt_ConsultasCitas, txt_IDMascota,txt_ClienteAsociado, txt_IDCliente;
    @FXML
    private javafx.scene.control.TextArea txt_MotCita;
    @FXML
    private javafx.scene.control.ComboBox cb_MascotaCita;
    @FXML
    private CheckBox checkbox_AgendarCitaa,checkbox_ActualizarReg;
    @FXML
    private DatePicker txt_FechaCita;

    @FXML
    private TableView Table_CitasServicios;

    @FXML
    private TableColumn<servicios, Integer> IDCita;

    @FXML
    private TableColumn<servicios, Integer> IDCliente;

    @FXML
    private TableColumn<servicios, Integer> IDMascota;

    @FXML
    private TableColumn<servicios, String> MotivoCita;

    @FXML
    private TextField txt_ActualizaMascota, txt_ActualizaCliente;
    @FXML
    private DatePicker txt_ActualizarDate;

    @FXML
    private TextArea txt_ActualizarMotivoCita;
    @FXML
    void btn_NuevaCitaSClick(MouseEvent event) throws SQLException {
        tabPane_servicios.getSelectionModel().select(tab_RegistroServicios);
        sv = new servicios();
        sv.fillCombobox(cb_MascotaCita,txt_ClienteAsociado,txt_IDMascota,txt_IDCliente);


    }


    @FXML
    void checkbox_ActualizarRegClick(MouseEvent event) {
        if(checkbox_ActualizarReg.isSelected()){
            txt_ActualizarDate.setDisable(false);
            txt_ActualizarDate.setEditable(true);
        }else{
            txt_ActualizarDate.setDisable(true);
            txt_ActualizarDate.setEditable(false);
        }
    }
    @FXML
    void btn_ActualizarRegClick(MouseEvent event) {
        int id=0;
        boolean flag = false;
        String fechaFormateada;
        TableColumn<servicios, Integer> idColumn = (TableColumn<servicios, Integer>) Table_CitasServicios.getColumns().get(0);
        servicios serviID = (servicios) Table_CitasServicios.getSelectionModel().getSelectedItem();
        if (serviID != null) {
            id = idColumn.getCellData(serviID);
        }

        LocalDate fechaSeleccionada = txt_ActualizarDate.getValue();
        if (checkbox_ActualizarReg.isSelected()) {
            if (fechaSeleccionada == null) {
                fechaSeleccionada = LocalDate.now();
            }
            fechaFormateada = fechaSeleccionada.format(formatter);
            if(!comprobarFechaServicios(fechaFormateada)){
                return;
            }
        } else {
            fechaFormateada = fechaSeleccionada.format(formatter);
        }
        try {
            sv = new servicios();
            sv2 = new servicios();
            if(txt_ActualizarMotivoCita.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"No tiene que dejar campos vacíos");
                return;
            }else{
                flag = sv.actualizaService(txt_ActualizarMotivoCita.getText(),fechaFormateada,id);
            }
            if(flag){
                JOptionPane.showMessageDialog(null,"Datos Actualizados con éxito");
                sv2.actualizarTabla(Table_CitasServicios);
                tabPane_servicios.getSelectionModel().select(tab_ListarServicios);
                limpiarCampos();
            }else{
                JOptionPane.showMessageDialog(null,"Llene los campos necesario");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Se produjo un error al Actualizar los datos", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.toString());
        }
    }

    @FXML
    void btn_ActualizarCitaClick(MouseEvent event) {

        int id = 0;
        String NameMascota = null;
        String NameCliente = null;
        String CitaMoti = null;
        String fech = null;
        TableColumn<servicios, Integer> idColumn = (TableColumn<servicios, Integer>) Table_CitasServicios.getColumns().get(0);
        servicios serviID = (servicios) Table_CitasServicios.getSelectionModel().getSelectedItem();
        if (serviID != null) {
            id = idColumn.getCellData(serviID);
        }

        TableColumn<servicios, String> mascotaColumn = (TableColumn<servicios, String>) Table_CitasServicios.getColumns().get(3);
        servicios serviMascota= (servicios) Table_CitasServicios.getSelectionModel().getSelectedItem();
        if(serviMascota != null){
            NameMascota = mascotaColumn.getCellData(serviMascota);
        }

        TableColumn<servicios, String> clienteColumn = (TableColumn<servicios, String>) Table_CitasServicios.getColumns().get(4);
        servicios serviCliente = (servicios) Table_CitasServicios.getSelectionModel().getSelectedItem();
        if(serviCliente != null){
            NameCliente = clienteColumn.getCellData(serviCliente);
        }

        TableColumn<servicios, String> motivoColumn = (TableColumn<servicios, String>) Table_CitasServicios.getColumns().get(2);
        servicios serviMot = (servicios) Table_CitasServicios.getSelectionModel().getSelectedItem();
        if(serviMot != null){
            CitaMoti = motivoColumn.getCellData(serviMot);
        }

        TableColumn<servicios, String> fechaColumn = (TableColumn<servicios, String>) Table_CitasServicios.getColumns().get(1);
        servicios serviFech = (servicios) Table_CitasServicios.getSelectionModel().getSelectedItem();
        if(serviFech != null){
            fech = fechaColumn.getCellData(serviFech);
        }
        if (id <= 0) {
            JOptionPane.showMessageDialog(null,"Seleccione un registro a actualizar");
            return;
        } else if (id >0) {
            txt_ActualizaMascota.setText(NameMascota);
            txt_ActualizaCliente.setText(NameCliente);
            txt_ActualizarMotivoCita.setText(CitaMoti);
            txt_ActualizarDate.setValue(LocalDate.parse(fech));
            tabPane_servicios.getSelectionModel().select(tab_ActualizacionServicios);
        }
    }

    @FXML
    void btn_CancelarCitaClick(MouseEvent event) {

        tabPane_servicios.getSelectionModel().select(tab_ListarServicios);
        limpiarCampos();
    }

    @FXML
    void btn_EliminarCitaClick(MouseEvent event) throws SQLException {

        sv = new servicios();
        sv2 = new servicios();

        int id = 0;
        TableColumn<servicios, Integer> idColumn = (TableColumn<servicios, Integer>) Table_CitasServicios.getColumns().get(0);
        servicios servicioSeleccionado = (servicios) Table_CitasServicios.getSelectionModel().getSelectedItem();
        if (servicioSeleccionado != null) {
            id = idColumn.getCellData(servicioSeleccionado);
        }

        if (id <= 0) {
            JOptionPane.showMessageDialog(null,"Seleccione un registro a eliminar");
            return;
        } else if (id >0) {
            int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de borrar el servicio con ID: " + id + "?", "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if(confirmacion == JOptionPane.YES_OPTION){
                boolean flag = sv.deleteService(id);
                if(flag){
                    JOptionPane.showMessageDialog(null,"Registro eliminado exitosamente");
                    sv2.actualizarTabla(Table_CitasServicios);
                }else{
                    JOptionPane.showMessageDialog(null, "Se produjo un error", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @FXML
    void checkbox_AgendarCitaaClick(MouseEvent event) {
        if(checkbox_AgendarCitaa.isSelected()){
            txt_FechaCita.setDisable(false);
        }else{
            txt_FechaCita.setDisable(true);
        }
    }

    @FXML
    void limpiarCampos(){
        txt_MotCita.setText("");

        txt_ActualizaMascota.setText("");
        txt_ActualizaCliente.setText("");
        txt_ActualizarMotivoCita.setText("");
    }

    @FXML
    void btn_GuardarCitaClick(MouseEvent event) {
        boolean flag = false;
        String fechaFormateada;
        if (checkbox_AgendarCitaa.isSelected()) {
            LocalDate fechaSeleccionada = txt_FechaCita.getValue();
            if (fechaSeleccionada == null) {
                fechaSeleccionada = LocalDate.now();
            }
            fechaFormateada = fechaSeleccionada.format(formatter);
            if(!comprobarFechaServicios(fechaFormateada)){
                return;
            }
        } else {
            fechaFormateada = LocalDate.now().format(formatter);
        }

        if(checkbox_AgendarCitaa.isSelected()){
            if(sv.comprobarFilas(fechaFormateada) < 5 ){
            }else{
                JOptionPane.showMessageDialog(null,"No se puede agendar mas citas el día: "+fechaFormateada);
                return;
            }
        }

        try {
            String nameOfPet;
            if(cb_MascotaCita.getValue() != null && !txt_MotCita.getText().isEmpty()){
                nameOfPet = cb_MascotaCita.getValue().toString();
                sv = new servicios(fechaFormateada,txt_MotCita.getText(),Integer.parseInt(txt_IDMascota.getText()),Integer.parseInt(txt_IDCliente.getText()));
                sv2 = new servicios(fechaFormateada,txt_MotCita.getText(),txt_ClienteAsociado.getText(),nameOfPet);
            }else{
                JOptionPane.showMessageDialog(null,"Asegurese de llenar todos los campos ");
                return;
            }

            flag = sv.addService();
            if(flag){
                JOptionPane.showMessageDialog(null,"Datos ingresados con éxito");
                sv2.actualizarTabla(Table_CitasServicios);
                tabPane_servicios.getSelectionModel().select(tab_ListarServicios);
                limpiarCampos();
            }else{
                JOptionPane.showMessageDialog(null,"Asegurese de llenar todos los campos");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Se produjo un error al ingresar los datos", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.toString());
        }
    }

    @FXML
    void btn_BuscarCitaClick(MouseEvent event) {
        sv = new servicios();
        sv.ActualizarTablaPorConsulta(Table_CitasServicios,txt_ConsultasCitas.getText());
        int rowCount = Table_CitasServicios.getItems().size();

        if(rowCount ==0){
            JOptionPane.showMessageDialog(null,"No se encontraron resultados");
        }
    }
    @FXML
    void btn_CancelarActRegClick(MouseEvent event) {
        tabPane_servicios.getSelectionModel().select(tab_ListarServicios);
    }

    @FXML
    void txt_ConsultasCitasClick(MouseEvent event) {
        sv2 = new servicios();
        sv2.actualizarTabla(Table_CitasServicios);
    }

    boolean comprobarFechaServicios(String fech){
        LocalDate fechaComparar = LocalDate.parse(fech);
        LocalDate fechaActual = LocalDate.now();
        // Comparar las fechas
        if(fechaComparar.isBefore(fechaActual)) {
            JOptionPane.showMessageDialog(null,"La fecha tiene que ser igual o posterior a la fecha actual","ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if(fechaComparar.isAfter(fechaActual)) {
            return true;
        } else {
            return true;
        }
    }


    //INGRESOS EGERESO Y INVENTARIO JOSE

    @FXML
    void TableViewInventarioclick(MouseEvent event) {
        btneditarinv.setDisable(false);
        fieldInvVencimiento.setEditable(false);
        index = TableViewInventario.getSelectionModel().getSelectedIndex();
        if(index<=-1){
            return;
        }
        fieldInvCategoria.setText(inventarioCategoriaColumna.getCellData(index).toString());
        fieldInvNombre.setText(nombre_productoColumna.getCellData(index).toString());
        fieldInvCosto.setText(inventarioPrecioColumna.getCellData(index).toString());

        btnInvRegistrar.setDisable(true);

    }

    @FXML
    void btnEgreRegistrar(MouseEvent event) {
        String fechaingresoegr =  fieldEgreFecha.getText();
        String motivoegresoegr = areaEgreMotivo.getText();
        String horaegr= fieldEgreHora.getText();
        double montoegr= Double.parseDouble(fieldEgreMonto.getText()) ;
        String tipoegr= "Egreso";
        {
            try {

                Connection connection = DriverManager.getConnection(url, user, password);
                Statement stmt = connection.createStatement();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO caja ( Caja_TipoOperacion, Caja_Motivo, Caja_Monto, Caja_Fecha, Caja_Hora) VALUES (?, ?, ?,?,?)");
                statement.setString(1, tipoegr);
                statement.setString(2, motivoegresoegr);
                statement.setDouble(3, montoegr);
                statement.setString(4, fechaingresoegr);
                statement.setString(5, horaegr);

                int rowsInserted = statement.executeUpdate();

                connection.close();


                areaEgreMotivo.setText("");

                fieldEgreMonto.setText("");
                JOptionPane.showMessageDialog(null, "El registro se realizó con éxito ");



            } catch (SQLException e) {

                System.out.println("NO CONEXION");


            }
        }

        try{

            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stmt = connection.createStatement();
            PreparedStatement statement = connection.prepareStatement("select * from caja where Caja_TipoOperacion = \"Egreso\"");
            ResultSet resultSet = statement.executeQuery();
            ObservableList<ingresoegresoRegistro> ingresosegresos = FXCollections.observableArrayList();
            while (resultSet.next()) {
                int idcaja = resultSet.getInt("Caja_Id");
                String fechacaja = resultSet.getString("Caja_Fecha");
                String horacaja = resultSet.getString("Caja_Hora");
                Float monto = Float.parseFloat(resultSet.getString("Caja_Monto"));
                String cajaMotivo= resultSet.getString("Caja_Motivo");
                ingresoegresoRegistro ingresosegreso = new ingresoegresoRegistro(idcaja,fechacaja,horacaja,monto,cajaMotivo);
                ingresosegresos.add(ingresosegreso);
            }

            resultSet.close();
            stmt.close();
            connection.close();

            tableViewEgreMotivo.getColumns().clear();

            fechaegrcolumna = new TableColumn<>("Fecha Egreso");
            fechaegrcolumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechaingegr()));

            horaegrcolumna = new TableColumn<>("Hora Egreso");
            horaegrcolumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHoraingregr()));

            motivoegrcolumna = new TableColumn<>("Motivo Egreso ");
            motivoegrcolumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMotivoingregr()));




            tableViewEgreMotivo.getColumns().add(fechaegrcolumna);
            tableViewEgreMotivo.getColumns().add(horaegrcolumna);
            tableViewEgreMotivo.getColumns().add(motivoegrcolumna);


            tableViewEgreMotivo.setItems(ingresosegresos);

        }catch (SQLException e){
            System.out.println("NO CONEXION");
        }


    }

    @FXML
    void btnIngRegistrarClick(MouseEvent event) {
        int seleccion1 = JOptionPane.showConfirmDialog(null, "¿Desea guardar los datos del ingreso monetario "  + "? ", "Crear Ingreso", JOptionPane.YES_NO_OPTION);
        String fechaingresoing =  fieldIngFecha.getText();
        String motivoingresoing = areaIngMotivo.getText();
        String horaing= fieldIngHora.getText();
        double monto= Double.parseDouble(fieldIngMonto.getText()) ;
        String tipoing= "Ingreso";
        {
            try {
                JOptionPane.showMessageDialog(null, "El registro se realizó con éxito ");
                Connection connection = DriverManager.getConnection(url, user, password);
                Statement stmt = connection.createStatement();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO caja ( Caja_TipoOperacion, Caja_Motivo, Caja_Monto, Caja_Fecha, Caja_Hora) VALUES (?, ?, ?,?,?)");
                statement.setString(1, tipoing);
                statement.setString(2, motivoingresoing);
                statement.setDouble(3, (monto));
                statement.setString(4, fechaingresoing);
                statement.setString(5, horaing);

                int rowsInserted = statement.executeUpdate();

                connection.close();


                areaIngMotivo.setText("");

                fieldIngMonto.setText("");
                JOptionPane.showMessageDialog(null, "El registro se realizó con éxito ");



            } catch (SQLException e) {

                System.out.println("NO CONEXION");


            }
        }

        try{

            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stmt = connection.createStatement();
            PreparedStatement statement = connection.prepareStatement("select * from caja where Caja_TipoOperacion = \"Ingreso\"");
            ResultSet resultSet = statement.executeQuery();
            ObservableList<ingresoegresoRegistro> ingresosegresos = FXCollections.observableArrayList();
            while (resultSet.next()) {
                int idcajaing = resultSet.getInt("Caja_Id");
                String fechacajaing = resultSet.getString("Caja_Fecha");
                String horacajaing = resultSet.getString("Caja_Hora");
                Float montoing = Float.parseFloat(resultSet.getString("Caja_Monto"));
                String cajaMotivoing= resultSet.getString("Caja_Motivo");
                ingresoegresoRegistro ingresosegreso = new ingresoegresoRegistro(idcajaing,fechacajaing,horacajaing,montoing,cajaMotivoing);
                ingresosegresos.add(ingresosegreso);
            }

            resultSet.close();
            stmt.close();
            connection.close();

            tableViewIngMotivo.getColumns().clear();

            fechaingrcolumna = new TableColumn<>("Fecha Ingreso");
            fechaingrcolumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechaingegr()));

            horaingcolumna = new TableColumn<>("Hora Ingreso");
            horaingcolumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHoraingregr()));

            motivoingcolumna = new TableColumn<>("Motivo Ingreso ");
            motivoingcolumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMotivoingregr()));

            montoingcolumna = new TableColumn<>("Monto Egreso");
            montoingcolumna.setCellValueFactory(cellData -> {
                float peso1 = cellData.getValue().getMontoegr();
                return new SimpleObjectProperty<Float>(peso1);
            });




            tableViewIngMotivo.getColumns().add(fechaingrcolumna);
            tableViewIngMotivo.getColumns().add(horaingcolumna);
            tableViewIngMotivo.getColumns().add(montoingcolumna);
            tableViewIngMotivo.getColumns().add(motivoingcolumna);


            tableViewIngMotivo.setItems(ingresosegresos);

        }catch (SQLException e){
            System.out.println("NO CONEXION");
        }




    }

    @FXML
    void btnInvConsultarIDClick(MouseEvent event) {
        int inventarioId = Integer.parseInt(fieldInvConsultarID.getText());
        try {

            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stmt = connection.createStatement();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM inventario WHERE Inventario_Id = ?");
            statement.setInt(1, inventarioId );
            ResultSet resultSet = statement.executeQuery();
            ObservableList<inventarioRegistro> inventarios = FXCollections.observableArrayList();
            if (resultSet.next()) {

                int idinventario = resultSet.getInt("Inventario_Id");
                String fechavenc = resultSet.getString("Inventario_FechaVencimiento");
                String nombre_producto = resultSet.getString("Inventario_Nombre");
                String categoria = resultSet.getString("Inventario_Categoria");
                String costo = resultSet.getString("Inventario_CostoUnitario");
                inventarioRegistro inventario = new inventarioRegistro(idinventario, fechavenc, nombre_producto, categoria, costo);
                inventarios.add(inventario);


                TableViewInventario.getColumns().clear();

                idInventarioColumna = new TableColumn<>("Id");
                idInventarioColumna.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());

                inventarioFechaVencColumna = new TableColumn<>("Fecha Vencimiento");
                inventarioFechaVencColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechavencinventario()));

                nombre_productoColumna = new TableColumn<>("Producto");
                nombre_productoColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre_producto()));

                inventarioCategoriaColumna = new TableColumn<>("Categoria");
                inventarioCategoriaColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategoriaproducto()));

                inventarioPrecioColumna = new TableColumn<>("Costo");
                inventarioPrecioColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCosto_venta()));


                TableViewInventario.getColumns().add(idInventarioColumna);
                TableViewInventario.getColumns().add(inventarioFechaVencColumna);
                TableViewInventario.getColumns().add(nombre_productoColumna);
                TableViewInventario.getColumns().add(inventarioCategoriaColumna);
                TableViewInventario.getColumns().add(inventarioPrecioColumna);

                TableViewInventario.setItems(inventarios);

            }
            else {
                JOptionPane.showMessageDialog(null, "No se encontraron resultados", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }
            resultSet.close();
            stmt.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("NO CONEXION");
        }
    }

    @FXML
    void btnInvConsultarNombreClick(MouseEvent event) {
        String nombreinv = fieldInvConsultarNombre.getText();
        try{

            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stmt = connection.createStatement();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM inventario WHERE INVENTARIO_NOMBRE LIKE ?");
            statement.setString(1, nombreinv + "%");
            ResultSet resultSet = statement.executeQuery();
            ObservableList<inventarioRegistro> inventarios = FXCollections.observableArrayList();
            while (resultSet.next()) {
                int idinventario = resultSet.getInt("Inventario_Id");
                String fechavenc = resultSet.getString("Inventario_FechaVencimiento");
                String nombre_producto =resultSet.getString("Inventario_Nombre");
                String categoria= resultSet.getString("Inventario_Categoria");
                String costo= resultSet.getString("Inventario_CostoUnitario");


                inventarioRegistro inventario = new inventarioRegistro(idinventario,fechavenc,nombre_producto,categoria, costo);
                inventarios.add(inventario);
            }

            resultSet.close();
            stmt.close();
            connection.close();

            TableViewInventario.getColumns().clear();

            idInventarioColumna = new TableColumn<>("Id");
            idInventarioColumna.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());

            inventarioFechaVencColumna = new TableColumn<>("Fecha Vencimiento");
            inventarioFechaVencColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechavencinventario()));

            nombre_productoColumna = new TableColumn<>("Producto");
            nombre_productoColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre_producto()));

            inventarioCategoriaColumna = new TableColumn<>("Categoria");
            inventarioCategoriaColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategoriaproducto()));

            inventarioPrecioColumna = new TableColumn<>("Costo");
            inventarioPrecioColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCosto_venta()));



            TableViewInventario.getColumns().add(idInventarioColumna);
            TableViewInventario.getColumns().add(inventarioFechaVencColumna);
            TableViewInventario.getColumns().add(nombre_productoColumna);
            TableViewInventario.getColumns().add(inventarioCategoriaColumna);
            TableViewInventario.getColumns().add(inventarioPrecioColumna);

            TableViewInventario.setItems(inventarios);

        }catch (SQLException e){
            System.out.println("NO CONEXION");
        }

    }

    @FXML
    void btnInvRegistrarClick(MouseEvent event) {

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate dpInvFechaVencValue = dpInvFechaVenc.getValue();
        String fechFormateada = dpInvFechaVencValue == null ? null : dpInvFechaVencValue.format(formatter1);
        int n=1;
        int seleccion1 = JOptionPane.showConfirmDialog(null, "¿Desea guardar los datos del producto " + fieldInvNombre.getText() + "? ", "Crear Producto", JOptionPane.YES_NO_OPTION);
        if (seleccion1 == JOptionPane.YES_OPTION) {
            String costoVentaProdStr = fieldInvCosto.getText();
            double costoVentaProd = 0.0;
            if ((validarllenos(fieldInvCosto))&&(validarllenos(fieldInvNombre)) && (validarllenos(fieldInvCategoria))) {
                if (fechFormateada!=null ) {

                    if(dpInvFechaVencValue.isAfter(currentDate) || dpInvFechaVencValue.equals(currentDate)){
                        try {

                            String nombreProducto = fieldInvNombre.getText();
                            String categoriaproducto = fieldInvCategoria.getText();
                            costoVentaProd = Double.parseDouble(costoVentaProdStr);
                            String fechavencprod = fechFormateada;
                            Connection connection = DriverManager.getConnection(url, user, password);
                            Statement stmt = connection.createStatement();
                            PreparedStatement statement = connection.prepareStatement("INSERT INTO inventario ( Inventario_Nombre, Inventario_Categoria, Inventario_CostoUnitario, Inventario_FechaVencimiento) VALUES (?, ?, ?,NULLIF(?, ''))");
                            statement.setString(1, nombreProducto);
                            statement.setString(2, categoriaproducto);
                            statement.setDouble(3, costoVentaProd);
                            statement.setString(4, fechavencprod);
                            int rowsInserted = statement.executeUpdate();
                            connection.close();
                            fieldInvNombre.setText("");
                            fieldInvCategoria.setText("");
                            fieldInvCosto.setText("");
                            fieldInvVencimiento.setText("");
                            JOptionPane.showMessageDialog(null, "El registro se realizó con éxito ");

                        } catch (SQLException e) {
                            System.out.println("NO CONEXION");
                        }

                        try {

                            Connection connection = DriverManager.getConnection(url, user, password);
                            Statement stmt = connection.createStatement();
                            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Inventario");
                            ResultSet resultSet = statement.executeQuery();
                            ObservableList<inventarioRegistro> inventarios = FXCollections.observableArrayList();
                            while (resultSet.next()) {
                                int idinventario = resultSet.getInt("Inventario_Id");
                                String fechavenc = resultSet.getString("Inventario_FechaVencimiento");
                                String nombre_producto = resultSet.getString("Inventario_Nombre");
                                String categoria = resultSet.getString("Inventario_Categoria");
                                String costo = resultSet.getString("Inventario_CostoUnitario");


                                inventarioRegistro inventario = new inventarioRegistro(idinventario, fechavenc, nombre_producto, categoria, costo);
                                inventarios.add(inventario);
                            }

                            resultSet.close();
                            stmt.close();
                            connection.close();

                            TableViewInventario.getColumns().clear();

                            idInventarioColumna = new TableColumn<>("Id");
                            idInventarioColumna.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());

                            inventarioFechaVencColumna = new TableColumn<>("Fecha Vencimiento");
                            inventarioFechaVencColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechavencinventario()));

                            nombre_productoColumna = new TableColumn<>("Producto");
                            nombre_productoColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre_producto()));

                            inventarioCategoriaColumna = new TableColumn<>("Categoria");
                            inventarioCategoriaColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategoriaproducto()));

                            inventarioPrecioColumna = new TableColumn<>("Costo");
                            inventarioPrecioColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCosto_venta()));


                            TableViewInventario.getColumns().add(idInventarioColumna);
                            TableViewInventario.getColumns().add(inventarioFechaVencColumna);
                            TableViewInventario.getColumns().add(nombre_productoColumna);
                            TableViewInventario.getColumns().add(inventarioCategoriaColumna);
                            TableViewInventario.getColumns().add(inventarioPrecioColumna);

                            TableViewInventario.setItems(inventarios);

                        } catch (SQLException e) {
                            System.out.println("NO CONEXION");
                        }


                    }else{
                        JOptionPane.showMessageDialog(null, "La fecha de vencimiento es previa a la fecha actual", "Error", JOptionPane.ERROR_MESSAGE);
                        return;

                    }

                } else {
                    //JOptionPane.showMessageDialog(null, "Error en el ingreso del producto: \n La fecha de vencimiento es previa a la fecha actual");
                    try {

                        String nombreProducto = fieldInvNombre.getText();
                        String categoriaproducto = fieldInvCategoria.getText();
                        costoVentaProd = Double.parseDouble(costoVentaProdStr);
                        String fechavencprod = fechFormateada;
                        Connection connection = DriverManager.getConnection(url, user, password);
                        Statement stmt = connection.createStatement();
                        PreparedStatement statement = connection.prepareStatement("INSERT INTO inventario ( Inventario_Nombre, Inventario_Categoria, Inventario_CostoUnitario, Inventario_FechaVencimiento) VALUES (?, ?, ?,NULLIF(?, ''))");
                        statement.setString(1, nombreProducto);
                        statement.setString(2, categoriaproducto);
                        statement.setDouble(3, costoVentaProd);
                        statement.setString(4, fechavencprod);
                        int rowsInserted = statement.executeUpdate();
                        connection.close();
                        fieldInvNombre.setText("");
                        fieldInvCategoria.setText("");
                        fieldInvCosto.setText("");
                        fieldInvVencimiento.setText("");
                        JOptionPane.showMessageDialog(null, "El registro se realizó con éxito ");

                    } catch (SQLException e) {
                        System.out.println("NO CONEXION");
                    }

                    try {

                        Connection connection = DriverManager.getConnection(url, user, password);
                        Statement stmt = connection.createStatement();
                        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Inventario");
                        ResultSet resultSet = statement.executeQuery();
                        ObservableList<inventarioRegistro> inventarios = FXCollections.observableArrayList();
                        while (resultSet.next()) {
                            int idinventario = resultSet.getInt("Inventario_Id");
                            String fechavenc = resultSet.getString("Inventario_FechaVencimiento");
                            String nombre_producto = resultSet.getString("Inventario_Nombre");
                            String categoria = resultSet.getString("Inventario_Categoria");
                            String costo = resultSet.getString("Inventario_CostoUnitario");


                            inventarioRegistro inventario = new inventarioRegistro(idinventario, fechavenc, nombre_producto, categoria, costo);
                            inventarios.add(inventario);
                        }

                        resultSet.close();
                        stmt.close();
                        connection.close();

                        TableViewInventario.getColumns().clear();

                        idInventarioColumna = new TableColumn<>("Id");
                        idInventarioColumna.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());

                        inventarioFechaVencColumna = new TableColumn<>("Fecha Vencimiento");
                        inventarioFechaVencColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechavencinventario()));

                        nombre_productoColumna = new TableColumn<>("Producto");
                        nombre_productoColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre_producto()));

                        inventarioCategoriaColumna = new TableColumn<>("Categoria");
                        inventarioCategoriaColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategoriaproducto()));

                        inventarioPrecioColumna = new TableColumn<>("Costo");
                        inventarioPrecioColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCosto_venta()));


                        TableViewInventario.getColumns().add(idInventarioColumna);
                        TableViewInventario.getColumns().add(inventarioFechaVencColumna);
                        TableViewInventario.getColumns().add(nombre_productoColumna);
                        TableViewInventario.getColumns().add(inventarioCategoriaColumna);
                        TableViewInventario.getColumns().add(inventarioPrecioColumna);

                        TableViewInventario.setItems(inventarios);

                    } catch (SQLException e) {
                        System.out.println("NO CONEXION");
                    }





                }
            } else{JOptionPane.showMessageDialog(null, "Error en el ingreso del producto: \n Uno de los campos está vacío");
            }

            } else{JOptionPane.showMessageDialog(null, "Producto no ingresado");
        }


    }




    @FXML
    void btnInvEditarClick(MouseEvent event) {
        int opcion = JOptionPane.showConfirmDialog(null, "¿Desea almacenar los cambios realizados?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcion == JOptionPane.YES_OPTION) {
            btnInvRegistrar.setDisable(false);

            try {

                Connection connection = DriverManager.getConnection(url, user, password);
                Statement stmt = connection.createStatement();
                PreparedStatement statement = connection.prepareStatement("UPDATE inventario SET Inventario_Nombre = ?, Inventario_CostoUnitario = ? WHERE Inventario_Id = ?");
                statement.setString(1, fieldInvNombre.getText());
                statement.setString(2, fieldInvCosto.getText());
                statement.setInt(3, idInventarioColumna.getCellData(index));

                int rowsInserted = statement.executeUpdate();

                connection.close();
                fieldInvNombre.setText("");
                fieldInvCosto.setText("");
                fieldInvCategoria.setText("");
                fieldInvVencimiento.setText("");
                JOptionPane.showMessageDialog(null, "La actualización se realizó con éxito ");
                fieldInvCategoria.setEditable(true);

            } catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Problemas con la base de datos contacte a soporte ");
            }
        }else{
            fieldInvNombre.setText("");
            fieldInvCosto.setText("");
            fieldInvCategoria.setText("");
            fieldInvVencimiento.setText("");
            btnInvRegistrar.setDisable(false);
        }



    }


    @FXML
    void btnproductcadclick(MouseEvent event) {
        try{

            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stmt = connection.createStatement();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM inventario  WHERE Inventario_FechaVencimiento >= DATE_SUB(CURDATE(), INTERVAL 10 DAY) Order by Inventario_FechaVencimiento asc ");
            ResultSet resultSet = statement.executeQuery();
            ObservableList<inventarioRegistro> inventarios = FXCollections.observableArrayList();
            while (resultSet.next()) {
                int idinventario = resultSet.getInt("Inventario_Id");
                String fechavenc = resultSet.getString("Inventario_FechaVencimiento");
                String nombre_producto =resultSet.getString("Inventario_Nombre");
                String categoria= resultSet.getString("Inventario_Categoria");
                String costo= resultSet.getString("Inventario_CostoUnitario");


                inventarioRegistro inventario = new inventarioRegistro(idinventario,fechavenc,nombre_producto,categoria, costo);
                inventarios.add(inventario);
            }

            resultSet.close();
            stmt.close();
            connection.close();

            TableViewInventario.getColumns().clear();

            idInventarioColumna = new TableColumn<>("Id");
            idInventarioColumna.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());

            inventarioFechaVencColumna = new TableColumn<>("Fecha Vencimiento");
            inventarioFechaVencColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechavencinventario()));

            nombre_productoColumna = new TableColumn<>("Producto");
            nombre_productoColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre_producto()));

            inventarioCategoriaColumna = new TableColumn<>("Categoria");
            inventarioCategoriaColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategoriaproducto()));

            inventarioPrecioColumna = new TableColumn<>("Costo");
            inventarioPrecioColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCosto_venta()));



            TableViewInventario.getColumns().add(idInventarioColumna);
            TableViewInventario.getColumns().add(inventarioFechaVencColumna);
            TableViewInventario.getColumns().add(nombre_productoColumna);
            TableViewInventario.getColumns().add(inventarioCategoriaColumna);
            TableViewInventario.getColumns().add(inventarioPrecioColumna);

            TableViewInventario.setItems(inventarios);

        }catch (SQLException e){
            System.out.println("NO CONEXION");
        }
    }







    @FXML
    void btnBalancConsulClick(MouseEvent event) {
        try{
            LocalDate dpFechaFinValue = dpFechaFin.getValue();
            LocalDate dpFechaInicioValue = dpFechaInicio.getValue();
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String dpFechaFinValueS = dpFechaFinValue.format(formatter1);
            String dpFechaInicioValueS = dpFechaInicioValue.format(formatter1);
            if(validarLlenosd(dpFechaFin)&& validarLlenosd(dpFechaInicio)) {
                if (dpFechaInicioValue.isBefore(dpFechaFinValue) || dpFechaInicioValue.isEqual(dpFechaFinValue)) {

                    try {
                        String fechaInicio = dpFechaInicioValueS;
                        String fechaFin = dpFechaFinValueS;
                        Connection connection = DriverManager.getConnection(url, user, password);
                        Statement stmt = connection.createStatement();
                        PreparedStatement statement = connection.prepareStatement("select * from caja where Caja_TipoOperacion = \"Ingreso\" AND Caja_Fecha BETWEEN ? AND ? ");
                        statement.setString(1, fechaInicio);
                        statement.setString(2, fechaFin);
                        ResultSet resultSet = statement.executeQuery();
                        ObservableList<ingresoegresoRegistro> ingresosegresos = FXCollections.observableArrayList();
                        while (resultSet.next()) {
                            int idcajaing = resultSet.getInt("Caja_Id");
                            String fechacajaing = resultSet.getString("Caja_Fecha");
                            String horacajaing = resultSet.getString("Caja_Hora");
                            Float montoing = resultSet.getFloat("Caja_Monto");
                            String cajaMotivoing = resultSet.getString("Caja_Motivo");
                            ingresoegresoRegistro ingresosegreso = new ingresoegresoRegistro(idcajaing, fechacajaing, horacajaing, montoing, cajaMotivoing);
                            ingresosegresos.add(ingresosegreso);
                        }

                        resultSet.close();
                        stmt.close();
                        connection.close();

                        tableViewBalanIng.getColumns().clear();
                        fechabalaning = new TableColumn<>("Fecha Ingreso");
                        fechabalaning.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechaingegr()));

                        horabalaning = new TableColumn<>("Hora Ingreso");
                        horabalaning.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHoraingregr()));

                        motivobalaning = new TableColumn<>("Motivo Ingreso ");
                        motivobalaning.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMotivoingregr()));

                        montobalaning = new TableColumn<>("Monto Ingreso");
                        montobalaning.setCellValueFactory(cellData -> {
                            float peso1 = cellData.getValue().getMontoegr();
                            return new SimpleObjectProperty<Float>(peso1);
                        });


                        tableViewBalanIng.getColumns().add(fechabalaning);
                        tableViewBalanIng.getColumns().add(horabalaning);
                        tableViewBalanIng.getColumns().add(montobalaning);
                        tableViewBalanIng.getColumns().add(motivobalaning);


                        tableViewBalanIng.setItems(ingresosegresos);

                    } catch (SQLException e) {
                        System.out.println("NO CONEXION");
                    }


                    try {
                        String fechaInicio = dpFechaInicioValueS;
                        String fechaFin = dpFechaFinValueS;
                        Connection connection = DriverManager.getConnection(url, user, password);
                        Statement stmt = connection.createStatement();
                        PreparedStatement statement = connection.prepareStatement("select * from caja where Caja_TipoOperacion = \"Egreso\" AND Caja_Fecha BETWEEN ? AND ? ");
                        statement.setString(1, fechaInicio);
                        statement.setString(2, fechaFin);
                        ResultSet resultSet = statement.executeQuery();
                        ObservableList<ingresoegresoRegistro> ingresosegresos = FXCollections.observableArrayList();
                        while (resultSet.next()) {
                            int idcajaing = resultSet.getInt("Caja_Id");
                            String fechacajaing = resultSet.getString("Caja_Fecha");
                            String horacajaing = resultSet.getString("Caja_Hora");
                            Float montoing = resultSet.getFloat("Caja_Monto");
                            String cajaMotivoing = resultSet.getString("Caja_Motivo");
                            ingresoegresoRegistro ingresosegreso = new ingresoegresoRegistro(idcajaing, fechacajaing, horacajaing, montoing, cajaMotivoing);
                            ingresosegresos.add(ingresosegreso);
                        }

                        resultSet.close();
                        stmt.close();
                        connection.close();

                        tableviewBalanEgre.getColumns().clear();

                        fechabalanegr = new TableColumn<>("Fecha Egreso");
                        fechabalanegr.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechaingegr()));

                        horabalanegr = new TableColumn<>("Hora Egreso");
                        horabalanegr.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHoraingregr()));

                        motivobalanegr = new TableColumn<>("Motivo Egreso");
                        motivobalanegr.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMotivoingregr()));

                        montobalanegr = new TableColumn<>("Monto Egreso");
                        montobalanegr.setCellValueFactory(cellData -> {
                            float peso1 = cellData.getValue().getMontoegr();
                            return new SimpleObjectProperty<Float>(peso1);
                        });

                        tableviewBalanEgre.getColumns().add(fechabalanegr);
                        tableviewBalanEgre.getColumns().add(horabalanegr);
                        tableviewBalanEgre.getColumns().add(montobalanegr);
                        tableviewBalanEgre.getColumns().add(motivobalanegr);


                        tableviewBalanEgre.setItems(ingresosegresos);

                    } catch (SQLException e) {
                        System.out.println("NO CONEXION");
                    }


                    double ingresosa = 0;
                    try {
                        String fechaInicio = dpFechaInicioValueS;
                        String fechaFin = dpFechaFinValueS;
                        Connection connection = DriverManager.getConnection(url, user, password);
                        Statement stmt = connection.createStatement();
                        PreparedStatement statement = connection.prepareStatement("SELECT SUM(Caja_Monto) as Ingresos FROM caja WHERE Caja_TipoOperacion = 'Ingreso' AND Caja_Fecha BETWEEN ? AND ? ");
                        statement.setString(1, fechaInicio);
                        statement.setString(2, fechaFin);
                        ResultSet resultSeting = statement.executeQuery();
                        ingresosa = 0.0;
                        if (resultSeting.next()) {
                            ingresosa = resultSeting.getDouble("Ingresos");
                        }

                        resultSeting.close();
                        stmt.close();
                        connection.close();


                    } catch (SQLException e) {
                        System.out.println("NO CONEXION");
                    }
                    double egresoa = 0;
                    try {
                        String fechaInicio = dpFechaInicioValueS;
                        String fechaFin = dpFechaFinValueS;
                        Connection connection = DriverManager.getConnection(url, user, password);
                        Statement stmt = connection.createStatement();
                        PreparedStatement statement = connection.prepareStatement("SELECT SUM(Caja_Monto) as Egresos FROM caja WHERE Caja_TipoOperacion = 'Egreso' AND Caja_Fecha BETWEEN ? AND ? ");
                        statement.setString(1, fechaInicio);
                        statement.setString(2, fechaFin);
                        ResultSet resultSeting = statement.executeQuery();
                        egresoa = 0.0;
                        if (resultSeting.next()) {
                            egresoa = resultSeting.getDouble("Egresos");
                        }
                        double balance = ingresosa - egresoa;
                        String balance1 = String.valueOf(balance);
                        String fechasbalance = "De " + fechaInicio + " a " + fechaFin;
                        resultSeting.close();
                        stmt.close();
                        connection.close();


                        tableViewBalanBalanc.getColumns().clear();
                        ObservableList<BalanceConsulta> balances = FXCollections.observableArrayList();

                        BalanceConsulta balancecon = new BalanceConsulta(fechasbalance, "Consulta de ingresos y egresos", Float.parseFloat(balance1));
                        balances.add(balancecon);

                        fechabalancfin = new TableColumn<>("Fechas");
                        fechabalancfin.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechasbalanc()));

                        motivobalanfin = new TableColumn<>("Motivo Balance");
                        motivobalanfin.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMotivo()));

                        balancebalancfin = new TableColumn<>("Monto Balance");
                        balancebalancfin.setCellValueFactory(cellData -> {
                            float peso = cellData.getValue().getMontoingre();
                            return new SimpleObjectProperty<Float>(peso).asString();
                        });

                        tableViewBalanBalanc.getColumns().add(fechabalancfin);
                        tableViewBalanBalanc.getColumns().add(motivobalanfin);
                        tableViewBalanBalanc.getColumns().add(balancebalancfin);

                        tableViewBalanBalanc.setItems(balances);


                    } catch (SQLException e) {
                        System.out.println("NO CONEXION");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "La fecha ingresada no es permitida \n La fecha de inicio es mayor a la fecha final", "Error de validación", JOptionPane.ERROR_MESSAGE);

                }
            }else{}

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "La fecha ingresada no es permitida \n Hay valores nulos en las fechas ", "Error de validación", JOptionPane.ERROR_MESSAGE);

        }





    }

    @FXML
    void TableViewIngresosClick(MouseEvent event) {



        }








    @FXML
    void TableviewEgresosClick(MouseEvent event) {



    }

    @FXML
    void btnegreEditarClick(MouseEvent event) {



    }

    @FXML
    void btningEditarClick(MouseEvent event) {

    }









    public boolean validarDecimal(TextField probar) {
        String texto = probar.getText();
        try {
            float num = Float.parseFloat(texto);
            DecimalFormat df = new DecimalFormat("#.##");
            String formatted = df.format(num);
            if (formatted.matches("^\\d{1,3}(\\.\\d{1,2})?$") && num <= 999.99f) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "El número ingresado no cumple con las especificaciones (decimal(5,2))","Error de validación",  JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El valor ingresado no es un número válido","Error de validación",  JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean validarLongitud250Caracteres(TextField cadena) {
        String elemento = cadena.getText();
        if (elemento.length() <= 250) {
            return true;
        } else {
            return false;
        }
    }

    public void inicializarComboBoxModuloClientes(){
        if (!cbSexo.getItems().contains("Macho")) {
            cbSexo.getItems().add("Macho");
        }
        if (!cbSexo.getItems().contains("Hembra")) {
            cbSexo.getItems().add("Hembra");
        }

    }

}





