package no.mesan.fagark.fengsel.rute;

import no.mesan.fagark.fengsel.FangeMedEiendeler;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

public class TilLogistikk extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        rest("/fengsel").post("/logistikk/{eierId}").type(FangeMedEiendeler.class).route().routeId("fengsel/logistikk")
                .process(new LogistikkMapper()).marshal().json(JsonLibrary.Jackson)
                .to("restlet:http://localhost:9999/logistikk/${header.eierId}?restletMethod=put");
    }

    static class LogistikkMapper implements Processor {

        public void process(final Exchange exchange) throws Exception {
            exchange.getOut().setHeader(Exchange.CONTENT_TYPE, "application/json");
            final FangeMedEiendeler in = exchange.getIn().getBody(FangeMedEiendeler.class);

            exchange.getOut().setBody(in);
        }
    }
}
