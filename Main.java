import java.util.Scanner;

public class Main {
  
  static int satir1;package battleship;

import java.util.Scanner;

public class Main {
    static int health = 17;
    static String[] shotCooArr;
    static String shotCoordinate;
    static int coo1;
    static int coo2;
    static int satir1;
    static int sutun1;
    static int satir2;
    static int sutun2;
    static boolean line;

    static String coordinate;
    static String[] cooArr;
    static Scanner scanner = new Scanner(System.in);
    static char[][] board = new char[12][12];
    static char[][] fog = new char[12][12];
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
        InitBoard();
        InıtFog();
        GetBoard();
        AircraftCarrier();
        Battleship();
        Submarine();
        Cruiser();
        Destroyer();
        System.out.println("The game starts!");
        GetFog();
        while (health != 0) {
            GameStart();
        }


        //GetFog();
        //System.out.printf("%d %d %d %d", satir1, sutun1, satir2, sutun2);

    }

    public static void InitBoard() {
        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                board[i][j] = '~';
            }
        }
    }

    public static void GetBoard() {
        for (int i = 1; i < 11; i++) {
            if (i == 1) {
                System.out.print("  ");
                for (int j = 1; j < 11; j++) {
                    System.out.printf("%d ", j);
                }
                System.out.println();
            }
            System.out.printf("%c ", i + 64);
            for (int j = 1; j < 11; j++) {
                System.out.printf("%c ", board[i][j]);
            }
            System.out.println();
        }
    }

    public static void GameStart() {
        ShotCoordinateCheck();
        ShotLocator();
    }

    public static void ShotLocator() {
        if (board[coo1][coo2] == 'O' || board[coo1][coo2] == 'X') {

            if (board[coo1][coo2] == 'O') {
                board[coo1][coo2] = 'X';
                fog[coo1][coo2] = 'X';
                health--;
            }
            if (health == 0) {
                GetFog();
                System.out.println("You sank the last ship. You won. Congratulations!");
                System.exit(0);
            }
            GetFog();
            System.out.println("You hit a ship! Try again:");
            GetBoard();

        } else {
            board[coo1][coo2] ='M';
            fog[coo1][coo2] = 'M';
            GetFog();
            System.out.println("You missed! Try again:");
            GetBoard();

        }
    }

    public static void ShotCoordinateCheck() {
        shotCoordinate = scanner.nextLine();
        //shotCooArr = shotCoordinate.substring(0);
        coo1 = shotCoordinate.charAt(0) - 64;
        coo2 = Integer.parseInt(shotCoordinate.substring(1));
        //System.out.println("coo1:" + coo1 +" " + coo2);
        if (!(coo1 >= 1 && coo1 <= 10) || !(coo2 >= 1 && coo2 <= 10)) {
            System.out.println("Error! You entered the wrong coordinates! Try again:");
            ShotCoordinateCheck();
        }
    }

    public static void CoordinateEdit() {
        coordinate = scanner.nextLine();
        cooArr = coordinate.split(" ");
        satir1 = cooArr[0].substring(0,1).charAt(0) - 64;
        sutun1 = Integer.parseInt(cooArr[0].substring(1));
        satir2 = cooArr[1].substring(0,1).charAt(0) - 64;
        sutun2 = Integer.parseInt(cooArr[1].substring(1));
        DiagonalCheck();
        CollisionCheck();
    }

    public static void ShipLocator() {

        if (Line()) {
            int max = Math.max(sutun1, sutun2);
            int min = Math.min(sutun1, sutun2);
            for (int i = min; i <= max; i++) {
                board[satir1][i] = 'O';
            }
        } else {
            int max = Math.max(satir1, satir2);
            int min = Math.min(satir1, satir2);
            for (int i = min; i <= max; i++) {
                board[i][sutun1] = 'O';
            }
        }
    }

    public static boolean Line() {
        if (satir1 == satir2) {
            return true;
        } else if (sutun1 == sutun2) {
            return false;
        }
        return false;
    }

    public static void DiagonalCheck() {
        if (satir1 != satir2 && sutun1 != sutun2) {
            System.out.println("Error! Wrong ship location! Try again:");
            CoordinateEdit();
        }
    }


    public static void AircraftCarrier() {
        System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells):");
        CoordinateEdit();
        if (Line()) {
            if (Math.abs(sutun1 - sutun2) != 4) {
                System.out.println("Error! Wrong length of the Aircraft Carrier! Try again:");
                CoordinateEdit();
            }
        } else {
            if (Math.abs(satir1 - satir2) != 4) {
                System.out.println("Error! Wrong length of the Aircraft Carrier! Try again:");
                CoordinateEdit();
            }
        }
        ShipLocator();
        GetBoard();
    }

    public static void Battleship() {
        System.out.println("Enter the coordinates of the Battleship (4 cells):");
        CoordinateEdit();
        if (Line()) {
            if (Math.abs(sutun1 - sutun2) != 3) {
                System.out.println("Error! Wrong length of the Battleship! Try again:");
                CoordinateEdit();
            }
        } else {
            if (Math.abs(satir1 - satir2) != 3) {
                System.out.println("Error! Wrong length of the Battleship! Try again:");
                CoordinateEdit();
            }
        }
        ShipLocator();
        GetBoard();
    }

    public static void Submarine() {
        System.out.println("Enter the coordinates of the Submarine (3 cells):");
        CoordinateEdit();
        if (Line()) {
            if (Math.abs(sutun1 - sutun2) != 2) {
                System.out.println("Error! Wrong length of the Submarine! Try again:");
                CoordinateEdit();
            }
        } else {
            if (Math.abs(satir1 - satir2) != 2) {
                System.out.println("Error! Wrong length of the Submarine! Try again:");
                CoordinateEdit();
            }
        }
        ShipLocator();
        GetBoard();
    }

    public static void Cruiser() {
        System.out.println("Enter the coordinates of the Cruiser (3 cells):");
        CoordinateEdit();
        if (Line()) {
            if (Math.abs(sutun1 - sutun2) != 2) {
                System.out.println("Error! Wrong length of the Cruiser! Try again:");
                CoordinateEdit();
            }
        } else {
            if (Math.abs(satir1 - satir2) != 2) {
                System.out.println("Error! Wrong length of the Cruiser! Try again:");
                CoordinateEdit();
            }
        }
        ShipLocator();
        GetBoard();
    }

    public static void Destroyer() {
        System.out.println("Enter the coordinates of the Destroyer (2 cells):");
        CoordinateEdit();
        if (Line()) {
            if (Math.abs(sutun1 - sutun2) != 1) {
                System.out.println("Error! Wrong length of the Destroyer! Try again:");
                CoordinateEdit();
            }
        } else {
            if (Math.abs(satir1 - satir2) != 1) {
                System.out.println("Error! Wrong length of the Destroyer! Try again:");
                CoordinateEdit();
            }
        }
        ShipLocator();
        GetBoard();
    }

    public static void CollisionCheck() {
        //System.out.printf("%d %d %d %d\n", satir1, sutun1, satir2, sutun2);
            int startx = Math.min(sutun1, sutun2) - 1;
            int starty = Math.min(satir1, satir2) - 1;
            int endx = Math.max(sutun1, sutun2) + 1;
            int endy = Math.max(satir1, satir2) + 1;
            for (int i = starty; i <= endy; i++) {
                for (int j = startx; j <= endx; j++) {
                    if (board[i][j] == 'O') {
                        System.out.println("Error! You placed it too close to another one. Try again:");
                        //System.out.printf("%d %d", i, j);
                        CoordinateEdit();
                        return;
                    }
                }
            }
        }

        public static void InıtFog() {
            for (int i = 1; i < 11; i++) {
                for (int j = 1; j < 11; j++) {
                    fog[i][j] = '~';
                }
            }

        }

        public static void GetFog() {
            for (int i = 1; i < 11; i++) {
                if (i == 1) {
                    System.out.print("  ");
                    for (int j = 1; j < 11; j++) {
                        System.out.printf("%d ", j);
                    }
                    System.out.println();
                }
                System.out.printf("%c ", i + 64);
                for (int j = 1; j < 11; j++) {
                    System.out.printf("%c ", fog[i][j]);
                }
                System.out.println();
            }
        }



}

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
