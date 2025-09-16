package com.tictactoe.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@WebServlet(name = "RestartServlet", value = "/restart")
public class RestartServlet extends AddOnServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    {
        req.getSession().invalidate();
        sendRedirectTo(resp, "/start");
    }
}
