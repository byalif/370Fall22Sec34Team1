package edu.qc.seclass.glm.Models;
public class RandomColors {
    int arr[] = { getColor(118, 194, 138), getColor(167, 122, 204),
            getColor(201, 119, 145),   getColor(108, 172, 184),
            getColor(124, 143, 196),   getColor(194, 153, 122),
            getColor(181, 175, 130),   getColor(142, 171, 123)};


    int arr2[] = { getColor(188, 198, 214), getColor(223, 211, 232),
            getColor(209, 232, 213),   getColor(205, 190, 209),
            getColor(190, 206, 209),getColor(124, 143, 196),
            getColor(207, 202, 217),   getColor(224, 197, 210)};

    public int getColor(int red, int green, int blue){
        return android.graphics.Color.argb(255, red , green, blue);
    }

    public RandomColors() {}

    public int getColor(int i) {
        int color = arr[i%8];
        return color;
    }

    public int getColor2(int i) {
        int color = arr2[i%8];
        return color;
    }
}