package br.ufpe.cin.if688.parsing.analysis;

import java.util.*;

import br.ufpe.cin.if688.parsing.grammar.*;


public final class SetGenerator {
    
    public static Map<Nonterminal, Set<GeneralSymbol>> getFirst(Grammar g) {
        
    	if (g == null) throw new NullPointerException("g nao pode ser nula.");
        
    	Map<Nonterminal, Set<GeneralSymbol>> first = initializeNonterminalMapping(g);
    	/*
    	 * Implemente aqui o método para retornar o conjunto first
    	 */
    	
    	Set<GeneralSymbol> firsts = new HashSet<GeneralSymbol>();
    	Nonterminal prev = null;
    	Nonterminal current;
    	for(Production element : g.getProductions()) {
    		current = element.getNonterminal();
    		if(current == prev) {
    			firsts.add(element.getProduction().get(0));
    		}
    		else {
    			firsts = new HashSet<GeneralSymbol>();
    			firsts.add(element.getProduction().get(0));
    		}
    		
    		//System.out.println(element.getProduction());
    		//System.out.println("Vai associar " + element.getProduction().get(0) + " com " + element.getNonterminal());
    		first.put(element.getNonterminal(), firsts);
    		prev = current;
    	}
    	//System.out.println(first.toString());
        return first;
    }

    
    public static Map<Nonterminal, Set<GeneralSymbol>> getFollow(Grammar g, Map<Nonterminal, Set<GeneralSymbol>> first) {
        
        if (g == null || first == null)
            throw new NullPointerException();
                
        Map<Nonterminal, Set<GeneralSymbol>> follow = initializeNonterminalMapping(g);
        
        /*
         * implemente aqui o método para retornar o conjunto follow
         */
        
        Set<GeneralSymbol> follows = new HashSet<GeneralSymbol>();
        
        List<Set<GeneralSymbol>> followList = new ArrayList<Set<GeneralSymbol>>();
        //ArrayList<Set<GeneralSymbol>> followu = new ArrayList<HashSet<GeneralSymbol>>();
        boolean startIndicator = true;
        for(Production element : g.getProductions()) {
        	if(startIndicator == true) {
         		follows.add(SpecialSymbol.EOF);
         		startIndicator = false;
        	}
        	
        	System.out.println("Produção de " + element.getNonterminal() + " " + element.getProduction());	
        	
        	for(GeneralSymbol subElement : element.getProduction()) {
        		System.out.println(subElement);
        	}
        }
        
        
        return follow;
    }
    
    //método para inicializar mapeamento nãoterminais -> conjunto de símbolos
    private static Map<Nonterminal, Set<GeneralSymbol>>
    initializeNonterminalMapping(Grammar g) {
    Map<Nonterminal, Set<GeneralSymbol>> result = 
        new HashMap<Nonterminal, Set<GeneralSymbol>>();

    for (Nonterminal nt: g.getNonterminals())
        result.put(nt, new HashSet<GeneralSymbol>());

    return result;
}

} 
