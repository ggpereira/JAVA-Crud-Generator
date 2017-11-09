import java.util.Collection;
import java.util.ArrayList;

public class GeraClasse {
	private String classe;
	
	
	private String geraAtributo(String atrib, String tipo)
	{
		String atributo = "private " + tipo + " " + atrib + ";";
		
		return atributo;
	}
	
	private String geraMetodoSet(String atrib, String tipo)
	{
		String metodo =  "\tpublic void " + "set" + atrib.substring(0, 1).toUpperCase() + atrib.substring(1) + "(" + tipo + " " + atrib + ")" +"{\n";
		metodo += "\t\tthis." + atrib + " = " +  atrib + ";\n";
		metodo += "\t}";
		
		return metodo;
	}
	
	private String geraMetodoGet(String atrib, String tipo)
	{
		String metodo = "\tpublic " + tipo + " " + "get" + atrib.substring(0, 1).toUpperCase() + atrib.substring(1) + "(){\n";
		metodo += "\t\treturn" + " " + atrib + ";\n"; 
		metodo += "\t}";
		
		return metodo; 
	}
	
	public String geraClasse(Tabela t)
	{
		classe = "public class " + t.getTableName().substring(0, 1).toUpperCase() + t.getTableName().substring(1) + " {\n";
		//Atributos da classe
		for(Coluna c : t.getColumns())
		{
			classe += "\t" + geraAtributo(c.getColumnName(), c.getTypeName()) + "\n";
		}
		classe += "\n";
		
		//Metodos get e set
		for(Coluna c : t.getColumns())
		{
			classe += geraMetodoSet(c.getColumnName(), c.getTypeName()) + "\n\n";
			classe += geraMetodoGet(c.getColumnName(), c.getTypeName()) + "\n\n";
		}
		
		classe += "}";
		
		return classe;
	}
	
}
