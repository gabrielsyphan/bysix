package com.syphan;

import com.syphan.countrylanguage.CountryLanguage;
import com.syphan.countrylanguage.CountryLanguageImpl;
import com.syphan.model.Country;

public class Main {

    public static void main(String[] args) {
        String jsonString = "[{\"country\":\"US\",\"languages\":[\"en\"]},{\"country\":\"BE\",\"languages\":[\"nl\",\"fr\",\"de\"]},{\"country\":\"NL\",\"languages\":[\"nl\",\"fy\"]},{\"country\":\"DE\",\"languages\":[\"de\"]},{\"country\":\"ES\",\"languages\":[\"es\"]}]";

        CountryLanguage countryLanguage = new CountryLanguageImpl(jsonString);
        long numberOfCountries = countryLanguage.countCountries();
        long numberOfLanguages = countryLanguage.countAllLanguages();
        Country countryMostLanguagesAndGerman= countryLanguage.getCountryWithMostOfficialLanguagesAndSpeak("de");
        Country countryHighestNumberOfLanguages = countryLanguage.getCountryWithHighestNumberOfLanguages();
        String mostCommonLanguage = countryLanguage.getMostCommonLanguage();

        System.out.println("Number of countries: " + numberOfCountries);
        System.out.println("Number of languages: " + numberOfLanguages);
        System.out.println("Country with most official languages and speak German: " + countryMostLanguagesAndGerman.getName());
        System.out.println("Country with highest number of languages: " + countryHighestNumberOfLanguages.getName());
        System.out.println("Most common language: " + mostCommonLanguage);
    }
}