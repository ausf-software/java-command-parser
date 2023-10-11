/*
 * Copyright © 2023 Shcherbina Daniil
 * License: http://opensource.org/licenses/MIT
 */

package io.github.ausf_software.jcp;

/**
 * An enumeration containing the types of possible tokens.
 */
public enum CTokenType {
    /**
     * A parameter that, when specified as arguments for launching the application, sets some
     * variable to true
     */
    FLAG,
    /**
     * A parameter that, when specified as arguments for launching the application, assigns to
     * some variable the value specified after this parameter as a string
     */
    PARAMETER;
}