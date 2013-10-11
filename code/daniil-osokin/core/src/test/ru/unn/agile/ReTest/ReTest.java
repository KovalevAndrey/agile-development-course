package ru.unn.agile.ReTest;

import org.junit.Test;
import ru.unn.agile.Re.core.Re;
import ru.unn.agile.Re.core.Regex;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ReTest
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
    public void returnFirstOccurrenceAtTheBeginningOfTheLine()
    {
        assertThat(Re.search("^fi", "first"), is(equalTo(0)));
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

}
