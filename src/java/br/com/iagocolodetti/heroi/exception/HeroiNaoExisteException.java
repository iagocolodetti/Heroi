package br.com.iagocolodetti.heroi.exception;

/**
 *
 * @author iagocolodetti
 */
public class HeroiNaoExisteException extends Exception {

    public HeroiNaoExisteException() {
        super("Herói não existe.");
    }

    public HeroiNaoExisteException(String msg) {
        super(msg);
    }
}
