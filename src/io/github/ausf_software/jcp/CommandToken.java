/*
 * Copyright © 2023 Shcherbina Daniil
 * License: http://opensource.org/licenses/MIT
 */

package io.github.ausf_software.jcp;

/**
 * A class describing the parameter token.
 *
 * @author  Shcherbina Daniil
 * @since 1.0
 * @version 1.0
 */
public class CommandToken {

    private CTokenType type;
    private String textType;
    private String text;

    public CommandToken(String textType) {
        this.textType = textType;
        type = CTokenType.FLAG;
    }

    public CommandToken(String textType, String text) {
        this.textType = textType;
        this.text = text;
        type = CTokenType.PARAMETER;
    }

    public CTokenType getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "[" + type + ":" + textType + ':' + text + ']';
    }
}
