package ru.unn.agile.Huffman.Infrastructure;


import ru.unn.agile.Huffman.HuffmanViewModel;
import ru.unn.agile.Huffman.HuffmanViewModelTest;

public class ViewModelWithTxtLoggerTests extends HuffmanViewModelTest {
    @Override
    public void beforeTest() {
        TxtLogger txtLogger = new TxtLogger("./HuffmanViewModelWithTxtLoggerTestLog.log");
        viewModelHuffman=new HuffmanViewModel(txtLogger);
    }
}
