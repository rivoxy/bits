package com.bits.kata.rest;

import com.bits.kata.GamePointRegistrationService;
import com.bits.kata.model.GameScore;
import com.bits.kata.repositories.GameScoreRepository;
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
@Path("/rs/gs")
@Scope("prototype")
@Service
public class GameScoreRestServices {

    @Autowired
    private GameScoreRepository gScoreRepository;

    @Autowired
    private GamePointRegistrationService pointRegistrationService;

    @GET
    @Path("/addplayerpoint/{matchid}/{playername}")
    @Produces({MediaType.APPLICATION_JSON})
    public void registerPlayerPoint(@PathParam("matchid") Integer matchid, @PathParam("playername") String playername) {
        pointRegistrationService.registerPlayerPoint(matchid, playername);
    }

    @GET
    @Path("/findbyid/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public GameScore findById(@PathParam("id") Integer id) {
        final Optional<GameScore> resOpt = gScoreRepository.findById(id);
        return resOpt.isPresent() ? resOpt.get() : null;
    }

    @GET
    @Path("/readplayerscore/{matchid}/{playername}")
    @Produces({MediaType.APPLICATION_JSON})
    public GameScore findByPlayerNameAndMatchId(@PathParam("matchid") Integer matchid, @PathParam("playername") String playername) {
        return gScoreRepository.findByPlayerNameAndMatchId(playername, matchid);
    }

    @GET
    @Path("/findall")
    @Produces({MediaType.APPLICATION_JSON})
    public List<GameScore> findAll() {
        return gScoreRepository.findAll();
    }

    @GET
    @Path("/removeall")
    @Produces({MediaType.APPLICATION_JSON})
    public void removeAll() {
        gScoreRepository.deleteAll();
    }
}
