package br.ufpe.cin.if688.minijava.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import antlr.AntlrLexer;
import antlr.AntlrParser;
import antlr.visitoragoravai;
import br.ufpe.cin.if688.minijava.ast.BooleanType;
import br.ufpe.cin.if688.minijava.ast.ClassDeclExtends;
import br.ufpe.cin.if688.minijava.ast.ClassDeclList;
import br.ufpe.cin.if688.minijava.ast.ClassDeclSimple;
import br.ufpe.cin.if688.minijava.ast.Identifier;
import br.ufpe.cin.if688.minijava.ast.IdentifierType;
import br.ufpe.cin.if688.minijava.ast.IntegerLiteral;
import br.ufpe.cin.if688.minijava.ast.IntegerType;
import br.ufpe.cin.if688.minijava.ast.MainClass;
import br.ufpe.cin.if688.minijava.ast.MethodDeclList;
import br.ufpe.cin.if688.minijava.ast.Print;
import br.ufpe.cin.if688.minijava.ast.Program;
import br.ufpe.cin.if688.minijava.ast.VarDecl;
import br.ufpe.cin.if688.minijava.ast.VarDeclList;
import br.ufpe.cin.if688.minijava.visitor.BuildSymbolTableVisitor;
import br.ufpe.cin.if688.minijava.visitor.PrettyPrintVisitor;
import br.ufpe.cin.if688.minijava.visitor.TypeCheckVisitor;

public class Main {

	public static void main(String[] args) throws IOException {
		MainClass main = new MainClass(
				new Identifier("Teste"), 
				new Identifier("Testando"), 
				new Print(new IntegerLiteral(2))
		);
		
		VarDeclList vdl1 = new VarDeclList();
		vdl1.addElement(new VarDecl(
			new BooleanType(),
			new Identifier("flag")
		));
		vdl1.addElement(new VarDecl(
				new IntegerType(),
				new Identifier("num")
		));
		
		MethodDeclList mdl = new MethodDeclList();
		
		ClassDeclSimple A = new ClassDeclSimple(
					new Identifier("A"), vdl1, mdl
		);
		
		ClassDeclExtends B = new ClassDeclExtends(
				new Identifier("B"), new Identifier("A"), 
				new VarDeclList(), new MethodDeclList()
		);
		
		VarDeclList vdl2 = new VarDeclList();
		vdl2.addElement(new VarDecl(
				new IdentifierType("A"),
				new Identifier("obj")
		));
		ClassDeclSimple C = new ClassDeclSimple(
				new Identifier("C"), vdl2, new MethodDeclList()
		);
		
		ClassDeclList cdl = new ClassDeclList();
		cdl.addElement(A);
		cdl.addElement(B);
		cdl.addElement(C);
		
		
		Map<Integer, String> tests = makeTests();		
		for(int i = 0;i<7;i++) {
			test(i, tests);
		}
			
	}
	
	public static void test(int i, Map<Integer, String> tests ) {
		String file = tests.get(i);
		InputStream stream = new FileInputStream(file); 
		
		ANTLRInputStream input = new ANTLRInputStream(stream);
		AntlrLexer lexer = new antlr.AntlrLexer(input);
		CommonTokenStream token = new CommonTokenStream(lexer);
		//atv 5 setup
		BuildSymbolTableVisitor stVis = new BuildSymbolTableVisitor();
		
		
		Program prog = (Program) new visitoragoravai().visit(new AntlrParser(token).goal());
		prog.accept(stVis); 
		prog.accept(new TypeCheckVisitor(stVis.getSymbolTable())); 
	}
	
	public Map<Integer, String> makeTests() {
		Map<Integer, String> testFilesToNumber = new HashMap<Integer, String>();
		testFilesToNumber.put(0, "src/test/resources/BubbleSort.java");
		testFilesToNumber.put(1, "src/test/resources/BinarySearch.java");
		testFilesToNumber.put(2, "src/test/resources/LinearSearch.java");
		testFilesToNumber.put(3, "src/test/resources/testingVaribleDeclarationMissMatch.java");
		testFilesToNumber.put(4, "src/test/resources/testingNonBooleanConditional.java");
		testFilesToNumber.put(5, "src/test/resources/quicksort.java");
		testFilesToNumber.put(6, "src/test/resources/testingUndeclaredVariable.java");
		return testFilesToNumber;
	}
}
