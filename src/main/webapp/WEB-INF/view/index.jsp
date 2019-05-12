<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>TodoJSP</title>
    <link rel="stylesheet" href="todojsp.css">
</head>
<body>
    <section class="todoapp">
        <header class="header">
            <h1>TodoJSP</h1>
            <form method="post">
                <input type="hidden" name="do" value="add">
                <input class="new-todo" name="title" placeholder="What needs to be done?" autofocus>
            </form>
        </header>
        <%--@elvariable id="model" type="de.cwkr.todojsp.TodoViewModel"--%>
        <c:if test="${not empty model.items}">
            <section class="main">
                <ul class="todo-list">
                    <c:forEach items="${model.items}" var="item">
                        <li class="${item.completed ? "completed" : ""}">
                            <form method="post">
                                <input type="hidden" name="id" value="${item.id}">
                                <div class="view">
                                    <button type="submit" class="toggle" name="do" value="toggle"></button>
                                    <label>${item.title}</label>
                                    <button type="submit" class="destroy" name="do" value="remove">&times;</button>
                                </div>
                            </form>
                        </li>
                    </c:forEach>
                </ul>
            </section>
        </c:if>
        <footer class="footer">
            <span class="todo-count">
                <strong>${model.numberOfItemsLeft}</strong> of
                <strong>${model.numberOfTotalItems}</strong>
                item(s) left
            </span>
            <ul class="filters">
                <li>
                    <a class="${model.mode == 'all' ? 'selected' : ''}" href="?mode=all">All</a>
                </li>
                <li>
                    <a class="${model.mode == 'active' ? 'selected' : ''}" href="?mode=active">Active</a>
                </li>
                <li>
                    <a class="${model.mode == 'completed' ? 'selected' : ''}" href="?mode=completed">Completed</a>
                </li>
            </ul>
        </footer>
    </section>
    <footer class="info">
        <p>Written by <a href="https://github.com/cwkr/todojsp">Christian Winkler</a></p>
        <p>Derived from <a href="http://todomvc.com">TodoMVC</a></p>
    </footer>
</body>
</html>
