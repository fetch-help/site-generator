package com.fetch.sitegenerator.context;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class FetchHeaderContext implements FetchContext{

    @Override
    public VelocityContext getContext(VelocityEngine ve) {
        VelocityContext vc = new VelocityContext();
        return vc;
    }
}
