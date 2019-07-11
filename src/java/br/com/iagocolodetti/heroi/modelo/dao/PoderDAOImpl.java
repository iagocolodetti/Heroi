package br.com.iagocolodetti.heroi.modelo.dao;

import br.com.iagocolodetti.heroi.controle.ConnectionFactory;
import br.com.iagocolodetti.heroi.modelo.Poder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author iagocolodetti
 */
public class PoderDAOImpl implements PoderDAO {

    @Override
    public void add(Connection con, int idHeroi, List<Poder> poderes) throws SQLException {
        
        PreparedStatement ps = null;
        try {
            for (Poder poder : poderes) {
                ps = con.prepareStatement("INSERT INTO poder(descricao, idHeroi) VALUES(?, ?)");
                ps.setString(1, poder.getDescricao());
                ps.setInt(2, idHeroi);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new SQLException("Não foi possível adicionar os poderes.");
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    @Override
    public List<Poder> getPoderes(int idHeroi) throws SQLException {
        
        ConnectionFactory cf = new ConnectionFactory();
        Connection con = cf.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Poder> poderes = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT id, descricao FROM poder WHERE idHeroi = ?");
            ps.setInt(1, idHeroi);
            rs = ps.executeQuery();
            while (rs.next()) {
                Poder poder = new Poder(rs.getInt("id"), rs.getString("descricao"), idHeroi);
                poderes.add(poder);
            }
        } catch (SQLException e) {
            throw new SQLException("Não foi possível buscar os poderes.");
        } finally {
            cf.closeConnection(con, ps, rs);
        }
        return poderes;
    }
    
    @Override
    public void delete(Connection con, int idHeroi) throws SQLException {
        
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM poder WHERE idHeroi = ?");
            ps.setInt(1, idHeroi);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Não foi possível excluir/remover o(s) poder(es).");
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }
}
