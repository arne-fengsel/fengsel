package no.mesan.fagark.fengsel.rute;

import java.util.Random;

import no.mesan.fagark.fengsel.Fange;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

public class TilIsolat extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        rest("/fengsel").post("/isolat").type(Fange.class).route().routeId("fengsel/isolat").process(new FangeTilIsolatMapper())
                .marshal().json(JsonLibrary.Jackson).to("restlet:http://localhost:9998/isolat?restletMethod=post");
    }

    static class FangeTilIsolatMapper implements Processor {
        public void process(final Exchange exchange) throws Exception {
            exchange.getOut().setHeader(Exchange.CONTENT_TYPE, "application/json");
            final Fange in = exchange.getIn().getBody(Fange.class);
            final IsolatFange fangeTilIsolat = new IsolatFange();
            fangeTilIsolat.fangeTilIsolat = in;
            fangeTilIsolat.callbackUrl = "http://localhost:9997/fengsel/isolat/ferdig";

            final Random random = new Random(10);

            fangeTilIsolat.isoleringsTid = random.nextInt();
            fangeTilIsolat.method = "GET";
            exchange.getOut().setBody(fangeTilIsolat);

        }

    }

    static class IsolatFange {
        public Fange fangeTilIsolat;
        public int isoleringsTid;
        public String callbackUrl;
        public String method;
    }

}
