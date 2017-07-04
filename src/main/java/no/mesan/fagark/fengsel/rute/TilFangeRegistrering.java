package no.mesan.fagark.fengsel.rute;

import no.mesan.fagark.fengsel.Fange;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

public class TilFangeRegistrering extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        rest("/fengsel").post("/fangereg").type(Fange.class).route().routeId("fengsel/fangereg")
                .process(new ExchangeInOutMapper()).marshal().json(JsonLibrary.Jackson)
                .to("restlet:http://localhost:49000/fanger?restletMethod=post");

    }
}
