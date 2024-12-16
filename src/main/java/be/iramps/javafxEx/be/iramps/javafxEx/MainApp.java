package be.iramps.javafxEx.be.iramps.javafxEx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    private TextField affichage;
    private double premierNombre = 0;
    private String operateur = "";
    private boolean nouveauNombre = true;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Calculatrice");

        // Création du champ d'affichage
        affichage = new TextField();
        affichage.setEditable(false);
        affichage.setStyle("-fx-font-size: 20px; -fx-alignment: center-right;");
        affichage.setPrefHeight(50);

        // Création des boutons
        GridPane grille = new GridPane();
        grille.setHgap(10);
        grille.setVgap(10);

        ajouterBouton(grille, "7", 0, 1, event -> ajouterTexte("7"));
        ajouterBouton(grille, "8", 1, 1, event -> ajouterTexte("8"));
        ajouterBouton(grille, "9", 2, 1, event -> ajouterTexte("9"));
        ajouterBouton(grille, "/", 3, 1, event -> definirOperateur("/"));

        ajouterBouton(grille, "4", 0, 2, event -> ajouterTexte("4"));
        ajouterBouton(grille, "5", 1, 2, event -> ajouterTexte("5"));
        ajouterBouton(grille, "6", 2, 2, event -> ajouterTexte("6"));
        ajouterBouton(grille, "*", 3, 2, event -> definirOperateur("*"));

        ajouterBouton(grille, "1", 0, 3, event -> ajouterTexte("1"));
        ajouterBouton(grille, "2", 1, 3, event -> ajouterTexte("2"));
        ajouterBouton(grille, "3", 2, 3, event -> ajouterTexte("3"));
        ajouterBouton(grille, "-", 3, 3, event -> definirOperateur("-"));

        ajouterBouton(grille, "0", 0, 4, event -> ajouterTexte("0"));
        ajouterBouton(grille, "C", 1, 4, event -> reinitialiser());
        ajouterBouton(grille, "=", 2, 4, event -> calculer());
        ajouterBouton(grille, "+", 3, 4, event -> definirOperateur("+"));

        // Ajout des éléments à la scène
        GridPane racine = new GridPane();
        racine.add(affichage, 0, 0, 4, 1);
        racine.add(grille, 0, 1);

        Scene scene = new Scene(racine, 300, 400);
        stage.setScene(scene);
        stage.show();
    }

    private void ajouterBouton(GridPane grille, String texte, int col, int ligne, javafx.event.EventHandler<javafx.event.ActionEvent> action) {
        Button bouton = new Button(texte);
        bouton.setPrefSize(60, 60);
        bouton.setStyle("-fx-font-size: 18px;");
        bouton.setOnAction(action);
        grille.add(bouton, col, ligne);
    }

    private void ajouterTexte(String texte) {
        if (nouveauNombre) {
            affichage.clear();
            nouveauNombre = false;
        }
        affichage.appendText(texte);
    }

    private void definirOperateur(String op) {
        premierNombre = Double.parseDouble(affichage.getText());
        operateur = op;
        nouveauNombre = true;
    }

    private void calculer() {
        double secondNombre = Double.parseDouble(affichage.getText());
        double resultat = 0;

        if (operateur.equals("+")) {
            resultat = premierNombre + secondNombre;
        } else if (operateur.equals("-")) {
            resultat = premierNombre - secondNombre;
        } else if (operateur.equals("*")) {
            resultat = premierNombre * secondNombre;
        } else if (operateur.equals("/")) {
            resultat = secondNombre != 0 ? premierNombre / secondNombre : 0; // Gestion de la division par zéro
        }

        affichage.setText(String.valueOf(resultat));
        nouveauNombre = true;
    }

    private void reinitialiser() {
        affichage.clear();
        premierNombre = 0;
        operateur = "";
        nouveauNombre = true;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
