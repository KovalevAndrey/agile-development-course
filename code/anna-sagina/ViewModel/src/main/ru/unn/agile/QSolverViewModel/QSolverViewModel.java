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
        a = "0";
        b = "0";
        c = "0";
    }

    public void setCoefficientValue() {
        try {
            Double.parseDouble(a);
            Double.parseDouble(b);
            Double.parseDouble(c);
            this.result = "";
            isSolveButtonEnabled = true;
        } catch (NumberFormatException e){
            result = errMessage;
            isSolveButtonEnabled = false;
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
                result = "x = " + roots[0];
           }
           if (roots.length == 2){
                result = "x1 = " + roots[0] + "   x2 = " + roots[1];
           }

        }catch (InvalidParameterException e){
            result = "Oops.. something is wrong: " + e.getMessage();
        }
   }

}
