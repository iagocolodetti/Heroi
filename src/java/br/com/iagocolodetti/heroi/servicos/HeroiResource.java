package br.com.iagocolodetti.heroi.servicos;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import br.com.iagocolodetti.heroi.exception.HeroiNaoExisteException;
import br.com.iagocolodetti.heroi.modelo.Heroi;
import br.com.iagocolodetti.heroi.modelo.dao.HeroiDAOImpl;
import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author iagocolodetti
 */
@Path("heroi")
public class HeroiResource {

    public HeroiResource() {
    }

    @Path("/get/{idHeroi}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getHeroi(@PathParam("idHeroi") int id) {
        String gson = "";
        try {
            gson = new Gson().toJson(new HeroiDAOImpl().getHeroi(id));
        } catch (HeroiNaoExisteException | SQLException e) {
            gson = e.getMessage();
        }
        return gson;
    }
    
    @Path("/get")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getHerois() {
        String gson = "";
        try {
            gson = new Gson().toJson(new HeroiDAOImpl().getHerois());
        } catch (HeroiNaoExisteException | SQLException e) {
            gson = e.getMessage();
        }
        return gson;
    }
    
    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addHeroi(String jsonHeroi) {
        try {
            Gson gson = new Gson();
            Heroi heroi = gson.fromJson(jsonHeroi, new TypeToken<Heroi>(){}.getType());
            heroi = new HeroiDAOImpl().add(heroi);
            jsonHeroi = gson.toJson(heroi);
        } catch (SQLException e) {
            jsonHeroi = "";
        }
        return jsonHeroi;
    }
    
    @Path("/delete/{idHeroi}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteHeroi(@PathParam("idHeroi") int id) {
        String resultado = "";
        try {
            new HeroiDAOImpl().delete(id);
            resultado = "Herói deletado com sucesso";
        } catch (HeroiNaoExisteException | SQLException e) {
            resultado = e.getMessage();
        }
        return resultado;
    }
    
}
