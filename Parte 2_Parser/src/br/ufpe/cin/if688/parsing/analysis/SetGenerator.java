package br.ufpe.cin.if688.parsing.analysis;

import java.util.*;

import br.ufpe.cin.if688.parsing.grammar.*;


public final class SetGenerator {
    
    public static Map<Nonterminal, Set<GeneralSymbol>> getFirst(Grammar g) {
        
    	if (g == null) throw new NullPointerException("g nao pode ser nula.");
        
    	Map<Nonterminal, Set<GeneralSymbol>> first = initializeNonterminalMapping(g);
    	/*
    	 * Implemente aqui o mÃ©todo para retornar o conjunto first
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
         * implemente aqui o mÃ©todo para retornar o conjunto follow
         */
        
        Set<GeneralSymbol> follows = new HashSet<GeneralSymbol>();
        
        ArrayList<Set<GeneralSymbol>> followList = new ArrayList<Set<GeneralSymbol>>();
    
        boolean startIndicator = true;
        for(Production element : g.getProductions()) {
        	//se for o primeiro, adiciona $ no follow
        	if(startIndicator == true) {
        		follows.add(SpecialSymbol.EOF);
        		follow.put(element.getNonterminal(), follows);
        		startIndicator = false;
        		follows = new HashSet<GeneralSymbol>();
        	}
        	
        	//System.out.println(element);
        	int i = 0;
        	for(GeneralSymbol subElement : element.getProduction()) {
        		//System.out.println(subElement);
        		if(isNonTerminal(subElement)) {
        			//se for o ultimo
        			if(element.getProduction().get(element.getProduction().size() -1 ).equals(subElement)) {
        				//System.out.println("Esse não terminal (" + subElement  + ") é o último elemento da produção, logo, no seu follow há o follow de " + element.getNonterminal());
        				follows.addAll(follow.get(element.getNonterminal()));
        				for(Nonterminal nElement : g.getNonterminals()) {
        					if(nElement.toString().equals(subElement.toString())) {
        						follows.addAll(follow.get(nElement));
        						follow.put(nElement , follows);
        						//System.out.println("Foi adicionado ao follow de " + nElement + " os seguintes simbolos" + follows);
        					}
        				}
        				follows = new HashSet<GeneralSymbol>();
        			}	
        			else {
        				//se tiver nãoterminal dps
        				if(isNonTerminal(element.getProduction().get(i+1))){
        					//System.out.println(" Esse não terminal (" + subElement + ") não é o último, e depois dele vem o não terminal " + element.getProduction().get(i+1));
        					//se o nonTerminal tem um epson no first
        					if(first.get(element.getProduction().get(i+1)).contains(SpecialSymbol.EPSILON)) {
        						follows.remove(SpecialSymbol.EPSILON);
        						follows.addAll(follow.get(subElement));
        						for(Nonterminal nElement : g.getNonterminals()) {
        							if(nElement.toString().equals(subElement.toString())) {
        								follow.put(nElement, follows);
        								//System.out.println("Foi adicionado ao follow de " + nElement + " os seguintes simbolos" + follows);
        							}
        						}
        					}		
        					//se o non terminal não tem um epson no first
        					if(!first.get(element.getProduction().get(i+1)).contains(SpecialSymbol.EPSILON)) {
        						follows.addAll(first.get(element.getProduction().get(i+1)));
        						follows.addAll(follow.get(subElement));
        						for(Nonterminal nElement : g.getNonterminals()) {
        							if(nElement.toString().equals(subElement.toString())) {
        								follow.put(nElement, follows);
        								//System.out.println("Foi adicionado ao follow de " + nElement + " os seguintes simbolos" + follows);
        							}
        						}
        					}
        				}
        				//se tiver terminal dps
        				if(!isNonTerminal(element.getProduction().get(i+1))) {
        					//System.out.println(" Esse não terminal (" + subElement + ") não é o último, e depois dele vem o terminal " + element.getProduction().get(i+1));
        					follows.add(element.getProduction().get(i+1));
        					for(Nonterminal nElement : g.getNonterminals()) {
            					if(nElement.toString().equals(subElement.toString())) {
            						follows.addAll(follow.get(nElement));
            						follow.put(nElement , follows);
            						//System.out.println("Foi adicionado ao follow de " + nElement + " os seguintes simbolos" + follows);
            					}
            				}
        				}
        				follows = new HashSet<GeneralSymbol>();
        			}
        		}
        	
        		i++;
        	}
        } 	
        //System.out.println(follow);
        return follow;
    }
    
    public static boolean isNonTerminal(GeneralSymbol x) {
    	if(x.toString().startsWith("<")) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    
    
    //mÃ©todo para inicializar mapeamento nÃ£oterminais -> conjunto de sÃ­mbolos
    private static Map<Nonterminal, Set<GeneralSymbol>>
    initializeNonterminalMapping(Grammar g) {
    Map<Nonterminal, Set<GeneralSymbol>> result = 
        new HashMap<Nonterminal, Set<GeneralSymbol>>();

    for (Nonterminal nt: g.getNonterminals())
        result.put(nt, new HashSet<GeneralSymbol>());

    return result;
}

} 