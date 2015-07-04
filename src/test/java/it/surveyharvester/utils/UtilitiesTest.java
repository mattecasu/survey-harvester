package it.surveyharvester.utils;

import static it.surveyharvester.utils.Utilities.extractListFromString;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

public class UtilitiesTest {

    @Test
    public void test() {
        List<String> extracted1 = extractListFromString("a,b,c , d", " ,");
        List<String> extracted2 = extractListFromString("a,b,c , d", ", ");
        List<String> extracted3 = extractListFromString("a,b,c,d", ",");
        List<String> extracted4 = extractListFromString("a,b,c;d", ", ");
        
        Assert.assertTrue(extracted1.containsAll(Lists.newArrayList("a", "b", "c", "d")));
        Assert.assertTrue(extracted2.containsAll(Lists.newArrayList("a", "b", "c", "d")));
        Assert.assertTrue(extracted3.containsAll(Lists.newArrayList("a", "b", "c", "d")));
        
        Assert.assertFalse(extracted4.containsAll(Lists.newArrayList("a", "b", "c", "d")));
    }
}
