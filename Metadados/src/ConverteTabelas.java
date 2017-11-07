import java.util.Collection; 

public class ConverteTabelas {
	
	public String convert(String tipoDado)
	{
		switch(tipoDado)
		{
			case "int4":
				return "int"; 
			case "varchar": 
				return "String"; 
			case "real": 
				return "float"; 
			case "double precision": 
				return "double"; 
			case "bigint":
				return "long"; 
			case "character":
				return "char";
			case "boolean" :
				return "boolean";
			default:
				System.out.println("Tipo não identificado!");
		}
		
		return null;
	}
	
	
	public void converteTabelas(Collection<Tabela> tables)
	{
		ConverteTabelas c = new ConverteTabelas();
		for(Tabela current_t : tables)
		{
			for(Coluna column : current_t.getColumns())
			{
				column.setTypeName(c.convert(column.getTypeName()));
			}
		}
	}
}
