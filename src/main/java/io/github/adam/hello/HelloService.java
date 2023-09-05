package io.github.adam.hello;

import io.github.adam.language.Language;
import io.github.adam.language.LanguageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class HelloService
{
    static final String FALLBACK_NAME = "world";
    static final Language FALLBACK_LANG = new Language(1, "Hello", "en");
    private LanguageRepository repository;
    private final Logger logger = LoggerFactory.getLogger(HelloService.class);

    HelloService()
    {
        this(new LanguageRepository());
    }
    HelloService(LanguageRepository repository)
    {
        this.repository = repository;
    }

    String prepareGreeting(String name, String lang)
    {
        Integer langId;
        try {
            langId = Optional.ofNullable(lang).map(Integer::valueOf).orElse(FALLBACK_LANG.getId());
        }
        catch(NumberFormatException e)
        {
            logger.warn("Non-numeric Language id used: " + lang);
            langId = FALLBACK_LANG.getId();
        }

        var welcomeMessage = repository.findById(langId).orElse(FALLBACK_LANG).getWelcomeMessage();

        return welcomeMessage + " "+ Optional.ofNullable(name).orElse(FALLBACK_NAME) + "!";
    }

}
