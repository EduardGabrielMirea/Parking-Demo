package parking.app.entity;

public class VehiculoNoResidente extends Vehiculo {
    private long tiempoEstacionado; // en minutos

    public VehiculoNoResidente(String matricula) {
        super(matricula);
        this.tiempoEstacionado = 0;
    }
    public void agregarTiempoEstacionado(long minutos) {
        this.tiempoEstacionado += minutos;
    }
    @Override
    public double calcularPago(long minutos) {
        return minutos * 0.02;
    }

    @Override
    public void mostrarPago(Estancia estancia, Vehiculo vehiculo) {
        if (vehiculo != null && vehiculo instanceof VehiculoNoResidente) {
            double pago = vehiculo.calcularPago(estancia.calcularMinutos());
            System.out.printf("El pago para la matrícula %s es: %.2f euros.%n", vehiculo.getMatricula(), pago);
        }else {
            System.out.println("No se encontró ningún vehículo o el vehículo no es un VehiculoNoResidente.");
        }
    }
}
