#include <stdio.h>
#include <Math.h>

#define PI 3.14159265
#define HEXAGON_SIDES 6 

typedef struct {
    double x;
    double y;
    double z;
} Point;

typedef struct {
    Point* vertices = (Point*)malloc(HEXAGON_SIDES * sizeof(Point));
} Hexagon;

Hexagon createHexagon(double x, double y, double z, double radius) {
    Point center;
    center.x = x;
    center.y = y;
    center.z = z;
    int sides = 6;
    Hexagon geometryVertex;
    for (int i = 1; i <= sides; i++) {
        int angle = 360 / sides;
        double currentAngle = (angle * i ) * (PI / 180);
        Point vertex;
        vertex.x = (center.x + (cos(currentAngle) * radius));
        vertex.y = (center.y + (sin(currentAngle) * radius));
        vertex.z = center.z;
        geometryVertex.vertices[i - 1] = vertex;
    }
    return geometryVertex;
}

Hexagon* createHexGrid(int width, int quadrants, double radius) {
    Hexagon* hexGrid = (Hexagon*)malloc(width * quadrants  * sizeof(Hexagon));
    Hexagon origin = createHexagon(0, 0, 0, radius);
    Hexagon tmpHexagon = origin;
    hexGrid[0] = origin;
    for(int g = 1; g < width * quadrants; g++) {
            // -- Top Right
        for(int i = 0 ; i < width; i++) {
            Hexagon hex = createHexagon(tmpHexagon.vertices[0].x + radius, tmpHexagon.vertices[0].y, tmpHexagon.vertices[0].z, radius);
            hexGrid[g] = hex;
            tmpHexagon = hex;
        }
        tmpHexagon = origin;
            // -- Top Left
        for(int i = 0 ; i < width; i++) {
            Hexagon hex = createHexagon(tmpHexagon.vertices[1].x - radius, tmpHexagon.vertices[1].y, tmpHexagon.vertices[1].z, radius);
            hexGrid[g] = hex;
            tmpHexagon = hex;
        }
        tmpHexagon = origin;
            // -- Bottom Left
        for(int i = 0 ; i < width; i++) {
            Hexagon hex = createHexagon(tmpHexagon.vertices[3].x - radius, tmpHexagon.vertices[3].y, tmpHexagon.vertices[3].z, radius);
            hexGrid[g] = hex;
            tmpHexagon = hex;
        }
        tmpHexagon = origin;
                // -- Bottom right
        for(int i = 0 ; i < width; i++) {
            Hexagon hex = createHexagon(tmpHexagon.vertices[4].x + radius, tmpHexagon.vertices[4].y, tmpHexagon.vertices[4].z, radius);
            hexGrid[g] = hex;
            tmpHexagon = hex;
        }
    }
    return hexGrid;
}

int main(){ 
    int width = 3;   
    int quadrants = 4;    
    Hexagon* grid = createHexGrid(width, quadrants, 2) ;
    FILE *file = fopen("map.json", "w");
    file = fopen("map.json", "w");
    fprintf(file, "{ hexagons: [");
    for(int i = 0; i < width * quadrants; i++) {
        for(int j = 0; j < HEXAGON_SIDES; j++) {
            fprintf(file, "{ x: %f, y: %f, z: %f},\n", grid[i].vertices[j].x, grid[i].vertices[j].y, grid[i].vertices[j].z);
        }
    }
    fprintf(file, "]} \n");
    fclose(file);    
    return 0;
}