package parking.app.entity;

import java.time.LocalDateTime;

public class Estancia {
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSalida;

    public Estancia(LocalDateTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public void registrarSalida(LocalDateTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public long calcularMinutos() {
        if (horaSalida != null) {
            return java.time.Duration.between(horaEntrada, horaSalida).toMinutes();
        }
        return 0;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public LocalDateTime getHoraSalida() {
        return horaSalida;
    }
}
