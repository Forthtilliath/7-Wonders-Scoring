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

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static com.example.a7wondersscoring.R.color.pagePlayersBackgroundEven;
import static com.example.a7wondersscoring.R.color.pagePlayersBackgroundOdd;

/**
 * [ ] Ajouter un bouton pour ajouter un nouveau joueur
 * [ ] Avoir un fichier de configuration pour enregistrer la liste des joueurs
 * [x] Charger automatiquement la liste des joueurs
 * [ ] Ajouter scrollbar sur toutes les pages
 */

public class PlayersFragment extends Fragment {

    private PlayersViewModel playersViewModel;
    private List<String> lPlayers = loadPlayers();
    private int nbMaxPlayers = 15;

    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

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
            TextView textViewPlayer = root.findViewById(getResId("text_player_" + i, R.id.class));

            // On le met en visible et on ajoute le nom du joueur dedans
            textViewPlayer.setVisibility(View.VISIBLE);
            textViewPlayer.setText(i + " - " + lPlayers.get(i));

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
            //textViewPlayer.setTextAppearance(R.style.TextAppearance_AppCompat_Large);

            // Margin & Taille
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) textViewPlayer.getLayoutParams();
            //params.height = dpToPx(50);
            params.setMargins(8, 16, 8, 8);
            textViewPlayer.setLayoutParams(params);
        }

        // Limite le nombre max de joueurs (tant qu'on ne peut pas générer les champs via le code)
        if (lPlayers.size() == nbMaxPlayers) {
            Button btAddPlayer = root.findViewById(R.id.bt_new_player);
            btAddPlayer.setVisibility(View.GONE);
        }

        return root;
    }
}