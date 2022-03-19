package com.aryeet.demo.bdd.model;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Setter
@Getter
public class CharacterFilter {


    private Set<String> pokédexNumber;
    private Set<String> height;
    private Set<String> weight;
    private Set<String> type;
    private Set<String> heldItems;


    public CharacterFilter setReviewFilter(Map<String, String> entry) {

        //|Pokédex_number | Height | Weight | Type | Held_Items |

        Optional<String> pokédexNumber = entry.entrySet()
                .stream()
                .filter(x -> x.getKey().equalsIgnoreCase("Pokédex_number"))
                .map(y -> y.getValue())
                .findFirst();

        Optional<String> height = entry.entrySet()
                .stream()
                .filter(x -> x.getKey().equalsIgnoreCase("Height"))
                .map(y -> y.getValue())
                .findFirst();

        Optional<String> weight = entry.entrySet()
                .stream()
                .filter(x -> x.getKey().equalsIgnoreCase("Weight"))
                .map(y -> y.getValue())
                .findFirst();
        Optional<String> type = entry.entrySet()
                .stream()
                .filter(x -> x.getKey().equalsIgnoreCase("Type"))
                .map(y -> y.getValue())
                .findFirst();
        Optional<String> heldItems = entry.entrySet()
                .stream()
                .filter(x -> x.getKey().equalsIgnoreCase("Held_Items"))
                .map(y -> y.getValue())
                .findFirst();


        System.out.println();
        return this;


    }

    @Override
    public String toString() {
        return "CharacterFilter{" +
                "pokédexNumber=" + pokédexNumber +
                ", height=" + height +
                ", weight=" + weight +
                ", type=" + type +
                ", heldItems=" + heldItems +
                '}';
    }
}
