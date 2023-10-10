/*
 * Copyright © 2023 Shcherbina Daniil
 * License: http://opensource.org/licenses/MIT
 */

package io.github.ausf_software.jcp;

import java.util.ArrayList;

/**
 * Static parser class of parameters passed when the application is launched.
 *
 * @author  Shcherbina Daniil
 * @since 1.0
 * @version 1.0
 */
public class TerminalParser {

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

}
