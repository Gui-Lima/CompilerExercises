package br.ufpe.cin.if688.minijava.visitor;


import java.nio.channels.NetworkChannel;

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
import br.ufpe.cin.if688.minijava.ast.Exp;
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
import br.ufpe.cin.if688.minijava.ast.Type;
import br.ufpe.cin.if688.minijava.ast.VarDecl;
import br.ufpe.cin.if688.minijava.ast.While;
import br.ufpe.cin.if688.minijava.symboltable.Class;
import br.ufpe.cin.if688.minijava.symboltable.Method;
import br.ufpe.cin.if688.minijava.symboltable.SymbolTable;
import br.ufpe.cin.if688.minijava.symboltable.Variable;

public class TypeCheckVisitor implements IVisitor<Type> {

	private SymbolTable symbolTable;
	private Class currClass;
	private Method currMethod;
		
	public TypeCheckVisitor(SymbolTable st) {
		
		symbolTable = st;
		
	}

	/*Aqui temos que definir as regras que vamos checar:
	 * 	
	 * 
	 * 
	 * 
	 * 
	 */
	
	public Type visit(Program n) {
		System.out.println("Visitando o programa ");
		n.m.accept(this);
		for (int i = 0; i < n.cl.size(); i++) {
			n.cl.elementAt(i).accept(this);
		}
		
		return null;
	}

	public Type visit(MainClass n) {
		System.out.println("Entrou na main Class ");
		currClass = symbolTable.getClass(n.i1.s);
		currMethod = symbolTable.getMethod("main", currClass.getId());
		
		n.i1.accept(this);
		n.i2.accept(this);
		n.s.accept(this);
		currClass = null;
		currMethod = null;
		return null;
	}

	public Type visit(ClassDeclSimple n) {
		System.out.println("Entrou numa classe ");
		currClass = symbolTable.getClass(n.i.s);
		
		n.i.accept(this);
		for (int i = 0; i < n.vl.size(); i++) {
			n.vl.elementAt(i).accept(this);
		}
		for (int i = 0; i < n.ml.size(); i++) {
			n.ml.elementAt(i).accept(this);
		}
		
		currClass = null;
		return null;
	}

	
	public Type visit(ClassDeclExtends n) {
		System.out.println("Entrou numa classe extendida ");
		currClass = symbolTable.getClass(n.i.s);
		n.i.accept(this);
		n.j.accept(this);
		for (int i = 0; i < n.vl.size(); i++) {
			n.vl.elementAt(i).accept(this);
		}
		for (int i = 0; i < n.ml.size(); i++) {
			n.ml.elementAt(i).accept(this);
		}
		
		currClass = null;
		return null;
	}

	public Type visit(VarDecl n) {
		System.out.println("Variável declarada ");
		n.i.accept(this);
		return n.t.accept(this);
	}

	public Type visit(MethodDecl n) {
		//Temos que checar se o return é do mesmo tipo que declarado
		System.out.println("Método declarado ");
		currMethod = symbolTable.getMethod(n.i.s, currClass.getId());
		
		Type declaredType = n.t.accept(this);
		
		n.i.accept(this);
		for (int i = 0; i < n.fl.size(); i++) {
			n.fl.elementAt(i).accept(this);
		}
		for (int i = 0; i < n.vl.size(); i++) {
			n.vl.elementAt(i).accept(this);
		}
		for (int i = 0; i < n.sl.size(); i++) {
			n.sl.elementAt(i).accept(this);
		}
		Type returnedType = n.e.accept(this);
		
		if(!assertTypesAreEqual(returnedType, declaredType)) {
			System.out.println("Type returned is not type declared");
			System.exit(0);
		}
		currMethod = null;
		return declaredType;
	}

	public Type visit(Formal n) {
		System.out.println("Parametro declarado ");
		n.i.accept(this);
		return n.t.accept(this);
	}

	public Type visit(IntArrayType n) {
		System.out.println("IntandArrayType");
		return n;
	}

	public Type visit(BooleanType n) {
		System.out.println("BooleanType " + n.toString());
		return n;
	}

