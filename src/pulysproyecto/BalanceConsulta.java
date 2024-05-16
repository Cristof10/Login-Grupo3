package pulysproyecto;

public class BalanceConsulta {
    String fechasbalanc,motivo= "Consulta de ingresos y egresos";
    Float montoingre;

    public BalanceConsulta(String fechasbalanc, String motivo, Float montoingre) {
        this.fechasbalanc = fechasbalanc;
        this.motivo = motivo;
        this.montoingre = montoingre;


    }

    public String getFechasbalanc() {
        return fechasbalanc;
    }

    public void setFechasbalanc(String fechasbalanc) {
        this.fechasbalanc = fechasbalanc;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Float getMontoingre() {
        return montoingre;
    }

    public void setMontoingre(Float montoingre) {
        this.montoingre = montoingre;
    }
}
