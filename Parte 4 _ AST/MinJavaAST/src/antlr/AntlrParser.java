// Generated from Antlr.g4 by ANTLR 4.4

    package antlr;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

public class AntlrParser extends Parser {
	public static final int
		T__33=1, T__32=2, T__31=3, T__30=4, T__29=5, T__28=6, T__27=7, T__26=8, 
		T__25=9, T__24=10, T__23=11, T__22=12, T__21=13, T__20=14, T__19=15, T__18=16, 
		T__17=17, T__16=18, T__15=19, T__14=20, T__13=21, T__12=22, T__11=23, 
		T__10=24, T__9=25, T__8=26, T__7=27, T__6=28, T__5=29, T__4=30, T__3=31, 
		T__2=32, T__1=33, T__0=34, COMMENT=35, LINE_COMMENT=36, IDENTIFIER=37, 
		INTEGER_LITERAL=38, WS=39;
	public static final String[] tokenNames = {
		"<INVALID>", "'main'", "'length'", "'new'", "'true'", "'return'", "'class'", 
		"'while'", "';'", "'{'", "'&&'", "'='", "'extends'", "'}'", "'if'", "'int'", 
		"'('", "'*'", "'this'", "','", "'false'", "'.'", "'boolean'", "'System.out.println'", 
		"'['", "'<'", "'String'", "']'", "'public'", "'Void'", "'static'", "'else'", 
		"')'", "'+'", "'-'", "COMMENT", "LINE_COMMENT", "IDENTIFIER", "INTEGER_LITERAL", 
		"WS"
	};
	public static final int
		RULE_goal = 0, RULE_mainClass = 1, RULE_classDeclaration = 2, RULE_varDeclaration = 3, 
		RULE_methodDeclaration = 4, RULE_type = 5, RULE_statement = 6, RULE_expression = 7, 
		RULE_identifier = 8, RULE_integerLiteral = 9;
	public static final String[] ruleNames = {
		"goal", "mainClass", "classDeclaration", "varDeclaration", "methodDeclaration", 
		"type", "statement", "expression", "identifier", "integerLiteral"
	};

