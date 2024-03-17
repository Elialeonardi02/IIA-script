import java.util.ArrayList;
import java.util.List;

public class Set{
    List<String> listVar = new ArrayList<String>();
    List<String> listVal = new ArrayList<String>();
    public boolean fail = false;

    public Set(){

    }

    public int in(String var){
        for (int i = 0; i < listVar.size(); i++) {
            if(listVar.get(i).equals(var)) return i;
        }
        return -1;
    }

    void setFail(){
        fail = true;
    }

    public void ourToString(){
        if(!fail) {
            if (listVar.size() == 0) {
                System.out.println("Insieme vuoto");
            }
            for (int i = 0; i < listVar.size(); i++) {
                System.out.println(listVar.get(i) + "/" + listVal.get(i));
            }
        }else{
            System.out.println("FAIL");
        }

    }

}