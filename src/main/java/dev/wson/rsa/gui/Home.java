package dev.wson.rsa.gui;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import dev.wson.rsa.criptografia.Criptografia;
/**
 * Classe que renderiza a janela inicial da aplicação.
 * Criada com Java Swing.
 * @author Wanderson
 */
public class Home extends JFrame {
    //Classe que cria a janela do aplicativo com título, ícone e botões
    private final JButton botaoCifrar, botaoDecifrar;
    private ImageIcon iconeJanela;
    private JPanel painel = new JPanel();
    private JLabel titulo = new JLabel("Criptografia RSA", SwingConstants.CENTER);

    /**
     * É o construtor que inicializa os componentes e monta a tela
     */
    public Home() {
        setTitle("Segurança e Criptografia");
        iconeJanela = new ImageIcon("/src/main/resources/icone.png");
        setIconImage(iconeJanela.getImage());
        setSize(300, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Definição de um LookAndFeel (tema) para a janela
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
        }
        //Configuração dos botões com a ação a ser executada após o click
        botaoCifrar = new JButton("Criptografar");
        botaoCifrar.setFont(new Font("Calibri", Font.PLAIN, 14));
        botaoCifrar.addActionListener(event -> new Criptografia().iniciar());
        //O botão decifrar não foi utilizado
        botaoDecifrar = new JButton("Descriptografar");
        botaoDecifrar.setFont(new Font("Calibri", Font.PLAIN, 14));
        botaoDecifrar.addActionListener(event -> {});
        
        titulo.setFont(new Font("Verdana", Font.BOLD, 25));
        add(painel);
        painel.add(titulo);
        painel.add(botaoCifrar);
        setVisible(true);
    }
}