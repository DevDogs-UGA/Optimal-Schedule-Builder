package edu.uga.devdogs.sampledataparser.records;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Represents the distances between buildings on campus.
 * It contains an array of building names and a distance matrix where each element represents
 * the distance between two buildings. The index of a building in the buildings array matches
 * its index in the matrix.
 *
 * @param buildings The array of building names.
 * @param matrix    The distance matrix where matrix[i][j] is the distance between buildings[i] and buildings[j].
 */
public record Distances(List<String> buildings,
                        @SerializedName("distance_matrix")
                        List<List<Double>> matrix) {
}
