import java.util.ArrayList;
import java.util.List;

public class Expression{
    List<String> listExp = new ArrayList<String>();

    public Expression(String e){
        String[] splitted = e.split(",");
        for (String s : splitted) {
            listExp.add(s);
            //System.out.println(s);
        }
    }

    public boolean equals(Expression e){
        if(listExp.size() != e.listExp.size()) return false;
        for (int i = 0; i < listExp.size(); i++) {
            if(!listExp.get(i).equals(e.listExp.get(i))) return false;
        }
        return true;
    }

}
