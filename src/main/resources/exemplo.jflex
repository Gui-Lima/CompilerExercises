package atividade1;

%%

/* Não altere as configurações a seguir */

%line
%column
%unicode
//%debug
%public
%standalone
%class Minijava
%eofclose

/* Insira as regras léxicas abaixo */

//basic stuff
char = [A-Za-z]
digit = [0-9]
whitespace = [ |\n|\t|\f|\r]+

comment = "/*" [^*] ~"*/" | "//" ~[\n|\r]
 
//reserved stuff
flowControl = "if"|"else"|"while"
types = "String"|"int"|"boolean"|"class"
definers = "public"|"extends"|"static"|"main"|"new"
ooLogic = "new"|"void"|"return"|"this"
bValues = "true"|"false"
sysout = "System.out.println"
fCalls = "length"
reserved = {types}|{definers}|{flowControl}|{ooLogic}|{bValues}|{sysout}|{fCalls}

//delimiters
pontuacao = ","|"."|";"
scopeLimiters = "(" | ")" | "[" | "]" | "{" | "}"
assignment = "="
delimiters = {pontuacao}|{scopeLimiters}|{assignment}

//operators
logicOp = "=="|"!="|"<"|"!"|"&&"
valueOp = "+"|"-"|"*"
operators = {logicOp}|{valueOp}

//Ids
allowed = {digit}*|{char}*|"_"
id = [A-Za-z|"_"]{allowed}+

//basic types
integer = 0|[1-9][0-9]*

%%

{integer} {System.out.println("token gerado foi um integer: '" + yytext() + "' na linha: " + yyline + ", coluna: " + yycolumn);}
{operators} {System.out.println("token gerado foi um operador: '" + yytext() + "' na linha: " + yyline + ", coluna: " + yycolumn);}
{delimiters} {System.out.println("token gerado foi um delimitador: '" + yytext() + "' na linha: " + yyline + ", coluna: " + yycolumn);}
{comment} {}
{whitespace} {} 
{reserved} {System.out.println("token gerado foi um reservado: '" + yytext() + "' na linha: " + yyline + ", coluna: " + yycolumn);}
{char} {System.out.println("token gerado foi um char: '" + yytext() + "' na linha: " + yyline + ", coluna: " + yycolumn);}
{id} {System.out.println("token gerado foi um id: '" + yytext() + "' na linha: " + yyline + ", coluna: " + yycolumn);}

/* Insira as regras léxicas no espaço acima */     
     
. { throw new RuntimeException("Caractere ilegal! '" + yytext() + "' na linha: " + yyline + ", coluna: " + yycolumn); }
