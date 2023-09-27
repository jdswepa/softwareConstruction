/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensordataprocessor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author 96650
 */
public class Sensordataprocessor {

    // Senson data and limits.
    public double[][][] data;
    public double[][] limit;
// constructor
//constructer name need to be tha same as the class name 

    public Sensordataprocessor(double[][][] data, double[][] limit) {
        this.data = data;
        this.limit = limit;
    }
// calculates average of sensor data
//chainge method name (make it more discribtive)
//redeuse unneccery variables 

    private double calculateAverage(double[] array) {
        double sum = 0;
        for (double value : array) {
            sum += value;
        }
        return sum / array.length;
    }

// calculate data
//adjest the indentation (format)
//declair i,j,k inside thier loops 
public void calculate(double d) {
        //int i, j, k = 0;
        //rename data2 for more clairty
        double[][][] calculatedData = new double[data.length][data[0].length][data[0][0].length];
        BufferedWriter writer = null;
//update the variable names to be more discribtive 
//add comments 
        try {
            writer = new BufferedWriter(new FileWriter("RacingStatsData.txt"));

            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[0].length; j++) {
                    for (int k = 0; k < data[0][0].length; k++) {
                        calculatedData[i][j][k] = data[i][j][k] / d - Math.pow(limit[i][j], 2.0);

                        if (calculateAverage(calculatedData[i][j]) > 10 && calculateAverage(calculatedData[i][j]) < 50) {
                            break;
                        } else if (Math.max(data[i][j][k], calculatedData[i][j][k]) > data[i][j][k]) {
                            break;
                        } else if (Math.pow(Math.abs(data[i][j][k]), 3) < Math.pow(Math.abs(calculatedData[i][j][k]), 3)
                                && calculateAverage(data[i][j]) < calculatedData[i][j][k] && (i + 1) * (j + 1) > 0) {
                            calculatedData[i][j][k] *= 2;
                        } else {
                            continue;
                        }
                    }
                }
            }

            for (double[][] row : calculatedData) {
                for (double[] column : row) {
                    for (double value : column) {
                        writer.write(value + "\t");
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
