/**
 * 
 */
package antlr;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.junit.runner.Result;
import java.util.Iterator;

import antlr.AntlrParser.ClassDeclarationContext;
import antlr.AntlrParser.ExpressionContext;
import antlr.AntlrParser.GoalContext;
import antlr.AntlrParser.IdentifierContext;
import antlr.AntlrParser.IntegerLiteralContext;
import antlr.AntlrParser.MainClassContext;
import antlr.AntlrParser.MethodDeclarationContext;
import antlr.AntlrParser.StatementContext;
import antlr.AntlrParser.TypeContext;
import antlr.AntlrParser.VarDeclarationContext;
import br.ufpe.cin.if688.minijava.ast.*;


//eu não aguento mais

public class visitoragoravai implements AntlrVisitor<Object> {

	/**
	 * 
	 */
	public visitoragoravai() {
		// TODO Auto-generated constructor stub
	}

	public Object visit(ParseTree pt) {
		
		return pt.accept(this);
	}

	public Result visitChildren(RuleNode arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Result visitErrorNode(ErrorNode arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Result visitTerminal(TerminalNode arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visitMethodDeclaration(MethodDeclarationContext ctx) {
		
		Type typ = (Type) ctx.type(0).accept(this);
		Identifier id = (Identifier) ctx.identifier(0).accept(this);
		
		FormalList fl = new FormalList();
		Iterator<TypeContext> itt = (Iterator<TypeContext>) ctx.type().iterator();
		Iterator<IdentifierContext> itd = (Iterator<IdentifierContext>) ctx.identifier().iterator();
		itt.next();
		itd.next();
		while(itd.hasNext() && itt.hasNext()) {
			Formal frm = new Formal((Type) itt.next().accept(this), (Identifier) itd.next().accept(this));
			fl.addElement(frm);
		}

		VarDeclList varlist = new VarDeclList();
		Iterator<VarDeclarationContext> iVarDec = (Iterator<VarDeclarationContext>) ctx.varDeclaration().iterator();
		while(iVarDec.hasNext()) {
			varlist.addElement((VarDecl) iVarDec.next().accept(this));
		}
		
		StatementList stmList = new StatementList();
		Iterator<StatementContext> itStm = (Iterator<StatementContext>) ctx.statement().iterator();
		while(itStm.hasNext()) {
			stmList.addElement((Statement) itStm.next().accept(this));
		}

		Exp expo = (Exp) ctx.expression().accept(this);
		return new MethodDecl(typ, id, fl, varlist, stmList, expo);
	}

	public Object visitGoal(GoalContext ctx) {
		MainClass mainC = (MainClass) ctx.mainClass().accept(this);
		
		ClassDeclList cdl = new ClassDeclList();
		Iterator<ClassDeclarationContext> it =  (Iterator<ClassDeclarationContext>) ctx.classDeclaration().iterator();
		while(it.hasNext()) {
			cdl.addElement((ClassDecl) it.next().accept(this));
		}
		return new Program(mainC, cdl);
	}

	public Object visitExpression(ExpressionContext ctx) {
		int nExps = ctx.expression().size();
		int nChield = ctx.getChildCount();
		
		if(nChield >= 5) {
			String operation = ctx.getChild(3).getText();
			if (operation.equals("(")) {
				Exp e = (Exp) ctx.expression(0).accept(this);
				Identifier i = (Identifier) ctx.identifier().accept(this);

				ExpList el = new ExpList();
				Iterator<ExpressionContext> ite = (Iterator<ExpressionContext>) ctx.expression().iterator();
				ite.next();
				while(ite.hasNext()) {
					el.addElement((Exp) ite.next().accept(this));
				}

				return new Call(e, i, el);
			}
		}

		if (nExps == 2) {
			Exp e1 = (Exp) ctx.expression(0).accept(this);
			Exp e2 = (Exp) ctx.expression(1).accept(this);
			
			if (nChield == 3) {
				switch(ctx.getChild(1).getText()) {
					case "&&" : return new And(e1, e2);
					case "+"  : return new Plus(e1, e2);
					case "-"  : return new Minus(e1, e2);
					case "<"  : return new LessThan(e1, e2);
					default  : return new Times(e1, e2);
				}
			} else return new ArrayLookup(e1, e2);
		} else if (nExps == 1) {
			Exp e = (Exp) ctx.expression(0).accept(this);
			switch(ctx.getChild(1).getText()) {
				case "!"   : return new Not(e);
				case "."   : return new ArrayLength(e);
				case "("   : return (Exp) ctx.expression(0).accept(this);
				default    : return new NewArray(e);
			}
		} else {
			String s = ctx.getStart().getText();
			switch(s) {
				case "false" : return new False();
				case "this"  : return new This();
				case "true"  : return new True();
				case "new"   : return new NewObject((Identifier) ctx.identifier().accept(this));
				default      :
					if(s.matches("\\d+")) {
						return (IntegerLiteral) ctx.integerLiteral().accept(this);
					} else {
						return (Identifier) ctx.identifier().accept(this);
					}
			}
}
	}

	public Object visitMainClass(MainClassContext ctx) {
		Identifier i1 = (Identifier) ctx.identifier(0).accept(this);

		Identifier i2 = (Identifier) ctx.identifier(1).accept(this);

		Statement s = (Statement) ctx.statement().accept(this);
		return new MainClass(i1, i2, s);
	}

	public Object visitStatement(StatementContext ctx) {
		switch(ctx.getStart().getText()) {
		case "{" : 
			StatementList stmList = new StatementList();
			Iterator<StatementContext> itSc = (Iterator<StatementContext>) ctx.statement().iterator();
			while(itSc.hasNext()) {
				stmList.addElement((Statement) itSc.next().accept(this));
			}
			return new Block(stmList);
		case "if" :
			Exp e = (Exp) ctx.expression(0).accept(this);
			Statement s1 = (Statement) ctx.statement(0).accept(this);
			Statement s2 = (Statement) ctx.statement(1).accept(this);
			return new If(e, s1, s2);
		case "while" :
			Exp e1 = (Exp) ctx.expression(0).accept(this);
			Statement s3 = (Statement) ctx.statement(0).accept(this);
			return new While(e1, s3);
		case "System.out.println" :
			return new Print((Exp) ctx.expression(0).accept(this));
		default: 
			if(ctx.expression().size() == 1) {
				Identifier i = (Identifier) ctx.identifier().accept(this);
				Exp e2 = (Exp) ctx.expression(0).accept(this);
				return new Assign(i, e2);
			} else {
				Identifier i = (Identifier) ctx.identifier().accept(this);
				Exp e3 = (Exp) ctx.expression(0).accept(this);
				Exp e4 = (Exp) ctx.expression(1).accept(this);
				return new ArrayAssign(i, e3, e4);
			}
}
	}

	public Object visitType(TypeContext ctx) {
		String str = ctx.getText();
		switch(str) {
			case "boolean" : return new BooleanType();
			case "int[]"   : return new IntArrayType();
			case "int"     : return new IntegerType();
			default        : return new IdentifierType(str);
		}
	}

	public Object visitVarDeclaration(VarDeclarationContext ctx) {
		Type t = (Type) ctx.type().accept(this);
		Identifier i = (Identifier) ctx.identifier().accept(this);
		return new VarDecl(t, i);
	}

	public Object visitClassDeclaration(ClassDeclarationContext ctx) {
		Identifier i = (Identifier) ctx.identifier(0).accept(this);
		
		VarDeclList vdl = new VarDeclList();
		Iterator<VarDeclarationContext> itVdc = (Iterator<VarDeclarationContext>) ctx.varDeclaration().iterator();
		while(itVdc.hasNext()) {
			vdl.addElement((VarDecl) itVdc.next().accept(this));
		}

		MethodDeclList mdl = new MethodDeclList();
		Iterator<MethodDeclarationContext> itMdc = (Iterator<MethodDeclarationContext>) ctx.methodDeclaration().iterator();
		while(itMdc.hasNext()) {
			mdl.addElement((MethodDecl) itMdc.next().accept(this));
		}
		
		if(ctx.identifier().size() > 1) {
			Identifier i2 = (Identifier) ctx.identifier(1).accept(this);
			return new ClassDeclExtends(i, i2, vdl, mdl);
		} else return new ClassDeclSimple(i, vdl, mdl);
	}

	@Override
	public Object visitIdentifier(IdentifierContext ctx) {
		return new Identifier(ctx.getText());
	}

	@Override
	public Object visitIntegerLiteral(IntegerLiteralContext ctx) {
		return new IntegerLiteral(Integer.parseInt(ctx.getText()));
	}
}
