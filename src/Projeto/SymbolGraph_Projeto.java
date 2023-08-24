/******************************************************************************
 *  Compilation:  javac SymbolGraph.java
 *  Execution:    java SymbolGraph filename.txt delimiter
 *  Dependencies: ST.java Graph.java In.java StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/41graph/routes.txt
 *                https://algs4.cs.princeton.edu/41graph/movies.txt
 *                https://algs4.cs.princeton.edu/41graph/moviestiny.txt
 *                https://algs4.cs.princeton.edu/41graph/moviesG.txt
 *                https://algs4.cs.princeton.edu/41graph/moviestopGrossing.txt
 *
 *  %  java SymbolGraph routes.txt " "
 *  JFK
 *     MCO
 *     ATL
 *     ORD
 *  LAX
 *     PHX
 *     LAS
 *
 *  % java SymbolGraph movies.txt "/"
 *  Tin Men (1987)
 *     Hershey, Barbara
 *     Geppi, Cindy
 *     Jones, Kathy (II)
 *     Herr, Marcia
 *     ...
 *     Blumenfeld, Alan
 *     DeBoy, David
 *  Bacon, Kevin
 *     Woodsman, The (2004)
 *     Wild Things (1998)
 *     Where the Truth Lies (2005)
 *     Tremors (1990)
 *     ...
 *     Apollo 13 (1995)
 *     Animal House (1978)
 *
 *
 *  Assumes that input file is encoded using UTF-8.
 *  % iconv -f ISO-8859-1 -t UTF-8 movies-iso8859.txt > movies.txt
 *
 ******************************************************************************/

package Projeto;

import algs4.*;

/**
 * The {@code SymbolGraph} class represents an undirected graph, where the
 * vertex names are arbitrary strings.
 * By providing mappings between string vertex names and integers,
 * it serves as a wrapper around the
 * {@link Graph} data type, which assumes the vertex names are integers
 * between 0 and <em>V</em> - 1.
 * It also supports initializing a symbol graph from a file.
 * <p>
 * This implementation uses an {@link ST} to map from strings to integers,
 * an array to map from integers to strings, and a {@link Graph} to store
 * the underlying graph.
 * The <em>indexOf</em> and <em>contains</em> operations take time
 * proportional to log <em>V</em>, where <em>V</em> is the number of vertices.
 * The <em>nameOf</em> operation takes constant time.
 * <p>
 * For additional documentation, see <a href="https://algs4.cs.princeton.edu/41graph">Section 4.1</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class SymbolGraph_Projeto {
    public ST<Vertice, Integer> st;  // string -> index
    private Vertice[] keys;           // index  -> string
    private Graph graph;             // the underlying graph

    /**
     * Initializes a graph from a file using the specified delimiter.
     * Each line in the file contains
     * the name of a vertex, followed by a list of the names
     * of the vertices adjacent to that vertex, separated by the delimiter.
     *
     * @param filename  the name of the file
     * @param delimiter the delimiter between fields
     */
    public SymbolGraph_Projeto(String filename, ST<Integer, Vertice> st1, String delimiter) {
        st = new ST<Vertice, Integer>();

        // First pass builds the index by reading strings to associate
        // distinct strings with an index
        In in = new In(filename);
        // while (in.hasNextLine()) {
        int k = 0;
        while (!in.isEmpty()) {
            String[] a = in.readLine().split(delimiter);
            Vertice v = st1.get(Integer.parseInt(a[0]));
            if (!st.contains(v)) {
                st.put(v, k);
                k++;
            }
        }

        // inverted index to get string keys in an array
        keys = new Vertice[st.size()];
        k = 0;
        for (Vertice v : st.keys()) {
            keys[k] = v;
            k++;
        }

        // second pass builds the graph by connecting first vertex on each
        // line to all others
        graph = new Graph(st.size());
        in = new In(filename);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delimiter);
            int v = Integer.parseInt(a[0]);
            for (int i = 1; i < a.length; i++) {
                int w = Integer.parseInt(a[i]);
                graph.addEdge(v, w);
            }
        }
    }

    /**
     * Does the graph contain the vertex named {@code s}?
     *
     * @param s the name of a vertex
     * @return {@code true} if {@code s} is the name of a vertex, and {@code false} otherwise
     */
    public boolean contains(Vertice s) {
        return st.contains(s);
    }

    /**
     * Returns the integer associated with the vertex named {@code s}.
     *
     * @param s the name of a vertex
     * @return the integer (between 0 and <em>V</em> - 1) associated with the vertex named {@code s}
     * @deprecated Replaced by {@link #indexOf(Vertice)}.
     */
    @Deprecated
    public int index(Vertice s) {
        return st.get(s);
    }


    /**
     * Returns the integer associated with the vertex named {@code s}.
     *
     * @param s the name of a vertex
     * @return the integer (between 0 and <em>V</em> - 1) associated with the vertex named {@code s}
     */
    public int indexOf(Vertice s) {
        return st.get(s);
    }

    /**
     * Returns the name of the vertex associated with the integer {@code v}.
     *
     * @param v the integer corresponding to a vertex (between 0 and <em>V</em> - 1)
     * @return the name of the vertex associated with the integer {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     * @deprecated Replaced by {@link #nameOf(int)}.
     */
    @Deprecated
    public Vertice name(int v) {
        validateVertex(v);
        return keys[v];
    }

    /**
     * Returns the name of the vertex associated with the integer {@code v}.
     *
     * @param v the integer corresponding to a vertex (between 0 and <em>V</em> - 1)
     * @return the name of the vertex associated with the integer {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Vertice nameOf(int v) {
        validateVertex(v);
        return keys[v];
    }

    /**
     * Returns the graph assoicated with the symbol graph. It is the client's responsibility
     * not to mutate the graph.
     *
     * @return the graph associated with the symbol graph
     * @deprecated Replaced by {@link #graph()}.
     */
    @Deprecated
    public Graph G() {
        return graph;
    }

    /**
     * Returns the graph assoicated with the symbol graph. It is the client's responsibility
     * not to mutate the graph.
     *
     * @return the graph associated with the symbol graph
     */
    public Graph graph() {
        return graph;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = graph.V();
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }


    /**
     * Unit tests the {@code SymbolGraph} data type.
     *
     * @param args the command-line arguments
     */
  /*  public static void main(String[] args) {
        String filename  = args[0];
        String delimiter = args[1];
        SymbolGraph_Projeto sg = new SymbolGraph_Projeto(filename, delimiter);
        Graph graph = sg.graph();
        while (StdIn.hasNextLine()) {
            String source = StdIn.readLine();
            if (sg.contains(source)) {
                int s = sg.index(source);
                for (int v : graph.adj(s)) {
                    StdOut.println("   " + sg.name(v));
                }
            }
            else {
                StdOut.println("input not contain '" + source + "'");
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
