package com.tictactoe.exceptions;

import java.io.IOException;

public class RedirectException extends RuntimeException
{
    public RedirectException(IOException e)
    {
        super("Failed to redirect in " + e.getStackTrace()[0].getClassName());
    }
}
