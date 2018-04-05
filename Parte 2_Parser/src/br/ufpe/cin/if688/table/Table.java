package br.ufpe.cin.if688.table;


import br.ufpe.cin.if688.parsing.analysis.*;
import br.ufpe.cin.if688.parsing.grammar.*;
import java.util.*;


public final class Table {
	private Table() {    }

	public static Map<LL1Key, List<GeneralSymbol>> createTable(Grammar g) throws NotLL1Exception {
        if (g == null) throw new NullPointerException();

        Map<Nonterminal, Set<GeneralSymbol>> first =
            SetGenerator.getFirst(g);
        Map<Nonterminal, Set<GeneralSymbol>> follow =
            SetGenerator.getFollow(g, first);

        Map<LL1Key, List<GeneralSymbol>> parsingTable = 
            new HashMap<LL1Key, List<GeneralSymbol>>();
      
       // //System.out.println(g);
        //System.out.println(first);
        //System.out.println(follow);
        //parsingTable.put(key, value)
        //nonterminal, generalSymbol
        
        for(Production element : g.getProductions()) {
        	//se no first tem epson
        	//System.out.println(g);
        	if(first.get(element.getNonterminal()).contains(SpecialSymbol.EPSILON)) {
        		//System.out.println("No nao terminal" + element.getNonterminal() + " ha epson no seu first");
        		for(GeneralSymbol x : first.get(element.getNonterminal())) {
        			//se x não for o epson
        			if(!x.equals(SpecialSymbol.EPSILON) && !element.getProduction().contains(SpecialSymbol.EPSILON)) {
        				//System.out.println("Mapeando o nao terminal" + element.getNonterminal() + " e o terminal [" + x + "] para a regra" + element);
            			LL1Key Xx = new LL1Key(element.getNonterminal(), x);
            			parsingTable.put(Xx, element.getProduction());
         			}
       
        			//se x for o epson
        			else if(x.equals(SpecialSymbol.EPSILON) && element.getProduction().contains(SpecialSymbol.EPSILON)) {
        				for(GeneralSymbol sbl : follow.get(element.getNonterminal())) {
        					//System.out.println("Mapeando o nao terminal" + element.getNonterminal() + " e o terminal [" + sbl + "] para a regra" + element);
        					LL1Key Xx = new LL1Key(element.getNonterminal(), sbl);
                			parsingTable.put(Xx, element.getProduction());
        				}
        				
        				
         			}
        		}
        		
        	}
        	
        	//se no first n tem epson
        	if(!first.get(element.getNonterminal()).contains(SpecialSymbol.EPSILON)) {
        		//System.out.println("No n terminal" + element.getNonterminal() + " nao ha epson no seu first");
        		for(GeneralSymbol x : first.get(element.getNonterminal())) {
        			//System.out.println("Estamos mexendo no simbolo " + x + " ");
        			if(isNonTerminal(element.getProduction().get(0))) {
        				//System.out.println("Mapeando o nao terminal" + element.getNonterminal() + " e o terminal [" + x + "] para a regra" + element);
            			LL1Key Xx = new LL1Key(element.getNonterminal(), x);
            			parsingTable.put(Xx, element.getProduction());
        			}
        			else {
        				if(element.getProduction().contains(x)) {
        					//System.out.println("Mapeando o nao terminal" + element.getNonterminal() + " e o terminal [" + x + "] para a regra" + element);
                			LL1Key Xx = new LL1Key(element.getNonterminal(), x);
                			parsingTable.put(Xx, element.getProduction());
        				}
        			}
        		}
        	}
        }
       
        //System.out.println(parsingTable);
        
        /*
         * Implemente aqui o método para retornar a parsing table
         */
        
        return parsingTable;
    }
	public static boolean isNonTerminal(GeneralSymbol x) {
    	if(x.toString().startsWith("<")) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
}