	public Type visit(IntegerType n) {
		System.out.println("Integer Type");
		return n;
	}

	// String s;
	public Type visit(IdentifierType n) {
		System.out.println("Identifier Type " + n.s);
		return n;
	}

	// StatementList sl;
	public Type visit(Block n) {
		System.out.println("Bloco ");
		for (int i = 0; i < n.sl.size(); i++) {
			n.sl.elementAt(i).accept(this);
		}
		return null;
	}

	// Exp e;
	// Statement s1,s2;
	public Type visit(If n) {
		//Assert inside the if we got a boolean
		System.out.println("If ");
		Type statement1Type = n.s1.accept(this);
		Type statement2Type = n.s2.accept(this);
		Type expressionType = n.e.accept(this);
	
		if(!this.symbolTable.compareTypes(expressionType, new BooleanType())) {
			System.out.println("Não é um boolean dentro do IF");
			System.exit(0);
			return null;
		}
	
		return null;
	}

	// Exp e;
	// Statement s;
	public Type visit(While n) {
		//Assert inside the while we got a boolean
		System.out.println("While ");
		
		Type expressionType = n.e.accept(this);
		
		if(!this.symbolTable.compareTypes(expressionType, new BooleanType())) {
			System.out.println("Não é um boolean dentro do While");
			System.exit(0);
			return null;
		}
		
		n.s.accept(this);
		return null;
	}

	// Exp e;
	public Type visit(Print n) {
		System.out.println("Print ");
		n.e.accept(this);
		return null;
	}

	// Identifier i;
	// Exp e;
	public Type visit(Assign n) {
		//makes sure the variable is assigned to the type defined
		Type typeDeclared = symbolTable.getVarType(currMethod, currClass, n.i.s);
		
		Type typeUsed = n.e.accept(this);
		
		if(!(assertTypesAreEqual(typeDeclared, typeUsed))) {
			System.out.println("Cannot convert these types: " +  typeDeclared + " To " + typeUsed);
			System.exit(0);
		}
		n.i.accept(this);
		return null;
	}

	// Identifier i;
	// Exp e1,e2;
	public Type visit(ArrayAssign n) {
		//assert e2 is a integer
		Type indexAcessor = n.e2.accept(this);
		
		if(!(indexAcessor instanceof IntegerType )) {
			System.out.println("O index não é um inteiro");
			return null;
		}
		
		//assert the array is being used in the same type it as declared
		Type variableDeclaredType = symbolTable.getVarType(currMethod, currClass, n.i.s);
		Type variableBeingUsedType = n.i.accept(this);
		
		if(!(variableDeclaredType.toString().equals(variableBeingUsedType.toString()))) {
			System.out.println("Tipo usado nao bate com declarado");
			return null;
		}
		
		n.e1.accept(this);
		n.e2.accept(this);
		return null;
	}

	// Exp e1,e2;
	public Type visit(And n) {
		System.out.println("and");
		n.e1.accept(this);
		n.e2.accept(this);
		return new BooleanType();
	}

	// Exp e1,e2;
	public Type visit(LessThan n) {
		System.out.println("<");
		n.e1.accept(this);
		n.e2.accept(this);
		return new BooleanType();
	}

	// Exp e1,e2;
	public Type visit(Plus n) {
		System.out.println("+");
		n.e1.accept(this);
		n.e2.accept(this);
		return new IntegerType();
	}

	// Exp e1,e2;
	public Type visit(Minus n) {
		System.out.println("-");
		n.e1.accept(this);
		n.e2.accept(this);
		return new IntegerType();
	}

	// Exp e1,e2;
	public Type visit(Times n) {
		System.out.println("*");
		n.e1.accept(this);
		n.e2.accept(this);
		return new IntegerType();
	}

	// Exp e1,e2;
	public Type visit(ArrayLookup n) {
		System.out.println("Array lookup");
		n.e1.accept(this);
		n.e2.accept(this);
		return new IntegerType();
	}

