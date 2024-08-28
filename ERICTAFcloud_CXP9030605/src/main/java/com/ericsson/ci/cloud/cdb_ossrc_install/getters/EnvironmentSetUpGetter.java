package com.ericsson.ci.cloud.cdb_ossrc_install.getters;

import com.ericsson.cifwk.taf.data.DataHandler;
import com.ericsson.cifwk.taf.tools.cli.CLI;
import com.ericsson.cifwk.taf.tools.cli.Shell;

public class EnvironmentSetUpGetter {

    private static String configFiles;
    private static String hostName;

    private static String getConfigFiles() {
        if (configFiles == null)
            configFiles = DataHandler.getAttribute("CONFIG_FILES").toString();
        return configFiles;

    }

    private static String getHostName() {
        if (hostName == null){
        	CLI cli = new CLI(DataHandler.getHostByName("gateway"));
        	Shell sh = cli.executeCommand("hostname");
        	hostName = sh.read();
        	sh.disconnect();
        	//            hostName = DataHandler.getHostByName("gateway").getIp();
        
        }return hostName;
    }

    public static String getInitialInstallCommand() {
        return "/export/scripts/CLOUD/bin/master.sh -c " + getConfigFiles() + " -g `hostname` -o yes -l /export/scripts/CLOUD/logs/web/CI_EXEC_OSSRC/ -f rollout_config";
    }
    
	//Command for Cloud upgrade
    public static String getInitialUpgradeCommand() {
        return "/export/scripts/CLOUD/bin/master.sh -c " + getConfigFiles() + " -g `hostname` -o yes -l /export/scripts/CLOUD/logs/web/CI_EXEC_OSSRC/ -f upgrade_adm";
    }

    //Command for initialising MC's
    public static String getManageMCCommand() {
        return "/export/scripts/CLOUD/bin/master.sh -c " + getConfigFiles() + " -g `hostname` -o yes -l /export/scripts/CLOUD/logs/web/CI_EXEC_OSSRC/ -f manage_mcs_initial";
    }
    

    public static String getArneImportCommand() {
        return "/export/scripts/CLOUD/bin/master.sh -c " + getConfigFiles() + "  -g `hostname`  -o yes -l /export/scripts/CLOUD/logs/web/CI_EXEC_OSSRC/ -f netsim_post_steps";
    }

    public static String getAddUserCommand() {
        return "/export/scripts/CLOUD/bin/master.sh -c " + getConfigFiles() + " -g `hostname` -o yes -l /export/scripts/CLOUD/logs/web/CI_EXEC_OSSRC/ -f create_users_config";

    }
    
    public static String getSimdepCommand() {
        return "/export/scripts/CLOUD/bin/master.sh -c " + getConfigFiles() + "  -g `hostname`  -o yes -l /export/scripts/CLOUD/logs/web/CI_EXEC_OSSRC/ -f simdep_call";
    }
    
    //Command to disable password expiry
    public static String getDisablePasswordExpiryCommand() {
        return "/export/scripts/CLOUD/bin/master.sh -c " + getConfigFiles() + "  -g `hostname`  -o yes -l /export/scripts/CLOUD/logs/web/CI_EXEC_OSSRC/ -f disable_password_expiry";
    }
    
    public static String getDisablePasswordLockoutCommand() {
        return "/export/scripts/CLOUD/bin/master.sh -c " + getConfigFiles() + "  -g `hostname`  -o yes -l /export/scripts/CLOUD/logs/web/CI_EXEC_OSSRC/ -f disable_password_lockout";
    }
    
    public static String getDisablePasswordMustChangeCommand() {
        return "/export/scripts/CLOUD/bin/master.sh -c " + getConfigFiles() + "  -g `hostname`  -o yes -l /export/scripts/CLOUD/logs/web/CI_EXEC_OSSRC/ -f disable_password_must_change";
    }
    
    public static String getRemovePasswordChangeHistoryCommand() {
        return "/export/scripts/CLOUD/bin/master.sh -c " + getConfigFiles() + "  -g `hostname`  -o yes -l /export/scripts/CLOUD/logs/web/CI_EXEC_OSSRC/ -f remove_password_change_history";
    }
    
    public static String getReduceMinPasswordLengthCommand() {
        return "/export/scripts/CLOUD/bin/master.sh -c " + getConfigFiles() + "  -g `hostname`  -o yes -l /export/scripts/CLOUD/logs/web/CI_EXEC_OSSRC/ -f reduce_min_password_length";
    }

	public static String getNetsimRollOutConfigCommand() {
	      return "/export/scripts/CLOUD/bin/master.sh -c " + getConfigFiles() + "  -g `hostname`  -o yes -l /export/scripts/CLOUD/logs/web/CI_EXEC_OSSRC/ -f  netsim_rollout_config";	      
	}
    
    
}
