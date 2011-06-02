import java.io.Serializable;
import java.util.HashMap;


public class Model implements Serializable{

  private final int docs_in_pos;
  private final int docs_in_neg;
  private final HashMap<String, Integer> negWords;
  private final HashMap<String, Integer> posWords;

  public Model(int pos, int neg, HashMap<String, Integer> posHash, HashMap<String,Integer> negHash) {
    docs_in_pos = pos;
    docs_in_neg = neg;
    posWords = posHash;
    negWords = negHash;
  }


}
