package com.tictactoe.controller;

import com.tictactoe.exceptions.ForwardException;
import com.tictactoe.exceptions.InvalidSessionException;
import com.tictactoe.exceptions.RedirectException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public abstract class AddOnServlet extends HttpServlet
{
    protected <T> T extractAttribute(HttpSession session, String attributeName, Class<T> clazz)
    {
        Object attribute = session.getAttribute(attributeName);

        if (attribute == null || clazz != attribute.getClass())
        {
            session.invalidate();
            throw new InvalidSessionException("Invalid session");
        }

        return (T) attribute;
    }

    protected void forwardTo(HttpServletRequest req, HttpServletResponse resp, String path)
    {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(path);
        try
        {
            dispatcher.forward(req, resp);
        }
        catch (ServletException | IOException e)
        {
            throw new ForwardException("Error forwarding to index.jsp in" + this.getClass().getSimpleName(), e);
        }
    }

    protected void sendRedirectTo(HttpServletResponse resp, String path)
    {
        try
        {
            resp.sendRedirect(path);
        }
        catch (IOException e)
        {
            throw new RedirectException(e);
        }
    }
}
