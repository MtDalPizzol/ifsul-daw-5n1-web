package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AcessorioDAO;
// import br.edu.ifsul.dao.EstadoDAO;
import br.edu.ifsul.modelo.Acessorio;
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
@ManagedBean(name = "controleAcessorio")
@SessionScoped
public class ControleAcessorio implements Serializable {
    
    private AcessorioDAO dao;
    private Acessorio objeto;
    // private EstadoDAO daoEstado;
    
    public ControleAcessorio(){
        dao = new AcessorioDAO();
        // daoEstado = new EstadoDAO();
    }
    
    public String listar(){
        return "/privado/acessorio/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new Acessorio();
        return "formulario?faces-redirect=true";
    }
    
    public String salvar(){
        if (dao.salvar(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
            return "listar?faces-redirect=true";
        } else {
            Util.mensagemErro(dao.getMensagem());
            return "formulario?faces-redirect=true";
        }
    }
    
    public String cancelar(){
        return "listar?faces-redirect=true";
    }
    
    public String editar(Integer id){
        objeto = dao.localizar(id);
        return "formulario?faces-redirect=true";
    }
    
    public void remover(Integer id){
        objeto = dao.localizar(id);
        if (dao.remover(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }
    
    public AcessorioDAO getDao() {
        return dao;
    }

    public void setDao(AcessorioDAO dao) {
        this.dao = dao;
    }

    public Acessorio getObjeto() {
        return objeto;
    }

    public void setObjeto(Acessorio objeto) {
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
