package com.jijizu.core.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOException extends java.lang.RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -3506544714301402135L;
    private static final Log log = LogFactory.getLog(DAOException.class);

    private String msg;

    public DAOException(String msg)
    {
        super(msg);
        log.info(msg);
        this.msg = msg;
    }
    public DAOException(String msg, Exception e)
    {
        super(msg,e);
        log.info(msg,e);
        this.msg = msg;
    }

    @Override
    public String getLocalizedMessage()
    {
        return msg;
    }
}

