package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.LinkedList;
//import java.util.List;

import model.entity.Produto;

public class ProdutoDAO {
    public void inserir(Produto veiculo) {
        ConectBD conexao = new ConectBD();
        String sql = "INSERT INTO veiculos (numero_chassi, placa, modelo, nome, valor) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conexao.getConnection().prepareStatement(sql)) {
            statement.setString(1, veiculo.getNumeroChassi());
            statement.setString(2, veiculo.getPlaca());
            statement.setString(3, veiculo.getModelo());
            statement.setString(4, veiculo.getNome());
            statement.setDouble(5, veiculo.getValor());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Produto consultarId(String numeroChassi) {
        ConectBD conexao = new ConectBD();
        String sql = "SELECT * FROM veiculos WHERE numero_chassi = ?";

        try (PreparedStatement statement = conexao.getConnection().prepareStatement(sql)) {
            statement.setString(1, numeroChassi);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String placa = resultSet.getString("placa");
                String modelo = resultSet.getString("modelo");
                String nome = resultSet.getString("nome");
                double valor = resultSet.getDouble("valor");

                return new Produto(numeroChassi, placa, modelo, nome, valor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Produto consultarPlaca(String placa) {
        ConectBD conexao = new ConectBD();
        String sql = "SELECT * FROM veiculos WHERE placa = ?";

        try (PreparedStatement statement = conexao.getConnection().prepareStatement(sql)) {
            statement.setString(1, placa);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String numeroChassi = resultSet.getString("numero_chassi");
                String modelo = resultSet.getString("modelo");
                String nome = resultSet.getString("nome");
                double valor = resultSet.getDouble("valor");

                return new Produto(numeroChassi, placa, modelo, nome, valor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean excluir(String numeroChassi) {
        ConectBD conexao = new ConectBD();
        String sql = "DELETE FROM veiculos WHERE numero_chassi = ?";

        try (PreparedStatement statement = conexao.getConnection().prepareStatement(sql)) {
            statement.setString(1, numeroChassi);

            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void atualizar(Produto veiculo) {
        ConectBD conexao = new ConectBD();
        String sql = "UPDATE veiculos SET placa = ?, modelo = ?, nome = ?, valor = ? WHERE numero_chassi = ?";

        try (PreparedStatement statement = conexao.getConnection().prepareStatement(sql)) {
            statement.setString(1, veiculo.getPlaca());
            statement.setString(2, veiculo.getModelo());
            statement.setString(3, veiculo.getNome());
            statement.setDouble(4, veiculo.getValor());
            statement.setString(5, veiculo.getNumeroChassi());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}