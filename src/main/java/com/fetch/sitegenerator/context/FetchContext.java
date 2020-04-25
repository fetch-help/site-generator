package com.fetch.sitegenerator.context;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public interface FetchContext {

    VelocityContext getContext(VelocityEngine ve);
}
