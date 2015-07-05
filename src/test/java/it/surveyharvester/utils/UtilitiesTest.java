package it.surveyharvester.utils;

import static com.google.common.collect.Lists.newArrayList;
import static it.surveyharvester.utils.Utilities.extractListFromString;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class UtilitiesTest {

    List<String> listToCheck;

    @Before
    public void setup() {
        listToCheck = newArrayList("a", "b", "c", "d");
    }

    @Test
    public void test() {
        List<String> extracted1 = extractListFromString("a,b,c , d", " ,");
        List<String> extracted2 = extractListFromString("a,b,c , d", ", ");
        List<String> extracted3 = extractListFromString("a,b,c,d", ",");
        List<String> extracted4 = extractListFromString("a,b,c;d", ", ");

        assertTrue(extracted1.containsAll(listToCheck));
        assertTrue(extracted2.containsAll(listToCheck));
        assertTrue(extracted3.containsAll(listToCheck));

        assertFalse(extracted4.containsAll(listToCheck));
        assertTrue(extracted4.containsAll(newArrayList("a", "b", "c;d")));
    }
}
