import java.util.ArrayList;
import java.util.List;

public class Expression{
    List<String> listExp = new ArrayList<String>();

    public Expression(String e){
        String[] splitted = e.split(";");
        int numParentesi = 0;
        String res = "";
        for (String s : splitted) {
            numParentesi = countOccurrences(s,'(') - countOccurrences(s,')');
            if (numParentesi == 0){ // se numero parentesi è 0
                if(res.equals(""))
                    listExp.add(s);
                else{
                    listExp.add(res + "," + s);
                    res = "";
                }
            } else {
                if (res.equals(""))
                    res = s;
                else
                    res = res + "," + s;
            }
        }
    }
    //x,y,H(x),F(x,G(x,y))
    /*
    x
    y
    H(x) -> 0
    F(x -> 1
    G(x -> 2
    y)) -> 0
     */
    // F(G(x,y),x)
    /*
    F(G(x
    y)
    x)
     */

    public boolean equals(Expression e){
        if(listExp.size() != e.listExp.size()) return false;
        for (int i = 0; i < listExp.size(); i++) {
            if(!listExp.get(i).equals(e.listExp.get(i))) return false;
        }
        return true;
    }
    public static int countOccurrences(String str, char targetChar) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == targetChar) {
                count++;
            }
        }
        return count;
    }

}
