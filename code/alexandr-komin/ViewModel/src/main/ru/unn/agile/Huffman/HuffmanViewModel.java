package ru.unn.agile.Huffman;

public class HuffmanViewModel {

    public boolean isActiveResetButton=true;
    public boolean isEditableTextArea;
    public boolean isActiveCompressButton;
    public boolean isActiveEncodingButton;
    public String status;
    public String text;

    public HuffmanViewModel() {
        reset();
    }

    public void compress() {
        try {
            text=Huffman.compress(text);
            isActiveCompressButton = false;
            isActiveEncodingButton = true;
            isEditableTextArea =false;
            status="String successful compress";
        }
        catch (RuntimeException e)
        {
            if(e.getMessage()=="Empty string are not allowed for compress");
            status="Empty string are not allowed for compress";
        }
    }
    public void expand() {
        try{
            text=Huffman.expand(text);
            isActiveCompressButton=true;
            isActiveEncodingButton=false;
            isEditableTextArea =true;
            status="String successful expand";
        }
        catch (RuntimeException e)
        {
            if(e.getMessage()=="Empty string are not allowed for expand");
            status="Empty string are not allowed for expand";
        }
    }

    public void reset() {
        isActiveCompressButton = true;
        isActiveEncodingButton = false;
        isEditableTextArea =true;
        status = "Enter string for compress";
        text="";
    }
}
