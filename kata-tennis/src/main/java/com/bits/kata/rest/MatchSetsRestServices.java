package com.bits.kata.rest;

import com.bits.kata.model.MatchSets;
import com.bits.kata.repositories.MatchSetsRepository;
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
@Path("/rs/sets")
@Scope("prototype")
@Service
public class MatchSetsRestServices {

    @Autowired
    private MatchSetsRepository setsRepository;

    @GET
    @Path("/findbyid/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public MatchSets findById(@PathParam("id") Integer id) {
        final Optional<MatchSets> resOpt = setsRepository.findById(id);
        return resOpt.isPresent() ? resOpt.get() : null;
    }

    @GET
    @Path("/findall")
    @Produces({MediaType.APPLICATION_JSON})
    public List<MatchSets> findAll() {
        return setsRepository.findAll();
    }

    @GET
    @Path("/removeall")
    @Produces({MediaType.APPLICATION_JSON})
    public void removeAll() {
        setsRepository.deleteAll();
    }
}
