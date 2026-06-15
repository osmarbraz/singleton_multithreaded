
/**
 * Singleton seguro para threads com carregamento lento.
 * 
 * A mesma classe se comporta incorretamente em um ambiente multithread.
 * Vários threads podem chamar o método de criação simultaneamente e
 * obter várias instâncias da classe Singleton.
 * 
 * Para corrigir o problema, você deve sincronizar as threads durante a primeira criação do objeto Singleton.
 */
public final class Singleton {

    // O campo deve ser declarado volátil para que o bloqueio de verificação 
    // dupla funcione corretamente.
    private static volatile Singleton instancia;
    public String valor;

    private Singleton(String valor) {
        this.valor = valor;
    }

    /**
     * Retorna a instancia armazenadada na classe.
     *
     * @param valor
     * @return A instância da classe.
     */
    public static Singleton getInstance(String valor) {
        // A abordagem adotada aqui é chamada de bloqueio com verificação dupla (DCL). Isto
        // existe para evitar a condição de corrida entre vários threads que podem
        // tenta obter a instância singleton ao mesmo tempo, criando
        // instâncias como resultado.
        //
        // Pode parecer que ter a variável `result` aqui é completamente
        // sem sentido. Há, no entanto, uma ressalva muito importante quando
        // implementando o bloqueio verificado duas vezes em Java, que é resolvido por
        // introduzindo esta variável local.
        //
        // Você pode ler mais informações sobre problemas de DCL em Java aqui:
        // https://refactoring.guru/java-dcl-issue
        Singleton result = instancia;
        if (result != null) {
            return result;
        }
        synchronized (Singleton.class) {
            if (instancia == null) {
                instancia = new Singleton(valor);
            }
            //Retorna a instância criada pela primeira vez
            return instancia;
        }
    }
}
