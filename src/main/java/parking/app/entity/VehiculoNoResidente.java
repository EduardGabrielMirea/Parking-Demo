package parking.app.entity;

public class VehiculoNoResidente extends Vehiculo {

    public VehiculoNoResidente(String matricula) {
        super(matricula);
    }

    @Override
    public double calcularPago(long minutos) {
        return minutos * 0.02;
    }
}
