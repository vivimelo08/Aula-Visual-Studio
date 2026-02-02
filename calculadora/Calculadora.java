package calculadora;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculadora extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private JTextField display;
    private double resultado;
    private String operador;
    private boolean novoNumero;

    public Calculadora() {
        super("Calculadora");

        // Display
        display = new JTextField("0");
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Painel de botões
        JPanel painelBotoes = new JPanel(new GridLayout(5, 4, 5, 5));

        String[] botoes = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C"
        };

        for (String texto : botoes) {
            JButton botao = new JButton(texto);
            botao.setFont(new Font("Arial", Font.BOLD, 20));
            botao.addActionListener(this);
            painelBotoes.add(botao);
        }

        // Layout
        setLayout(new BorderLayout(5, 5));
        add(display, BorderLayout.NORTH);
        add(painelBotoes, BorderLayout.CENTER);

        setSize(300, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        // Estado inicial
        resultado = 0;
        operador = "";
        novoNumero = true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        // Números
        if ("0123456789".contains(comando)) {
            if (novoNumero) {
                display.setText(comando);
                novoNumero = false;
            } else {
                display.setText(display.getText() + comando);
            }
            return;
        }

        // Ponto decimal
        if (comando.equals(".")) {
            if (novoNumero) {
                display.setText("0.");
                novoNumero = false;
            } else if (!display.getText().contains(".")) {
                display.setText(display.getText() + ".");
            }
            return;
        }

        // Limpar
        if (comando.equals("C")) {
            display.setText("0");
            resultado = 0;
            operador = "";
            novoNumero = true;
            return;
        }

        /*Operações */ 
        if (comando.equals("=")) {
            calcular(Double.parseDouble(display.getText()));
            operador = "";
            novoNumero = true;
        } else {
            calcular(Double.parseDouble(display.getText()));
            operador = comando;
            novoNumero = true;
        }
    }

    private void calcular(double numero) {
        switch (operador) {
            case "":
                resultado = numero;
                break;
            case "+":
                resultado += numero;
                break;
            case "-":
                resultado -= numero;
                break;
            case "*":
                resultado *= numero;
                break;
            case "/":
                if (numero == 0) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Não é possível dividir por zero",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                    );
                    display.setText("0");
                    resultado = 0;
                    operador = "";
                    novoNumero = true;
                    return;
                }
                resultado /= numero;
                break;
        }

        display.setText(
            resultado == (long) resultado
                ? String.valueOf((long) resultado)
                : String.valueOf(resultado)
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Calculadora::new);
    }
}
