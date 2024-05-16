package pulysproyecto;

public class ingresoegresoRegistro {
    public String fechaingegr, horaingregr, motivoingregr;

    public int idingegr;
    public float montoegr;
    public ingresoegresoRegistro(int idingegr, String fechaingegr, String horaingregr, float montoegr, String motivoingregr) {
        this.fechaingegr = fechaingegr;
        this.horaingregr = horaingregr;
        this.motivoingregr = motivoingregr;
        this.montoegr= montoegr;
        this.idingegr=idingegr;


    }

    public String getFechaingegr() {
        return fechaingegr;
    }

    public void setFechaingegr(String fechaingegr) {
        this.fechaingegr = fechaingegr;
    }

    public String getHoraingregr() {
        return horaingregr;
    }

    public void setHoraingregr(String horaingregr) {
        this.horaingregr = horaingregr;
    }

    public String getMotivoingregr() {
        return motivoingregr;
    }

    public void setMotivoingregr(String motivoingregr) {
        this.motivoingregr = motivoingregr;
    }

    public int getIdingegr() {
        return idingegr;
    }

    public void setIdingegr(int idingegr) {
        this.idingegr = idingegr;
    }

    public float getMontoegr() {
        return montoegr;
    }

    public void setMontoegr(float montoegr) {
        this.montoegr = montoegr;
    }
}
