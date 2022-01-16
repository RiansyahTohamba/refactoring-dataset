package org.omegat.core.data;
// CINT = 9, CDISP = 0.78, FANOUT = 7
// CDISP = FANOUT/CINT
// FANOUT = CINT * CDISP
// =  9 * 0.78 
// =~ 7
// ada 7 FANOUT
// apakah cdisp menghitung java standardlibrary, misal IOException ?
// pada try-catch ini ada 2 FANOUT tambahan
// 2 FANOUT = StaticUtils, CommandMonitor
class RealProject {
    private void doExternalCommand(String command) {
        if (StringUtil.isEmpty(command)) {
            return;
        }
        Core.getMainWindow().showStatusMessageRB("CT_START_EXTERNAL_CMD");
        CommandVarExpansion expander = new CommandVarExpansion(command);
        command = expander.expandVariables(config);
        Log.log("Executing command: " + command);
        try {
            Process p = Runtime.getRuntime().exec(StaticUtils.parseCLICommand(command));
            processCache.push(p);
            CommandMonitor stdout = CommandMonitor.newStdoutMonitor(p);
            CommandMonitor stderr = CommandMonitor.newStderrMonitor(p);
            stdout.start();
            stderr.start();
        } catch (IOException e) {
            String message;
            Throwable cause = e.getCause();
            if (cause == null) {
                message = e.getLocalizedMessage();
            } else {
                message = cause.getLocalizedMessage();
            }
            Core.getMainWindow().showStatusMessageRB("CT_ERROR_STARTING_EXTERNAL_CMD", message);
        }
    }

}
