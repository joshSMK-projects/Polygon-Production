Project Name
    Polygon Production

Project Description
    Takes shape input from a text file that is formatted to the following:
        Identifying character for the shape:
            P - Polygons (Triangles, Square, any shape with more than two sides excl. round shapes)
            C - Circle
            S - Semi-Circle
        
        Followed by amount of sides in shape (excl. round shapes).
            - In case of round shapes the next two numbers are the x and y coordinates of the centre.
        
        Finally the x and y coordinates respectfully for each point in the shape (excl. round shapes).
            - For circles the last number is the radius.
            - For semi-circles the last two numbers are the x and y coordinates of the point on the circumference which is perpendicular to the base.

        For example:
            P 3 4 0 4 8 7 8: Is a polygon with 3 sides with the three points being (4, 0), (4, 8) and (7, 8)
            C 2 1 5: Is a circle with radius 2 and a centre of (1, 5)
            S 2 1 2 2: Is a semi-circle with a centre of (2, 1) and a point perpendicular to the base of (2, 2)
    
    After parsing the shapes they are then added to two lists.
    One is unsorted and the other is sorted by the area. If the shapes are of the same area we then sort by the shortest distance from the origin.

    Note 1: This project only allows ONE file for input.
    Note 2: This project does not use Collections, so it is made from scratch.

How to run the project
    1. Download project
    2. Open the command prompt or terminal on the base folder of the project
    3. Place a text document with shapes specified in the format specified above
        - Example files are supplied for reference
    4. Type the following
        - javac PA2B.java
        - java PA2B file.txt (where file.txt is your file)

How to use the project
    Supply shape specifics to the text file specified in the format above to sort and display shapes based on their area and distance from origin.