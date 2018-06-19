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
	private errorMessage err;
	
	public TypeCheckVisitor(SymbolTable st) {
		err = new errorMessage();
		symbolTable = st;
		
	}

	/*Aqui temos que definir as regras que vamos checar:
	 * . Métodos retornam oque foi declarado	
	 * . If é um boolean
	 * . While é um boolean
	 * . O valor assignado para uma variável é do mesmo tipo que o declarado
	 * . Ao tentar acessar um index, o número usado é um inteiro
	 * . O valor assignado para um array é do mesmo tipo que o declarado
	 * . Numa chamada de função, os parâmetros devem ser do mesmo tipo e na mesma quantidade que declarados
	 */
	
	/* Outros detalhes de implementação:
	 * 	 Numa call, nós temos que mudar o currClass para a classe onde está presente o método sendo chamado, para que possamos checar as
	 * 	 variáveis usadas no escopo certo
	 * 
	 *	Consequentemente num identifier temos que ver se a variável é de um método, classe, parâmetro ou outra coisa, para dar o Get na
	 *	SymbolTable
	 */
	
	public Type visit(Program n) {
		
		n.m.accept(this);
		for (int i = 0; i < n.cl.size(); i++) {
			n.cl.elementAt(i).accept(this);
		}
		
		return null;
	}

	public Type visit(MainClass n) {
		
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
		
		n.i.accept(this);
		return n.t.accept(this);
	}

	public Type visit(MethodDecl n) {
		//Temos que checar se o return é do mesmo tipo que declarado
		
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
			System.out.println("In method: " + currMethod.getId());
			err.cannotConvert(declaredType, returnedType);
			System.exit(0);
		}
		currMethod = null;
		return declaredType;
	}

	public Type visit(Formal n) {
		n.i.accept(this);
		return n.t.accept(this);
	}

	public Type visit(IntArrayType n) {
		return n;
	}

	public Type visit(BooleanType n) {
		return n;
	}

	public Type visit(IntegerType n) {
		return n;
	}

	// String s;
	public Type visit(IdentifierType n) {
		return n;
	}

	// StatementList sl;
	public Type visit(Block n) {
		for (int i = 0; i < n.sl.size(); i++) {
			n.sl.elementAt(i).accept(this);
		}
		return null;
	}

	// Exp e;
	// Statement s1,s2;
	public Type visit(If n) {
		//Assert inside the if we got a boolean
		Type statement1Type = n.s1.accept(this);
		Type statement2Type = n.s2.accept(this);
		Type expressionType = n.e.accept(this);
	
		if(!this.symbolTable.compareTypes(expressionType, new BooleanType())) {
			err.notABoolean(expressionType);
			System.exit(0);
			return null;
		}
	
		return null;
	}

	// Exp e;
	// Statement s;
	public Type visit(While n) {
		//Assert inside the while we got a boolean		
		Type expressionType = n.e.accept(this);
		
		if(!this.symbolTable.compareTypes(expressionType, new BooleanType())) {
			err.notABoolean(expressionType);
			System.exit(0);
			return null;
		}
		n.s.accept(this);
		return null;
	}

	// Exp e;
	public Type visit(Print n) {
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
			System.out.println("In variable : " + n.i.s);
			err.cannotConvert(typeDeclared, typeUsed);
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
			err.indexAcessorNotInt(indexAcessor);
			return null;
		}
		
		//assert the array is being used in the same type it as declared
		Type variableDeclaredType = symbolTable.getVarType(currMethod, currClass, n.i.s);
		Type variableBeingUsedType = n.i.accept(this);
		
		if(!(assertTypesAreEqual(variableDeclaredType, variableBeingUsedType))) {
			System.out.println("In array : " + n.i.s);
			err.cannotConvert(variableDeclaredType, variableBeingUsedType);
			return null;
		}
		
		n.e1.accept(this);
		n.e2.accept(this);
		return null;
	}

	// Exp e1,e2;
	public Type visit(And n) {
		Type typeLeft = n.e1.accept(this);
		Type typeRight = n.e2.accept(this);
		Type bool = new BooleanType();
		String op = "AND";
		if(!assertTypesAreEqual(typeLeft, bool) && assertTypesAreEqual(typeRight, bool)) {
			err.badOperands(typeLeft, typeRight, op);
		}
		return new BooleanType();
	}

	// Exp e1,e2;
	public Type visit(LessThan n) {
		Type typeLeft = n.e1.accept(this);
		Type typeRight = n.e2.accept(this);
		Type integer = new IntegerType();
		String op = "Less than ( < )";
		if(!assertTypesAreEqual(typeLeft, integer) && assertTypesAreEqual(typeRight, integer)) {
			err.badOperands(typeLeft, typeRight, op);
		}
		return new BooleanType();
	}

	// Exp e1,e2;
	public Type visit(Plus n) {
		Type typeLeft = n.e1.accept(this);
		Type typeRight = n.e2.accept(this);
		Type integer = new IntegerType();
		String op = "Plus";
		if(!assertTypesAreEqual(typeLeft, integer) && assertTypesAreEqual(typeRight, integer)) {
			err.badOperands(typeLeft, typeRight, op);
		}
		return new IntegerType();
	}

	// Exp e1,e2;
	public Type visit(Minus n) {
		Type typeLeft = n.e1.accept(this);
		Type typeRight = n.e2.accept(this);
		Type integer = new IntegerType();
		String op = "Minus";
		if(!assertTypesAreEqual(typeLeft, integer) && assertTypesAreEqual(typeRight, integer)) {
			err.badOperands(typeLeft, typeRight, op);
		}
		return new IntegerType();
	}

	// Exp e1,e2;
	public Type visit(Times n) {
		Type typeLeft = n.e1.accept(this);
		Type typeRight = n.e2.accept(this);
		Type integer = new IntegerType();
		String op = "Times";
		if(!assertTypesAreEqual(typeLeft, integer) && assertTypesAreEqual(typeRight, integer)) {
			err.badOperands(typeLeft, typeRight, op);
		}
		return new IntegerType();
	}

	// Exp e1,e2;
	public Type visit(ArrayLookup n) {
		Type type1 = n.e1.accept(this);
		Type type2 = n.e2.accept(this);
		if(!(assertTypesAreEqual(type1, new IntArrayType()))) {
			err.cannotConvert(type1, new IntArrayType());
			System.exit(0);
		}
		if(!(assertTypesAreEqual(type2, new IntegerType()))) {
			err.cannotConvert(type2, new IntegerType());
			System.exit(0);
		}
		return new IntegerType();
	}

	// Exp e;
	public Type visit(ArrayLength n) {
		Type type = n.e.accept(this);
		if(!(assertTypesAreEqual(type, new IntArrayType()))) {
			err.cannotConvert(type, new IntArrayType());
			System.exit(0);
		}
		return new IntegerType();
	}

	// Exp e;
	// Identifier i;
	// ExpList el;
	public Type visit(Call n) {
		//this is a complicated matter, we have to return the method being called return type
		Type returnType = null;
		
		Type check = n.e.accept(this);
		//if it is a method from itself
		if(n.e instanceof This) {
			returnType = currClass.getMethod(n.i.s).type();
		}
		else if(check instanceof IdentifierType) {
			Class calledClass = this.symbolTable.getClass(((IdentifierType) check).s);
			Method calledMethod = this.symbolTable.getMethod(n.i.toString(), calledClass.getId());
			//this is important for the identifier accept to work
			Class currentClass = this.currClass;
			this.currClass = calledClass;
			
			
			int i;
			for ( i = 0; i < n.el.size(); i++) {
				Type parametersTypes = n.el.elementAt(i).accept(this);
			
				Variable parametersDeclared = calledMethod.getParamAt(i);
				//check if there are enough parameters
				if(parametersDeclared == null) {
					err.parametersDifferInLength(calledMethod);
					System.exit(0);
					return null;
				}
				Type parametersDeclaredTypes = calledMethod.getParamAt(i).type();
				//check if the types match
				if(!(assertTypesAreEqual(parametersTypes, parametersDeclaredTypes))) {
					err.cannotConvert(parametersDeclaredTypes, parametersTypes);
					System.exit(0);
					return null;
				}
			}
			//check if there are parameters left
			if(calledMethod.getParamAt(i) != null) {
				err.parametersDifferInLength(calledMethod);
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
		return new IntegerType();
	}

	public Type visit(True n) {
		return new BooleanType();
	}

	public Type visit(False n) {
		return new BooleanType();
	}

	// String s;
	public Type visit(IdentifierExp n) {
		Type t = symbolTable.getVarType(this.currMethod, this.currClass, n.s);
		return t;
	}

	public Type visit(This n) {
		return currClass.type();
	}

	// Exp e;
	public Type visit(NewArray n) {
		Type indexType = n.e.accept(this);
		
		if(!(assertTypesAreEqual(indexType, new IntegerType()))) {
			err.cannotConvert(indexType, new IntegerType());
			System.exit(0);
		}
		return new IntArrayType();
	}

	// Iid.dentifier i;
	public Type visit(NewObject n) {
		return n.i.accept(this);
	}

	// Exp e;
	public Type visit(Not n) {
		n.e.accept(this);
		return null;
	}

	// String s;
	public Type visit(Identifier n) {

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
			Class c = this.symbolTable.getClass(n.s);
			if(c == null) {
				err.cannotFindVariable(n);
				System.exit(0);
			}
			return c.type();
		}

	}
	
	public boolean assertTypesAreEqual(Type t1, Type t2) {
		return symbolTable.compareTypes(t1, t2);
	}
}
