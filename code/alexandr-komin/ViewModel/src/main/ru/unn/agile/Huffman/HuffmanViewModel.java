package ru.unn.agile.Huffman;

import java.util.List;

public class HuffmanViewModel {

    public class LogMessages {
        public static final String COMPRESS = "Trying to compress string: ";
        public static final String EXPAND = "Trying to expand string: ";
        public static final String RESET = "Reset";
        public static final String SUCCESS = "Operation successful";
        public static final String FAIL = "Operation failed";
    }

    public boolean isEditableTextArea;
    public boolean isActiveCompressButton;
    public boolean isActiveEncodingButton;
    public String status;
    public String text;

    private ILogger logger;

    public HuffmanViewModel(ILogger logger) {
        if (logger == null)
            throw new IllegalArgumentException("Logger can't be null");
        this.logger=logger;
        init();
    }

    public void compress() {
        try {
            logger.log(LogMessages.COMPRESS+text);
            text=Huffman.compress(text);
            logger.log(LogMessages.SUCCESS);
            isActiveCompressButton = false;
            isActiveEncodingButton = true;
            isEditableTextArea =false;
            status="String successful compress";

        }
        catch (RuntimeException e)
        {
            if(e.getMessage()=="Empty string are not allowed for compress");
            status="Empty string are not allowed for compress";
            logger.log(LogMessages.FAIL);
        }
    }
    public void expand() {
        try{
            logger.log(LogMessages.EXPAND+text);
            text=Huffman.expand(text);
            logger.log(LogMessages.SUCCESS);
            isActiveCompressButton=true;
            isActiveEncodingButton=false;
            isEditableTextArea =true;
            status="String successful expand";
        }
        catch (RuntimeException e)
        {
            logger.log(LogMessages.FAIL);
            if(e.getMessage()=="Empty string are not allowed for expand");
            status="Empty string are not allowed for expand";
        }
    }

    public void reset() {
        init();
        logger.log(LogMessages.RESET);
    }

    private void init() {
        isActiveCompressButton = true;
        isActiveEncodingButton = false;
        isEditableTextArea =true;
        status = "Enter string for compress";
        text="";
    }

    public List<String> getLog() {
        return logger.getLog();
    }
}
