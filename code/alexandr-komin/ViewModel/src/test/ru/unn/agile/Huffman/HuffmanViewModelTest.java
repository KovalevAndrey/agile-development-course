package ru.unn.agile.Huffman;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HuffmanViewModelTest {

    private HuffmanViewModel viewModelHuffman;

    @Before
    public void beforeTest() {
        viewModelHuffman = new HuffmanViewModel();
    }
    @After
    public void afterTest() {
        viewModelHuffman = null;
    }

    @Test
    public void emptyInputStringsForCompressAreNotAllowed(){
        compress("");
        assertEquals("Empty string are not allowed for compress",viewModelHuffman.status);
    }

    @Test
    public void emptyInputStringsForExpandAreNotAllowed(){
        expand("");
        assertEquals("Empty string are not allowed for expand",viewModelHuffman.status);
    }

    @Test
    public void stringAfterCompressIsNotEmpty(){
        compress("example");
        assertEquals(false,viewModelHuffman.text.isEmpty());
    }

    @Test
    public void stringAfterExpandIsNotEmpty(){
        compressAndExpand("example");
        assertEquals(false,viewModelHuffman.text.isEmpty());
    }

    @Test
    public void stringBeforeCompressAndAfterEncodingAreEquals(){
        compressAndExpand("example");
        assertEquals("example",viewModelHuffman.text);
    }

    @Test
    public void fieldsHaveDefaultValuesAfterReset(){
        assertDefaultValues();
    }

    @Test
    public void fieldsHaveDefaultValuesAfterCreating(){
        assertDefaultValues();
    }

    @Test
    public void fieldsHaveCorrectStateAfterCompress(){
        compress("example");
        assertEquals("String successful compress",viewModelHuffman.status);
        assertEquals(false,viewModelHuffman.isActiveCompressButton);
        assertEquals(true,viewModelHuffman.isActiveEncodingButton);
        assertEquals(false,viewModelHuffman.isEditableTextArea);
        assertEquals(false,viewModelHuffman.text.isEmpty());
    }

    @Test
    public void fieldsHaveCorrectStateAfterExpand(){
        compressAndExpand("example");
        assertEquals("String successful expand",viewModelHuffman.status);
        assertEquals(true,viewModelHuffman.isActiveCompressButton);
        assertEquals(false,viewModelHuffman.isActiveEncodingButton);
        assertEquals(true,viewModelHuffman.isEditableTextArea);
        assertEquals(false,viewModelHuffman.text.isEmpty());
    }

    private void compress(String string){
        viewModelHuffman.text=string;
        viewModelHuffman.compress();
    }

    private void expand(String string){
        viewModelHuffman.text=string;
        viewModelHuffman.expand();
    }

    private void compressAndExpand(String string){
        viewModelHuffman.text=string;
        viewModelHuffman.compress();
        viewModelHuffman.expand();
    }

    private void assertDefaultValues(){
        assertEquals("Enter string for compress",viewModelHuffman.status);
        assertEquals(true,viewModelHuffman.isActiveCompressButton);
        assertEquals(false,viewModelHuffman.isActiveEncodingButton);
        assertEquals(true,viewModelHuffman.isEditableTextArea);
        assertEquals("",viewModelHuffman.text);
    }
}
