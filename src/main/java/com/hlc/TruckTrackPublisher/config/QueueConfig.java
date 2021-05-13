package com.hlc.TruckTrackPublisher.config;

import ionic.Msmq.MessageQueueException;
import ionic.Msmq.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class QueueConfig {
   private Queue queue= null;

    @Autowired
    private Environment environment;

    @Bean
    public Queue openQueue(){
        try {
            if (queue!=null) {
                queue.close();
                queue= null;
            }
            //nName of Queue to open
            String qname= environment.getProperty("truck.queue", "test");
            //Name of Queue Server (ENTER for local)
            String hostname= environment.getProperty("truck.queue", "");
            String fullname= getQueueFullName(hostname,qname);
            System.out.println("open (" + fullname + ")");
            queue= new Queue(fullname);
            //queue= new Queue(fullname, 0x02); // 0x02 == SEND only
            System.out.println("open: OK.");
        }
        catch (MessageQueueException ex1) {
            System.out.println("Queue open failure: " + ex1);
        }
        return queue;
    }

    private String getQueueFullName( String hostname, String queueShortName ) {
        String h1= hostname;
        String a1= "OS";
        if ((h1==null) || h1.equals("")) h1=".";
        char[] c= h1.toCharArray();
        if ((c[0]>='1')
                && (c[0]<='9')) a1= "TCP";

        return "DIRECT=" + a1 + ":" + h1 + "\\private$\\" + queueShortName;
    }
}
