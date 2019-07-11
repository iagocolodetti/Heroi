package br.com.iagocolodetti.heroi.modelo.dao;

import br.com.iagocolodetti.heroi.controle.ConnectionFactory;
import br.com.iagocolodetti.heroi.modelo.Universo;
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
public class UniversoDAOImpl implements UniversoDAO {
    
    @Override
    public Universo getUniverso(int id) throws SQLException {
        
        ConnectionFactory cf = new ConnectionFactory();
        Connection con = cf.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Universo universo = null;
        try {
            ps = con.prepareStatement("SELECT nome FROM universo WHERE id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                universo = new Universo(id, rs.getString("nome"));
            }
        } catch (SQLException e) {
            throw new SQLException("Não foi possível buscar o universo.");
        } finally {
            cf.closeConnection(con, ps, rs);
        }
        return universo;
    }
    
    @Override
    public List<Universo> getUniversos() throws SQLException {
        
        ConnectionFactory cf = new ConnectionFactory();
        Connection con = cf.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Universo> universos = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM universo");
            rs = ps.executeQuery();
            while (rs.next()) {
                Universo universo = new Universo(rs.getInt("id"), rs.getString("nome"));
                universos.add(universo);
            }
        } catch (SQLException e) {
            throw new SQLException("Não foi possível buscar os universos.");
        } finally {
            cf.closeConnection(con, ps, rs);
        }
        return universos;
    }
}
