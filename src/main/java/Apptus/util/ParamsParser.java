package Apptus.util;

import java.io.File;


class ParamsParser {

    private String[] args;
    private boolean verbose;
    private File configFile;
    private File baseDirFile;

    public ParamsParser(String... args) {
        this.args = args;
    }

    public boolean isVerbose() {
        return verbose;
    }

    public File getConfigFile() {
        return configFile;
    }

    public File getBaseDirFile() {
        return baseDirFile;
    }

    public ParamsParser parse() throws Exception {
        if (args.length > 3) {
            throw new Exception();
        }

        String templatesPath = null;
        verbose = false;
        String regexRulesPath = null;

        for (String arg : args) {
            if ("-verbose".equals(arg)) {
                verbose = true;
                continue;
            }

            if (arg.startsWith("-rules=")) {
                regexRulesPath = arg.replace("-rules=", "");
                continue;
            }

            if (!arg.startsWith("-")) {
                templatesPath = arg;
                continue;
            } else {
                throw new Exception("Unknown param: " + arg);
            }
        }

        if (templatesPath == null) {
            templatesPath = System.getProperty("user.dir");
        }

        if (!templatesPath.endsWith("/")) {
            templatesPath = templatesPath + "/";
        }

        configFile = null;
        if (regexRulesPath != null) {
            configFile = new File(regexRulesPath);
        }

        baseDirFile = new File(templatesPath);
        return this;
    }
}
