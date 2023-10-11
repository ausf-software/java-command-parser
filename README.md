# Java command parser
A small library parsing the input parameters specified when launching the application.
## Specifying default parameters
* Flag:
```sh
  --a
```
* Parameter: 
```sh
  -g text_as_a_parameter
```
## Parameters in the 'properties' file:
* flagOperator - the string specified in this parameter will be a prefix for flags
* commandOperator - the string specified in this parameter will be a prefix for parameters that 
  require specifying some string after themselves
* checkParametersFromMap - value 'true' to check for matching parameter names
* checkFlagsFromMap - value 'true' to check for matching flag names
* flagMap - a comma separated string containing all the names of the flags supported by the application
* parameterMap - a comma separated string containing all the names of the parameters supported by the application

## Example of a custom rule for defining tokens
```properties
flagOperator=--
commandOperator=-
checkParametersFromMap=true
checkFlagsFromMap=true
flagMap=a,b,c
parameterMap=abc,abv
```

## Usage example
```java
ArrayList<CommandToken> ttokens = null;
try {
    ttokens = CommandParser.parse(args);
} catch (Exception e) {
    e.printStackTrace();
}
System.out.println(ttokens);
```