package cy.ac.uclancyprus.thesisapp.model;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author Nearchos
 *         Created: 12-Dec-17
 */

public class Quiz implements Serializable {

    private Planet [] planets;

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

    @Override
    public String toString() {
        return "Quiz{" +
                "planets=" + Arrays.toString(planets) +
                '}';
    }
}