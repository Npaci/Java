import Classes.*;
import UserInt.Menu;
import data_access.entities.*;
import data_access.interfaces.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("======== Bienvenu chez MotorTech ========");

        Menu menu = new Menu();

        menu.start();
        //endregion


        //Avec BD
        //region MARQUE
//        MarqueDAO marqueDAO = new MarqueDAOImpl();
//        List<Marque> allMarques = marqueDAO.getAll();
//
//        System.out.println("Marque(s) lue(s) dans la DB:");
//        allMarques.forEach(System.out::println);
//
//        System.out.println("La marque dont l'id = 3:");
//        System.out.println(marqueDAO.getById(3));
//
//        System.out.println("On va essayer d'ajouter une nouvelle marquede voiture (Aston Martin)");
//        Marque newM = new Marque(allMarques.get(allMarques.size()-1).getId_marque() + 1, "Aston Martin");
//        boolean check = marqueDAO.insert(newM);
//
//        if (check)
//            System.out.println("**** "+newM+" a été ajouté à la BD ***");
//        else
//            System.out.println("**** !!" +newM+ " n'a pas été ajouté à la BD!! ***");
//
//        System.out.println("Va essayer de mettre à jours la marque dont l'Id=6");
//        newM = new Marque(6, "Chevrolet");
//        check = marqueDAO.update(newM);
//
//        if (check)
//            System.out.println("**** "+newM+" a été mis à jours dans la BD ***");
//        else
//            System.out.println("**** !!" + newM + " n'a pas été mis à jours dans la BD!! ***");
//
//        System.out.println("On va supprimer la marque ID=8");
//        if(marqueDAO.delete(8))
//            System.out.println("**** La marque dont l'ID vaut 8, a été supprimée de la BD ***");
//        else
//            System.out.println("**** La marque dont l'ID vaut 8, n'a pas été supprimée de la BD!! ***");
        //endregion

        //region OPTIONS
//        OptionDAO optionDAO = new OptionDAOIpml();
//        List<Option> allOptions = optionDAO.getAll();
//
//        System.out.println("Option(s) lue(s) dans la DB:");
//        allOptions.forEach(System.out::println);
//
//        System.out.println("L'option dont l'id = 3:");
//        System.out.println(optionDAO.getById(3));

//        System.out.println("On va essayer d'ajouter une nouvelle marquede voiture (Aston Martin)");
//        Option newO = new Option(allOptions.get(allOptions.size()-1).getId_option() + 1, "Commande au volant", 1500);
//        boolean check = optionDAO.insert(newO);
//
//        if (check)
//            System.out.println("**** "+newO+" a été ajouté à la BD ***");
//        else
//            System.out.println("**** !!" +newO+ " n'a pas été ajouté à la BD!! ***");

//        System.out.println("Va essayer de mettre à jours la marque dont l'Id=6");
//        Option newO = new Option(3, "Bose audio", 3250);
//        boolean check = optionDAO.update(newO);
//
//        if (check)
//            System.out.println("**** "+newO+" a été mis à jours dans la BD ***");
//        else
//            System.out.println("**** !!" + newO + " n'a pas été mis à jours dans la BD!! ***");
//
//        System.out.println("On va supprimer l'option ID=8");
//        if(optionDAO.delete(10))
//            System.out.println("**** L'option dont l'ID vaut 10, a été supprimée de la BD ***");
//        else
//            System.out.println("**** L'option dont l'ID vaut 10, n'a pas été supprimée de la BD!! ***");

        //endregion

        //region MODELE
//        ModeleDAO modeleDAO = new ModeleDAOImpl();
//        List<Modele> allModeles = modeleDAO.getAll();
//
//        System.out.println("Modele(s) lue(s) dans la DB:");
//        allModeles.forEach(System.out::println);
//
//        System.out.println("Le modele dont l'id = 3:");
//        System.out.println(modeleDAO.getById(3));
//
//        System.out.println("On va essayer d'ajouter un nouveau modele de voiture (Focus)");
//        Marque marque = new Marque(1, "Ford");
//        Modele newM = new Modele(allModeles.get(allModeles.size()-1).getId_modele() + 1, "Focus", marque);
//        boolean check = modeleDAO.insert(newM);
//
//        if (check)
//            System.out.println("**** "+newM+" a été ajouté à la BD ***");
//        else
//            System.out.println("**** !!" +newM+ " n'a pas été ajouté à la BD!! ***");
//
//        System.out.println("Va essayer de mettre à jours le modele dont l'Id=1");
//        newM = new Modele(1, "Fiesta", new Marque(1, "Ford"));
//        check = modeleDAO.update(newM);
//
//        if (check)
//            System.out.println("**** "+newM+" a été mis à jours dans la BD ***");
//        else
//            System.out.println("**** !!" + newM + " n'a pas été mis à jours dans la BD!! ***");
//
//        System.out.println("On va supprimer l'option ID=8");
//        if(modeleDAO.delete(6))
//            System.out.println("**** L'option dont l'ID vaut 6, a été supprimée de la BD ***");
//        else
//            System.out.println("**** L'option dont l'ID vaut 6, n'a pas été supprimée de la BD!! ***");
        //endregion

        //region VOITURE
