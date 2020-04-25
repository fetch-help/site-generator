package com.fetch.sitegenerator;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import java.io.StringWriter;

public class Generator {

    public String run(VelocityContext vc, Template template){
        StringWriter sw = new StringWriter();
        template.merge(vc, sw);
        return sw.toString();
    }
}
