package org.sn.camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.Main;

public class MainRouteBuilder extends RouteBuilder {

    public static void main(String[] args) throws Exception {
	Main.main(args);
    }

    @Override
    public void configure() throws Exception {
	from("file:data/inbox?charset=utf-8").to("file:data/outbox");
    }
}
