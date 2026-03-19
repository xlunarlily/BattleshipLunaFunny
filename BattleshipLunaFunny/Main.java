import java.util.Scanner;

void main() {
    Scanner sc = new Scanner(System.in);

    System.out.println("Weclome to battleship!");

    Battlefield fieldAttR = new Battlefield();
    Battlefield fieldAttS = new Battlefield();
    Battlefield fieldDefR = new Battlefield();
    Battlefield fieldDefS = new Battlefield();

    int turnNum = 2;

    for(int i = 1; i < 3; i++){
        System.out.println("\nSet your ship positions player " + i);
        if(i == 1){
            System.out.println(fieldAttR.toString());
            setShips(fieldAttR);
        }
        else{
            System.out.println(fieldDefR.toString());
            setShips(fieldDefR);
        }
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    while(!fieldAttR.checkOver() && !fieldDefR.checkOver()){
        if(turnNum%2 == 0){
            turnNum = takeTurn(fieldDefR, fieldDefS, turnNum);
        }
        else{
            turnNum = takeTurn(fieldAttR, fieldAttS, turnNum);
        }
    }
    if(turnNum%2 == 0){
        System.out.println("\nPlayer 2 wins!");
    }
    else{
        System.out.println("\nPlayer 1 wins!");
    }

}
int takeTurn(Battlefield targField, Battlefield shown, int turnNum){
    Scanner sc = new Scanner(System.in);
    System.out.println("\nTarget Field: \n" + shown.toString());
    System.out.println("\nTake your turn player " + ((turnNum%2) + 1 ));

    System.out.println("\nEnter a row");
    String rowS = sc.nextLine();
    int rowUse = parseInteger(rowS);

    while(!(rowUse >= 0 && rowUse <10)) {
        System.out.println("\nEnter a valid row");
        rowS = sc.nextLine();
        rowUse = parseInteger(rowS);
    }

    System.out.println("\nEnter a column");
    String colS = sc.nextLine();
    int colUse = parseInteger(colS);

    while(!(colUse >= 0 && colUse <10)) {
        System.out.println("\nEnter a valid column");
        colS = sc.nextLine();
        colUse = parseInteger(colS);
    }

    String[][] shownField = shown.getField();
    String status = targField.shootGun(rowUse, colUse, shownField);
    System.out.println(shown.toString());

    if(status.equals("hit")){
        System.out.println("\nYou got a hit!\nYou get to shoot again.");
    }
    else if(status.equals("redo")){
        System.out.println("\nYou already shot there.\nRedo your shot.");
    }
    else{
        System.out.println("\nYou missed.");
        turnNum += 1;
    }
    return turnNum;
}

void setShips(Battlefield field){
    Scanner sc = new Scanner(System.in);
    int x = 0;
    while(x < 5) {

        Ship currShip = field.getShip(x);
        System.out.println("\nEnter an orientation: vertical OR horizontal");
        String orient = sc.nextLine();

        if(orient.equalsIgnoreCase("horizontal")){
            int colStart = -1;

            System.out.println("\nEnter a starting column between 0 and " + (10 - currShip.getLength()));

            while(!(colStart >= 0 && colStart < 10-currShip.getLength())) {

                String colStartS = sc.nextLine();
                colStart = parseInteger(colStartS);

                if (colStart >= 0 && colStart < 10 - currShip.getLength()) {
                    break;
                }

                else {
                    System.out.println("\nEnter a starting column between 0 and " + (10 - currShip.getLength()));
                }
            }
            int rowUse = -1;
            System.out.println("\nEnter a row between 0 and 9: ");

            boolean larp = false;

            while ((!(rowUse >= 0 && rowUse < 10) || !larp)) {

                String rowS = sc.nextLine();
                rowUse = parseInteger(rowS);

                if (rowUse >= 0 && rowUse < 10) {
                    larp = field.checkSpotHo(rowUse, colStart, colStart + currShip.getLength());
                    if(larp){
                        break;
                    }
                    else {
                        System.out.println("\nPlease enter a row such that the ships won't intersect");

                    }
                }

                System.out.println("\nEnter a row between 0 and 9: ");

            }
            field.setHoPosition(currShip, rowUse, colStart, colStart + currShip.getLength());
            System.out.println(field.toString());
            x++;
        }
        else if(orient.equalsIgnoreCase("vertical")){
            int rowStart = -1;
            System.out.println("\nEnter a starting row between 0 and " + (10 - currShip.getLength()));

            while(!(rowStart >= 0 && rowStart < 10-currShip.getLength())) {

                String rowStartS = sc.nextLine();
                rowStart = parseInteger(rowStartS);

                if (rowStart >= 0 && rowStart < 10 - currShip.getLength()) {
                    break;
                }

                System.out.println("\nEnter a starting row between 0 and " + (10 - currShip.getLength()));

            }
            int colUse = -1;
            System.out.println("\nEnter a column between 0 and 9: ");

            boolean larp = false;

            while ((!(colUse >= 0 && colUse < 10) || !larp)) {

                String colS = sc.nextLine();
                colUse = parseInteger(colS);

                if (colUse >= 0 && colUse < 10) {
                    larp = field.checkSpotVert(colUse, rowStart, rowStart + currShip.getLength());
                    if(larp){
                        break;
                    }
                    else {
                        System.out.println("\nPlease enter a column such that the ships won't intersect");
                    }
                }
                System.out.println("\nEnter a column between 0 and 9: ");
            }

            field.setVertPosition(currShip, colUse, rowStart, rowStart + currShip.getLength());
            System.out.println(field.toString());
            x++;
        }
        else{
            System.out.println("\nEnter a valid orientation");
        }
    }
}

int parseInteger(String entered){
    try{
        int num = Integer.parseInt(entered);
        return num;
    }
    catch(NumberFormatException e){

    }
    return -1;
}