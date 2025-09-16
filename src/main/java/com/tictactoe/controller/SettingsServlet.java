package com.tictactoe.controller;

import com.tictactoe.AI;

import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet(name = "SettingsServlet", value = "/settings")
public class SettingsServlet extends AddOnServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    {
        HttpSession session = req.getSession();
        AI computer = extractAttribute(session, "ai", AI.class);

        String difficulty = req.getParameter("difficulty");

        if (difficulty != null && difficulty.matches("\\d"))
        {
            computer.setDifficulty(Integer.parseInt(difficulty));
            session.setAttribute("ai", computer);
        }

        forwardTo(req, resp, "/index.jsp");
    }




}
