package com.jijizu.base.util;

/**
 * 代表不可能到达的代码的异常。<br/>
 * 
 * @author luke_huang
 * @since 2011-05-25
 */
public class UnreachableCodeException extends RuntimeException
{

    private static final long serialVersionUID = 620891308859225220L;

    public UnreachableCodeException()
    {
        super();
    }

    public UnreachableCodeException(String message)
    {
        super(message);
    }

    public UnreachableCodeException(Throwable cause)
    {
        super(cause);
    }

    public UnreachableCodeException(String message, Throwable cause)
    {
        super(message, cause);
    }

}

