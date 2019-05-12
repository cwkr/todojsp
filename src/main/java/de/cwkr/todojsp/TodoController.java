package de.cwkr.todojsp;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/index.html")
public class TodoController extends HttpServlet {
    private final TodoService todoService = TodoService.getInstance();

    private TodoViewModel buildModel(HttpServletRequest request) {
        String mode = request.getParameter("mode");
        if (mode == null || mode.trim().isEmpty()) {
            mode = "all";
        }

        request.setAttribute("mode", mode);

        List<TodoItem> items;
        if (mode.equalsIgnoreCase("active")) {
            items = todoService.getActiveItems();
        } else if (mode.equalsIgnoreCase("completed")) {
            items = todoService.getCompletedItems();
        } else {
            items = todoService.getItems();
        }
        return new TodoViewModel(
            mode,
            items,
            todoService.getTotalItemsCount(),
            todoService.getItemsLeftCount(),
            todoService.getCompletedItemsCount()
        );
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TodoViewModel model = buildModel(request);
        request.setAttribute("model", model);

        request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("do");

        switch (action == null ? "" : action) {
            case "add":
                String title = request.getParameter("title");
                todoService.addItem(title);
                break;
            case "toggle":
                int toggleId = Integer.parseInt(request.getParameter("id"));
                todoService.toggleItem(toggleId);
                break;
            case "remove":
                int removeId = Integer.parseInt(request.getParameter("id"));
                todoService.removeItem(removeId);
                break;
        }

        TodoViewModel model = buildModel(request);
        request.setAttribute("model", model);

        request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
    }
}
