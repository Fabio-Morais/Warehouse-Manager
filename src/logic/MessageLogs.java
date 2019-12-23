package logic;

import db.DataBase;

/**
 * Historico das açoes dos users
 */
public class MessageLogs {
	
	private static MessageLogs instance=null;
	private DataBase db;
	private Ip ip;
	
	/**
	 * Construtor
	 */
	private MessageLogs() {
		this.db = DataBase.getInstance();
		this.ip = new Ip();
	}
	
	/**
	 * Inicializa a classe
	 * @return MessageLogs - cria o objeto e retorna ou retorna a classe já criada
	 * */
	public static MessageLogs getInstance() {
		if(instance == null)
			instance = new MessageLogs();
		return instance;
	}
	
	/**
	 * Envia o log para base de dados, alguem saiu no sistema
	 * @param username - username que fez a açao
	 * @param admin - true se for admin, false se for user normal
	 * @param nomeArmazem - nomeArmazem que ocorreu a açao
	 * @return boolean  - true se enviou corretamente / false caso contrário
	 * */
	public boolean saiuSistema(String username, boolean admin, String nomeArmazem) {
		return db.addLog(username, admin, "saiu do sistema", "saiu do sistema", ip.getIp(), nomeArmazem);
	}
	
	/**
	 * Envia o log para base de dados, alguem entrou no sistema
	 * @param username - username que fez a açao
	 * @param admin - true se for admin, false se for user normal
	 * @param nomeArmazem - nomeArmazem que ocorreu a açao
	 * @return boolean  - true se enviou corretamente / false caso contrário
	 * */
	public boolean entrouSistema(String username, boolean admin, String nomeArmazem) {
		return db.addLog(username, admin, "entrou no sistema", "entrou no sistema", ip.getIp(), nomeArmazem);
	}
	
	
	/**
	 * Envia o log para base de dados, adicionou user novo
	 * @param username - username que fez a açao
	 * @param admin - true se for admin, false se for user normal
	 * @param nif - nif do user que foi adicionado
	 * @param nome - nome do user que foi adicionado
	 * @param nomeArmazem - nomeArmazem que ocorreu a açao
	 * @return boolean  - true se enviou corretamente / false caso contrário
	 * */
	public boolean adicionaUser(String username, boolean admin,String nif, String nome, String nomeArmazem) {
		return db.addLog(username, admin,"adicionou user \""+username+"\"" ,"adicionou o user \""+ nome+"\" com nif \""+ nif+"\"", ip.getIp(), nomeArmazem);
	}
	
	/**
	 * Envia o log para base de dados, removeu user
	 * @param username - username que fez a açao
	 * @param admin - true se for admin, false se for user normal
	 * @param nome - nome do user que foi removido
	 * @param nomeArmazem - nomeArmazem que ocorreu a açao
	 * @return boolean  - true se enviou corretamente / false caso contrário
	 * */
	public boolean removeUser(String username, boolean admin, String nome, String nomeArmazem) {
		return db.addLog(username, admin, "removeu user \""+nome+"\"","removeu o user \""+ nome+"\"", ip.getIp(), nomeArmazem);
	}
	
	/**
	 * Envia o log para base de dados, editou user
	 * @param username - username que fez a açao
	 * @param admin - true se for admin, false se for user normal
	 * @param user - novo nome de user após a ediçao
	 * @param oldUser - nome de user antigo antes da ediçao
	 * @param nomeArmazem - nomeArmazem que ocorreu a açao
	 * @return boolean  - true se enviou corretamente / false caso contrário
	 * */
	public boolean editaUser(String username, boolean admin,String user, String oldUser, String nomeArmazem) {
		return db.addLog(username, admin,"editou user \""+oldUser+"\"", "editou o user \""+ oldUser+"\" para o user \""+ user+"\"", ip.getIp(), nomeArmazem);
	}
	
	/**
	 * Envia o log para base de dados, adicionou maquina nova
	 * @param username - username que fez a açao
	 * @param admin - true se for admin, false se for user normal
	 * @param nome - nome da maquina que foi adicionado
	 * @param numero - numero da maquina que foi adicionado
	 * @param nomeArmazem - nomeArmazem que ocorreu a açao
	 * @return boolean  - true se enviou corretamente / false caso contrário
	 * */
	public boolean adicionaMaquina(String username, boolean admin, String nome, String numero, String nomeArmazem) {
		return db.addLog(username, admin, "adicionou maquina \""+ nome+ "\"","adicionou a maquina \""+ nome+ "\" com o numero \""+ numero+"\"", ip.getIp(), nomeArmazem);
	}
	
