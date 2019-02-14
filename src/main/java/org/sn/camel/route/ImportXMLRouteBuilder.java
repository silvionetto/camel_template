package org.sn.camel.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.Main;

public class ImportXMLRouteBuilder extends RouteBuilder {

    public static void main(String[] args) throws Exception {
        Main.main(args);
    }

    @Override
    public void configure() throws Exception {
        // 1 - trigger the process
        from("timer:timer.dev?period=5000&repeatCount=1").autoStartup("true")
                .to("direct:retrieve.files").end();

        // 2.1 - retrieve files from web
        from("direct:retrieve.files")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/xml"))
                .setHeader(
                        Exchange.HTTP_URI,
                        simple("http://www.fpml.org/spec/fpml-5-3-6-rec-1/html/transparency/xml/products/rpt_ex50-gas-swap-prices-first-day.xml"))
                .to("http4://retrieve.files?throwExceptionOnFailure=true")
                .to("file:data/received?fileName=Trade${date:now:yyyyMMddHHmm}.xml")
                .end();

    }
}
