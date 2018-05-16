grammar Antlr;

@header {
    package antlr;
}

goal: MainClass ( ClassDeclaration )* ;

MainClass :
 'class'Identifier'{''public''static' 'Void' 'main' '(' 'String' '[' ']' Identifier ')' '{' Statement '}' '}';


ClassDeclaration: 
'class' Identifier ( 'extends' Identifier )? '{' ( VarDeclaration )* ( MethodDeclaration )* '}';


VarDeclaration: Type Identifier ';';


MethodDeclaration: 
'public' Type Identifier '(' ( Type Identifier ( ',' Type Identifier )* )? ')' '{' ( VarDeclaration )* ( Statement )* 'return' Expression ';' '}';


Type: 
'int' '[' ']'
|
'boolean'
|
'int'
|
Identifier ;



Statement: 
'{' ( Statement )* '}'
|
'if' '(' Expression ')' Statement 'else' Statement
|
'while' '(' Expression ')' Statement
|
'System.out.println' '(' Expression ')' ';'
|
Identifier '=' Expression ';'
|
Identifier '[' Expression ']' '=' Expression ';';



Expression: 
INTEGER_LITERAL (ExpAux)?
|
'true' (ExpAux)?
|
'false' (ExpAux)?
|
Identifier  (ExpAux)?
|
'this' (ExpAux)?
|
'new' 'int' '[' Expression ']' (ExpAux)?
|
'new' Identifier '(' ')' (ExpAux)?
|
'!' Expression (ExpAux)?
|
'(' Expression ')' (ExpAux)?;

ExpAux:
( '&&' | '<' | '+' | '-' | '*' ) Expression (ExpAux)?
|
'[' Expression ']' (ExpAux)?
|
'.' 'length' (ExpAux)?
|
'.' Identifier '(' ( Expression ( ',' Expression )* )? ')' (ExpAux)?
;



INTEGER_LITERAL: [0-9][0-9]*;


Identifier: IDENTIFIER;
IDENTIFIER:[A-Za-z0-9]+;

WS: [ \t\r\n] -> skip;
