	/**
	 * Envia o log para base de dados, removeu maquina nova
	 * @param username - username que fez a açao
	 * @param admin - true se for admin, false se for user normal
	 * @param nome - nome da maquina que foi removido
	 * @param numero - numero da maquina que foi removido
	 * @param nomeArmazem - nomeArmazem que ocorreu a açao
	 * @return boolean  - true se enviou corretamente / false caso contrário
	 * */
	public boolean removeMaquina(String username, boolean admin, String nome, String numero, String nomeArmazem) {
		return db.addLog(username, admin, "removeu maquina \""+nome+"\"", "removeu a maquina \""+ nome+"\" com o numero \""+ numero+"\"", ip.getIp(), nomeArmazem);
	}
	
	/**
	 * Envia o log para base de dados, editou maquina
	 * @param username - username que fez a açao
	 * @param admin - true se for admin, false se for user normal
	 * @param user - novo nome de user após a ediçao
	 * @param oldUser - nome de user antigo antes da ediçao
	 * @param nomeArmazem - nomeArmazem que ocorreu a açao
	 * @return boolean  - true se enviou corretamente / false caso contrário
	 * */
	public boolean editaMaquina(String username, boolean admin,String nome, String numero, String novoNome, String novoNumero, String nomeArmazem) {
		return db.addLog(username, admin, "editou maquina \""+nome+"\"","editou a maquina \""+ nome+"\" com o numero \""+ numero+"\" para o novo nome \""+ novoNome+"\" com o novo numero \""+novoNumero+"\"", ip.getIp(), nomeArmazem);
	}
	
	/**
	 * Envia o log para base de dados, adicionou funcionario novo
	 * @param username - username que fez a açao
	 * @param admin - true se for admin, false se for user normal
	 * @param nome - nome do funcionario que foi adicionado
	 * @param nif - nif do funcionario que foi adicionado
	 * @param nomeArmazem - nomeArmazem que ocorreu a açao
	 * @return boolean  - true se enviou corretamente / false caso contrário
	 * */
	public boolean adicionaFuncionario(String username, boolean admin, String nome, String nif, String nomeArmazem) {
		return db.addLog(username, admin,"adicionou funcionario \""+nome+"\"", 
				"adicionou o funcionario \""+ nome+ "\" com o nif \""+ nif+"\"", ip.getIp(), nomeArmazem);
	}
	
	/**
	 * Envia o log para base de dados, removeu funcionario
	 * @param username - username que fez a açao
	 * @param admin - true se for admin, false se for user normal
	 * @param nome - nome do funcionario que foi removido
	 * @param nif - nif do funcionario que foi removido
	 * @param nomeArmazem - nomeArmazem que ocorreu a açao
	 * @return boolean  - true se enviou corretamente / false caso contrário
	 * */
	public boolean removeFuncionario(String username, boolean admin, String nome, String nif, String nomeArmazem) {
		return db.addLog(username, admin,"removeu funcionario \""+nome+"\"", "removeu o funcionario \""+ nome+"\" com o nif \""+ nif+"\"", ip.getIp(), nomeArmazem);
	}
	
	/**
	 * Envia o log para base de dados, editou funcionario
	 * @param username - username que fez a açao
	 * @param admin - true se for admin, false se for user normal
	 * @param user - novo nome de user após a ediçao
	 * @param oldUser - nome de user antigo antes da ediçao
	 * @param nomeArmazem - nomeArmazem que ocorreu a açao
	 * @return boolean  - true se enviou corretamente / false caso contrário
	 * */
	public boolean editaFuncionario(String username, boolean admin,String nome, String numero, String novoNome, String novoNumero, String nomeArmazem) {
		return db.addLog(username, admin,"editou funcionario \""+nome+"\"", "editou o funcionario \""+ nome+"\" com o numero \""+ numero+"\" para o novo nome \""+ novoNome+"\" com o novo numero \""+novoNumero+"\"", ip.getIp(), nomeArmazem);
	}
	
	/**
	 * Envia o log para base de dados, adicionou fornecedor novo
	 * @param username - username que fez a açao
	 * @param admin - true se for admin, false se for user normal
	 * @param nome - nome do fornecedor que foi adicionado
	 * @param nomeArmazem - nomeArmazem que ocorreu a açao
	 * @return boolean  - true se enviou corretamente / false caso contrário
	 * */
	public boolean adicionaFornecedor(String username, boolean admin, String nome, String nomeArmazem) {
		return db.addLog(username, admin,"adicionou fornecedor \""+nome+"\"","adicionou o fornecedor \""+ nome+ "\"", ip.getIp(), nomeArmazem);
	}
	
