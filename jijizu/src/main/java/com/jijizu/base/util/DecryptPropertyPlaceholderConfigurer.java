package com.jijizu.base.util;

import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import static com.jijizu.base.util.StringUtil.*;

public class DecryptPropertyPlaceholderConfigurer extends
		PropertyPlaceholderConfigurer {

	/*
	 * private String placeholderPrefix = DEFAULT_PLACEHOLDER_PREFIX;
	 * 
	 * private String placeholderSuffix = DEFAULT_PLACEHOLDER_SUFFIX;
	 * 
	 * private int systemPropertiesMode = SYSTEM_PROPERTIES_MODE_FALLBACK;
	 * 
	 * private boolean ignoreUnresolvablePlaceholders = false;
	 * 
	 * private String nullValue;
	 */
	/*
	 * protected String parseStringValue(String strVal, Properties props, Set
	 * visitedPlaceholders) throws BeanDefinitionStoreException { // 新增
	 * 
	 * StringBuffer buf = new StringBuffer(strVal);
	 * 
	 * int startIndex = strVal.indexOf(this.placeholderPrefix); while
	 * (startIndex != -1) { int endIndex = buf.indexOf(this.placeholderSuffix,
	 * startIndex + this.placeholderPrefix.length()); if (endIndex != -1) {
	 * String placeholder = buf.substring(startIndex +
	 * this.placeholderPrefix.length(), endIndex); if
	 * (!visitedPlaceholders.add(placeholder)) { throw new
	 * BeanDefinitionStoreException( "Circular placeholder reference '" +
	 * placeholder + "' in property definitions"); } String propVal =
	 * resolvePlaceholder(placeholder, props, this.systemPropertiesMode); if
	 * (propVal != null) { // Recursive invocation, parsing placeholders
	 * contained in // the // previously resolved placeholder value. propVal =
	 * parseStringValue(propVal, props, visitedPlaceholders);
	 * buf.replace(startIndex, endIndex + this.placeholderSuffix.length(),
	 * propVal); if (logger.isTraceEnabled()) { logger.trace("Resolved
	 * placeholder '" + placeholder + "'"); } startIndex =
	 * buf.indexOf(this.placeholderPrefix, startIndex + propVal.length()); }
	 * else if (this.ignoreUnresolvablePlaceholders) { // Proceed with
	 * unprocessed value. startIndex = buf.indexOf(this.placeholderPrefix,
	 * endIndex + this.placeholderSuffix.length()); } else { throw new
	 * BeanDefinitionStoreException( "Could not resolve placeholder '" +
	 * placeholder + "'"); } visitedPlaceholders.remove(placeholder); } else {
	 * startIndex = -1; } }
	 * 
	 * String value = buf.toString(); return (value.equals(this.nullValue) ?
	 * null : value); }
	 */

	/*
	 * protected String resolvePlaceholder(String placeholder, Properties props,
	 * int systemPropertiesMode) { String propVal = null; if
	 * (systemPropertiesMode == SYSTEM_PROPERTIES_MODE_OVERRIDE) { propVal =
	 * resolveSystemProperty(placeholder); } if (propVal == null) { propVal =
	 * resolvePlaceholder(placeholder, props); } if (propVal == null &&
	 * systemPropertiesMode == SYSTEM_PROPERTIES_MODE_FALLBACK) { propVal =
	 * resolveSystemProperty(placeholder); } return propVal; }
	 */

	protected String resolvePlaceholder(String placeholder, Properties props) {
		String s = props.getProperty(placeholder);

		/*
		 * if("jdbc.url".equals(placeholder) && s!=null ){ String patternStr =
		 * "jdbc:oracle:thin:\\w+/([^@]+)@.+"; Pattern pattern =
		 * Pattern.compile(patternStr); Matcher matcher = pattern.matcher(s);
		 * StringBuffer buf = new StringBuffer(); while(matcher.find()){
		 * 
		 * String replaceStr = matcher.group(1);
		 * replaceStr=Encrypter.decrypt(replaceStr);
		 * matcher.appendReplacement(buf, replaceStr);
		 * 
		 * s=buf.toString(); } matcher.appendTail(buf); }else
		 */
		if ("jdbc.password".equals(placeholder) && isNotNullOrEmpty(s)) {
			s = Encrypter.decrypt(s);
		}
		if ("log.jdbc.password".equals(placeholder) && isNotNullOrEmpty(s)) {
			s = Encrypter.decrypt(s);
		}

		return s;
	}

	public static void main(String[] args) {
		System.out.println(Encrypter.encrypt(""));
	}
}
