package com.syphan.contrylanguage;

import com.syphan.countrylanguage.CountryLanguage;
import com.syphan.countrylanguage.CountryLanguageImpl;
import com.syphan.model.Country;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class CountryLanguageTest {

    @Test
    public void testCountriesSuccess() {
        String jsonString = "[{\"country\":\"US\",\"languages\":[\"en\"]},{\"country\":\"BE\",\"languages\":[\"nl\",\"fr\",\"de\"]},{\"country\":\"NL\",\"languages\":[\"nl\",\"fy\"]},{\"country\":\"DE\",\"languages\":[\"de\"]},{\"country\":\"ES\",\"languages\":[\"es\"]}]";
        CountryLanguage countryLanguage = new CountryLanguageImpl(jsonString);

        assertDoesNotThrow(() -> {
            long numberOfCountries = countryLanguage.countCountries();
            long numberOfLanguages = countryLanguage.countAllLanguages();
            Country countryMostLanguagesAndGerman= countryLanguage.getCountryWithMostOfficialLanguagesAndSpeak("de");
            Country countryHighestNumberOfLanguages = countryLanguage.getCountryWithHighestNumberOfLanguages();
            String mostCommonLanguage = countryLanguage.getMostCommonLanguage();

            assertEquals(5, numberOfCountries);
            assertEquals(6, numberOfLanguages);
            assertEquals("BE", countryMostLanguagesAndGerman.getName());
            assertEquals("BE", countryHighestNumberOfLanguages.getName());
            assertEquals("de", mostCommonLanguage);
        });
    }

    @Test
    public void testCountriesNullNameFailure() {
        String jsonString = "[{\"country\":null,\"languages\":[\"en\"]}]";
        this.validateFailure(jsonString);
    }

    @Test
    public void testCountriesEmptyNameFailure() {
        String jsonString = "[{\"country\":\"\",\"languages\":[\"nl\",\"fy\"]},{\"country\":\"DE\",\"languages\":[\"de\"]}]";
        this.validateFailure(jsonString);
    }

    @Test
    public void testCountriesNullLanguagesFailure() {
        String jsonString = "[{\"country\":\"US\",\"languages\":null}]";
        this.validateFailure(jsonString);
    }

    private void validateFailure(String jsonString) {
        assertThrows(RuntimeException.class, () -> {
            CountryLanguage countryLanguage = new CountryLanguageImpl(jsonString);
        });
    }
}
