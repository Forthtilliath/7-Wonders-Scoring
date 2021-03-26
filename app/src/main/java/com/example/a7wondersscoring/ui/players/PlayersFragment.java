package com.example.a7wondersscoring.ui.players;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.a7wondersscoring.MainActivity;
import com.example.a7wondersscoring.R;

import java.util.Arrays;
import java.util.List;

import static com.example.a7wondersscoring.R.color.pagePlayersBackgroundEven;
import static com.example.a7wondersscoring.R.color.pagePlayersBackgroundOdd;

/**
 * Page Players
 * [x] Ajouter un bouton pour ajouter un nouveau joueur
 * [x] Avoir un fichier de configuration pour enregistrer la liste des joueurs
 * [x] Charger automatiquement la liste des joueurs
 * [ ] Style du bouton nouveau joueur
 * [ ] Création page pour nouveau joueur
 * [ ] Nouvelle page
 * [ ] Faire le lien via le bouton
 * [ ] Ajouter un champ texte
 * [ ] Bouton valider
 * [ ] Enregistrement du nom dans le fichier de config
 * <p>
 * All pages
 * [ ] Ajouter scrollbar sur toutes les pages
 * [ ] Mettre à jour les thèmes
 * <p>
 * Page scores
 * [ ] Fichier config pour une partie
 * [ ] Afficher les résultats d'une partie
 * <p>
 * Configs
 * [ ] Combiner les fichiers config
 * <p>
 * Page Home
 * [ ] Mettre à jour la home page pour lien vers soutien
 * <p>
 * Page Config
 * [ ] Ajouter une page de config
 */

public class PlayersFragment extends Fragment {

    private PlayersViewModel playersViewModel;
    private List<String> lPlayers = loadPlayers();
    private int nbMaxPlayers = R.integer.nb_max_players;

    // Récupère le nom des joueurs via un fichier json
    private List<String> loadPlayers() {
        String[] lPlayers;

        try {
            Players players = ReadJSONPlayers.readPlayersJSONFile(MainActivity.getContext());
            System.out.println(players.toString());
            lPlayers = players.getNames();
        } catch (Exception e) {
            lPlayers = new String[]{"Riri", "Fifi", "Loulou"};
            e.printStackTrace();
        }
        return Arrays.asList(lPlayers);
    }

    private int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    private int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    @SuppressLint("ResourceAsColor")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        playersViewModel = new ViewModelProvider(this).get(PlayersViewModel.class);
        View root = inflater.inflate(R.layout.fragment_players, container, false);

        // Pour chaque joueur
        for (int i = 0; i < lPlayers.size(); i++) {
            // On récupère le champ
            //TextView textViewPlayer = root.findViewById(MainActivity.getResId("text_player_" + i, R.id.class));

            TextView textViewPlayer;
            // Si c'est le premier joueur, on peut utiliser le champ disponible,
            // Sinon, on doit générer un nouveau champ
            if (i == 0)
                textViewPlayer = root.findViewById(R.id.text_player);
            else {
                // Clone la vue actuelle
                View v = inflater.inflate(R.layout.fragment_players, null);
                // Récupère le champ texte à copier
                textViewPlayer = (TextView) v.findViewById(R.id.text_player);
                // Affecte un nouvelle id généré aléatoirement
                textViewPlayer.setId(View.generateViewId());
                // Récupère le groupe
                ViewGroup insertPoint = (ViewGroup) root.findViewById(R.id.linear_players);
                // Ajoute le nouveau champ au groupe
                insertPoint.addView(v);
            }

            // On le met en visible et on ajoute le nom du joueur dedans
            textViewPlayer.setVisibility(View.VISIBLE);
            textViewPlayer.setText((i + 1) + " - " + lPlayers.get(i));

            // Fond alterné
            if (i % 2 == 0)
                textViewPlayer.setBackgroundResource(pagePlayersBackgroundEven);
            else
                textViewPlayer.setBackgroundResource(pagePlayersBackgroundOdd);

            // Ajout padding, alignement, bold, color
            textViewPlayer.setPadding(25, 25, 25, 25);
            textViewPlayer.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textViewPlayer.setTypeface(Typeface.DEFAULT_BOLD);
            textViewPlayer.setTextSize(22);

            // Margin & Taille
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) textViewPlayer.getLayoutParams();
            //params.height = dpToPx(50);
            //params.setMargins(8, 16, 8, 8);
            textViewPlayer.setLayoutParams(params);
        }

        // Limite le nombre max de joueurs (tant qu'on ne peut pas générer les champs via le code)
        if (lPlayers.size() < nbMaxPlayers) {
            Button btAddPlayer = root.findViewById(R.id.bt_new_player);
            btAddPlayer.setVisibility(View.VISIBLE);
            //ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) btAddPlayer.getLayoutParams();
            //layoutParams.bottomToTop = MainActivity.getResId("text_player_" + (lPlayers.size()-1), R.id.class);
            //btAddPlayer.setLayoutParams(layoutParams);
        }

        return root;
    }
}