# dgs-matching
weighted bipartite matching using the DGS (Demange, Gale, Sotomayor) algorithm

## Weighted Bipartite Matching

Weighted bipartite graphs are bipartite graphs in which each edge (x,y) has a weight,
or value, w(x,y).

The weight of a matching M is the sum of the weights of edges in M.

The maximum weighted matching M for a graph G has a matching weight greater than or equal
to any other matching M' for graph G.

## DGS (Demange, Gale, and Sotomayor) Algorithm

Initialization:

  1. For each good j, set p_j = 0 and owner_j = null.
  
  2. Initialize a queue Q to contain all bidders i.
  
  3. Fix delta = 1/(n_g+1), where n_g is the number of goods.
  
While Q is not empty do:

  1. i = Q.deque().
  
  2. Find j that maximizes w_{ij} - p_j.
  
  3. If w_{ij} - p_j >= 0 then
  
      3a. Enque current owner_j into Q.
    
      3b. owner_j = i.
    
      3c. p_j = p_j + delta.
    
Resultant matching: the set of (owner_j, j) for all j.

## EE 382V Social Computing - Assignment 1 - #5

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
