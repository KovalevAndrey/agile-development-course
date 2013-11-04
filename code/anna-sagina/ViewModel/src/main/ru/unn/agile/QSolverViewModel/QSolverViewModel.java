package ru.unn.agile.QSolverViewModel;

import ru.unn.agile.quadsolver.QuadraticEquationSolver;
import java.security.InvalidParameterException;

public class QSolverViewModel {
   public boolean isSolveButtonEnabled = false;
   public String result = "";
   public String a;
   public String b;
   public String c;

   private  String errMessage = "Invalid coefficient found! Please don't worry and check your input..";

    public QSolverViewModel() {
        a = "";
        b = "";
        c = "";
    }

    public void setA(String a) {
        try {
            Double.parseDouble(a);
            this.a = a;
            if ( !c.equals("") && !b.equals("")) {
                isSolveButtonEnabled = true;
            }
        } catch (NumberFormatException e){
             result = errMessage;
            isSolveButtonEnabled = false;
            this.a = "";
        }
    }

    public void setB(String b) {
        try {
            Double.parseDouble(b);
            this.b = b;
            if ( !c.equals("") && !b.equals("")) {
                isSolveButtonEnabled = true;
            }

        } catch (NumberFormatException e){
            result = errMessage;
            isSolveButtonEnabled = false;
            this.b = "";
        }
    }

    public void setC(String c) {
        try {
            Double.parseDouble(a);
            this.c = c;
            if ( !a.equals("") && !b.equals("")) {
                isSolveButtonEnabled = true;
            }
        } catch (NumberFormatException e){
            result = errMessage;
            isSolveButtonEnabled = false;
            this.c = "";

        }
    }


    public void RunSolver(){
        try {
           double[] roots;
           roots = QuadraticEquationSolver.solve(Double.parseDouble(a),Double.parseDouble(b),Double.parseDouble(c));
           if (roots.length == 0){
               result = "I'm so sorry.. Your equal has no real roots at all!";
           }
           if (roots.length == 1){
                result = "The equal has only one root:\n x = " + roots[0];
           }
           if (roots.length == 2){
                result = "The equal has two real roots:\n x = " + roots[0] + "   x = " + roots[1];
           }

        }catch (InvalidParameterException e){
            result = "Oops.. something is wrong: " + e.getMessage();
        }
   }

}
