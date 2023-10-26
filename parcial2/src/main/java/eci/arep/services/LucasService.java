package eci.arep.services;

import java.util.List;

public class LucasService {

    public static void getSequence(int limit, List<Integer> currentVals){
        if (currentVals.size() == 0){
            currentVals.add(2);
            currentVals.add(1);
        }

        if (currentVals.size() != limit + 1) {
            int previousNum = currentVals.get(currentVals.size() - 1);
            int previousNum2 = currentVals.get(currentVals.size() - 2);
            currentVals.add(previousNum + previousNum2);
            getSequence(limit, currentVals);
        };


    }
}
