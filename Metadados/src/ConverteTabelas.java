import java.util.Collection; 

public class ConverteTabelas {
	
	public String convert(String tipoDado)
	{
		switch(tipoDado)
		{
			case "int4":
				return "Int"; 
			case "varchar": 
				return "String"; 
			case "real": 
				return "Float"; 
			case "double precision": 
				return "Double"; 
			case "bigint":
				return "Long"; 
			case "character":
				return "Char";
			case "boolean" :
				return "Boolean";
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
