import java.util.Random;


public class GeradorValores {
	/*CLASSE USADA PARA GERAR DADOS ALEATÓRIOS PARA OS TESTES NAS CLASSES DE EXEMPLO*/
	
	
	/*É sorteada uma posição de um vetor de palavras
	 * A posição sorteada é a palavra que será retornada
	 */
	private static String getPalavra()
	{
		Random g = new Random();  /*Gerar números aleatórios*/

		String palavras[] = {"Batman", "Nicolas Cage", "Morgan Freeman", "Iron Maiden", "Pink Floyd", "Homem Aranha", "Zeus", "Megaman", "Megadrive", "Gandalf", "Hobbit", "Led Zeppelin", "War pigs", "Paranoid", "Iron Man"};
		 
		int index = g.nextInt(8);
		
		return palavras[index]; /*Retorna string aleatória*/
	} 
	
	/*Gera e retorna um número aleatório para dados numéricos*/
	private static String getNumero()
	{
		Random g = new Random(); 
		
		return Integer.toString(g.nextInt(300));/*Retorna numero aleatório entre 0 e 300*/
	}
	
	/*Sorteia um valor que será uma posição correspondente a um char na String
	 * O char selecionado é retornado 
	 */
	private static char getChar()
	{
		char letra;
		
		String letras = "abcdefghijklmnopqrstuvxz"; 
		
		Random g = new Random();  /*Gerar números aleatórios*/
		
		letra = letras.charAt(g.nextInt(23));
		
		return letra; /*Retorna char aleatório*/
	}
	
	/*Retorna um valor de acordo com o tipo solicitado*/
	public static String getValor(String tipoDado) 
	{
		switch(tipoDado)
		{
			case "int":
				return getNumero();
			case "String": 
				return "\"" + getPalavra() + "\""; 
			case "float": 
				return getNumero();
			case "double": 
				return getNumero();
			case "long":
				return getNumero();
			case "char":
				return Character.toString(getChar());
			case "boolean" :
				return "true";
			default:
				System.out.println("Tipo não identificado!");
		}
		
		return null;
	}
	
}
