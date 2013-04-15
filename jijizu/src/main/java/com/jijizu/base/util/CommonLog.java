package com.jijizu.base.util;

import java.text.MessageFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Apache Commmon Log包装类。<br/>
 * 
 * @author majun
 * @since 2011-06-14
 */
public class CommonLog
{

    private Log log;

    public static CommonLog getLog(Class<?> clazz)
    {
        return new CommonLog(clazz);
    }
    
    public static CommonLog getLog(String name)
    {
        return new CommonLog(name);
    }

    private CommonLog(Class<?> clazz)
    {
        super();
        log = LogFactory.getLog(clazz);
    }

    private CommonLog(String name)
    {
        super();
        log = LogFactory.getLog(name);
    }

    public boolean isDebugEnabled()
    {
        return log.isDebugEnabled();
    }

    public boolean isErrorEnabled()
    {
        return log.isErrorEnabled();
    }

    public boolean isFatalEnabled()
    {
        return log.isFatalEnabled();
    }

    public boolean isInfoEnabled()
    {
        return log.isInfoEnabled();
    }

    public boolean isTraceEnabled()
    {
        return log.isTraceEnabled();
    }

    public boolean isWarnEnabled()
    {
        return log.isWarnEnabled();
    }

    public void trace(Object object)
    {
        if (isTraceEnabled())
        {
            log.trace(object);
        }
    }

    public void trace(Object object, Object... params)
    {
        if (isTraceEnabled())
        {
            log.trace(MessageFormat.format(String.valueOf(object), params));
        }
    }

    public void trace(Object object, Throwable t, Object... params)
    {
        if (isTraceEnabled())
        {
            log.trace(MessageFormat.format(String.valueOf(object), params), t);
        }
    }

    public void debug(Object object)
    {
        if (isDebugEnabled())
        {
            log.debug(object);
        }
    }

    public void debug(Object object, Object... params)
    {
        if (isDebugEnabled())
        {
            log.debug(MessageFormat.format(String.valueOf(object), params));
        }
    }

    public void debug(Object object, Throwable t)
    {
        if (isDebugEnabled())
            log.debug(object, t);
    }

    public void debug(Object object, Throwable t, Object... params)
    {
        if (isDebugEnabled())
            log.debug(MessageFormat.format(String.valueOf(object), params), t);
    }

    public void info(Object object)
    {
        if (isInfoEnabled())
        {
            log.info(object);
        }
    }

    public void info(Object object, Object... params)
    {
        if (isInfoEnabled())
        {
            log.info(MessageFormat.format(String.valueOf(object), params));
        }
    }

    public void info(Object object, Throwable t, Object... params)
    {
        if (isInfoEnabled())
        {
            log.info(MessageFormat.format(String.valueOf(object), params), t);
        }
    }

    public void warn(Object object)
    {
        if (isWarnEnabled())
        {
            log.warn(object);
        }
    }

    public void warn(Object object, Object... params)
    {
        if (isWarnEnabled())
        {
            log.warn(MessageFormat.format(String.valueOf(object), params));
        }
    }

    public void warn(Object object, Throwable t, Object... params)
    {
        if (isWarnEnabled())
            log.warn(MessageFormat.format(String.valueOf(object), params), t);
    }

    public void error(Object object)
    {
        if (isErrorEnabled())
        {
            log.error(object);
        }
    }

    public void error(Object object, Object... params)
    {
        if (isErrorEnabled())
        {
            log.error(MessageFormat.format(String.valueOf(object), params));
        }
    }

    public void error(Object object, Throwable t, Object... params)
    {
        if (isErrorEnabled())
        {
            log.error(MessageFormat.format(String.valueOf(object), params), t);
        }
    }

    public void error(Object object, Throwable t)
    {
        if (isErrorEnabled())
        {
            log.error(object, t);
        }
    }

    public void fatal(Object object)
    {
        if (isFatalEnabled())
        {
            log.fatal(object);
        }
    }

    public void fatal(Object object, Object... params)
    {
        if (isFatalEnabled())
        {
            log.fatal(MessageFormat.format(String.valueOf(object), params));
        }
    }

    public void fatal(Object object, Throwable t, Object... params)
    {
        if (isFatalEnabled())
        {
            log.fatal(MessageFormat.format(String.valueOf(object), params), t);
        }
    }
    
    public final Log getRealLog()
    {
        return log;
    }

}
