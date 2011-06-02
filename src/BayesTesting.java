import java.io.*;

public class BayesTesting {

  public static void main(String[] args) {
    if (args.length == 1) {
      String directory = args[0];

      File dir = new File(directory);

      String[] subDirs = dir.list();
      for (String s : subDirs) {
        File trainingFiles = new File(dir + "/" + s);
        System.out.println(s);
        String[] files = trainingFiles.list();
        for (String file : files) {

        }
      }
    }
  }
}
