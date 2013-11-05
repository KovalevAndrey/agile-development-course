package ru.unn.agile.Re.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.Re.model.ReError;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

public class RegexViewModelTests
{
    private RegexViewModel viewModel;

    @Before
    public void setUp()
    {
        viewModel = new RegexViewModel();
    }

    @After
    public void tearDown()
    {
        viewModel = null;
    }

    @Test
    public void canSetDefaultStatus()
    {
        assertEquals("", viewModel.status);
    }

    @Test
    public void canSetDefaultPattern()
    {
        assertEquals("", viewModel.pattern);
    }

    @Test
    public void canSetDefaultText()
    {
        assertEquals("", viewModel.text);
    }

    @Test
    public void setStatusMessageWithZeroFirstOccurrenceWhenPatternIsEmpty()
    {
        viewModel.text = "cat\n";
        viewModel.search();
        assertThat(viewModel.status, is(equalTo(Status.FIRST_OCCURRENCE_IS + 0)));
    }

    @Test
    public void setNothingFoundStatusWhenSearchInEmptyText()
    {
        viewModel.pattern = "cat";
        viewModel.search();
        assertThat(viewModel.status, is(equalTo(Status.NOTHING_FOUND)));
    }

    @Test
    public void setMultipleRegexMessageOnMultipleRegex()
    {
        viewModel.pattern = "?{1}*";
        viewModel.search();
        assertThat(viewModel.status, is(equalTo(ReError.MULTIPLE_REGEX)));
    }

    @Test
    public void setNotFoundMessageIfPatternNotFound()
    {
        viewModel.text = "dog";
        viewModel.pattern = "{1}cat";
        viewModel.search();
        assertThat(viewModel.status, is(equalTo(Status.NOTHING_FOUND)));
    }

    @Test
    public void setFoundMessageWhenPatternIsFound()
    {
        viewModel.text = "dog\ncat\n";
        viewModel.pattern = "^cat";
        viewModel.search();
        assertThat(viewModel.status, is(equalTo(Status.FIRST_OCCURRENCE_IS + 4)));
    }

    @Test
    public void setSameStatusWithSamePattern()
    {
        viewModel.text = "dog\ncat\n";
        viewModel.pattern = "^cat";
        viewModel.search();

        String previousStatus = viewModel.status;
        viewModel.search();

        assertEquals(viewModel.status, previousStatus);
    }

    @Test
    public void setDifferentStatusWithDifferentPattern()
    {
        viewModel.text = "dog\ncat\n";
        viewModel.pattern = "^cat";
        viewModel.search();

        String previousStatus = viewModel.status;
        viewModel.pattern = "^dog";
        viewModel.search();

        assertNotEquals(viewModel.status, previousStatus);
    }

}
