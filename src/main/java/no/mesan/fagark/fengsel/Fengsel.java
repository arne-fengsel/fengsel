package no.mesan.fagark.fengsel;

import no.mesan.fagark.fengsel.rute.FraIsolat;
import no.mesan.fagark.fengsel.rute.TilFangeRegistrering;
import no.mesan.fagark.fengsel.rute.TilIsolat;
import no.mesan.fagark.fengsel.rute.TilLogistikk;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestConfigurationDefinition;
import org.apache.camel.spi.RestConfiguration;

public class Fengsel {

    public static void main(final String[] args) {
        try {

            final CamelContext camelContext = new DefaultCamelContext();
            final RestConfiguration restConfiguration = new RestConfigurationDefinition().component("jetty").host("localhost")
                    .bindingMode(RestBindingMode.json).port(9997).asRestConfiguration(camelContext);

            camelContext.setRestConfiguration(restConfiguration);
            camelContext.addRoutes(new FraIsolat());
            camelContext.addRoutes(new TilIsolat());
            camelContext.addRoutes(new TilFangeRegistrering());
            camelContext.addRoutes(new TilLogistikk());
            camelContext.start();

        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
