package ru.unn.agile.TC.viewmodel;

public class LogMessage {
    private String message;

    public LogMessage(MessagePattern pattern, Object... args) {
        if(pattern == null)
            throw new IllegalArgumentException("MessagePattern cannot be null");

        String stringPattern = pattern.toString();
        String slotString = "%s";

        int slots = (stringPattern.length() -
                stringPattern.replaceAll(slotString, "").length()) / slotString.length();

        if(slots != args.length)
            throw new IllegalArgumentException("Inconsistent number of arguments passed");

        message = String.format(stringPattern, args);
    }

    @Override
    public String toString() {
        return message;
    }


    public enum MessagePattern {
        LOG_INFO_VM_OK("Conversion result success", 0),
        LOG_INFO_CONVERT("Converting %s to %s", 0),
        LOG_INFO_INPUT("User entered [value %s; inScale %s; outScale %s]", 0),

        LOG_ERROR_INPUT_SCALE_IS_NULL("User haven't selected input scale", 1),
        LOG_ERROR_RESULT_SCALE_IS_NULL("User haven't selected input scale", 2),
        LOG_ERROR_WRONG_INPUT_STRING("User typed wrong input string", 3);

        MessagePattern(String message, int code) {
            this.message = message;
            this.code = code;
        }

        @Override
        public String toString() {
            return message;
        }

        private final int code;
        private final String message;
    }
}
