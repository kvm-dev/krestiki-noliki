package ru.geekbrains.krestikinoliki;
import java.util.Random;
import java.util.Scanner;

public class krestikinoliki {

    
    private static  char PLAYER = 'X'; 
    private static char ANGRY_AI = 'O'; 
    private static final char TILE = '.';
    private static final Scanner READER = new Scanner(System.in);
    private static final Random RANDOM = new Random();
    private static char field[][];
    private static int fieldSizeX;
    private static int fieldSizeY;
    //для AI запоминаем ход игрока и от этого пляшем в последнем задании
    private static int userX;
    private static int userY;

   



    //init field
    private static void initField(){
        fieldSizeX = 3;
        fieldSizeY = 3;
        field = new char[fieldSizeY][fieldSizeX];
        for(int y=0;y<fieldSizeY;y++){
            for (int x=0; x<fieldSizeX;x++)
            {
                field[y][x] = TILE;
            }
        }
    }

    //print field
    private static void printField(){
        for (int i=0; i<fieldSizeY; i++) {
            for (int j=0; j<fieldSizeX; j++) {
                System.out.print(field[i][j]);
                }
        System.out.println();
        }

    }

    //player turn
    private static void playerTurn(){
        int x, y;
        do {
            System.out.print("Введит координаты хода X и Y (от 1 до 3) через пробел >>>");
            x = READER.nextInt() -1;
            y = READER.nextInt() -1;
            userX = x;
            userY = y;
        } while(!isCellValid(x, y) || !isCellEmpty(x, y));

        field[y][x] = PLAYER;
    }

    private static boolean isCellEmpty(int x, int y) {
        return field[y][x] ==TILE;
    }

    private static boolean isCellValid(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    private static void aiTurn(){
        int x, y;
        do {
            //дефолтная версия
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);

            //незаконченный ai, но смысл я думаю понятен... 100500 условий по итогу, но на каждый ход игрока AI выберет наиболее подходящий вариант.
            //немного рандома оставим все же, дабы выбирать разные варианты хода в зависимости от ситуации


            if(userX==0 && userY==0)
            {
                int angry = RANDOM.nextInt(2);
                if(angry==0)
                {
                    x = 1;y=0;
                }
                if(angry==1)
                {
                    x = 1;y=1;
                }
                if(angry==2)
                {
                    x = 0;y=1;
                }

            }

            if(userX==0 && userY==1)
            {
                int angry = RANDOM.nextInt(4);
                if(angry==0)
                {
                    x =0;y=0;
                }
                if(angry==1)
                {
                    x = 1;y=0;
                }
                if(angry==2)
                {
                    x = 1;y=1;
                }
                if(angry==3)
                {
                    x = 1;y=2;
                }
                if(angry==4)
                {
                    x = 0;y=2;
                }

            }


            // ну и далее по аналогиии... генерим рандомно число, соответствующее кол-ву возможных ходов.

        }

        while(!isCellEmpty(x, y));
        field[y][x] = ANGRY_AI;

    }

    // checkWin
    private static boolean checkWin(char c){
        //horizontal
            for(int y=0;y<fieldSizeY;y++) {
            int x=fieldSizeX-1;
            if(field[y][x-2]==c & field[y][x-1]==c && field[y][x]==c){
                return true;
            }


        }

        //vertical
            for(int y=0;y<fieldSizeY;y++) {
            int x=fieldSizeX-1;
            if(field[x-2][y]==c & field[x-1][y]==c && field[x][y]==c){
                return true;
            }


        }

        //diagonal
        if(field[0][0] ==c && field[1][1] == c && field[2][2] == c) return true;
        if(field[0][2] ==c && field[1][1] == c && field[2][0] == c) return true;
        //здесь наверное можно былоб как-то вложенным циклом решить, но я не придумал как лучше, получалось по кол-ву кода примерно также.

        //про поле 5x5 и кол-ва фишек 4, для начала поменял бы филдсайз для x и y на 5, а код проверки ниже, как реализовать цикл, чтоб в нем был и инкремент и декремент не придумал... еще думал про вариант в 4 цикла, но это ничем не лучше if
        //if(field[0][0] ==c && field[1][1] == c && field[2][2] == c && field[3][3]==c) return true;
        //if(field[1][1] ==c && field[2][2] == c && field[3][3] == c && field[4][4]==c) return true;
        //if(field[0][1] ==c && field[1][2] == c && field[2][3] == c && field[3][4]==c) return true;
        //if(field[1][0] ==c && field[2][1] == c && field[3][2] == c && field[4][3]==c) return true;


        //if(field[0][4] ==c && field[1][3] == c && field[2][2] == c && field[3][1]) return true;
        //if(field[1][3] ==c && field[2][2] == c && field[3][1] == c && field[4][0]) return true;
        //if(field[0][3] ==c && field[1][2] == c && field[2][1] == c && field[3][0]) return true;
        //if(field[1][4] ==c && field[2][3] == c && field[3][2] == c && field[4][1]) return true;
        return false;
    }

    //checkDraw
    private static boolean checkDraw(){
        for(int y = 0; y<fieldSizeY;y++){
            for (int x=0;x<fieldSizeX;x++){
                if(isCellEmpty(x, y)){
                   return false;
                }
            }
        }
        return true;
    }

    private static boolean gameChecks (char dot, String s){
        if(checkWin(dot)){
            System.out.println(s);
            return true;
        }
        if(checkDraw()){
            System.out.println("Ничья!");
            return true;
        }
        return false;
    }
    public static void main (String[] args)
    {
        while (true) {
            initField();
            printField();
            while (true) {
                playerTurn();
                printField();
                if (gameChecks(PLAYER, "Человек победил")) break;
                aiTurn();
                printField();
                if (gameChecks(ANGRY_AI, "Машина победила")) break;
            }
            System.out.println("Сыграть еще? Если да - нажмите Y");
            if(!READER.next().equals("Y"))
                break;
        }


        //while end game
        //while round
    }
}
