package br.com.iagocolodetti.heroi.modelo.dao;

import br.com.iagocolodetti.heroi.modelo.Universo;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author iagocolodetti
 */
public interface UniversoDAO {
    
    public Universo getUniverso(int id) throws SQLException;
    
    public List<Universo> getUniversos() throws SQLException;
}
