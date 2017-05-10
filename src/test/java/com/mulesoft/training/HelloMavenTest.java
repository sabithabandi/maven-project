package com.mulesoft.training;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.mule.api.MuleEvent;
import org.mule.tck.junit4.FunctionalTestCase;
import org.mule.tck.junit4.rule.DynamicPort;

public class HelloMavenTest extends FunctionalTestCase {

	
	@Rule
	public DynamicPort dynamicPort = new DynamicPort("http.port");
    // mavenFlow returns Hello Maven
    @Test
    public void mavenFlowReturnsHelloMaven() throws Exception {
    	System.out.println (" Dyn Port is"+dynamicPort.getNumber());
        runFlowAndExpect("mavenFlow", "Hello Maven");
    }
       
    // run maven-config.xml when testing
    @Override
    protected String getConfigFile() {
        return "maven-project.xml";
    }
    
    // retrieveFlights response contains content-type header
    // content-type headers has a value of application/json
   @Test
    public void retrieveFlightsAddsAppropriateHeader() throws Exception{
	   System.out.println (" Dyn Port is"+dynamicPort.getNumber());
    	MuleEvent event = runFlow("retrieveFlights");
    	String contentType = event.getMessage().getOutboundProperty("Content-Type");
    	assertEquals("application/json", contentType);
    }

}
