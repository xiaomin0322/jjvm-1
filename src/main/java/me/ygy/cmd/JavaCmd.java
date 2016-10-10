package me.ygy.cmd;

import org.apache.commons.cli.*;

import java.util.List;

/**
 * Author: guangyuanyu
 * Email: guangyuanyu@sohu-inc.com
 * Date: 2016/9/30 17:52.
 */
public class JavaCmd {
    private boolean helpFlag;
    private boolean versionFlag;
    private String cpOption;
    private String clazz;
    private List<String> args;

    public static JavaCmd newCmd(String[] args) throws ParseException {
        if (args.length < 1) {
            throw new IllegalArgumentException();
        }
        Options options = new Options();
        Option helpOpt = new Option("h", false, "print help message");
        options.addOption(helpOpt);

        helpOpt = new Option("?", false, "print help message");
        options.addOption(helpOpt);

        Option versionOpt = new Option("version", false, "print version and exit");
        options.addOption(versionOpt);

        Option cpOpt = new Option("classpath", true, "classpath");
        options.addOption(cpOpt);

        cpOpt = new Option("cp", true, "classpath");
        options.addOption(cpOpt);

        HelpFormatter hf = new HelpFormatter();
        hf.setWidth(110);

        CommandLine commandLine = null;
        PosixParser parser = new PosixParser();
        try {
            commandLine = parser.parse(options, args);
        } catch (ParseException e) {
            hf.printHelp("java", options, true);
            throw e;
        }
        JavaCmd cmd = new JavaCmd();
        cmd.setHelpFlag(commandLine.hasOption("h") || commandLine.hasOption("?"));
        cmd.setVersionFlag(commandLine.hasOption("version"));
        if (commandLine.hasOption("cp")) {
            cmd.setCpOption(commandLine.getOptionValue("cp"));
        }

        if (commandLine.hasOption("classpath")) {
            cmd.setCpOption(commandLine.getOptionValue("classpath"));
        }

        List argList = commandLine.getArgList();
        if (argList.size() > 0) {
            cmd.setClazz(argList.get(0).toString());
            argList.remove(0);
        }
        cmd.setArgs(argList);

        return cmd;
    }

    public boolean isHelpFlag() {
        return helpFlag;
    }

    public void setHelpFlag(boolean helpFlag) {
        this.helpFlag = helpFlag;
    }

    public boolean isVersionFlag() {
        return versionFlag;
    }

    public void setVersionFlag(boolean versionFlag) {
        this.versionFlag = versionFlag;
    }

    public String getCpOption() {
        return cpOption;
    }

    public void setCpOption(String cpOption) {
        this.cpOption = cpOption;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public List<String> getArgs() {
        return args;
    }

    public void setArgs(List<String> args) {
        this.args = args;
    }
}
