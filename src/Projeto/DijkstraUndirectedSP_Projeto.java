/******************************************************************************
 *  Compilation:  javac DijkstraUndirectedSP.java
 *  Execution:    java DijkstraUndirectedSP input.txt s
 *  Dependencies: EdgeWeightedGraph.java IndexMinPQ.java Stack.java Edge.java
 *  Data files:   https://algs4.cs.princeton.edu/43mst/tinyEWG.txt
 *                https://algs4.cs.princeton.edu/43mst/mediumEWG.txt
 *                https://algs4.cs.princeton.edu/43mst/largeEWG.txt
 *
 *  Dijkstra's algorithm. Computes the shortest path tree.
 *  Assumes all weights are nonnegative.
 *
 *  % java DijkstraUndirectedSP tinyEWG.txt 6
 *  6 to 0 (0.58)  6-0 0.58000
 *  6 to 1 (0.76)  6-2 0.40000   1-2 0.36000
 *  6 to 2 (0.40)  6-2 0.40000
 *  6 to 3 (0.52)  3-6 0.52000
 *  6 to 4 (0.93)  6-4 0.93000
 *  6 to 5 (1.02)  6-2 0.40000   2-7 0.34000   5-7 0.28000
 *  6 to 6 (0.00)
 *  6 to 7 (0.74)  6-2 0.40000   2-7 0.34000
 *
 *  % java DijkstraUndirectedSP mediumEWG.txt 0
 *  0 to 0 (0.00)
 *  0 to 1 (0.71)  0-44 0.06471   44-93  0.06793  ...   1-107 0.07484
 *  0 to 2 (0.65)  0-44 0.06471   44-231 0.10384  ...   2-42  0.11456
 *  0 to 3 (0.46)  0-97 0.07705   97-248 0.08598  ...   3-45  0.11902
 *  ...
 *
 *  % java DijkstraUndirectedSP largeEWG.txt 0
 *  0 to 0 (0.00)  
 *  0 to 1 (0.78)  0-460790 0.00190  460790-696678 0.00173   ...   1-826350 0.00191
 *  0 to 2 (0.61)  0-15786  0.00130  15786-53370   0.00113   ...   2-793420 0.00040
 *  0 to 3 (0.31)  0-460790 0.00190  460790-752483 0.00194   ...   3-698373 0.00172
 *
 ******************************************************************************/

package Projeto;


import algs4.*;

/**
 *  The {@code DijkstraUndirectedSP} class represents a data type for solving
 *  the single-source shortest paths problem in edge-weighted graphs
 *  where the edge weights are nonnegative.
 *  <p>
 *  This implementation uses Dijkstra's algorithm with a binary heap.
 *  The constructor takes time proportional to <em>E</em> log <em>V</em>,
 *  where <em>V</em> is the number of vertices and <em>E</em> is the number of edges.
 *  Each call to {@code distTo(int)} and {@code hasPathTo(int)} takes constant time;
 *  each call to {@code pathTo(int)} takes time proportional to the number of
 *  edges in the shortest path returned.
 *  <p>
 *  For additional documentation,
 *  see <a href="https://algs4.cs.princeton.edu/44sp">Section 4.4</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *  See {@link DijkstraSP} for a version on edge-weighted digraphs.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *  @author Nate Liu
 */
public class DijkstraUndirectedSP_Projeto {
    private double[] distToW;          // distTo[v] = distance  of shortest s->v path
    private double[] distToT;          // distTo[v] = distance  of shortest s->v path
    private Edge_Projeto[] edgeToW;            // edgeTo[v] = last edge on shortest s->v path
    private Edge_Projeto[] edgeToT;
    private IndexMinPQ<Double> pqW;    // priority queue of vertices
    private IndexMinPQ<Double> pqT;

    /**
     * Computes a shortest-paths tree from the source vertex {@code s} to every
     * other vertex in the edge-weighted graph {@code G}.
     *
     * @param  G the edge-weighted digraph
     * @param  s the source vertex
     * @throws IllegalArgumentException if an edge weight is negative
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     */
    public DijkstraUndirectedSP_Projeto(EdgeWeightedGraph_Projeto G, int s) {
        for (Edge_Projeto e : G.edges()) {
            if (e.weight() < 0)
                throw new IllegalArgumentException("edge " + e + " has negative weight");
            if (e.getTempo() < 0)
                throw new IllegalArgumentException("edge " + e + " has negative tempo");
        }

        distToW = new double[G.V()];
        distToT = new double[G.V()];
        edgeToW = new Edge_Projeto[G.V()];
        edgeToT = new Edge_Projeto[G.V()];

        validateVertexW(s);
        validateVertexT(s);

        for (int v = 0; v < G.V(); v++) {
            distToW[v] = Double.POSITIVE_INFINITY;
            distToT[v] = Double.POSITIVE_INFINITY;
        }
        distToW[s] = 0.0;
        distToT[s] = 0.0;

        // relax vertices in order of distance from s
        pqW = new IndexMinPQ<Double>(G.V());
        pqW.insert(s, distToW[s]);
        while (!pqW.isEmpty()) {
            int v = pqW.delMin();
            for (Edge_Projeto e : G.adj(v)) {
                relaxW(e, v);
            }
        }

        pqT = new IndexMinPQ<Double>(G.V());
        pqT.insert(s, distToT[s]);
        while (!pqT.isEmpty()) {
            int v = pqT.delMin();
            for (Edge_Projeto e : G.adj(v)) {
                relaxT(e,v);
            }
        }

        // check optimality conditions
        assert checkW(G, s);
        assert checkT(G, s);
    }

