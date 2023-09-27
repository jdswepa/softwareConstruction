/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensordataprocessor;

import java.io.BufferedWriter;
import java.io.FileWriter;

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
public void calculate(double d) {
int i, j, k = 0;
double[][][] data2 = new
double[data.length][data[0].length][data[0][0].length];
BufferedWriter out;
// Write racing stats data into a file
try {
out = new BufferedWriter(new FileWriter("RacingStatsData.txt"));
for (i = 0; i < data.length; i++) {
for (j = 0; j < data[0].length; j++) {
for (k = 0; k < data[0][0].length; k++) {
data2[i][j][k] = data[i][j][k] / d -
Math.pow(limit[i][j], 2.0);
if (calculateAverage(data2[i][j]) > 10 && calculateAverage(data2[i][j])
< 50)
break;
else if (Math.max(data[i][j][k], data2[i][j][k]) >
data[i][j][k])
break;
else if (Math.pow(Math.abs(data[i][j][k]), 3) <
Math.pow(Math.abs(data2[i][j][k]), 3)
&& calculateAverage(data[i][j]) < data2[i][j][k] && (i + 1)
* (j + 1) > 0)
data2[i][j][k] *= 2;
else
continue;
}
}
}
for (i = 0; i < data2.length; i++) {
for (j = 0; j < data2[0].length; j++) {
out.write(data2[i][j] + "\t");
}
}
out.close();
} catch (Exception e) {
System.out.println("Error= " + e);
}
}
    
}
