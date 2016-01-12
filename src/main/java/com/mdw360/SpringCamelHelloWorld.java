package com.mdw360;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Created by awentzell on 2016-01-12.
 */
public class SpringCamelHelloWorld {
    public static void main (String[] args) throws Exception {
//        ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//        CamelContext camelContext = SpringCamelContext.springCamelContext(appContext, false);
//        try {
//            ProducerTemplate template = camelContext.createProducerTemplate();
//            camelContext.start();
//            template.sendBody("activemq:test.queue", "Hello World");
//        } finally {
//            camelContext.stop();
//        }

        CamelContext context = new DefaultCamelContext();
        try {
            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from("timer://myTimer?period=2000")
                            .setBody()
                            .simple("Hello World Camel fired at ${header.firedTime}")
                            .to("stream:out");
                }
            });
            context.start();
            Thread.sleep(10000);
        } finally {
            context.stop();
        }


    }
}
