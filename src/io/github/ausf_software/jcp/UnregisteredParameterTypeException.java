/*
 * Copyright © 2023 Shcherbina Daniil
 * License: http://opensource.org/licenses/MIT
 */

package io.github.ausf_software.jcp;

/**
 * Thrown when the application cannot determine the type of the received parameter.
 *
 * @author  Shcherbina Daniil
 * @since 1.0
 * @version 1.0
 */
public class UnregisteredParameterTypeException extends Exception {
    /**
     * Constructs a {@code UnregisteredParameterType} with the specified
     * detail message.
     *
     * @param arg the text of the intended parameter.
     * @param pos position
     */
    public UnregisteredParameterTypeException(String arg, int pos) {
        super(arg + ", pos: " + pos);
    }
}
