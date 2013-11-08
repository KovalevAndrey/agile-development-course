package ru.unn.agile.Re.model;

class CharacterRegex extends Regex
{
    CharacterRegex(String pattern)
    {
        lexemes.add(pattern);
    }
}
