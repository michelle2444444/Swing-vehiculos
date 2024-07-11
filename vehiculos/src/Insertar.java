import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Insertar {
    public JPanel Insertarpanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JButton insertarButton;
    private JButton limpiarButton;

    private static final String URL = "jdbc:mysql://localhost:3306/vehiculos";
    private static final String USER = "root";
    private static final String PASSWORD = "172843";

    public Insertar() {
        insertarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String placa = textField1.getText();
                String marca = textField2.getText();
                String cilindraje = textField3.getText();
                String tipoCombustible = textField4.getText();
                String color = textField5.getText();
                String propietario = textField6.getText();

                try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                    String sql = "INSERT INTO vehiculos (Placa, Marca, Cilindraje, Tipo_combustible, Color, Propietario) VALUES (?, ?, ?, ?, ?, ?)";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, placa);
                    statement.setString(2, marca);
                    statement.setFloat(3, Float.parseFloat(cilindraje));
                    statement.setString(4, tipoCombustible);
                    statement.setString(5, color);
                    statement.setString(6, propietario);
                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Vehículo insertado correctamente");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al insertar el vehículo");
                }
            }
        });

        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
                textField5.setText("");
                textField6.setText("");
            }
        });

    }
}