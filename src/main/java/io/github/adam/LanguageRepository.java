package io.github.adam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LanguageRepository
{
    private List<Language> languages;

    LanguageRepository()
    {
        languages = new ArrayList<>();
        languages.add(new Language(1L, "Hello", "en"));
        languages.add(new Language(2L, "Czesc", "pl"));
    }

    Optional<Language> findById(Long id)
    {
        return languages.stream().filter(l->l.getId().equals(id)).findFirst(); //finding id with stream
    }
}
