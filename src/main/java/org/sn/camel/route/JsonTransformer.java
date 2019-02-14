package org.sn.camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.XmlJsonDataFormat;
import org.apache.camel.spring.Main;

public class JsonTransformer extends RouteBuilder {

    public static void main(String[] args) throws Exception {
        Main.main(args);
    }

    @Override
    public void configure() throws Exception {
        XmlJsonDataFormat xmlJsonFormat = new XmlJsonDataFormat();
        xmlJsonFormat.setEncoding("UTF-8");
        xmlJsonFormat.setForceTopLevelObject(true);
        xmlJsonFormat.setTrimSpaces(true);
        xmlJsonFormat.setRootName("newRoot");
        xmlJsonFormat.setSkipNamespaces(true);
        xmlJsonFormat.setRemoveNamespacePrefixes(true);

        from("file:data/received?noop=true")
                .marshal(xmlJsonFormat)
                .to("file:data/json/out?fileName=${file:onlyname.noext}.json").end();
    }
}
