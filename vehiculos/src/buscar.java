import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class buscar {
    public JPanel buscarpanel;
    private JTextField textField1;
    private JButton buscarButton;
    private JButton limpiarButton;
    private JLabel placaLabel;
    private JLabel marcaLabel;
    private JLabel cilindrajeLabel;
    private JLabel tipoCombustibleLabel;
    private JLabel colorLabel;
    private JLabel propietarioLabel;

    private static final String URL = "jdbc:mysql://localhost:3306/vehiculos";
    private static final String USER = "root";
    private static final String PASSWORD = "172843";

    public buscar() {
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String placa = textField1.getText();

                try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                    String sql = "SELECT * FROM vehiculos WHERE Placa = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, placa);
                    ResultSet resultSet = statement.executeQuery();

                    if (resultSet.next()) {
                        String marca = resultSet.getString("Marca");
                        float cilindraje = resultSet.getFloat("Cilindraje");
                        String tipoCombustible = resultSet.getString("Tipo_combustible");
                        String color = resultSet.getString("Color");
                        String propietario = resultSet.getString("Propietario");

                        placaLabel.setText("Placa: " + placa);
                        marcaLabel.setText("Marca: " + marca);
                        cilindrajeLabel.setText("Cilindraje: " + cilindraje);
                        tipoCombustibleLabel.setText("Tipo de Combustible: " + tipoCombustible);
                        colorLabel.setText("Color: " + color);
                        propietarioLabel.setText("Propietario: " + propietario);
                    } else {
                        JOptionPane.showMessageDialog(null, "Vehículo no encontrado");
                        limpiarCampos();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al buscar el vehículo");
                }
            }
        });

        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
    }

    private void limpiarCampos() {
        textField1.setText("");

        placaLabel.setText("Placa: ");
        marcaLabel.setText("Marca: ");
        cilindrajeLabel.setText("Cilindraje: ");
        tipoCombustibleLabel.setText("Tipo de Combustible: ");
        colorLabel.setText("Color: ");
        propietarioLabel.setText("Propietario: ");
    }
}
