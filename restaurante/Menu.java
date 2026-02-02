package restaurante;

import javax.swing.*;
import java.awt.*;

public class Menu {

    public static void main(String[] args) {
        // Frame principal
        JFrame frame = new JFrame("Menu do Restaurante");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Comidas
        Comida[] comidas = {
            new Comida("Pizza", "imagens/pizza.png", "Deliciosa pizza com queijo, tomate e orégano."),
            new Comida("Hambúrguer", "imagens/hamburguer.png", "Hambúrguer suculento com alface, tomate e queijo."),
            new Comida("Sorvete", "imagens/sorvete.png", "Sorvete cremoso de baunilha ou chocolate."),
            new Comida("Sushi", "imagens/sushi.png", "Sushi fresco com arroz e peixe de alta qualidade.")
        };

        // Lista de comidas
        JList<Comida> list = new JList<>(comidas);
        list.setCellRenderer((l, c, i, sel, focus) -> {
            JLabel lbl = new JLabel(c.getNome());
            lbl.setIcon(new ImageIcon(c.getImagem().getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH)));
            lbl.setOpaque(true);
            lbl.setBackground(sel ? l.getSelectionBackground() : l.getBackground());
            lbl.setForeground(sel ? l.getSelectionForeground() : l.getForeground());
            return lbl;
        });

        // Preview
        JLabel lblImagem = new JLabel();
        JLabel lblDescricao = new JLabel();
        lblDescricao.setVerticalAlignment(SwingConstants.TOP);
        JPanel preview = new JPanel(new BorderLayout());
        preview.add(lblImagem, BorderLayout.CENTER);
        preview.add(lblDescricao, BorderLayout.SOUTH);

        // Atualiza preview ao selecionar
        list.addListSelectionListener(e -> {
            Comida c = list.getSelectedValue();
            if (c != null) {
                lblImagem.setIcon(new ImageIcon(c.getImagem().getImage().getScaledInstance(200,200,Image.SCALE_SMOOTH)));
                lblDescricao.setText("<html><p style='width:200px;'>"+c.getDescricao()+"</p></html>");
            }
        });

        // Dividir tela
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(list), preview);
        split.setDividerLocation(200);
        frame.add(split);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

// Classe comida simples
class Comida {
    private String nome;
    private ImageIcon imagem;
    private String descricao;

    public Comida(String nome, String caminhoImagem, String descricao) {
        this.nome = nome;
        this.imagem = new ImageIcon(caminhoImagem);
        this.descricao = descricao;
    }

    public String getNome() { return nome; }
    public ImageIcon getImagem() { return imagem; }
    public String getDescricao() { return descricao; }

    @Override
    public String toString() { return nome; }
}
