package UserInt;

import Classes.*;
import data_access.entities.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private Scanner scan = new Scanner(System.in);
    public List<Voiture> toutesVoitures = new VoitureDAOImpl().getAll();
    public List<Modele> tousModeles = new ModeleDAOImpl().getAll();
    public List<Marque> toutesMarques = new MarqueDAOImpl().getAll();
    public List<Option> toutesOptions = new OptionDAOIpml().getAll();

    public static final String[] TYPES_CARBURANTS = new String[]{"Essence", "Diesel", "LPG"};

    //region Avant DB
//    public Marque[] mesMarques = new Marque[]{new Marque(1, "Ford"),
//            new Marque(2, "BMW"),
//            new Marque(3,"Volkswagen"),
//            new Marque(4, "Audi"),
//            new Marque(5, "Mercedes")};
//
//    public Modele[] mesModeles = new Modele[]{new Modele(1, "Fiesta", mesMarques[0]),
//            new Modele(2, "Serie 1", mesMarques[1]),
//            new Modele(3, "Polo", mesMarques[2]),
//            new Modele(4, "A1", mesMarques[3]),
//            new Modele(5, "class A", mesMarques[4])};
//
//    public Option[] mesOptions = new Option[]{new Option(1, "ABS", 2000.),
//            new Option(2, "Bluetooth", 200.),
//            new Option(3, "Airco", 1000.),
//            new Option(4, "Sièges chaufants", 2000.),
//            new Option(5, "Camera de recul", 2500.),
//            new Option(6, "Capteurs de recul", 1000.),
//            new Option(7, "GPS", 2000.)
//            };
    //endregion

    public int afficherMenu() {

        boolean correct = false;
        int choix=0;

        System.out.println("\n\nQue voulez-vous faire ?");
        do{

            System.out.println("1. Ajouter une nouvelle voiture");
            System.out.println("2. Afficher toutes les voitures");
            System.out.println("3. Modifier une voiture");
            System.out.println("4. Supprimer une voiture");
            System.out.println("5. Quitter");
            System.out.print("=> ");

            try {

                choix = scan.nextInt();

                if (choix != 1 && choix != 2 && choix != 3 && choix != 4 && choix != 5) {
                    correct = false;
                    System.out.println("Vous n'avez que 5 possibilités\n\n");
                }else
                    correct = true;

            }catch (InputMismatchException ex){
                scan.nextLine();
                correct = false;
                System.out.println("Seul les chiffres sont autorisés!\n\n");
            }

        }while (!correct);

        return choix;

    }

    public void afficherToutesVoitures() {
        if(toutesVoitures.size() == 0)
            System.out.println("*Aucune voiture dans le garage!*");
        else
            toutesVoitures.stream()
                .forEach(System.out::println);
    }

    public void start() {

        int choix;

        do{

            choix = afficherMenu();

            switch (choix){
                case 1:
                    Voiture nouvelleV = creerVoiture();

                    if (nouvelleV != null) {
                        if (new VoitureDAOImpl().insert(nouvelleV)) {
                            toutesVoitures.add(nouvelleV);
                            System.out.println(nouvelleV+"** a bien été ajouté à la BD ! **");
                        }
                        else
                            System.out.println("\n** Erreur lors de l'ajout de la nouvelle voiture dans la BD ! **");
                    }else
                        System.out.println("\n** Erreur lors de la création de la nouvelle voiture ! **");
                    break;
                case 2:
                    afficherToutesVoitures();
                    break;
                case 3:
                    if(toutesVoitures.size() > 0)
                        modifierVoiture();
                    else
                        System.out.println("-- Aucune voiture dans le garage! --");
                    break;
                case 4:
                    supprimerVoiture();
                    break;
                case 5:
                    System.out.println("======= Merci de nous avoir choisi. Au revoir et à très bientôt che MotorTech ! =======");
                    break;
                default:
                    System.out.println("Choix invalide !");
            }
        }while (choix != 5);
    }

    private void modifierVoiture() {
        boolean continuer = true, correct = true;

        System.out.println("Que voulez-vous modifier?");
        while (continuer){

            do {
                System.out.println("\n1. Modifier les options");
                System.out.println("2. Modifier la couleur");
                System.out.println("3. Quitter");
                System.out.print("=> ");

                try{
                    int choix = scan.nextInt();

                    if (choix == 1 || choix == 2 || choix == 3)
                        correct = true;
                    else
                        correct = false;

                    switch (choix) {
                        case 1:
                            modifierOptions();
                            break;
                        case 2:
                            modifierCouleur();
                            break;
                        case 3:
                            continuer = false;
                            break;

                        default:
                            System.out.println("Vous n'avez que 3 possibilités\n\n");

                    }
                }catch (InputMismatchException ex){
                    scan.nextLine();
                    System.err.println("Seul les chiffres et une virgule sont autorisés!");
                }

            }while (!correct);
        }
    }

    private void modifierOptions() {
        int idCible;
        Voiture found;
        System.out.println("Voici la liste de(s) voiture(s):");

        do {
            found = null;
            afficherToutesVoitures();
            System.out.println("Saisissez l'ID de la voiture à modifier");
            System.out.print("=> ");

            try{
                idCible = scan.nextInt();
                scan.nextLine();//for the next scan

                for (int i = 0; i < toutesVoitures.size() && found == null; i++)
                    if (toutesVoitures.get(i).getId_voiture() == idCible)
                        found = toutesVoitures.get(i);

                if(found != null){
                    //if (!(found.getListOptions().size() == toutesOptions.size()))
                    boolean correct;
                    int choix=0;

                    do {
                        System.out.println("1. Ajouter des options");
                        System.out.println("2. Retirer des options");
                        System.out.print("=> ");

                        try {

                            choix = scan.nextInt();

                            if (choix != 1 && choix != 2) {
                                correct = false;
                                System.out.println("Vous n'avez que 2 possibilités\n\n");
                            } else
                                correct = true;

                        } catch (InputMismatchException ex) {
                            scan.nextLine();
                            correct = false;
                            System.out.println("Seul les chiffres sont autorisés!\n\n");
                        }
                    }while (!correct);

                    List<Option> resList = null;

                    if (choix == 1)
                        resList = newOptions(found.getListOptions());
                    else if (choix == 2)
                        resList = removeOptions(found.getListOptions());

                    if (resList != found.getListOptions()){
                        if (new VoitureDAOImpl().update(found)){
                            toutesVoitures.set(idCible, found);
                            System.out.println("-- Les options de la voiture #"+found.getId_voiture()+" ont été modifiés ! --");
                        }else
                            System.out.println("** Erreur lors de la modification des options de la voiture #"+found.getId_voiture()+" ! **");
                    }

                }else
                    System.out.println("Aucune voiture ne possède l'ID ("+idCible+")!");
            }catch (InputMismatchException ex){
                scan.nextLine();
                found = null;
                System.err.println("Seul les chiffres et une virgule sont autorisés!");
            }
        }while (found == null);
    }

    private void modifierCouleur() {
        int idCible;
        Voiture found;
        System.out.println("Voici la liste de(s) voiture(s):");

        do {
            found = null;
            afficherToutesVoitures();
            System.out.println("Saisissez l'ID de la voiture à modifier");
            System.out.print("=> ");

            try{
                idCible = scan.nextInt();
                scan.nextLine();//for the next scan

                for (int i = 0; i < toutesVoitures.size() && found == null; i++)
                    if (toutesVoitures.get(i).getId_voiture() == idCible)
                        found = toutesVoitures.get(i);

                if(found != null){
                    System.out.println("Veuillez entrer la nouvelle couleur");
                    System.out.print("=> ");
                    String couleur = scan.nextLine();

                    found.setCouleur(couleur);

                    if(!new VoitureDAOImpl().update(found)){
                        System.out.println("** Erreur lors de la modification de la couleur ! **");
                        return;
                    }else {
                        toutesVoitures.set(idCible, found);
                        System.out.println("-- La couleur de la voiture #"+found.getId_voiture()+" a bien été modifiée ! --");
                    }
                }else
                    System.out.println("Aucune voiture ne possède l'ID ("+idCible+")!");
            }catch (InputMismatchException ex){
                scan.nextLine();
                found = null;
                System.err.println("Seul les chiffres et une virgule sont autorisés!");
            }
        }while (found == null);

    }


    public void supprimerVoiture(){

        if(toutesVoitures.size() == 0)
            System.out.println("*Aucune voiture dans le garage!*");
        else{
            int idCible, nbElm;
            Voiture found = null;
            System.out.println("Voici la liste de(s) voiture(s):");

            do {
                found = null;
                afficherToutesVoitures();
                System.out.println("Saisissez l'ID de la voiture à supprimer");
                System.out.print("=> ");

                try{
                    idCible = scan.nextInt();

                    for (int i = 0; i < toutesVoitures.size() && found == null; i++)
                        if (toutesVoitures.get(i).getId_voiture() == idCible)
                            found = toutesVoitures.get(i);

                    if(found != null){
                        if (new VoitureDAOImpl().delete(found.getId_voiture())){
                            toutesVoitures.remove(found);
                            System.out.println("\n-- Voiture supprimée --");
                            System.out.println(found);
                        }else
                            System.out.println("\n** Erreur lors de la suppression, la voiture: "+found+" n'a pas été supprimé! **");
                    }else
                        System.out.println("Aucune voiture ne possède l'ID ("+idCible+")!");


                }catch (InputMismatchException ex){
                    scan.nextLine();
                    found = null;
                    System.err.println("Seul les chiffres et une virgule sont autorisés!");
                }
            }while (found == null);

            scan.nextLine();//for the next scan
        }

    }

    private int genererIdVoiture(){
        if (toutesVoitures.size() == 0)
            return 1;

        return toutesVoitures.get(toutesVoitures.size()-1).getId_voiture() + 1;
    }

    private Voiture creerVoiture (){

        List<Option> listOptions = null;
        Marque newMarque = null;
        Modele newModele = null;
        double prix=0, km=0;
        String saisie, couleur, carburant;


        System.out.println("\n======== Veuillez compléter les infos ========");
        newMarque = choixMarque();

        newModele = choixModele(newMarque);

        System.out.println("Voulez-vous ajouter des options (y/n)?");
        saisie = scan.nextLine();

        if (saisie.equals("y") || saisie.equals("Y") || saisie.equals("o") || saisie.equals("O")){
            listOptions = newOptions(new ArrayList<>());
        }

        carburant = choixCarburant();

        prix = saisirDouble("Quel sera le prix de cette voiture?", false);

        couleur = saisirTexte("Quelle sera la couleur de cette voiture");

        km = saisirDouble("Quel est son nombre de kilomètre?", true);

        return new Voiture(genererIdVoiture(),newModele,listOptions,prix,couleur, carburant,km);

    }

    private String choixCarburant(){
        boolean correct = false;
        int choixCarburant=0;

        System.out.println("Quel sera son type de carburant");

        do{
            for (int i = 0; i < TYPES_CARBURANTS.length; i++)
                System.out.println((i+1)+". "+TYPES_CARBURANTS[i]);

            System.out.print("=> ");

            try {

                choixCarburant = scan.nextInt();

                if (choixCarburant != 1 && choixCarburant != 2 && choixCarburant != 3) {
                    correct = false;
                    System.err.println("Vous n'avez que 3 possibilités");
                }else
                    correct = true;

            }catch (InputMismatchException ex){
                scan.nextLine();
                correct = false;
                System.err.println("Seul les chiffres sont autorisés!");
            }

        }while (!correct);

        return TYPES_CARBURANTS[choixCarburant-1];
    }

    private double saisirDouble(String message, boolean egalZero){

        boolean correct = false;
        double prix = 0;

        do{
            try {

                System.out.println(message);
                System.out.print("=> ");
                prix = scan.nextDouble();

                if (prix < 0 || (!egalZero && prix == 0)) {
                    correct = false;
                    System.err.println("Le nombre doit être strictement positif!");
                }else
                    correct = true;

            }catch (InputMismatchException ex){
                scan.nextLine();
                correct = false;
                System.err.println("Seul les chiffres et une virgule sont autorisés!");
            }
        }while (!correct);

        scan.nextLine();// for the next scan
        return prix;
    }

    private String saisirTexte(String message) {

        String saisie="";

        do {
            System.out.println(message);
            System.out.print("=> ");
            saisie = scan.nextLine();

            if (saisie == null || saisie.equals(""))
                System.err.println("Votre saisie ne peut être vide!");

        }while (saisie == null || saisie.equals(""));

        return saisie;

    }

    private Modele choixModele (Marque marque) {

        boolean correct = false;
        int choix=-1;
        List<Modele> modeleList = new ArrayList<>();

        for (int i = 0; i < tousModeles.size(); i++)
            if (tousModeles.get(i).getMarque().getId_marque() == marque.getId_marque())
                modeleList.add(tousModeles.get(i));

        do {
            System.out.println("Voici le(s) modèle(s) disponible(s) pour cette marque:");
            for (int j = 0; j < modeleList.size(); j++)
                System.out.println((j + 1) + ". " + modeleList.get(j).getNom());

            System.out.println("Quel modèle choisissez-vous?");
            System.out.print("=> ");

            try {

                choix = scan.nextInt();

                if (choix <= 0 || choix > modeleList.size()) {
                    correct = false;
                    if (modeleList.size() == 1)
                        System.err.println("Vous n'avez que 1 possibilité");
                    else
                        System.err.println("Vous n'avez que "+(modeleList.size()-1)+" possibilités");
                }else
                    correct = true;

            }catch (InputMismatchException ex){
                scan.nextLine();
                correct = false;
                System.err.println("Seul les chiffres sont autorisés!");
            }
        }while (!correct);

        scan.nextLine();//for the next scan
        if (modeleList.size() == 0)
            return null;

        return modeleList.get(choix-1);
    }

    private Marque choixMarque () {

        boolean correct = false;
        int choix=-1;

        System.out.println("Quelle est la marque de la voiture ?");
        System.out.println("Voici les marques disponibles:");

        do {

            for (Marque marque : toutesMarques) {
                System.out.println(marque.getId_marque()+". "+marque.getNom());
            }
            System.out.println("Quelle marque choisissez-vous?");
            System.out.print("=> ");

            try {

                choix = scan.nextInt();

                if (choix <= 0 || choix > toutesMarques.size()) {
                    correct = false;
                    System.out.println("Vous n'avez que "+toutesMarques.size()+" possibilités\n\n");
                }else
                    correct = true;

            }catch (InputMismatchException ex){
                scan.nextLine();
                correct = false;
                System.err.println("Seul les chiffres sont autorisés!");
            }
        }while (!correct);

        scan.nextLine();//for the next scan
        for (Marque marque : toutesMarques) {
            if (marque.getId_marque() == choix)
                return marque;
        }

        return null;
    }

    private List<Option> newOptions(List<Option> newList) {
        List<Option> noChosenOptions = new ArrayList<>(toutesOptions);

        boolean correct = false, onemore=true;
        int choix=-1;

        while (onemore) {

            do {

                if (noChosenOptions.size() == 0){
                    System.out.println("-- Vous avez toutes les options disponibles ! --");
                    correct = true;
                    onemore = false;
                }else {
                    System.out.println("Voici les options disponible: ");
                    for (int i = 0; i < noChosenOptions.size(); i++)
                        System.out.println((i+1) + ". " + noChosenOptions.get(i).getNom() +
                                " (" + noChosenOptions.get(i).getPrix() + " eur)");

                    System.out.println("Quelle option voulez-vous?");
                    System.out.print("=> ");

                    try {

                        choix = scan.nextInt();

                        if (choix <= 0 || choix > noChosenOptions.size()) {
                            correct = false;
                            System.err.println("Vous n'avez que " + noChosenOptions.size() + " possibilités");
                        } else
                            correct = true;

                    } catch (InputMismatchException ex) {
                        scan.nextLine();
                        correct = false;
                        System.err.println("Seul les chiffres sont autorisés!");
                    }
                }

            } while (!correct);

            scan.nextLine();//for the next scan
            newList.add(noChosenOptions.get(choix-1));
            noChosenOptions.remove(choix-1);

            if (noChosenOptions.size() == 0){
                System.out.println("Vous avez ajouté toutes les options disponible!");
                onemore = false;
            }
            else {
                System.out.println("Voulez-vous ajouter une autre option (y/n)?");
                String saisie = scan.nextLine();

                if (saisie.equals("y") || saisie.equals("Y") || saisie.equals("o") || saisie.equals("O"))
                    onemore = true;
                else
                    onemore = false;
            }

        }

        return new ArrayList<>(newList);

    }

    private List<Option> removeOptions(List<Option> newList) {

        boolean correct = false, onemore=true;
        int choix=-1;

        while (onemore) {

            do {

                if (newList.size() == 0){
                    System.out.println("-- Vous avez retirer toutes les options de la voiture ! --");
                    correct = true;
                    onemore = false;
                }else {
                    System.out.println("Voici les options que possède cette voiture: ");
                    for (int i = 0; i < newList.size(); i++)
                        System.out.println((i+1) + ". " + newList.get(i).getNom() +
                                " (" + newList.get(i).getPrix() + " eur)");

                    System.out.println("Quelle option voulez-vous retirer?");
                    System.out.print("=> ");

                    try {

                        choix = scan.nextInt();

                        if (choix <= 0 || choix > newList.size()) {
                            correct = false;
                            System.err.println("Vous n'avez que " + newList.size() + " possibilités");
                        } else
                            correct = true;

                    } catch (InputMismatchException ex) {
                        scan.nextLine();
                        correct = false;
                        System.err.println("Seul les chiffres sont autorisés!");
                    }
                }

            } while (!correct);

            scan.nextLine();//for the next scan
            newList.remove(choix-1);

            if (newList.size() == 0){
                System.out.println("-- Vous avez retirer toutes les options de la voiture ! --");
                onemore = false;
            }
            else {
                System.out.println("Voulez-vous retirer une autre option (y/n)?");
                String saisie = scan.nextLine();

                if (saisie.equals("y") || saisie.equals("Y") || saisie.equals("o") || saisie.equals("O"))
                    onemore = true;
                else
                    onemore = false;
            }

        }

        return new ArrayList<>(newList);

    }
}
