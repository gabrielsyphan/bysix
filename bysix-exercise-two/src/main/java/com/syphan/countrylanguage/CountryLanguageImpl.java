package com.syphan.countrylanguage;

import com.google.gson.Gson;
import com.syphan.model.Country;

import java.util.*;
import java.util.stream.Collectors;

public class CountryLanguageImpl implements CountryLanguage {

    private final List<Country> countries;

    public CountryLanguageImpl(String jsonData) {
        List<Country> countriesList = List.of(new Gson().fromJson(jsonData, Country[].class));
        this.countries = countriesList.stream().peek(country -> {
            if(country.getName() == null || country.getName().isBlank() || country.getLanguages() == null) {
                throw new RuntimeException("Both country name and languages must be present");
            }
        }).collect(Collectors.toList());
    }

    @Override
    public long countCountries() {
        Map<String, Integer> countryMap = new HashMap<>();
        countries.forEach(country -> countryMap.put(country.getName(), 1));
        return countryMap.size();
    }

    @Override
    public long countAllLanguages() {
        List<String> languages = new ArrayList<>();
        countries.forEach(country -> languages.addAll(List.of(country.getLanguages())));
        return languages.stream().distinct().count();
    }

    @Override
    public Country getCountryWithMostOfficialLanguagesAndSpeak(String language) {
        // Find all countries that speak the language passed in
        Map<Country, Integer> countryMap = new HashMap<>();
        countries.forEach(country -> {
            List<String> languages = Arrays.asList(country.getLanguages());
            if(languages.contains(language)) {
                countryMap.put(country, languages.size());
            }
        });

        // Find the country that speaks the most languages and also speaks the language passed in
        Optional<Map.Entry<Country, Integer>> countryWithMostOfficialLanguagesAndSpeak = countryMap.entrySet().stream()
                .max(Map.Entry.comparingByValue());

        if(countryWithMostOfficialLanguagesAndSpeak.isEmpty()) {
            throw new RuntimeException("No countries found");
        }

        return countryWithMostOfficialLanguagesAndSpeak.get().getKey();
    }

    @Override
    public Country getCountryWithHighestNumberOfLanguages() {
        // Create a map of countries and the number of languages they speak
        Map<Country, Integer> countryMap = new HashMap<>();
        countries.forEach(country -> countryMap.put(country, country.getLanguages().length));

        Optional<Map.Entry<Country, Integer>> countryWithHighestNumberOfLanguages = countryMap.entrySet().stream()
                .max(Map.Entry.comparingByValue());

        if(countryWithHighestNumberOfLanguages.isEmpty()) {
            throw new RuntimeException("No countries found");
        }

        return countryWithHighestNumberOfLanguages.get().getKey();
    }

    @Override
    public String getMostCommonLanguage() {
        Map<String, Integer> languageMap = new HashMap<>();

        // Create a map of languages and the number of countries that speak them
        countries.forEach(country -> {
            List<String> languages = Arrays.asList(country.getLanguages());

            // If a language is spoken by multiple countries, increment the count
            languages.forEach(language -> {
                if(languageMap.containsKey(language)) {
                    languageMap.put(language, languageMap.get(language) + 1);
                } else {
                    languageMap.put(language, 1);
                }
            });
        });

        // Find the language spoken by the most countries
        Optional<Map.Entry<String, Integer>> mostCommonLanguage = languageMap.entrySet().stream()
                .max(Map.Entry.comparingByValue());

        if(mostCommonLanguage.isEmpty()) {
            throw new RuntimeException("No languages found");
        }

        return mostCommonLanguage.get().getKey();
    }
}
