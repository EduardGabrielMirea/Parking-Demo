package parking.app.controller;

import parking.app.connect.DatabaseConnection;
import parking.app.entity.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Controlador {
    private Map<String, Vehiculo> vehiculos;
    private Map<String, Estancia> estanciasActivas;

    public Controlador() {
        vehiculos = new HashMap<>();
        estanciasActivas = new HashMap<>();
    }

    public void registrarEntrada(String matricula) {
        //estanciasActivas.put(matricula, new Estancia(LocalDateTime.now()));

        try (Connection conn = DatabaseConnection.getConnection()) {
            // Primero, insertar el vehículo en la tabla "vehiculos" si no existe
            String sqlVehiculo = "INSERT INTO vehiculos (matricula) VALUES (?) ON CONFLICT DO NOTHING";
            try (PreparedStatement stmtVehiculo = conn.prepareStatement(sqlVehiculo)) {
                stmtVehiculo.setString(1, matricula);
                stmtVehiculo.executeUpdate();
            }

            // Luego, insertar la estancia en la tabla "estancias"
            String sqlEstancia = "INSERT INTO estancias (matricula, hora_entrada) VALUES (?, ?)";
            try (PreparedStatement stmtEstancia = conn.prepareStatement(sqlEstancia)) {
                stmtEstancia.setString(1, matricula);
                stmtEstancia.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
                stmtEstancia.executeUpdate();
            }
            estanciasActivas.put(matricula, new Estancia(LocalDateTime.now()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        public void registrarSalida(String matricula) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sqlSelect = "SELECT hora_entrada FROM estancias WHERE matricula = ? AND hora_salida IS NULL";
            LocalDateTime horaEntrada = null;
            try (PreparedStatement stmt = conn.prepareStatement(sqlSelect)) {
                stmt.setString(1, matricula);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        horaEntrada = rs.getTimestamp("hora_entrada").toLocalDateTime();
                    }
                }
            }

            if (horaEntrada != null) {
                String sqlUpdate = "UPDATE estancias SET hora_salida = ? WHERE matricula = ? AND hora_salida IS NULL";
                try (PreparedStatement stmt = conn.prepareStatement(sqlUpdate)) {
                    stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
                    stmt.setString(2, matricula);
                    stmt.executeUpdate();
                }

                Estancia estancia = estanciasActivas.remove(matricula);
                if (estancia != null) {
                    estancia.registrarSalida(LocalDateTime.now());
                    long minutos = estancia.calcularMinutos();
                    Vehiculo vehiculo = obtenerVehiculo(conn, matricula);
                    if (vehiculo != null) {
                        double pago = vehiculo.calcularPago(minutos);
                        if (vehiculo instanceof VehiculoResidente) {
                            ((VehiculoResidente) vehiculo).agregarTiempoEstacionado(minutos);
                            actualizarTiempoResidente(conn, matricula, minutos);
                        } else if (vehiculo instanceof VehiculoNoResidente) {
                            // Mostrar pago
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*
        Estancia estancia = estanciasActivas.remove(matricula);
        if (estancia != null) {
            estancia.registrarSalida(LocalDateTime.now());
            Vehiculo vehiculo = vehiculos.get(matricula);
            if (vehiculo != null) {
                long minutos = estancia.calcularMinutos();
                double pago = vehiculo.calcularPago(minutos);
                // Procesar el pago si es necesario
                if (vehiculo instanceof VehiculoResidente) {
                    ((VehiculoResidente) vehiculo).agregarTiempoEstacionado(minutos);
                }
                // Mostrar el pago si es un VehiculoNoResidente
            }
        }
         */
    }

    public void darDeAltaVehiculoOficial(String matricula) {
        //vehiculos.put(matricula, new VehiculoOficial(matricula));
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO vehiculos (matricula, tipo) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, matricula);
                stmt.setString(2, "oficial");
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void darDeAltaVehiculoResidente(String matricula) {
        //vehiculos.put(matricula, new VehiculoResidente(matricula));
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO vehiculos (matricula, tipo) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, matricula);
                stmt.setString(2, "residente");
                stmt.executeUpdate();
            }

            String sqlResidente = "INSERT INTO tiempo_residentes (matricula) VALUES (?)";
            try (PreparedStatement stmt = conn.prepareStatement(sqlResidente)) {
                stmt.setString(1, matricula);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void comenzarMes() {
      /*  for (Vehiculo vehiculo : vehiculos.values()) {
            if (vehiculo instanceof VehiculoResidente) {
                ((VehiculoResidente) vehiculo).resetTiempoEstacionado();
            }
        }
        // Limpiar estancias de vehículos oficiales si es necesario
    */
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sqlDelete = "DELETE FROM estancias WHERE matricula IN (SELECT matricula FROM vehiculos WHERE tipo = 'oficial')";
            try (PreparedStatement stmt = conn.prepareStatement(sqlDelete)) {
                stmt.executeUpdate();
            }

            String sqlUpdate = "UPDATE tiempo_residentes SET tiempo_acumulado = 0";
            try (PreparedStatement stmt = conn.prepareStatement(sqlUpdate)) {
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void generarInformePagosResidentes(String nombreArchivo) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT v.matricula, tr.tiempo_acumulado FROM vehiculos v JOIN tiempo_residentes tr ON v.matricula = tr.matricula WHERE v.tipo = 'residente'";
            try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
                StringBuilder informe = new StringBuilder();
                while (rs.next()) {
                    String matricula = rs.getString("matricula");
                    int tiempoAcumulado = rs.getInt("tiempo_acumulado");
                    double pago = tiempoAcumulado * 0.002;
                    informe.append("Matrícula: ").append(matricula)
                            .append(", Tiempo Acumulado: ").append(tiempoAcumulado)
                            .append(", Pago: ").append(pago).append("\n");
                }

                // Guardar el informe en un archivo
                java.nio.file.Files.write(java.nio.file.Paths.get(nombreArchivo), informe.toString().getBytes());
            }
        } catch (SQLException | java.io.IOException e) {
            e.printStackTrace();
        }
    }

    private Vehiculo obtenerVehiculo(Connection conn, String matricula) throws SQLException {
        String sql = "SELECT tipo FROM vehiculos WHERE matricula = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, matricula);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String tipo = rs.getString("tipo");
                    if ("oficial".equals(tipo)) {
                        return new VehiculoOficial(matricula);
                    } else if ("residente".equals(tipo)) {
                        return new VehiculoResidente(matricula);
                    } else if ("no_residente".equals(tipo)) {
                        return new VehiculoNoResidente(matricula);
                    }
                }
            }
        }
        return null;
    }

    private void actualizarTiempoResidente(Connection conn, String matricula, long minutos) throws SQLException {
        String sql = "UPDATE tiempo_residentes SET tiempo_acumulado = tiempo_acumulado + ? WHERE matricula = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, (int) minutos);
            stmt.setString(2, matricula);
            stmt.executeUpdate();
        }
    }

}
