package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {
  public static void main(String[] args) throws Exception {
    Reader reader = new Reader();
    double[][] data = reader.read(new File("data.txt"), 'o');
    reader.print(data, reader.getRows(data), reader.getColumns(data));
    
    data = reader.read(new File("A.txt"), 'o');
    reader.print(data, reader.getRows(data), reader.getColumns(data));
    
    data = reader.read(new File("B.txt"), 'o');
    reader.print(data, reader.getRows(data), reader.getColumns(data));
  }
  
  public int getRows(double[][] data) {
    return data.length;
  }
  
  public int getColumns(double[][] data) {
    return data[0].length;
  }
  
  public void print(double[][] data, int rows, int columns) {
    System.out.format("%d by %d matrix:%n", rows, columns);
    for(int i = 0; i < rows; i++) {
      for(int j = 0; j < columns; j++) {
        System.out.format("%.0f", data[i][j]);
      }
      System.out.println();
    }
  }
  
  public double[][] read(File file, char matchChar) throws IOException {
    List<double[]> rows = new ArrayList<>();
    
    int columns = -1;
    
    try(FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr)) {
      String line = null;
      
      int lineNo = 0;
      
      while((line = br.readLine()) != null) {
        lineNo++;
        if(columns < 0) {
          columns = line.length();
        }
        
        if(line.length() != columns) {
          throw new RuntimeException("Row size mismatch on line " + lineNo);
        }
        
        double[] rowVals = new double[columns];
        for(int i = 0; i < line.length(); i++) {
          rowVals[i] = line.charAt(i) == matchChar ? 1 : 0;
        }
        
        rows.add(rowVals);
      }
    }
    
    double[][] output = new double[rows.size()][];
    for(int i = 0; i < rows.size(); i++) {
      output[i] = rows.get(i);
    }
    
    return output;
    
  }
}
