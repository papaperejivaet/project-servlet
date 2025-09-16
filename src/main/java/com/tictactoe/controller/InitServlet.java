package com.tictactoe.controller;

import com.tictactoe.AI;
import com.tictactoe.Field;
import com.tictactoe.Sign;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;

@WebServlet(name = "InitServlet", value = "/start")
public class InitServlet extends AddOnServlet
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    {
        HttpSession session = req.getSession(true);

        Field field = new Field();
        List<Sign> data = field.getFieldData();

        AI computer = new AI();
        computer.setDifficulty(1);

        session.setAttribute("field", field);
        session.setAttribute("data", data);
        session.setAttribute("ai", computer);

        forwardTo(req, resp, "/index.jsp");
    }
}
