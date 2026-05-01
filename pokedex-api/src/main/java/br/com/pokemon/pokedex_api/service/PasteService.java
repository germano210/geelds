package br.com.pokemon.pokedex_api.service;

import br.com.pokemon.pokedex_api.dto.PasteDTO;
import br.com.pokemon.pokedex_api.model.PokemonBuild;
import org.springframework.stereotype.Service;

@Service
public class PasteService {

    public PasteDTO parseSmogonText(String text) {
        PasteDTO result = new PasteDTO();
        PokemonBuild build = result.getBuild();


        String[] lines = text.split("\\r?\\n");

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].trim();
            if (line.isEmpty()) continue;


            if (i == 0) {
                if (line.contains("@")) {
                    String[] parts = line.split("@");
                    result.setPokemonName(parts[0].trim());
                    build.setItem(parts[1].trim());
                } else {
                    result.setPokemonName(line.trim());
                }
            }
            else if (line.startsWith("Ability:")) {
                build.setAbility(line.replace("Ability:", "").trim());
            }
            else if (line.startsWith("EVs:")) {
                parseStats(line.replace("EVs:", "").trim(), build, true);
            }
            else if (line.startsWith("IVs:")) {
                parseStats(line.replace("IVs:", "").trim(), build, false);
            }
            else if (line.endsWith("Nature")) {
                build.setNature(line.replace(" Nature", "").trim());
            }
            else if (line.startsWith("- ")) {
                result.getMoveNames().add(line.substring(2).trim());
            }
        }

        build.setBuildName("Imported " + result.getPokemonName() + " Build");
        return result;
    }
    private void parseStats(String statLine, PokemonBuild build, boolean isEv) {
        String[] parts = statLine.split("/");
        for (String part : parts) {
            String[] valAndStat = part.trim().split(" ");
            if (valAndStat.length == 2) {
                int value = Integer.parseInt(valAndStat[0]);
                String stat = valAndStat[1].toLowerCase();

                if (isEv) {
                    switch (stat) {
                        case "hp": build.setEvHp(value); break;
                        case "atk": build.setEvAtk(value); break;
                        case "def": build.setEvDef(value); break;
                        case "spa": build.setEvSpa(value); break;
                        case "spd": build.setEvSpd(value); break;
                        case "spe": build.setEvSpe(value); break;
                    }
                } else {
                    switch (stat) {
                        case "hp": build.setIvHp(value); break;
                        case "atk": build.setIvAtk(value); break;
                        case "def": build.setIvDef(value); break;
                        case "spa": build.setIvSpa(value); break;
                        case "spd": build.setIvSpd(value); break;
                        case "spe": build.setIvSpe(value); break;
                    }
                }
            }
        }
    }
}