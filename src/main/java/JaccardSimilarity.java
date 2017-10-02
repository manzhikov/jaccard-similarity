/**
 * Searches for patterns inside a given data set using the Jaccard similarity coefficient algorithm.
 * 
 * @author Leonard Szigeti
 *
 */

public class JaccardSimilarity {
  private static double[][] pattern =
    {{0,1,0},
     {0,1,0},
     {1,0,1}};
  
  private static double[][] data =
      {{0,0,0,0,0,0,0,0,1,0},
      {0,0,0,0,0,0,0,1,0,1},
      {0,0,0,0,0,0,0,0,0,0},
      {1,0,0,0,1,0,0,0,0,0},
      {1,0,0,0,1,0,0,0,0,0},
      {0,1,0,1,0,1,0,0,0,0},
      {0,0,0,0,0,0,0,0,0,0},
      {0,0,0,0,0,0,0,0,1,0},
      {0,0,1,0,0,0,0,0,1,0},
      {0,0,1,0,0,0,0,1,0,0}};
      
  
  public static void main(String[] args) {
    JaccardSimilarity s = new JaccardSimilarity();
    s.scan(pattern, data, 10, 10, 3, 3, 0.50);
  }
  
  public void scan(double[][] item, double[][] data, int dataRows, int dataColumns, int itemRows, int itemColumns, double threshold) {
    double[][] buffer = new double[itemRows][itemColumns];
    
    for(int i = 0 - itemRows; i < dataRows; i++) {
      for(int j = 0 - itemColumns; j < dataColumns; j++) {
        select(buffer, data, i, j, itemRows, itemColumns);
        double similarity = similarity(buffer, item, itemRows, itemColumns);
        if(similarity >= threshold) {
          System.out.format("Found at %d, %d (%.3f)%n", i, j, similarity);
        }
      }
    }
    
  }
  
  private double[][] select(double[][] buffer, double[][] data, int row, int col, int rows, int columns) {
    for(int i = 0; i < rows; i++) {
      for(int j = 0; j < columns; j++) {
        buffer[i][j] = get(data, row + i, col + j, 0);
      }
    }
    
    return buffer;
  }
  
  private double get(double[][] data, int row, int col, double defaultVal) {
    return row < 0 || col < 0 || data.length <= row || data[row].length <= col ? defaultVal : data[row][col];
  }
  
  private double similarity(double[][] src, double[][] dst, int rows, int cols) {
    int union = 0;
    int intersect = 0;
    
    for(int i = 0; i < rows; i++) {
      for(int j = 0; j < cols; j++) {
        if(src[i][j] > 0 && dst[i][j] > 0) {
          intersect++;
        }
        if(src[i][j] > 0 || dst[i][j] > 0) {
          union++;
        }
      }
    }
    
    return (double)intersect / union;
  }

}
