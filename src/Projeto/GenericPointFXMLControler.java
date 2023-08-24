package Projeto;

import algs4.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GenericPointFXMLControler<T extends Vertice> {
    private static String SET_DELIMITER = ",";
    private static int radius = 35;
    public Pane graphGroup;

    public TableView<Vertice> verticesTable;
    public TableColumn<Vertice, String> name;
    public TableColumn<Vertice, Integer> x;
    public TableColumn<Vertice, Integer> y;
    public TableColumn<Vertice, Integer> z;
    public TableColumn<Vertice, Integer> type;
    public TableColumn<Vertice, String> description;

    public TextField tname;
    public TextField tx;
    public TextField ty;
    public TextField tz;
    public TextField ttype;
    public TextField tdescription;

    public TextFlow result;
    public TextField p1;
    public TextField p2;
    public TextField set;
    public Button cmc;
    public Button cmr;
    public Button cmcs;
    public Button conexo;

    public Button scmc;
    public Button scmr;
    public Button scmcs;
    public Button sconexo;
    public Button clear;


    public ComboBox<String> floorComboBox;
    public ComboBox<String> verticesComboBox;
    public ComboBox<String> pesquisasComboBox;
    public ComboBox<String> floor2ComboBox;
    public Label floor2;

    private SymbolGraph_Projeto sg;
    private ArrayList<T> vertices = new ArrayList<>();

    public GenericPointFXMLControler(SymbolGraph_Projeto sg) {
        this.sg = sg;
    }

    @FXML
    public void initialize() {
        fillFloorComboBox();
        fillVerticesComboBox();
        fillPesquisasComboBox();
        fillFloor2ComboBox();

        createGraphGroup(vertices);
        addEdges();

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        x.setCellValueFactory(new PropertyValueFactory<>("X"));
        y.setCellValueFactory(new PropertyValueFactory<>("Y"));
        z.setCellValueFactory(new PropertyValueFactory<>("Z"));
        type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));

        name.setCellFactory(TextFieldTableCell.forTableColumn());
        //x.setCellFactory(TextFieldTableCell.forTableColumn());
        //y.setCellFactory(TextFieldTableCell.forTableColumn());
        //z.setCellFactory(TextFieldTableCell.forTableColumn());
        //type.setCellFactory(TextFieldTableCell.forTableColumn());
        description.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    /**
     * adiciona um vertice ao array de vertices
     * @param vertice vertice a adicionar
     */
    public void addVertice(T vertice) {
        vertices.add(vertice);
    }

    /**
     * preenche a Floor combo box com os vários pisos possíveis
     */
    private void fillFloorComboBox() {
        floorComboBox.getItems().add("All");
        for (T t : vertices) {
            String floor = Integer.toString(t.getZ());
            if (!floorComboBox.getItems().contains(floor))
                floorComboBox.getItems().add(floor);
        }
    }

    /**
     * preenche a Floor combo box com os vários pisos possíveis
     */
    private void fillFloor2ComboBox() {
        for (T t : vertices) {
            String floor = Integer.toString(t.getZ());
            if (!floor2ComboBox.getItems().contains(floor))
                floor2ComboBox.getItems().add(floor);
        }
    }

    /**
     * preenche a Vertice combo box com os vários vertices possíveis
     */
    private void fillVerticesComboBox() {
        verticesComboBox.getItems().add("All");
        verticesComboBox.getItems().add("Students");
        verticesComboBox.getItems().add("Professors");
        verticesComboBox.getItems().add("Rooms");
        verticesComboBox.getItems().add("Pontos de Passagem");
    }

    /**
     * preenche a Pesquisas combo box com os vários pesquisas possíveis
     */
    private void fillPesquisasComboBox() {
        pesquisasComboBox.getItems().add("Conexo");
        pesquisasComboBox.getItems().add("Caminho mais curto entre duas salas");
        pesquisasComboBox.getItems().add("Caminho mais rápido");
        pesquisasComboBox.getItems().add("Caminho mais curto evitando um determinado set de pontos");
        pesquisasComboBox.getItems().add("Caminho mais curto até ao ponto exterior");
        pesquisasComboBox.getItems().add("Selecionar subgrafo + Conexo");
        pesquisasComboBox.getItems().add("Selecionar subgrafo + Caminho mais curto entre duas salas");
        pesquisasComboBox.getItems().add("Selecionar subgrafo + Caminho mais rápido");
        pesquisasComboBox.getItems().add("Selecionar subgrafo + Caminho mais curto evitando um determinado set de pontos");
        pesquisasComboBox.getItems().add("Selecionar subgrafo + Caminho mais curto até ao ponto exterior");
    }

    /**
     * cria os círculos que representam os vértices do grafo
     * @param pointsItems array de Vértices
     */
    private void createGraphGroup(ArrayList<T> pointsItems) {
        graphGroup.getChildren().clear();
        int i = 0;
        for (T t : pointsItems) {
            Circle c = new Circle(t.getX(), t.getY(), radius);
            if (t.getZ() == 0)
                c.setFill(Color.GRAY);
            else if (t.getZ() == 1)
                c.setFill(Color.GREEN);
            else if (t.getZ() == 2)
                c.setFill(Color.ORANGE);
            Text text = new Text(t.getName() + " (" + i + ")");
            StackPane stackPane = new StackPane();
            stackPane.setLayoutX(t.getX() - radius);
            stackPane.setLayoutY(t.getY() - radius);
            stackPane.getChildren().addAll(c, text);
            graphGroup.getChildren().add(stackPane);
            i++;
        }
    }

    /**
     * adiciona à Vertices Table os vértices recebidos no array list
     * @param pointsItems array list de Vértices
     */
    private void createVerticesGroup(ArrayList<T> pointsItems) {
        verticesTable.getItems().clear();
        for (T t : pointsItems)
            verticesTable.getItems().add(t);
    }

    /**
     * retorna um array com os vértices do floor pretendido
     * @param floor floor a pesquisar
     * @return array com os vértices do floor pretendido
     */
    private ArrayList<T> getVerticesByFloor(int floor) {
        ArrayList<T> verticesByFloor = new ArrayList<>();

        for (T t : this.vertices) {
            if (t.getZ() == floor)
                verticesByFloor.add(t);
        }
        return verticesByFloor;
    }

    /**
     * retorna um array com os vértices do type pretendido
     * @param type type a pesquisar
     * @return array com os vértices do type pretendido
     */
    private ArrayList<T> getVerticesByType(int type) {
        ArrayList<T> verticesByComboBox = new ArrayList<>();

        for (T t : this.vertices) {
            if (t.getType() == type)
                verticesByComboBox.add(t);
        }
        return verticesByComboBox;
    }

    /**
     * handler que lista os vértices do grafo dependendo da opção selecionada pelo utilizador
     * @param actionEvent
     */
    public void handleFloorChoiceAction(ActionEvent actionEvent) {
        result.setVisible(false);
        graphGroup.setVisible(true);
        String floor = floorComboBox.getValue();
        if (floor.compareTo("All") == 0) {
            createGraphGroup(this.vertices);
            addEdges();
        } else
            createGraphGroup(getVerticesByFloor(Integer.parseInt(floor)));
    }

    /**
     * handler que lista o resultado da pesquisa sobre o grafo
     * @param actionEvent
     */
    public void handleFloor2ChoiceAction(ActionEvent actionEvent) {
        result.setVisible(false);
        graphGroup.setVisible(true);
        String floor = floor2ComboBox.getValue();
        String pesquisar = pesquisasComboBox.getValue();
        if (pesquisar.compareTo("Selecionar subgrafo + Conexo") == 0) {
            subConexo(getVerticesByFloor(Integer.parseInt(floor)));
        }else if (pesquisar.compareTo("Selecionar subgrafo + Caminho mais curto entre duas salas") == 0) {
            SubCaminhoMaisCurto(getVerticesByFloor(Integer.parseInt(floor)));
        } else if (pesquisar.compareTo("Selecionar subgrafo + Caminho mais rápido") == 0) {
            SubCaminhoMaisRapido(getVerticesByFloor(Integer.parseInt(floor)));
        } else if (pesquisar.compareTo("Selecionar subgrafo + Caminho mais curto evitando um determinado set de pontos") == 0) {
            SubCaminhoMaisCurtoSet(getVerticesByFloor(Integer.parseInt(floor)));
        } else if (pesquisar.compareTo("Selecionar subgrafo + Caminho mais curto até ao ponto exterior") == 0) {
            SubCaminhoMaisCurto(getVerticesByFloor(Integer.parseInt(floor)));
        }
    }

    /**
     * handler que exibe os buttons e textfields pretendidos dependendo da opção selecionada pelo utilizador
     * @param actionEvent
     */
    public void handlePesquisasChoiceAction(ActionEvent actionEvent) {
        String pesquisar = pesquisasComboBox.getValue();
        if (pesquisar.compareTo("Caminho mais curto entre duas salas") == 0) {
            p1.setVisible(true);
            p2.setVisible(true);
            set.setVisible(false);
            cmc.setVisible(true);
            cmr.setVisible(false);
            cmcs.setVisible(false);
            conexo.setVisible(false);
            floor2.setVisible(false);
            floor2ComboBox.setVisible(false);
            scmc.setVisible(false);
            scmr.setVisible(false);
            scmcs.setVisible(false);
            sconexo.setVisible(false);
        } else if (pesquisar.compareTo("Caminho mais rápido") == 0) {
            p1.setVisible(true);
            p2.setVisible(true);
            set.setVisible(false);
            cmc.setVisible(false);
            cmr.setVisible(true);
            cmcs.setVisible(false);
            conexo.setVisible(false);
            floor2.setVisible(false);
            floor2ComboBox.setVisible(false);
            scmc.setVisible(false);
            scmr.setVisible(false);
            scmcs.setVisible(false);
            sconexo.setVisible(false);
        } else if (pesquisar.compareTo("Caminho mais curto evitando um determinado set de pontos") == 0) {
            p1.setVisible(true);
            p2.setVisible(true);
            set.setVisible(true);
            cmc.setVisible(false);
            cmr.setVisible(false);
            cmcs.setVisible(true);
            conexo.setVisible(false);
            floor2.setVisible(false);
            floor2ComboBox.setVisible(false);
            scmc.setVisible(false);
            scmr.setVisible(false);
            scmcs.setVisible(false);
            sconexo.setVisible(false);
        } else if (pesquisar.compareTo("Caminho mais curto até ao ponto exterior") == 0) {
            p1.setVisible(true);
            p2.setVisible(true);
            set.setVisible(false);
            cmc.setVisible(true);
            cmr.setVisible(false);
            cmcs.setVisible(false);
            conexo.setVisible(false);
            floor2.setVisible(false);
            floor2ComboBox.setVisible(false);
            scmc.setVisible(false);
            scmr.setVisible(false);
            scmcs.setVisible(false);
            sconexo.setVisible(false);
        } else if (pesquisar.compareTo("Selecionar subgrafo + Caminho mais curto entre duas salas") == 0) {
            p1.setVisible(true);
            p2.setVisible(true);
            set.setVisible(false);
            cmc.setVisible(false);
            cmr.setVisible(false);
            cmcs.setVisible(false);
            conexo.setVisible(false);
            floor2.setVisible(true);
            floor2ComboBox.setVisible(true);
            scmc.setVisible(true);
            scmr.setVisible(false);
            scmcs.setVisible(false);
            sconexo.setVisible(false);
        } else if (pesquisar.compareTo("Selecionar subgrafo + Caminho mais rápido") == 0) {
            p1.setVisible(true);
            p2.setVisible(true);
            set.setVisible(false);
            cmc.setVisible(false);
            cmr.setVisible(false);
            cmcs.setVisible(false);
            conexo.setVisible(false);
            floor2.setVisible(true);
            floor2ComboBox.setVisible(true);
            scmc.setVisible(false);
            scmr.setVisible(true);
            scmcs.setVisible(false);
            sconexo.setVisible(false);
        } else if (pesquisar.compareTo("Selecionar subgrafo + Conexo") == 0) {
            p1.setVisible(true);
            p2.setVisible(false);
            set.setVisible(false);
            cmc.setVisible(false);
            cmr.setVisible(false);
            cmcs.setVisible(false);
            conexo.setVisible(false);
            floor2.setVisible(true);
            floor2ComboBox.setVisible(true);
            scmc.setVisible(false);
            scmr.setVisible(false);
            scmcs.setVisible(false);
            sconexo.setVisible(true);
        }else if (pesquisar.compareTo("Selecionar subgrafo + Caminho mais curto evitando um determinado set de pontos") == 0) {
            p1.setVisible(true);
            p2.setVisible(true);
            set.setVisible(true);
            cmc.setVisible(false);
            cmr.setVisible(false);
            cmcs.setVisible(false);
            conexo.setVisible(false);
            floor2.setVisible(true);
            floor2ComboBox.setVisible(true);
            scmc.setVisible(false);
            scmr.setVisible(false);
            scmcs.setVisible(true);
            sconexo.setVisible(false);
        } else if (pesquisar.compareTo("Selecionar subgrafo + Caminho mais curto até ao ponto exterior") == 0) {
            p1.setVisible(true);
            p2.setVisible(true);
            set.setVisible(false);
            cmc.setVisible(false);
            cmr.setVisible(false);
            cmcs.setVisible(false);
            conexo.setVisible(false);
            floor2.setVisible(true);
            floor2ComboBox.setVisible(true);
            scmc.setVisible(true);
            scmr.setVisible(false);
            scmcs.setVisible(false);
            sconexo.setVisible(false);
        } else if (pesquisar.compareTo("Conexo") == 0) {
            p1.setVisible(true);
            p2.setVisible(false);
            set.setVisible(false);
            cmc.setVisible(false);
            cmr.setVisible(false);
            cmcs.setVisible(false);
            conexo.setVisible(true);
            floor2.setVisible(false);
            floor2ComboBox.setVisible(false);
            scmc.setVisible(false);
            scmr.setVisible(false);
            scmcs.setVisible(false);
            sconexo.setVisible(false);
        }
    }

    /**
     * handler que imprime o resultado da pesquisa do caminho mais curto
     * @param actionEvent
     */
    public void handleCaminhoMaisCurto(ActionEvent actionEvent) {
        DijkstraUndirectedSP_Projeto sp = new DijkstraUndirectedSP_Projeto(MainGenericPointRenderer.u.g, Integer.parseInt(p1.getText()));
        for (int i = 0; i < MainGenericPointRenderer.u.g.V(); i++) {
            if (sp.hasPathToW(i)) {
                if (Integer.parseInt(p1.getText()) != i && i == Integer.parseInt(p2.getText())) {
                    graphGroup.setVisible(false);
                    result.setVisible(true);
                    Text text_1 = new Text("\n"+p1.getText() + " to " + i + " " + sp.distToW(i) + "\n\n");
                    text_1.setFont(Font.font("Verdana", 10));
                    result.getChildren().add(text_1);
                    for (Edge_Projeto e : sp.pathToW(i)) {
                        Text text_2 = new Text(e.v + "-" + e.w + " dist:" + e.weight + " tempo:" + e.getTempo() + "\n");
                        text_2.setFont(Font.font("Verdana", 10));
                        result.getChildren().add(text_2);
                    }
                }
            }
        }
        p1.setText("");
        p2.setText("");
    }

    /**
     * handler que imprime o resultado da pesquisa do caminho mais rápido
     * @param actionEvent
     */
    public void handleCaminhoMaisRapido(ActionEvent actionEvent) {
        DijkstraUndirectedSP_Projeto sp = new DijkstraUndirectedSP_Projeto(MainGenericPointRenderer.u.g, Integer.parseInt(p1.getText()));
        for (int i = 0; i < MainGenericPointRenderer.u.g.V(); i++) {
            if (sp.hasPathToT(i)) {
                if (Integer.parseInt(p1.getText()) != i && i == Integer.parseInt(p2.getText())) {
                    graphGroup.setVisible(false);
                    result.setVisible(true);
                    Text text_1 = new Text("\n"+p1.getText() + " to " + i + " " + sp.distToT(i) + "\n\n");
                    text_1.setFont(Font.font("Verdana", 10));
                    result.getChildren().add(text_1);
                    for (Edge_Projeto e : sp.pathToT(i)) {
                        Text text_2 = new Text(e.v + "-" + e.w + " dist:" + e.weight + " tempo:" + e.getTempo() + "\n");
                        text_2.setFont(Font.font("Verdana", 10));
                        result.getChildren().add(text_2);
                    }
                }
            }
        }
        p1.setText("");
        p2.setText("");
    }

    /**
     * handler que imprime o resultado da pesquisa do caminho mais curto evitando um set de pontos
     * @param actionEvent
     */
    public void handleCaminhoMaisCurtoSet(ActionEvent actionEvent) {
        String[] s = set.getText().split(SET_DELIMITER);

        int[] n= new int[s.length];
        for (int i = 0; i < s.length; i++)
            n[i] = Integer.parseInt(s[i]);

        DijkstraUndirectedSP_Projeto sp = new DijkstraUndirectedSP_Projeto(MainGenericPointRenderer.u.g, Integer.parseInt(p1.getText()),n);
        for (int i = 0; i < MainGenericPointRenderer.u.g.V(); i++) {
            if (sp.hasPathToW(i)) {
                if (Integer.parseInt(p1.getText()) != i && i == Integer.parseInt(p2.getText())) {
                    graphGroup.setVisible(false);
                    result.setVisible(true);
                    Text text_1 = new Text("\n"+p1.getText() + " to " + i + " " + sp.distToW(i) + "\n\n");
                    text_1.setFont(Font.font("Verdana", 10));
                    result.getChildren().add(text_1);
                    for (Edge_Projeto e : sp.pathToW(i)) {
                        Text text_2 = new Text(e.v + "-" + e.w + " dist:" + e.weight + " tempo:" + e.getTempo() + "\n");
                        text_2.setFont(Font.font("Verdana", 10));
                        result.getChildren().add(text_2);
                    }
                }
            }
        }
        p1.setText("");
        p2.setText("");
        set.setText("");
    }

    /**
     * handler que lista o resultado pesquisa do tipo de vértice
     * @param actionEvent
     */
    public void handleListarChoiceAction(ActionEvent actionEvent) {
        String listar = verticesComboBox.getValue();
        if (listar.compareTo("All") == 0)
            createVerticesGroup(this.vertices);
        else if (listar.compareTo("Students") == 0)
            createVerticesGroup(getVerticesByType(1));
        else if (listar.compareTo("Professors") == 0)
            createVerticesGroup(getVerticesByType(2));
        else if (listar.compareTo("Rooms") == 0)
            createVerticesGroup(getVerticesByType(3));
        else if (listar.compareTo("Pontos de Passagem") == 0)
            createVerticesGroup(getVerticesByType(4));
    }

    /**
     * handler que adiciona um novo vértice à tableview
     * @param actionEvent
     */
    public void handleAddButton(ActionEvent actionEvent) {
        Vertice v = new Vertice(Integer.parseInt(tx.getText()), Integer.parseInt(ty.getText()), Integer.parseInt(tz.getText()), Integer.parseInt(ttype.getText()));
        v.setName(tname.getText());
        v.setDescription(tdescription.getText());
        verticesTable.getItems().add(v);
        T t = (T) v;
        vertices.add(t);
        tname.setText("");
        tx.setText("");
        ty.setText("");
        tz.setText("");
        ttype.setText("");
        tdescription.setText("");
    }

    /**
     * handler que apaga as pesquisas anteriores
     * @param actionEvent
     */
    public  void handleClearButton (ActionEvent actionEvent){
        result.getChildren().clear();
    }

    /**
     * handler que remove o vértice selecionado da tableview
     * @param actionEvent
     */
    public void handleRemoveButton(ActionEvent actionEvent){
        verticesTable.getItems().removeAll(verticesTable.getSelectionModel().getSelectedItems());
        vertices.removeAll(verticesTable.getSelectionModel().getSelectedItems());
    }

    /**
     * handler que imprime se o grafo é conexo ou não
     * @param actionEvent
     */
    public void handleConexo(ActionEvent actionEvent) {
        int count = 0;
        Graph g1 = new Graph(MainGenericPointRenderer.u.g.V());
        for (Edge e : MainGenericPointRenderer.u.g.edges()) {
            int v = e.either();
            int w = e.other(v);
            g1.addEdge(v, w);
        }

        DepthFirstPaths dfs = new DepthFirstPaths(g1, Integer.parseInt(p1.getText()));
        for (int i = 0; i < g1.V(); i++) {
            if (!dfs.hasPathTo(i))
                count++;
        }
        if (count > 0) {
            graphGroup.getChildren().clear();
            result.setVisible(true);
            Text text_1 = new Text("\nO grafo não é conexo");
            text_1.setFont(Font.font("Verdana", 15));
            result.getChildren().add(text_1);
        } else {
            graphGroup.getChildren().clear();
            result.setVisible(true);
            Text text_1 = new Text("\nO grafo é conexo");
            text_1.setFont(Font.font("Verdana", 15));
            result.getChildren().add(text_1);
        }
        p1.setText("");
    }

    /**
     * handler que imprime se o subgrafo é conexo ou não
     * @param t array de vértices pertencentes ao subgrafo
     */
    public void subConexo(ArrayList<T> t) {
        int count = 0, q=0;
        ST<Integer, Vertice> st = new ST<>();
        ST<Integer, Edge> st2 = new ST<>();
        int aux=0,aux2=0;
        Graph g1 = new Graph(t.size());
        for (Edge e : MainGenericPointRenderer.u.g.edges()) {
            int v = e.either();
            int w = e.other(v);
            Vertice ver = MainGenericPointRenderer.u.st.get(v);
            Vertice ver1 = MainGenericPointRenderer.u.st.get(w);
            q=0;
            for (T teste : t) {
                if (teste == ver)
                    q++;
                if (teste == ver1)
                    q++;
                System.out.println("v: "+v + " w: "+w  + " q = "+q);
            }
            if(q==2) {
                if (!st.contains(aux) && !st.contains(aux + 1)) {
                    st.put(aux, ver);
                    aux++;
                    st.put(aux, ver1);
                    aux++;
                    Edge newE = new Edge(aux-1,aux,e.weight);
                    System.out.println("aux:"+aux);
                    st2.put(aux2,newE);
                    aux2++;
                }
            }
        }

        for (int i: st.keys()){
            Edge e = st2.get(i);
            int v = e.either();
            int w = e.other(v);
            g1.addEdge(v,w);
        }
        System.out.println(g1);

        DepthFirstPaths dfs = new DepthFirstPaths(g1, Integer.parseInt(p1.getText()));
        for (int i = 0; i < g1.V(); i++) {
            if (!dfs.hasPathTo(i))
                count++;
        }
        if (count > 0) {
            graphGroup.getChildren().clear();
            result.setVisible(true);
            Text text_1 = new Text("\nO grafo não é conexo");
            text_1.setFont(Font.font("Verdana", 15));
            result.getChildren().add(text_1);
        } else {
            graphGroup.getChildren().clear();
            result.setVisible(true);
            Text text_1 = new Text("\nO grafo é conexo");
            text_1.setFont(Font.font("Verdana", 15));
            result.getChildren().add(text_1);
        }
        p1.setText("");
    }

    /**
     * handler que imprime o caminho mais curto do subgrafo
     * @param t array de vértices pertencentes ao subgrafo
     */
    public void SubCaminhoMaisCurto(ArrayList<T> t) {
        DijkstraUndirectedSP_Projeto sp = new DijkstraUndirectedSP_Projeto(MainGenericPointRenderer.u.g, Integer.parseInt(p1.getText()));
        for (int i = 0; i < MainGenericPointRenderer.u.g.V(); i++) {
            Vertice v = MainGenericPointRenderer.u.st.get(i);
            for (T teste : t) {
                if (teste == v) {
                    if (sp.hasPathToW(i)) {
                        if (Integer.parseInt(p1.getText()) != i && i == Integer.parseInt(p2.getText())) {
                            graphGroup.setVisible(false);
                            result.setVisible(true);
                            Text text_1 = new Text("\n"+p1.getText() + " to " + i + " " + sp.distToW(i) + "\n\n");
                            text_1.setFont(Font.font("Verdana", 10));
                            result.getChildren().add(text_1);
                            for (Edge_Projeto e : sp.pathToW(i)) {
                                Text text_2 = new Text(e.v + "-" + e.w + " dist:" + e.weight + " tempo:" + e.getTempo() + "\n");
                                text_2.setFont(Font.font("Verdana", 10));
                                result.getChildren().add(text_2);
                            }
                        }
                    }
                }
            }
        }
        p1.setText("");
        p2.setText("");
    }

    /**
     * handler que imprime o caminho mais rápido do subgrafo
     * @param t array de vértices pertencentes ao subgrafo
     */
    public void SubCaminhoMaisRapido(ArrayList<T> t) {
        DijkstraUndirectedSP_Projeto sp = new DijkstraUndirectedSP_Projeto(MainGenericPointRenderer.u.g, Integer.parseInt(p1.getText()));
        for (int i = 0; i < MainGenericPointRenderer.u.g.V(); i++) {
            Vertice v = MainGenericPointRenderer.u.st.get(i);
            for (T teste : t) {
                if (teste == v) {
                    if (sp.hasPathToT(i)) {
                        if (Integer.parseInt(p1.getText()) != i && i == Integer.parseInt(p2.getText())) {
                            graphGroup.setVisible(false);
                            result.setVisible(true);
                            Text text_1 = new Text("\n"+p1.getText() + " to " + i + " " + sp.distToT(i) + "\n\n");
                            text_1.setFont(Font.font("Verdana", 10));
                            result.getChildren().add(text_1);
                            for (Edge_Projeto e : sp.pathToT(i)) {
                                Text text_2 = new Text(e.v + "-" + e.w + " dist:" + e.weight + " tempo:" + e.getTempo() + "\n");
                                text_2.setFont(Font.font("Verdana", 10));
                                result.getChildren().add(text_2);
                            }
                        }
                    }
                }
            }
        }
        p1.setText("");
        p2.setText("");
    }

    /**
     * handler que imprime o caminho mais curto do subgrafo evitando um set de pontos
     * @param t array de vértices pertencentes ao subgrafo
     */
    public void SubCaminhoMaisCurtoSet(ArrayList<T> t) {
        String[] s = set.getText().split(SET_DELIMITER);
        int[] n= new int[s.length];
        for (int i = 0; i < s.length; i++)
            n[i] = Integer.parseInt(s[i]);

        DijkstraUndirectedSP_Projeto sp = new DijkstraUndirectedSP_Projeto(MainGenericPointRenderer.u.g, Integer.parseInt(p1.getText()),n);
        for (int i = 0; i < MainGenericPointRenderer.u.g.V(); i++) {
            Vertice v = MainGenericPointRenderer.u.st.get(i);
            for (T teste : t) {
                if (teste == v) {
                    if (sp.hasPathToW(i)) {
                        if (Integer.parseInt(p1.getText()) != i && i == Integer.parseInt(p2.getText())) {
                            graphGroup.setVisible(false);
                            result.setVisible(true);
                            Text text_1 = new Text("\n"+p1.getText() + " to " + i + " " + sp.distToW(i) + "\n\n");
                            text_1.setFont(Font.font("Verdana", 10));
                            result.getChildren().add(text_1);
                            for (Edge_Projeto e : sp.pathToW(i)) {
                                Text text_2 = new Text(e.v + "-" + e.w + " dist:" + e.weight + " tempo:" + e.getTempo() + "\n");
                                text_2.setFont(Font.font("Verdana", 10));
                                result.getChildren().add(text_2);
                            }
                        }
                    }
                }
            }
        }
        p1.setText("");
        p2.setText("");
    }

    /**
     * função que adiciona as ligações entre os círculos que representam os vértices
     */
    private void addEdges() {
        for (int j = 0; j < sg.graph().V(); j++) {
            // Obter a StackPane na posição correspondente ao vértice j
            StackPane spv = (StackPane) graphGroup.getChildren().get(j);
            // Obter o círculo da StackPane
            Circle cv = (Circle) spv.getChildren().get(0);
            // Percorrer cada ligação
            for (int i : sg.graph().adj(j)) {
                // Obter a StackPane na posição correspondente ao vértice i
                StackPane spw = (StackPane) graphGroup.getChildren().get(i);
                // Obter o círculo da StackPane
                Circle cw = (Circle) spw.getChildren().get(0);
                // Desenhar uma linha entre os círculos correspondentes aos vértices da ligação
                Line line = new Line(cv.getCenterX(), cv.getCenterY(), cw.getCenterX(), cw.getCenterY());
                graphGroup.getChildren().add(line);
            }
        }
    }

    /**
     * handler que chama a função para guardar o grafo para um txt
     * @param actionEvent
     */
    public void handleSaveFileAction(ActionEvent actionEvent) {
        saveInterfaceInfoToFile();
    }

    /**
     * função que guarda o grafo num txt
     */
    private void saveInterfaceInfoToFile (){
        PrintWriter pw =null;
        try {
            FileWriter fw = new FileWriter("src//data//interfaceInfo.txt");
            pw = new PrintWriter(fw);
        } catch (IOException e) {
            //e.printStackTrace();
            Logger.getLogger(GenericPointFXMLControler.class.getName()).log(Level.SEVERE, "Error Opening File", e);
        }
        if (pw != null) {
            pw.write("Graph info:" + "\n");
            for (int i = 0; i< vertices.size();  i++) {
                Vertice v  = vertices.get(i);
                pw.write("name: " + v.getName() + " x: "+ v.getX()+ " y: "+ v.getY()+ " z: "+ v.getZ()+ " type: "+ v.getType()+ " description: "+ v.getDescription()+"\n");
            }
            pw.close();
        }
    }

    /**
     * handler que chama a função para guardar as pesquisas relativas ao grafo num txt
     * @param actionEvent
     */
    public void handleSearchFileAction(ActionEvent actionEvent) {
        saveSearchToFile();
    }

    /**
     * função que guarda as pesquisas relativas ao grafo num txt
     */
    private void saveSearchToFile (){
        PrintWriter pw = null ;
        try {
            FileWriter fw = new FileWriter("src//data//SearchInfo.txt");
            pw = new PrintWriter(fw);
        } catch (IOException e) {
            //e.printStackTrace();
            Logger.getLogger(GenericPointFXMLControler.class.getName()).log(Level.SEVERE, "Error Opening File", e);
        }
        if (pw != null) {
            pw.write("Search info:" + "\n");

            StringBuilder sb = new StringBuilder();
            for (Node node : result.getChildren()) {
                if (node instanceof Text) {
                    sb.append(((Text) node).getText());
                }
            }
            String fullText = sb.toString();
            pw.write(fullText);
            pw.close();
        }
    }

    /**
     * handler que chama a função para guardar o grafo num bin
     * @param actionEvent
     */
    public void handleSaveBinFileAction(ActionEvent actionEvent) {
        saveSaveBinToFile();
    }

    /**
     * função que  guarda o grafo num bin
     */
    private void saveSaveBinToFile (){
        File f = new File("src//data//SaveBinInfo.txt");
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            System.out.println(sg.graph().toString());
            oos.writeChars(sg.graph().toString());
        } catch (IOException e) {
            System.out.println("saveToBinFile()\n\tIO Exception");
            e.printStackTrace();
        }
    }
}