    public DijkstraUndirectedSP_Projeto(EdgeWeightedGraph_Projeto G, int s, int[]a) {
        for (Edge_Projeto e : G.edges()) {
            if (e.weight() < 0)
                throw new IllegalArgumentException("edge " + e + " has negative weight");
            if (e.getTempo() < 0)
                throw new IllegalArgumentException("edge " + e + " has negative tempo");
        }

        distToW = new double[G.V()];
        distToT = new double[G.V()];
        edgeToW = new Edge_Projeto[G.V()];
        edgeToT = new Edge_Projeto[G.V()];

        validateVertexW(s);
        validateVertexT(s);

        for (int v = 0; v < G.V(); v++) {
            distToW[v] = Double.POSITIVE_INFINITY;
            distToT[v] = Double.POSITIVE_INFINITY;
        }
        distToW[s] = 0.0;
        distToT[s] = 0.0;

        // relax vertices in order of distance from s
        pqW = new IndexMinPQ<Double>(G.V());
        pqW.insert(s, distToW[s]);
        while (!pqW.isEmpty()) {
            int v = pqW.delMin();
            for (Edge_Projeto e : G.adj(v)) {
                int count=0;
                int x = e.either();
                int y = e.other(x);
                for (int i = 0; i < a.length;i++) {
                    if(x!=a[i])
                        count++;
                    if(y!=a[i])
                        count++;
                    if(count==2)
                        relaxW(e, v);
                }
            }
        }

        pqT = new IndexMinPQ<Double>(G.V());
        pqT.insert(s, distToT[s]);
        while (!pqT.isEmpty()) {
            int v = pqT.delMin();
            for (Edge_Projeto e : G.adj(v)) {
                relaxT(e,v);
            }
        }

        // check optimality conditions
        assert checkW(G, s);
        assert checkT(G, s);
    }



    // relax edge e and update pq if changed
    private void relaxW(Edge_Projeto e, int v) {
        int w = e.other(v);
        if (distToW[w] > distToW[v] + e.weight()) {
            distToW[w] = distToW[v] + e.weight();
            edgeToW[w] = e;
            if (pqW.contains(w)) pqW.decreaseKey(w, distToW[w]);
            else                pqW.insert(w, distToW[w]);
        }
    }

    private void relaxT(Edge_Projeto e, int v) {
        int w = e.other(v);
        if (distToT[w] > distToT[v] + e.getTempo()) {
            distToT[w] = distToT[v] + e.getTempo();
            edgeToT[w] = e;
            if (pqT.contains(w)) pqT.decreaseKey(w, distToT[w]);
            else                pqT.insert(w, distToT[w]);
        }
    }

