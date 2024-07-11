package parking.app.entity;

public abstract class Vehiculo {
    protected String matricula;

    public Vehiculo(String matricula) {
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public abstract double calcularPago(long minutos);
}
