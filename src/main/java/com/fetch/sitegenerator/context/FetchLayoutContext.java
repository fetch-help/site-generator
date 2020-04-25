package com.fetch.sitegenerator.context;

import com.fetch.sitegenerator.Generator;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class FetchLayoutContext implements FetchContext{

    @Override
    public VelocityContext getContext(VelocityEngine ve) {
        VelocityContext vc = new VelocityContext();
        vc.put("header", new Generator().run(vc, ve.getTemplate("header.vm")));
        vc.put("footer", new Generator().run(vc, ve.getTemplate("footer.vm")));
        return vc;
    }
}
