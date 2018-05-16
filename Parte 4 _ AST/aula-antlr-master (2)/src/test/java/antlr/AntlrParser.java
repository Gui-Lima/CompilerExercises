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
		MainClass=1, ClassDeclaration=2, VarDeclaration=3, MethodDeclaration=4, 
		Type=5, Statement=6, Expression=7, ExpAux=8, INTEGER_LITERAL=9, Identifier=10, 
		IDENTIFIER=11, WS=12;
	public static final String[] tokenNames = {
		"<INVALID>", "MainClass", "ClassDeclaration", "VarDeclaration", "MethodDeclaration", 
		"Type", "Statement", "Expression", "ExpAux", "INTEGER_LITERAL", "Identifier", 
		"IDENTIFIER", "WS"
	};
	public static final int
		RULE_goal = 0;
	public static final String[] ruleNames = {
		"goal"
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
		public TerminalNode MainClass() { return getToken(AntlrParser.MainClass, 0); }
		public TerminalNode ClassDeclaration(int i) {
			return getToken(AntlrParser.ClassDeclaration, i);
		}
		public List<? extends TerminalNode> ClassDeclaration() { return getTokens(AntlrParser.ClassDeclaration); }
		public GoalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_goal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AntlrListener ) ((AntlrListener)listener).enterGoal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AntlrListener ) ((AntlrListener)listener).exitGoal(this);
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
			setState(2); match(MainClass);
			setState(6);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ClassDeclaration) {
				{
				{
				setState(3); match(ClassDeclaration);
				}
				}
				setState(8);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static final String _serializedATN =
		"\3\uaf6f\u8320\u479d\ub75c\u4880\u1605\u191c\uab37\3\16\f\4\2\t\2\3\2"+
		"\3\2\7\2\7\n\2\f\2\16\2\n\13\2\3\2\2\2\2\3\2\2\2\2\13\2\4\3\2\2\2\4\b"+
		"\7\3\2\2\5\7\7\4\2\2\6\5\3\2\2\2\7\n\3\2\2\2\b\6\3\2\2\2\b\t\3\2\2\2\t"+
		"\3\3\2\2\2\n\b\3\2\2\2\3\b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
	}
}