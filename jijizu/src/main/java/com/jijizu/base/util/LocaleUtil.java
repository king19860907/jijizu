package com.jijizu.base.util;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.jijizu.base.util.CollectionUtil.createLinkedList;
import static com.jijizu.base.util.StringUtil.*;
import static com.jijizu.base.util.BasicConstant.*;

/**
 * 用来处理地域和字符编码的工具类。
 * <p>
 * 由于系统locale和charset是不可靠的，不同的环境可能会有不同的系统设置，因此应用程序最好不要依赖这个系统值。
 * <code>LocaleUtil</code>提供了一个方案，可以“修改”默认locale和charset。
 * </p>
 * <p>
 * <code>LocaleUtil</code>提供了以下几个作用域的locale/charset设定：
 * </p>
 * <ul>
 * <li>系统作用域：由JVM所运行的操作系统环境决定，在JVM生命期内不改变。可通过<code>LocaleUtil.getSystem()</code>
 * 取得。</li>
 * <li>默认作用域：在整个JVM中全局有效，可被改变。可通过<code>LocaleUtil.getDefault()</code>
 * 取得。如未明确指定，则取“系统作用域”的值。</li>
 * <li>线程作用域：在整个线程中全局有效，可被改变。可通过<code>LocaleUtil.getContext()</code>
 * 取得。如未明确指定，则取“默认作用域”的值。每个线程都可以有自己的locale和charset设置，不会干扰其它线程。</li>
 * </ul>
 * <p>
 * Util工具箱里的其它工具类，当需要时，将从<code>LocaleUtil.getContext()</code>
 * 中取得当前的locale和charset设置。例如：<code>StringEscapeUtil.escapeURL(value)</code>
 * ，如不指定charset
 * ，将从context中取得charset。这样，框架往往可以修改context值，而所有线程中的方法调用将服从于框架的locale和charset设定。
 * </p>
 * @author luke_huang
 * @since 2011-05-17
 *
 */
public final class LocaleUtil
{

    private static final LocaleInfo systemLocaleInfo = new LocaleInfo();
    private static LocaleInfo defaultLocalInfo = systemLocaleInfo;
    private static final ThreadLocal<LocaleInfo> contextLocaleInfoHolder = new ThreadLocal<LocaleInfo>();

    /**
     * 取得备选的resource bundle风格的名称列表。
     * <p>
     * 例如：
     * <code>calculateBundleNames("hello.jsp", new Locale("zh", "CN", "variant"))</code>
     * 将返回下面列表：
     * <ol>
     * <li>hello_zh_CN_variant.jsp</li>
     * <li>hello_zh_CN.jsp</li>
     * <li>hello_zh.jsp</li>
     * <li>hello.jsp</li>
     * </ol>
     * </p>
     * 
     * @param baseName bundle的基本名
     * @param locale 区域设置
     * @return 所有备选的bundle名
     */
    public static List<String> calculateBundleNames(String baseName, Locale locale) {
        return calculateBundleNames(baseName, locale, false);
    }

    /**
     * 取得备选的resource bundle风格的名称列表。
     * <p>
     * 例如：
     * <code>calculateBundleNames("hello.jsp", new Locale("zh", "CN", "variant"),
     * false)</code>将返回下面列表：
     * <ol>
     * <li>hello_zh_CN_variant.jsp</li>
     * <li>hello_zh_CN.jsp</li>
     * <li>hello_zh.jsp</li>
     * <li>hello.jsp</li>
     * </ol>
     * </p>
     * <p>
     * 当<code>noext</code>为<code>true</code>时，不计算后缀名，例如
     * <code>calculateBundleNames("hello.world",
     * new Locale("zh", "CN", "variant"), true)</code>将返回下面列表：
     * <ol>
     * <li>hello.world_zh_CN_variant</li>
     * <li>hello.world_zh_CN</li>
     * <li>hello.world_zh</li>
     * <li>hello.world</li>
     * </ol>
     * </p>
     * 
     * @param baseName bundle的基本名
     * @param locale 区域设置
     * @return 所有备选的bundle名
     */
    public static List<String> calculateBundleNames(String baseName, Locale locale, boolean noext) {
        baseName = StringUtil.trimToEmpty(baseName);

        if (locale == null) {
            locale = new Locale(EMPTY_STRING);
        }

        // 取后缀。
        String ext = EMPTY_STRING;
        int extLength = 0;

        if (!noext) {
            int extIndex = baseName.lastIndexOf(".");

            if (extIndex != -1) {
                ext = baseName.substring(extIndex, baseName.length());
                extLength = ext.length();
                baseName = baseName.substring(0, extIndex);

                if (extLength == 1) {
                    ext = EMPTY_STRING;
                    extLength = 0;
                }
            }
        }

        // 计算locale后缀。
        LinkedList<String> result = createLinkedList();
        String language = locale.getLanguage();
        int languageLength = language.length();
        String country = locale.getCountry();
        int countryLength = country.length();
        String variant = locale.getVariant();
        int variantLength = variant.length();

        StringBuilder buffer = new StringBuilder(baseName);

        buffer.append(ext);
        result.addFirst(buffer.toString());
        buffer.setLength(buffer.length() - extLength);

        // 如果locale是("", "", "").
        if (languageLength + countryLength + variantLength == 0) {
            return result;
        }

        // 加入baseName_language，如果baseName为空，则不加下划线。
        if (buffer.length() > 0) {
            buffer.append('_');
        }

        buffer.append(language);

        if (languageLength > 0) {
            buffer.append(ext);
            result.addFirst(buffer.toString());
            buffer.setLength(buffer.length() - extLength);
        }

        if (countryLength + variantLength == 0) {
            return result;
        }

        // 加入baseName_language_country
        buffer.append('_').append(country);

        if (countryLength > 0) {
            buffer.append(ext);
            result.addFirst(buffer.toString());
            buffer.setLength(buffer.length() - extLength);
        }

        if (variantLength == 0) {
            return result;
        }

        // 加入baseName_language_country_variant
        buffer.append('_').append(variant);

        buffer.append(ext);
        result.addFirst(buffer.toString());
        buffer.setLength(buffer.length() - extLength);

        return result;
    }
    
