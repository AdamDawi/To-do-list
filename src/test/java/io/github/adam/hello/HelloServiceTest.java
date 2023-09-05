package io.github.adam.hello;

import io.github.adam.hello.HelloService;
import io.github.adam.language.Language;
import io.github.adam.language.LanguageRepository;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class HelloServiceTest
{
    private final static String WELCOME = "Hello";
    private final static String FALLBACK_ID_WELCOME = "Hola";
    @Test
    public void test_prepareGreeting_nullName_returnsGreetingWithFallBackValue() throws Exception
    {
        //given
        var mockRepository = alwaysReturningHelloRepository(); //new repository for testing

        HelloService SUT = new HelloService(mockRepository); //SUT - System under test

        //when
        var result = SUT.prepareGreeting(null, "-1");

        //then
        assertEquals(WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }


    @Test
    public void test_prepareGreeting_name_returnsGreetingWithName() throws Exception
    {
        //given
        HelloService SUT = new HelloService(); //SUT - System under test
        var name = "test";

        //when
        var result = SUT.prepareGreeting(name, "-1");

        //then
        assertEquals(WELCOME + " " + name + "!", result);
    }

    @Test
    public void test_prepareGreeting_nullLang_returnsGreetingWithFallBackIdLang() throws Exception
    {
        //given
        var mockRepository = fallBackLangIdRepository();

        var SUT = new HelloService(mockRepository); //SUT - System under test

        //when
        var result = SUT.prepareGreeting(null, null);

        //then
        assertEquals(FALLBACK_ID_WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    @Test
    public void test_prepareGreeting_textLang_returnsGreetingWithFallBackIdLang() throws Exception
    {
        //given
        var mockRepository = fallBackLangIdRepository();

        var SUT = new HelloService(mockRepository); //SUT - System under test

        //when
        var result = SUT.prepareGreeting(null, "abc");

        //then
        assertEquals(FALLBACK_ID_WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    @Test
    public void test_prepareGreeting_nonExistingLang_returnsGreetingWithFallbackLang() throws Exception
    {
        //given
        var mockRepository = alwaysReturningEmptyOptionalRepository();

        var SUT = new HelloService(mockRepository); //SUT - System under test

        //when
        var result = SUT.prepareGreeting(null, "-1");

        //then
        assertEquals(HelloService.FALLBACK_LANG.getWelcomeMessage() + " " + HelloService.FALLBACK_NAME + "!", result);
    }


    private LanguageRepository alwaysReturningHelloRepository()
    {
        return new LanguageRepository()
        {
            @Override
            public Optional<Language> findById(Integer id) {
                return Optional.of(new Language(null, WELCOME, null));
            }
        };
    }

    private LanguageRepository fallBackLangIdRepository()
    {
        return new LanguageRepository()
        {
            @Override
            public Optional<Language> findById(Integer id)
            {
                if(id.equals(HelloService.FALLBACK_LANG.getId()))
                {
                    return Optional.of(new Language(null, FALLBACK_ID_WELCOME, null));
                }
                return Optional.empty();
            }
        };

    }

    private LanguageRepository alwaysReturningEmptyOptionalRepository()
    {
        return new LanguageRepository()
        {
            @Override
            public Optional<Language> findById(Integer id)
            {
                return Optional.empty();
            }
        };
    }
}


