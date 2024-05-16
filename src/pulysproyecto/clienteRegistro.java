package pulysproyecto;

public class clienteRegistro {

    public String nombre_cliente, numero_telefono;
    public int id;

    public clienteRegistro(int id,String nombre_cliente, String numero_telefono) {
        this.id=id;
        this.nombre_cliente = nombre_cliente;
        this.numero_telefono=numero_telefono;

    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public String getNumero_telefono() {
        return numero_telefono;
    }

    public int getId() {
        return id;
    }
}