    /**
     * Returns the length of a shortest path between the source vertex {@code s} and
     * vertex {@code v}.
     *
     * @param  v the destination vertex
     * @return the length of a shortest path between the source vertex {@code s} and
     *         the vertex {@code v}; {@code Double.POSITIVE_INFINITY} if no such path
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public double distToW(int v) {
        validateVertexW(v);
        return distToW[v];
    }

    public double distToT(int v) {
        validateVertexT(v);
        return distToT[v];
    }

    /**
     * Returns true if there is a path between the source vertex {@code s} and
     * vertex {@code v}.
     *
     * @param  v the destination vertex
     * @return {@code true} if there is a path between the source vertex
     *         {@code s} to vertex {@code v}; {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean hasPathToW(int v) {
        validateVertexW(v);
        return distToW[v] < Double.POSITIVE_INFINITY;
    }

    public boolean hasPathToT(int v) {
        validateVertexT(v);
        return distToT[v] < Double.POSITIVE_INFINITY;
    }

    /**
     * Returns a shortest path between the source vertex {@code s} and vertex {@code v}.
     *
     * @param  v the destination vertex
     * @return a shortest path between the source vertex {@code s} and vertex {@code v};
     *         {@code null} if no such path
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Edge_Projeto> pathToW(int v) {
        validateVertexW(v);
        if (!hasPathToW(v)) return null;
        Stack<Edge_Projeto> path = new Stack<Edge_Projeto>();
        int x = v;
        for (Edge_Projeto e = edgeToW[v]; e != null; e = edgeToW[x]) {
            path.push(e);
            x = e.other(x);
        }
        return path;
    }

    public Iterable<Edge_Projeto> pathToT(int v) {
        validateVertexT(v);
        if (!hasPathToT(v)) return null;
        Stack<Edge_Projeto> path = new Stack<Edge_Projeto>();
        int x = v;
        for (Edge_Projeto e = edgeToT[v]; e != null; e = edgeToT[x]) {
            path.push(e);
            x = e.other(x);
        }
        return path;
    }


    // check optimality conditions:
    // (i) for all edges e = v-w:            distTo[w] <= distTo[v] + e.weight()
    // (ii) for all edge e = v-w on the SPT: distTo[w] == distTo[v] + e.weight()
    private boolean checkW(EdgeWeightedGraph_Projeto G, int s) {

        // check that edge weights are nonnegative
        for (Edge_Projeto e : G.edges()) {
            if (e.weight() < 0) {
                System.err.println("negative edge weight detected");
                return false;
            }
        }

        // check that distTo[v] and edgeTo[v] are consistent
        if (distToW[s] != 0.0 || edgeToW[s] != null) {
            System.err.println("distTo[s] and edgeTo[s] inconsistent");
            return false;
        }
        for (int v = 0; v < G.V(); v++) {
            if (v == s) continue;
            if (edgeToW[v] == null && distToW[v] != Double.POSITIVE_INFINITY) {
                System.err.println("distTo[] and edgeTo[] inconsistent");
                return false;
            }
        }

        // check that all edges e = v-w satisfy distTo[w] <= distTo[v] + e.weight()
        for (int v = 0; v < G.V(); v++) {
            for (Edge_Projeto e : G.adj(v)) {
                int w = e.other(v);
                if (distToW[v] + e.weight() < distToW[w]) {
                    System.err.println("edge " + e + " not relaxed");
                    return false;
                }
            }
        }

        // check that all edges e = v-w on SPT satisfy distTo[w] == distTo[v] + e.weight()
        for (int w = 0; w < G.V(); w++) {
            if (edgeToW[w] == null) continue;
            Edge_Projeto e = edgeToW[w];
            if (w != e.either() && w != e.other(e.either())) return false;
            int v = e.other(w);
            if (distToW[v] + e.getTempo() != distToW[w]) {
                System.err.println("edge " + e + " on shortest path not tight");
                return false;
            }
        }
        return true;
    }

    private boolean checkT(EdgeWeightedGraph_Projeto G, int s) {

        // check that edge weights are nonnegative
        for (Edge_Projeto e : G.edges()) {
            if (e.getTempo() < 0) {
                System.err.println("negative edge tempo detected");
                return false;
            }
        }

        // check that distTo[v] and edgeTo[v] are consistent
        if (distToT[s] != 0.0 || edgeToT[s] != null) {
            System.err.println("distTo[s] and edgeTo[s] inconsistent");
            return false;
        }
        for (int v = 0; v < G.V(); v++) {
            if (v == s) continue;
            if (edgeToT[v] == null && distToT[v] != Double.POSITIVE_INFINITY) {
                System.err.println("distTo[] and edgeTo[] inconsistent");
                return false;
            }
        }

        // check that all edges e = v-w satisfy distTo[w] <= distTo[v] + e.weight()
        for (int v = 0; v < G.V(); v++) {
            for (Edge_Projeto e : G.adj(v)) {
                int w = e.other(v);
                if (distToT[v] + e.getTempo() < distToT[w]) {
                    System.err.println("edge " + e + " not relaxed");
                    return false;
                }
            }
        }

        // check that all edges e = v-w on SPT satisfy distTo[w] == distTo[v] + e.weight()
        for (int w = 0; w < G.V(); w++) {
            if (edgeToT[w] == null) continue;
            Edge_Projeto e = edgeToT[w];
            if (w != e.either() && w != e.other(e.either())) return false;
            int v = e.other(w);
            if (distToT[v] + e.getTempo() != distToT[w]) {
                System.err.println("edge " + e + " on shortest path not tight");
                return false;
            }
        }
        return true;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertexW(int v) {
        int V = distToW.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    private void validateVertexT(int v) {
        int V = distToT.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    /**
     * Unit tests the {@code DijkstraUndirectedSP} data type.
     *
     * @param args the command-line arguments
     */
   /* public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph_Projeto G = new EdgeWeightedGraph_Projeto(in);
        int s = Integer.parseInt(args[1]);

        // compute shortest paths
        DijkstraUndirectedSP_Projeto sp = new DijkstraUndirectedSP_Projeto(G, s);


        // print shortest path
        for (int t = 0; t < G.V(); t++) {
            if (sp.hasPathTo(t)) {
                StdOut.printf("%d to %d (%.2f)  ", s, t, sp.distTo(t));
                for (Edge e : sp.pathTo(t)) {
                    StdOut.print(e + "   ");
                }
                StdOut.println();
            }
            else {
                StdOut.printf("%d to %d         no path\n", s, t);
            }
        }
    }*/

}

/******************************************************************************
 *  Copyright 2002-2018, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/
