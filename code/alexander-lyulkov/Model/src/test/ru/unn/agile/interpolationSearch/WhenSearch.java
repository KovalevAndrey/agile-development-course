package ru.unn.agile.interpolationSearch;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class WhenSearch {
    private InterpolationSearch interpolationSearch;

    @Before
    public void setUp() {
        interpolationSearch = new InterpolationSearch();
    }


    @Test
    public void KeyFromSingleElementArrayGives0(){
        assertSearchReturns(new int [] {5}, 5, 0);
    }

    @Test
    public void KeyNotFromSingleElementArrayGivesNotFound(){
        assertSearchReturns(new int [] {5}, 10, InterpolationSearch.NOT_FOUND);
    }


    @Test
    public void KeyFromArrayGivesIndex(){
        assertSearchReturns(new int [] {1, 4, 7, 78, 345}, 78, 3);
    }

    @Test
    public void KeyNotFromArrayGivesNotFound(){
        assertSearchReturns(new int [] {1, 4, 7, 78, 345}, 15, InterpolationSearch.NOT_FOUND);
    }

    @Test
    public void SearchSeveralTimes(){
        assertSearchReturns(new int [] {-34, 4, 56, 80, 343, 400 }, 400, 5);
        assertSearchReturns(new int [] {0, 1, 2, 5}, 8, InterpolationSearch.NOT_FOUND);
        assertSearchReturns(new int [] {10 , 20 , 46, 100, 200, 300}, 10, 0);
    }

    @Test
    public void SearchEmptyArrayGivesNotFound(){
        assertSearchReturns(new int [] {}, 15, InterpolationSearch.NOT_FOUND);
    }

    @Test
    public void SearchForTheFirstElement(){
        assertSearchReturns(new int [] {5, 6, 7, 8}, 5, 0);
    }

    @Test
    public void SearchForTheLastElement(){
        assertSearchReturns(new int [] {7, 8, 9}, 9, 2);
    }


    private void assertSearchReturns(int[] list, int key, int expected) {
        assertEquals(expected, interpolationSearch.Search(list, key));
    }
}