	/**
	 * Envia o log para base de dados, removeu fornecedor 
	 * @param username - username que fez a açao
	 * @param admin - true se for admin, false se for user normal
	 * @param nome - nome do fornecedor que foi removido
	 * @param nomeArmazem - nomeArmazem que ocorreu a açao
	 * @return boolean  - true se enviou corretamente / false caso contrário
	 * */
	public boolean removeFornecedor(String username, boolean admin, String nome , String nomeArmazem) {
		return db.addLog(username, admin,"removeu fornecedor \""+nome+"\"", "removeu o fornecedor \""+ nome+"\"", ip.getIp(), nomeArmazem);
	}
	
	/**
	 * Envia o log para base de dados, editou fornecedor
	 * @param username - username que fez a açao
	 * @param admin - true se for admin, false se for user normal
	 * @param nome - nome do fornecedor antes da ediçao
	 * @param novoNome - novo nome do fornecedor apos a ediçao
	 * @param nomeArmazem - nomeArmazem que ocorreu a açao
	 * @return boolean  - true se enviou corretamente / false caso contrário
	 * */
	public boolean editaFornecedor(String username, boolean admin,String nome, String novoNome , String nomeArmazem) {
		return db.addLog(username, admin,"editou fornecedor \""+nome+"\"", "editou a fornecedor \""+ nome+"\" para o novo nome \""+ novoNome+"\"", ip.getIp(), nomeArmazem);
	}
	
	/**
	 * Envia o log para base de dados, adicionou nova categoria ou sub categoria
	 * @param username - username que fez a açao
	 * @param admin - true se for admin, false se for user normal
	 * @param categoria - categoria a que pertence a sub categoria / ou categoria que pretende adicionar
	 * @param subCategoria - nome da sub categoria que foi adicionada / null caso esteja a adicionar uma categoria
	 * @param nomeArmazem - nomeArmazem que ocorreu a açao
	 * @return boolean  - true se enviou corretamente / false caso contrário
	 * */
	public boolean adicionaCategoria(String username, boolean admin,String categoria, String subCategoria, String nomeArmazem) {
		if(subCategoria != null)
			return db.addLog(username, admin,"adicionou sub categoria \""+subCategoria+"\"","adicionou a sub categoria \""+ subCategoria+ "\" peretencente à categoria \""+categoria+"\"", ip.getIp(), nomeArmazem);
		else
			return db.addLog(username, admin,"adicionou categoria \""+categoria+"\"","adicionou a categoria \""+ categoria+ "\"", ip.getIp(), nomeArmazem);
	}
	
	/**
	 * Envia o log para base de dados, removeu categoria ou sub categoria
	 * @param username - username que fez a açao
	 * @param admin - true se for admin, false se for user normal
	 * @param categoria - categoria a que pertence a sub categoria / ou categoria que pretende remover
	 * @param subCategoria - nome da sub categoria que foi removida / null caso esteja a remover uma categoria
	 * @param nomeArmazem - nomeArmazem que ocorreu a açao
	 * @return boolean  - true se enviou corretamente / false caso contrário
	 * */
	public boolean removeCategoria(String username, boolean admin,String categoria, String subCategoria, String nomeArmazem) {
		if(subCategoria != null)
			return db.addLog(username, admin,"removeu sub categoria \""+subCategoria+"\"","removeu a sub categoria \""+ subCategoria+ "\" peretencente à categoria \""+categoria+"\"", ip.getIp(), nomeArmazem);
		else
			return db.addLog(username, admin,"removeu categoria \""+categoria+"\"","removeu a categoria \""+ categoria+ "\"", ip.getIp(), nomeArmazem);
	}
	
	/**
	 * Envia o log para base de dados, editou base de dados
	 * @param username - username que fez a açao
	 * @param admin - true se for admin, false se for user normal
	 * @param dadosAntigos - user,url e password antes da ediçao
	 * @param dadosNovos - user,url e password  antes da ediçao
	 * @param nomeArmazem - nomeArmazem que ocorreu a açao
	 * @return boolean  - true se enviou corretamente / false caso contrário
	 * */
	public boolean editaBaseDados(String username, boolean admin,String dadosAntigos,String dadosNovos, String nomeArmazem) {
		String[] aux = dadosAntigos.split(";");
		String userAntigo = aux[0];
		String urlAntigo = aux[0];
		String passAntigo = aux[0];

		String[] aux2 = dadosNovos.split(";");
		String user = aux2[0];
		String url = aux2[0];
		String password = aux2[0];

		return db.addLog(username, admin,"editou base de dados", "editou o user \""+ userAntigo+"\", url \""
		+urlAntigo+"\" password \""+passAntigo+"para  o user \""+ user+"\", url \""+url+"\" password \""+password+"\"", ip.getIp(), nomeArmazem);
	}
	
	
	/**
	 * Envia o log para base de dados, reportou avaria na maquina
	 * @param username - username que fez a açao
	 * @param admin - true se for admin, false se for user normal
	 * @param nomeMaquina - nome da maquina
	 * @param id - id da maquina
	 * @param descricao - descricao da avaria
	 * @param nomeArmazem - nomeArmazem que ocorreu a açao
	 * @return boolean  - true se enviou corretamente / false caso contrário
	 * */
	public boolean reportaAvaria(String username, boolean admin, String nomeMaquina,String id,String descricao, String nomeArmazem) {
		return db.addLog(username, admin,"reportou avaria da maquina\""+nomeMaquina+"\"","reportou avaria da maquina \""
	+ nomeMaquina+ "\" com o id \""+id+"\" e com a descricao: \""+descricao+"\"", ip.getIp(), nomeArmazem);
	}
	
