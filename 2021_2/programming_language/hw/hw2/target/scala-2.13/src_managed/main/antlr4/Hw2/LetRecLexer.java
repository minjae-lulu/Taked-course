// Generated from D:\coding\Taked-course\2021_2\programming_language\hw\hw2\src\main\antlr4\LetRecLexer.g4 by ANTLR 4.8
package Hw2;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LetRecLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, INT=2, LET=3, IN=4, PLUS=5, MINUS=6, LETREC=7, ISZERO=8, EQUAL=9, 
		IF=10, THEN=11, ELSE=12, EQ=13, LPAREN=14, RPAREN=15, PROC=16, ID=17;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"WS", "INT", "LET", "IN", "PLUS", "MINUS", "LETREC", "ISZERO", "EQUAL", 
			"IF", "THEN", "ELSE", "EQ", "LPAREN", "RPAREN", "PROC", "ID", "DIGIT", 
			"ALPHA"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, "'let'", "'in'", "'+'", "'-'", "'letrec'", "'iszero'", 
			"'=='", "'if'", "'then'", "'else'", "'='", "'('", "')'", "'proc'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "WS", "INT", "LET", "IN", "PLUS", "MINUS", "LETREC", "ISZERO", 
			"EQUAL", "IF", "THEN", "ELSE", "EQ", "LPAREN", "RPAREN", "PROC", "ID"
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


	public LetRecLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "LetRecLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\23r\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\3\2\6\2+\n\2\r\2\16\2,\3\2\3\2\3\3\6\3\62\n\3\r\3"+
		"\16\3\63\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\f"+
		"\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21"+
		"\3\21\3\21\3\21\3\21\3\22\6\22k\n\22\r\22\16\22l\3\23\3\23\3\24\3\24\2"+
		"\2\25\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\37\21!\22#\23%\2\'\2\3\2\5\4\2\13\f\"\"\3\2\62;\4\2C\\c|\2r\2\3\3"+
		"\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2"+
		"\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3"+
		"\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\3"+
		"*\3\2\2\2\5\61\3\2\2\2\7\65\3\2\2\2\t9\3\2\2\2\13<\3\2\2\2\r>\3\2\2\2"+
		"\17@\3\2\2\2\21G\3\2\2\2\23N\3\2\2\2\25Q\3\2\2\2\27T\3\2\2\2\31Y\3\2\2"+
		"\2\33^\3\2\2\2\35`\3\2\2\2\37b\3\2\2\2!d\3\2\2\2#j\3\2\2\2%n\3\2\2\2\'"+
		"p\3\2\2\2)+\t\2\2\2*)\3\2\2\2+,\3\2\2\2,*\3\2\2\2,-\3\2\2\2-.\3\2\2\2"+
		"./\b\2\2\2/\4\3\2\2\2\60\62\5%\23\2\61\60\3\2\2\2\62\63\3\2\2\2\63\61"+
		"\3\2\2\2\63\64\3\2\2\2\64\6\3\2\2\2\65\66\7n\2\2\66\67\7g\2\2\678\7v\2"+
		"\28\b\3\2\2\29:\7k\2\2:;\7p\2\2;\n\3\2\2\2<=\7-\2\2=\f\3\2\2\2>?\7/\2"+
		"\2?\16\3\2\2\2@A\7n\2\2AB\7g\2\2BC\7v\2\2CD\7t\2\2DE\7g\2\2EF\7e\2\2F"+
		"\20\3\2\2\2GH\7k\2\2HI\7u\2\2IJ\7|\2\2JK\7g\2\2KL\7t\2\2LM\7q\2\2M\22"+
		"\3\2\2\2NO\7?\2\2OP\7?\2\2P\24\3\2\2\2QR\7k\2\2RS\7h\2\2S\26\3\2\2\2T"+
		"U\7v\2\2UV\7j\2\2VW\7g\2\2WX\7p\2\2X\30\3\2\2\2YZ\7g\2\2Z[\7n\2\2[\\\7"+
		"u\2\2\\]\7g\2\2]\32\3\2\2\2^_\7?\2\2_\34\3\2\2\2`a\7*\2\2a\36\3\2\2\2"+
		"bc\7+\2\2c \3\2\2\2de\7r\2\2ef\7t\2\2fg\7q\2\2gh\7e\2\2h\"\3\2\2\2ik\5"+
		"\'\24\2ji\3\2\2\2kl\3\2\2\2lj\3\2\2\2lm\3\2\2\2m$\3\2\2\2no\t\3\2\2o&"+
		"\3\2\2\2pq\t\4\2\2q(\3\2\2\2\6\2,\63l\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}