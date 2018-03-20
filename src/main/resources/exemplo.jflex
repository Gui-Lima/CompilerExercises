package atividade1;

%%

/* N�o altere as configura��es a seguir */

%line
%column
%unicode
//%debug
%public
%standalone
%class Minijava
%eofclose

/* Insira as regras l�xicas abaixo */

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
/* Insira as regras l�xicas no espa�o acima */     
     
. { throw new RuntimeException("Caractere ilegal! '" + yytext() + "' na linha: " + yyline + ", coluna: " + yycolumn); }
