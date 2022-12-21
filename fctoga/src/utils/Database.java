package utils;

import models.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private Connection connection;

    public Database() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            if (!this.createTables())
                throw new SQLException("Não foi possível criar as tabelas.");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("Conectado ao Banco de Dados.");
    }

    public boolean execute(String sql) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<String[]> query(String sql) {
        ArrayList<String[]> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String[] row = new String[resultSet.getMetaData().getColumnCount()];
                for (int i = 0; i < row.length; i++) {
                    row[i] = resultSet.getString(i + 1);
                }
                result.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    private boolean createTables() {
        return execute("""
                CREATE TABLE IF NOT EXISTS usuarios(
                    usuario_cpf CHAR(11) PRIMARY KEY,
                    usuario_nome VARCHAR(60) UNIQUE NOT NULL,
                    usuario_senha VARCHAR(30) NOT NULL,
                    usuario_tipo VARCHAR(20) NOT NULL,
                    usuario_comarca VARCHAR(60),
                    usuario_numero_oab CHAR(8),
                    usuario_estado_oab CHAR(2)
                )
                """
        );
    }

    public List<Usuario> getUsuarios() {
        List<String[]> usuarioQuery = query("SELECT * FROM usuarios");

        List<Usuario> usuarios = new ArrayList<>();
        for (String[] usuario : usuarioQuery) {
            // Usando padrão factory
            Usuario instanciaUsuario = FactoryUsuario.createUsuario(usuario[3], usuario[0], usuario[1], usuario[2], usuario[4], usuario[5], usuario[6]);
            usuarios.add(instanciaUsuario);
        }
        return usuarios;
    }

    public boolean addUsuario(String CPF, String senha, String nomeCompleto, String tipo, String numeroOAB, String estadoOAB, String comarca) {
        return execute("INSERT INTO usuarios VALUES (" +
                "'" + CPF + "'," +
                "'" + nomeCompleto + "'," +
                "'" + senha + "'," +
                "'" + tipo + "'," +
                "'" + comarca + "'," +
                "'" + numeroOAB + "'," +
                "'" + estadoOAB + "')");
    }

}
