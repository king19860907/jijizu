package com.jijizu.base.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.BitSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import com.jijizu.base.util.internal.Entities;

import static java.nio.charset.Charset.isSupported;
import static com.jijizu.base.util.BasicConstant.*;
import static com.jijizu.base.util.MD5.*;

/**
 * 字符实用类。 <br/>
 * 
 * @author huangyongqiang
 * @since 2011-05-13
 */
public final class StringUtil extends StringUtils {

	public static final String REG_EXP = "[$(\\?\\:\\=\\!)*+.\\[?\\]^|{\'}\"\\\\]%<>&";

	private static final String[] RANDOM_PSW = { "0123456789",
			"abcdefghijklmnopqrstuvwxyz", "ABCDEFGHIJKLMNOPQRSTUVWXYZ" };

	public static final String[] RANDOM_BASE = { "0123456789_",
			"abcdefghijklmnopqrstuvwxyz", "ABCDEFGHIJKLMNOPQRSTUVWXYZ_",
			"~#$%^&*()@\\/" };

	private static final Random RANDOM = new Random();

	private static final char[] CHARS = new char[] { 'a', 'b', 'c', 'd', 'e',
			'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
			's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4',
			'5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
			'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
			'V', 'W', 'X', 'Y', 'Z', '_'

	};

	/** "Unreserved" characters from RFC 2396. */
	private static final BitSet UNRESERVED = new BitSet(256);

	/** "Reserved" characters from RFC 2396. */
	private static final BitSet RESERVED = new BitSet(256);

	/** 将一个数字转换成16进制的转换表。 */
	private static char[] HEXADECIMAL = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	public static final Map<String, String> SPECIAL_CHAR_ENTITIES = new LinkedHashMap<String, String>();

	static {
		SPECIAL_CHAR_ENTITIES.put("'", "&#039;");
		SPECIAL_CHAR_ENTITIES.put("\\", "\\\\");
		SPECIAL_CHAR_ENTITIES.put("&", "&amp;");
		SPECIAL_CHAR_ENTITIES.put("\"", "&quot;");
		SPECIAL_CHAR_ENTITIES.put("<", "&lt;");
		SPECIAL_CHAR_ENTITIES.put(">", "&gt;");
		SPECIAL_CHAR_ENTITIES.put("*", "*");
		SPECIAL_CHAR_ENTITIES.put("?", "?");
		SPECIAL_CHAR_ENTITIES.put("%", "%");
		SPECIAL_CHAR_ENTITIES.put("\"", "&quot;");

		RESERVED.set(';');
		RESERVED.set('/');
		RESERVED.set('?');
		RESERVED.set(':');
		RESERVED.set('@');
		RESERVED.set('&');
		RESERVED.set('=');
		RESERVED.set('+');
		RESERVED.set('$');
		RESERVED.set(',');
	};

	public static boolean isNullOrEmpty(String s) {
		if (null == s || 1 > s.trim().length()) {
			return true;
		}
		return false;
	}

	public static boolean isNotNullOrEmpty(String s) {
		if (null == s || s.trim().length() < 1) {
			return false;
		}
		return true;
	}

	public static Long stringToLongNumber(String s, long defaultValue) {
		Long ret = null;
		if (isNotNullOrEmpty(s) && isNumeric(s)) {
			try {
				ret = Long.valueOf(s);
			} catch (Exception e) {
				// LOG.error("把{0}转换为Long型数字出错", e, s);
			}
		}
		if (null == ret) {
			ret = defaultValue;
		}
		return ret;
	}

	public static Integer stringToIntNumber(String s, int defaultValue) {
		Integer ret = null;
		if (isNotNullOrEmpty(s) && isNumeric(s)) {
			try {
				ret = Integer.valueOf(s);
			} catch (Exception e) {
				// LOG.error("把{0}转换为Integer型数字出错", e, s);
			}
		}
		if (null == ret) {
			ret = defaultValue;
		}
		return ret;
	}

	public static String UUIDString() {
		return UUID.randomUUID().toString();
	}

	public static String shortUUIDString() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static String format(String msg, String args[]) {
		if (msg != null && msg.length() > 0 && msg.indexOf('#') > -1) {
			StringBuilder sb = new StringBuilder();
			boolean isArg = false;
			for (int x = 0; x < msg.length(); x++) {
				char c = msg.charAt(x);
				if (isArg) {
					isArg = false;
					if (Character.isDigit(c)) {
						int val = Character.getNumericValue(c);
						if (val >= 0 && val < args.length) {
							sb.append(args[val]);
							continue;
						}
					}
					sb.append('#');
				}
				if (c == '#')
					isArg = true;
				else
					sb.append(c);
			}

			if (isArg)
				sb.append('#');
			return sb.toString();
		} else {
			return msg;
		}
	}
	
	/**
     * 将字符串中{0} 替换为参数
     * 
     * @param str
     * @param params
     * @return
     */
    public static String formatString(String str, String... params) {
		StringBuffer pre = new StringBuffer();
		Pattern p = Pattern.compile("\\{(\\d+)\\}");
		Matcher m = p.matcher(str);
		while (m.find()) {
		    m.appendReplacement(pre, params[Integer.valueOf(m.group(1))]);
		}
		m.appendTail(pre);
		return pre.toString();
    }

	/**
	 * 过滤特殊字符。<br/>
	 * 
	 * @param old
	 * @return
	 */
	public static String filterSpecialChars(String old) {
		if (isNullOrEmpty(old)) {
			old = old.trim().replaceAll(REG_EXP, "");
		}
		return old;
	}

	/**
	 * 对字符串编码。<br/>
	 * 
	 * @param oldStr
	 *            原有字符串
	 * @param oldCharset
	 *            原有字符集
	 * @param newCharset
	 *            新字符集
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String encode(String oldStr, String oldCharset,
			String newCharset) throws UnsupportedEncodingException {
		String ret = null;
		if (!isNullOrEmpty(oldStr) && !isNullOrEmpty(oldCharset)
				&& !isNullOrEmpty(newCharset)) {
			byte[] arr = oldStr.getBytes(oldCharset);
			ret = new String(arr, newCharset);
		}
		return ret;
	}

	/**
	 * unicode码转换成对应的html显示。<br/>
	 * 
	 * @param oldStr
	 *            原有字符串
	 * @return
	 */
	public static String escapeSprcialChar(String oldStr) {
		String ret = null;
		if (isNotNullOrEmpty(oldStr)
				&& SPECIAL_CHAR_ENTITIES.containsKey(oldStr)) {
			ret = SPECIAL_CHAR_ENTITIES.get(oldStr);
		}
		return ret;
	}

	/**
	 * 随机生成密码
	 * 
	 * @param length
	 * @return add by zhx
	 */
	public static String getRandomPsw(int length) {
		if (length > 0) {
			StringBuilder builder = new StringBuilder();

			for (int i = 0; i < RANDOM_PSW.length; i++) {
				builder.append(RANDOM_PSW[i]);
			}
			return getRandom(length, builder.toString());
		}
		return "";
	}

