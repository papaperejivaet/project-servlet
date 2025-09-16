package com.tictactoe.exceptions;

public class ForwardException extends RuntimeException
{
    public ForwardException(String message, Exception e)
    {
        super(message, e);
    }
}
