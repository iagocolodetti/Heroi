package br.com.iagocolodetti.heroi.modelo.dao;

import br.com.iagocolodetti.heroi.controle.ConnectionFactory;
import br.com.iagocolodetti.heroi.exception.HeroiNaoExisteException;
import br.com.iagocolodetti.heroi.modelo.Heroi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author iagocolodetti
 */
public class HeroiDAOImpl implements HeroiDAO {

    @Override
    public Heroi add(Heroi heroi) throws SQLException {
        
        ConnectionFactory cf = new ConnectionFactory();
        Connection con = cf.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        con.setAutoCommit(false);
        try {
            ps = con.prepareStatement("INSERT INTO heroi(nome, dataCadastro, idUniverso, ativo) VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, heroi.getNome());
            ps.setTimestamp(2, new Timestamp(heroi.getDataCadastro().getTime()));
            ps.setInt(3, heroi.getIdUniverso());
            ps.setInt(4, 1);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if(rs.next()) {
                new PoderDAOImpl().add(con, rs.getInt(1), heroi.getPoderes());
                heroi.setId(rs.getInt(1));
            }
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            throw new SQLException("Não foi possível adicionar o herói.");
        } finally {
            cf.closeConnection(con, ps, rs);
        }
        return heroi;
    }

    @Override
    public Heroi getHeroi(int id) throws HeroiNaoExisteException, SQLException {
        
        ConnectionFactory cf = new ConnectionFactory();
        Connection con = cf.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Heroi heroi = null;
        try {
            ps = con.prepareStatement("SELECT h.id, h.nome, h.dataCadastro, h.idUniverso, u.nome FROM heroi h JOIN universo u ON h.idUniverso = u.id WHERE h.id = ? AND  WHERE h.ativo = ?");
            ps.setInt(1, id);
            ps.setInt(2, 1);
            rs = ps.executeQuery();
            while (rs.next()) {
                heroi = new Heroi(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), new PoderDAOImpl().getPoderes(rs.getInt(1)));
            }
            if (heroi == null) {
                throw new HeroiNaoExisteException("Não existe herói com esse ID.");
            }
        } catch (SQLException e) {
            throw new SQLException("Não foi possível buscar o herói.");
        } finally {
            cf.closeConnection(con, ps, rs);
        }
        return heroi;
    }

    @Override
    public List<Heroi> getHerois() throws HeroiNaoExisteException, SQLException {
        
        ConnectionFactory cf = new ConnectionFactory();
        Connection con = cf.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Heroi> herois = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT h.id, h.nome, h.dataCadastro, h.idUniverso, u.nome FROM heroi h JOIN universo u ON h.idUniverso = u.id WHERE h.ativo = ?");
            ps.setInt(1, 1);
            rs = ps.executeQuery();
            while (rs.next()) {
                Heroi heroi = new Heroi(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), new PoderDAOImpl().getPoderes(rs.getInt(1)));
                herois.add(heroi);
            }
            if (herois.isEmpty()) {
                throw new HeroiNaoExisteException("Não existem heróis.");
            }
        } catch (SQLException e) {
            throw new SQLException("Não foi possível buscar os heróis.");
        } finally {
            cf.closeConnection(con, ps, rs);
        }
        return herois;
    }

    @Override
    public void delete(int id) throws HeroiNaoExisteException, SQLException {
        
        ConnectionFactory cf = new ConnectionFactory();
        Connection con = cf.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE heroi SET ativo = ? WHERE id = ?");
            ps.setInt(1, 0);
            ps.setInt(2, id);
            if (ps.executeUpdate() == 0) {
                throw new HeroiNaoExisteException();
            }
        } catch (SQLException e) {
            throw new SQLException("Não foi possível excluir/remover o herói.");
        } finally {
            cf.closeConnection(con, ps);
        }
    }

}
