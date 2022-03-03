package dev.wson.rsa.criptografia;

import java.math.BigInteger;
/**
 * Classe responsável pela verificação dos números escolhidos e por outros cálculos relacionados.
 * @author Wanderson
 */
public class Calculo {
    /**
     * Retorna o máximo divisor comum de dois números recebidos por parâmetro. Os argumentos n1 e n2 são do tipo <a href="#{@link}">{@link BigInteger}</a>.
     * @param n1   um número inteiro.
     * @param n2   um número inteiro.
     * @return     o máximo divisor comum dos dois números inteiros.
     */
    public BigInteger getMDC(BigInteger n1, BigInteger n2) {
        BigInteger MDC = n1.gcd(n2);
        // gdc() é o método da classe BigInteger que realiza essa operação
        return MDC;
    }
    /**
     * Verifica se um número é primo. O argumento numero é do tipo <a href="#{@link}">{@link BigInteger}</a>.
     * @param numero  um número inteiro.
     * @return        1 se o número é primo, 0 em caso contrário.
     */
    public BigInteger numeroPrimo(BigInteger numero) {
        BigInteger i;
        BigInteger contador = BigInteger.ZERO;
        //Verificação do número de divisores do número, considerando as divisões exatas
        for (i = BigInteger.ONE; i.compareTo(numero) <= 0; i = i.add(BigInteger.ONE)) {
            BigInteger modulo = numero.mod(i);
            if (modulo == BigInteger.ZERO) {
                contador = contador.add(BigInteger.ONE);
            }
        }
        if (contador.equals(BigInteger.valueOf(2))) { //Um número primo possui apenas dois divisores, o 1 e ele mesmo
            return BigInteger.ONE;
        } else {
            return BigInteger.ZERO; //Um número com mais de dois divisores não é primo, é composto
        }
    }
    /**
     * Define um novo número primo (D) em relação a Z, onde o MDC(D,Z) é igual a 1.
     * @param Z  valor resultante da multiplicação de (P-1) por (Q-1).
     * @return   o número primo encontrado.
     */
    public BigInteger getNovoPrimo(BigInteger Z) {
        BigInteger i, MDC;
        for (i = new BigInteger("2"); i.compareTo(Z) < 0; i = i.add(BigInteger.ONE)) {
            if (numeroPrimo(i) == BigInteger.ONE) { //Verificação se o número é primo
                MDC = i.gcd(Z); //Máximo divisor comum do número e Z
                if (MDC.equals(BigInteger.ONE)) { //MDC deve ser igual a 1
                    return i;
                }
            }
        }
        return i;
    } 
    /**
     * Define o valor para E, desde que (E*D) mod Z seja igual a 1.
     * @param D  número primo definido em relação a Z, onde MDC(D,Z) é igual a 1.
     * @param Z  valor resultante da multiplicação de (P-1) por (Q-1).
     * @return   o valor de E.
     */
    public BigInteger getE(BigInteger D, BigInteger Z) {
        BigInteger E = BigInteger.ZERO, i = BigInteger.ONE;
        int flag = 0;
        while (flag == 0) {
            BigInteger modulo = (i.multiply(D)).mod(Z); // Fórmula: (E*D) mod Z
            if (modulo.equals(BigInteger.ONE)) { //O resultado do módulo deve ser igual a 1
                E = i;
                flag = 1;
            }
            i = i.add(BigInteger.ONE);
        }
        return E;
    }
}