	/**
	 * 取得随机字符串。<br/>
	 * 
	 * @param length
	 *            字符串长度
	 * @return
	 */
	public static String getRandom(int length) {
		if (length > 0) {
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < RANDOM_BASE.length; i++) {
				builder.append(RANDOM_BASE[i]);
			}
			return getRandom(length, builder.toString());
		}
		return "";
	}

	/**
	 * 取得随机数字字符串。<br/>
	 * 
	 * @param length
	 *            字符串长度
	 * @return
	 */
	public static String getRandomNumber(int length) {
		if (length > 0) {
			String s = RANDOM_BASE[0];
			return getRandom(length, s.substring(0, s.length() - 1));
		}
		return "";
	}

	/**
	 * 取得随机字符串。<br/>
	 * 
	 * @param length
	 *            字符串长度
	 * @param base
	 *            基础字符串
	 * @return
	 */
	public static String getRandom(int length, String base) {
		StringBuilder sb = null;
		if (length > 0 && !isNullOrEmpty(base)) {
			base = base.trim();
			sb = new StringBuilder(length);
			int range = base.length();
			for (int i = 0; i < length; i++) {
				sb.append(base.charAt(RANDOM.nextInt(range)));
			}
		} else {
			sb = new StringBuilder(0);
		}
		return sb.toString();
	}

	/**
	 * BigDecimal转换成String
	 * 
	 * @param old
	 *            原始数值
	 * @param isKeepScale
	 *            是否保持小数经典，不保持时如果小数点后全为0将会被忽略
	 * @return
	 */
	public static String bigDecimalToString(BigDecimal old, boolean isKeepScale) {
		String ret = null;
		if (old != null) {
			String bigDecimalStr = old.toString();
			if (isKeepScale) {
				ret = bigDecimalStr;
			} else {
				if (bigDecimalStr.contains(".")) {
					/* 小数部分 */
					String s1 = bigDecimalStr.substring(
							bigDecimalStr.indexOf(".") + 1,
							bigDecimalStr.length());
					/* 整数部分 */
					String s2 = bigDecimalStr.substring(0,
							bigDecimalStr.indexOf("."));
					String s3 = s1.replace("0", "");
					if (s3.length() > 0) {
						/* 小数部分不全为0。 */
						ret = bigDecimalStr;
					} else {
						/* 小数部分全为0。 */
						ret = s2;
					}
				} else {
					ret = bigDecimalStr;
				}
			}
		}
		return ret;
	}

	/**
	 * URL 编码, Encode默认为UTF-8.
	 */
	public static String urlEncode(String input) {
		return urlEncode(input, DEFAULT_CHARSET);
	}

	/**
	 * URL 编码.
	 */
	public static String urlEncode(String input, String encoding) {
		try {
			return URLEncoder.encode(input, encoding);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException(
					"Unsupported Encoding Exception", e);
		}
	}

	/**
	 * URL 解码, Encode默认为UTF-8.
	 */
	public static String urlDecode(String input) {
		return urlDecode(input, DEFAULT_CHARSET);
	}

	/**
	 * URL 解码.
	 */
	public static String urlDecode(String input, String encoding) {
		try {
			return URLDecoder.decode(input, encoding);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException(
					"Unsupported Encoding Exception", e);
		}
	}

	/**
	 * Html 转码.
	 */
	public static String htmlEscape(String html) {
		return StringEscapeUtils.escapeHtml(html);
	}

	/**
	 * Html 解码.
	 */
	public static String htmlUnescape(String htmlEscaped) {
		return StringEscapeUtils.unescapeHtml(htmlEscaped);
	}

	/**
	 * Xml 转码.
	 */
	public static String xmlEscape(String xml) {
		return StringEscapeUtils.escapeXml(xml);
	}

	/**
	 * Xml 解码.
	 */
	public static String xmlUnescape(String xmlEscaped) {
		return StringEscapeUtils.unescapeXml(xmlEscaped);
	}

	/**
	 * Base64编码, URL安全(将Base64中的URL非法字符如+,/=转为其他字符, 见RFC3548).
	 */
	public static String base64UrlSafeEncode(byte[] input) {
		return Base64.encode(input);
	}

	/**
	 * Hex编码.
	 */
	public static String hexEncode(byte[] input) {
		return String.valueOf(Hex.encodeHex(input));
	}

	/**
	 * Hex解码.
	 */
	public static byte[] hexDecode(String input) {
		try {
			return Hex.decodeHex(input.toCharArray());
		} catch (DecoderException e) {
			throw new IllegalStateException("Hex Decoder exception", e);
		}
	}

	/**
	 * 统计父字符串中子字符串的数量
	 * 
	 * @param parent
	 * @param child
	 * @return
	 */
	public static int countChildString(String parent, String child) {
		if (StringUtil.isNullOrEmpty(parent)) {
			return 0;
		}
		if (StringUtil.isNullOrEmpty(child)) {
			return 0;
		}
		if (parent.contains(child)) {
			Matcher matcher = Pattern.compile(child).matcher(parent);
			int ret = 0;
			while (matcher.find()) {
				ret++;
			}
			return ret;
		} else {
			return 0;
		}
	}

	/**
	 * 除去字符串头尾部的空白，如果字符串是<code>null</code>，则返回空字符串<code>""</code>。
	 * 
	 * <pre>
	 * StringUtil.trimToEmpty(null)          = ""
	 * StringUtil.trimToEmpty("")            = ""
	 * StringUtil.trimToEmpty("     ")       = ""
	 * StringUtil.trimToEmpty("abc")         = "abc"
	 * StringUtil.trimToEmpty("    abc    ") = "abc"
	 * </pre>
	 * 
	 * @author luke_huang
	 * @since 2011-05-25
	 * 
	 * @param str
	 *            要处理的字符串
	 * @return 除去空白的字符串，如果原字串为<code>null</code>或结果字符串为<code>""</code>，则返回
	 *         <code>null</code>
	 */
	public static String trimToEmpty(String str) {
		if (str == null) {
			return "";
		}

		return str.trim();
	}

	/**
	 * 除去字符串头尾部的空白，如果结果字符串是空字符串<code>""</code>，则返回<code>null</code>。
	 * 
	 * <pre>
	 * StringUtil.trimToNull(null)          = null
	 * StringUtil.trimToNull("")            = null
	 * StringUtil.trimToNull("     ")       = null
	 * StringUtil.trimToNull("abc")         = "abc"
	 * StringUtil.trimToNull("    abc    ") = "abc"
	 * </pre>
	 * 
	 * @author luke_huang
	 * @since 2011-05-25
	 * 
	 * @param str
	 *            要处理的字符串
	 * @return 除去空白的字符串，如果原字串为<code>null</code>或结果字符串为<code>""</code>，则返回
	 *         <code>null</code>
	 */
	public static String trimToNull(String str) {
		if (str == null) {
			return null;
		}

		String result = str.trim();

		if (result == null || result.length() == 0) {
			return null;
		}

		return result;
	}

	/**
	 * 将字符串转换成小写。
	 * <p>
	 * 如果字符串是<code>null</code>则返回<code>null</code>。
	 * 
	 * <pre>
	 * StringUtil.toLowerCase(null)  = null
	 * StringUtil.toLowerCase("")    = ""
	 * StringUtil.toLowerCase("aBc") = "abc"
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @author luke_huang
	 * @since 2011-05-25
	 * 
	 * @param str
	 *            要转换的字符串
	 * @return 大写字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String toLowerCase(String str) {
		if (str == null) {
			return null;
		}

		return str.toLowerCase();
	}

	/**
	 * 根据XML的规则，将字符串中的部分字符转换成实体编码。
	 * <p>
	 * 例如：<code>"bread" & "butter"</code>将被转换成
	 * <tt>&amp;quot;bread&amp;quot; &amp;amp;
	 * &amp;quot;butter&amp;quot;</tt>.
	 * </p>
	 * <p>
	 * 只转换4种基本的XML实体：<code>gt</code>、<code>lt</code>、<code>quot</code>和
	 * <code>amp</code>。 不支持DTD或外部实体。
	 * </p>
	 * 
	 * @param str
	 *            要转义的字符串
	 * @return 用实体编码转义的字符串，如果原字串为<code>null</code>，则返回<code>null</code>
	 */
	public static String escapeXml(String str) {
		return escapeEntities(Entities.XML, str);
	}

	/**
	 * 根据XML的规则，将字符串中的部分字符转换成实体编码。
	 * <p>
	 * 例如：<code>"bread" & "butter"</code>将被转换成
	 * <tt>&amp;quot;bread&amp;quot; &amp;amp;
	 * &amp;quot;butter&amp;quot;</tt>.
	 * </p>
	 * <p>
	 * 只转换4种基本的XML实体：<code>gt</code>、<code>lt</code>、<code>quot</code>和
	 * <code>amp</code>。 不支持DTD或外部实体。
	 * </p>
	 * 
	 * @param str
	 *            要转义的字符串
	 * @param out
	 *            输出流
	 * @throws IllegalArgumentException
	 *             如果输出流为<code>null</code>
	 * @throws IOException
	 *             如果输出失败
	 */
	public static void escapeXml(String str, Appendable out) throws IOException {
		escapeEntities(Entities.XML, str, out);
	}

	/**
	 * 根据指定的规则，将字符串中的部分字符转换成实体编码。
	 * 
	 * @param entities
	 *            实体集合
	 * @param str
	 *            要转义的字符串
	 * @return 用实体编码转义的字符串，如果原字串为<code>null</code>，则返回<code>null</code>
	 */
	public static String escapeEntities(Entities entities, String str) {
		if (str == null) {
			return null;
		}

		try {
			StringBuilder out = new StringBuilder(str.length());

			if (escapeEntitiesInternal(entities, str, out)) {
				return out.toString();
			}

			return str;
		} catch (IOException e) {
			return str; // StringBuilder不可能发生这个异常
		}
	}

	/**
	 * 根据指定的规则，将字符串中的部分字符转换成实体编码。
	 * 
	 * @param entities
	 *            实体集合
	 * @param str
	 *            要转义的字符串
	 * @param out
	 *            输出流
	 * @throws IllegalArgumentException
	 *             如果输出流为<code>null</code>
	 * @throws IOException
	 *             如果输出失败
	 */
	public static void escapeEntities(Entities entities, String str,
			Appendable out) throws IOException {
		escapeEntitiesInternal(entities, str, out);
	}

	/**
	 * 将字符串中的部分字符转换成实体编码。
	 * 
	 * @param entities
	 *            实体集合
	 * @param str
	 *            要转义的字符串
	 * @param out
	 *            字符输出流，不能为<code>null</code>
	 * @return 如果字符串没有变化，则返回<code>false</code>
	 * @throws IllegalArgumentException
	 *             如果<code>entities</code>或输出流为 <code>null</code>
	 * @throws IOException
	 *             如果输出失败
	 */
	private static boolean escapeEntitiesInternal(Entities entities,
			String str, Appendable out) throws IOException {
		boolean needToChange = false;

		if (entities == null) {
			throw new IllegalArgumentException("The Entities must not be null");
		}

		if (out == null) {
			throw new IllegalArgumentException(
					"The Appendable must not be null");
		}

		if (str == null) {
			return needToChange;
		}

		for (int i = 0; i < str.length(); ++i) {
			char ch = str.charAt(i);
			String entityName = entities.getEntityName(ch);

			if (entityName == null) {
				out.append(ch);
			} else {
				out.append('&');
				out.append(entityName);
				out.append(';');

				// 设置改变标志
				needToChange = true;
			}
		}

		return needToChange;
	}

	/**
	 * 将字符串中的已定义实体和unicode实体如<code>&amp;#12345;</code>转换成相应的unicode字符。
	 * <p>
	 * 未定义的实体将保留不变。
	 * </p>
	 * 
	 * @param entities
	 *            实体集合，如果为<code>null</code>，则只转换<code>&amp;#number</code> 实体。
	 * @param str
	 *            包含转义字符的字符串
	 * @param out
	 *            字符输出流，不能为<code>null</code>
	 * @return 如果字符串没有变化，则返回<code>false</code>
	 * @throws IllegalArgumentException
	 *             如果输出流为<code>null</code>
	 * @throws IOException
	 *             如果输出失败
	 */
	private static boolean unescapeEntitiesInternal(Entities entities,
			String str, Appendable out) throws IOException {
		boolean needToChange = false;

		if (out == null) {
			throw new IllegalArgumentException(
					"The Appendable must not be null");
		}

		if (str == null) {
			return needToChange;
		}

		for (int i = 0; i < str.length(); ++i) {
			char ch = str.charAt(i);

			if (ch == '&') {
				// 查找&xxxx;
				int semi = str.indexOf(';', i + 1);

				if (semi == -1 || i + 1 >= semi - 1) {
					out.append(ch);
					continue;
				}

				// 如果是&#xxxxx;
				if (str.charAt(i + 1) == '#') {
					int firstCharIndex = i + 2;
					int radix = 10;

					if (firstCharIndex >= semi - 1) {
						out.append(ch);
						out.append('#');
						i++;
						continue;
					}

					char firstChar = str.charAt(firstCharIndex);

					if (firstChar == 'x' || firstChar == 'X') {
						firstCharIndex++;
						radix = 16;

						if (firstCharIndex >= semi - 1) {
							out.append(ch);
							out.append('#');
							i++;
							continue;
						}
					}

					try {
						int entityValue = Integer.parseInt(
								str.substring(firstCharIndex, semi), radix);

						out.append((char) entityValue);

						// 设置改变标志
						needToChange = true;
					} catch (NumberFormatException e) {
						out.append(ch);
						out.append('#');
						i++;
						continue;
					}
				} else {
					String entityName = str.substring(i + 1, semi);
					int entityValue = -1;

					if (entities != null) {
						entityValue = entities.getEntityValue(entityName);
					}

					if (entityValue == -1) {
						out.append('&');
						out.append(entityName);
						out.append(';');
					} else {
						out.append((char) entityValue);

						// 设置改变标志
						needToChange = true;
					}
				}

				i = semi;
			} else {
				out.append(ch);
			}
		}

		return needToChange;
	}

	/**
	 * 按XML的规则对字符串进行反向转义，支持unicode实体如<code>&amp;#12345;</code>。
	 * <p>
	 * 例如："&amp;lt;Fran&amp;ccedil;ais&amp;gt;"将被转换成"&lt;Fran&ccedil;ais&gt;"
	 * </p>
	 * <p>
	 * 如果实体不能被识别，它将被保留不变。
	 * </p>
	 * 
	 * @param str
	 *            不包含转义字符的字符串
	 * @return 恢复成未转义的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String unescapeXml(String str) {
		return unescapeEntities(Entities.XML, str);
	}

	/**
	 * 按XML的规则对字符串进行反向转义，支持unicode实体如<code>&amp;#12345;</code>。
	 * <p>
	 * 例如："&amp;lt;Fran&amp;ccedil;ais&amp;gt;"将被转换成"&lt;Fran&ccedil;ais&gt;"
	 * </p>
	 * <p>
	 * 如果实体不能被识别，它将被保留不变。
	 * </p>
	 * 
	 * @param str
	 *            不包含转义字符的字符串
	 * @param out
	 *            输出流
	 * @throws IllegalArgumentException
	 *             如果输出流为<code>null</code>
	 * @throws IOException
	 *             如果输出失败
	 */
	public static void unescapeXml(String str, Appendable out)
			throws IOException {
		unescapeEntities(Entities.XML, str, out);
	}

	/**
	 * 按指定的规则对字符串进行反向转义。
	 * 
	 * @param entities
	 *            实体集合
	 * @param str
	 *            不包含转义字符的字符串
	 * @return 恢复成未转义的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String unescapeEntities(Entities entities, String str) {
		if (str == null) {
			return null;
		}

		try {
			StringBuilder out = new StringBuilder(str.length());

			if (unescapeEntitiesInternal(entities, str, out)) {
				return out.toString();
			}

			return str;
		} catch (IOException e) {
			return str; // StringBuilder不可能发生这个异常
		}
	}

	/**
	 * 按指定的规则对字符串进行反向转义。
	 * <p>
	 * 如果实体不能被识别，它将被保留不变。
	 * </p>
	 * 
	 * @param entities
	 *            实体集合
	 * @param str
	 *            不包含转义字符的字符串
	 * @param out
	 *            输出流
	 * @throws IllegalArgumentException
	 *             如果输出流为<code>null</code>
	 * @throws IOException
	 *             如果输出失败
	 */
	public static void unescapeEntities(Entities entities, String str,
			Appendable out) throws IOException {
		unescapeEntitiesInternal(entities, str, out);
	}

	/**
	 * 根据HTML的规则，将字符串中的部分字符转换成实体编码。
	 * <p>
	 * 例如：<code>"bread" & "butter"</code>将被转换成
	 * <tt>&amp;quot;bread&amp;quot; &amp;amp;
	 * &amp;quot;butter&amp;quot;</tt>.
	 * </p>
	 * <p>
	 * 支持所有HTML 4.0 entities。
	 * </p>
	 * 
	 * @param str
	 *            要转义的字符串
	 * @return 用实体编码转义的字符串，如果原字串为<code>null</code>，则返回<code>null</code>
	 * @see <a
	 *      href="http://hotwired.lycos.com/webmonkey/reference/special_characters/">ISO
	 *      Entities</a>
	 * @see <a href="http://www.w3.org/TR/REC-html32#latin1">HTML 3.2 Character
	 *      Entities for ISO Latin-1</a>
	 * @see <a href="http://www.w3.org/TR/REC-html40/sgml/entities.html">HTML
	 *      4.0 Character entity references</a>
	 * @see <a href="http://www.w3.org/TR/html401/charset.html#h-5.3">HTML 4.01
	 *      Character References</a>
	 * @see <a
	 *      href="http://www.w3.org/TR/html401/charset.html#code-position">HTML
	 *      4.01 Code positions</a>
	 */
	public static String escapeHtml(String str) {
		return escapeEntities(Entities.HTML40_MODIFIED, str);
	}

	/**
	 * 根据HTML的规则，将字符串中的部分字符转换成实体编码。
	 * <p>
	 * 例如：<code>"bread" & "butter"</code>将被转换成
	 * <tt>&amp;quot;bread&amp;quot; &amp;amp;
	 * &amp;quot;butter&amp;quot;</tt>.
	 * </p>
	 * <p>
	 * 支持所有HTML 4.0 entities。
	 * </p>
	 * 
	 * @param str
	 *            要转义的字符串
	 * @param out
	 *            输出流
	 * @throws IllegalArgumentException
	 *             如果输出流为<code>null</code>
	 * @throws IOException
	 *             如果输出失败
	 * @see <a
	 *      href="http://hotwired.lycos.com/webmonkey/reference/special_characters/">ISO
	 *      Entities</a>
	 * @see <a href="http://www.w3.org/TR/REC-html32#latin1">HTML 3.2 Character
	 *      Entities for ISO Latin-1</a>
	 * @see <a href="http://www.w3.org/TR/REC-html40/sgml/entities.html">HTML
	 *      4.0 Character entity references</a>
	 * @see <a href="http://www.w3.org/TR/html401/charset.html#h-5.3">HTML 4.01
	 *      Character References</a>
	 * @see <a
	 *      href="http://www.w3.org/TR/html401/charset.html#code-position">HTML
	 *      4.01 Code positions</a>
	 */
	public static void escapeHtml(String str, Appendable out)
			throws IOException {
		escapeEntities(Entities.HTML40_MODIFIED, str, out);
	}

	/**
	 * 按Java的规则对字符串进行转义。
	 * <p>
	 * 将双引号和控制字符转换成<code>'\\'</code>开头的形式，例如tab制表符将被转换成<code>\t</code>。
	 * </p>
	 * <p>
	 * Java和JavaScript字符串的唯一差别是，JavaScript必须对单引号进行转义，而Java不需要。
	 * </p>
	 * <p>
	 * 例如：字符串：<code>He didn't say, "Stop!"</code>被转换成
	 * <code>He didn't say, \"Stop!\"</code>
	 * </p>
	 * 
	 * @param str
	 *            要转义的字符串
	 * @return 转义后的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String escapeJava(String str) {
		return escapeJavaStyleString(str, false, false);
	}

	/**
	 * 按Java的规则对字符串进行转义。
	 * <p>
	 * 将双引号和控制字符转换成<code>'\\'</code>开头的形式，例如tab制表符将被转换成<code>\t</code>。
	 * </p>
	 * <p>
	 * Java和JavaScript字符串的唯一差别是，JavaScript必须对单引号进行转义，而Java不需要。
	 * </p>
	 * <p>
	 * 例如：字符串：<code>He didn't say, "Stop!"</code>被转换成
	 * <code>He didn't say, \"Stop!\"</code>
	 * </p>
	 * 
	 * @param str
	 *            要转义的字符串
	 * @param strict
	 *            是否以严格的方式编码字符串
	 * @return 转义后的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String escapeJava(String str, boolean strict) {
		return escapeJavaStyleString(str, false, strict);
	}

	/**
	 * 按Java的规则对字符串进行转义。
	 * <p>
	 * 将双引号和控制字符转换成<code>'\\'</code>开头的形式，例如tab制表符将被转换成<code>\t</code>。
	 * </p>
	 * <p>
	 * Java和JavaScript字符串的唯一差别是，JavaScript必须对单引号进行转义，而Java不需要。
	 * </p>
	 * <p>
	 * 例如：字符串：<code>He didn't say, "Stop!"</code>被转换成
	 * <code>He didn't say, \"Stop!\"</code>
	 * </p>
	 * 
	 * @param str
	 *            要转义的字符串
	 * @param out
	 *            输出流
	 * @throws IllegalArgumentException
	 *             如果输出流为<code>null</code>
	 * @throws IOException
	 *             如果输出失败
	 */
	public static void escapeJava(String str, Appendable out)
			throws IOException {
		escapeJavaStyleString(str, false, out, false);
	}

	/**
	 * 按Java的规则对字符串进行转义。
	 * <p>
	 * 将双引号和控制字符转换成<code>'\\'</code>开头的形式，例如tab制表符将被转换成<code>\t</code>。
	 * </p>
	 * <p>
	 * Java和JavaScript字符串的唯一差别是，JavaScript必须对单引号进行转义，而Java不需要。
	 * </p>
	 * <p>
	 * 例如：字符串：<code>He didn't say, "Stop!"</code>被转换成
	 * <code>He didn't say, \"Stop!\"</code>
	 * </p>
	 * 
	 * @param str
	 *            要转义的字符串
	 * @param out
	 *            输出流
	 * @param strict
	 *            是否以严格的方式编码字符串
	 * @throws IllegalArgumentException
	 *             如果输出流为<code>null</code>
	 * @throws IOException
	 *             如果输出失败
	 */
	public static void escapeJava(String str, Appendable out, boolean strict)
			throws IOException {
		escapeJavaStyleString(str, false, out, strict);
	}

	/**
	 * 按JavaScript的规则对字符串进行转义。
	 * <p>
	 * 将双引号、单引号和控制字符转换成<code>'\\'</code>开头的形式，例如tab制表符将被转换成<code>\t</code>。
	 * </p>
	 * <p>
	 * Java和JavaScript字符串的唯一差别是，JavaScript必须对单引号进行转义，而Java不需要。
	 * </p>
	 * <p>
	 * 例如：字符串：<code>He didn't say, "Stop!"</code>被转换成
	 * <code>He didn\'t say, \"Stop!\"</code>
	 * </p>
	 * 
	 * @param str
	 *            要转义的字符串
	 * @return 转义后的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String escapeJavaScript(String str) {
		return escapeJavaStyleString(str, true, false);
	}

	/**
	 * 按JavaScript的规则对字符串进行转义。
	 * <p>
	 * 将双引号、单引号和控制字符转换成<code>'\\'</code>开头的形式，例如tab制表符将被转换成<code>\t</code>。
	 * </p>
	 * <p>
	 * Java和JavaScript字符串的唯一差别是，JavaScript必须对单引号进行转义，而Java不需要。
	 * </p>
	 * <p>
	 * 例如：字符串：<code>He didn't say, "Stop!"</code>被转换成
	 * <code>He didn\'t say, \"Stop!\"</code>
	 * </p>
	 * 
	 * @param str
	 *            要转义的字符串
	 * @param strict
	 *            是否以严格的方式编码字符串
	 * @return 转义后的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String escapeJavaScript(String str, boolean strict) {
		return escapeJavaStyleString(str, true, strict);
	}

	/**
	 * 按JavaScript的规则对字符串进行转义。
	 * <p>
	 * 将双引号、单引号和控制字符转换成<code>'\\'</code>开头的形式，例如tab制表符将被转换成<code>\t</code>。
	 * </p>
	 * <p>
	 * Java和JavaScript字符串的唯一差别是，JavaScript必须对单引号进行转义，而Java不需要。
	 * </p>
	 * <p>
	 * 例如：字符串：<code>He didn't say, "Stop!"</code>被转换成
	 * <code>He didn\'t say, \"Stop!\"</code>
	 * </p>
	 * 
	 * @param str
	 *            要转义的字符串
	 * @param out
	 *            输出流
	 * @throws IllegalArgumentException
	 *             如果输出流为<code>null</code>
	 * @throws IOException
	 *             如果输出失败
	 */
	public static void escapeJavaScript(String str, Appendable out)
			throws IOException {
		escapeJavaStyleString(str, true, out, false);
	}

	/**
	 * 按JavaScript的规则对字符串进行转义。
	 * <p>
	 * 将双引号、单引号和控制字符转换成<code>'\\'</code>开头的形式，例如tab制表符将被转换成<code>\t</code>。
	 * </p>
	 * <p>
	 * Java和JavaScript字符串的唯一差别是，JavaScript必须对单引号进行转义，而Java不需要。
	 * </p>
	 * <p>
	 * 例如：字符串：<code>He didn't say, "Stop!"</code>被转换成
	 * <code>He didn\'t say, \"Stop!\"</code>
	 * </p>
	 * 
	 * @param str
	 *            要转义的字符串
	 * @param out
	 *            输出流
	 * @param strict
	 *            是否以严格的方式编码字符串
	 * @throws IllegalArgumentException
	 *             如果输出流为<code>null</code>
	 * @throws IOException
	 *             如果输出失败
	 */
	public static void escapeJavaScript(String str, Appendable out,
			boolean strict) throws IOException {
		escapeJavaStyleString(str, true, out, strict);
	}

	/**
	 * 按Java或JavaScript的规则对字符串进行转义。
	 * 
	 * @param str
	 *            要转义的字符串
	 * @param javascript
	 *            是否对单引号和slash进行转义
	 * @param strict
	 *            是否以严格的方式编码字符串
	 * @return 转义后的字符串
	 */
	private static String escapeJavaStyleString(String str, boolean javascript,
			boolean strict) {
		if (str == null) {
			return null;
		}

		try {
			StringBuilder out = new StringBuilder(str.length() * 2);

			if (escapeJavaStyleString(str, javascript, out, strict)) {
				return out.toString();
			}

			return str;
		} catch (IOException e) {
			return str; // StringBuilder不可能发生这个异常
		}
	}

	/**
	 * 按Java或JavaScript的规则对字符串进行转义。
	 * 
	 * @param str
	 *            要转义的字符串
	 * @param javascript
	 *            是否对单引号和slash进行转义
	 * @param out
	 *            输出流
	 * @param strict
	 *            是否以严格的方式编码字符串
	 * @return 如果字符串没有变化，则返回<code>false</code>
	 */
	private static boolean escapeJavaStyleString(String str,
			boolean javascript, Appendable out, boolean strict)
			throws IOException {
		boolean needToChange = false;

		if (out == null) {
			throw new IllegalArgumentException(
					"The Appendable must not be null");
		}

		if (str == null) {
			return needToChange;
		}

		int length = str.length();

		for (int i = 0; i < length; i++) {
			char ch = str.charAt(i);

			if (ch < 32) {
				switch (ch) {
				case '\b':
					out.append('\\');
					out.append('b');
					break;

				case '\n':
					out.append('\\');
					out.append('n');
					break;

				case '\t':
					out.append('\\');
					out.append('t');
					break;

				case '\f':
					out.append('\\');
					out.append('f');
					break;

				case '\r':
					out.append('\\');
					out.append('r');
					break;

				default:

					if (ch > 0xf) {
						out.append("\\u00"
								+ Integer.toHexString(ch).toUpperCase());
					} else {
						out.append("\\u000"
								+ Integer.toHexString(ch).toUpperCase());
					}

					break;
				}

				// 设置改变标志
				needToChange = true;
			} else if (strict && ch > 0xff) {
				if (ch > 0xfff) {
					out.append("\\u").append(
							Integer.toHexString(ch).toUpperCase());
				} else {
					out.append("\\u0").append(
							Integer.toHexString(ch).toUpperCase());
				}

				// 设置改变标志
				needToChange = true;
			} else {
				switch (ch) {
				case '\'':
				case '/': // 注意：对于javascript，对/进行escape是重要的安全措施。

					if (javascript) {
						out.append('\\');

						// 设置改变标志
						needToChange = true;
					}

					out.append(ch);

					break;

				case '"':
					out.append('\\');
					out.append('"');

					// 设置改变标志
					needToChange = true;
					break;

				case '\\':
					out.append('\\');
					out.append('\\');

					// 设置改变标志
					needToChange = true;
					break;

				default:
					out.append(ch);
					break;
				}
			}
		}

		return needToChange;
	}

	/**
	 * 按Java的规则对字符串进行反向转义。
	 * <p>
	 * <code>'\\'</code>开头的形式转换成相应的字符，例如<code>\t</code>将被转换成tab制表符
	 * </p>
	 * <p>
	 * 如果转义符不能被识别，它将被保留不变。
	 * </p>
	 * 
	 * @param str
	 *            不包含转义字符的字符串
	 * @return 恢复成未转义的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String unescapeJava(String str) {
		return unescapeJavaStyleString(str);
	}

	/**
	 * 按Java的规则对字符串进行反向转义。
	 * <p>
	 * <code>'\\'</code>开头的形式转换成相应的字符，例如<code>\t</code>将被转换成tab制表符
	 * </p>
	 * <p>
	 * 如果转义符不能被识别，它将被保留不变。
	 * </p>
	 * 
	 * @param str
	 *            包含转义字符的字符串
	 * @param out
	 *            输出流
	 * @throws IllegalArgumentException
	 *             如果输出流为<code>null</code>
	 * @throws IOException
	 *             如果输出失败
	 */
	public static void unescapeJava(String str, Appendable out)
			throws IOException {
		unescapeJavaStyleString(str, out);
	}

	/**
	 * 按JavaScript的规则对字符串进行反向转义。
	 * <p>
	 * <code>'\\'</code>开头的形式转换成相应的字符，例如<code>\t</code>将被转换成tab制表符
	 * </p>
	 * <p>
	 * 如果转义符不能被识别，它将被保留不变。
	 * </p>
	 * 
	 * @param str
	 *            包含转义字符的字符串
	 * @return 恢复成未转义的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String unescapeJavaScript(String str) {
		return unescapeJavaStyleString(str);
	}

	/**
	 * 按Java的规则对字符串进行反向转义。
	 * <p>
	 * <code>'\\'</code>开头的形式转换成相应的字符，例如<code>\t</code>将被转换成tab制表符
	 * </p>
	 * <p>
	 * 如果转义符不能被识别，它将被保留不变。
	 * </p>
	 * 
	 * @param str
	 *            包含转义字符的字符串
	 * @param out
	 *            输出流
	 * @throws IllegalArgumentException
	 *             如果输出流为<code>null</code>
	 * @throws IOException
	 *             如果输出失败
	 */
	public static void unescapeJavaScript(String str, Appendable out)
			throws IOException {
		unescapeJavaStyleString(str, out);
	}

	/**
	 * 按Java的规则对字符串进行反向转义。
	 * <p>
	 * <code>'\\'</code>开头的形式转换成相应的字符，例如<code>\t</code>将被转换成tab制表符
	 * </p>
	 * <p>
	 * 如果转义符不能被识别，它将被保留不变。
	 * </p>
	 * 
	 * @param str
	 *            包含转义字符的字符串
	 * @return 不包含转义字符的字符串
	 */
	private static String unescapeJavaStyleString(String str) {
		if (str == null) {
			return null;
		}

		try {
			StringBuilder out = new StringBuilder(str.length());

			if (unescapeJavaStyleString(str, out)) {
				return out.toString();
			}

			return str;
		} catch (IOException e) {
			return str; // StringBuilder不可能发生这个异常
		}
	}

	/**
	 * 按Java的规则对字符串进行反向转义。
	 * <p>
	 * <code>'\\'</code>开头的形式转换成相应的字符，例如<code>\t</code>将被转换成tab制表符
	 * </p>
	 * <p>
	 * 如果转义符不能被识别，它将被保留不变。
	 * </p>
	 * 
	 * @param str
	 *            包含转义字符的字符串
	 * @param out
	 *            输出流
	 * @return 如果字符串没有变化，则返回<code>false</code>
	 * @throws IllegalArgumentException
	 *             如果输出流为<code>null</code>
	 * @throws IOException
	 *             如果输出失败
	 */
	private static boolean unescapeJavaStyleString(String str, Appendable out)
			throws IOException {
		boolean needToChange = false;

		if (out == null) {
			throw new IllegalArgumentException(
					"The Appendable must not be null");
		}

		if (str == null) {
			return needToChange;
		}

		int length = str.length();
		StringBuilder unicode = new StringBuilder(4);
		boolean hadSlash = false;
		boolean inUnicode = false;

		for (int i = 0; i < length; i++) {
			char ch = str.charAt(i);

			if (inUnicode) {
				unicode.append(ch);

				if (unicode.length() == 4) {
					String unicodeStr = unicode.toString();

					try {
						int value = Integer.parseInt(unicodeStr, 16);

						out.append((char) value);
						unicode.setLength(0);
						inUnicode = false;
						hadSlash = false;

						// 设置改变标志
						needToChange = true;
					} catch (NumberFormatException e) {
						out.append("\\u" + unicodeStr);
					}
				}

				continue;
			}

			if (hadSlash) {
				hadSlash = false;

				switch (ch) {
				case '\\':
					out.append('\\');

					// 设置改变标志
					needToChange = true;
					break;

				case '\'':
					out.append('\'');

					// 设置改变标志
					needToChange = true;
					break;

				case '\"':
					out.append('"');

					// 设置改变标志
					needToChange = true;
					break;

				case 'r':
					out.append('\r');

					// 设置改变标志
					needToChange = true;
					break;

				case 'f':
					out.append('\f');

					// 设置改变标志
					needToChange = true;
					break;

				case 't':
					out.append('\t');

					// 设置改变标志
					needToChange = true;
					break;

				case 'n':
					out.append('\n');

					// 设置改变标志
					needToChange = true;
					break;

				case 'b':
					out.append('\b');

					// 设置改变标志
					needToChange = true;
					break;

				case 'u': {
					inUnicode = true;
					break;
				}

				default:
					out.append(ch);
					break;
				}

				continue;
			} else if (ch == '\\') {
				hadSlash = true;
				continue;
			}

			out.append(ch);
		}

		if (hadSlash) {
			out.append('\\');
		}

		return needToChange;
	}

	// ==========================================================================
	// SQL语句。
	// ==========================================================================

	/**
	 * 按SQL语句的规则对字符串进行转义。
	 * <p>
	 * 例如：
	 * 
	 * <pre>
	 * statement.executeQuery(&quot;SELECT * FROM MOVIES WHERE TITLE='&quot;
	 * 		+ StringEscapeUtil.escapeSql(&quot;McHale's Navy&quot;) + &quot;'&quot;);
	 * </pre>
	 * 
	 * </p>
	 * <p>
	 * 目前，此方法只将单引号转换成两个单引号：<code>"McHale's Navy"</code>转换成<code>"McHale''s
	 * Navy"</code>。不处理字符串中包含的<code>%</code>和<code>_</code>字符。
	 * </p>
	 * 
	 * @param str
	 *            要转义的字符串
	 * @return 转义后的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
	 * @see <a href="http://www.jguru.com/faq/view.jsp?EID=8881">faq</a>
	 */
	public static String escapeSql(String str) {
		return StringUtil.replace(str, "'", "''");
	}

	/**
	 * 将指定字符串编码成<code>application/x-www-form-urlencoded</code>格式。
	 * <p>
	 * 除了RFC2396中的<code>unreserved</code>字符之外的所有字符，都将被转换成URL编码<code>%xx</code>。
	 * 根据RFC2396，<code>unreserved</code>的定义如下：
	 * 
	 * <pre>
	 * &lt;![CDATA
	 *  unreserved  = alphanum | mark
	 *  alphanum    = 大小写英文字母 | 数字
	 *  mark        = &quot;-&quot; | &quot;_&quot; | &quot;.&quot; | &quot;!&quot; | &quot;&tilde;&quot; | &quot;*&quot; | &quot;'&quot; | &quot;(&quot; | &quot;)&quot;
	 * ]]&gt;
	 * </pre>
	 * 
	 * </p>
	 * <p>
	 * 警告：该方法使用当前线程默认的字符编码来编码URL，因此该方法在不同的上下文中可能会产生不同的结果。
	 * </p>
	 * 
	 * @param str
	 *            要编码的字符串，可以是<code>null</code>
	 * @return URL编码后的字符串
	 */
	public static String escapeURL(String str) {
		try {
			return escapeURLInternal(str, null, true);
		} catch (UnsupportedEncodingException e) {
			return str; // 不可能发生这个异常
		}
	}

	/**
	 * 将指定字符串编码成<code>application/x-www-form-urlencoded</code>格式。
	 * <p>
	 * 除了RFC2396中的<code>unreserved</code>字符之外的所有字符，都将被转换成URL编码<code>%xx</code>。
	 * 根据RFC2396，<code>unreserved</code>的定义如下：
	 * 
	 * <pre>
	 * &lt;![CDATA
	 *  unreserved  = alphanum | mark
	 *  alphanum    = 大小写英文字母 | 数字
	 *  mark        = &quot;-&quot; | &quot;_&quot; | &quot;.&quot; | &quot;!&quot; | &quot;&tilde;&quot; | &quot;*&quot; | &quot;'&quot; | &quot;(&quot; | &quot;)&quot;
	 * ]]&gt;
	 * </pre>
	 * 
	 * </p>
	 * <p>
	 * 该方法使用指定的字符编码来编码URL。
	 * </p>
	 * 
	 * @param str
	 *            要编码的字符串，可以是<code>null</code>
	 * @param encoding
	 *            输出字符编码，如果为<code>null</code>，则使用系统默认编码
	 * @return URL编码后的字符串
	 * @throws UnsupportedEncodingException
	 *             如果指定的<code>encoding</code>为非法的
	 */
	public static String escapeURL(String str, String encoding)
			throws UnsupportedEncodingException {
		return escapeURLInternal(str, encoding, true);
	}

	/**
	 * 将指定字符串编码成<code>application/x-www-form-urlencoded</code>格式。
	 * 
	 * @param str
	 *            要编码的字符串，可以是<code>null</code>
	 * @param encoding
	 *            输出字符编码，如果为<code>null</code>，则使用系统默认编码
	 * @param strict
	 *            是否以严格的方式编码URL
	 * @return URL编码后的字符串
	 * @throws UnsupportedEncodingException
	 *             如果指定的<code>encoding</code>为非法的
	 */
	private static String escapeURLInternal(String str, String encoding,
			boolean strict) throws UnsupportedEncodingException {
		if (str == null) {
			return null;
		}

		try {
			StringBuilder out = new StringBuilder(64);

			if (escapeURLInternal(str, encoding, out, strict)) {
				return out.toString();
			}

			return str;
		} catch (UnsupportedEncodingException e) {
			throw e;
		} catch (IOException e) {
			return str; // StringBuilder不可能发生这个异常
		}
	}

	/**
	 * 将指定字符串编码成<code>application/x-www-form-urlencoded</code>格式。
	 * 
	 * @param str
	 *            要编码的字符串，可以是<code>null</code>
	 * @param encoding
	 *            输出字符编码，如果为<code>null</code>，则使用系统默认编码
	 * @param strict
	 *            是否以严格的方式编码URL
	 * @param out
	 *            输出流
	 * @return 如果字符串被改变，则返回<code>true</code>
	 * @throws IOException
	 *             如果输出到<code>out</code>失败
	 * @throws UnsupportedEncodingException
	 *             如果指定的<code>encoding</code>为非法的
	 * @throws IllegalArgumentException
	 *             <code>out</code>为<code>null</code>
	 */
	private static boolean escapeURLInternal(String str, String encoding,
			Appendable out, boolean strict) throws IOException {
		if (encoding == null) {
			encoding = LocaleUtil.getContext().getCharset().name();
		}

		boolean needToChange = false;

		if (out == null) {
			throw new IllegalArgumentException(
					"The Appendable must not be null");
		}

		if (str == null) {
			return needToChange;
		}

		char[] charArray = str.toCharArray();
		int length = charArray.length;

		for (int i = 0; i < length; i++) {
			int ch = charArray[i];

			if (isSafeCharacter(ch, strict)) {
				// “安全”的字符，直接输出
				out.append((char) ch);
			} else if (ch == ' ') {
				// 特殊情况：空格（0x20）转换成'+'
				out.append('+');

				// 设置改变标志
				needToChange = true;
			} else {
				// 对ch进行URL编码。
				// 首先按指定encoding取得该字符的字节码。
				byte[] bytes = String.valueOf((char) ch).getBytes(encoding);

				for (byte toEscape : bytes) {
					out.append('%');

					int low = toEscape & 0x0F;
					int high = (toEscape & 0xF0) >> 4;

					out.append(HEXADECIMAL[high]);
					out.append(HEXADECIMAL[low]);
				}

				// 设置改变标志
				needToChange = true;
			}
		}

		return needToChange;
	}

	/**
     * 解码<code>application/x-www-form-urlencoded</code>格式的字符串。
     * <p>
     * 警告：该方法使用系统字符编码来解码URL，因此该方法在不同的系统中可能会产生不同的结果。
     * </p>
     * 
     * @param str 要解码的字符串，可以是<code>null</code>
     * @return URL解码后的字符串
     */
    public static String unescapeURL(String str) {
        try {
            return unescapeURLInternal(str, null);
        } catch (UnsupportedEncodingException e) {
            return str; // 不可能发生这个异常
        }
    }

    /**
     * 解码<code>application/x-www-form-urlencoded</code>格式的字符串。
     * 
     * @param str 要解码的字符串，可以是<code>null</code>
     * @param encoding 输出字符编码，如果为<code>null</code>，则使用系统默认编码
     * @return URL解码后的字符串
     * @throws UnsupportedEncodingException 如果指定的<code>encoding</code>为非法的
     */
    public static String unescapeURL(String str, String encoding) throws UnsupportedEncodingException {
        return unescapeURLInternal(str, encoding);
    }

    /**
     * 解码<code>application/x-www-form-urlencoded</code>格式的字符串。
     * 
     * @param str 要解码的字符串，可以是<code>null</code>
     * @param encoding 输出字符编码，如果为<code>null</code>，则使用系统默认编码
     * @param out 输出流
     * @throws IOException 如果输出到<code>out</code>失败
     * @throws UnsupportedEncodingException 如果指定的<code>encoding</code>为非法的
     * @throws IllegalArgumentException <code>out</code>为<code>null</code>
     */
    public static void unescapeURL(String str, String encoding, Appendable out) throws IOException {
        unescapeURLInternal(str, encoding, out);
    }

    /**
     * 解码<code>application/x-www-form-urlencoded</code>格式的字符串。
     * 
     * @param str 要解码的字符串，可以是<code>null</code>
     * @param encoding 输出字符编码，如果为<code>null</code>，则使用系统默认编码
     * @return URL解码后的字符串
     * @throws UnsupportedEncodingException 如果指定的<code>encoding</code>为非法的
     */
    private static String unescapeURLInternal(String str, String encoding) throws UnsupportedEncodingException {
        if (str == null) {
            return null;
        }

        try {
            StringBuilder out = new StringBuilder(str.length());

            if (unescapeURLInternal(str, encoding, out)) {
                return out.toString();
            }

            return str;
        } catch (UnsupportedEncodingException e) {
            throw e;
        } catch (IOException e) {
            return str; // StringBuilder不可能发生这个异常
        }
    }

    /**
     * 解码<code>application/x-www-form-urlencoded</code>格式的字符串。
     * 
     * @param str 要解码的字符串，可以是<code>null</code>
     * @param encoding 输出字符编码，如果为<code>null</code>，则使用系统默认编码
     * @param out 输出流
     * @return 如果字符串被改变，则返回<code>true</code>
     * @throws IOException 如果输出到<code>out</code>失败
     * @throws UnsupportedEncodingException 如果指定的<code>encoding</code>为非法的
     * @throws IllegalArgumentException <code>out</code>为<code>null</code>
     */
    private static boolean unescapeURLInternal(String str, String encoding, Appendable out) throws IOException {
        if (encoding == null) {
            encoding = LocaleUtil.getContext().getCharset().name();
        }

        boolean needToChange = false;

        if (out == null) {
            throw new IllegalArgumentException("The Appendable must not be null");
        }

        byte[] buffer = null;
        int pos = 0;
        int startIndex = 0;

        char[] charArray = str.toCharArray();
        int length = charArray.length;

        for (int i = 0; i < length; i++) {
            int ch = charArray[i];

            if (ch < 256) {
                // 读取连续的字节，并将它按指定编码转换成字符。
                if (buffer == null) {
                    buffer = new byte[length - i]; // 最长只需要length - i
                }

                if (pos == 0) {
                    startIndex = i;
                }

                switch (ch) {
                    case '+':

                        // 将'+'转换成' '
                        buffer[pos++] = ' ';

                        // 设置改变标志
                        needToChange = true;
                        break;

                    case '%':

                        if (i + 2 < length) {
                            try {
                                byte b = (byte) Integer.parseInt(str.substring(i + 1, i + 3), 16);

                                buffer[pos++] = b;
                                i += 2;

                                // 设置改变标志
                                needToChange = true;
                            } catch (NumberFormatException e) {
                                // 如果%xx不是合法的16进制数，则原样输出
                                buffer[pos++] = (byte) ch;
                            }
                        } else {
                            buffer[pos++] = (byte) ch;
                        }

                        break;

                    default:

                        // 写到bytes中，到时一起输出。
                        buffer[pos++] = (byte) ch;
                        break;
                }
            } else {
                // 先将buffer中的字节串转换成字符串。
                if (pos > 0) {
                    String s = new String(buffer, 0, pos, encoding);

                    out.append(s);

                    if (!needToChange && !s.equals(new String(charArray, startIndex, pos))) {
                        needToChange = true;
                    }

                    pos = 0;
                }

                // 如果ch是ISO-8859-1以外的字符，直接输出即可
                out.append((char) ch);
            }
        }

        // 先将buffer中的字节串转换成字符串。
        if (pos > 0) {
            String s = new String(buffer, 0, pos, encoding);

            out.append(s);

            if (!needToChange && !s.equals(new String(charArray, startIndex, pos))) {
                needToChange = true;
            }

            pos = 0;
        }

        return needToChange;
    }
    
	/**
	 * 判断指定字符是否是“安全”的，这个字符将不被转换成URL编码。
	 * 
	 * @param ch
	 *            要判断的字符
	 * @param strict
	 *            是否以严格的方式编码
	 * @return 如果是“安全”的，则返回<code>true</code>
	 */
	private static boolean isSafeCharacter(int ch, boolean strict) {
		if (strict) {
			return UNRESERVED.get(ch);
		} else {
			return ch > ' ' && !RESERVED.get(ch)
					&& !Character.isWhitespace((char) ch);
		}
	}

	/**
	 * 根据原始字符串得到一组(4个)唯一的随机字符串。<br/>
	 * 
	 * @param old
	 *            原始字符串
	 * @return
	 */
	public static String[] uniqueRandomStr(String old) {
		if (isNullOrEmpty(old)) {
			return null;
		}
		String hex = encodeAsHex(old);
		int hexLen = hex.length();
		int subHexLen = hexLen / 8;
		String[] shortString = new String[4];

		for (int i = 0; i < subHexLen; i++) {
			String outChars = "";
			int j = i + 1;
			String subHex = hex.substring(i * 8, j * 8);
			long idx = Long.valueOf("3FFFFFFF", 16) & Long.valueOf(subHex, 16);

			for (int k = 0; k < 6; k++) {
				int index = (int) (Long.valueOf("0000003D", 16) & idx);
				outChars += CHARS[index];
				idx = idx >> 5;
			}
			shortString[i] = outChars;
		}
		return shortString;
	}

	/**
	 * 得到一组(4个)唯一的随机字符串。<br/>
	 * 
	 * @return
	 */
	public static String uniqueRandomStr() {

		return uniqueRandomStr(shortUUIDString())[0];
	}

	/**
	 * 判断字符串是否是浮点数格式。<br/>
	 * 
	 * @param num
	 *            字符串
	 * @param type
	 *            浮点类型，"0+":非负浮点数 "+":正浮点数 "-0":非正浮点数 "-":负浮点数 "":浮点数
	 * @return
	 */
	public static boolean isFloatNumeric(String num, String type) {
		if (isNullOrEmpty(num)) {
			return false;
		}
		num = trim(num);

		String eL = "";
		if (type.equals("0+")) {
			eL = "^//d+(//.//d+)?$";// 非负浮点数
		} else if (type.equals("+")) {
			eL = "^((//d+//.//d*[1-9]//d*)|(//d*[1-9]//d*//.//d+)|(//d*[1-9]//d*))$";// 正浮点数
		} else if (type.equals("-0")) {
			eL = "^((-//d+(//.//d+)?)|(0+(//.0+)?))$";// 非正浮点数
		} else if (type.equals("-")) {
			eL = "^(-((//d+//.//d*[1-9]//d*)|(//d*[1-9]//d*//.//d+)|(//d*[1-9]//d*)))$";// 负浮点数
		} else {
			eL = "^(-?//d+)(//.//d+)?$";// 浮点数
		}
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(num);
		return m.matches();
	}

	/**
	 * 返回限定长度的字符串。<br/>
	 * 
	 * @param str
	 *            原始字符串
	 * @param maxLength
	 *            最大长度
	 * @return
	 */
	public static String getString(String str, int maxLength) {
		if (isNotNullOrEmpty(str) && str.length() > maxLength) {
			return str.substring(0, maxLength);
		}
		return str;
	}

	/**
	 * 返回限定长度的字符串。<br/>
	 * 
	 * @param str
	 *            原始字符串
	 * @param maxLength
	 *            最大长度
	 * @return
	 */
	public static String getString(Object obj, int maxLength) {
		if (null != obj) {
			return getString(obj, maxLength);
		}
		return "";
	}

	/**
	 * 判断字符串是否是某一字符集。<br/>
	 * @param str 字符串
	 * @param charsetName 字符集名称。<br/>
	 * @return
	 */
	public static boolean isCharset(String str, String charsetName) {
		if(isBlank(charsetName) || null == str) {
			return false;
		}
		if(!isSupported(charsetName)) {
			return false;
		}
		try {
			byte[] bytes = str.getBytes(charsetName);
			if(null == bytes || bytes.length < 1) {
				return false;
			}
			String s = new String(bytes, charsetName);
			return str.equals(s);
		} catch (UnsupportedEncodingException e) {
			return false;
		} catch (UnsupportedOperationException e) {
			return false;
		} catch (Throwable e) {
			return false;
		}
       
    }
	
	/**
	 * 计算字符串长度，一个英文字长度为1；一个中文字长度为2
	 * @param s
	 * @return
	 */
	public static int calStringLength(String s){
		if (s==null){
			return 0;
		}
		
		int result = 0;
		for (char c : s.toCharArray()){
			if (isChinese(c)){
				result += 2;
			}
			else{
				result += 1;
			}
		}
		
		return result;
	}
	
	 /**
	  * 判断是否是中文字
	 * @param c
	 * @return
	 */
	public static boolean isChinese(char c) {
	      Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
	      if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS	        
	    		  || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS	       
	    		  || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A	        
	    		  //|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION	        
	    		  //|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION	       
	    		  //|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
	    		  ){	       
	    	  return true;
	      }
	      return false;	   
	}
	
   /**
	* 是否包含中英文
	* @Function: hasContainChineseOrEngLetter 
	* @Description:是否包含中英文 
	* @param  @param s
	* @param  @return  
	* @return boolean   
	* @throws
	 */
	public static boolean isNotContainChineseOrEngLetter(String s) {
		int len = s.length();
		for (int i = 0; i < len; ++i) {
			char ch = s.charAt(i);
			if (!(((ch >= 'a') && (ch <= 'z')) || ((ch >= 'A') && (ch <= 'Z')))) {
				boolean isChinese = isChinese(ch);
				if (!isChinese){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 判断是否全部为特殊符号
	* @Function: isAllSpecialChar 
	* @Description: 
	* @param  @param str
	* @param  @return  
	* @return boolean   
	* @throws
	 */
	public static boolean isAllSpecialChar(String str){
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？× ：\n]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		int matchCount = 0;
		while(m.find()){
			matchCount++;
		}
		if(matchCount == str.length()){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 判断是否全部为标点符号
	* @Function: isAllPunctuation 
	* @Description: 
	* @param  @param str
	* @param  @return  
	* @return boolean   
	* @throws
	 */
	public static boolean isAllPunctuation(String str){
		int matchCount = 0;
		Pattern p = Pattern.compile("[\\pP‘’“”]"); 
		Matcher m = p.matcher(str); 
		while(m.find()){
			matchCount++;
		}
		if(matchCount == str.length()){
			return true;
		}else{
			return false;
		}
	}
	
	/**   
	 *******************************************************************************
	 * @function : 在字符串大于指定字节长度时，截取指定长度的字符串，加指定后缀（支持全半角混合字符串）
	 * @param s 字符串
	 * @param len 截取的字节长度
	 * @param subffix 后缀
	 * @return 结果字符串
	 *******************************************************************************
	 * @creator ：nelson   
	 * @date ：2010-12-15   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public static String getStrByLen(String s, int len, String subffix) {
		String retStr = s;

		if (getStrBytesLen(retStr) > len) {
			retStr = subStrByLen(retStr, len) + subffix;
		}

		return retStr;
	}
	
	/**   
	 *******************************************************************************
	 * @function : 支持全半角混合字符串
	 * @param s
	 * @param length
	 * @return
	 *******************************************************************************
	 * @creator ：nelson   
	 * @date ：2011-1-6   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public static String subStrByLen(String s, int length) {
		if(s == null) {
			return "";
		}
		final StringBuffer sb = new StringBuffer(s);
		while (true) {

			final String x = sb.toString();
			int len = 0;
			try {
				len = x.getBytes("GBK").length;
			} catch (UnsupportedEncodingException e) {
			}
			if (len <= length) {
				break;
			}
			sb.deleteCharAt(x.length() - 1);
		}
		return sb.toString();
	}
	
	/**   
	 *******************************************************************************
	 * @function : 获得字符串字节长度
	 * @param s
	 * @return
	 *******************************************************************************
	 * @creator ：nelson   
	 * @date ：2011-1-6   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public static int getStrBytesLen(String s) {
			int len = 0;
			if (s!= null) {
				try {
					len = s.getBytes("GBK").length;
				} catch (UnsupportedEncodingException e) {
				}
			}
		return len;
	}

	public static void main(String[] args) {
		System.out.println(getRandom(5, ""));
	}
}
