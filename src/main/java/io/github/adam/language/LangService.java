package io.github.adam.language;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

class LangService
{
    private LanguageRepository repository;

    LangService()
    {
        this(new LanguageRepository());
    }

    LangService(LanguageRepository repository)
    {
        this.repository = repository;
    }
    List<LangDTO> findAll()
    {
        return repository.findAll().stream().map(LangDTO::new).collect(toList());
    }
}
