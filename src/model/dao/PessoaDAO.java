package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.entity.Pessoa;

public class PessoaDAO {
    // CRUD
    // C- create | R- retrieve | U - update | D - delete
    public void inserir(Pessoa pessoa) {
        ConectBD conexao = new ConectBD();
        String sql = "INSERT INTO pessoa (nome,email) VALUES (?,?)";
        try {
            PreparedStatement pst = conexao.getConnection().prepareStatement(sql);
            pst.setString(1, pessoa.getNome());
            pst.setString(2, pessoa.getEmail());
            pst.execute();
            System.out.println(pessoa.getNome() + " inserido.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * metodo que consulta registro do tipo pessoa por id
     * 
     * @param id - refere-se a pessoa primaria
     * @return um ojeto do tipo Pessoa, caso nao encontre retorna null
     */
    public Pessoa consultarId(int id) {
        ConectBD conn = new ConectBD();
        String sql = "select * from pessoa where id = ?";
        Pessoa p = null;
        try {
            PreparedStatement pst = conn.getConnection().prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                p = new Pessoa(nome, email);
                p.setId(id);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return p;
    }

    public boolean deletar(int id) {
        ConectBD con = new ConectBD();
        String sql = "delete from pessoa where id = ?";
        try {
            PreparedStatement pst = con.getConnection().prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;

    }

    public boolean update(Pessoa pessoa) {
        try {
            String sql = "update pessoa set nome = ?, email = ? where id = ?";
            ConectBD con = new ConectBD();
            PreparedStatement pst = con.getConnection().prepareStatement(sql);
            pst.setString(1, pessoa.getNome());
            pst.setString(2, pessoa.getEmail());
            pst.setInt(3, pessoa.getId());
            int linhas = pst.executeUpdate();
            return linhas > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<Pessoa> consultaTodos() {
        ConectBD con = new ConectBD();
        String sql = "SELECT * FROM pessoa";
        List<Pessoa> lista = new LinkedList<Pessoa>();
        try {
            PreparedStatement pst = con.getConnection().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                pessoa.setId(id);
                pessoa.setNome(nome);
                pessoa.setEmail(email);
                lista.add(pessoa);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }
}