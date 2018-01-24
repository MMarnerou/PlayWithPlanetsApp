package com.example.mariamarnerou.testapp.model;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Maria Marnerou on 22-Jan-18.
 */

public class Quiz implements Serializable {
    private Planet[] planets;

    public Quiz() { super(); }

    public Planet[] getPlanets() {
        return planets;
    }

    public int getNumOfPlanets() {
        return planets.length;
    }

    public Planet getPlanet(final int i) {
        return planets[i];
    }

    public Planet getPlanet(final String planetName) {
        for (Planet planet : planets) {
            if (planetName.equals(planet.getName())) {
                return planet;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "planets=" + Arrays.toString(planets) +
                '}';
    }
}
