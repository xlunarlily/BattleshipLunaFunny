import java.util.ArrayList;
public class Battlefield {
    private String[][] field;
    private ArrayList<Ship> shipList;

    public Battlefield(){
        field = new String[10][10];
        for(int r = 0; r < field.length; r++){
            for(int c = 0; c < field[0].length; c++){
                field[r][c] = "[-]";
            }
        }

        Ship car = new Ship(5, "C");
        Ship battShip = new Ship(4, "B");
        Ship sub = new Ship(3, "S");
        Ship destr = new Ship(3, "D");
        Ship patr = new Ship(2, "P");
        shipList = new ArrayList<Ship>(5);
        shipList.add(car);
        shipList.add(battShip);
        shipList.add(sub);
        shipList.add(destr);
        shipList.add(patr);
    }
    //I need a check open spot method

    public void setHoPosition(Ship s, int row, int colStart, int colEnd){
        for(int i = colStart; i <= colEnd; i++){
            field[row][i] = "[" + s.getName() + "]";
        }
    }

    public void setVertPosition(Ship s, int col, int rowStart, int rowEnd){
        for(int i = rowStart; i <= rowEnd; i++){
            field[i][col] = "[" + s.getName() + "]";
        }
    }

    public boolean checkSpotVert(int col, int rowStart, int rowEnd){


        for(int i = rowStart; i <= rowEnd; i++){
            String check = field[i][col];
            if(!check.equals("[-]")){
                return false;
            }
        }

        return true;
    }

    public boolean checkSpotHo(int row, int colStart, int colEnd){

        for(int i = colStart; i <= colEnd; i++){
            String check = field[row][i];
            if(!check.equals("[-]")){
                return false;
            }
        }

        return true;
    }

    public Ship getShip(int num){
        return shipList.get(num);
    }

    public String[][]getField(){
        return field;
    }

    public String shootGun(int row, int col, String[][] shown){
        //check if a spot is equal to "[-]"
        //All you need to see if it's over is if all spots are equal to either "[-]" or [X]"
        String status = "";
        if(!field[row][col].equals("[X]")){

            if(field[row][col].equals("[0]") || field[row][col].equals("[X]")){
                status = "redo";
            }
            else if(field[row][col].equals("[-]")){
                field[row][col] = "[0]";
                shown[row][col] = "[0]";
                status = "miss";
            }
            else{
                field[row][col] = "[X]";
                shown[row][col] = "[X]";
                status = "hit";
            }
        }
        return status;
    }

    public int checkClosedColumn(){

        for(int i = 0; i < field[0].length; i++){
            int occCol = 0;

            for(int j = 0; j < field.length; j++){
                if(field[j][i].equals("[-]")){
                    break;
                }
                else{
                    occCol++;
                }
            }
            if(occCol == 10){
                return i;
            }
        }
        return -1;
    }

    public int checkClosedRow(){

        for(int i = 0; i < field.length; i++){
            int occSpots = 0;
            for(int j = 0; j < field[0].length; j++){
                if(field[i][j].equals("[-]")){
                    break;
                }
                else{
                    occSpots++;
                }
            }
            if(occSpots == 10){
                return i;
            }
        }
        return -1;
    }

    public boolean checkOver(){

        for(int i = 0; i < field.length; i++){
            for(int j = 0; j < field[0].length; j++){
                if(!field[i][j].equals("[-]") && !field[i][j].equals("[0]") && !field[i][j].equals("[X]")){
                    return false;
                }
            }
        }
        return true;
    }

    public String toString(){
        String f = "   0  1  2  3  4  5  6  7  8  9";
        for(int r = 0; r < field.length; r++){
            f += "\n" + r + " ";
            for(int c = 0; c < field[0].length; c++){
                f += field[r][c];
            }
        }
        return f;
    }
}
