import Entities.Voiture;
import UserInt.Menu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.toutesVoitures.add(new Voiture(1, menu.mesModeles[1],null,2545,"Bleu",Menu.TYPES_CARBURANTS[2], 10000));

        System.out.println("======== Bienvenu chez MotorTech ========");
        menu.start();
    }
}
