// Generated from D:\coding\Taked-course\2021_2\programming_language\hw\hw3\src\main\antlr4\MiniScalaLexer.g4 by ANTLR 4.8
package hw3;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MiniScalaLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, INT=2, BOOL=3, VAR=4, VAL=5, DEF=6, PLUS=7, MINUS=8, STAR=9, SLASH=10, 
		MOD=11, SEMI=12, ARROW=13, GT=14, GEQ=15, ASN=16, ISZERO=17, IF=18, THEN=19, 
		ELSE=20, EQ=21, LPAREN=22, RPAREN=23, LBRACKET=24, RBRACKET=25, LSQBRACKET=26, 
		RSQBRACKET=27, COMMA=28, CONS=29, NIL=30, ID=31;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"WS", "INT", "BOOL", "VAR", "VAL", "DEF", "PLUS", "MINUS", "STAR", "SLASH", 
			"MOD", "SEMI", "ARROW", "GT", "GEQ", "ASN", "ISZERO", "IF", "THEN", "ELSE", 
			"EQ", "LPAREN", "RPAREN", "LBRACKET", "RBRACKET", "LSQBRACKET", "RSQBRACKET", 
			"COMMA", "CONS", "NIL", "ID", "DIGIT", "ALPHA"
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


	public MiniScalaLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MiniScalaLexer.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2!\u00b7\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\3\2\6\2G\n\2\r\2\16\2H\3\2\3\2\3\3\6\3N\n\3\r\3\16\3O\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4[\n\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6"+
		"\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\f"+
		"\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3"+
		"\25\3\25\3\25\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3"+
		"\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3"+
		"\37\3\37\3 \6 \u00b0\n \r \16 \u00b1\3!\3!\3\"\3\"\2\2#\3\3\5\4\7\5\t"+
		"\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\2C\2\3"+
		"\2\5\4\2\13\f\"\"\3\2\62;\4\2C\\c|\2\u00b8\2\3\3\2\2\2\2\5\3\2\2\2\2\7"+
		"\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2"+
		"\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2"+
		"\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2"+
		"\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2"+
		"\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2"+
		"\3F\3\2\2\2\5M\3\2\2\2\7Z\3\2\2\2\t\\\3\2\2\2\13`\3\2\2\2\rd\3\2\2\2\17"+
		"h\3\2\2\2\21j\3\2\2\2\23l\3\2\2\2\25n\3\2\2\2\27p\3\2\2\2\31t\3\2\2\2"+
		"\33v\3\2\2\2\35y\3\2\2\2\37{\3\2\2\2!~\3\2\2\2#\u0081\3\2\2\2%\u0088\3"+
		"\2\2\2\'\u008b\3\2\2\2)\u0090\3\2\2\2+\u0095\3\2\2\2-\u0097\3\2\2\2/\u0099"+
		"\3\2\2\2\61\u009b\3\2\2\2\63\u009d\3\2\2\2\65\u009f\3\2\2\2\67\u00a1\3"+
		"\2\2\29\u00a3\3\2\2\2;\u00a5\3\2\2\2=\u00aa\3\2\2\2?\u00af\3\2\2\2A\u00b3"+
		"\3\2\2\2C\u00b5\3\2\2\2EG\t\2\2\2FE\3\2\2\2GH\3\2\2\2HF\3\2\2\2HI\3\2"+
		"\2\2IJ\3\2\2\2JK\b\2\2\2K\4\3\2\2\2LN\5A!\2ML\3\2\2\2NO\3\2\2\2OM\3\2"+
		"\2\2OP\3\2\2\2P\6\3\2\2\2QR\7v\2\2RS\7t\2\2ST\7w\2\2T[\7g\2\2UV\7h\2\2"+
		"VW\7c\2\2WX\7n\2\2XY\7u\2\2Y[\7g\2\2ZQ\3\2\2\2ZU\3\2\2\2[\b\3\2\2\2\\"+
		"]\7x\2\2]^\7c\2\2^_\7t\2\2_\n\3\2\2\2`a\7x\2\2ab\7c\2\2bc\7n\2\2c\f\3"+
		"\2\2\2de\7f\2\2ef\7g\2\2fg\7h\2\2g\16\3\2\2\2hi\7-\2\2i\20\3\2\2\2jk\7"+
		"/\2\2k\22\3\2\2\2lm\7,\2\2m\24\3\2\2\2no\7\61\2\2o\26\3\2\2\2pq\7o\2\2"+
		"qr\7q\2\2rs\7f\2\2s\30\3\2\2\2tu\7=\2\2u\32\3\2\2\2vw\7?\2\2wx\7@\2\2"+
		"x\34\3\2\2\2yz\7@\2\2z\36\3\2\2\2{|\7@\2\2|}\7?\2\2} \3\2\2\2~\177\7<"+
		"\2\2\177\u0080\7?\2\2\u0080\"\3\2\2\2\u0081\u0082\7k\2\2\u0082\u0083\7"+
		"u\2\2\u0083\u0084\7|\2\2\u0084\u0085\7g\2\2\u0085\u0086\7t\2\2\u0086\u0087"+
		"\7q\2\2\u0087$\3\2\2\2\u0088\u0089\7k\2\2\u0089\u008a\7h\2\2\u008a&\3"+
		"\2\2\2\u008b\u008c\7v\2\2\u008c\u008d\7j\2\2\u008d\u008e\7g\2\2\u008e"+
		"\u008f\7p\2\2\u008f(\3\2\2\2\u0090\u0091\7g\2\2\u0091\u0092\7n\2\2\u0092"+
		"\u0093\7u\2\2\u0093\u0094\7g\2\2\u0094*\3\2\2\2\u0095\u0096\7?\2\2\u0096"+
		",\3\2\2\2\u0097\u0098\7*\2\2\u0098.\3\2\2\2\u0099\u009a\7+\2\2\u009a\60"+
		"\3\2\2\2\u009b\u009c\7}\2\2\u009c\62\3\2\2\2\u009d\u009e\7\177\2\2\u009e"+
		"\64\3\2\2\2\u009f\u00a0\7]\2\2\u00a0\66\3\2\2\2\u00a1\u00a2\7_\2\2\u00a2"+
		"8\3\2\2\2\u00a3\u00a4\7.\2\2\u00a4:\3\2\2\2\u00a5\u00a6\7E\2\2\u00a6\u00a7"+
		"\7q\2\2\u00a7\u00a8\7p\2\2\u00a8\u00a9\7u\2\2\u00a9<\3\2\2\2\u00aa\u00ab"+
		"\7P\2\2\u00ab\u00ac\7k\2\2\u00ac\u00ad\7n\2\2\u00ad>\3\2\2\2\u00ae\u00b0"+
		"\5C\"\2\u00af\u00ae\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00af\3\2\2\2\u00b1"+
		"\u00b2\3\2\2\2\u00b2@\3\2\2\2\u00b3\u00b4\t\3\2\2\u00b4B\3\2\2\2\u00b5"+
		"\u00b6\t\4\2\2\u00b6D\3\2\2\2\7\2HOZ\u00b1\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}