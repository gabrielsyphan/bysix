package com.syphan.countrylanguage;

import com.syphan.model.Country;

public interface CountryLanguage {

    long countCountries();

    long countAllLanguages();

    Country getCountryWithMostOfficialLanguagesAndSpeak(String language);

    Country getCountryWithHighestNumberOfLanguages();

    String getMostCommonLanguage();
}
