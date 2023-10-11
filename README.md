# Java command parser
A small library parsing the input parameters specified when launching the application.
## Types of parameters
* Flag:
```sh
  --a
```
* Parameter: 
```sh
  -g text_as_a_parameter
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