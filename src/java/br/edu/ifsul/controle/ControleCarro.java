package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AcessorioDAO;
import br.edu.ifsul.dao.CarroDAO;
import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.modelo.Acessorio;
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

    private CarroDAO<Carro> dao;
    private Carro objeto;
    private PessoaDAO daoPessoa;
    private AcessorioDAO daoAcessorio;
    private Acessorio acessorio;
    private Boolean novoAcessorio;

    public ControleCarro() {
        dao = new CarroDAO();
        daoPessoa = new PessoaDAO();
        daoAcessorio = new AcessorioDAO();
    }

    public void adicionarAcessorio() {
        if (acessorio == null) {
            return;
        }
        
        if (objeto.getAcessorios().contains(acessorio)) {
            Util.mensagemErro("Acessórios já está na lista de acessórios do carro");
            return;
        }
        
        objeto.getAcessorios().add(acessorio);
        Util.mensagemInformacao("Acessório adicionado");
    }
    
    public void removerAcessorio(int index) {
        objeto.getAcessorios().remove(index);
        Util.mensagemInformacao("Acessório removido");
    }
    
    
    public String listar() {
        return "/privado/carro/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Carro();
    }    
    
    public void salvar() {
        boolean persistiu;
        if (objeto.getId() == null) {
            persistiu = dao.persist(objeto);
        } else {
            persistiu = dao.merge(objeto);
        }
        if (persistiu) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public void editar(Integer id) {
        objeto = dao.localizar(id);
    }

    public void remover(Integer id) {
        objeto = dao.localizar(id);
        if (dao.remover(objeto)) {
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

    public Acessorio getAcessorio() {
        return acessorio;
    }

    public void setAcessorio(Acessorio acessorio) {
        this.acessorio = acessorio;
    }

    public Boolean getNovoAcessorio() {
        return novoAcessorio;
    }

    public void setNovoAcessorio(Boolean novoAcessorio) {
        this.novoAcessorio = novoAcessorio;
    }

}
