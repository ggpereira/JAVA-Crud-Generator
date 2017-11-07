import java.util.Collection; 
import java.util.ArrayList; 


public class GeraMetodosDAO 
{
	private String metodoInserir; 
	private String metodoDeletar;
	private String metodoBuscar;
	private String metodoAlterar;
	
	
	private String geraPreparedStatement(String type)
	{
		switch(type)
		{
		   case "int":
			   return "ps.setInt";
		   case "float":
			   return "ps.setFloat";
		   case "double": 
		   	   return "ps.setDouble"; 
		   case "String":
			   return "ps.setString"; 
		   case "long":
		   	   return "ps.setLong";
		   case "boolean":
			   return "ps.setBoolean";
		   case "char" :
			   return "ps.setChar"; 
		   default:
			   System.out.println("Tipo não identificado");
		}
		return null;
	}
	
	public String geraInsert(Tabela t)
	{
		int i = 1;
		GeraSQL sqlCode = new GeraSQL();
		metodoInserir = "public void inserir(" + t.getTableName() + " t){\n";
		metodoInserir += "\tConnection conexao = open();\n";
		metodoInserir += "\ttry{\n";
		metodoInserir += "\t\tPreparedStatement ps = conexao.prepareStatement(\""+ sqlCode.createInsert(t) + "\");\n";
		
		for(Coluna columns : t.getColumns())
		{
			metodoInserir += "\t\t" + geraPreparedStatement(columns.getTypeName()) + "(" + i + ", t.get" + columns.getColumnName() + "());\n";
			i += 1;
		}
	
		metodoInserir += "\t\tps.execute();\n";
		metodoInserir += "\t\tps.close();\n";
		metodoInserir += "\t}catch(SQLException e){\n";
		metodoInserir += "\t\te.printStackTrace();\n";
		metodoInserir += "\t}\n";
		metodoInserir += "}";
		
		return metodoInserir;
	}
	
	

}
