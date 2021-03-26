package com.example.a7wondersscoring.ui.players;

import org.jetbrains.annotations.NotNull;

public class Players {

    private String[] names;

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    @NotNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        /*sb.append("\n id:" + this.id);
        sb.append("\n name:" + this.name);
        if (this.websites != null) {
            sb.append("\n website: ");
            for (String website : this.websites) {
                sb.append(website + ", ");
            }
        }
        if (this.address != null) {
            sb.append("\n address:" + this.address.toString());
        }*/
        if (this.names != null) {
            sb.append("\n Noms : ");
            for (String name : this.names) {
                sb.append(name.concat(", "));
            }
        }
        return sb.toString();
    }

}
