/*
 * Copyright © 2023 Shcherbina Daniil
 * License: http://opensource.org/licenses/MIT
 */

package io.github.ausf_software.jcp;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Static parser class of parameters passed when the application is launched.
 *
 * @author  Shcherbina Daniil
 * @since 1.0
 * @version 2.0
 */
public class CommandParser {

    public static ArrayList<CommandToken> parse(String[] args) throws Exception {
        ArrayList<CommandToken> temp = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            if (args[i].charAt(1) == '-') {
                temp.add(new CommandToken(args[i].substring(2)));
            } else {
                if (args[i].charAt(0) == '-' && i < args.length - 1) {
                    temp.add(new CommandToken(args[i].substring(1), args[i + 1]));
                    i++;
                } else {
                    throw new UnregisteredParameterType(args[i], i);
                }
            }
        }
        return temp;
    }

    public static ArrayList<CommandToken> parse(String[] args, String rulePath)
            throws IOException, UnregisteredParameterType {
        ArrayList<CommandToken> temp = new ArrayList<>();
        File rules = new File(rulePath);
        Properties properties = new Properties();
        properties.load(new FileReader(rules));

        String flagPref = properties.getProperty("flagOperator");
        String paramPref = properties.getProperty("commandOperator");

        boolean checkFlags = Boolean.parseBoolean(properties.getProperty("checkParametersFromMap"));
        boolean checkParameter = Boolean.parseBoolean(properties.getProperty("checkFlagsFromMap"));

        List<String> flags = Arrays.asList(properties.getProperty("flagMap").split(","));
        List<String> params = Arrays.asList(properties.getProperty("parameterMap").split(","));

        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith(flagPref) && (!checkFlags || (checkFlags
                    && flags.contains(args[i].substring(flagPref.length()))))) {
                temp.add(new CommandToken(args[i].substring(flagPref.length())));
                continue;
            }

            if ((args[i].startsWith(paramPref) && i < args.length - 1)
                    && (!checkParameter || (checkParameter
                    && params.contains(args[i].substring(paramPref.length()))))) {
                temp.add(new CommandToken(args[i].substring(paramPref.length()), args[i + 1]));
                i++;
                continue;
            }

            throw new UnregisteredParameterType(args[i], i);
        }
        return temp;
    }

}
