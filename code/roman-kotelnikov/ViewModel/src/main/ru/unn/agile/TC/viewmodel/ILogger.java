package ru.unn.agile.TC.viewmodel;

import java.util.List;

public interface ILogger {
    void putError(Errors message);
    void putMessage(String message);

    List<String> getLog();
    String getLastMessage();

    public class Messages {
        public static final String LOG_VIEW_MODEL_OK = "Conversion result success";
        public static final String LOG_CONVERT_MESSAGE = "Converting %s to %s";
        public static final String LOG_INPUT_MESSAGE = "User entered [value %s; inScale %s; outScale %s]";
    }

    public enum Errors {
        LOG_ERROR_INPUT_SCALE_IS_NULL("User haven't selected input scale", 1),
        LOG_ERROR_RESULT_SCALE_IS_NULL("User haven't selected input scale", 2),
        LOG_ERROR_WRONG_INPUT_STRING("User typed wrong input string", 3);


        Errors(String message, int code) {
            this.message = message;
            this.code = code;
        }

        @Override
        public String toString() {
            return String.format("ERROR #%2d: %s", code, message);
        }

        String message;
        int code;
    }
}

