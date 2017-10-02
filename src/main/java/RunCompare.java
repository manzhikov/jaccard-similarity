import java.io.File;

import util.Reader;

public class RunCompare {
  public static void main(String[] args) throws Exception {
    final double threshold = 0.60;
    
    Reader reader = new Reader();
    double[][] data = reader.read(new File("data.txt"), 'o');
    double[][] sampleA = reader.read(new File("A.txt"), 'o');
    double[][] sampleB = reader.read(new File("B.txt"), 'o');
    
    int dataRows = reader.getRows(data);
    int dataCols = reader.getColumns(data);
    int sampleARows = reader.getRows(sampleA);
    int sampleACols = reader.getColumns(sampleA);
    int sampleBRows = reader.getRows(sampleB);
    int sampleBCols = reader.getColumns(sampleB);
    
    reader.print(data, dataRows, dataCols);
    reader.print(sampleA, sampleARows, sampleACols);
    reader.print(sampleB, sampleBRows, sampleBCols);
    
    JaccardSimilarity s = new JaccardSimilarity();
    System.out.println("Scanning for A:");
    s.scan(sampleA, data, dataRows, dataCols, sampleARows, sampleACols, threshold);
    System.out.println("Scanning for B:");
    s.scan(sampleB, data, dataRows, dataCols, sampleBRows, sampleBCols, threshold);
  }
}
