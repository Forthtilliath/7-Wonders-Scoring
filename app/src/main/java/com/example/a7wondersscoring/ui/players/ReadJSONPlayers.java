package com.example.a7wondersscoring.ui.players;

import android.content.Context;

import com.example.a7wondersscoring.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadJSONPlayers {

    // Read the players.json file and convert it to a java object.
    public static Players readPlayersJSONFile(Context context) throws IOException, JSONException {

        // Read content of players.json
        String jsonText = readText(context, R.raw.players);

        JSONObject jsonRoot = new JSONObject(jsonText);


        JSONArray jsonArray = jsonRoot.getJSONArray("names");
        String[] names = new String[jsonArray.length()];

        for (int i = 0; i < jsonArray.length(); i++) {
            names[i] = jsonArray.getString(i);
        }

        Players players = new Players();
        players.setNames(names);
        return players;
    }


    private static String readText(Context context, int resId) throws IOException {
        InputStream is = context.getResources().openRawResource(resId);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String s;
        while ((s = br.readLine()) != null) {
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }
}
