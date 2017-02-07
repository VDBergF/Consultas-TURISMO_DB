package Modelos;

/**
 * Created by berg on 06/02/17.
 */
public class Igreja {
    private int codigo;
    private String nome;
    private String dataConstrucao;

    public Igreja(int codigo, String nome, String dataConstrucao) {
        this.codigo = codigo;
        this.nome = nome;
        this.dataConstrucao = dataConstrucao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getDataConstrucao() {
        return dataConstrucao;
    }
}
