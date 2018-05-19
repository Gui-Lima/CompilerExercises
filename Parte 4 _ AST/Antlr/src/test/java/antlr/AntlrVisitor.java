// Generated from Antlr.g4 by ANTLR 4.4

    package antlr;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link AntlrParser}.
 *
 * @param <Result> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface AntlrVisitor<Result> extends ParseTreeVisitor<Result> {
	/**
	 * Visit a parse tree produced by {@link AntlrParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Result visitIdentifier(@NotNull AntlrParser.IdentifierContext ctx);

	/**
	 * Visit a parse tree produced by {@link AntlrParser#methodDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Result visitMethodDeclaration(@NotNull AntlrParser.MethodDeclarationContext ctx);

	/**
	 * Visit a parse tree produced by {@link AntlrParser#goal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Result visitGoal(@NotNull AntlrParser.GoalContext ctx);

	/**
	 * Visit a parse tree produced by {@link AntlrParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Result visitExpression(@NotNull AntlrParser.ExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link AntlrParser#mainClass}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Result visitMainClass(@NotNull AntlrParser.MainClassContext ctx);

	/**
	 * Visit a parse tree produced by {@link AntlrParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Result visitStatement(@NotNull AntlrParser.StatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link AntlrParser#integerLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Result visitIntegerLiteral(@NotNull AntlrParser.IntegerLiteralContext ctx);

	/**
	 * Visit a parse tree produced by {@link AntlrParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Result visitType(@NotNull AntlrParser.TypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link AntlrParser#varDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Result visitVarDeclaration(@NotNull AntlrParser.VarDeclarationContext ctx);

	/**
	 * Visit a parse tree produced by {@link AntlrParser#classDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	Result visitClassDeclaration(@NotNull AntlrParser.ClassDeclarationContext ctx);
}