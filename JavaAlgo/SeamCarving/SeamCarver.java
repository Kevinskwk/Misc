import edu.princeton.cs.algs4.Picture;
import java.awt.Color;

public class SeamCarver {
   private static final int BORDER_ENERGY = 1000;
   private int width;
   private int height;
   private Picture p;

   // create a seam carver object based on the given picture
   public SeamCarver(Picture picture) {
      if (picture == null)
         throw new IllegalArgumentException("null argument");

      p = new Picture(picture);
      width = picture.width();
      height = picture.height();
   }

   // current picture
   public Picture picture() {
      Picture picture = new Picture(width, height);
      for (int x = 0; x < width; ++x) {
         for (int y = 0; y < height; ++y) {
               picture.set(x, y, p.get(x, y));
         }
      }
      return picture;
   }

   // width of current picture
   public int width() {
      return width;
   }

   // height of current picture
   public int height() {
      return height;
   }

   private int gradientSquare(int x1, int y1, int x2, int y2) {
      Color color1 = p.get(x1, y1);
      Color color2 = p.get(x2, y2);
      int R = color1.getRed() - color2.getRed();
      int G = color1.getGreen() - color2.getGreen();
      int B = color1.getBlue() - color2.getBlue();
      return R * R + G * G + B * B;
   }

   // energy of pixel at column x and row y
   public double energy(int x, int y) {
      if (x < 0 || x >= this.width() || y < 0 || y >= this.height())
         throw new IllegalArgumentException("x or y out of range");

      if (x == 0 || x == width - 1 || y == 0 || y == height - 1)
         return BORDER_ENERGY;

      int deltaX = gradientSquare(x + 1, y, x - 1, y);
      int deltaY = gradientSquare(x, y + 1, x, y - 1);
      return Math.sqrt(deltaX + deltaY);
   }

   // sequence of indices for horizontal seam
   public int[] findHorizontalSeam() {
      double[][] sum = new double[2][height];
      int[][] parent = new int[width][height];
      for (int y = 0; y < height; y++) {
         sum[0][y] = BORDER_ENERGY;
         parent[0][y] = y;
      }

      for (int x = 1; x < width; x++) {
         for (int y = 0; y < height; y++) {
            double temp = sum[(x - 1) % 2][y];
            parent[x][y] = y;
            if (y > 0 && sum[(x - 1) % 2][y - 1] < temp) {
               temp = sum[(x - 1) % 2][y - 1];
               parent[x][y] = y - 1;
            }

            if (y < height - 1 && sum[(x - 1) % 2][y + 1] < temp) {
               temp = sum[(x - 1) % 2][y + 1];
               parent[x][y] = y + 1;
            }
            sum[x % 2][y] = energy(x, y) + temp;
         }
      }

      int i = 0;
      for (int y = 1; y < height; y++) {
         if (sum[(width - 1) % 2][y] < sum[(width - 1) % 2][i]) {
            i = y;
         }
      }
      int [] seam = new int[width];
      seam[width - 1] = i;
      for (int x = width - 2; x >= 0; x--) {
         seam[x] = parent[x+1][seam[x+1]];
      }
      return seam;
   }

   // sequence of indices for vertical seam
   public int[] findVerticalSeam() {
      double[][] sum = new double[width][2];
      int[][] parent = new int[width][height];
      for (int x = 0; x < width; x++) {
         sum[x][0] = BORDER_ENERGY;
         parent[x][0] = x;
      }

      for (int y = 1; y < height; y++) {
         for (int x = 0; x < width; x++) {
            double temp = sum[x][(y - 1) % 2];
            parent[x][y] = x;
            if (x > 0 && sum[x - 1][(y - 1) % 2] < temp) {
               temp = sum[x - 1][(y - 1) % 2];
               parent[x][y] = x - 1;
            }

            if (x < width - 1 && sum[x + 1][(y - 1) % 2] < temp) {
               temp = sum[x + 1][(y - 1) % 2];
               parent[x][y] = x + 1;
            }
            sum[x][y % 2] = energy(x, y) + temp;
         }
      }

      int i = 0;
      for (int x = 1; x < width; x++) {
         if (sum[x][(height - 1) % 2] < sum[i][(height - 1) % 2]) {
            i = x;
         }
      }
      int [] seam = new int[height];
      seam[height - 1] = i;
      for (int y = height - 2; y >= 0; y--) {
         seam[y] = parent[seam[y+1]][y+1];
      }
      return seam;
   }

   // remove horizontal seam from current picture
   public void removeHorizontalSeam(int[] seam) {
      if (seam == null || seam.length != width())
         throw new IllegalArgumentException("Illegal seam argument");

      if (height <= 1)
         throw new IllegalArgumentException("Height <= 1");
      
      for (int x = 0; x < width(); x++) {
         if (seam[x] < 0 || seam[x] >= height || (x > 0 && Math.abs(seam[x] - seam[x-1]) > 1))
            throw new IllegalArgumentException("Illegal seam");

         for (int y = seam[x]; y < height - 1; y++)
            p.set(x, y, p.get(x, y + 1));
      }

      height--;
   }

   // remove vertical seam from current picture
   public void removeVerticalSeam(int[] seam) {
      if (seam == null || seam.length != height())
         throw new IllegalArgumentException("Illegal seam argument");

      if (width <= 1)
         throw new IllegalArgumentException("Width <= 1");
      
      for (int y = 0; y < height(); y++) {
         if (seam[y] < 0 || seam[y] >= width || (y > 0 && Math.abs(seam[y] - seam[y-1]) > 1))
            throw new IllegalArgumentException("Illegal seam");

         for (int x = seam[y]; x < width - 1; x++)
            p.set(x, y, p.get(x + 1, y));
      }

      width--;
   }

   //  unit testing (optional)
   public static void main(String[] args) {

   }

}