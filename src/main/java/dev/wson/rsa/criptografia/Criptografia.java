package dev.wson.rsa.criptografia;

import java.math.BigInteger;
import javax.swing.JOptionPane;
/**
 * Classe responsável pela entrada dos dados e pelos processos de criptografia.
 * O algoritmo vai cifrar e decifrar números com as chaves geradas.
 * @author Wanderson
 */
public class Criptografia extends Calculo {
    private BigInteger P, Q, N, Z, D, E, cifra = BigInteger.ZERO, entrada = BigInteger.ZERO, saida = BigInteger.ZERO;
    /**
     * Recebe os números informados pelo usuário (P e Q), verifica se são primos e invoca o método cifrar.
     */
    public void iniciar() {
        int flag = 0;
        while (flag == 0) {
            P = BigInteger.valueOf(Integer.parseInt(JOptionPane.showInputDialog("Informe o primeiro numero primo;"))); //Entrada e conversão do número digitado
            if (numeroPrimo(P) == BigInteger.ONE) { //Verificação se o número é primo
                flag = 1;
            } else {
                JOptionPane.showMessageDialog(null, "O numero informado não é primo");
            }
        }
        flag = 0;
        while (flag == 0) {
            Q = BigInteger.valueOf(Integer.parseInt(JOptionPane.showInputDialog("Informe o segundo numero primo;"))); //Entrada e conversão do número digitado
            if (numeroPrimo(Q) == BigInteger.ONE) { //Verificação se o número é primo
                flag = 1;
            } else {
                JOptionPane.showMessageDialog(null, "O numero informado não é primo");
            }
        }
        cifrar(P, Q); //Chamada ao método que cifra a informação
    }
    /**
     * Cifra o número informado pelo usuário e apresenta os detalhes do processo na tela.
     * @param P número primo.
     * @param Q Número primo.
     */
    public void cifrar(BigInteger P, BigInteger Q) {
        N = P.multiply(Q); //N = P*Q
        Z = (P.subtract(BigInteger.ONE)).multiply(Q.subtract(BigInteger.ONE)); //Z = (P-1)*(Q-1)
        D = getNovoPrimo(Z); //Definição do valor de D
        E = getE(D, Z); //Definição do valor de E
        entrada = BigInteger.valueOf(Integer.parseInt(JOptionPane.showInputDialog("Informe o numero a ser criptografado:"))); //Entrada e conversão do número a ser cifrado
        BigInteger potencia = entrada.pow(E.intValue()); //Entrada^E
        cifra = potencia.mod(N); //(Entrada^E) mod N
        JOptionPane.showMessageDialog(null, "P: " + P + "\nQ: " + Q + "\nN: " + N + "\nZ: " + Z + "\nD: " + D
                + "\nE: " + E + "\nChave pública: " + E + " e " + N + "\nInformação a ser criptografada: " + entrada
                + "\nInformação criptografada: " + cifra, "Criptografia RSA", 1);
        decifrar(cifra, D, N); //Chamada ao método que decifra o número solicitado
    }
    /**
     * Decifra o número informado pelo usuário e apresenta o resultado na tela.
     * @param cifra valor resultante do processo de criptografia.
     * @param D     número primo definido em relação a Z, onde MDC(D,Z) é igual a 1.
     * @param N     valor resultante da multiplicação de P e Q.
     */
    public void decifrar(BigInteger cifra, BigInteger D, BigInteger N) {
        BigInteger potencia = cifra.pow(D.intValue()); //Cifra^D
        saida = potencia.mod(N); //(Cifra^D) mod N
        JOptionPane.showMessageDialog(null, "\nInformação a ser descriptografada: " + cifra + "\nChave privada: " + D + " e " + N + "\nInformação descriptografada: " + saida, "Criptografia RSA", 1);
    }

}