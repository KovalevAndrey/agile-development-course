package ru.unn.agile.Polynomial;

class Term implements  Comparable {
    public int coefficient;
    public int degree;

    public Term(int coefficient, int degree) {
        this.coefficient = coefficient;
        this.degree = degree;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Term)
        {
            if (((Term)object).degree == degree && ((Term)object).coefficient == coefficient)
                return true;
            return false;
        }
        return false;
    }

    public void setValues(int coefficient, int degree) {
        this.coefficient = coefficient;
        this.degree = degree;
    }

    @Override
    public int compareTo(Object o) {
        Term term = (Term)o;

        if (degree > term.degree)
        {
            return -1;
        } else if (degree < term.degree) {
            return 1;
        }

        return 0;
    }
}
