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
integer = {digit}+
whitespace = [ |\n|\t|\f|\r]+


comment = "/*" [^*] ~"*/" | "//" ~[\n|\r]
 
//reserved stuff
flowControl = "if"|"else"|"while"
types = "String"|"int"|"boolean"|"class"
definers = "public"|"extends"|"static"|"main"|"new"
ooLogic = "new"|"void"|"return"|"this"
bValues = "true"|"false"
sysout = "System.out.println"
reserved = {types}|{definers}|{flowControl}|{ooLogic}|{bValues}|{sysout}


%%
//ignoramos comentarios e whitespace
{comment} {}
{whitespace} {} 
{reserved} {}
{char} {}
/* Insira as regras léxicas no espaço acima */     
     
. { throw new RuntimeException("Caractere ilegal! '" + yytext() + "' na linha: " + yyline + ", coluna: " + yycolumn); }
