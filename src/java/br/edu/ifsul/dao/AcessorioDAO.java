package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Acessorio;
import java.io.Serializable;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
public class AcessorioDAO<TIPO> extends DAOGenerico<Acessorio>implements Serializable {

        public AcessorioDAO() {
        super();
        classePersistente = Acessorio.class;
        ordem = "descricao";
    }
    
}
