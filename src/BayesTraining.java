import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;


public class BayesTraining {

  public static final int POS = 1;
  public static final int NEG = 0;

  public static int docs_in_neg = 0;
  public static int docs_in_pos = 0;

  public static HashMap<String, Integer> neg_words;
  public static HashMap<String, Integer> pos_words;

  /**
   * @param args
   */
  public static void main(String[] args) {
    neg_words = new HashMap<String, Integer>();
    pos_words = new HashMap<String, Integer>();

    String directory = args[0];

    File dir = new File(directory);
    String[] subdir = dir.list();
    for (String f : subdir) {
      System.out.println(f);
      File trainingFiles = new File(dir + "/" + f);
      String[] files = trainingFiles.list();
      if (f.compareTo("neg") == 0) {
        for (String file : files)
          process(dir + "/" + f + "/" + file, NEG);
      } else if (f.compareTo("pos") == 0) {
        for (String file : files)
          process(dir + "/" + f + "/" + file, POS);
      }
    }

    Model m = new Model(docs_in_pos, docs_in_neg, pos_words, neg_words);

    try {
      FileOutputStream fos = new FileOutputStream("model");
      ObjectOutputStream out = new ObjectOutputStream(fos);
      out.writeObject(m);
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void process(String file, int type) {
    try {
      if (type == NEG)
        docs_in_neg++;
      else
        docs_in_pos++;

      File f = new File(file);
      BufferedReader in = new BufferedReader(new FileReader(f));
      String line = null;
      while ((line = in.readLine()) != null) {
        String[] words = line.split(" ");
        for (String word : words)
          if (word.matches(".") || word.matches(",") || word.matches("!") || word.matches("/?")) {
            //blah, ignore punctuation
          } else {
            HashMap<String, Integer> map;
            if (type == NEG)
              map = neg_words;
            else
              map = pos_words;

            Integer i = map.get(word);
            if (i == null)
              map.put(word, new Integer(0));
            else
              i++;
          }
      }
    in.close();

  } catch (IOException e) {
    e.printStackTrace();
  }
}

}
