package edu.uga.devdogs.records;

import com.google.gson.annotations.SerializedName;

public record Distances(String[] buildings,
                        @SerializedName("distance_matrix")
                        float[][] matrix) {
}
