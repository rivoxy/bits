package com.bits.kata;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Rivo
 */
@Configuration
@ApplicationPath("/kataservices")
public class KataTennisApplicationResourceConfig extends ResourceConfig {

    public KataTennisApplicationResourceConfig() {
        packages(KataTennisApplicationResourceConfig.class.getPackage().getName());
    }

}
