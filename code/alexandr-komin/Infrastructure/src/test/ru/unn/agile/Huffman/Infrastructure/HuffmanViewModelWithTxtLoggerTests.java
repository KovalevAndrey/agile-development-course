package ru.unn.agile.Huffman.Infrastructure;


import ru.unn.agile.Huffman.HuffmanViewModel;
import ru.unn.agile.Huffman.HuffmanViewModelTest;

public class HuffmanViewModelWithTxtLoggerTests extends HuffmanViewModelTest {
    @Override
    public void beforeTest() {
        HuffmanTxtLogger huffmanTxtLogger = new HuffmanTxtLogger("./HuffmanViewModelWithTxtLoggerTestLog.log");
        viewModelHuffman=new HuffmanViewModel(huffmanTxtLogger);
    }
}
