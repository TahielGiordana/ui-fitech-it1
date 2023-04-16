import views.Home;

public class Application {

    public static void main(String[] args) {
        /*Person person = new Person(new Routine("rutina_a", Arrays.asList("A1","A2")), "persona_1", "persona_1");
        Machine machine = new Machine("A1","maquina_1");
        FrontController frontController = new FrontController();
        boolean validar = frontController.validar(person, machine);
        System.out.println("Validar: " + validar);*/

        Home home = new Home();

        /*SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new Home();
                frame.setSize(400,400);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });*/
    }
}
