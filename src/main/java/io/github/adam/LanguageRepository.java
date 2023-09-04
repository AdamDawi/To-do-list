package io.github.adam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LanguageRepository
{
    Optional<Language> findById(Integer id)
    {
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        var result = session.get(Language.class, id); //get data to language class
        transaction.commit();
        session.close();
        return Optional.ofNullable(result);
    }
}
