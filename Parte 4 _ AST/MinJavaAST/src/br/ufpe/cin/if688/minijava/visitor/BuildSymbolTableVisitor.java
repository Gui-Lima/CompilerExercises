package br.ufpe.cin.if688.minijava.visitor;

import br.ufpe.cin.if688.minijava.ast.And;
import br.ufpe.cin.if688.minijava.ast.ArrayAssign;
import br.ufpe.cin.if688.minijava.ast.ArrayLength;
import br.ufpe.cin.if688.minijava.ast.ArrayLookup;
import br.ufpe.cin.if688.minijava.ast.Assign;
import br.ufpe.cin.if688.minijava.ast.Block;
import br.ufpe.cin.if688.minijava.ast.BooleanType;
import br.ufpe.cin.if688.minijava.ast.Call;
import br.ufpe.cin.if688.minijava.ast.ClassDeclExtends;
import br.ufpe.cin.if688.minijava.ast.ClassDeclSimple;
import br.ufpe.cin.if688.minijava.ast.False;
import br.ufpe.cin.if688.minijava.ast.Formal;
import br.ufpe.cin.if688.minijava.ast.Identifier;
import br.ufpe.cin.if688.minijava.ast.IdentifierExp;
import br.ufpe.cin.if688.minijava.ast.IdentifierType;
import br.ufpe.cin.if688.minijava.ast.If;
import br.ufpe.cin.if688.minijava.ast.IntArrayType;
import br.ufpe.cin.if688.minijava.ast.IntegerLiteral;
import br.ufpe.cin.if688.minijava.ast.IntegerType;
import br.ufpe.cin.if688.minijava.ast.LessThan;
import br.ufpe.cin.if688.minijava.ast.MainClass;
import br.ufpe.cin.if688.minijava.ast.MethodDecl;
import br.ufpe.cin.if688.minijava.ast.Minus;
import br.ufpe.cin.if688.minijava.ast.NewArray;
import br.ufpe.cin.if688.minijava.ast.NewObject;
import br.ufpe.cin.if688.minijava.ast.Not;
import br.ufpe.cin.if688.minijava.ast.Plus;
import br.ufpe.cin.if688.minijava.ast.Print;
import br.ufpe.cin.if688.minijava.ast.Program;
import br.ufpe.cin.if688.minijava.ast.This;
import br.ufpe.cin.if688.minijava.ast.Times;
import br.ufpe.cin.if688.minijava.ast.True;
import br.ufpe.cin.if688.minijava.ast.VarDecl;
import br.ufpe.cin.if688.minijava.ast.While;
import br.ufpe.cin.if688.minijava.symboltable.Class;
import br.ufpe.cin.if688.minijava.symboltable.Method;
import br.ufpe.cin.if688.minijava.symboltable.SymbolTable;

public class BuildSymbolTableVisitor implements IVisitor<Void> {

	//talvez tenha que botar this. em tudo
	SymbolTable symbolTable;

	public BuildSymbolTableVisitor() {
		symbolTable = new SymbolTable();
	}

	public SymbolTable getSymbolTable() {
		return symbolTable;
	}

	private Class currClass;
	private Method currMethod;

	
	public Void visit(Program n) {
		/*Visitando o programa, em geral, ele é estruturado da seguinte forma:
			class xxx {
				public static void main(string[] args){
					System.out.println(funcaoA());
				}
			}
			
			class xxx2{
				codigo;
				codigo;
				return;
			}
			
			class xxx3{
				codigo;
				codigo;
				return;
			}
			....
			
			Em geral aqui não temos nada interessante, vamos visitar primeiro o main, depois
			as outras classes. Lá faremos coisas com a SymbolTable
		 */

		n.m.accept(this);

		for (int i = 0; i < n.cl.size(); i++) {
			n.cl.elementAt(i).accept(this);
		}
		return null;
	}


