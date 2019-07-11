package br.com.iagocolodetti.heroi.servicos;

import com.google.gson.Gson;
import br.com.iagocolodetti.heroi.modelo.dao.UniversoDAOImpl;
import java.sql.SQLException;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author iagocolodetti
 */
@Path("universo")
public class UniversoResource {

    public UniversoResource() {
    }

    @Path("/get/{idUniverso}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getUniverso(@PathParam("idUniverso") int id) {
        String gson = "";
        try {
            gson = new Gson().toJson(new UniversoDAOImpl().getUniverso(id));
        } catch (SQLException e) {
            gson = e.getMessage();
        }
        return gson;
    }
    
    @Path("/get")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getUniversos() {
        String gson = "";
        try {
            gson = new Gson().toJson(new UniversoDAOImpl().getUniversos());
        } catch (SQLException e) {
            gson = e.getMessage();
        }
        return gson;
    }
}
