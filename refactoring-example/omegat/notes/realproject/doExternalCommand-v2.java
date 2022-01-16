package org.omegat.core.data;
class RealProject {	
	// "doExternalCommand","RealProject",,5,0.8  
	// CINT = isEmpty,showStatusMessageRB,CommandVarExpansion,expandVariables,log
	// CINT = 5
	// FANOUT = StringUtil,Core,CommandVarExpansion,Log
	// FANOUT = 4
	// CDISP = 4/5

	private void doExternalCommand(String command) {
	    if (StringUtil.isEmpty(command)) {
	        return;
	    }
	    Core.showStatusMessageRB("CT_START_EXTERNAL_CMD");
	    command = new CommandVarExpansion(command).expandVariables(config);
	    Log.log("Executing command: " + command);
	    try {
	        startCmdMonitor(command);
	    } catch (IOException e) {
	        errorExtCommand(e);
	    }
	}

}

