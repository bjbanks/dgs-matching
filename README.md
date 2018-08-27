# dgs-matching
weighted bipartite matching using the DGS (Demange, Gale, Sotomayor) algorithm

## EE 382V Social Computing - Assignment 1 - Problem 5

```
5. (35 points) This question requires you to implement DGS Algorithm for the weighted bipartite matching.
The interface is identical to Kuhn-Munkres algorithm. The name of your program should be DGS.

The goal is to understand how the algorithm works; we will not evaluate your submission on
the running time. The input to your program would be a square matrix w of non-negative integers, where
w[i, j] is the weight of the edge between the item i and the bidder j. The program should output the
weight of the maximum weighted matching followed by the sorted list of edges in the matching.
A sample input and output is shown below.

input.txt
--------
3 // number of rows and columns
12 2 4
8 7 6
7 5 2

The program should produce

23 // weight of the matching
(1,1)
(2,3)
(3,2)
```
