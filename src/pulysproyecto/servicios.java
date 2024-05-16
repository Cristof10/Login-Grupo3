package pulysproyecto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.sql.*;
import java.util.HashMap;
import java.awt.event.ActionListener;
import javafx.event.EventHandler;
public class servicios {

    private int Cita_Id;
    private String fecha;
    private String citaMotivo;
    private int pet_Id;
    private int Client_Id;
    private String Pet_Name;
    private String Client_Name;
    FXMLVistaController fx = new FXMLVistaController();
    public servicios() {
    }

    public servicios(String fecha, String citaMotivo, int pet_Id, int client_Id) {
        this.fecha = fecha;
        this.citaMotivo = citaMotivo;
        this.pet_Id = pet_Id;
        Client_Id = client_Id;
    }

    public servicios(String fecha, String citaMotivo, String pet_Name, String Client_Name) {
        this.fecha = fecha;
        this.citaMotivo = citaMotivo;
        this.Pet_Name = pet_Name;
        this.Client_Name = Client_Name;
    }

    public int getCita_Id() {
        return Cita_Id;
    }

    public void setCita_Id(int cita_Id) {
        Cita_Id = cita_Id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCitaMotivo() {
        return citaMotivo;
    }

    public void setCitaMotivo(String citaMotivo) {
        this.citaMotivo = citaMotivo;
    }

    public int getPet_Id() {
        return pet_Id;
    }

    public void setPet_Id(int pet_Id) {
        this.pet_Id = pet_Id;
    }

    public int getClient_Id() {
        return Client_Id;
    }

    public void setClient_Id(int client_Id) {
        Client_Id = client_Id;
    }
    public String getPet_Name() {
        return Pet_Name;
    }

    public void setPet_Name(String pet_Name) {
        Pet_Name = pet_Name;
    }

    public String getClient_Name() {
        return Client_Name;
    }

    public void setClient_Name(String client_Name) {
        Client_Name = client_Name;
    }

    //METODOS PARA SQL



    public void fillCombobox(ComboBox jComboBox, TextField nameClient, TextField IdPet, TextField idclient) throws SQLException {
        try(Connection connection = DriverManager.getConnection(fx.getUrl(), fx.getUser(), fx.getPassword());
            Statement stmt = connection.createStatement()){

            //Primero creo la tabla con el id, el nombre de la mascota y el nombre del cliente
            String query = "select pets.Pet_Id, pets.Pet_Name,clients.Client_Name, clients.Client_Id from clients join pets on pets.Client_Id = clients.Client_Id;";
            ResultSet rs = stmt.executeQuery(query);

            //Creamos un HashMap para guardar los datos Pet_id - Pet_Name - Client_Name - Client_Id
            HashMap<Integer, String[]> data = new HashMap<Integer, String[]>();

            //Lleno el hashmap con un while
            while (rs.next()) {
                int idPets = rs.getInt("Pet_Id");
                int idClients = rs.getInt("Client_Id");
                String nombrePet = rs.getString("Pet_Name");
                String nombreCliente = rs.getString("Client_Name");
                String[] values = {nombrePet, String.valueOf(idPets),nombreCliente, String.valueOf(idClients)};
                data.put(idPets, values);
            }

            //Lo añado al comboBox
            for (Integer key : data.keySet()) {
                jComboBox.getItems().add(data.get(key)[0]);
            }

            // Agregamos un listener al ComboBox para mostrar los valores seleccionados en los TextFields
            jComboBox.setOnAction(new EventHandler<ActionEvent>() {

                public void handle(ActionEvent event) {
                    int id = jComboBox.getSelectionModel().getSelectedIndex()+ 1;
                    IdPet.setText(data.get(id)[1]);
                    nameClient.setText(data.get(id)[2]);
                    idclient.setText(data.get(id)[3]);
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //AÑADIR
    public boolean addService() throws SQLException {
        boolean flag = false;
        try (Connection connection = DriverManager.getConnection(fx.getUrl(), fx.getUser(), fx.getPassword());
             PreparedStatement pps = connection.prepareStatement("INSERT INTO Servicios (Cita_FechaHora, Cita_Motivo, Pet_Id, Client_Id) VALUES (?,?,?,?);",Statement.RETURN_GENERATED_KEYS)) {
            pps.setString(1, this.fecha);
            pps.setString(2, this.citaMotivo);
            pps.setInt(3, this.pet_Id);
            pps.setInt(4, this.Client_Id);
            int rowsAffected = pps.executeUpdate();
            if (rowsAffected > 0) {
                flag = true;
                try (ResultSet generatedKeys = pps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        this.Cita_Id = generatedKeys.getInt(1);
                    }
                    else {
                        throw new SQLException("No se pudo obtener el id de la cita creada");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flag;

    }

    //ELIMINAR

    public boolean deleteService(int id) throws SQLException{
        boolean flag = false;

        try(Connection connection = DriverManager.getConnection(fx.getUrl(), fx.getUser(), fx.getPassword());
            PreparedStatement pps = connection.prepareStatement("delete from Servicios where Cita_Id = ?")) {

            pps.setInt(1,id);
            pps.executeUpdate();
            flag = true;
        }

        return flag;
    }

    //ACTUALIZAR
    public boolean actualizaService(String cita, String fech, int id) throws SQLException {
        boolean flag = false;

        try (Connection connection = DriverManager.getConnection(fx.getUrl(), fx.getUser(), fx.getPassword());
             PreparedStatement pps = connection.prepareStatement("update Servicios set Cita_Motivo = ?, Cita_FechaHora = ? where Cita_Id = ?")) {
            pps.setString(1, cita);
            pps.setString(2, fech);
            pps.setInt(3, id);
            pps.executeUpdate();
            flag = true;
        }

        return flag;
    }

    //LEER DATOS
    public void actualizarTabla(javafx.scene.control.TableView tabla) {

        tabla.getColumns().clear();

        // Crear las columnas que quieres mostrar
        TableColumn<servicios, Integer> idColumn = new TableColumn<>("Cita ID");
        TableColumn<servicios, String> fechaColumn = new TableColumn<>("Fecha de la cita");
        TableColumn<servicios, String> motivoColumn = new TableColumn<>("Atención Requerida");
        TableColumn<servicios, String> mascotaColumn = new TableColumn<>("Mascota");
        TableColumn<servicios, String> clienteColumn = new TableColumn<>("Cliente");

        // Asignar las propiedades de los objetos de la clase "servicios" a las columnas correspondientes
        idColumn.setCellValueFactory(new PropertyValueFactory<>("Cita_Id"));
        fechaColumn.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        motivoColumn.setCellValueFactory(new PropertyValueFactory<>("citaMotivo"));
        mascotaColumn.setCellValueFactory(new PropertyValueFactory<>("Pet_Name"));
        clienteColumn.setCellValueFactory(new PropertyValueFactory<>("Client_Name"));

        // Crear una lista observable para los datos de la tabla
        ObservableList<servicios> listaServicios = FXCollections.observableArrayList();

        //Realizar la consulta y agregar los datos a la lista observable
        try (Connection connection = DriverManager.getConnection(fx.getUrl(), fx.getUser(), fx.getPassword());
             Statement stmt = connection.createStatement()) {
            String query = "SELECT s.Cita_Id, s.Cita_FechaHora, s.Cita_Motivo, p.Pet_Name, c.Client_Name " +
                    "FROM Servicios s " +
                    "JOIN Pets p ON s.Pet_Id = p.Pet_Id " +
                    "JOIN Clients c ON p.Client_Id = c.Client_Id;";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                servicios serv = new servicios(rs.getString("Cita_FechaHora"), rs.getString("Cita_Motivo"),
                        rs.getString("Pet_Name"), rs.getString("Client_Name"));
                serv.setCita_Id(rs.getInt("Cita_Id"));
                listaServicios.add(serv);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Asignar los datos a la tabla
        tabla.setItems(listaServicios);
        tabla.getColumns().addAll(idColumn, fechaColumn, motivoColumn, mascotaColumn, clienteColumn);
    }
    //LEER DATOS POR CONSULTA
    public void ActualizarTablaPorConsulta(javafx.scene.control.TableView tabla, String consulta){
        tabla.getColumns().clear();

        // Crear las columnas que quieres mostrar
        TableColumn<servicios, Integer> idColumn = new TableColumn<>("Cita ID");
        TableColumn<servicios, String> fechaColumn = new TableColumn<>("Fecha de la cita");
        TableColumn<servicios, String> motivoColumn = new TableColumn<>("Atención Requerida");
        TableColumn<servicios, String> mascotaColumn = new TableColumn<>("Mascota");
        TableColumn<servicios, String> clienteColumn = new TableColumn<>("Cliente");

        // Asignar las propiedades de los objetos de la clase "servicios" a las columnas correspondientes
        idColumn.setCellValueFactory(new PropertyValueFactory<>("Cita_Id"));
        fechaColumn.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        motivoColumn.setCellValueFactory(new PropertyValueFactory<>("citaMotivo"));
        mascotaColumn.setCellValueFactory(new PropertyValueFactory<>("Pet_Name"));
        clienteColumn.setCellValueFactory(new PropertyValueFactory<>("Client_Name"));

        // Crear una lista observable para los datos de la tabla
        ObservableList<servicios> listaServicios = FXCollections.observableArrayList();
//SELECT s.Cita_Id, s.Cita_FechaHora, s.Cita_Motivo, p.Pet_Name, c.Client_Name FROM servicios s JOIN pets p ON s.Pet_Id = p.Pet_Id  JOIN clients c ON p.Client_Id = c.Client_Id where Pet_Name = 'Fido' or Client_Name = 'Fido' or Cita_Motivo = 'Fido';
        //Realizar la consulta y agregar los datos a la lista observable
        try (Connection connection = DriverManager.getConnection(fx.getUrl(), fx.getUser(), fx.getPassword());
             PreparedStatement pps = connection.prepareStatement("SELECT s.Cita_Id, s.Cita_FechaHora, s.Cita_Motivo, p.Pet_Name, c.Client_Name FROM Servicios s JOIN Pets p ON s.Pet_Id = p.Pet_Id  JOIN Clients c ON p.Client_Id = c.Client_Id where Pet_Name = ? or Client_Name = ? or Cita_Motivo like ?;")) {
           pps.setString(1,consulta);
           pps.setString(2,consulta);
           pps.setString(3,"%" +consulta+"%");
            ResultSet rs = pps.executeQuery();
            while (rs.next()) {
                servicios serv = new servicios(rs.getString("Cita_FechaHora"), rs.getString("Cita_Motivo"),
                        rs.getString("Pet_Name"), rs.getString("Client_Name"));
                serv.setCita_Id(rs.getInt("Cita_Id"));
                listaServicios.add(serv);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Asignar los datos a la tabla
        tabla.setItems(listaServicios);
        tabla.getColumns().addAll(idColumn, fechaColumn, motivoColumn, mascotaColumn, clienteColumn);
    }


    public int comprobarFilas(String fech){
        int count = 0;
        try (Connection connection = DriverManager.getConnection(fx.getUrl(), fx.getUser(), fx.getPassword());
             PreparedStatement pps = connection.prepareStatement("select COUNT(Cita_FechaHora) from Servicios where Cita_FechaHora = ?")) {
            pps.setString(1,fech);
            ResultSet rs = pps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

}
