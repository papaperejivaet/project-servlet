package com.tictactoe.controller;

import com.tictactoe.AI;
import com.tictactoe.Field;
import com.tictactoe.Sign;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;

@WebServlet(name = "LogicServlet", value = "/logic")
public class LogicServlet extends AddOnServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    {
        HttpSession session = req.getSession();

        // Получаем объект игрового поля из сессии
        Field field = extractAttribute(session, "field", Field.class);

        //Получаем объект AI
        AI computer = extractAttribute(session, "ai", AI.class);

        // получаем индекс ячейки, по которой произошел клик
        int index = getSelectedIndex(req);
        Sign sign = field.getField().get(index);

        // Проверяем, что ячейка, по которой был клик пустая.
        // Иначе ничего не делаем и отправляем пользователя на ту же страницу без изменений
        // параметров в сессии
        if (Sign.EMPTY != sign) {
            forwardTo(req, resp, "/index.jsp");
            return;
        }

        field.getField().put(index, Sign.CROSS);
        if (checkWin(resp, session, field)) {
            return;
        }


        //Ход компьютера
        int computerMoveCellNumber = computer.computeMove(field);
        if (computerMoveCellNumber != -1)
        {
            field.getField().put(computerMoveCellNumber, Sign.NOUGHT);
        }
        else
        {
            session.setAttribute("draw", true);

            List<Sign> data = field.getFieldData();
            session.setAttribute("data", data);

            forwardTo(req, resp, "/index.jsp");
        }
        if (checkWin(resp, session, field)) {
            return;
        }

        List<Sign> data = field.getFieldData();

        session.setAttribute("data", data);
        session.setAttribute("field", field);

        forwardTo(req, resp, "/index.jsp");
    }


    private int getSelectedIndex(HttpServletRequest req)
    {
        String click = req.getParameter("click");
        boolean isNumeric = click.matches("\\d");
        return isNumeric ? Integer.parseInt(click) : 0;
    }

    private boolean checkWin(HttpServletResponse response, HttpSession currentSession, Field field) {
        Sign winner = field.checkWin();
        if (Sign.CROSS == winner || Sign.NOUGHT == winner) {

            currentSession.setAttribute("winner", winner);

            List<Sign> data = field.getFieldData();
            currentSession.setAttribute("data", data);

            sendRedirectTo(response, "/index.jsp");
            return true;
        }
        return false;
    }
}
