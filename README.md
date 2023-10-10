# Java command parser

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
    ttokens = TerminalParser.parse(args);
} catch (Exception e) {
    e.printStackTrace();
}
System.out.println(ttokens);
```