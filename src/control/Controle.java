package control;

import java.util.List;

import dao.SenhaDao;
import model.Senha;

public class  Controle {
	public String gerarSenha(String tipo){
		SenhaDao dao = new SenhaDao();
		Senha senha = dao.getUltima(tipo);
		senha.setNumero(senha.getNumero()+1);
		senha.setStatus("S");
		dao.adiciona(senha);
		return tipo + senha.getNumero().toString();
	}
	public void reiniciarContagem(){
		SenhaDao dao = new SenhaDao();
		List<Senha> senhas = dao.getLista();
		for (Senha senha : senhas){
			senha.setStatus("N");
			dao.atualiza(senha);
		}
	}
	public void chamarSenha(){
		SenhaDao dao = new SenhaDao();
		Senha senha = dao.getChamada();
		senha.setStatus("N");
		dao.atualiza(senha);		
	}
	public String ultimaChamada(){
		SenhaDao dao = new SenhaDao();
		Senha senha = dao.getChamada();
		return senha.getTipo() + senha.getNumero().toString();
	}	
	
}
