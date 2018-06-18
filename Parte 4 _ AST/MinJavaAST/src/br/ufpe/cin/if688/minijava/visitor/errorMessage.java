package br.ufpe.cin.if688.minijava.visitor;

import br.ufpe.cin.if688.minijava.ast.BooleanType;
import br.ufpe.cin.if688.minijava.ast.Identifier;
import br.ufpe.cin.if688.minijava.ast.IdentifierType;
import br.ufpe.cin.if688.minijava.ast.IntArrayType;
import br.ufpe.cin.if688.minijava.ast.IntegerType;
import br.ufpe.cin.if688.minijava.ast.Type;
import br.ufpe.cin.if688.minijava.symboltable.Method;

public class errorMessage {

	public errorMessage() {
		
	}
	
	public void cannotConvert(Type t1, Type t2) {
		String type1ToString = getNameFromType(t1);
		String type2ToString = getNameFromType(t2);
		System.err.println("Cannot convert from type : " + type1ToString + " to " + type2ToString);
	}
	
	public void parametersDifferInLength(Method method) {
		System.err.println("Parameters differ in length, in function call : " + method.getId());
	}
	
	public void indexAcessorNotInt(Type t1) {
		String typeToString = getNameFromType(t1);
		System.err.println("Index is not an Integer, instead it is an " + typeToString);
	}
	
	public void notABoolean(Type t1) {
		String typeToString = getNameFromType(t1);
		System.err.println("Conditional bot a boolean, instead it is a : " + typeToString);
	}
	
	public void cannotFindVariable(Identifier i) {
		System.err.println("Cannot find this variable : " + i.s + " in any method or class");
	}
	
	public void badOperands(Type t1, Type t2, String op) {
		System.err.println("Bad types for operand " + op + " : " + getNameFromType(t1) + " " +
	getNameFromType(t2));
	}
	
	public String getNameFromType(Type t1) {
		if(t1 instanceof IntegerType) {
			return "Integer";
		}
		if(t1 instanceof IdentifierType) {
			return "Id";
		}
		if(t1 instanceof IntArrayType) {
			return "Array";
		}
		if(t1 instanceof BooleanType) {
			return "Boolean";
		}
		else {
			return "No type match";
		}
	}

}
