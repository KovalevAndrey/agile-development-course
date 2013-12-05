package ru.unn.agile.Huffman;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HuffmanViewModelTest {

    public HuffmanViewModel viewModelHuffman;

    @Before
    public void beforeTest() {
        FakeLogger logger=new FakeLogger();
        viewModelHuffman = new HuffmanViewModel(logger);
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

    @Test (expected = IllegalArgumentException.class)
    public void nullLoggerIsNotAllowed() {
        viewModelHuffman = new HuffmanViewModel(null);
    }

    @Test
    public void canAcceptNotNullLogger() {
        assertNotNull(viewModelHuffman);
    }

    @Test
    public void canGetViewModelLog() {
        assertNotNull(viewModelHuffman.getLog());
    }

    @Test
    public void loggerAtStartIsEmpty() {
        assertEquals(0, viewModelHuffman.getLog().size());
    }

    @Test
    public void afterResetToLoggerAddOneRecord()
    {
        viewModelHuffman.reset();
        assertEquals(1,viewModelHuffman.getLog().size());
    }

    @Test
    public void afterResetLoggerContentCorrespondingRecord()
    {
        viewModelHuffman.reset();
        assertEquals(true, viewModelHuffman.getLog().get(0).indexOf(HuffmanViewModel.LogMessages.RESET)>=0);
    }

    @Test
    public void afterCompressAndAfterExpandToLoggerAddFourRecord()
    {
        compressAndExpand("test");
        assertEquals(4,viewModelHuffman.getLog().size());
    }

    @Test
    public void afterCompressEmptyStringLoggerContainRecordAboutFail()
    {
        compress("");
        assertEquals(true,viewModelHuffman.getLog().get(1).indexOf(HuffmanViewModel.LogMessages.FAIL)>=0);
    }

    @Test
    public void afterCompressNotEmptyStringLoggerContainRecordAboutSuccess()
    {
        compress("test");
        assertEquals(true,viewModelHuffman.getLog().get(1).indexOf(HuffmanViewModel.LogMessages.SUCCESS)>=0);
    }

    @Test
    public void afterExpandEmptyStringLoggerContainRecordAboutFail()
    {
        expand("");
        assertEquals(true, viewModelHuffman.getLog().get(1).indexOf(HuffmanViewModel.LogMessages.FAIL)>=0);
    }

    @Test
    public void afterExpandCorrectStringLoggerContainRecordAboutSuccess()
    {
        compressAndExpand("test");
        assertEquals(true,viewModelHuffman.getLog().get(3).indexOf(HuffmanViewModel.LogMessages.SUCCESS)>=0);
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
