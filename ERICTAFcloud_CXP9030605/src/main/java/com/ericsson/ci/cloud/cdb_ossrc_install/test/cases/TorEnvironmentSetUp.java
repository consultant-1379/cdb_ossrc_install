package com.ericsson.ci.cloud.cdb_ossrc_install.test.cases;

import javax.inject.Inject;

import org.testng.annotations.Test;

import com.ericsson.cifwk.taf.TestCase;
import com.ericsson.cifwk.taf.TorTestCaseHelper;
import com.ericsson.cifwk.taf.annotations.Context;
import com.ericsson.cifwk.taf.guice.OperatorRegistry;
import com.ericsson.ci.cloud.cdb_ossrc_install.operators.EnvironmentSetUpOperator;

public class TorEnvironmentSetUp extends TorTestCaseHelper implements TestCase {

    @Inject
    OperatorRegistry<EnvironmentSetUpOperator> operatorRegistry;

    private EnvironmentSetUpOperator getOperator() {
        return operatorRegistry.provide(EnvironmentSetUpOperator.class);
    }

    @Context(context = { Context.CLI })
    @Test
    public void verifyConfigureTorMS() {
        assertTrue(getOperator().configureTorMS());
    }

    @Context(context = { Context.CLI })
    @Test(dependsOnMethods = { "verifyConfigureTorMS" })
    public void verifyConfigureTorPeerNodes() {
        assertTrue(getOperator().configureTorPeerNodes());
    }

}
