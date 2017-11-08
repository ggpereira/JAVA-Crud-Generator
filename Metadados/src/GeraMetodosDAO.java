import java.util.Collection; 
import java.util.ArrayList; 


public class GeraMetodosDAO 
{
	private String metodoInserir; 
	private String metodoDeletar;
	private String metodoBuscar;
	private String metodoAlterar;
        private int contador=1;
	
	public Collection<String> geraResultSet(Tabela t)
	{
		Collection<String> result_set = new ArrayList<String>();
		String op;
		
		for(Coluna column  : t.getColumns())
		{
			op = "rs.get" + column.getTypeName();
			System.out.println(op);
			result_set.add(op);
		}
	
		
		return result_set;
	}
	
	
	public String geraInsert(Tabela t)
	{
		GeraSQL sql = new GeraSQL();
		metodoInserir = "public void inserir(" + t.getTableName() + " t) {\n";
		metodoInserir += "\tConnection conexao = open();\n";
		metodoInserir += "\ttry {\n\t\tPreparedStatement ps = conexao.prepareStatement(\"" + sql.createInsert(t) + "\");\n";
                for(Coluna column : t.getColumns())					
		{			
                    metodoInserir += "\t\tps.set" + column.getTypeName() + "(" + contador + ",t.get" + column.getColumnName().substring(0,1).toUpperCase() + (column.getColumnName()).substring(1) + "());\n";
                    contador++;
                }
                metodoInserir += "\t\tps.execute();\n";
                metodoInserir += "\t\tps.close();\n";
                metodoInserir += "\t\tconexao.close();\n";
                metodoInserir += "\t} catch (SQLException e) {\n";
                metodoInserir += "\t\te.printStackTrace();\n\t}\n}";	
		return metodoInserir;
	}
        
        public String geraUpdate(Tabela t)
        {
                GeraSQL sql = new GeraSQL();
                metodoAlterar = "public void alterar(" + t.getTableName() + " t) {\n";
		metodoAlterar += "\tConnection conexao = open();\n";
		metodoAlterar += "\ttry {\n\t\tPreparedStatement ps = conexao.prepareStatement(\"" + sql.createUpdate(t) + "\");\n";
                for(Coluna column : t.getColumns())					
		{			
                    metodoAlterar += "\t\tps.set" + column.getTypeName() + "(" + contador + ",t.get" + column.getColumnName().substring(0,1).toUpperCase() + (column.getColumnName()).substring(1) + "());\n";
                    contador++;
                }
                metodoAlterar += "\t\tps.execute();\n";
                metodoAlterar += "\t\tps.close();\n";
                metodoAlterar += "\t\tconexao.close();\n";
                metodoAlterar += "\t} catch (SQLException e) {\n";
                metodoAlterar += "\t\te.printStackTrace();\n\t}\n}";	
		return metodoAlterar;
        }
        
        public String geraSearch(Tabela t)
        {
                GeraSQL sql = new GeraSQL();
                metodoBuscar = "public void buscar(" + t.getTableName() + " t) {\n";
		metodoBuscar += "\tConnection conexao = open();\n";
                metodoBuscar += "\tCollection<" + t.getTableName() + "> " + t.getTableName() + " = new ArrayList<" + t.getTableName() + ">();\n";
		metodoBuscar += "\ttry {\n\t\tStatement s = conexao.createStatement();\n";
                metodoBuscar += "\t\tResultSet rs = s.executeQuery(\"" + sql.createSelect(t) + "\");\n";
                metodoBuscar += "\t\twhile(rs.next()) {\n\t\t\t" + t.getTableName() + " temp = new " + t.getTableName() + "();\n";
                for(Coluna column : t.getColumns())					
		{			
                    metodoBuscar += "\t\t\ttemp.set" + column.getColumnName().substring(0,1).toUpperCase() + (column.getColumnName()).substring(1) + "(rs.get" + column.getTypeName() + "(\"" + column.getColumnName() + "\");\n";
                    contador++;
                }
                metodoBuscar += "\t\t\t" + t.getTableName() + ".add(temp);\n\t\t}\n";
                metodoBuscar += "\t\trs.close();\n";
                metodoBuscar += "\t\tconexao.close();\n";
                metodoBuscar += "\t} catch (SQLException e) {\n";
                metodoBuscar += "\t\te.printStackTrace();\n\t}\n";	
                metodoBuscar += "\treturn " + t.getTableName() + ";\n}";
		return metodoBuscar;
        }
        
        public String geraDelete(Tabela t)
        {
                GeraSQL sql = new GeraSQL();
                metodoDeletar = "public void deletar(" + t.getTableName() + " t) {\n";
		metodoDeletar += "\tConnection conexao = open();\n";
                metodoDeletar += "\ttry {\n\t\tPreparedStatement ps = conexao.prepareStatement(\"" + sql.createDelete(t) + "\");\n";
                for(Coluna column : t.getColumns())					
		{			
                    if(column.getColumnName().equals(t.getPrimaryKey())){
                        metodoDeletar += "\t\tps.set" + column.getTypeName() + "(" + contador + ",t.get" + column.getColumnName().substring(0,1).toUpperCase() + (column.getColumnName()).substring(1) + "());\n";
                    }
                    contador++;
                }
                metodoDeletar += "\t\tps.execute();\n";
                metodoDeletar += "\t\tps.close();\n";
                metodoDeletar += "\t\tconexao.close();\n";
                metodoDeletar += "\t} catch (SQLException e) {\n";
                metodoDeletar += "\t\te.printStackTrace();\n\t}\n}";	
                return metodoDeletar;
        }
}
