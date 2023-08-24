package Projeto;

import algs4.Graph;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainGenericPointRenderer extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    public static Universidade u = new Universidade();

    /**
     * @param primaryStage
     * @throws IOException
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("generic_point_renderer.fxml"));

        Main.readProfessorsTxt("src//data//professors.txt",u.professors);
        Main.readStudentsTxt("src//data//students.txt",u.students);
        Main.readRoomsTxt("src//data//rooms.txt",u.rooms);
        Main.readGraph("src//data//graph.txt",u.students,u.professors,u.rooms);

        SymbolGraph_Projeto sg = new SymbolGraph_Projeto("src//data//sg.txt", u.st, ";");
        Graph g = sg.graph();
        loader.setController(new GenericPointFXMLControler<>(sg));

        GenericPointFXMLControler<Vertice> controller = loader.getController();
        for (int v = 0; v < g.V(); v++)
            controller.addVertice(sg.nameOf(v));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Graph");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
