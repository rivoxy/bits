package com.bits.kata.rest;

import com.bits.kata.GameInitializerService;
import com.bits.kata.YesNoEnum;
import com.bits.kata.model.RunMatch;
import com.bits.kata.repositories.RunMatchRepository;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rivo
 */
@Path("/rs/rmatch")
@Scope("prototype")
@Service
public class RunMatchRestServices {

    @Autowired
    private RunMatchRepository mRepository;

    @Autowired
    private GameInitializerService initGame;

    @GET
    @Path("/updaterules/{matchid}/{deuce}/{tiebreak}")
    @Produces({MediaType.APPLICATION_JSON})
    public RunMatch updateRules(@PathParam("matchid") Integer matchid, @PathParam("deuce") String deuce, @PathParam("tiebreak") String tiebreak) {
        RunMatch updatedGame = initGame.updateRules(matchid, YesNoEnum.valueOf(deuce), YesNoEnum.valueOf(tiebreak));
        return updatedGame;
    }

    @GET
    @Path("/creategame/{p1}/{p2}")
    @Produces({MediaType.APPLICATION_JSON})
    public RunMatch intiNewGame(@PathParam("p1") String p1, @PathParam("p2") String p2) {
        RunMatch createdGame = initGame.createNewGame(p1, p2, YesNoEnum.N, YesNoEnum.N);
        return createdGame;
    }

    @GET
    @Path("/findbyid/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public RunMatch findById(@PathParam("id") Integer id) {
        final Optional<RunMatch> resOpt = mRepository.findById(id);
        return resOpt.isPresent() ? resOpt.get() : null;
    }

    @GET
    @Path("/findall")
    @Produces({MediaType.APPLICATION_JSON})
    public List<RunMatch> findAll() {
        return mRepository.findAll();
    }

    @GET
    @Path("/removeall")
    @Produces({MediaType.APPLICATION_JSON})
    public void removeAll() {
        mRepository.deleteAll();
    }

}
