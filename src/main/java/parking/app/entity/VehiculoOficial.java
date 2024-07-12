package parking.app.entity;

public class VehiculoOficial extends Vehiculo {

    public VehiculoOficial(String matricula) {
        super(matricula);
    }

    @Override
    public double calcularPago(long minutos) {
        return 0.0;
    }

    @Override
    public void mostrarPago(Estancia estancia, Vehiculo vehiculo) {
        if (vehiculo != null && vehiculo instanceof VehiculoOficial) {
            double pago = vehiculo.calcularPago(estancia.calcularMinutos());
            System.out.printf("El pago para la matrícula %s es: %.2f euros.%n", vehiculo.getMatricula(), pago);
        }else {
            System.out.println("No se encontró ningún vehículo o el vehículo no es un VehiculoNoResidente.");
        }
    }
}
