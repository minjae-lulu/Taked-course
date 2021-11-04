// Generated from D:\coding\Taked-course\2021_2\programming_language\hw\hw3\src\main\antlr4\MiniScalaParser.g4 by ANTLR 4.8
package hw3;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MiniScalaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, INT=2, BOOL=3, VAR=4, VAL=5, DEF=6, PLUS=7, MINUS=8, STAR=9, SLASH=10, 
		MOD=11, SEMI=12, ARROW=13, GT=14, GEQ=15, ASN=16, ISZERO=17, IF=18, THEN=19, 
		ELSE=20, EQ=21, LPAREN=22, RPAREN=23, LBRACKET=24, RBRACKET=25, LSQBRACKET=26, 
		RSQBRACKET=27, COMMA=28, CONS=29, NIL=30, ID=31;
	public static final int
		RULE_program = 0, RULE_expr = 1, RULE_iszero = 2, RULE_ite = 3, RULE_val = 4, 
		RULE_var = 5, RULE_proc = 6, RULE_def = 7, RULE_cons = 8, RULE_intlist = 9, 
		RULE_integer = 10, RULE_bool = 11, RULE_id = 12;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "expr", "iszero", "ite", "val", "var", "proc", "def", "cons", 
			"intlist", "integer", "bool", "id"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, "'var'", "'val'", "'def'", "'+'", "'-'", "'*'", 
			"'/'", "'mod'", "';'", "'=>'", "'>'", "'>='", "':='", "'iszero'", "'if'", 
			"'then'", "'else'", "'='", "'('", "')'", "'{'", "'}'", "'['", "']'", 
			"','", "'Cons'", "'Nil'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "WS", "INT", "BOOL", "VAR", "VAL", "DEF", "PLUS", "MINUS", "STAR", 
			"SLASH", "MOD", "SEMI", "ARROW", "GT", "GEQ", "ASN", "ISZERO", "IF", 
			"THEN", "ELSE", "EQ", "LPAREN", "RPAREN", "LBRACKET", "RBRACKET", "LSQBRACKET", 
			"RSQBRACKET", "COMMA", "CONS", "NIL", "ID"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "MiniScalaParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MiniScalaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode EOF() { return getToken(MiniScalaParser.EOF, 0); }
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniScalaParserVisitor ) return ((MiniScalaParserVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			expr(0);
			setState(27);
			match(EOF);
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

	public static class ExprContext extends ParserRuleContext {
		public IntegerContext integer() {
			return getRuleContext(IntegerContext.class,0);
		}
		public BoolContext bool() {
			return getRuleContext(BoolContext.class,0);
		}
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public IszeroContext iszero() {
			return getRuleContext(IszeroContext.class,0);
		}
		public IteContext ite() {
			return getRuleContext(IteContext.class,0);
		}
		public ValContext val() {
			return getRuleContext(ValContext.class,0);
		}
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public ProcContext proc() {
			return getRuleContext(ProcContext.class,0);
		}
		public DefContext def() {
			return getRuleContext(DefContext.class,0);
		}
		public TerminalNode ASN() { return getToken(MiniScalaParser.ASN, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode LPAREN() { return getToken(MiniScalaParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(MiniScalaParser.RPAREN, 0); }
		public TerminalNode LBRACKET() { return getToken(MiniScalaParser.LBRACKET, 0); }
		public TerminalNode RBRACKET() { return getToken(MiniScalaParser.RBRACKET, 0); }
		public TerminalNode LSQBRACKET() { return getToken(MiniScalaParser.LSQBRACKET, 0); }
		public TerminalNode RSQBRACKET() { return getToken(MiniScalaParser.RSQBRACKET, 0); }
		public IntlistContext intlist() {
			return getRuleContext(IntlistContext.class,0);
		}
		public ConsContext cons() {
			return getRuleContext(ConsContext.class,0);
		}
		public TerminalNode NIL() { return getToken(MiniScalaParser.NIL, 0); }
		public TerminalNode PLUS() { return getToken(MiniScalaParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(MiniScalaParser.MINUS, 0); }
		public TerminalNode STAR() { return getToken(MiniScalaParser.STAR, 0); }
		public TerminalNode SLASH() { return getToken(MiniScalaParser.SLASH, 0); }
		public TerminalNode MOD() { return getToken(MiniScalaParser.MOD, 0); }
		public TerminalNode GT() { return getToken(MiniScalaParser.GT, 0); }
		public TerminalNode GEQ() { return getToken(MiniScalaParser.GEQ, 0); }
		public TerminalNode SEMI() { return getToken(MiniScalaParser.SEMI, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniScalaParserVisitor ) return ((MiniScalaParserVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(30);
				integer();
				}
				break;
			case 2:
				{
				setState(31);
				bool();
				}
				break;
			case 3:
				{
				setState(32);
				id();
				}
				break;
			case 4:
				{
				setState(33);
				iszero();
				}
				break;
			case 5:
				{
				setState(34);
				ite();
				}
				break;
			case 6:
				{
				setState(35);
				val();
				}
				break;
			case 7:
				{
				setState(36);
				var();
				}
				break;
			case 8:
				{
				setState(37);
				proc();
				}
				break;
			case 9:
				{
				setState(38);
				def();
				}
				break;
			case 10:
				{
				setState(39);
				id();
				setState(40);
				match(ASN);
				setState(41);
				expr(9);
				}
				break;
			case 11:
				{
				setState(43);
				match(LPAREN);
				setState(44);
				expr(0);
				setState(45);
				match(RPAREN);
				}
				break;
			case 12:
				{
				setState(47);
				match(LBRACKET);
				setState(48);
				expr(0);
				setState(49);
				match(RBRACKET);
				}
				break;
			case 13:
				{
				setState(51);
				match(LSQBRACKET);
				setState(52);
				match(RSQBRACKET);
				}
				break;
			case 14:
				{
				setState(53);
				match(LSQBRACKET);
				setState(54);
				intlist();
				setState(55);
				match(RSQBRACKET);
				}
				break;
			case 15:
				{
				setState(57);
				cons();
				}
				break;
			case 16:
				{
				setState(58);
				match(NIL);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(71);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(69);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(61);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(62);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << STAR) | (1L << SLASH) | (1L << MOD) | (1L << GT) | (1L << GEQ))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(63);
						expr(17);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(64);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(65);
						match(SEMI);
						setState(66);
						expr(9);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(67);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(68);
						expr(8);
						}
						break;
					}
					} 
				}
				setState(73);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
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

	public static class IszeroContext extends ParserRuleContext {
		public TerminalNode ISZERO() { return getToken(MiniScalaParser.ISZERO, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public IszeroContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iszero; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniScalaParserVisitor ) return ((MiniScalaParserVisitor<? extends T>)visitor).visitIszero(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IszeroContext iszero() throws RecognitionException {
		IszeroContext _localctx = new IszeroContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_iszero);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			match(ISZERO);
			setState(75);
			expr(0);
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

	public static class IteContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(MiniScalaParser.IF, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode THEN() { return getToken(MiniScalaParser.THEN, 0); }
		public TerminalNode ELSE() { return getToken(MiniScalaParser.ELSE, 0); }
		public IteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ite; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniScalaParserVisitor ) return ((MiniScalaParserVisitor<? extends T>)visitor).visitIte(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IteContext ite() throws RecognitionException {
		IteContext _localctx = new IteContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_ite);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			match(IF);
			setState(78);
			expr(0);
			setState(79);
			match(THEN);
			setState(80);
			expr(0);
			setState(81);
			match(ELSE);
			setState(82);
			expr(0);
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

	public static class ValContext extends ParserRuleContext {
		public TerminalNode LBRACKET() { return getToken(MiniScalaParser.LBRACKET, 0); }
		public TerminalNode VAL() { return getToken(MiniScalaParser.VAL, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TerminalNode EQ() { return getToken(MiniScalaParser.EQ, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode SEMI() { return getToken(MiniScalaParser.SEMI, 0); }
		public TerminalNode RBRACKET() { return getToken(MiniScalaParser.RBRACKET, 0); }
		public ValContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_val; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniScalaParserVisitor ) return ((MiniScalaParserVisitor<? extends T>)visitor).visitVal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValContext val() throws RecognitionException {
		ValContext _localctx = new ValContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_val);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(LBRACKET);
			setState(85);
			match(VAL);
			setState(86);
			id();
			setState(87);
			match(EQ);
			setState(88);
			expr(0);
			setState(89);
			match(SEMI);
			setState(90);
			expr(0);
			setState(91);
			match(RBRACKET);
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

	public static class VarContext extends ParserRuleContext {
		public TerminalNode LBRACKET() { return getToken(MiniScalaParser.LBRACKET, 0); }
		public TerminalNode VAR() { return getToken(MiniScalaParser.VAR, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TerminalNode EQ() { return getToken(MiniScalaParser.EQ, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode SEMI() { return getToken(MiniScalaParser.SEMI, 0); }
		public TerminalNode RBRACKET() { return getToken(MiniScalaParser.RBRACKET, 0); }
		public VarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniScalaParserVisitor ) return ((MiniScalaParserVisitor<? extends T>)visitor).visitVar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarContext var() throws RecognitionException {
		VarContext _localctx = new VarContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_var);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			match(LBRACKET);
			setState(94);
			match(VAR);
			setState(95);
			id();
			setState(96);
			match(EQ);
			setState(97);
			expr(0);
			setState(98);
			match(SEMI);
			setState(99);
			expr(0);
			setState(100);
			match(RBRACKET);
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

	public static class ProcContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(MiniScalaParser.LPAREN, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(MiniScalaParser.RPAREN, 0); }
		public TerminalNode ARROW() { return getToken(MiniScalaParser.ARROW, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ProcContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_proc; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniScalaParserVisitor ) return ((MiniScalaParserVisitor<? extends T>)visitor).visitProc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProcContext proc() throws RecognitionException {
		ProcContext _localctx = new ProcContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_proc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			match(LPAREN);
			setState(103);
			id();
			setState(104);
			match(RPAREN);
			setState(105);
			match(ARROW);
			setState(106);
			expr(0);
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

	public static class DefContext extends ParserRuleContext {
		public TerminalNode LBRACKET() { return getToken(MiniScalaParser.LBRACKET, 0); }
		public TerminalNode DEF() { return getToken(MiniScalaParser.DEF, 0); }
		public List<IdContext> id() {
			return getRuleContexts(IdContext.class);
		}
		public IdContext id(int i) {
			return getRuleContext(IdContext.class,i);
		}
		public TerminalNode LPAREN() { return getToken(MiniScalaParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(MiniScalaParser.RPAREN, 0); }
		public TerminalNode EQ() { return getToken(MiniScalaParser.EQ, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode SEMI() { return getToken(MiniScalaParser.SEMI, 0); }
		public TerminalNode RBRACKET() { return getToken(MiniScalaParser.RBRACKET, 0); }
		public DefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_def; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniScalaParserVisitor ) return ((MiniScalaParserVisitor<? extends T>)visitor).visitDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefContext def() throws RecognitionException {
		DefContext _localctx = new DefContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_def);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			match(LBRACKET);
			setState(109);
			match(DEF);
			setState(110);
			id();
			setState(111);
			match(LPAREN);
			setState(112);
			id();
			setState(113);
			match(RPAREN);
			setState(114);
			match(EQ);
			setState(115);
			expr(0);
			setState(116);
			match(SEMI);
			setState(117);
			expr(0);
			setState(118);
			match(RBRACKET);
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

	public static class ConsContext extends ParserRuleContext {
		public TerminalNode CONS() { return getToken(MiniScalaParser.CONS, 0); }
		public TerminalNode LPAREN() { return getToken(MiniScalaParser.LPAREN, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode COMMA() { return getToken(MiniScalaParser.COMMA, 0); }
		public TerminalNode RPAREN() { return getToken(MiniScalaParser.RPAREN, 0); }
		public ConsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cons; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniScalaParserVisitor ) return ((MiniScalaParserVisitor<? extends T>)visitor).visitCons(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConsContext cons() throws RecognitionException {
		ConsContext _localctx = new ConsContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_cons);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			match(CONS);
			setState(121);
			match(LPAREN);
			setState(122);
			expr(0);
			setState(123);
			match(COMMA);
			setState(124);
			expr(0);
			setState(125);
			match(RPAREN);
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

	public static class IntlistContext extends ParserRuleContext {
		public IntegerContext integer() {
			return getRuleContext(IntegerContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(MiniScalaParser.COMMA, 0); }
		public IntlistContext intlist() {
			return getRuleContext(IntlistContext.class,0);
		}
		public IntlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_intlist; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniScalaParserVisitor ) return ((MiniScalaParserVisitor<? extends T>)visitor).visitIntlist(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntlistContext intlist() throws RecognitionException {
		IntlistContext _localctx = new IntlistContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_intlist);
		try {
			setState(132);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(127);
				integer();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(128);
				integer();
				setState(129);
				match(COMMA);
				setState(130);
				intlist();
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

	public static class IntegerContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(MiniScalaParser.INT, 0); }
		public IntegerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integer; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniScalaParserVisitor ) return ((MiniScalaParserVisitor<? extends T>)visitor).visitInteger(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntegerContext integer() throws RecognitionException {
		IntegerContext _localctx = new IntegerContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_integer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			match(INT);
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

	public static class BoolContext extends ParserRuleContext {
		public TerminalNode BOOL() { return getToken(MiniScalaParser.BOOL, 0); }
		public BoolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bool; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniScalaParserVisitor ) return ((MiniScalaParserVisitor<? extends T>)visitor).visitBool(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolContext bool() throws RecognitionException {
		BoolContext _localctx = new BoolContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_bool);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			match(BOOL);
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

	public static class IdContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MiniScalaParser.ID, 0); }
		public IdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniScalaParserVisitor ) return ((MiniScalaParserVisitor<? extends T>)visitor).visitId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdContext id() throws RecognitionException {
		IdContext _localctx = new IdContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_id);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			match(ID);
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
		case 1:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 16);
		case 1:
			return precpred(_ctx, 8);
		case 2:
			return precpred(_ctx, 7);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3!\u008f\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\5\3>\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3H\n"+
		"\3\f\3\16\3K\13\3\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\5\13\u0087\n\13\3\f\3\f"+
		"\3\r\3\r\3\16\3\16\3\16\2\3\4\17\2\4\6\b\n\f\16\20\22\24\26\30\32\2\3"+
		"\4\2\t\r\20\21\2\u0094\2\34\3\2\2\2\4=\3\2\2\2\6L\3\2\2\2\bO\3\2\2\2\n"+
		"V\3\2\2\2\f_\3\2\2\2\16h\3\2\2\2\20n\3\2\2\2\22z\3\2\2\2\24\u0086\3\2"+
		"\2\2\26\u0088\3\2\2\2\30\u008a\3\2\2\2\32\u008c\3\2\2\2\34\35\5\4\3\2"+
		"\35\36\7\2\2\3\36\3\3\2\2\2\37 \b\3\1\2 >\5\26\f\2!>\5\30\r\2\">\5\32"+
		"\16\2#>\5\6\4\2$>\5\b\5\2%>\5\n\6\2&>\5\f\7\2\'>\5\16\b\2(>\5\20\t\2)"+
		"*\5\32\16\2*+\7\22\2\2+,\5\4\3\13,>\3\2\2\2-.\7\30\2\2./\5\4\3\2/\60\7"+
		"\31\2\2\60>\3\2\2\2\61\62\7\32\2\2\62\63\5\4\3\2\63\64\7\33\2\2\64>\3"+
		"\2\2\2\65\66\7\34\2\2\66>\7\35\2\2\678\7\34\2\289\5\24\13\29:\7\35\2\2"+
		":>\3\2\2\2;>\5\22\n\2<>\7 \2\2=\37\3\2\2\2=!\3\2\2\2=\"\3\2\2\2=#\3\2"+
		"\2\2=$\3\2\2\2=%\3\2\2\2=&\3\2\2\2=\'\3\2\2\2=(\3\2\2\2=)\3\2\2\2=-\3"+
		"\2\2\2=\61\3\2\2\2=\65\3\2\2\2=\67\3\2\2\2=;\3\2\2\2=<\3\2\2\2>I\3\2\2"+
		"\2?@\f\22\2\2@A\t\2\2\2AH\5\4\3\23BC\f\n\2\2CD\7\16\2\2DH\5\4\3\13EF\f"+
		"\t\2\2FH\5\4\3\nG?\3\2\2\2GB\3\2\2\2GE\3\2\2\2HK\3\2\2\2IG\3\2\2\2IJ\3"+
		"\2\2\2J\5\3\2\2\2KI\3\2\2\2LM\7\23\2\2MN\5\4\3\2N\7\3\2\2\2OP\7\24\2\2"+
		"PQ\5\4\3\2QR\7\25\2\2RS\5\4\3\2ST\7\26\2\2TU\5\4\3\2U\t\3\2\2\2VW\7\32"+
		"\2\2WX\7\7\2\2XY\5\32\16\2YZ\7\27\2\2Z[\5\4\3\2[\\\7\16\2\2\\]\5\4\3\2"+
		"]^\7\33\2\2^\13\3\2\2\2_`\7\32\2\2`a\7\6\2\2ab\5\32\16\2bc\7\27\2\2cd"+
		"\5\4\3\2de\7\16\2\2ef\5\4\3\2fg\7\33\2\2g\r\3\2\2\2hi\7\30\2\2ij\5\32"+
		"\16\2jk\7\31\2\2kl\7\17\2\2lm\5\4\3\2m\17\3\2\2\2no\7\32\2\2op\7\b\2\2"+
		"pq\5\32\16\2qr\7\30\2\2rs\5\32\16\2st\7\31\2\2tu\7\27\2\2uv\5\4\3\2vw"+
		"\7\16\2\2wx\5\4\3\2xy\7\33\2\2y\21\3\2\2\2z{\7\37\2\2{|\7\30\2\2|}\5\4"+
		"\3\2}~\7\36\2\2~\177\5\4\3\2\177\u0080\7\31\2\2\u0080\23\3\2\2\2\u0081"+
		"\u0087\5\26\f\2\u0082\u0083\5\26\f\2\u0083\u0084\7\36\2\2\u0084\u0085"+
		"\5\24\13\2\u0085\u0087\3\2\2\2\u0086\u0081\3\2\2\2\u0086\u0082\3\2\2\2"+
		"\u0087\25\3\2\2\2\u0088\u0089\7\4\2\2\u0089\27\3\2\2\2\u008a\u008b\7\5"+
		"\2\2\u008b\31\3\2\2\2\u008c\u008d\7!\2\2\u008d\33\3\2\2\2\6=GI\u0086";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}