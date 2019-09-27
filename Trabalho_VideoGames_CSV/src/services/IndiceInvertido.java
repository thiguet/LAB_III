package services;

import dao.RAFDAO;

public abstract class IndiceInvertido<MODEL, WORD, HASH> {
	protected RAFDAO<MODEL> model;
	protected RAFDAO<WORD> word;
	protected RAFDAO<HASH> hash;

	public abstract void genWordList();
	public abstract void buildIndiceInvertido();
}
