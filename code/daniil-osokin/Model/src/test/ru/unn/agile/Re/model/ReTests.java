package ru.unn.agile.Re.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ReTests
{
    @Test
    public void returnFirstOccurrenceIndexWithoutRegex()
    {
        assertThat(Re.search("a", "cat"), is(equalTo(1)));
    }

    @Test
    public void returnNotFoundIndexIfNothingFound()
    {
        assertThat(Re.search("cat", "dog"), is(equalTo(Regex.NOT_FOUND_INDEX)));
    }

    @Test
    public void returnNullIndexWithNullOrOneRepeat()
    {
        assertThat(Re.search("a?", "cat"), is(equalTo(0)));
    }

    @Test
    public void returnFirstOccurrenceIndexWithNullOrOneRepeat()
    {
        assertThat(Re.search("a?rt", "cart"), is(equalTo(1)));
    }

    @Test
    public void returnFirstOccurrenceWithExactTwoRepeats()
    {
        assertThat(Re.search("g{2}oo", "ggaaggoo"), is(equalTo(4)));
    }

    @Test
    public void returnFirstOccurrenceWithExactZeroRepeats()
    {
        assertThat(Re.search("c{0}at", "it's a cat"), is(equalTo(8)));
    }

    @Test
    public void returnFirstOccurrenceWithMultilineRegex()
    {
        assertThat(Re.search("^fi", "second\nfirst"), is(equalTo(7)));
    }

    @Test
    public void returnFirstOccurrenceAtTheBeginningOfTheLineWithMultipleLines()
    {
        assertThat(Re.search("^s", "f\ns\nt\n"), is(equalTo(2)));
    }

    @Test(expected = RuntimeException.class)
    public void throwRuntimeExceptionWithMultipleRegex()
    {
        Re.search("^ab?", "aaa");
    }

    @Test
    public void returnNotFoundIndexWithCompiledRegex()
    {
        Regex regex = Re.compile("d{1}og");
        assertThat(regex.search("cat"), is(equalTo(Regex.NOT_FOUND_INDEX)));
    }

    @Test
    public void returnFirstOccurrenceIndexWithCompiledRegex()
    {
        Regex regex = Re.compile("c{1}at");
        assertThat(regex.search("where was the cat"), is(equalTo(14)));
    }

    @Test(expected = RuntimeException.class)
    public void throwsRuntimeExceptionWhenPatternIsGarbageWithMultipleRegex()
    {
        Re.compile("\\{{{1^?");
    }

    @Test
    public void canSearchEverythingThatIsNotRecognizedAsRegex()
    {
        assertThat(Re.search("\\(((%&*#@!", "where was the cat"), is(equalTo(Regex.NOT_FOUND_INDEX)));
    }

    @Test
    public void returnNotFoundIndexWithExactNumberOfRepeatsInMultipleLines()
    {
        assertThat(Re.search("c{1}at", "crat\ncat"), is(equalTo(Regex.NOT_FOUND_INDEX)));
    }

    @Test(expected = RuntimeException.class)
    public void throwExceptionIfNoTextInPattern()
    {
        Re.search("^", "cat");
    }

    @Test(expected = RuntimeException.class)
    public void throwExceptionIfNoTextInExactNumberOfRepeatPattern()
    {
        Re.search("{1}", "cat");
    }

    @Test(expected = RuntimeException.class)
    public void cantCompileAtTheBeginningOfTheLinePatternWithLeadCharInPattern()
    {
        Re.compile("a^");
    }

    @Test(expected = RuntimeException.class)
    public void cantCompileTwoSameRegexInOnePattern()
    {
        Re.compile("?a?");
    }
}
