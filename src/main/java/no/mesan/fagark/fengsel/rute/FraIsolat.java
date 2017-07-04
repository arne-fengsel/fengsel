package no.mesan.fagark.fengsel.rute;

import no.mesan.fagark.fengsel.Fange;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class FraIsolat extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        rest("/fengsel").get("/isolat/ferdig").type(Fange.class).route().routeId("fengsel/isolat/ferdig")
                .process(new Processor() {

                    public void process(final Exchange exchange) throws Exception {
                        System.out.println(exchange.getIn().getBody());
                    }
                }).end();
    }
}
