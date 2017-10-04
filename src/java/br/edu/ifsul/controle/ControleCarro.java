package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AcessorioDAO;
import br.edu.ifsul.dao.CarroDAO;
import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.modelo.Carro;
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
@ManagedBean(name = "controleCarro")
@SessionScoped
public class ControleCarro implements Serializable {
    
    private CarroDAO dao;
    private Carro objeto;
    private PessoaDAO daoPessoa;
    private AcessorioDAO daoAcessorio;
    
    public ControleCarro(){
        dao = new CarroDAO();
        daoPessoa = new PessoaDAO();
        daoAcessorio = new AcessorioDAO();
    }
    
    public String listar(){
        return "/privado/carro/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new Carro();
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
    
    public CarroDAO getDao() {
        return dao;
    }

    public void setDao(CarroDAO dao) {
        this.dao = dao;
    }

    public Carro getObjeto() {
        return objeto;
    }

    public void setObjeto(Carro objeto) {
        this.objeto = objeto;
    }

    public PessoaDAO getDaoPessoa() {
        return daoPessoa;
    }

    public void setDaoPessoa(PessoaDAO daoPessoa) {
        this.daoPessoa = daoPessoa;
    }

    public AcessorioDAO getDaoAcessorio() {
        return daoAcessorio;
    }

    public void setDaoAcessorio(AcessorioDAO daoAcessorio) {
        this.daoAcessorio = daoAcessorio;
    }

}
