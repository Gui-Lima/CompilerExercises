/**
 * 
 */
package antlr;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.junit.runner.Result;

import antlr.AntlrParser.ClassDeclarationContext;
import antlr.AntlrParser.ExpressionContext;
import antlr.AntlrParser.GoalContext;
import antlr.AntlrParser.MainClassContext;
import antlr.AntlrParser.MethodDeclarationContext;
import antlr.AntlrParser.StatementContext;
import antlr.AntlrParser.TypeContext;
import antlr.AntlrParser.VarDeclarationContext;

/**
 * @author guila
 *
 */
public class visitoragoravai implements AntlrVisitor<Result> {

	/**
	 * 
	 */
	public visitoragoravai() {
		// TODO Auto-generated constructor stub
	}

	public Result visit(ParseTree arg0) {
		// TODO Auto-generated method stub
		return null;
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

	public Result visitMethodDeclaration(MethodDeclarationContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	public Result visitGoal(GoalContext ctx) {
		
		return ctx.accept(this); 
	}

	public Result visitExpression(ExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	public Result visitMainClass(MainClassContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	public Result visitStatement(StatementContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	public Result visitType(TypeContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	public Result visitVarDeclaration(VarDeclarationContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	public Result visitClassDeclaration(ClassDeclarationContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

}