	/**
	 * Envia o log para base de dados, corrige avaria na maquina
	 * @param username - username que fez a açao
	 * @param admin - true se for admin, false se for user normal
	 * @param nomeMaquina - nome da maquina
	 * @param id - id da maquina
	 * @param nomeArmazem - nomeArmazem que ocorreu a açao
	 * @return boolean  - true se enviou corretamente / false caso contrário
	 * */
	public boolean corrigeAvaria(String username, boolean admin, String nomeMaquina,String id, String nomeArmazem) {
		return db.addLog(username, admin,"corrigiu avaria da maquina\""+nomeMaquina+"\"","corrigiu avaria da maquina \""
	+ nomeMaquina+ "\" com o id \""+id+"\"", ip.getIp(), nomeArmazem);
	}
	
	/**
	 * Envia o log para base de dados, reportou defeito no produto
	 * @param username - username que fez a açao
	 * @param admin - true se for admin, false se for user normal
	 * @param sku - sku do produto com defeito
	 * @param nomeArmazem - nomeArmazem que ocorreu a açao
	 * @return boolean  - true se enviou corretamente / false caso contrário
	 * */
	public boolean reportaDefeito(String username, boolean admin, String sku, String nomeArmazem) {
		return db.addLog(username, admin,"reportou defeito no produto \""+sku+"\"","reportou defeito no produto \""+sku+"\"", ip.getIp(), nomeArmazem);
	}
	
	/**
	 * Envia o log para base de dados, envia produto
	 * @param username - username que fez a açao
	 * @param admin - true se for admin, false se for user normal
	 * @param sku - sku do produto com defeito
	 * @param nomeArmazem - nomeArmazem que ocorreu a açao
	 * @return boolean  - true se enviou corretamente / false caso contrário
	 * */
	public boolean enviaProduto(String username, boolean admin, String sku, String data, String nomeArmazem) {
		return db.addLog(username, admin,"enviou produto \""+sku+"\"","enviou o produto com a sku \""+sku+"\" e com uma data de \""+data+"\"", ip.getIp(), nomeArmazem);
	}
	
	/**
	 * Envia o log para base de dados, recebeu lote
	 * @param username - username que fez a açao
	 * @param admin - true se for admin, false se for user normal
	 * @param dados - dados da receçao do produto, nome do produto, quantidade, origem, data e numero de lote 
	 * @param nomeArmazem - nomeArmazem que ocorreu a açao
	 * @return boolean  - true se enviou corretamente / false caso contrário
	 * */
	public boolean receberProduto(String username, boolean admin, String dados, String nomeArmazem) {
		String[] aux = dados.split(";");
		if(aux.length != 5)
			return false;
		
		return db.addLog(username, admin,"recebeu lote \""+aux[4]+"\"","recebeu lote \""+aux[4]+"\" de origem \""+aux[2]+"\" na data \""+aux[3]+"\" com \""+aux[1]
				+"\" produtos \""+aux[0]+"\"", ip.getIp(), nomeArmazem);
	}

	/**
	 * Envia o log para base de dados, mudou armazem
	 * @param username - username que fez a açao
	 * @param admin - true se for admin, false se for user normal
	 * @param dados - dados do armazem, nome armazem antigo | localizacao antigo | nome armazem novo | localizacao novo 
	 * @return boolean  - true se enviou corretamente / false caso contrário
	 * */
	public boolean mudarArmazem(String username, boolean admin, String dados) {
		String[] aux = dados.split(";");
		if(aux.length != 4)
			return false;
		
		return db.addLog(username, admin,"mudou armazem para \""+aux[2]+"\"","mudou o armazem com o nome \""+aux[0]+"\" com localizacao \""
		+aux[1]+"\" para o nome \""+aux[2]+"\" para a localizacao \""+aux[3]+"\"", ip.getIp(), aux[2]);
	}
}
