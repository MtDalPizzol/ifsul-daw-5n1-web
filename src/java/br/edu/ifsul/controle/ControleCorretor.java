package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CorretorDAO;
// import br.edu.ifsul.dao.EstadoDAO;
import br.edu.ifsul.modelo.Corretor;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@ManagedBean(name = "controleCorretor")
@SessionScoped
public class ControleCorretor implements Serializable {
    
    private CorretorDAO<Corretor> dao;
    private Corretor objeto;
    
    public ControleCorretor(){
        dao = new CorretorDAO();
    }
    
    public String listar(){
        return "/privado/corretor/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Corretor();        
    }
    
    public void salvar(){
        boolean persistiu;
        if (objeto.getId() == null) {
            persistiu = dao.persist(objeto);
        } else {
            persistiu = dao.merge(objeto);
        }
        if (persistiu){
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }
    
    public void editar(Integer id){
        objeto = dao.localizar(id);        
    }
    
    public void remover(Integer id){
        objeto = dao.localizar(id);
        if (dao.remover(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }
    
    public CorretorDAO getDao() {
        return dao;
    }

    public void setDao(CorretorDAO dao) {
        this.dao = dao;
    }

    public Corretor getObjeto() {
        return objeto;
    }

    public void setObjeto(Corretor objeto) {
        this.objeto = objeto;
    }

//    public EstadoDAO getDaoEstado() {
//        return daoEstado;
//    }
//
//    public void setDaoEstado(EstadoDAO daoEstado) {
//        this.daoEstado = daoEstado;
//    }

}