    /**
     * 获取本地化资源文字。 <br/>
     * 
     * @param resourceBaseName
     *            本地化资源的基本名称
     * @param locale
     *            本地化对象
     * @param key
     *            资源的key
     * @return 显示文字
     */
    public static String getLocaleText(String resourceBaseName, Locale locale,
            String key)
    {
        String text = null;
        if (isNotNullOrEmpty(resourceBaseName) && isNotNullOrEmpty(key))
        {
            String baseName = resourceBaseName.trim();
            // File file=new File("/"+baseName.replace(".", "/")+".properties");
            // if(file.exists() && file.isFile()) {
            String k = key.trim();
            text = (locale != null ? ResourceBundle.getBundle(baseName, locale)
                    .getString(k) : ResourceBundle.getBundle(baseName)
                    .getString(k));
            // }
        }
        return text;
    }

    /**
     * 获取本地化资源文字。 <br/>
     * 
     * @param resourceBaseName
     *            本地化资源的基本名称
     * @param locale
     *            本地化对象
     * @param key
     *            资源的key
     * @return 显示文字
     */
    public static String getLocaleText(String resourceBaseName, String key)
    {
        return getLocaleText(resourceBaseName, null, key);
    }

    /**
     * 取得可用的本地化信息。 <br/>
     * 
     * @return
     */
    public static Locale[] getAvailableLocales()
    {
        return Locale.getAvailableLocales();
    }

    /**
     * 解析locale字符串。
     * <p>
     * Locale字符串是符合下列格式：<code>language_country_variant</code>。
     * </p>
     * 
     * @param localeString
     *            要解析的字符串
     * @return <code>Locale</code>对象，如果locale字符串为空，则返回<code>null</code>
     */
    public static Locale parseLocale(String localeString)
    {
        localeString = trimToNull(localeString);

        if (localeString == null)
        {
            return null;
        }

        String language = EMPTY_STRING;
        String country = EMPTY_STRING;
        String variant = EMPTY_STRING;

        // language
        int start = 0;
        int index = localeString.indexOf("_");

        if (index >= 0)
        {
            language = localeString.substring(start, index).trim();

            // country
            start = index + 1;
            index = localeString.indexOf("_", start);

            if (index >= 0)
            {
                country = localeString.substring(start, index).trim();

                // variant
                variant = localeString.substring(index + 1).trim();
            } else
            {
                country = localeString.substring(start).trim();
            }
        } else
        {
            language = localeString.substring(start).trim();
        }

        return new Locale(language, country, variant);
    }

    /**
     * 取得默认的区域。
     * 
     * @return 默认的区域
     */
    public static LocaleInfo getDefault()
    {
        return defaultLocalInfo == null ? systemLocaleInfo : defaultLocalInfo;
    }

    /**
     * 取得当前thread默认的区域。
     * 
     * @return 当前thread默认的区域
     */
    public static LocaleInfo getContext()
    {
        LocaleInfo contextLocaleInfo = contextLocaleInfoHolder.get();
        return contextLocaleInfo == null ? getDefault() : contextLocaleInfo;
    }

    /**
     * 取得操作系统默认的区域。
     * 
     * @return 操作系统默认的区域
     */
    public static LocaleInfo getSystem()
    {
        return systemLocaleInfo;
    }

}