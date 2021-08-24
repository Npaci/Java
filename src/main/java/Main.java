import Classes.Marque;
import Classes.Modele;
import Classes.Option;
import Classes.Voiture;
import data_access.entities.ModeleDAOImpl;
import data_access.entities.OptionDAOIpml;
import data_access.entities.VoitureDAOImpl;
import data_access.interfaces.MarqueDAO;
import data_access.entities.MarqueDAOImpl;
import data_access.interfaces.ModeleDAO;
import data_access.interfaces.OptionDAO;
import data_access.interfaces.VoitureDAO;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("======== Bienvenu chez MotorTech ========");
        //region Sans BD
//        Menu menu = new Menu();
//        menu.toutesVoitures.add(new Voiture(1, menu.mesModeles[1],null,2545,"Bleu",Menu.TYPES_CARBURANTS[2], 10000));
//
//        menu.start();
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
        //enregion

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
//        allOptions.forEach(System.out::println);
//
//        System.out.println("Le modele dont l'id = 3:");
//        System.out.println(optionDAO.getById(3));

//        System.out.println("On va essayer d'ajouter un nouveau modele de voiture (Focus)");
//        Marque marque = new Marque(1, "Ford");
//        Modele newM = new Modele(allModeles.get(allModeles.size()-1).getId_modele() + 1, "Focus", marque);
//        boolean check = modeleDAO.insert(newM);
//
//        if (check)
//            System.out.println("**** "+newM+" a été ajouté à la BD ***");
//        else
//            System.out.println("**** !!" +newM+ " n'a pas été ajouté à la BD!! ***");

//        System.out.println("Va essayer de mettre à jours le modele dont l'Id=1");
//        Modele newM = new Modele(1, "Fiesta", new Marque(1, "Ford"));
//        boolean check = modeleDAO.update(newM);
//
//        if (check)
//            System.out.println("**** "+newM+" a été mis à jours dans la BD ***");
//        else
//            System.out.println("**** !!" + newM + " n'a pas été mis à jours dans la BD!! ***");
//
//        System.out.println("On va supprimer l'option ID=8");
//        if(optionDAO.delete(6))
//            System.out.println("**** L'option dont l'ID vaut 6, a été supprimée de la BD ***");
//        else
//            System.out.println("**** L'option dont l'ID vaut 6, n'a pas été supprimée de la BD!! ***");

        //VOITURE
        VoitureDAO voitureDAO = new VoitureDAOImpl();
        List<Voiture> allVoitures = voitureDAO.getAll();

        System.out.println("Voiture(s) lue(s) dans la DB:");
        allVoitures.forEach(System.out::println);

        System.out.println("La voiture dont l'id = 3:");
        System.out.println(voitureDAO.getById(3));

        System.out.println("On va essayer d'ajouter une nouvelle voiture");
        Modele m = new Modele(10, "Mondeo", new Marque(1, "Fords"));
        Voiture voiture = new Voiture(allVoitures.get(allVoitures.size()-1).getId_voiture() + 1,m,null,12850,"Bleu marine","Diesel", 4650);

        boolean check = voitureDAO.insert(voiture);

        if (check)
            System.out.println("**** "+voiture+" a été ajouté à la BD ***");
        else
            System.out.println("**** !!" +voiture+ " n'a pas été ajouté à la BD!! ***");

        System.out.println("Va essayer de mettre à jours la dont l'Id=1");
        voiture.setCarburant("Essence");
        check = voitureDAO.update(voiture);

        if (check)
            System.out.println("**** "+voiture+" a été mis à jours dans la BD ***");
        else
            System.out.println("**** !!" + voiture + " n'a pas été mis à jours dans la BD!! ***");

        System.out.println("On va supprimer l'option ID=8");
        if(voitureDAO.delete(1))
            System.out.println("**** L'option dont l'ID vaut 6, a été supprimée de la BD ***");
        else
            System.out.println("**** L'option dont l'ID vaut 6, n'a pas été supprimée de la BD!! ***");

    }
}