	public Void visit(MainClass n) {
		
		/*
		 * A mainClass de um programa. 
		 * No parâmetro i1 temos o nome da classe principal, no caso temos que adiciona-la a symbolTable
		 * No parâmetro i2 temos o nome do stringArgs[], que geralmente é 'a' nos testes
		 * No parâmetro s temos o corpo do main, no qual acontece as coisas interessantes
		 * 
		 * public static void main(String[] a){
		 *     código;
		 * }
		 * 
		 */
		
		//Adicionando a classe principal(que fica em cima do Main), ela não tem pai
		symbolTable.addClass(n.i1.s, null);
		//Como estamos dentro do Main, temos que setar a currentClass para justamente oque acabou de adicionar
		currClass = symbolTable.getClass(n.i1.s);
		//Assim como o método atual que estamos, que é o Main! temos que adiciona-lo na classe. O tipo é null pq void
		currClass.addMethod("main", null );
		currMethod = currClass.getMethod("main");
		//Eu ainda não sei se esse é o tipo certo, é um array de Strings
		currMethod.addParam(n.i2.s, new IntArrayType());
		
		n.i1.accept(this);
		n.i2.accept(this);
		n.s.accept(this);
		
		//reset the currClass and currMethod after leaves main
		currClass = null;
		currMethod = null;
		return null;
	}

	
	public Void visit(ClassDeclSimple n) {
		/* Uma declaração de classe, isso geralmente ocorre depois do Main, uma classe é algo como:
		 * 	class xxx{
		 * 		código;
		 * 		Metódo(args){
		 * 			código;
		 * 		}
		 * 
		 * 		Método(args){
		 * 			código
		 * 		}
		 * }
		 * 
		 */
		
		
		//Adicionando a classe atual como uma filha da currentClass, caso seja alguma
		if(currClass != null) {
			//significa que já existia uma classe com esse nome
			if(!symbolTable.addClass(n.i.s, currClass.getId())){
				System.out.println("Já tem uma classe com esse nome");
				return null;
			}
		}
		else{
			if(!symbolTable.addClass(n.i.s, currClass.getId())){
				System.out.println("Já tem uma classe com esse nome");
				return null;
			}
		}
		
		//Agora que estamos entrando na classe, ela passa a ser a currentClass
		currClass = symbolTable.getClass(n.i.s);
		
		n.i.accept(this);
		
		//Temos uma lista de varDeclarations
		for (int i = 0; i < n.vl.size(); i++) {
			n.vl.elementAt(i).accept(this);
		}
		//e uma lista de MethodDeclarations
		for (int i = 0; i < n.ml.size(); i++) {
			n.ml.elementAt(i).accept(this);
		}
		
		currClass = null;
		return null;
	}


	public Void visit(ClassDeclExtends n) {
		/* Uma declaração de classe que extende outra, isso geralmente ocorre depois do Main, uma classe é algo como:
		 * 	class xxx extends yyy{
		 * 		código;
		 * 		Metódo(args){
		 * 			código;
		 * 		}
		 * 
		 * 		Método(args){
		 * 			código
		 * 		}
		 * }
		 * 
		 */
		
		
		//Adicionando a classe atual como uma filha da currentClass, caso seja alguma
		if(currClass != null) {
			//significa que já existia uma classe com esse nome
			if(!symbolTable.addClass(n.i.s, currClass.getId())){
				System.out.println("Já tem uma classe com esse nome");
				return null;
			}
		}
		else{
			if(!symbolTable.addClass(n.i.s, currClass.getId())){
				System.out.println("Já tem uma classe com esse nome");
				return null;
			}
		}
		
		currClass = symbolTable.getClass(n.i.s);
				
		n.i.accept(this);
		n.j.accept(this);
		for (int i = 0; i < n.vl.size(); i++) {
			n.vl.elementAt(i).accept(this);
		}
		for (int i = 0; i < n.ml.size(); i++) {
			n.ml.elementAt(i).accept(this);
		}
		return null;
	}


	
	public Void visit(VarDecl n) {
		/*Chegamos numa declaração de variável! Aqui nós botaremos a variável no escopo dela, que se tudo estiver certo é,
		 * se existir um método, ela pertence ao método, se não existir ela pertence a classe.
		 * Exemplo de varDecl:
		 *	int[] number ;
		 *	int size ;
		 */
	
		System.out.println("	VarDecl");
		
		if(currMethod != null) {
			//eu não sei se devo adicionar na classe também
			currMethod.addVar(n.i.s, n.t);
		}
		else {
			currClass.addVar(n.i.s, n.t);
		}
		
		//n.t.accept(this);
		//n.i.accept(this);
		return null;
	}

	
	public Void visit(MethodDecl n) {
		/*Declaração de um método! Geralmente é uma função criada dentro de uma classe, na forma:
		 * class xxx{
		 *		código...;
		 *
		 * 		metódo(args){
		 * 			código
		 * 		}
		 * 
		 * 
		 *}
		 */
		
		
		System.out.println("	MethodDecl");
		
		//Temos que criar esse método na classe atual, setar ele como o current e pegar os parâmetros e tal
		if(currClass != null) {
			currClass.addMethod(n.i.s, n.t);
		}
		else {
			System.out.println("Erro: Método declarado fora de classes");
			return null;
		}
		
		currMethod = currClass.getMethod(n.i.s);
		
		//n.t.accept(this);
		//n.i.accept(this);
		
		//lista de parâmetros, pelo q entendi
		for (int i = 0; i < n.fl.size(); i++) {
			n.fl.elementAt(i).accept(this);
		}
		//lista de varDeclarations
		for (int i = 0; i < n.vl.size(); i++) {
			n.vl.elementAt(i).accept(this);
		}
		//lista de statements
		for (int i = 0; i < n.sl.size(); i++) {
			n.sl.elementAt(i).accept(this);
		}
		//n sei
		n.e.accept(this);
		return null;
	}

