package pulysproyecto;

public class clienteMascota
{

    private String date, nombreCliente, phone, nombreMascota, sexo, raza , observaciones;
    private float peso;
    private int idCliente,idMascota;

    public clienteMascota(String date, int idCliente,String nombreCliente, String phone,int idMascota, String nombreMascota, String sexo, String raza,float peso, String observaciones ) {
        this.date = date;
        this.nombreCliente = nombreCliente;
        this.phone = phone;
        this.nombreMascota = nombreMascota;
        this.sexo = sexo;
        this.raza = raza;
        this.observaciones = observaciones;
        this.peso = peso;
        this.idCliente=idCliente;
        this.idMascota=idMascota;

    }

    public String getDate() {
        return date;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getPhone() {
        return phone;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public String getSexo() {
        return sexo;
    }

    public String getRaza() {
        return raza;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public float getPeso() {
        return peso;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public int getIdMascota() {
        return idMascota;
    }
}
