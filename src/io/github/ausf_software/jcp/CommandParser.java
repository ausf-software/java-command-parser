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
 * @version 1.1
 */
public class CommandParser {

    /**
     * Parses tokens from a given array of strings in the following format:
     * <ul>
     *      <li>--flag</li>
     *      <li>-parameterName parameterText</li>
     * </ul>
     *
     * @param args array of strings containing parameters for the operation of the application
     * @return returns a list of tokens with the found parameters
     * @throws UnregisteredParameterTypeException If a token is found that does not match the
     * form of the record
     */
    public static ArrayList<CommandToken> parse(String[] args) throws UnregisteredParameterTypeException {
        ArrayList<CommandToken> temp = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            if (args[i].charAt(1) == '-') {
                temp.add(new CommandToken(args[i].substring(2)));
            } else {
                if (args[i].charAt(0) == '-' && i < args.length - 1) {
                    temp.add(new CommandToken(args[i].substring(1), args[i + 1]));
                    i++;
                } else {
                    throw new UnregisteredParameterTypeException(args[i], i);
                }
            }
        }
        return temp;
    }

    /**
     * Parses tokens from a given array of strings in accordance with the rules
     * specified in a separate 'properties' file.
     *
     * @param args array of strings containing parameters for the
     *             operation of the application
     * @param rulePath the path to the 'properties' file containing
     *                 custom parameters for defining tokens
     * @return returns a list of tokens with the found parameters
     * @throws UnregisteredParameterTypeException If a token is found that does not match the
     * form of the record
     * @throws IOException If it does not find a file containing custom parameters for determining
     * tokens
     * @throws FileMissingParameterException if all the parameters for custom token definition are
     * missing in the file
     */
    public static ArrayList<CommandToken> parse(String[] args, String rulePath)
            throws IOException, UnregisteredParameterTypeException, FileMissingParameterException {
        ArrayList<CommandToken> temp = new ArrayList<>();
        File rules = new File(rulePath);
        Properties properties = new Properties();
        properties.load(new FileReader(rules));

        if (!properties.contains("flagOperator") ||
                !properties.contains("commandOperator") ||
                !properties.contains("checkParametersFromMap") ||
                !properties.contains("checkFlagsFromMap") ||
                !properties.contains("flagMap") ||
                !properties.contains("parameterMap")) {
            throw new FileMissingParameterException();
        }

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

            throw new UnregisteredParameterTypeException(args[i], i);
        }
        return temp;
    }

}
