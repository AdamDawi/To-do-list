package io.github.adam.language;

import io.github.adam.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class LanguageRepository
{
    List<Language> findAll()
    {
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        var result = session.createQuery("from Language", Language.class).list();
        transaction.commit();
        session.close();
        return result;
    }
    public Optional<Language> findById(Integer id)
    {
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        var result = session.get(Language.class, id); //get data to language class
        transaction.commit();
        session.close();
        return Optional.ofNullable(result);
    }
}
