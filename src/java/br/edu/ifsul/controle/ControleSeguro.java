package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CarroDAO;
import br.edu.ifsul.dao.CorretorDAO;
import br.edu.ifsul.dao.SeguroDAO;
import br.edu.ifsul.modelo.Carro;
import br.edu.ifsul.modelo.Corretor;
import br.edu.ifsul.modelo.Seguro;
import br.edu.ifsul.modelo.Cobertura;
import br.edu.ifsul.util.Util;
// import br.edu.ifsul.util.UtilRelatorios;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@ManagedBean(name = "controleSeguro")
@ViewScoped
public class ControleSeguro implements Serializable {
    
    private SeguroDAO<Seguro> dao;
    private Seguro objeto;
    private CorretorDAO<Corretor> daoCorretor;
    private CarroDAO<Carro> daoCarro;
    private Cobertura cobertura;
    private Boolean novaCobertura;    
    
    public ControleSeguro(){
        dao = new SeguroDAO<>();
        daoCorretor = new CorretorDAO<>();
        daoCarro = new CarroDAO<>();        
    }
    
    public void novaCobertura() {
        setCobertura(new Cobertura());
        setNovaCobertura((Boolean) true);
    }
    
    public void alterarCobertura(int index) {
        cobertura = objeto.getCoberturas().get(index);
        novaCobertura = false;
    }    
    
    public void salvarCobertura() {
        if (getNovaCobertura()) {
            getObjeto().adicionarCobertura(getCobertura());            
        } else {
            Double vlr = 0.0;
            for (Cobertura co : objeto.getCoberturas()) {
                vlr += co.getValor();                
            }
            objeto.setValorTotal(vlr);
        }
        Util.mensagemInformacao("Cobertura persistida com sucesso!");
    }
    
    public void removerCobertura(int index) {
        getObjeto().removerCobertura(index);
        Util.mensagemInformacao("Cobertura removida com succeso!");
    }
    
    public String listar(){
        return "/privado/seguro/listar?faces-redirect=true";
    }
    
    public void novo(){
        setObjeto(new Seguro());        
    }
    
    public void salvar(){
        boolean persistiu;
        if (getObjeto().getId() == null) {
            persistiu = getDao().persist(getObjeto());
        } else {
            persistiu = getDao().merge(getObjeto());
        }
        if (persistiu){
            Util.mensagemInformacao(getDao().getMensagem());
        } else {
            Util.mensagemErro(getDao().getMensagem());
        }
    }
    
    public void editar(Integer id){
        setObjeto(getDao().localizar(id));        
    }
    
//    public void imprimir(Integer id) {
//        objeto = dao.localizar(id);
//        List<Seguro> lista = new ArrayList<>();
//        lista.add(objeto);
//        HashMap parametros = new HashMap();
//        UtilRelatorios.imprimeRelatorio("relatorioSeguro", parametros, lista);
//    }
    
    public void remover(Integer id){
        setObjeto(getDao().localizar(id));
        if (getDao().remover(getObjeto())){
            Util.mensagemInformacao(getDao().getMensagem());
        } else {
            Util.mensagemErro(getDao().getMensagem());
        }
    }

    public SeguroDAO<Seguro> getDao() {
        return dao;
    }

    public void setDao(SeguroDAO<Seguro> dao) {
        this.dao = dao;
    }

    public Seguro getObjeto() {
        return objeto;
    }

    public void setObjeto(Seguro objeto) {
        this.objeto = objeto;
    }

    public CorretorDAO<Corretor> getDaoCorretor() {
        return daoCorretor;
    }

    public void setDaoCorretor(CorretorDAO<Corretor> daoCorretor) {
        this.daoCorretor = daoCorretor;
    }

    public CarroDAO<Carro> getDaoCarro() {
        return daoCarro;
    }

    public void setDaoCarro(CarroDAO<Carro> daoCarro) {
        this.daoCarro = daoCarro;
    }

    public Cobertura getCobertura() {
        return cobertura;
    }

    public void setCobertura(Cobertura cobertura) {
        this.cobertura = cobertura;
    }

    public Boolean getNovaCobertura() {
        return novaCobertura;
    }

    public void setNovaCobertura(Boolean novaCobertura) {
        this.novaCobertura = novaCobertura;
    }
    
}
