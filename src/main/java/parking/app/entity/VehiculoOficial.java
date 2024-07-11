package parking.app.entity;

public class VehiculoOficial extends Vehiculo {

    public VehiculoOficial(String matricula) {
        super(matricula);
    }

    @Override
    public double calcularPago(long minutos) {
        return 0.0;
    }
}
