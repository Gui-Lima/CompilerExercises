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
import br.ufpe.cin.if688.minijava.ast.Formal;
import br.ufpe.cin.if688.minijava.ast.FormalList;
import br.ufpe.cin.if688.minijava.ast.Identifier;
import br.ufpe.cin.if688.minijava.ast.IdentifierExp;
import br.ufpe.cin.if688.minijava.ast.IdentifierType;
import br.ufpe.cin.if688.minijava.ast.If;
import br.ufpe.cin.if688.minijava.ast.IntegerLiteral;
import br.ufpe.cin.if688.minijava.ast.IntegerType;
import br.ufpe.cin.if688.minijava.ast.LessThan;
import br.ufpe.cin.if688.minijava.ast.MainClass;
import br.ufpe.cin.if688.minijava.ast.MethodDecl;
import br.ufpe.cin.if688.minijava.ast.MethodDeclList;
import br.ufpe.cin.if688.minijava.ast.Print;
import br.ufpe.cin.if688.minijava.ast.Program;
import br.ufpe.cin.if688.minijava.ast.Statement;
import br.ufpe.cin.if688.minijava.ast.StatementList;
import br.ufpe.cin.if688.minijava.ast.True;
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
		
		FormalList fl = new FormalList();
		fl.addElement(new Formal(
				new IntegerType(), 
				new Identifier("x")
		));
		fl.addElement(new Formal(
				new IntegerType(), 
				new Identifier("y")
		));
		
		VarDeclList vdlm1 = new VarDeclList();
		vdlm1.addElement(new VarDecl(
			new BooleanType(),
			new Identifier("run")
		));
		vdlm1.addElement(new VarDecl(
				new IntegerType(),
				new Identifier("count")
		));
		

		MethodDeclList mdl = new MethodDeclList();
		mdl.addElement( new MethodDecl(
				new IntegerType(),
				new Identifier("func"),
				fl,
				vdlm1,
				new StatementList(),
				new IdentifierExp("count")
		));
	
		mdl.addElement( new MethodDecl(
				new IntegerType(),
				new Identifier("func2"),
				new FormalList(),
				new VarDeclList(),
				new StatementList(),
				new IntegerLiteral(0)
		));
		
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
		
		
		/*In the tests file there are some progs builded the way above
		 * so you can copy paste and test different errors. I did not use my own
		 * prog builder couse it's leading to some errors.
		 */
		
		Program prog = new Program(main, cdl);
		
		PrettyPrintVisitor ppv = new PrettyPrintVisitor();
		ppv.visit(prog);
		
		BuildSymbolTableVisitor myAwesomeTable = new BuildSymbolTableVisitor();
		myAwesomeTable.visit(prog);
		
		TypeCheckVisitor typeChecker = new TypeCheckVisitor(myAwesomeTable.getSymbolTable());
		typeChecker.visit(prog);
		
	}
}
