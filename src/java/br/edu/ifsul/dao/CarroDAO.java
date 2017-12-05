package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Carro;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
public class CarroDAO<T> extends DAOGenerico<Carro> implements Serializable {

    public CarroDAO() {
        super();
        classePersistente = Carro.class;
        ordem = "placa";
    }
    
    public List<Carro> getListaObjetos() {
        String jpql = "from " + classePersistente.getCanonicalName();
        String where = "";
        
        filtro = filtro.replaceAll("[-;']", "");
        
        if (filtro.length() > 0) {
            if (ordem.equals("id") || ordem.equals("anoFabricacao") || ordem.equals("anoModelo")) {
                try {
                    Integer.parseInt(filtro);
                    where += " where " + ordem + " = '" + filtro + "'";
                } catch (Exception e) {
                }
            } else {
                where += " where upper(" + ordem + ") like '%" + filtro.toUpperCase() + "%' ";                
            }                        
        }
        
        jpql += where;
        jpql += " order by " + ordem;
        
        totalObjetos = em.createQuery(jpql).getResultList().size();
        return em.createQuery(jpql).setFirstResult(posicaoAtual).setMaxResults(maximoObjetos).getResultList();
    }
    
    
}
