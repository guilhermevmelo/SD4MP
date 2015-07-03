package br.ufc.dc.sd4mp.todo;

import java.util.Date;

/**
 * Created by guilherme on 5/11/15.
 */
public class Tarefa {
    private String titulo;
    private String descricao;
    private Date dataCriacao;
    private boolean concluida;

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) {this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Date getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(Date dataCriacao) { this.dataCriacao = dataCriacao; }
    public boolean isConcluida() { return concluida; }
    public void setConcluida(boolean concluida) { this.concluida = concluida; }


}
