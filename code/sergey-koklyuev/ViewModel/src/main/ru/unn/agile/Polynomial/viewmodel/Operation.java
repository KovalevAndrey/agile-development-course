package ru.unn.agile.Polynomial.viewmodel;

enum Operation {
    ADD("Add"),
    SUB("Sub"),
    MUL("Mul");
    private final String name;

    private Operation(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }


}
