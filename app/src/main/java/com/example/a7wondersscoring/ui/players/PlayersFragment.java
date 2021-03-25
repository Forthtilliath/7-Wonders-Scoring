package com.example.a7wondersscoring.ui.players;

        import android.annotation.SuppressLint;
        import android.content.res.Resources;
        import android.graphics.Typeface;
        import android.os.Build;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.LinearLayout;
        import android.widget.TextView;

        import androidx.annotation.ColorInt;
        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.annotation.RequiresApi;
        import androidx.constraintlayout.widget.ConstraintLayout;
        import androidx.constraintlayout.widget.ConstraintSet;
        import androidx.fragment.app.Fragment;
        import androidx.lifecycle.Observer;
        import androidx.lifecycle.ViewModelProvider;

        import com.example.a7wondersscoring.R;

        import org.w3c.dom.Text;

        import java.util.Arrays;
        import java.util.List;

        import static com.example.a7wondersscoring.R.color.black;

public class PlayersFragment extends Fragment {

    private PlayersViewModel playersViewModel;
    private List<String> lPlayers = Arrays.asList("Isabelle", "Noémie", "Vincent", "Jacquie",
            "Michel", "Chieuse", "Piplette", "Raleuse", "Emmerdeuse", "Jalouse", "Poufiasse",
            "Idiote", "Faignasse", "Glandeuse", "Pétasse");

    private List<Integer> textIdPlayers = Arrays.asList(R.id.text_player_0, R.id.text_player_1, R.id.text_player_2, R.id.text_player_3, R.id.text_player_4, R.id.text_player_5, R.id.text_player_6, R.id.text_player_7, R.id.text_player_8, R.id.text_player_9, R.id.text_player_10, R.id.text_player_11, R.id.text_player_12, R.id.text_player_13, R.id.text_player_14, R.id.text_player_15, R.id.text_player_16, R.id.text_player_17, R.id.text_player_18, R.id.text_player_19, R.id.text_player_20, R.id.text_player_21, R.id.text_player_22, R.id.text_player_23, R.id.text_player_24, R.id.text_player_25);

    private int dpToPx(int dp) { return (int) (dp * Resources.getSystem().getDisplayMetrics().density); }
    private int pxToDp(int px) { return (int) (px / Resources.getSystem().getDisplayMetrics().density); }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.Q)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        playersViewModel = new ViewModelProvider(this).get(PlayersViewModel.class);
        View root = inflater.inflate(R.layout.fragment_players, container, false);

        // On ajoute un élément vide à la fin le temps de savoir pourquoi le dernier
        // élément ne veut pas s'afficher
        lPlayers.add("");

        // Pour chaque joueur
        for( int i = 0 ; i < lPlayers.size() ; i++) {
            // On récupère le champ
            TextView textViewPlayer = root.findViewById(textIdPlayers.get(i));

            // On le met en visible et on ajoute le nom du joueur dedans
            textViewPlayer.setVisibility(View.VISIBLE);
            textViewPlayer.setText(lPlayers.get(i));

            // Fond alterné
            if(i%2 == 0)
                textViewPlayer.setBackgroundResource(R.color.purple_200);
            else
                textViewPlayer.setBackgroundResource(R.color.teal_200);

            // Ajout padding, alignement, bold, color
            textViewPlayer.setPadding(25,25,25,25);
            textViewPlayer.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textViewPlayer.setTypeface(Typeface.DEFAULT_BOLD);
            textViewPlayer.setTextAppearance(R.style.TextAppearance_AppCompat_Large);
            textViewPlayer.setTextColor(black);

            // Margin & Taille
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) textViewPlayer.getLayoutParams();
            //params.height = dpToPx(50);
            params.setMargins(8,16,8,8);
            textViewPlayer.setLayoutParams(params);
        }
        return root;
    }
}