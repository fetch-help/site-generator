package com.fetch.sitegenerator.context;

import com.fetch.sitegenerator.Generator;
import com.fetch.sitegenerator.model.ProductCatalogDb;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.util.ArrayList;
import java.util.List;

public class FetchLayoutContext implements FetchContext{

    final ProductCatalogDb db;

    public FetchLayoutContext(ProductCatalogDb db){
        this.db = db;
    }

    @Override
    public VelocityContext getContext(VelocityEngine ve) {
        VelocityContext vc = new VelocityContext();
        vc.put("header", new Generator().run(vc, ve.getTemplate("header.vm")));
        vc.put("footer", new Generator().run(vc, ve.getTemplate("footer.vm")));
        vc.put("db", db);
        return vc;
    }
}
