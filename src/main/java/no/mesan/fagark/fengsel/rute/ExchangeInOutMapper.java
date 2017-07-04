package no.mesan.fagark.fengsel.rute;

import no.mesan.fagark.fengsel.Fange;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ExchangeInOutMapper implements Processor {

        public void process(final Exchange exchange) throws Exception {
            exchange.getOut().setHeader(Exchange.CONTENT_TYPE, "application/json");
            final Fange in = exchange.getIn().getBody(Fange.class);
            exchange.getOut().setBody(in);
        }
}
