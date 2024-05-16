package pulysproyecto;

public class inventarioRegistro {
    public String fechavencinventario, nombre_producto, categoriaproducto, costo_venta;
    public int id;

    public inventarioRegistro(int idinventario, String fechavencinventario, String nombre_producto, String categoriaproducto, String costo_venta) {
        this.fechavencinventario = fechavencinventario;
        this.nombre_producto = nombre_producto;
        this.categoriaproducto = categoriaproducto;
        this.costo_venta = costo_venta;
        this.id=idinventario;
    }

    public String getFechavencinventario() {
        return fechavencinventario;
    }

    public void setFechavencinventario(String fechavencinventario) {
        this.fechavencinventario = fechavencinventario;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public String getCategoriaproducto() {
        return categoriaproducto;
    }

    public void setCategoriaproducto(String categoriaproducto) {
        this.categoriaproducto = categoriaproducto;
    }

    public String getCosto_venta() {
        return costo_venta;
    }

    public void setCosto_venta(String costo_venta) {
        this.costo_venta = costo_venta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