	// Exp e;
	public Type visit(ArrayLength n) {
		System.out.println("Array length");
		n.e.accept(this);
		return null;
	}

	// Exp e;
	// Identifier i;
	// ExpList el;
	public Type visit(Call n) {
		//this is a complicated matter, we have to return the method being called return type
		Type returnType = null;
		
		Type check = n.e.accept(this);
		System.out.println("Call");
		
		if(n.e instanceof This) {
			returnType = currClass.getMethod(n.i.s).type();
		}
		else if(check instanceof IdentifierType) {
			Class calledClass = this.symbolTable.getClass(((IdentifierType) check).s);
			Method calledMethod = this.symbolTable.getMethod(n.i.toString(), calledClass.getId());
			//this is important for the id accept to work
			Class currentClass = this.currClass;
			this.currClass = calledClass;
			
			
			int i;
			for ( i = 0; i < n.el.size(); i++) {
				Type parametersTypes = n.el.elementAt(i).accept(this);
				Type parametersDeclaredTypes = calledMethod.getParamAt(i).type();
				//check if there are enough parameters
				if(parametersDeclaredTypes == null) {
					System.out.println("Parameters differ in length");
					System.exit(0);
					return null;
				}
				//check if the types match
				if(!(assertTypesAreEqual(parametersTypes, parametersDeclaredTypes))) {
					System.out.println("Parameters types you declared do not match the used ones");
					System.exit(0);
					return null;
				}
			}
			//check if there are parameters left
			if(calledMethod.getParamAt(i) != null) {
				System.out.println("Parameters differ in length");
				System.exit(0);
				return null;
			}
			Type idType = n.i.accept(this);
			currClass = currentClass;
			return idType;
		}

		//the parameters
		
		return returnType;
	}

	// int i;
	public Type visit(IntegerLiteral n) {
		System.out.println("Integer Literal " + n.i);
		return new IntegerType();
	}

	public Type visit(True n) {
		System.out.println("True " + n.toString());
		return new BooleanType();
	}

	public Type visit(False n) {
		System.out.println("False" + n.toString());
		return new BooleanType();
	}

	// String s;
	public Type visit(IdentifierExp n) {
		System.out.println("IdentifierExp " + n.s);
		Type t = symbolTable.getVarType(this.currMethod, this.currClass, n.s);
		return t;
	}

	public Type visit(This n) {
		System.out.println("This ");
		return currClass.type();
	}

	// Exp e;
	public Type visit(NewArray n) {
		System.out.println("NewArray " + n.toString());
		n.e.accept(this);
		return new IntArrayType();
	}

	// Iid.dentifier i;
	public Type visit(NewObject n) {
		System.out.println("New Object " + n.i.s);
		return n.i.accept(this);
	}

	// Exp e;
	public Type visit(Not n) {
		System.out.println("not");
		n.e.accept(this);
		return null;
	}

	// String s;
	public Type visit(Identifier n) {
		System.out.println("Identifier " + n.s);
		if(currClass == null) {
			Class c = symbolTable.getClass(n.s);
			if(c == null) {
				System.err.println("error: cannot find symbol: " + n.toString());
				System.exit(0);
			}
			return c.type();
		}
		if(currClass.containsVar(n.s)) {
			return symbolTable.getVarType(currMethod, currClass, n.s);
		}
		if(currClass.containsMethod(n.s)) {
			return symbolTable.getMethodType(n.s, currClass.getId());
		}
		if(currMethod != null && currMethod.containsVar(n.s)) {
			return currMethod.getVar(n.s).type();
		}
		if(currMethod != null && currMethod.containsParam(n.s)) {
			return currMethod.getParam(n.s).type();
		}
		else {
			Class c = this.symbolTable.getClass(n.toString());
			if(c == null) {
				System.err.println("error: cannot find symbol: " + n.toString());
				System.exit(0);
			}
			return c.type();
		}

	}
	
	public boolean assertTypesAreEqual(Type t1, Type t2) {
		return symbolTable.compareTypes(t1, t2);
	}
}
