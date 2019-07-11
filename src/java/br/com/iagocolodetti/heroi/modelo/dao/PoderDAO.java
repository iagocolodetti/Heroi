package br.com.iagocolodetti.heroi.modelo.dao;

import br.com.iagocolodetti.heroi.modelo.Poder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author iagocolodetti
 */
public interface PoderDAO {
    
    public void add(Connection con, int idHeroi, List<Poder> poderes) throws SQLException;
    
    public List<Poder> getPoderes(int idHeroi) throws SQLException;
    
    public void delete(Connection con, int idHeroi) throws SQLException;
}
