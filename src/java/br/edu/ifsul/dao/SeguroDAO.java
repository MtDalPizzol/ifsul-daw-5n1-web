/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Corretor;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.modelo.Seguro;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author dalpizzol
 */
public class SeguroDAO<T> extends DAOGenerico<Seguro> implements Serializable {

    private Boolean filtroValor;
    private Double filtroValorMin;
    private Double filtroValorMax;
    private Corretor filtroCorretor;

    public SeguroDAO() {
        super();
        classePersistente = Seguro.class;
        ordem = "id";
        filtroValor = false;
        filtroValorMin = 0.0;
        filtroValorMax = 10000.0;
    }

    @Override
    public List<Seguro> getListaObjetos() {
        String jpql = "from " + classePersistente.getCanonicalName();
        String where = "";

        filtro = filtro.replaceAll("[-;']", "");

        String c = Corretor.class.getCanonicalName();
        String p = Pessoa.class.getCanonicalName();
        
        if (filtro.length() > 0) {
            if (ordem.equals("id")) {
                try {
                    Integer.parseInt(filtro);
                    where += " where " + ordem + " = '" + filtro + "'";
                } catch (Exception e) {
                }
            } else {
                where += " where upper(" + ordem + ") like '" + filtro.toUpperCase() + "%' ";
            }
        }

        if (filtroCorretor != null) {
            if (!(where.length() > 0)) {
                where = " where ";
            } else {
                where += " and ";
            }
            where += " corretor = '" + filtroCorretor.getId() + "'";
        }        
        
        if (filtroValor) {
            if (!(where.length() > 0)) {
                where = " where ";
            } else {
                where += " and ";
            }
            where += " valorTotal >= '" + filtroValorMin + "' and valorTotal <= '" + filtroValorMax + "'";
        }

        jpql += where;
        jpql += " order by " + ordem;

        totalObjetos = em.createQuery(jpql).getResultList().size();
        return em.createQuery(jpql).setFirstResult(posicaoAtual).setMaxResults(maximoObjetos).getResultList();
    }

    public Boolean getFiltroValor() {
        return filtroValor;
    }

    public void setFiltroValor(Boolean filtroValor) {
        this.filtroValor = filtroValor;
    }

    public Double getFiltroValorMin() {
        return filtroValorMin;
    }

    public void setFiltroValorMin(Double filtroValorMin) {
        this.filtroValorMin = filtroValorMin;
    }

    public Double getFiltroValorMax() {
        return filtroValorMax;
    }

    public void setFiltroValorMax(Double filtroValorMax) {
        this.filtroValorMax = filtroValorMax;
    }

    public Corretor getFiltroCorretor() {
        return filtroCorretor;
    }

    public void setFiltroCorretor(Corretor filtroCorretor) {
        this.filtroCorretor = filtroCorretor;
    }

}
