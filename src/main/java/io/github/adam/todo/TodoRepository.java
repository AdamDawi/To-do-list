package io.github.adam.todo;

import io.github.adam.HibernateUtil;
import io.github.adam.language.Language;

import java.util.List;

public class TodoRepository
{
    List<Todo> findAll()
    {
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        var result = session.createQuery("from Todo", Todo.class).list();
        transaction.commit();
        session.close();
        return result;
    }

    Todo toggleTodo(Integer id)
    {
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();

        var result = session.get(Todo.class, id); //get data to language class
        result.setDone(!result.isDone());

        transaction.commit();
        session.close();
        return result;
    }

    Todo addTodo(Todo newTodo)
    {
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();

        session.persist(newTodo);

        transaction.commit();
        session.close();

        return newTodo;
    }
}
