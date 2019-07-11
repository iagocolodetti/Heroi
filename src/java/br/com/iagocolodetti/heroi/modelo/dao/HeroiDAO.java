package br.com.iagocolodetti.heroi.modelo.dao;

import br.com.iagocolodetti.heroi.exception.HeroiNaoExisteException;
import br.com.iagocolodetti.heroi.modelo.Heroi;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author iagocolodetti
 */
public interface HeroiDAO {
    
    public Heroi add(Heroi Heroi) throws SQLException;

    public Heroi getHeroi(int id) throws HeroiNaoExisteException, SQLException;

    public List<Heroi> getHerois() throws HeroiNaoExisteException, SQLException;

    public void delete(int id) throws HeroiNaoExisteException, SQLException;
}
