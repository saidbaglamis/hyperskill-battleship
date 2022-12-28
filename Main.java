import java.util.Scanner;

public class Main {
  
  static int satir1;
  static int sutun1;
  static int satir2;
  static int sutun2;
  
  static String coordinate;
  static String[] cooArr;
  static Scanner scanner = new Scanner(System.in);
  static char[][] board = new char[10][10];
  //static char[] alphabet = {'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
  
  public static String[] kkk = {"A1 D1"};
  public static boolean CheckCollision(int x, int y) {
    for(int i = 0; i < kkk.length; i++)
    {
     	int startx = kkk[i].charAt(1) - 48;
      	int starty = kkk[i].charAt(0) - 64;
      	int endx = kkk[i].charAt(4) - 48;
      	int endy = kkk[i].charAt(3) - 64;
		if ((x <= endx + 1 && x >= startx -1) && (y <= endy + 1 && y >= starty - 1))
          return true;
      	
    }
    return false;
  }
  public static void main(String[] args) {
    System.out.println(CheckCollision(1,1));
    InitBoard();
    GetBoard();
    AircraftCarrier();
    CoordinateEdit();
    System.out.printf("%d %d", satir1, sutun1);
  
  }
  
  public static void InitBoard() {
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        board[i][j] = '~';
      }
    }
  }
  
  public static void GetBoard() {
    for (int i = 0; i < 11; i++) {
      if (i > 0) {
        System.out.printf("%c ", i + 64);
      } else {
        System.out.print("  ");
        for (int j = 1; j < 11; j++) {
          System.out.printf("%d ", j);
        }
        System.out.println();
        continue;
      }
      for (int j = 0; j < 10; j++) {
        System.out.printf("%c ", board[i - 1][j]);
      }
      System.out.println();
    }
  }

  public static void CoordinateEdit() {
    cooArr = coordinate.split(" ");
    satir1 = cooArr[0].substring(0,1).charAt(0) - 65;
    sutun1 = Integer.parseInt(cooArr[0].substring(1));
    satir2 = cooArr[1].substring(0,1).charAt(0) - 65;
    sutun2 = Integer.parseInt(cooArr[1].substring(1));
  }

  
  public static void AircraftCarrier() {
    System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells):");
    coordinate = scanner.nextLine();
      
  }
}
