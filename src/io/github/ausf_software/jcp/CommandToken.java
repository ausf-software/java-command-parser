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
 * @version 1.1
 */
public class CommandToken {

    private CTokenType type;
    private String name;
    private String text;

    /**
     * Creates a flag token with the specified name.
     * @param name flag name
     */
    public CommandToken(String name) {
        this.name = name;
        type = CTokenType.FLAG;
    }

    /**
     * Creates a parameter token with the specified name and the specified parameter.
     * @param name parameter name
     * @param text parameter text
     */
    public CommandToken(String name, String text) {
        this.name = name;
        this.text = text;
        type = CTokenType.PARAMETER;
    }

    /**
     * Returns the token type.
     * @return the token type
     */
    public CTokenType getType() {
        return type;
    }

    /**
     * Returns the token name.
     * @return the token name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the text of the token parameter.
     * If the token is of the 'flag' type, null will be returned
     * @return the text of the token parameter
     */
    public String getText() {
        return text;
    }

    /**
     * Returns a text representation of the token content in the
     * form of [token_type:token_name:parameter_text].
     * @return a text representation of the token content in
     *          the form of [token_type:token_name:parameter_text]
     */
    @Override
    public String toString() {
        return "[" + type + ":" + name + ':' + text + ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommandToken that = (CommandToken) o;

        if (type != that.type) return false;
        if (!name.equals(that.name)) return false;
        return text != null ? text.equals(that.text) : that.text == null;
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }
}
