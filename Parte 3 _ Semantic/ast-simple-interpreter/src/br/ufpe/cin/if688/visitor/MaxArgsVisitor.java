package br.ufpe.cin.if688.visitor;

import br.ufpe.cin.if688.ast.AssignStm;
import br.ufpe.cin.if688.ast.CompoundStm;
import br.ufpe.cin.if688.ast.EseqExp;
import br.ufpe.cin.if688.ast.Exp;
import br.ufpe.cin.if688.ast.ExpList;
import br.ufpe.cin.if688.ast.IdExp;
import br.ufpe.cin.if688.ast.LastExpList;
import br.ufpe.cin.if688.ast.NumExp;
import br.ufpe.cin.if688.ast.OpExp;
import br.ufpe.cin.if688.ast.PairExpList;
import br.ufpe.cin.if688.ast.PrintStm;
import br.ufpe.cin.if688.ast.Stm;

public class MaxArgsVisitor implements IVisitor<Integer> {

	@Override
	public Integer visit(Stm s) {
		return s.accept(this);
	}

	@Override
	public Integer visit(AssignStm s) {
		return s.getExp().accept(this);
	}

	@Override
	public Integer visit(CompoundStm s) {
		int esq = s.getStm1().accept(this);
		int dir = s.getStm2().accept(this);
		return Math.max(esq, dir);
	}

	@Override
	public Integer visit(PrintStm s) {
		return s.getExps().accept(this);
	}

	@Override
	public Integer visit(Exp e) {
		// TODO Auto-generated method stub
		return e.accept(this);
	}

	@Override
	public Integer visit(EseqExp e) {
		// TODO Auto-generated method stub
		return Math.max(e.getExp().accept(this), e.getStm().accept(this));
	}

	@Override
	public Integer visit(IdExp e) {
		return 1;
	}

	@Override
	public Integer visit(NumExp e) {
		return 1;
	}

	@Override
	public Integer visit(OpExp e) {
		return Math.max(e.getLeft().accept(this), e.getRight().accept(this));
	}

	@Override
	public Integer visit(ExpList el) {
		// TODO Auto-generated method stub
		return el.accept(this);
	}

	@Override
	public Integer visit(PairExpList el) {
		// TODO Auto-generated method stub
		return el.getHead().accept(this) + el.getTail().accept(this);
	}

	@Override
	public Integer visit(LastExpList el) {
		// TODO Auto-generated method stub
		return el.getHead().accept(this);
	}
	

}
