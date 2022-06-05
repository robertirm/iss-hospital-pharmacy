package ui;

import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import persistence.MedicalPersonnelRepository;
import persistence.MedicineRepository;
import persistence.OrderRepository;
import persistence.PharmacistRepository;
import persistence.impl.MedicalPersonnelRepositoryImpl;
import persistence.impl.MedicineRepositoryImpl;
import persistence.impl.OrderRepositoryImpl;
import persistence.impl.PharmacistRepositoryImpl;
import services.Services;


import java.io.IOException;

public class Main extends Application {
    private static SessionFactory sessionFactory;

    static void initialize() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            System.err.println("Exceptie "+e);
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    static void close() {
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/login.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        PharmacistRepository pharmacistRepository = new PharmacistRepositoryImpl(sessionFactory);
        MedicineRepository medicineRepository = new MedicineRepositoryImpl(sessionFactory);
        OrderRepository orderRepository = new OrderRepositoryImpl(sessionFactory);
        MedicalPersonnelRepository medicalPersonnelRepository = new MedicalPersonnelRepositoryImpl(sessionFactory);

        Services services = new Services(medicalPersonnelRepository,orderRepository,pharmacistRepository,medicineRepository);
        LoginController guiController = fxmlLoader.getController();
        guiController.setServices(services);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    public static void main(String[] args) {
        initialize();
        launch();
        close();
    }
}