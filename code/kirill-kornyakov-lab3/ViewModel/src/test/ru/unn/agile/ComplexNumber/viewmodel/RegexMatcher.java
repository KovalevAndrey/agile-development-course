package ru.unn.agile.ComplexNumber.viewmodel;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class RegexMatcher extends BaseMatcher {
    private final String regex;

    public RegexMatcher(String regex){
        this.regex = regex;
    }

    public boolean matches(Object o){
        return ((String)o).matches(regex);
    }

    public void describeTo(Description description){
        description.appendText("matches regex = ");
        description.appendText(regex);
    }

    public static Matcher<? super String> matchesPattern(String regex){
        RegexMatcher matcher = new RegexMatcher(regex);
        //NOTE: this ugly cast is needed to workaround 'unchecked' Java warning
        @SuppressWarnings (value="unchecked")
        Matcher<? super String> castedMatcher = (Matcher<? super String>)matcher;
        return castedMatcher;
    }
}