	@Override
	public String getGrammarFileName() { return "Antlr.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	public AntlrParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN);
	}
	public static class GoalContext extends ParserRuleContext {
		public List<? extends ClassDeclarationContext> classDeclaration() {
			return getRuleContexts(ClassDeclarationContext.class);
		}
		public TerminalNode EOF() { return getToken(AntlrParser.EOF, 0); }
		public ClassDeclarationContext classDeclaration(int i) {
			return getRuleContext(ClassDeclarationContext.class,i);
		}
		public MainClassContext mainClass() {
			return getRuleContext(MainClassContext.class,0);
		}
		public GoalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_goal; }
		@Override
		public <Result> Result accept(ParseTreeVisitor<? extends Result> visitor) {
			if ( visitor instanceof AntlrVisitor<?> ) return ((AntlrVisitor<? extends Result>)visitor).visitGoal(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final GoalContext goal() throws RecognitionException {
		GoalContext _localctx = new GoalContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_goal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(20); mainClass();
			setState(24);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__28) {
				{
				{
				setState(21); classDeclaration();
				}
				}
				setState(26);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(27); match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MainClassContext extends ParserRuleContext {
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public List<? extends IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public MainClassContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mainClass; }
		@Override
		public <Result> Result accept(ParseTreeVisitor<? extends Result> visitor) {
			if ( visitor instanceof AntlrVisitor<?> ) return ((AntlrVisitor<? extends Result>)visitor).visitMainClass(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final MainClassContext mainClass() throws RecognitionException {
		MainClassContext _localctx = new MainClassContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_mainClass);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29); match(T__28);
			setState(30); identifier();
			setState(31); match(T__25);
			setState(32); match(T__6);
			setState(33); match(T__4);
			setState(34); match(T__5);
			setState(35); match(T__33);
			setState(36); match(T__18);
			setState(37); match(T__8);
			setState(38); match(T__10);
			setState(39); match(T__7);
			setState(40); identifier();
			setState(41); match(T__2);
			setState(42); match(T__25);
			setState(43); statement();
			setState(44); match(T__21);
			setState(45); match(T__21);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassDeclarationContext extends ParserRuleContext {
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public List<? extends MethodDeclarationContext> methodDeclaration() {
			return getRuleContexts(MethodDeclarationContext.class);
		}
		public List<? extends VarDeclarationContext> varDeclaration() {
			return getRuleContexts(VarDeclarationContext.class);
		}
		public List<? extends IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public MethodDeclarationContext methodDeclaration(int i) {
			return getRuleContext(MethodDeclarationContext.class,i);
		}
		public VarDeclarationContext varDeclaration(int i) {
			return getRuleContext(VarDeclarationContext.class,i);
		}
		public ClassDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDeclaration; }
		@Override
		public <Result> Result accept(ParseTreeVisitor<? extends Result> visitor) {
			if ( visitor instanceof AntlrVisitor<?> ) return ((AntlrVisitor<? extends Result>)visitor).visitClassDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final ClassDeclarationContext classDeclaration() throws RecognitionException {
		ClassDeclarationContext _localctx = new ClassDeclarationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_classDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47); match(T__28);
			setState(48); identifier();
			setState(51);
			_la = _input.LA(1);
			if (_la==T__22) {
				{
				setState(49); match(T__22);
				setState(50); identifier();
				}
			}

			setState(53); match(T__25);
			setState(57);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__19) | (1L << T__12) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(54); varDeclaration();
				}
				}
				setState(59);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__6) {
				{
				{
				setState(60); methodDeclaration();
				}
				}
				setState(65);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(66); match(T__21);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDeclarationContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public VarDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDeclaration; }
		@Override
		public <Result> Result accept(ParseTreeVisitor<? extends Result> visitor) {
			if ( visitor instanceof AntlrVisitor<?> ) return ((AntlrVisitor<? extends Result>)visitor).visitVarDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final VarDeclarationContext varDeclaration() throws RecognitionException {
		VarDeclarationContext _localctx = new VarDeclarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_varDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68); type();
			setState(69); identifier();
			setState(70); match(T__26);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodDeclarationContext extends ParserRuleContext {
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public List<? extends VarDeclarationContext> varDeclaration() {
			return getRuleContexts(VarDeclarationContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<? extends TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<? extends IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public List<? extends StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VarDeclarationContext varDeclaration(int i) {
			return getRuleContext(VarDeclarationContext.class,i);
		}
		public MethodDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodDeclaration; }
		@Override
		public <Result> Result accept(ParseTreeVisitor<? extends Result> visitor) {
			if ( visitor instanceof AntlrVisitor<?> ) return ((AntlrVisitor<? extends Result>)visitor).visitMethodDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final MethodDeclarationContext methodDeclaration() throws RecognitionException {
		MethodDeclarationContext _localctx = new MethodDeclarationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_methodDeclaration);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(72); match(T__6);
			setState(73); type();
			setState(74); identifier();
			setState(75); match(T__18);
			setState(87);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__19) | (1L << T__12) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(76); type();
				setState(77); identifier();
				setState(84);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__15) {
					{
					{
					setState(78); match(T__15);
					setState(79); type();
					setState(80); identifier();
					}
					}
					setState(86);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(89); match(T__2);
			setState(90); match(T__25);
			setState(94);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(91); varDeclaration();
					}
					} 
				}
				setState(96);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			setState(100);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__27) | (1L << T__25) | (1L << T__20) | (1L << T__11) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(97); statement();
				}
				}
				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(103); match(T__29);
			setState(104); expression(0);
			setState(105); match(T__26);
			setState(106); match(T__21);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public <Result> Result accept(ParseTreeVisitor<? extends Result> visitor) {
			if ( visitor instanceof AntlrVisitor<?> ) return ((AntlrVisitor<? extends Result>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_type);
		try {
			setState(114);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(108); match(T__19);
				setState(109); match(T__10);
				setState(110); match(T__7);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(111); match(T__12);
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(112); match(T__19);
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(113); identifier();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<? extends StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public List<? extends ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public <Result> Result accept(ParseTreeVisitor<? extends Result> visitor) {
			if ( visitor instanceof AntlrVisitor<?> ) return ((AntlrVisitor<? extends Result>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_statement);
		int _la;
		try {
			setState(157);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(116); match(T__25);
				setState(120);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__27) | (1L << T__25) | (1L << T__20) | (1L << T__11) | (1L << IDENTIFIER))) != 0)) {
					{
					{
					setState(117); statement();
					}
					}
					setState(122);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(123); match(T__21);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(124); match(T__20);
				setState(125); match(T__18);
				setState(126); expression(0);
				setState(127); match(T__2);
				setState(128); statement();
				setState(129); match(T__3);
				setState(130); statement();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(132); match(T__27);
				setState(133); match(T__18);
				setState(134); expression(0);
				setState(135); match(T__2);
				setState(136); statement();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(138); match(T__11);
				setState(139); match(T__18);
				setState(140); expression(0);
				setState(141); match(T__2);
				setState(142); match(T__26);
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(144); identifier();
				setState(145); match(T__23);
				setState(146); expression(0);
				setState(147); match(T__26);
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(149); identifier();
				setState(150); match(T__10);
				setState(151); expression(0);
				setState(152); match(T__7);
				setState(153); match(T__23);
				setState(154); expression(0);
				setState(155); match(T__26);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public IntegerLiteralContext integerLiteral() {
			return getRuleContext(IntegerLiteralContext.class,0);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public List<? extends ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public <Result> Result accept(ParseTreeVisitor<? extends Result> visitor) {
			if ( visitor instanceof AntlrVisitor<?> ) return ((AntlrVisitor<? extends Result>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(160); integerLiteral();
				}
				break;

			case 2:
				{
				setState(161); match(T__30);
				}
				break;

			case 3:
				{
				setState(162); match(T__14);
				}
				break;

			case 4:
				{
				setState(163); identifier();
				}
				break;

			case 5:
				{
				setState(164); match(T__16);
				}
				break;

			case 6:
				{
				setState(165); match(T__31);
				setState(166); match(T__19);
				setState(167); match(T__10);
				setState(168); expression(0);
				setState(169); match(T__7);
				}
				break;

			case 7:
				{
				setState(171); match(T__31);
				setState(172); identifier();
				setState(173); match(T__18);
				setState(174); match(T__2);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(207);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(205);
					switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(178);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(179);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__24) | (1L << T__17) | (1L << T__9) | (1L << T__1) | (1L << T__0))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(180); expression(12);
						}
						break;

					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(181);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(182); match(T__10);
						setState(183); expression(0);
						setState(184); match(T__7);
						}
						break;

					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(186);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(187); match(T__13);
						setState(188); match(T__32);
						}
						break;

					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(189);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(190); match(T__13);
						setState(191); identifier();
						setState(192); match(T__18);
						setState(201);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__31) | (1L << T__30) | (1L << T__16) | (1L << T__14) | (1L << IDENTIFIER) | (1L << INTEGER_LITERAL))) != 0)) {
							{
							setState(193); expression(0);
							setState(198);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while (_la==T__15) {
								{
								{
								setState(194); match(T__15);
								setState(195); expression(0);
								}
								}
								setState(200);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
							}
						}

						setState(203); match(T__2);
						}
						break;
					}
					} 
				}
				setState(209);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(AntlrParser.IDENTIFIER, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public <Result> Result accept(ParseTreeVisitor<? extends Result> visitor) {
			if ( visitor instanceof AntlrVisitor<?> ) return ((AntlrVisitor<? extends Result>)visitor).visitIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntegerLiteralContext extends ParserRuleContext {
		public TerminalNode INTEGER_LITERAL() { return getToken(AntlrParser.INTEGER_LITERAL, 0); }
		public IntegerLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integerLiteral; }
		@Override
		public <Result> Result accept(ParseTreeVisitor<? extends Result> visitor) {
			if ( visitor instanceof AntlrVisitor<?> ) return ((AntlrVisitor<? extends Result>)visitor).visitIntegerLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final IntegerLiteralContext integerLiteral() throws RecognitionException {
		IntegerLiteralContext _localctx = new IntegerLiteralContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_integerLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212); match(INTEGER_LITERAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 7: return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return precpred(_ctx, 11);

		case 1: return precpred(_ctx, 10);

		case 2: return precpred(_ctx, 9);

		case 3: return precpred(_ctx, 8);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\uaf6f\u8320\u479d\ub75c\u4880\u1605\u191c\uab37\3)\u00d9\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\3\2\3\2\7\2\31\n\2\f\2\16\2\34\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\5\4"+
		"\66\n\4\3\4\3\4\7\4:\n\4\f\4\16\4=\13\4\3\4\7\4@\n\4\f\4\16\4C\13\4\3"+
		"\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6U\n"+
		"\6\f\6\16\6X\13\6\5\6Z\n\6\3\6\3\6\3\6\7\6_\n\6\f\6\16\6b\13\6\3\6\7\6"+
		"e\n\6\f\6\16\6h\13\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\5\7u"+
		"\n\7\3\b\3\b\7\by\n\b\f\b\16\b|\13\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u00a0\n\b\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00b3\n\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u00c7"+
		"\n\t\f\t\16\t\u00ca\13\t\5\t\u00cc\n\t\3\t\3\t\7\t\u00d0\n\t\f\t\16\t"+
		"\u00d3\13\t\3\n\3\n\3\13\3\13\3\13\2\2\3\20\f\2\2\4\2\6\2\b\2\n\2\f\2"+
		"\16\2\20\2\22\2\24\2\2\3\6\2\f\f\23\23\33\33#$\u00eb\2\26\3\2\2\2\4\37"+
		"\3\2\2\2\6\61\3\2\2\2\bF\3\2\2\2\nJ\3\2\2\2\ft\3\2\2\2\16\u009f\3\2\2"+
		"\2\20\u00b2\3\2\2\2\22\u00d4\3\2\2\2\24\u00d6\3\2\2\2\26\32\5\4\3\2\27"+
		"\31\5\6\4\2\30\27\3\2\2\2\31\34\3\2\2\2\32\30\3\2\2\2\32\33\3\2\2\2\33"+
		"\35\3\2\2\2\34\32\3\2\2\2\35\36\7\2\2\3\36\3\3\2\2\2\37 \7\b\2\2 !\5\22"+
		"\n\2!\"\7\13\2\2\"#\7\36\2\2#$\7 \2\2$%\7\37\2\2%&\7\3\2\2&\'\7\22\2\2"+
		"\'(\7\34\2\2()\7\32\2\2)*\7\35\2\2*+\5\22\n\2+,\7\"\2\2,-\7\13\2\2-.\5"+
		"\16\b\2./\7\17\2\2/\60\7\17\2\2\60\5\3\2\2\2\61\62\7\b\2\2\62\65\5\22"+
		"\n\2\63\64\7\16\2\2\64\66\5\22\n\2\65\63\3\2\2\2\65\66\3\2\2\2\66\67\3"+
		"\2\2\2\67;\7\13\2\28:\5\b\5\298\3\2\2\2:=\3\2\2\2;9\3\2\2\2;<\3\2\2\2"+
		"<A\3\2\2\2=;\3\2\2\2>@\5\n\6\2?>\3\2\2\2@C\3\2\2\2A?\3\2\2\2AB\3\2\2\2"+
		"BD\3\2\2\2CA\3\2\2\2DE\7\17\2\2E\7\3\2\2\2FG\5\f\7\2GH\5\22\n\2HI\7\n"+
		"\2\2I\t\3\2\2\2JK\7\36\2\2KL\5\f\7\2LM\5\22\n\2MY\7\22\2\2NO\5\f\7\2O"+
		"V\5\22\n\2PQ\7\25\2\2QR\5\f\7\2RS\5\22\n\2SU\3\2\2\2TP\3\2\2\2UX\3\2\2"+
		"\2VT\3\2\2\2VW\3\2\2\2WZ\3\2\2\2XV\3\2\2\2YN\3\2\2\2YZ\3\2\2\2Z[\3\2\2"+
		"\2[\\\7\"\2\2\\`\7\13\2\2]_\5\b\5\2^]\3\2\2\2_b\3\2\2\2`^\3\2\2\2`a\3"+
		"\2\2\2af\3\2\2\2b`\3\2\2\2ce\5\16\b\2dc\3\2\2\2eh\3\2\2\2fd\3\2\2\2fg"+
		"\3\2\2\2gi\3\2\2\2hf\3\2\2\2ij\7\7\2\2jk\5\20\t\2kl\7\n\2\2lm\7\17\2\2"+
		"m\13\3\2\2\2no\7\21\2\2op\7\32\2\2pu\7\35\2\2qu\7\30\2\2ru\7\21\2\2su"+
		"\5\22\n\2tn\3\2\2\2tq\3\2\2\2tr\3\2\2\2ts\3\2\2\2u\r\3\2\2\2vz\7\13\2"+
		"\2wy\5\16\b\2xw\3\2\2\2y|\3\2\2\2zx\3\2\2\2z{\3\2\2\2{}\3\2\2\2|z\3\2"+
		"\2\2}\u00a0\7\17\2\2~\177\7\20\2\2\177\u0080\7\22\2\2\u0080\u0081\5\20"+
		"\t\2\u0081\u0082\7\"\2\2\u0082\u0083\5\16\b\2\u0083\u0084\7!\2\2\u0084"+
		"\u0085\5\16\b\2\u0085\u00a0\3\2\2\2\u0086\u0087\7\t\2\2\u0087\u0088\7"+
		"\22\2\2\u0088\u0089\5\20\t\2\u0089\u008a\7\"\2\2\u008a\u008b\5\16\b\2"+
		"\u008b\u00a0\3\2\2\2\u008c\u008d\7\31\2\2\u008d\u008e\7\22\2\2\u008e\u008f"+
		"\5\20\t\2\u008f\u0090\7\"\2\2\u0090\u0091\7\n\2\2\u0091\u00a0\3\2\2\2"+
		"\u0092\u0093\5\22\n\2\u0093\u0094\7\r\2\2\u0094\u0095\5\20\t\2\u0095\u0096"+
		"\7\n\2\2\u0096\u00a0\3\2\2\2\u0097\u0098\5\22\n\2\u0098\u0099\7\32\2\2"+
		"\u0099\u009a\5\20\t\2\u009a\u009b\7\35\2\2\u009b\u009c\7\r\2\2\u009c\u009d"+
		"\5\20\t\2\u009d\u009e\7\n\2\2\u009e\u00a0\3\2\2\2\u009fv\3\2\2\2\u009f"+
		"~\3\2\2\2\u009f\u0086\3\2\2\2\u009f\u008c\3\2\2\2\u009f\u0092\3\2\2\2"+
		"\u009f\u0097\3\2\2\2\u00a0\17\3\2\2\2\u00a1\u00a2\b\t\1\2\u00a2\u00b3"+
		"\5\24\13\2\u00a3\u00b3\7\6\2\2\u00a4\u00b3\7\26\2\2\u00a5\u00b3\5\22\n"+
		"\2\u00a6\u00b3\7\24\2\2\u00a7\u00a8\7\5\2\2\u00a8\u00a9\7\21\2\2\u00a9"+
		"\u00aa\7\32\2\2\u00aa\u00ab\5\20\t\2\u00ab\u00ac\7\35\2\2\u00ac\u00b3"+
		"\3\2\2\2\u00ad\u00ae\7\5\2\2\u00ae\u00af\5\22\n\2\u00af\u00b0\7\22\2\2"+
		"\u00b0\u00b1\7\"\2\2\u00b1\u00b3\3\2\2\2\u00b2\u00a1\3\2\2\2\u00b2\u00a3"+
		"\3\2\2\2\u00b2\u00a4\3\2\2\2\u00b2\u00a5\3\2\2\2\u00b2\u00a6\3\2\2\2\u00b2"+
		"\u00a7\3\2\2\2\u00b2\u00ad\3\2\2\2\u00b3\u00d1\3\2\2\2\u00b4\u00b5\f\r"+
		"\2\2\u00b5\u00b6\t\2\2\2\u00b6\u00d0\5\20\t\16\u00b7\u00b8\f\f\2\2\u00b8"+
		"\u00b9\7\32\2\2\u00b9\u00ba\5\20\t\2\u00ba\u00bb\7\35\2\2\u00bb\u00d0"+
		"\3\2\2\2\u00bc\u00bd\f\13\2\2\u00bd\u00be\7\27\2\2\u00be\u00d0\7\4\2\2"+
		"\u00bf\u00c0\f\n\2\2\u00c0\u00c1\7\27\2\2\u00c1\u00c2\5\22\n\2\u00c2\u00cb"+
		"\7\22\2\2\u00c3\u00c8\5\20\t\2\u00c4\u00c5\7\25\2\2\u00c5\u00c7\5\20\t"+
		"\2\u00c6\u00c4\3\2\2\2\u00c7\u00ca\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c8\u00c9"+
		"\3\2\2\2\u00c9\u00cc\3\2\2\2\u00ca\u00c8\3\2\2\2\u00cb\u00c3\3\2\2\2\u00cb"+
		"\u00cc\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00ce\7\"\2\2\u00ce\u00d0\3\2"+
		"\2\2\u00cf\u00b4\3\2\2\2\u00cf\u00b7\3\2\2\2\u00cf\u00bc\3\2\2\2\u00cf"+
		"\u00bf\3\2\2\2\u00d0\u00d3\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d1\u00d2\3\2"+
		"\2\2\u00d2\21\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d4\u00d5\7\'\2\2\u00d5\23"+
		"\3\2\2\2\u00d6\u00d7\7(\2\2\u00d7\25\3\2\2\2\22\32\65;AVY`ftz\u009f\u00b2"+
		"\u00c8\u00cb\u00cf\u00d1";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
	}
}