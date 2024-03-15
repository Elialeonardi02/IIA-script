import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Inserisci la prima formula con la seguente forma: x,...\n");
        Expression x = new Expression(scanner.next());

        System.out.print("Inserisci la seconda formula con la seguente forma: x,...\n");
        Expression y = new Expression(scanner.next());

        if(x.listExp.size() != y.listExp.size()){
            System.out.println("Il numero dei termini dentro le espressioni non è uguale");
        } else {
            unify(x,y,new Set()).ourToString();
        }

        scanner.close();
    }

    public static Set unify(Expression x, Expression y, Set s){
        if(s.fail) return s;
        else if(x.equals(y)) return s;
    /*else if(x.listExp.size() == 0 && y.listExp.size() == 0)
      return s;*/
        else if(x.listExp.size() == 1 && isVar(x.listExp.get(0)))
            return unifyVar(x.listExp.remove(0), y.listExp.remove(0), s);
        else if(y.listExp.size() == 1 && isVar(y.listExp.get(0)))
            return unifyVar(y.listExp.remove(0), x.listExp.remove(0), s);
            // quell'else if del composto lo metto?
        else if(x.listExp.size() > 1 && y.listExp.size() > 1){
            Expression testaX = new Expression(x.listExp.remove(0));
            Expression testaY = new Expression(y.listExp.remove(0));
            return unify( x, y, unify( testaX, testaY, s));
        }
        s.setFail();
        return s;
    }

    public static Set unifyVar(String var, String x, Set s){
        int i;
        for(int j = 0; j < s.listVar.size(); j++){
            if(var.contains(s.listVar.get(j))){
                var = var.replaceAll(s.listVar.get(j), s.listVal.get(j));
            }
            if(x.contains(s.listVar.get(j))){
                x = x.replaceAll(s.listVar.get(j), s.listVal.get(j));
            }
        }
        if ((i = s.in(var)) != -1)
            return unify(new Expression(s.listVal.get(i)), new Expression(x), s);
        else if ((i = s.in(x)) != -1)
            return unify(new Expression(var), new Expression(s.listVal.get(i)), s);
        else if (x.contains(var)) {
            s.setFail();
            return s;
        }
        else return aggiungi(var, x, s);
    }

    public static Set aggiungi(String var, String x, Set s){
        s.listVar.add(var);
        s.listVal.add(x);

        for(int i = 0; i < s.listVal.size(); i++){
            for(int j = 0; j < s.listVar.size(); j++){
                if(j != i && s.listVal.get(i).contains(s.listVar.get(j))){
                    s.listVal.set(i,s.listVal.get(i).replaceAll(s.listVar.get(j), s.listVal.get(j)));
                }
            }
        }

        return s;
    }

    public static boolean isCon(String str) {
        // Confronta la stringa originale con la sua versione maiuscola
        return str.equals(str.toUpperCase());
    }

    public static boolean isVar(String str) {
        // Confronta la stringa originale con la sua versione minuscola
        return str.equals(str.toLowerCase());
    }
}

