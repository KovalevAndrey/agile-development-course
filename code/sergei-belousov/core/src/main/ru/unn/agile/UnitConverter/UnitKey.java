package ru.unn.agile.UnitConverter;

public class UnitKey {
    private final String from;
    private final String to;

    public UnitKey(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public UnitKey reverse() {
        return new UnitKey(to, from);
    }

    public String toString() {
        return from + "-" + to;
    }

    public String getFrom(){
        return from;
    }

    public String getTo(){
        return to;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof UnitKey)) return false;
        UnitKey key = (UnitKey) obj;
        return from.equals(key.from) && to.equals(key.to);
    }

    @Override
    public int hashCode() {
        return new String(from + "-" + to).hashCode();
    }
}
