package com.ericsson.ci.cloud.cdb_ossrc_install.test.cases;

import javax.inject.Inject;

import org.testng.annotations.Test;

import com.ericsson.cifwk.taf.TestCase;
import com.ericsson.cifwk.taf.TorTestCaseHelper;
import com.ericsson.cifwk.taf.annotations.Context;
import com.ericsson.cifwk.taf.guice.OperatorRegistry;
import com.ericsson.ci.cloud.cdb_ossrc_install.operators.EnvironmentSetUpOperator;
import com.ericsson.ci.cloud.cdb_ossrc_install.operators.EnvironmentSetUpOperatorCli;


public class EnvironmentUpgradeAndSetUp extends TorTestCaseHelper implements TestCase {

    @Inject
    OperatorRegistry<EnvironmentSetUpOperator> operatorRegistry;

    private EnvironmentSetUpOperator getOperator() {
        return operatorRegistry.provide(EnvironmentSetUpOperator.class);
    }

    @Context(context = { Context.CLI })
    @Test
    public void verifyEnvironmentUpgrade() {
    	assertTrue(getOperator().executeInitialUpgrade());
    }
    
    @Context(context = { Context.CLI })
    @Test(dependsOnMethods = { "verifyEnvironmentUpgrade" })
    public void verifyMCInitialisation() {
    	assertTrue(getOperator().manageMC());
    }
    
    @Context(context = { Context.CLI })
    @Test(dependsOnMethods = { "verifyMCInitialisation" })
    public void verifyNetworkPreparation() {
		assertTrue(getOperator().executeNetsimRollOutConfig());
    }

}
