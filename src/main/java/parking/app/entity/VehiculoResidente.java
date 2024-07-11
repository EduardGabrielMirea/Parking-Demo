package parking.app.entity;

public class VehiculoResidente extends Vehiculo {
    private long tiempoEstacionado; // en minutos

    public VehiculoResidente(String matricula) {
        super(matricula);
        this.tiempoEstacionado = 0;
    }

    public void agregarTiempoEstacionado(long minutos) {
        this.tiempoEstacionado += minutos;
    }

    @Override
    public double calcularPago(long minutos) {
        return minutos * 0.002;
    }

    public long getTiempoEstacionado() {
        return tiempoEstacionado;
    }

    public void resetTiempoEstacionado() {
        this.tiempoEstacionado = 0;
    }
}
