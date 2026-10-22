package school.sptech;

public class ConsumoGenero {

    private Integer idConsumoMusical;
    private Integer qtdStreams; // coluna qtd_streams
    private Integer ranking;
    private Integer idGenero;
    private Integer idRegiao;

    public Integer getIdConsumoMusical() {
        return idConsumoMusical;
    }

    public void setIdConsumoMusical(Integer idConsumoMusical) {
        this.idConsumoMusical = idConsumoMusical;
    }

    public Integer getQtdStreams() {
        return qtdStreams;
    }

    public void setQtdStreams(Integer qtdStreams) {
        this.qtdStreams = qtdStreams;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Integer getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(Integer idGenero) {
        this.idGenero = idGenero;
    }

    public Integer getIdRegiao() {
        return idRegiao;
    }

    public void setIdRegiao(Integer idRegiao) {
        this.idRegiao = idRegiao;
    }
}