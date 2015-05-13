package estudo.com.br.entitys;

import java.io.Serializable;
import java.util.List;

public class Pagina implements Serializable {

	private int page;
	private int per_page;
	private int pages;
	private int total;
	private List<Shhhot> shots;
	
	public List<Shhhot> getShhhot(){
		return shots;
	}
	public int getPage(){
		return page;
	}
	public int perPage(){
		return per_page;
	}
	public int getNumPages(){
		return pages;
	}
	public int totalPlayer(){
		return total;
	}

}
