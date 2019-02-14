package org.sn.camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.Main;

public class SecondRouteBuilder extends RouteBuilder {

    public static void main(String[] args) throws Exception {
        Main.main(args);
    }

    @Override
    public void configure() throws Exception {
        from("file:data/trade?charset=utf-8")
                .choice()
                    .when().xpath("/trade/product='FX'")
                        .to("file:data/FX")
                    .otherwise()
                .to("file:data/others");
    }
}
