package ru.unn.agile.Huffman;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import java.util.regex.*;

public class HuffmanTest {

    @Test
    public void emptyInputStringForCompressNotAllowed(){
        try{
            Huffman.compress("");
            fail("Runtime exception was expected");
        }
        catch(RuntimeException e){
            assertEquals("Empty string are not allowed for compress",e.getMessage());
        }
        catch (Exception e){
            fail("Unexpected exception: " + e.toString());
        }
    }

    @Test
    public void outputStringAfterCompressCanContainsOnlyOneAndZero(){
        String outputText=Huffman.compress("Example");
        Pattern patternOnlyOneAndZero = Pattern.compile("^[0-1]+$");
        Matcher matcher = patternOnlyOneAndZero.matcher(outputText);
        assertEquals(true,matcher.matches());
    }

    @Test
    public void outputStringAfterCompressIsNotEmpty(){
        String outputText=Huffman.compress("Example");
        assertEquals(false,outputText.isEmpty());
    }

    @Test
    public void emptyInputStringForExpandNotAllowed(){
        try{
            Huffman.expand("");
            fail("Runtime exception was expected");
        }
        catch(RuntimeException e){
            assertEquals("Empty string are not allowed for expand",e.getMessage());
        }
        catch (Exception e){
            fail("Unexpected exception: " + e.toString());
        }
    }

    @Test
    public void inputStringWithIncorrectSymbolForExpandAreNotAllowed(){
        try{
            Huffman.expand("120010");
            fail("Runtime exception was expected");
        }
        catch(RuntimeException e){
            assertEquals("Input string for expand contains incorrect symbol",e.getMessage());
        }
        catch (Exception e){
            fail("Unexpected exception: " + e.toString());
        }
    }

    @Test
    public void stringsBeforeCompressAndAfterExpandAreEquals(){
        String inputText="Example";
        String outputAfterCompress=Huffman.compress(inputText);
        String outputAfterExpand=Huffman.expand(outputAfterCompress);
        assertEquals(inputText, outputAfterExpand);
    }

}
