/*
 * Stephen Turner, Computer Science BSc Year 3
 * University Of the West Of England
 */
package biocomputation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class evolutionaryMain {

    /**
     * @param args the command line arguments
     * @throws java.io.UnsupportedEncodingException
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException, IOException {

        int genCount = 0;
        int generations = 50;
        int geneLength = 60;
        int populationSize = 50;
        int bestGen = 0;
        float muteRate = (float) 0.009;
        float xoverRate = (float) 0.9;

        Population p = new Population(populationSize, geneLength);
        Individual best = new Individual(geneLength);
        PrintWriter bestText = new PrintWriter("best.txt", "utf-8");
        PrintWriter meanText = new PrintWriter("mean.txt", "utf-8");
//------------------------------------------------------------------------------------------------------------------------------------------------------\\

        //randomly create new population
        p.populateData();
        p.initialise();

        //keep the best of the previous generation.
        while (genCount < generations) {

            if (genCount > 0) {
                p.getPopulation().get(p.getR().nextInt(49));
                p.getPopulation().add(best);
            }

            //Tournament selection creates a new offspring arrayList
            p.selection();

            //crosses over selected individuals and places them into a new arrayList this is based on the xover rate
            for (int i = 0; i < p.getMaxPop() / 2; i++) {

                p.crossOver(p.getOffspring().get(i), p.getOffspring().get(p.getMaxPop() - 1 - i), xoverRate);

            }

            //a chance for mutation on each bit of each individual.
            for (int i = 0; i < p.getCrossed().size(); i++) {
                p.mutation(p.getCrossed().get(i), muteRate);

            }

            //population and offspring cleared. the crossed list contained the mutated and xovered individuals
            p.getPopulation().clear();
            p.getOffspring().clear();

            //the manipulated individuals are placed into the population array for the next generation.
            for (int i = 0; i < p.getCrossed().size(); i++) {
                p.getPopulation().add(i, p.getCrossed().get(i));

            }
            //crossed list cleared for next generations crossover.
            p.getCrossed().clear();
            genCount++;

            //Best individual is saved to be placed into the next generation
            for (int i = 0; i < p.getPopulation().size(); i++) {

                if (p.getPopulation().get(i).getFitness() > best.getFitness()) {

                    best.setGenes(p.getPopulation().get(i).getGenes());
                    best.setRules(p.getPopulation().get(i).getRules());
                    best.evaluateFitness(p.getDataList());

                    bestGen = genCount;

                }

            }

//Text file related------------------------------------------------------------------------------------------------------------------------------
            System.out.println("Generation; " + genCount);
            System.out.println(best.getFitness());
            bestText.print(best.getFitness());
            bestText.append("\n");
            int totalFitness = 0;
            int fitMean = 0;

            for (Individual population : p.getPopulation()) {
                totalFitness += population.getFitness();
                //     System.out.println(population.fitness);
            }

            fitMean = (totalFitness / p.getMaxPop());
            meanText.print(fitMean);
            meanText.append("\n");

            System.out.println("Mean: " + fitMean);

        }

        bestText.close();
        meanText.close();
//-----------------------------------------------------------------------------------------------------------------------------------------

        //create a graph
        XYLineChart_AWT a = new XYLineChart_AWT("Title");

    }

}
