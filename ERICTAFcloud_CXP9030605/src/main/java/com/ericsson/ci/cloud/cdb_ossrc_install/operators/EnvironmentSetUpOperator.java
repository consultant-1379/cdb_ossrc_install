package com.ericsson.ci.cloud.cdb_ossrc_install.operators;

public interface EnvironmentSetUpOperator {

	boolean executeInitialInstall();

	boolean executeInitialUpgrade();

	boolean manageMC();

    boolean executeArneImport();

    boolean prepareUsers();
    
    boolean configureTorMS();
    
    boolean configureTorPeerNodes();

    boolean physicalInstallation();

    boolean autoDeploymentInstallation();

    boolean executeSonVisInitialInstall();
    
    boolean executeSimdep();
    
    boolean disablePasswordExpiry(); 
    
    boolean disablePasswordLockout();
    
    boolean disablePasswordMustChange();
    
    boolean removePasswordChangeHistory();
    	
    boolean reduceMinPasswordLength();

	boolean executeNetsimRollOutConfig();    
    
}