//        VoitureDAO voitureDAO = new VoitureDAOImpl();
//        List<Voiture> allVoitures = voitureDAO.getAll();
//
//        System.out.println("Voiture(s) lue(s) dans la DB:");
//        allVoitures.forEach(System.out::println);
//
//        System.out.println("La voiture dont l'id = 1:");
//        System.out.println(voitureDAO.getById(1));

//        System.out.println("On va essayer d'ajouter une nouvelle voiture");
//        Modele m = new Modele(1, "Fiesta", new Marque(1, "Ford"));
//        Voiture voiture = new Voiture(allVoitures.get(allVoitures.size()-1).getId_voiture() + 1,m,null,4500,"Bleu marine","Diesel", 4650);
//
//        check = voitureDAO.insert(voiture);
//
//        if (check)
//            System.out.println("**** "+voiture+" a été ajouté à la BD ***");
//        else
//            System.out.println("**** !!" +voiture+ " n'a pas été ajouté à la BD!! ***");

//        System.out.println("Va essayer de mettre à jours la dont l'Id=1");
//        voiture.setCarburant("Essence");
//        voiture.setId_voiture(1);
//        check = voitureDAO.update(voiture);
//
//        if (check)
//            System.out.println("**** "+voiture+" a été mis à jours dans la BD ***");
//        else
//            System.out.println("**** !!" + voiture + " n'a pas été mis à jours dans la BD!! ***");
//
//        System.out.println("On va supprimer l'option ID=8");
//        if(voitureDAO.delete(2))
//            System.out.println("**** L'option dont l'ID vaut 6, a été supprimée de la BD ***");
//        else
//            System.out.println("**** L'option dont l'ID vaut 6, n'a pas été supprimée de la BD!! ***");
        //endregion

        //region VOITURE_OPTION
        Voiture_OptionDAO voiture_optionDAO = new Voiture_OptionDAOImpl();
        List<Voiture_Option> allVoitureOptions = voiture_optionDAO.getAll();
//
//        System.out.println("Voiture(s) lue(s) dans la DB:");
//        allVoitureOptions.forEach(System.out::println);
//
//        System.out.println("La voiture dont l'id = 3:");
//        System.out.println(voiture_optionDAO.getById(3));
//
//        System.out.println("On va essayer d'ajouter une nouvelle option à une voiture");
//        Option option = new OptionDAOIpml().getById(3);
//        Voiture voiture = new VoitureDAOImpl().getById(1);
//        Voiture_Option voiture_option = new Voiture_Option(
//                allVoitureOptions.get(allVoitureOptions.size()-1).getId_voiture_option() + 1, voiture, option);
//
//
//        boolean check = voiture_optionDAO.insert(voiture_option);
//
//        if (check)
//            System.out.println("**** "+voiture_option+" a été ajouté à la BD ***");
//        else
//            System.out.println("**** !!" +voiture_option+ " n'a pas été ajouté à la BD!! ***");

//        System.out.println("Va essayer de mettre à jours la dont l'Id=1");
//        voiture.setCarburant("Essence");
//        voiture.setId_voiture(1);
//        boolean check = voiture_optionDAO.update(voiture_option);

//        if (check)
//            System.out.println("**** "+voiture_option+" a été mis à jours dans la BD ***");
//        else
//            System.out.println("**** !!" + voiture_option + " n'a pas été mis à jours dans la BD!! ***");

//        System.out.println("On va supprimer l'option ID=8");
//        if(voiture_optionDAO.delete(2))
//            System.out.println("**** L'option dont l'ID vaut 2, a été supprimée de la BD ***");
//        else
//            System.out.println("**** L'option dont l'ID vaut 2, n'a pas été supprimée de la BD!! ***");

        //endregion

    }
}