	// Type t;
	// Identifier i;
	public Void visit(Formal n) {
		/*Isso aqui é chamado Formal mas é na verdade um parâmetro!!!
		 */
		
		System.out.println("	Formal");
		
		//Precisamos adicionar esse parâmetro no método em que estamos
		if(currMethod!=null)
			currMethod.addParam(n.i.s, n.t);
		else
			return null;
		
		//n.t.accept(this);
		//n.i.accept(this);
		return null;
	}

	public Void visit(IntArrayType n) {
		System.out.println("IntArrayType");
		return null;
	}

	public Void visit(BooleanType n) {
		System.out.println("BooleanType");
		return null;
	}

	public Void visit(IntegerType n) {
		System.out.println("IntegerType");
		return null;
	}

	// String s;
	public Void visit(IdentifierType n) {
		System.out.println("IdType");
		return null;
	}

	// StatementList sl;
	public Void visit(Block n) {
		System.out.println("Block");
		for (int i = 0; i < n.sl.size(); i++) {
			n.sl.elementAt(i).accept(this);
		}
		return null;
	}

	// Exp e;
	// Statement s1,s2;
	public Void visit(If n) {
		System.out.println("if");
		n.e.accept(this);
		n.s1.accept(this);
		n.s2.accept(this);
		return null;
	}

	// Exp e;
	// Statement s;
	public Void visit(While n) {
		System.out.println("while");
		n.e.accept(this);
		n.s.accept(this);
		return null;
	}

	// Exp e;
	public Void visit(Print n) {
		//Um print! No momento estamos fazendo uma tabela de símbolos, não vejo nem pra que visitar oque tem dentro do print
		//visto que não vai ter nenhuma declaração dentro dele(acho)
		
		System.out.println("	Print statment");
		//n.e.accept(this);
		return null;
	}

	// Identifier i;
	// Exp e;
	public Void visit(Assign n) {
		System.out.println("Assign");
		n.i.accept(this);
		n.e.accept(this);
		return null;
	}

	// Identifier i;
	// Exp e1,e2;
	public Void visit(ArrayAssign n) {
		System.out.println("Array Assign");
		n.i.accept(this);
		n.e1.accept(this);
		n.e2.accept(this);
		return null;
	}

	// Exp e1,e2;
	public Void visit(And n) {
		System.out.println("And");
		n.e1.accept(this);
		n.e2.accept(this);
		return null;
	}

	// Exp e1,e2;
	public Void visit(LessThan n) {
		System.out.println("<");
		n.e1.accept(this);
		n.e2.accept(this);
		return null;
	}

	// Exp e1,e2;
	public Void visit(Plus n) {
		System.out.println("+");
		n.e1.accept(this);
		n.e2.accept(this);
		return null;
	}

	// Exp e1,e2;
	public Void visit(Minus n) {
		System.out.println("-");
		n.e1.accept(this);
		n.e2.accept(this);
		return null;
	}

	// Exp e1,e2;
	public Void visit(Times n) {
		System.out.println("*");
		n.e1.accept(this);
		n.e2.accept(this);
		return null;
	}

	// Exp e1,e2;
	public Void visit(ArrayLookup n) {
		System.out.println("Array Lookup");
		n.e1.accept(this);
		n.e2.accept(this);
		return null;
	}

	// Exp e;
	public Void visit(ArrayLength n) {
		System.out.println("Array Length");
		n.e.accept(this);
		return null;
	}

	// Exp e;
	// Identifier i;
	// ExpList el;
	public Void visit(Call n) {
		System.out.println("Call");
		n.e.accept(this);
		n.i.accept(this);
		for (int i = 0; i < n.el.size(); i++) {
			n.el.elementAt(i).accept(this);
		}
		return null;
	}

	// int i;
	public Void visit(IntegerLiteral n) {
		System.out.println("Int Literal");
		return null;
	}

	public Void visit(True n) {
		System.out.println("True");
		return null;
	}

	public Void visit(False n) {
		System.out.println("False");
		return null;
	}

	// String s;
	public Void visit(IdentifierExp n) {
		System.out.println("iDEXP");
		return null;
	}

	public Void visit(This n) {
		System.out.println("This");
		return null;
	}

	// Exp e;
	public Void visit(NewArray n) {
		System.out.println("NewArray");
		n.e.accept(this);
		return null;
	}

	// Identifier i;
	public Void visit(NewObject n) {
		System.out.println("NewObject");
		return null;
	}

	// Exp e;
	public Void visit(Not n) {
		System.out.println("!");
		n.e.accept(this);
		return null;
	}

	// String s;
	public Void visit(Identifier n) {
		System.out.println("Id");
		return null;
	}
}
