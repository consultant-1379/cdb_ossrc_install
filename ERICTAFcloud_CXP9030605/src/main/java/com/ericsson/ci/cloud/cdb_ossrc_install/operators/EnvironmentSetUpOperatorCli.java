package com.ericsson.ci.cloud.cdb_ossrc_install.operators;


import org.apache.log4j.Logger;

import com.ericsson.ci.cloud.cdb_ossrc_install.getters.EnvironmentSetUpGetter;
import com.ericsson.ci.cloud.cdb_ossrc_install.getters.TorEnvironmentSetUpGetter;
import com.ericsson.ci.cloud.cdb_ossrc_install.getters.SonVisEnvironmentSetUpGetter;
import com.ericsson.cifwk.taf.annotations.Context;
import com.ericsson.cifwk.taf.annotations.Operator;
import com.ericsson.cifwk.taf.data.DataHandler;
import com.ericsson.cifwk.taf.tools.cli.CLI;
import com.ericsson.cifwk.taf.tools.cli.CLITool;
import com.ericsson.cifwk.taf.tools.cli.Shell;
import com.ericsson.cifwk.taf.tools.cli.jsch.JSchCLIToolException;

@Operator(context = { Context.CLI })
public class EnvironmentSetUpOperatorCli implements EnvironmentSetUpOperator {

    private static final CLI cli = new CLI(DataHandler.getHostByName("gateway"));

    private static final Logger logger = Logger.getLogger(EnvironmentSetUpOperatorCli.class); 
    
    private static final String EXIT_CODE = "EXIT_CODE:";

    private boolean runCommand(String command, Long timeOut) {
        logger.info("Running "+ command +" cmd");
        Shell runShell = cli.executeCommand(command, "echo \"" + EXIT_CODE + "\"$?");

        String output = null;
        while (!runShell.isClosed() && timeOut > 0) {
            output = runShell.read();
            if (output.length() > 1){
                logger.debug(output);
            }
            timeOut -= CLITool.DEFAULT_TIMEOUT_SEC;
        }

        String lastLine = runShell.read();
        short result = -1;
        try {
            if (lastLine.length() < EXIT_CODE.length())
                lastLine = output;
            String exitCode = lastLine.split(EXIT_CODE)[1].trim();
            result = Short.valueOf(exitCode);
        } catch (JSchCLIToolException | IndexOutOfBoundsException e) {
            logger.error("Cannot get command result", e);

        }
        logger.info("Shell exit code is " + result);
        runShell.disconnect();
        return result == 0;

    }

    @Override
    public boolean executeInitialInstall() {
        return runCommand(EnvironmentSetUpGetter.getInitialInstallCommand(), 43200L);
    }
    
	//New method added for Cloud Upgrade call 
    @Override
    public boolean executeInitialUpgrade() {
        return runCommand(EnvironmentSetUpGetter.getInitialUpgradeCommand(), 43200L);
    }


    @Override
    public boolean executeArneImport() {
        return runCommand(EnvironmentSetUpGetter.getArneImportCommand(), 14400L);
    }
    
    @Override
	public boolean executeSimdep() {
    	return runCommand(EnvironmentSetUpGetter.getSimdepCommand(), 14400L);
	}

    @Override
    public boolean prepareUsers() {
        return runCommand(EnvironmentSetUpGetter.getAddUserCommand(), 3600L);
    }

	@Override
	public boolean configureTorMS() {
		return runCommand(TorEnvironmentSetUpGetter.getConfigureTorMSCommand(), 16200L);
	}

	@Override
	public boolean configureTorPeerNodes() {
		return runCommand(TorEnvironmentSetUpGetter.getConfigureTorPeerNodesCommand(), 16200L);
	}

	@Override
	public boolean physicalInstallation() {
		return runCommand(TorEnvironmentSetUpGetter.getPhysicalInstallationCommand(), 32400L);
	}

	@Override
	public boolean autoDeploymentInstallation() {
		return runCommand(TorEnvironmentSetUpGetter.getautoDeploymentInstallationCommand(), 54000L);
	}

	public boolean executeSonVisInitialInstall() {
        	return runCommand(SonVisEnvironmentSetUpGetter.getSonVisInitialInstallCommand(), 32400L);
    	}

	@Override
	public boolean manageMC() {
		return runCommand(EnvironmentSetUpGetter.getManageMCCommand(), 32400L);
	}

	@Override
	public boolean disablePasswordExpiry() {
		return runCommand(EnvironmentSetUpGetter.getDisablePasswordExpiryCommand(), 3600L);
	}

	@Override
	public boolean disablePasswordLockout() {
		return runCommand(EnvironmentSetUpGetter.getDisablePasswordLockoutCommand(), 3600L);
	}

	@Override
	public boolean disablePasswordMustChange() {
		return runCommand(EnvironmentSetUpGetter.getDisablePasswordMustChangeCommand(), 3600L);
	}

	@Override
	public boolean removePasswordChangeHistory() {
		return runCommand(EnvironmentSetUpGetter.getRemovePasswordChangeHistoryCommand(), 3600L);
	}

	@Override
	public boolean reduceMinPasswordLength() {
		return runCommand(EnvironmentSetUpGetter.getReduceMinPasswordLengthCommand(), 3600L);
	}

	@Override
	public boolean executeNetsimRollOutConfig() {
		return runCommand(EnvironmentSetUpGetter.getNetsimRollOutConfigCommand(), 34400L);
	}


}
